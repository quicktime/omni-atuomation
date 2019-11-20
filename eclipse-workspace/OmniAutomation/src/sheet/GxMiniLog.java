package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import testcontrol.Main;

/**
 * Handles all GxMini log file actions
 * @author Brendan Dolan
 * @date Created on: Apr 9, 2018
 */
public class GxMiniLog {
	
	private static final String filePath = Main.filesPath + "LogFile.txt";

	/**
	 * Gets all rows from the Gx Mini LogFile
	 * @param filePath - file path of the file
	 * @return all rows in an ArrayList
	 */
	@SuppressWarnings("resource")
	public static String allRows() {
		ArrayList<String> allRows = new ArrayList<String>();
		try { // to catch IOException from read
			BufferedReader input = new BufferedReader(new FileReader(filePath));
			for (int i = 0; i < LineCount.lineCount(filePath); i++) {
				allRows.add(input.readLine());
			}
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		
		return allRows.toString();
	}
	
	/**
	 * Checks if the GxMini LogFile contains the passed-in string
	 * @param string - string to check if it exists in the log file
	 */
	public static void logContains(String string) {
		String allRows = allRows();
		
		if (allRows.contains(string)) {
			Main.debug.LOG("LogFile contains " + string);
		} else {
			Main.debug.LOGError("LogFile contains " + string);
		}
	}
}
