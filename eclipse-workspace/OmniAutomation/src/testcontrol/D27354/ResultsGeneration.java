package testcontrol.D27354;

import java.util.ArrayList;

import com.borland.silktest.jtf.Utils;

import db_browser.DBBrowser;
import helper.Assays;
import helper.CompareExpected;
import helper.StringManipulation;
import mobile.MobileTests;
import mobile.MobileWaits;
import putty.Putty;
import sheet.DBBrowserExport;
import sheet.PuttyLog;
import testcontrol.Main;
import winscp.Download;

/**
 * Handles all test cases for the Results Generation Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class ResultsGeneration {

	/**
	 * Runs test case RG-1
	 */
	public static void rg1Run() {
		Main.debug.LOG("Starting test case RG-1...");
		String assay[] = Assays.RG1;
		String dbFilePath = Main.path + "files\\databases\\rg-1\\";
		
		// literals
		String lsTestGUID 					= new String();
		String dbTestGUID					= new String();
		String lsXMLTimeStamp				= new String();
		String lsGXMTimeStamp				= new String();
		ArrayList<String> lsOutput				= new ArrayList<String>();
		ArrayList<String> backupTimeStamp1	= new ArrayList<String>();
		ArrayList<String> backupTimeStamp2	= new ArrayList<String>();
		String assayName 					= assay[0];
		String nfcTag						= assay[1];
		String assayLocator					= assay[2];
		
		Putty.configuration();
		// step 1
		Main.debug.LOG("Step 1");
		Putty.deleteBak();
		Putty.restart();
		Utils.sleep(70000); // TODO wait for instrument to restart
		Putty.configuration();
		Putty.typeKeys("cd /sandbox/omni-data/backup");
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains("epsilon.bak", 2);
		
		// step 2
		Main.debug.LOG("Step 2");
		Putty.configuration();
		Putty.deleteDB();
		Putty.restart();
		Utils.sleep(70000); // TODO waits
		Putty.configuration();
		Putty.goToOmniData();
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains("epsilon.db", 2);
		
		// step 3
		Main.debug.LOG("Step 3");
		Putty.configuration();
		Putty.deleteDBBak();
		Putty.restart();
		Utils.sleep(70000); // TODO waits
		Putty.configuration();
		Putty.typeKeys("cd /sandbox/omni-data");
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains("epsilon.db", 2);
		
		// step 4
		Main.debug.LOG("Step 4");
		Putty.configuration();
		Putty.typeKeys("cd /sandbox/omni-data/backup");
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains("epsilon.bak", 2);
		lsOutput = PuttyLog.log(2); // sets lsOutput to the output from putty log (previous ls command in this case)
		backupTimeStamp1 = StringManipulation.puttyGetFileTimeStamps(lsOutput); // sets backupTimeStamp1 to the intial timestamp of the epsilon.bak file
		
		// step 5
		Main.debug.LOG("Step 5");
//		MobileTests.startTest(assayLocator);
		
		// step 6
		Main.debug.LOG("Step 6");
		Putty.configuration();
		Putty.typeKeys("cd /sandbox/omni-data/backup");
		Putty.lsLTr();
		Putty.close();
		lsOutput = PuttyLog.log(2); // resets lsOutput to the updated output from putty log (previous ls command in this case)
		backupTimeStamp2 = StringManipulation.puttyGetFileTimeStamps(lsOutput); // sets backupTimeStamp2 to the second timestamp of the epsilon.bak file
		PuttyLog.timeStampUpdated(backupTimeStamp1.get(0), backupTimeStamp2.get(0)); // checks if the timestamp has changed after the test run
		
		// step 7
		Main.debug.LOG("Step 7");
		Download.downloadDB(dbFilePath);
		
		// step 8
		Main.debug.LOG("Step 8");
		DBBrowser.open();
		DBBrowser.testTable();
		DBBrowser.close();
		
		// step 9
		Main.debug.LOG("Step 9");
		Putty.configuration();
		Putty.typeKeys("cd /sandbox/omni-data/tests/in");
		
		// step 10
		Main.debug.LOG("Step 10");
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains(".gxm", 2);
		PuttyLog.logContains(".xml", 2);
		
		// steps 11-13
		Main.debug.LOG("Steps 11-13");
		PuttyLog.logMatches("(.*)" + assayName + "_(.*)_(.*).gxm.*", 2); // assayname_testGUID_timestamp.gxm
		PuttyLog.logMatches("(.*)" + assayName + "_(.*)_(.*).xml.*", 2); // assayname_testGUID_timestamp.xml
		
		// step 14
		Main.debug.LOG("Step 14");
		lsOutput = PuttyLog.log(2); // resets lsOutput to the updated output from putty log (previous ls command in this case)
		lsTestGUID = StringManipulation.puttyTestGUID(lsOutput, 1); // sets lsTestGUID to the last run test GUID, pulled from the /tests/in/ folder
		Main.debug.LOG(lsTestGUID);
		DBBrowser.open();
		DBBrowser.testGUID(dbFilePath);
		DBBrowser.close();
		dbTestGUID = DBBrowserExport.testGUID(dbFilePath); // sets dbTestGUID to the test GUID from the DB Browser export
		Main.debug.LOG(dbTestGUID);
		CompareExpected.compare(lsTestGUID, dbTestGUID); // they should be the same
		
		// step 15
		Main.debug.LOG("Step 15");
		lsXMLTimeStamp = StringManipulation.puttyTestTimeStamp(lsOutput, 1); // sets lsXMLTimeStamp to the last test's xml file timestamp from lsOutput
		lsGXMTimeStamp = StringManipulation.puttyTestTimeStamp(lsOutput, 2); // sets lsGXMTimeStamp to the last test's gxm file timestamp from lsOutput
		CompareExpected.compare(lsXMLTimeStamp, lsGXMTimeStamp); // they should be the same
		
		Main.debug.LOG("Test case RG-1 passed!");
	}
	
	/**
	 * Runs test case RG-2 - NOT TESTED
	 * Open XML in browser
	 * Observe info
	 */
	
	/**
	 * Runs test case RG-3
	 */
	public static void rg3Run() {
		Main.debug.LOG("Starting test case RG-1");
		String assay[] = Assays.HIV_1_Qual_v2;
		
		// literals
		String sampleID						= new String();
		String sampleID2					= new String();
		String assayName 					= assay[0];
		String nfcTag						= assay[1];
		String assayLocator					= assay[2];
		
		// step 1
		Main.debug.LOG("Step 1");
//		MobileTests.startTest(assayLocator);
//		sampleID = MobileTests.sampleID;
		
		// step 2
		Main.debug.LOG("Step 2");
		Putty.configuration();
		Putty.killProcess("GxNext");
		
		// step 3
		Main.debug.LOG("Step 3");
		Putty.configuration();
		
		// step 4
		Main.debug.LOG("Step 4");
		Putty.typeKeys("cd /sandbox/omni-data/tests/in");
		
		// step 5
		Main.debug.LOG("Step 5");
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logNotContains(sampleID, 2);
//		MobileWaits.testCompletion();
		
		// step 6
		Main.debug.LOG("Step 6");
//		MobileTests.startTest(assayLocator);
//		sampleID2 = MobileTests.sampleID;
		
		// step 7
		Main.debug.LOG("Step 7");
		Putty.configuration();
		Putty.killProcess("GxCellCoreGateway");
		
		// step 8
		Main.debug.LOG("Step 8");
		Putty.configuration();
		
		// step 9
		Main.debug.LOG("Step 9");
		Putty.typeKeys("cd /sandbox/omni-data/tests/in");
		
		// step 10
		Main.debug.LOG("Step 10");
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logNotContains(sampleID2, 2);
		Putty.configuration();
		Putty.restart();
		Putty.close();
	}
}
