package testcontrol.D23304;

import java.util.ArrayList;

import helper.Assays;
import helper.CompareExpected;
import mobile.HandHeld;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.Navigation;
import mobile.PendingCommands;
import mobile.Processor;
import mobile.ViewResultsScreen;
import putty.Putty;
import sheet.Export;
import sheet.PuttyLog;
import testcontrol.Main;
import winscp.Download;

/**
 * Handles all test cases for the Manage Results Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class ManageResults {

	/**
	 * Runs test case OM_MR-1: Test results are not permanently stored on the Mobile Device
	 */
	public static void om_mr1Run() {
		Main.debug.LOG("Starting test case OM_MR-1...");
		
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String sampleID[] 			= new String[4];
		String assayName 			= assay[0];
		String nfcTag				= assay[1];
		String assayLocator			= assay[2];
		
		// setup
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		// step 1
		Main.debug.LOG("Step 1");
		MobileTests.startTest(assayLocator);
		sampleID[0] = MobileTests.sampleID;
		MobileWaits.testCompletion();
		MobileTests.clickActiveTest();
		MobileTests.startTest(assayLocator);
		sampleID[1] = MobileTests.sampleID;
		MobileWaits.testCompletion();
		MobileTests.clickActiveTest();
		MobileTests.startTest(assayLocator);
		sampleID[2] = MobileTests.sampleID;
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		sampleID[3] = MobileTests.sampleID;
		MobileWaits.testCompletion();
		
		// step 2
		Main.debug.LOG("Step 2");
		Processor.removeInstrument();
		
		// step 3
		Main.debug.LOG("Step 3");
		HandHeld.sync();
		
		// step 4
		// TODO: Verify no instrument connected
		
		// step 5
		Main.debug.LOG("Step 5");
		MobileTests.noActiveTests();
		
		//step 6
		Main.debug.LOG("Step 6");
		ViewResultsScreen.testNotExists(sampleID[0]);
		ViewResultsScreen.testNotExists(sampleID[1]);
		
		// steps 7 - 19
		Main.debug.LOG("Steps 7-19");
		Main.debug.LOGWarning("Steps 7-19 need to be performed manually!");
		
		Main.debug.LOG("Automated test case OM_MR-1 completed!");
	}
	
	/**
	 * Runs test case OM_MR-2 -- NOT TESTED
	 * Disconnecting and connecting multiple instruments
	 */
	
	/**
	 * Runs test case OM_MR-3
	 */
	public static void om_mr3Run() {
		Main.debug.LOG("Starting test case OM_MR-3...");
		
		String assay[] = Assays.HIV_1_Qual_v2;
		String localFilePath = Main.filesPath + "D23304 Test Results\\Manage Results\\OM_MR-3\\";
		
		ArrayList<String> log 		= new ArrayList<String>();
		String sampleID[] 			= new String[2];
		String assayName 			= assay[0];
		String nfcTag				= assay[1];
		String assayLocator			= assay[2];
		int actualNumberOfFiles 	= 0;
		int expectedNumberOfFiles 	= 4;
		
		// setup
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		// step 1
		Main.debug.LOG("Step 1");
		MobileTests.startTest(assayLocator);
		sampleID[0] = MobileTests.sampleID;
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		sampleID[1] = MobileTests.sampleID;
		
		// step 2
		Main.debug.LOG("Step 2");
		HandHeld.sync();
		
		// step 3
		Main.debug.LOG("Step 3");
		Putty.typeKeys("cd /sandbox/omni-data/tests/out/");
		Putty.lsL();
		log = PuttyLog.log(2);
		actualNumberOfFiles = PuttyLog.numberOfFiles(2);
		CompareExpected.compare(actualNumberOfFiles, expectedNumberOfFiles);
		
		// step 4
		Main.debug.LOG("Step 4");
		Download.downloadFile("out", localFilePath, "/sandbox/omni-data/tests/out");
		Export.xmlContainsSN(localFilePath + "out\\" + log.get(0));
		
		// step 5
		Main.debug.LOG("Step 5");
		Export.xmlContainsGPS(localFilePath + "out\\" + log.get(0));
		
		// steps 6 - 8
		// TODO: c360 analytics test results
		
		// steps 9 - 12
		Main.debug.LOG("Steps 9-12");
		Main.debug.LOGError("Steps 9-12 need to be performed manually!");
		
		Main.debug.LOG("Automated test case OM_MR-3 completed!");
	}
}
