package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import helper.Time;
import testcontrol.Main;

/**
 * Handles putty log information
 * @author Brendan Dolan
 * @date Created on: Mar 27, 2018
 */
public class PuttyLog {
	
	/**
	 * Skips headers from putty.log
	 * Always skips the first 8 rows (log in and info)
	 * @param input - BufferedReader of the putty.log file
	 * @param extra - extra headers to skip
	 */
	public static void skipHeaders(BufferedReader input, int extra) {
		try {
			for (int i = 0; i < 9; i++) {
				input.readLine(); // always skip the first 6 rows
			}
			for (int i = 0; i < extra; i++) {
				input.readLine(); // skip # of extra headers
			}
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
	}

	/**
	 * Gets the output from ps -ef|grep <PROCESS> command
	 * @param log - passed in 
	 * @return - PID from the -ef|grep <PROCESS> command
	 */
	@SuppressWarnings("resource")
	public static String grepOutput(String log) {
		Main.debug.LOG("Extracting PID from putty log...");
		
		String PID = null;
		try { // to catch IOExceptions from readLine()
			BufferedReader input = new BufferedReader(new FileReader(log)); // creates BufferedReader of passed-in log
			skipHeaders(input, 0); // skip the headers
			String fullLine = input.readLine(); // reads the full row
			Main.debug.LOG(fullLine);
			Scanner PIDLine = new Scanner(fullLine); // surround String with Scanner to use delimiters
			PIDLine.useDelimiter(" +"); // sets delimiter to tab
			String skipUser = PIDLine.next(); // gets the next segment (1st segment, user)
			PID	= PIDLine.next(); // gets the next segment (3rd segment, pid)
			Main.debug.LOG("PID is : " + PID);
		} catch (IOException e) {
			Main.debug.LOGWarning("" + e);
		}
		if (PID != null) { // 3rd segment contains data
			Main.debug.LOG("PID Extracted from putty log!");
		} else {
			Main.debug.LOGError("PID was not able to be extracted!");
		}
		return PID; // returns the 3rd segment, the process ID
	}
	
	/**
	 * Gets the output from the putty log
	 * @param extraHeaders - how many extra lines should be skipped
	 * @return an ArrayList of strings, each element is a row in the putty.log file
	 */
	public static ArrayList<String> log(int extraHeaders) {
		Main.debug.LOG("Extracting output from putty log...");
		
		ArrayList<String> output = new ArrayList<String>(); // create a new ArrayList to hold the output from log
		String log = Main.path + "programs\\Putty\\putty.log";
		
		try { // to catch IOExceptions from sheet functions
			BufferedReader input = new BufferedReader(new FileReader(log));
			skipHeaders(input, extraHeaders); // skip the headers
			for (int i = (8 + extraHeaders); i < LineCount.lineCount(log) - 1; i++) { // start at putty log line 8 + extra, go till end of file
				output.add(input.readLine()); // add the rest of the lines to the array list, excluding the last line
			}
		} catch (IOException e) {
			Main.debug.LOGWarning("" + e);
		}
		return output; // return the array list, containing the putty.log output
	}
	
	/**
	 * Checks if log timestamps are more than 4 hrs apart
	 * @param timeStamps ArrayList of timeStamps from {@link helper.StringManipulation#puttyGetFileTimeStamps(ArrayList)}
	 */
	public static void moreThan4HrsApart(ArrayList<String> timeStamps) {
		boolean flag = true; // declare flag. initialize to true
		for (int i = 0; i < timeStamps.size() - 1; i++) {
			String first = timeStamps.get(i); // first file's timestamp
			String second = timeStamps.get(i + 1); // second file's timestamp
			double time1 = Time.UNIXTimeStamp(first); // sets time1 to the first file's timestamp
			double time2 = Time.UNIXTimeStamp(second); // sets time2 to the second file's timestamp
		
			if ((time2 - time1) < 4) { // if difference in time is less than 4 hours
				Main.debug.LOGError("Time between timestamps is less than 4 hours!");
				flag = false; // set flag to false
			}
		}
		if (flag) { // if flag is true
			Main.debug.LOG("Time between timestamps is greater than 4 hours!");
		}
	}
	
	/**
	 * Determines if two timestamps are different
	 * @param firstTimeStamp - first time stamp
	 * @param secondTimeStamp - second time stamp, after an update
	 */
	public static void timeStampUpdated(String firstTimeStamp, String secondTimeStamp) {
		if (!firstTimeStamp.matches(secondTimeStamp)) {
			Main.debug.LOG("File timestamp has been updated.");
		} else {
			Main.debug.LOGError("File timestamp has not been updated.");
		}
		
	}
	
	/**
	 * Checks if the passed-in string is in the putty log
	 * @param string - string to check whether it exists in the log
	 * @param extraHeaders - number of extra headers to skip (# of commands previously run)
	 * cd commands = 1 extraHeader
	 * ls commands = 1 extraHeader
	 */
	public static void logContains(String string, int extraHeaders) {
		ArrayList<String> list = new ArrayList<String>(); // new ArrayList to hold PuttyLog.lsL() output
		list.addAll(PuttyLog.log(extraHeaders)); // add all elements (lines) to list
		if (list.toString().contains(string)) { // if list contains the passed-in string
			Main.debug.LOG(string + " exists.");
		} else { // list does not contain the passed-in string
			Main.debug.LOG(list.toString());
			Main.debug.LOGError(string + " does not exist."); // log as error
		}
	}
	
	/**
	 * Checks if the passed-in string matches the putty.log output
	 * @param string string to see if putty.log matches
	 */
	public static void logMatches(String string, int extraHeaders) {
		ArrayList<String> list = new ArrayList<String>(); // new ArrayList to hold PuttyLog.lsL() outpu
		list.addAll(PuttyLog.log(extraHeaders)); // add all elements (lines) to list
		if (list.toString().matches(string)) { // if list matches the passed-in string
			Main.debug.LOG(string + " exists.");
		} else { // list does not match the passed-in string
			Main.debug.LOG(list.toString());
			Main.debug.LOGError(string + " does not exist."); // log as error
		}
	}
	
	/**
	 * Checks the putty.log output of the previous ls -l command for the passed-in string to not exist
	 * @param string - string to search for
	 */
	public static void logNotContains(String string, int extraHeaders) {
		ArrayList<String> list = new ArrayList<String>(); // new ArrayList to hold PuttyLog.lsL() output
		list.addAll(log(extraHeaders)); // add all elements (lines) to list
		if (!list.contains(string)) { // if list does not contain the passed-in string
			Main.debug.LOG("string does not exist");
		} else { // list does contain the string
			Main.debug.LOGError("string does is exist"); // log as error
		}
	}
	
	/**
	 * Gets number of files in a directory
	 * Used after an ls command
	 * @param extraHeaders - number of extra headers to skip
	 * @return number of files in the directory
	 */
	public static int numberOfFiles(int extraHeaders) {
		Main.debug.LOG("Getting number of files");
		int numberOfFiles = 0;
		ArrayList<String> list = new ArrayList<String>();
		
		list.addAll(log(extraHeaders));
		numberOfFiles = list.size() - 1; // -1 because of the last row
		
		Main.debug.LOG("Number of files is : " + numberOfFiles);
		return numberOfFiles;
	}
}
