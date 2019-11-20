package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import testcontrol.Main;

/**
 * Counts # of lines in data sheet
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class LineCount {
	
	/**
	 * Gets # of lines in data sheet
	 * @param sheet - data sheet to obtain number of lines from
	 * @return int, number of lines from the data sheet param
	 */
	public static int lineCount(String sheet) {
		int rowCount = 0;
		try {
			BufferedReader inSheet = new BufferedReader(new FileReader(sheet)); // creates BufferedReader of passed-in sheet		
			String input; // declaring String to be used later
			rowCount = 0; // counter for how many lines there are in the inSheet file
			inSheet.readLine(); // if you want to skip the first line (headers)
			while ((input = inSheet.readLine()) != null) { // if there is any data in the row, data gets put into input. If input is null, there is no data in that row, break
				rowCount++; // add one row to the counter each time
			}
			inSheet.close(); // always close BufferedReaders
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		return rowCount; // returns the count of rows
	}
}
