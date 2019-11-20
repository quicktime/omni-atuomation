package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import testcontrol.Main;

/**
 * Handles file exports
 * @author Brendan Dolan
 * @date Created on: Apr 2, 2018
 */
public class Export {
	
	/**
	 * Gets the order_time, start_time, & end_time from an export file
	 * @param filePath - export file
	 * @return ArrayList<String> of order_time, start_time, end_time
	 */
	@SuppressWarnings("resource")
	public static ArrayList<String> testTimes(String filePath) {
		ArrayList<String> testTimes = new ArrayList<String>();
		try { // to catch IOException readLine
			BufferedReader input = new BufferedReader(new FileReader(filePath));
			for (int i = 0; i < LineCount.lineCount(filePath) - 1; i++) { 
				input.readLine(); // skip up to the last row
			}
			Scanner testLine = new Scanner(input); // creates scanner of BufferedInput
			testLine.useDelimiter(";"); // sets delimiter to ;
			testTimes.add(testLine.next()); // order_time
			testTimes.add(testLine.next()); // start_time
			testTimes.add(testLine.next()); // end_time
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		return testTimes;
	}
	
	/**
	 * Determines whether or not the test times are null
	 * @param arrayList passed-in arrayList from {@link sheet.Export#testTimes(String)}
	 */
	public static void test_timeIsNull(ArrayList<String> arrayList) {
		String order_time = arrayList.get(0);
		String start_time = arrayList.get(1);
		String end_time = arrayList.get(2);
		
		if (!order_time.isEmpty()) { // arrayList is not empty (not null)
			Main.debug.LOG("order_time is not null");
		} else { // arrayList is empty (null)
			Main.debug.LOGError("order_time is null");
		}
		
		if (!start_time.isEmpty()) {
			Main.debug.LOG("start_time is not null");
		} else {
			Main.debug.LOGError("start_time is null");
		}
		
		if (!end_time.isEmpty()) {
			Main.debug.LOG("end_time is not null");
		} else {
			Main.debug.LOGError("end_time is null");
		}
	}

	/**
	 * Determines whether the .xml file contains instrument serial number
	 * @param filePath - file path of the .xml file
	 */
	public static void xmlContainsSN(String filePath) {
		String row = GenericSheet.getRow(filePath, 0);
		if (row.contains("serialNumber=\"")) {
			Main.debug.LOG(".xml file contains instrument S/N");
		} else {
			Main.debug.LOGError(".xml file does not contain instrument S/N");
		}
	}
	
	/**
	 * Determines whether the .xml file contains GPS coords
	 * @param filePath - file path of the .xml file
	 */
	public static void xmlContainsGPS(String filePath) {
		String row = GenericSheet.getRow(filePath, 0);
		if (row.contains("longtitude=\"") && row.contains("latitude=\"")) {
			Main.debug.LOG(".xml file contains longitude");
		} else {
			Main.debug.LOGError(".xml file does not contain GPS coords:");
		}
	}
}
