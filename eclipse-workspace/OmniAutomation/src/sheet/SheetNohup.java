package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import testcontrol.Main;

/**
 * Handles all Nohup sheet actions
 * @author Brendan Dolan
 * @date Created on: Apr 9, 2018
 */
public class SheetNohup {
	
	private static final String filePath = Main.filesPath + "D23308 Master Media\\Installation\\INST-1\\nohup-run-epsilon-java.sh";

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
	 * Checks if the file contains the passed-in string
	 * @param string - string to check if it exists in the log file
	 */
	public static void fileContains(String string) {
		String allRows = allRows();
		
		if (allRows.contains(string)) {
			Main.debug.LOG("File contains " + string);
		} else {
			Main.debug.LOGError("File does not contain " + string);
		}
	}

	/**
	 * Gets parameters from nohup sheets
	 * @param sheet - sheet to get params from
	 * @param line - line number to get param from
	 * @return param as string
	 */
	public static String getParam(String sheet, int line) {
		Main.debug.LOG("Getting param...");
		
		String param = new String();
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(sheet));
			file.readLine(); // skip headers
			for (int i = 0; i < line; i++) {
				file.readLine(); // skip lines up to current line
			}
			param = file.readLine();
			file.close();
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		
		return param;
	}
}
