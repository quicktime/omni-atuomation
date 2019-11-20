package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import testcontrol.Main;

/**
 * Handles generic file reads
 * @author Brendan Dolan
 * @date Created on: Apr 11, 2018
 */
public class GenericSheet {
	
	/**
	 * Gets the passed-in row
	 * @param sheet - file to get row from
	 * @param line - line number to get row 
	 * @return string of the full row
	 */
	public static String getRow(String sheet, int line) {
		Main.debug.LOG("Getting row...");
		
		String row = new String();
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(sheet));
			file.readLine(); // skip headers
			for (int i = 0; i < line; i++) {
				file.readLine();
			}
			row = file.readLine();
			file.close();
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		
		Main.debug.LOG("Got row --- " + row);
		return row;
	}
}
