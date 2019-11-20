package testcontrol.D27351;

import java.io.IOException;

import dterm.CloseDoor;
import dterm.DTermNFC;
import helper.Assays;
import helper.CompareErrors;
import mobile.MobileTestErrors;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.OmniErrors;
import nfc.CartridgeNFCTagGenerator;
import postman.PostmanGet;
import postman.PostmanPut;
import putty.OmniReset;
import putty.Putty;
import sheet.LineCount;
import sheet.SheetTestErrors;
import testcontrol.Main;
import winscp.Upload;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Handles all test cases for the Initialization Errors tab
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class InitializationErrors {
	
	/**
	 * Base path for all Initialization Errors sheets
	 */
	public final static String path = "\\\\SWATTESTEXCC\\Omni_Validation_Scripts\\Omni_Automation_Master\\sheets\\D27351 Instrument Errors\\initialization errors\\inite";
	
	/**
	 * Runs test case inite1 - REMOVED FROM PROTOCOL
	 */

	/**
	 * Runs test cases inite2 through inite6
	 */
	public static void inite2_6Run() {
		// ErrorCode, ErrorMessage, ErrorDetails
		String sheet = path + "2_6.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case inite" + (1 + line) + "...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID				= null;
			String mobileErrorMessage	= null;
			String mobileErrors 		= null;
			String mobileErrorCode		= null;
			String omniErrorMessage		= null;
			String omniErrors			= null;
			String omniErrorCode		= null;
			String postmanErrorCode		= null;
			String assayName 			= assay[0];
			String nfcTag				= assay[1];
			String assayLocator			= assay[2];
			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			
			Putty.initErrors(sendCode);
			Putty.restart();
			omniErrors = OmniErrors.getErrors(1);
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			OmniReset.reset();
			
			Main.debug.LOG("Test case inite" + (1 + line) + " passed!");
		}
	}
	
	/**
	 * Runs test case inite7
	 */
	public static void inite7Run() {
		String sheet = path + "7.csv";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case inite7...");
			
			// literals
			String omniErrors			= null;
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			
			// commands
			DTermNFC.setCartNFC("SR&ASE&RASEMGMG!!@");
			MobileTests.startTest();
			MobileWaits.omniErrorAppears();
			omniErrors = OmniErrors.getErrors(1);
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			OmniReset.reset();
			
			Main.debug.LOG("Test case inite7 passed!");
		}
	}
	
	/**
	 * Runs test case inite8
	 */
	public static void inite8Run() {
		String sheet = path + "8.csv";
		String filePath = "\\\\SWATTESTEXCC\\Omni_Validation_Scripts\\Omni_Automation_Master\\files\\large.file";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case inite8...");
			
			// literals
			String omniErrors			= null;
			String sheetErrors			= SheetTestErrors.getErrors(sheet, 0);
			
			// commands
			Upload.uploadLargeFile(filePath);
			Putty.restart();
			MobileWaits.omniErrorAppears();
			omniErrors = OmniErrors.getErrors(1);
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			OmniReset.reset();
			
			Main.debug.LOG("Test case inite8 passed!");
		}
	}
	
	/**
	 * Runs test case inite9
	 */
	public static void inite9Run() {
		String sheet = path + "9.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		String productCode = "465";
		String BCC = "BA";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case inite9...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID				= null;
			String mobileErrorMessage	= null;
			String mobileErrors 		= null;
			String mobileErrorCode		= null;
			String omniErrorMessage		= null;
			String omniErrors			= null;
			String omniErrorCode		= null;
			String postmanErrorCode		= null;
			String assayName 			= assay[0];
			String nfcTag				= assay[1];
			String assayLocator			= assay[2];
			String sendCode 			= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, line);
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, line);
			
			// commands
			MobileTests.almostStartTest(assayLocator);
			CartridgeNFCTagGenerator.changeProductCode(productCode, BCC);
			CloseDoor.closeDoor(); // TODO: Close the door
			mobileErrors = MobileTestErrors.getErrors(1);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			
			Main.debug.LOG("Test case inite9 passed!");
		}
	}
	
	/**
	 * Runs test cases inite10 through inite33
	 */
	public static void inite10_33Run() {
		String sheet = path + "10_33.csv";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case inite" + (10 + line) + "...");
			
			// literals
			boolean errorsExist			= false;
			String omniErrors			= null;
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String sendCode				= SheetTestErrors.getErrorSendCode(sheet, line);
			
			// commands
			Putty.initErrors(sendCode);
			Putty.restart();
			omniErrors = OmniErrors.getErrors(1);
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			OmniReset.reset();
			
			Main.debug.LOG("Test case inite" + (10 + line) + " passed!");
		}
	}
	
	/**
	 * Runs test cases inite34 through inite36
	 */
	public static void inite34_36Run() {
		String sheet = path + "34_36.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case inite" + (34 + line) + "...");
			
			// literals
			boolean errorsExist			= false;
			String sampleID				= null;
			String mobileErrorMessage	= null;
			String mobileErrors 		= null;
			String mobileErrorCode		= null;
			String omniErrorMessage		= null;
			String omniErrors			= null;
			String omniErrorCode		= null;
			String postmanErrorCode		= null;
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
			sampleID = MobileTests.sampleID; // test started, so sampleID has been generated
			MobileWaits.testErrorAppears();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1); // error appears, so gather error message
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			MobileWaits.testCompletion();
			mobileErrors = MobileTestErrors.getErrors(1); // test ended, so gather errors from mobile
			mobileErrorCode = MobileTestErrors.getErrorCode(1); // test ended, so gather error code from mobile
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID); // test ended, so gather error code from postman
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareMobilePostman(mobileErrorCode, postmanErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case inite" + (34 + line) + " passed!");
		}
	}
	
	/**
	 * Runs test case inite37 - NOT TESTED
	 * No power supply during FW upgrade
	 */
}
