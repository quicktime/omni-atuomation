package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import testcontrol.Main;

/**
 * Handles DB browser export information
 * @author Brendan Dolan
 * @date Created on: Apr 3, 2018
 */
public class DBBrowserExport {
	
	/**
	 * Gets the test GUID from db browser export
	 * @param dbFilePath passed-in filepath for the export
	 * @return String of the test GUID
	 */
	public static String testGUID(String dbFilePath) {
		String testGUID = new String(); // declare new String
		
		try { // to catch IOExceptions from readLine()
			BufferedReader input = new BufferedReader(new FileReader(dbFilePath + "GUID.txt"));
			testGUID = input.readLine(); // initialize testGUID as the first (and only) line
			input.close();
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		
		return testGUID;
	}
	
	/**
	 * Gets the test_name from db browser export
	 * @param dbFilePath passed-in filepath for the export
	 * @return String of the test_name
	 */
	public static String stTest_name(String dbFilePath) {
		String testName = new String(); // declare new String
		
		try { // to catch IOExceptions from readLine()
			BufferedReader input = new BufferedReader(new FileReader(dbFilePath + "test_name.txt"));
			testName = input.readLine(); // initialize testName as the first (and only) line
			input.close();
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		return testName;
	}
	
	/**
	 * Gets the schema_version from db browser export
	 * @param dbFilePath - passed-in filepath for the export
	 * @return schema_version as string
	 */
	public static String dbvSchema_version(String dbFilePath) {
		String schema_version = new String();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(dbFilePath + "schema_version.txt"));
			schema_version = input.readLine();
			input.close();
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		
		return schema_version;
	}
	
	
}
