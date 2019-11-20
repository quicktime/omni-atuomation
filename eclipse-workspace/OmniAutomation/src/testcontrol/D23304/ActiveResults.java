package testcontrol.D23304;

import java.io.IOException;

import helper.Assays;
import helper.CompareExpected;
import mobile.BaseState;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.Navigation;
import mobile.ViewResultsScreen;
import postman.PostmanPut;
import sheet.LineCount;
import sheet.SheetTestErrors;
import testcontrol.Main;

import org.junit.Assert;

import com.borland.silktest.jtf.Utils;

/**
 * Handles all test cases for the Active Results Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class ActiveResults extends BaseState {
	
	/**
	 * Base path for all Active Results sheets
	 */
	public final static String sheetsPath = "\\\\SWATTESTEXCC\\Omni_Validation_Scripts\\Omni_Automation_Master\\sheets\\D23304 Test Results\\Active Results\\OM_VAR-";
	
	/**
	 * Runs test case OM_VAR-1
	 */
	public static void om_var1Run() {
		String assay[] = Assays.HIV_1_Qual_v2;
		
		Main.debug.LOG("Starting test case OM_VAR-1...");
				
		// literals
		String sampleID				= null;
		int expectedActiveTests		= 0;
		int actualActiveTests		= 0;
		String assayName 			= assay[0];
		String nfcTag				= assay[1];
		String assayLocator			= assay[2];
		
		// step 1
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		
		// step 2
		Navigation.toTests();
		expectedActiveTests = 3; // number of tests ran
		actualActiveTests = MobileTests.activeTestCount();
		CompareExpected.compare(actualActiveTests, expectedActiveTests);
		
		// step 3
		MobileTests.startTest(assayLocator);
		sampleID = MobileTests.sampleID;
		MobileWaits.testStarts();
		Navigation.toViewResults();
		ViewResultsScreen.runningTest(sampleID);
		
		// step 4
		Navigation.toTests();
		Navigation.toViewResults();
		Navigation.toTests();
		Navigation.toViewResults();
		MobileWaits.testCompletion();
		
		// step 5
		MobileTests.startTest(assayLocator);
		MobileWaits.testStarts();
		MobileTests.stopTest();
		
		// step 6
		Navigation.toTests();
		expectedActiveTests = 5;
		actualActiveTests = MobileTests.activeTestCount();
		CompareExpected.compare(actualActiveTests, expectedActiveTests);
		
		// step 7
		MobileTests.startTest(assayLocator);
		MobileWaits.testStarts();
		String errorSampleID = MobileTests.sampleID;
		PostmanPut.sendError("20");
		MobileWaits.testCompletion();
		
		// step 8 & 9
		Navigation.toTests();
		expectedActiveTests = 6;
		actualActiveTests = MobileTests.activeTestCount();
		CompareExpected.compare(actualActiveTests, expectedActiveTests);
		
		// step 10 & 11
		Main.debug.LOGWarning("View bitmap to verify steps 10 & 11");
		
		// step 12
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		MobileWaits.testStarts();
		Utils.sleep(10000);
		MobileTests.stopTest();
		
		// step 13
		Navigation.toTests();
		Main.debug.LOGWarning("View bitmap to verify steps 13 - 17");	
	}
	
	/**
	 * Runs test case OM_VAR-2
	 */
	public static void om_var2Run() {
		Main.debug.LOG("Starting test case OM_VAR-2...");
		String assay[] = Assays.HIV_1_Qual_v2;
		
		// literals
		int beforeClick;
		int afterClick;
		String sampleID1 			= new String();
		String sampleID2			= new String();
		String assayName 			= assay[0];
		String nfcTag				= assay[1];
		String assayLocator			= assay[2];
		
		// step 0
		MobileTests.startTest(assayLocator);
		sampleID1 = MobileTests.sampleID;
		Main.debug.LOG(sampleID1);
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		sampleID2 = MobileTests.sampleID;
		Main.debug.LOG(sampleID2);
		MobileWaits.testStarts();
		PostmanPut.sendError("20");
		
		// step 1
		Navigation.toTests();
		beforeClick = MobileTests.activeTestCount();
		// TODO: Click the 1st test (w/o errors)
		Navigation.toTests();
		afterClick = MobileTests.activeTestCount();
		CompareExpected.different(afterClick, beforeClick);
		
		// steps 2 & 3
		ViewResultsScreen.testExists(sampleID1);
		
		// step 4
		Navigation.toTests();
		beforeClick = MobileTests.activeTestCount();
		// TODO: Click the 1st test (w/errors);
		Navigation.toTests();
		afterClick = MobileTests.activeTestCount();
		CompareExpected.different(afterClick, beforeClick);
		
		// step 5 & 6
		ViewResultsScreen.testExists(sampleID2);
	}
}
