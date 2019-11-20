package db_browser;

import com.borland.silktest.jtf.common.types.TextPosition;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.win32.AccessibleControl;

import testcontrol.Main;

import com.borland.silktest.jtf.Dialog;
import com.borland.silktest.jtf.TextField;

/**
 * Performs all actions for the DB Browser
 * @author Brendan Dolan
 * @date Created on: Apr 2, 2018
 */
public class DBBrowser extends BaseState {
	
	private static String testDirectory = Main.databasesPath + "test\\";

	/**
	 * Opens the epsilon.db at the test directory
	 * @param filepath - the passed-in filepath
	 */
	public static void open() {
		run();
		getDesktop().waitForObject("DB Browser for SQLite", 10000);
		window().setActive();
		window().maximize();
		window().typeKeys("<Left Ctrl+o>");
		textField("DB Browser for SQLite.Choose a database file.File name").setText(testDirectory + "epsilon.db"); 
		pushButton("DB Browser for SQLite.Choose a database file.Open").select();
	}
	
	/**
	 * Goes to the test table of the database
	 */
	public static void testTable() {
		window("DB Browser for SQLite - swattestexcc").setActive();
		accessibleControl("DB Browser for SQLite - swattestexcc.Browse Data").click();
		accessibleControl("DB Browser for SQLite - swattestexcc.Table Down").click();
		for (int i = 0; i < 9; i++) { // always go-to the top row
			window("DB Browser for SQLite - swattestexcc").typeKeys("<UP>");
		}
		for (int i = 0; i < 8; i++) { // go down to the test row
			window("DB Browser for SQLite - swattestexcc").typeKeys("<DOWN>");
		}
		window("DB Browser for SQLite - swattestexcc").typeKeys("<Enter>");
	}
	
	/**
	 * Goes to the test table of the database
	 */
	public static void self_testTable() {
		open();
		window("DB Browser for SQLite - swattestexcc").setActive();
		accessibleControl("DB Browser for SQLite - swattestexcc.Browse Data").click();
		accessibleControl("DB Browser for SQLite - swattestexcc.Table Down").click();
		for (int i = 0; i < 9; i++) { // always go-to the top row
			window("DB Browser for SQLite - swattestexcc").typeKeys("<UP>");
		}
		for (int i = 0; i < 7; i++) { // go down to the test row
			window("DB Browser for SQLite - swattestexcc").typeKeys("<DOWN>");
		}
		window("DB Browser for SQLite - swattestexcc").typeKeys("<Enter>");
	}
	
	/**
	 * Exports the last test_name field from the self_test table
	 * @param filePath - file path to save the exported data to
	 */
	public static void stTest_name(String filePath, String testName) {
		self_testTable();
		pushButton("DB Browser for SQLite - swattestexcc.Last Row").select();
		if (exists("DB Browser for SQLite - swattestexcc." + testName)) {
			accessibleControl("DB Browser for SQLite - swattestexcc." + testName).click();
			window("DB Browser for SQLite - swattestexcc").typeKeys("<Left Alt+e>"); // shortcut for export
			window("DB Browser for SQLite - swattestexcc").typeKeys("<Enter>");
			textField("DB Browser for SQLite - swattestexcc.Choose a filename to export data.TextField")
					.setText(filePath + "test_name.txt");
			pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Save").click();
			if (exists("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")) {
				pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")
						.select();
			}
		} else {
			Main.debug.LOGError(testName + " does not exist in the database!");
		}
	}
	
	/**
	 * Runs the order_time.sql script to get all test times. Saves the test times to a passed-in file
	 * @param csv - file to save the order_time.sql output
	 */
	public static void testTimes(String filePath) {
		window("DB Browser for SQLite - swattestexcc").setActive();
		accessibleControl("DB Browser for SQLite - swattestexcc.Execute SQL").click();
		pushButton("DB Browser for SQLite - swattestexcc.Open SQL file").select();
		textField("DB Browser for SQLite - swattestexcc.Select SQL file to open.File name")
				.setText(Main.path + "files\\order_time.sql");
		pushButton("DB Browser for SQLite - swattestexcc.Select SQL file to open.Open").select();
		pushButton("DB Browser for SQLite - swattestexcc.Execute SQL PushButton").select();
		accessibleControl("DB Browser for SQLite - swattestexcc.menu button").click();
		accessibleControl("Export to CSV Alt+C").click();
		accessibleControl("DB Browser for SQLite - swattestexcc.Export data as CSV.Field seperator").click();
		for (int i = 0; i < 5; i++) { // always go to the top row
			window("DB Browser for SQLite - swattestexcc.Export data as CSV").typeKeys("<UP>");
		}
		window("DB Browser for SQLite - swattestexcc.Export data as CSV").typeKeys("<Down>");
		accessibleControl("DB Browser for SQLite - swattestexcc.Export data as CSV.Field seperator").click();
		accessibleControl("DB Browser for SQLite - swattestexcc.Export data as CSV.New line chars").click();
		for (int i = 0; i < 3; i++) { // always go to the top row
			window("DB Browser for SQLite - swattestexcc.Export data as CSV").typeKeys("<UP>");
		}
		window("DB Browser for SQLite - swattestexcc.Export data as CSV").typeKeys("<Down>");
		accessibleControl("DB Browser for SQLite - swattestexcc.Export data as CSV.New line chars").click();
		pushButton("DB Browser for SQLite - swattestexcc.Export data as CSV.OK Enter").select();
		textField("DB Browser for SQLite - swattestexcc.Choose a filename to export data.TextField")
				.setText(filePath + "testTimes.csv");
		pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Save")
				.select();
		if (exists("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")) {
			pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")
					.select();
		}
		pushButton("DB Browser for SQLite - swattestexcc.Export data as CSV.DB Browser for SQLite.OK Enter").select();
	}
	
	/**
	 * Saves the last run test GUID to a file in the same directory
	 */
	public static void testGUID(String filePath) {
		testTable();
		pushButton("DB Browser for SQLite - swattestexcc.Last Row").select();
		window("DB Browser for SQLite - swattestexcc").typeKeys("<Left Alt+e>"); // shortcut for export
		window("DB Browser for SQLite - swattestexcc").typeKeys("<Enter>");
		textField("DB Browser for SQLite - swattestexcc.Choose a filename to export data.TextField")
				.setText(filePath + "GUID.txt");
		pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Save").click();
		if (exists("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")) {
			pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")
					.select();
		}
	}
	
	/**
	 * Goes to the dbVersion table 
	 */
	public static void dbVersionTable() {
		open();
		window("DB Browser for SQLite - swattestexcc").setActive();
		accessibleControl("DB Browser for SQLite - swattestexcc.Browse Data").click();
		accessibleControl("DB Browser for SQLite - swattestexcc.Table Down").click();
		for (int i = 0; i < 9; i++) { // always go-to the top row
			window("DB Browser for SQLite - swattestexcc").typeKeys("<UP>");
		}
		for (int i = 0; i < 3; i++) { // go down to the test row
			window("DB Browser for SQLite - swattestexcc").typeKeys("<DOWN>");
		}
		window("DB Browser for SQLite - swattestexcc").typeKeys("<Enter>");
	}
	
	/**
	 * Saves the scehma version to a file in the passed-in directory
	 * @param filePath - filepath to save the export file to
	 */
	public static void schema_version(String filePath) {
		dbVersionTable();
		pushButton("DB Browser for SQLite - swattestexcc.Last Row").select();
		for (int i = 0; i < 6; i++) {
			window("DB Browser for SQLite - swattestexcc").typeKeys("<Tab>");
		}
		window("DB Browser for SQLite - swattestexcc").typeKeys("<Left Alt+e>"); // shortcut for export
		window("DB Browser for SQLite - swattestexcc").typeKeys("<Enter>");
		textField("DB Browser for SQLite - swattestexcc.Choose a filename to export data.TextField")
				.setText(filePath + "schema_version.txt");
		pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Save").click();
		if (exists("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")) {
			pushButton("DB Browser for SQLite - swattestexcc.Choose a filename to export data.Confirm Save As.Yes")
					.select();
		}
	}
	
	/**
	 * Closes the DB Browser
	 */
	public static void close() {
		window("DB Browser for SQLite - swattestexcc").close();
	}
}