package testcontrol.D27355;

import com.borland.silktest.jtf.Utils;

import helper.Assays;
import helper.CompareErrors;
import helper.CompareExpected;
import mobile.MobileTestErrors;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.Navigation;
import mobile.Processor;
import postman.PostmanGet;
import postman.PostmanPut;
import putty.OmniReset;
import putty.Putty;
import sheet.GxMiniLog;
import sheet.PuttyLog;
import sheet.SheetTestErrors;
import testcontrol.Main;
import testcontrol.D23307.OmniInstrument;
import winscp.Download;
import winscp.Upload;

/**
 * Handles all test cases for the Instrument Status Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class InstrumentStatus {
	
	public static final String sheetsPath = Main.sheetsPath + "D27355 Instrument Status and Maintenance\\Instrument Status\\";
	public static final String filesPath = Main.filesPath + "D27355 Instrument Status and Maintenance\\Instrument Status\\";

	/**
	 * Runs test case ISt-1 Instrument: Off-line
	 */
	
	/**
	 * Runs test case ISt-2 Instrument: On-Line
	 */
	
	/**
	 * Runs test case ISt-3 Instrument Status - Busy
	 */
	public static void ist3Run() {
		Main.debug.LOG("Starting test case ISt-3...");
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String actualInstrumentState 		= new String();
		String actualDoorStatus 			= new String();
		String actualCartStatus 			= new String();
		String actualStatus 				= new String();
		String expectedInstrumentState 		= "WAITING_FOR_CART";
		String expectedDoorStatus 			= "OPEN";
		String expectedCartStatus 			= "PRESENT";
		String expectedStatus 				= "Busy";
		String doorStatus 					= new String();
		String assayName 					= assay[0];
		String nfcTag 						= assay[1];
		String assayLocator 				= assay[2];
		
		MobileTests.almostStartTest(assayLocator);
		actualInstrumentState = PostmanGet.getInstrumentState();
		actualDoorStatus = PostmanGet.getDoorStatus();
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		CompareExpected.compare(actualDoorStatus, expectedDoorStatus);
		MobileTests.insertCart();
		expectedInstrumentState = "LOADING_CART";
		actualInstrumentState = PostmanGet.getInstrumentState();
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		// TODO observe instrument icon. shows instrument contains a cart
		actualCartStatus = PostmanGet.getCartStatus();
		CompareExpected.compare(actualCartStatus, expectedCartStatus);
		MobileWaits.testStarts();
		Navigation.toProcessor();
		actualStatus = Processor.getStatus();
		CompareExpected.compare(actualStatus, expectedStatus);
		MobileWaits.testCompletion();
		// TODO observe instrument icon. shows instrument contains a cart
		Download.downloadGxMiniLog(filesPath + "ISt-3\\");
		GxMiniLog.logContains("STATE_CARTRIDGE_PRELOAD");
		GxMiniLog.logContains("STATE_WAITING_FOR_CART");
		GxMiniLog.logContains("STATE_LOADING_CART");
		GxMiniLog.logContains("STATE_CARTRIDGE_RECIEVED");
		GxMiniLog.logContains("STATE_CARTRIDGE_LOADED");
		GxMiniLog.logContains("STATE_RUNNING_ASSAY");
		Navigation.toProcessor();
		actualStatus = Processor.getStatus();
		expectedStatus = "Available";
		CompareExpected.compare(actualStatus, expectedStatus);
		Processor.restartInstrument();
		Utils.sleep(2000);
		actualInstrumentState = PostmanGet.getInstrumentState();
		expectedInstrumentState = "INITIALIZING";
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		
		Main.debug.LOG("Test case ISt-3 passed!");
	}
	
	/**
	 * Runs test case ISt-4 Instrument Status - Error
	 */
	public static void ist4Run() {
		Main.debug.LOG("Starting test case ISt-4...");
		String assay[] = Assays.Omni_Field_Test_v2;
		
		String sheet 						= sheetsPath + "ist4.csv";
		String localFilePath 				= filesPath + "ISt-4\\blocking\\";
		String remoteFilePath 				= "//sandbox//epsilon-java//Epsilon_Java_Jar-1.0";
		String file 						= "nohup-run-epsilon-java.sh";
		String assayName 					= assay[0];
		String nfcTag 						= assay[1];
		String assayLocator 				= assay[2];
		String sendCode 					= SheetTestErrors.getErrorSendCode(sheet, 0);
		String sheetErrorMessage 			= SheetTestErrors.getErrorMessage(sheet, 0);
		String mobileErrorMessage 			= new String();
		String expectedInstrumentState 		= new String();
		String actualInstrumentState 		= new String();
		
		MobileTests.startTest(assayLocator);
		MobileWaits.testStarts();
		PostmanPut.sendError(sendCode);
		MobileWaits.testErrorAppears();
		mobileErrorMessage = MobileTestErrors.getErrorMessage(0);
		CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
		// TODO: Observe module icon
		MobileWaits.testCompletion();
		OmniReset.reset();
		Upload.uploadFile(file, localFilePath, remoteFilePath);
		Putty.restart();
		MobileTests.startTest(assayLocator);
		MobileWaits.testStarts();
		sendCode = SheetTestErrors.getErrorSendCode(sheet, 1);
		PostmanPut.sendError(sendCode);
		// TODO: Observe module icon
		expectedInstrumentState = "STATE_ERROR";
		actualInstrumentState = PostmanGet.getInstrumentState();
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		// TODO: Test cannot start
		// TODO: Test cannot perform maintenance tasks
		localFilePath = filesPath + "ISt-4\\non-blocking\\";
		Upload.uploadFile(file, localFilePath, remoteFilePath);
		
		Main.debug.LOG("Test case ISt-4 passed!");
	}
	
	/**
	 * Runs test case ISt-5 Instrument Loss Communication
	 */
	public static void ist5Run() {
		Main.debug.LOG("Starting test case ISt-5...");
		
		String actualInstrumentState = new String();
		String expectedInstrumentState = "IDLE";
		
		actualInstrumentState = PostmanGet.getInstrumentState();
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		Putty.killProcess("GxNext");
		actualInstrumentState = PostmanGet.getInstrumentState();
		expectedInstrumentState = "COMM_LOSS";
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		// TODO: Test cannot start
		// TODO: Only disconnect omni icon is enabled
		Putty.restart();
		MobileWaits.instrumentReady();
		actualInstrumentState = PostmanGet.getInstrumentState();
		expectedInstrumentState = "IDLE";
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		Putty.killProcess("GxCellCoreGateway");
		actualInstrumentState = PostmanGet.getInstrumentState();
		expectedInstrumentState = "COMM_LOSS";
		CompareExpected.compare(actualInstrumentState, expectedInstrumentState);
		// TODO: Test cannot start
		// TODO: Only disconnect omni icon is enabled
		Putty.restart();
		
		Main.debug.LOG("Test case ISt-5 passed!");
	}
}
