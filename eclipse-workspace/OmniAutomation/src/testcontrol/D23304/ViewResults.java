package testcontrol.D23304;

import helper.Assays;
import mobile.BaseState;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.Navigation;
import mobile.ViewResultsScreen;
import postman.PostmanPut;
import putty.Putty;
import testcontrol.Main;
import winscp.Upload;

/**
 * Handles all test cases for the View Results Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class ViewResults extends BaseState {

	/**
	 * Runs test case OM_VR-1
	 */
	public static void om_vr1Run() {
		String assay[] = Assays.HIV_1_Qual_v2;
		Main.debug.LOG("Starting test case OM_VR-1...");
		
		// literals
		String assayName 			= assay[0];
		String nfcTag				= assay[1];
		String assayLocator			= assay[2];
		
		// setup
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		
		// step 1
		Navigation.toTests();
		
		// step 2 & 3
		Navigation.toViewResults();
		
		// step 4
		Navigation.toTests();
		
		// step 5
		Navigation.toViewResults();
		
		// step 6
		Navigation.toCurrentUser();
		
		// step 7 & 8
		Navigation.toViewResults();
		
		// step 9
		Navigation.toTools();
		
		// step 10
		Navigation.toViewResults();
		
		// step 11
		Navigation.toInfo();
		
		// step 12 & 13
		Navigation.toViewResults();
		
		// step 14 & 15
		Navigation.toTests();
		Navigation.toCurrentUser();
		Navigation.toTools();
		Navigation.toInfo();
		
		// step 16 & 17
		Navigation.toViewResults();
		ViewResultsScreen.headerToday();
		ViewResultsScreen.headerYesterday();
		ViewResultsScreen.headerThisWeek();
		ViewResultsScreen.headerThisMonth();
		ViewResultsScreen.headerSearch();
		
		Main.debug.LOG("Test case OM_VR-1 complete!");
	}
	
	/**
	 * Runs test case OM_VR-2 -- NOT TESTED
	 * SMS connected
	 */
	
	/**
	 * Runs test case OM_VR-3
	 */
	public static void om_vr3Run() {
		String assay[] = Assays.HIV_1_Qual_v2;
		Main.debug.LOG("Starting test case OM_VR-3...");
		
		// literals
		String sampleID				= null;
		String assayName 			= assay[0];
		String nfcTag				= assay[1];
		String assayLocator			= assay[2];
		
		// setup
		MobileTests.startTest(assayLocator);
		sampleID = MobileTests.sampleID;
		MobileWaits.testStarts();
		PostmanPut.sendError("20");
		MobileWaits.testCompletion();
		
		// step 1
		Navigation.toViewResults();
		ViewResultsScreen.sampleIDSearch(sampleID);
		
		// steps 2-10
		ViewResultsScreen.resultsTextExists();
		ViewResultsScreen.userNameTextExists();
		ViewResultsScreen.processorNameExists();
		ViewResultsScreen.endTimeExists();
		
		// step 11-25
		ViewResultsScreen.clickErrors(1);
		ViewResultsScreen.clickErrors(2);
		ViewResultsScreen.clickErrors(3);
				
		
		Main.debug.LOG("Test case OM_VR-3 compete!");
	}
	
	/**
	 * Runs test case OM_VR-4
	 */
	public static void om_vr4Run() {
		String assay[] = Assays.HIV_1_Qual_v2;
		Main.debug.LOG("Starting test case OM_VR-3...");
		
		// literals
		String sampleID				= null;
		String assayName 			= assay[0];
		String nfcTag				= assay[1];
		String assayLocator			= assay[2];
		
		// setup
		Upload.uploadDB(Main.databasesPath + "OM_VR-4\\");
		Putty.restart();
		
		// step 1
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		MobileTests.clickActiveTest();
		MobileTests.startTest(assayLocator);
		MobileWaits.testCompletion();
		MobileTests.clickActiveTest();
		
		// step 3-8
		Navigation.toViewResults();
		
		// step 9-14
		ViewResultsScreen.headerYesterday();
		ViewResultsScreen.headerThisWeek();
		ViewResultsScreen.headerThisMonth();
		
		// step 15-28
		ViewResultsScreen.headerToday();
		ViewResultsScreen.headerYesterday();
		ViewResultsScreen.headerThisWeek();
		ViewResultsScreen.headerThisMonth();
	}
}
