package testcontrol.D27354;

import com.borland.silktest.jtf.Utils;

import db_browser.DBBrowser;
import helper.CompareExpected;
import mobile.MobileTests;
import mobile.Navigation;
import mobile.Processor;
import putty.Putty;
import sheet.DBBrowserExport;
import testcontrol.Main;
import winscp.Download;

/**
 * Handles all test cases for the Self-Test Generation Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class SelfTestGeneration {

	/**
	 * Runs test case STG-1
	 */
	public static void stg1Run() {
		Main.debug.LOG("Starting test case STG-1...");
		
		String expected_test_name = "postSelfTestData";
		String actual_test_name = new String();
		
		Navigation.toTools();
		Processor.selfTest();
		Utils.sleep(40000); // TODO MobileWaits
		Download.downloadDB(Main.databasesPath + "STG-1");
		DBBrowser.stTest_name(Main.filesPath, "postSelfTestData");
		actual_test_name = DBBrowserExport.stTest_name(Main.filesPath);
		CompareExpected.compare(actual_test_name, expected_test_name);
		// TODO Login to c360
		// TODO Select the lab
		// TODO open the lab instruments drop down
		// TODO Select the insturment and click the Event History
		// There exists a record with Type = 'Self test data' approximately around the time that the self test was completed. 
		// Time is displayed in UTC or GMT.
		
		Main.debug.LOG("Test case STG-1 passed!");
	}
	
	/**
	 * Runs test case STG-2
	 */
	public static void stg2Run() {
		Main.debug.LOG("Starting test case STG-2...");
		
		String okToLoadData = "okToLoadData";
		String okToRunData = "okToRunData";
		String actual_test_name = new String();
		
		Putty.configuration();
		Putty.deleteDBBak();
		Putty.restart();
		Putty.close();
		MobileTests.startTest();
		DBBrowser.stTest_name(Main.filesPath, okToLoadData);
		actual_test_name = DBBrowserExport.stTest_name(Main.filesPath);
		CompareExpected.compare(actual_test_name, okToLoadData);
		DBBrowser.stTest_name(Main.filesPath, okToRunData);
		actual_test_name = DBBrowserExport.stTest_name(Main.filesPath);
		CompareExpected.compare(actual_test_name, okToRunData);
		// TODO Login to c360
		// TODO Select the lab
		// TODO Open the lab instruments drop down
		// TODO Select the instrument and click the Event History
		// There exists a record with Type = 'Self test data' approximately around the time that the self test was completed.
		// The Description will contain the string: 
		// "SelfTest name": "okToRunData" 
		// Time is displayed in UTC or GMT.

		Main.debug.LOG("Test case STG-2 passed!");
	}
}
