package testcontrol.D27351;

import helper.Assays;
import helper.CompareErrors;
import helper.StringManipulation;
import mobile.MobileTestErrors;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.OmniErrors;
import mobile.PendingCommands;
import nfc.NFCTools;
import mobile.BaseState;
import mobile.HandHeld;
import postman.PostmanGet;
import postman.PostmanPut;
import putty.OmniReset;
import putty.Putty;
import sheet.LineCount;
import sheet.SheetTestErrors;
import testcontrol.Main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.borland.silktest.jtf.Utils;
import com.borland.silktest.jtf.internal.Agent;

import c360.c360Errors;
import dterm.DTermNFC;

import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.mobile.MobileObject;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.PushToolItem;
import com.borland.silktest.jtf.Tree;
import com.borland.silktest.jtf.VerticalScrollBar;
import com.borland.silktest.jtf.win32.AccessibleControl;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.PushButton;

/**
 * Handles all test cases for the Instrument Errors tab
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class InstrumentErrors {
	
	/**
	 * Path to the Instrument Errors sheets
	 */
	public final static String path = Main.sheetsPath + "D27351 Instrument Errors\\instrument errors\\ie";

	/**
	 * Runs test cases ie1 through ie7. Assay with 2 temp cycles.
	 */
	public static void ie1_7Run() {
		String sheet = path + "1_7.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case ie" + (1 + line) + "...");

			// literals
			boolean errorsExist 		= false;
			String sampleID 			= new String();
			String mobileErrorMessage 	= new String();
			String mobileErrors 		= new String();
			String mobileErrorCode 		= new String();
			String omniErrorMessage 	= new String();
			String omniErrors 			= new String();
			String omniErrorCode 		= new String();
			String postmanErrorCode 	= new String();
			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage 	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors 			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode 		= SheetTestErrors.getErrorCode(sheet, line);

			// commands
			MobileTests.startTest(assayLocator); // start a test with the specified assay
			sampleID = MobileTests.sampleID; // test started, so sampleID has been generated
			MobileWaits.testStarts(); // wait until test starts
			PostmanPut.sendError(sendCode); // send the error code from sheets to the Omni instrument
			MobileWaits.testErrorAppears(); // wait until test error appears
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1); // error message appears, so gather error message
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage); // compare expected error message with actual error message
			MobileWaits.testCompletion(); // wait until test completes
			mobileErrors = MobileTestErrors.getErrors(1); // test ended, so gather errors from mobile
			CompareErrors.compareErrors(mobileErrors, sheetErrors); // compare actual errors with expected errors
			mobileErrorCode = MobileTestErrors.getErrorCode(1); // test ended, so gather error code from mobile
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID); // test ended, so gather error code from postman
			CompareErrors.compareMobilePostman(mobileErrorCode, postmanErrorCode); // compare mobile error code with postman error code
			OmniReset.reset(); // reset the omni instrument

			Main.debug.LOG("Test case ie" + (1 + line) + " passed!\n");
		}
	}

	/**
	 * Runs test cases ie8 through ie12. Non_Termination_Error assays
	 */
	public static void ie8_12Run() {
		String sheet = path + "8_12.csv";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie" + (8 + line) + "...");
			
			// literals
			String assayName 			= Assays.NON_TERMINATION_ERROR[line * 3 + 0]; // gets the right array index
			String nfcTag				= Assays.NON_TERMINATION_ERROR[line * 3 + 1]; // gets the right array index
			String assayLocator			= Assays.NON_TERMINATION_ERROR[line * 3 + 2]; // gets the right array index
			String sampleID 			= new String();
			String mobileErrorMessage	= new String();
			String mobileErrors 		= new String();
			String mobileErrorCode 		= new String();
			String postmanErrorCode 	= new String();
			String sendCode			 	= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors 			= SheetTestErrors.getErrors(sheet, line);
			
			// commands
			Assays.assaySetup(assayName, nfcTag, assayLocator);
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID; // test started, so sampleID has been generated
			MobileWaits.testErrorAppears();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1); // error appeared, so get error message
			MobileWaits.testCompletion();
			mobileErrors = MobileTestErrors.getErrors(1); // test completed, so get errors
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			mobileErrorCode = MobileTestErrors.getErrorCode(1); // test ended, so gather error code from mobile
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID); // test ended, so gather error code from postman
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareMobilePostman(mobileErrorCode, postmanErrorCode);
			
			Main.debug.LOG("Test case ie" + (8 + line) + " passed!\n");
		}
	}
	
	/**
	 * Test case ie13 - REMOVED FROM PROTOCOL
	 */
	
	/**
	 * Runs test case ie14 // TODO See if setting a blank nfc tag work or not
	 */
	public static void ie14Run() {
		String sheet = path + "14.csv";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie14...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID 			= new String();
			String mobileErrors			= new String();
			String sheetErrors			= SheetTestErrors.getErrors(sheet, 0);
			
			// commands
			NFCTools.writeTag(" ");
			MobileTests.startTest();
			sampleID = MobileTests.sampleID;
			MobileWaits.testErrorAppears();
			mobileErrors = MobileTestErrors.getErrors(1);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			OmniReset.reset();
			
			Main.debug.LOG("Test case ie14 passed!\n");
		}
	}
	
	/**
	 * Test case ie15 - REMOVED FROM PROTOCOL
	 */

	/**
	 * Runs test case ie16
	 */
	public static void ie16Run() {
		String sheet = path + "16.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0];
		String nfcTag					= assay[1];
		String assayLocator				= assay[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator);
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie16...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID				= new String();
			String mobileErrorMessage	= new String();
			String mobileErrors 		= new String();
			String mobileErrorCode		= new String();
			String omniErrorMessage		= new String();
			String omniErrors			= new String();
			String omniErrorCode		= new String();
			String postmanErrorCode		= new String();

			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			
			String mobileErrorMessage2	= new String();
			String mobileErrors2		= new String();
			String sheetErrorMessage2 	= SheetTestErrors.getErrorMessage(sheet, line + 1);
			String sheetErrors2			= SheetTestErrors.getErrors(sheet, line + 1);
			
			// commands
			// Mobile Device connected to c360
			PostmanPut.sendError(sendCode);
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID; // test started, so sampleID has been generated
			MobileWaits.testCompletion();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrorMessage2 = MobileTestErrors.getErrorMessage(2);
			mobileErrors = MobileTestErrors.getErrors(1);
			mobileErrors2 = MobileTestErrors.getErrors(2);
			postmanErrorCode = PostmanGet.getModuleErrorCode();
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrorMessages(mobileErrorMessage2, sheetErrorMessage2);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareErrors(mobileErrors2, sheetErrors2);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			c360Errors.errorsExist(); // returns true if errors exist in c360, breaks if they do not TODO make c360errors
			OmniReset.reset();
			
			Main.debug.LOG("Test case ie16 passed!\n");
		}
	}
	
	/**
	 * Runs test case ie17
	 */
	public static void ie17Run() {
		String sheet = path + "17.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie17...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID				= new String();
			String mobileErrorMessage	= new String();
			String mobileErrors 		= new String();
			String mobileErrorCode		= new String();
			String omniErrorMessage		= new String();
			String omniErrors			= new String();
			String omniErrorCode		= new String();
			String postmanErrorCode		= new String();
			String assayName 			= assay[0];
			String nfcTag				= assay[1];
			String assayLocator			= assay[2];
			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			
			String mobileErrorMessage2	= new String();
			String mobileErrors2		= new String();
			String sheetErrorMessage2 	= SheetTestErrors.getErrorMessage(sheet, line + 1);
			String sheetErrors2			= SheetTestErrors.getErrors(sheet, line + 1);
			
			// commands
			// Mobile Device connected to c360
			Assays.assaySetup(assayName, nfcTag, assayLocator);
			PostmanPut.sendError(sendCode);
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID; // test started, so sampleID has been generated
			MobileWaits.testCompletion();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrorMessage2 = MobileTestErrors.getErrorMessage(2);
			mobileErrors = MobileTestErrors.getErrors(1);
			mobileErrors2 = MobileTestErrors.getErrors(2);
			postmanErrorCode = PostmanGet.getModuleErrorCode();
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareErrors(mobileErrors2, sheetErrors2);
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrorMessages(mobileErrorMessage2, sheetErrorMessage2);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case ie17 passed!\n");
		}
	}

	/**
	 * Runs test cases ie18 through 35
	 */
	public static void ie18_35Run() {
		String sheet = path + "18_35.csv";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie" + (18 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			String omniErrors		 	= new String();
			String postmanErrorCode 	= new String();
			String sendCode			 	= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			String sheetErrors 			= SheetTestErrors.getErrors(sheet, line);
			
			// commands
			PostmanPut.sendError(sendCode);
			MobileWaits.omniErrorAppears();
			omniErrors = OmniErrors.getErrors(1);
			postmanErrorCode = PostmanGet.getModuleErrorCode();
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case ie" + (18 + line) + " passed!\n");
		}
	}
	
	/**
	 * Runs test case 36 - NOT TESTED
	 */
	public static void ie36Run() {
		// sendCode
		// run test to completion
		// verify errors
		// postman compare code
		// reset
		// start test without errors
		// connected instrument, unplugged
		// postman GET module/
		// view powerStatus and batteryStatus
		// view battery symbol on APP
		// when battery <= 50%, plug in
		// postman GET module/
		// view battery symbol
	}
	
	/**
	 * Runs test cases 37 through 44
	 */
	public static void ie37_44Run() {
		String sheet = path + "37_44.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie" + (37 + line) + "...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID				= new String();
			String mobileErrorMessage	= new String();
			String mobileErrors 		= new String();
			String mobileErrorCode		= new String();
			String omniErrorMessage		= new String();
			String omniErrors			= new String();
			String omniErrorCode		= new String();
			String postmanErrorCode		= new String();
			String assayName 			= assay[0];
			String nfcTag				= assay[1];
			String assayLocator			= assay[2];
			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			
			// commands
			PostmanPut.sendError(sendCode);
			MobileWaits.omniErrorAppears();
			omniErrors = OmniErrors.getErrors(1);
			postmanErrorCode = PostmanGet.getModuleErrorCode();
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case ie" + (37 + line) + " passed!\n");
		}
	}
	
	/**
	 * Runs test case 45
	 */
	public static void ie45Run() {
		String sheet = path + "45.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie45...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID				= new String();
			String mobileErrorMessage	= new String();
			String mobileErrors 		= new String();
			String mobileErrorCode		= new String();
			String omniErrorMessage		= new String();
			String omniErrors			= new String();
			String omniErrorCode		= new String();
			String postmanErrorCode		= new String();
			String assayName 			= assay[0];
			String nfcTag				= assay[1];
			String assayLocator			= assay[2];
			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			
			// commands
			Assays.assaySetup(assayName, nfcTag, assayLocator);
			PostmanPut.sendError(sendCode);
			MobileWaits.omniErrorAppears();
			// TODO Verify instrument is not in blocking state
			omniErrors = OmniErrors.getErrors(1);
			postmanErrorCode = PostmanGet.getModuleErrorCode();
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			OmniReset.reset();
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID;
			MobileWaits.testStarts();
			PostmanPut.sendError(sendCode);
			MobileWaits.testErrorAppears();
			// TODO Verify instrument is not in blocking state
			mobileErrors = MobileTestErrors.getErrors(1);
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case ie45 passed!\n");
		}
	}
	
	/**
	 * Runs test case 46
	 */
	public static void ie46Run() {
		String sheet = path + "46.csv";
		String assay[] = Assays.SONICATION;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case ie46...");
				
			// literals
			boolean errorsExist			= false;
			String sampleID				= new String();
			String mobileErrorMessage	= new String();
			String mobileErrors 		= new String();
			String mobileErrorCode		= new String();
			String omniErrorMessage		= new String();
			String omniErrors			= new String();
			String omniErrorCode		= new String();
			String postmanErrorCode		= new String();
			String assayName 			= assay[0];
			String nfcTag				= assay[1];
			String assayLocator			= assay[2];
			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			
			// commands
			Assays.assaySetup(assayName, nfcTag, assayLocator);
			PostmanPut.sendError(sendCode);
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID;
			MobileWaits.testErrorAppears();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrors = MobileTestErrors.getErrors(2);
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			OmniReset.reset();
			
			Main.debug.LOG("Test case ie46 passed!\n");
		}
	}
}