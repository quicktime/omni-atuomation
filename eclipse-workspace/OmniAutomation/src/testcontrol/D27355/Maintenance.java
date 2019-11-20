package testcontrol.D27355;

import db_browser.DBBrowser;
import helper.CompareExpected;
import mobile.Navigation;
import mobile.Processor;
import postman.PostmanGet;
import putty.Putty;
import sheet.DBBrowserExport;
import sheet.OmniEpsilonLog;
import testcontrol.Main;
import winscp.Download;

/**
 * Handles all test cases for the Maintenance Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class Maintenance {

	/**
	 * Runs test case Mtn-1 Plunger Cleaning
	 */
	public static void mtn1Run() {
		Main.debug.LOG("Starting test case Mtn-1...");
		
		Processor.weeklyMaintenance();
		// TODO small blue wrench is shown on the Omni Icon on instrument footer
		
		Main.debug.LOG("Test case Mtn-1 passed!");
	}
	
	/**
	 * Runs test case Mtn-2 Plunger Cleaning
	 */
	public static void mtn2Run() {
		Main.debug.LOG("Starting test case Mtn-2...");
		
		String postSelfTest = "postSelfTest";
		String actual_test_name = new String();
		
		Processor.monthlyMaintenance();
		// TODO small blue wrench is shown on the Omni Icon on instrument footer
		Processor.selfTest();
		// TODO Login to c360
		// TODO Select the lab
		// TODO Select instruments drop down section
		// TODO Select the instrument and click Event History
		// There exists a record with Type = 'Self test data' approximately around the time that the self test was completed. 
		// Time is displayed in UTC or GMT.
		Download.downloadDB(Main.databasesPath + "Mtn-2\\");
		DBBrowser.stTest_name(Main.filesPath, postSelfTest);
		actual_test_name = DBBrowserExport.stTest_name(Main.filesPath);
		CompareExpected.compare(actual_test_name, postSelfTest);
		if (PostmanGet.getSelfTest() != null);
		Download.downloadLog(Main.filesPath + "Mtn-2\\");
		OmniEpsilonLog.logContains(Main.filesPath + "Mtn-2\\", "IDLE ==> SELF_TESTING");
		OmniEpsilonLog.logContains(Main.filesPath + "Mtn-2\\", "SELF_TESTING ==> IDLE");
		
		Main.debug.LOG("Test case Mtn-2 passed!");
	}
}
