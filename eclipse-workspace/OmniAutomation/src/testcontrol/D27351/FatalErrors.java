package testcontrol.D27351;

import java.io.IOException;

import c360.c360Errors;
import dterm.DTermNFC;
import helper.Assays;
import helper.CompareErrors;
import helper.StringManipulation;
import mobile.MobileTestErrors;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.OmniErrors;
import mobile.PendingCommands;
import nfc.NFCTools;
import postman.PostmanGet;
import postman.PostmanPut;
import putty.OmniReset;
import putty.Putty;
import sheet.LineCount;
import sheet.SheetTestErrors;
import testcontrol.Main;

import static org.junit.Assert.*;

/**
 * Handles all test cases for the Fatal Errors Tab
 * @author Brendan Dolan
 * @date Created on: Feb 22, 2018
 */
public class FatalErrors {
	
	/**
	 * Base path for all Fatal Errors sheets
	 */
	public final static String path = "\\\\SWATTESTEXCC\\Omni_Validation_Scripts\\Omni_Automation_Master\\sheets\\D27351 Instrument Errors\\fatal errors\\fe";
	
	/**
	 * Runs test case fe1
	 */
	public static void fe1Run() {
		String sheet = path + "1.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case fe1...");
			
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
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID;
			MobileWaits.testStarts();
			PostmanPut.sendError(sendCode);
			MobileWaits.testCompletion();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrors = MobileTestErrors.getErrors(1);
			mobileErrorCode = MobileTestErrors.getErrorCode(1);
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID);
			c360Errors.errorsExist();
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			CompareErrors.compareMobilePostman(mobileErrorCode, postmanErrorCode);
			OmniReset.reset();			
			
			Main.debug.LOG("Test case fe1 passed!");
		}
	}
	/**
	 * Runs test cases fe2 through fe18
	 */
	public static void fe2_18Run() {
		String sheet = path + "2_18.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case fe" + (2 + line) + "...");
			
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
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID;
			MobileWaits.testStarts();
			PostmanPut.sendError(sendCode);
			MobileWaits.testCompletion();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrors = MobileTestErrors.getErrors(1);
			mobileErrorCode = MobileTestErrors.getErrorCode(1);
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID);
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			CompareErrors.compareMobilePostman(mobileErrorCode, postmanErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case fe" + (2 + line) + " passed!");
		}
	}
	
	/**
	 * Runs test case fe19 - NOT TESTED
	 */
	
	/**
	 * Runs test cases fe20 through fe24
	 */
	public static void fe20_24Run() {
		String sheet = path + "20_24.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case fe" + (20 + line) + "...");

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
			MobileTests.startTest(assayLocator);
			MobileWaits.testCompletion();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrors = MobileTestErrors.getErrors(1); // test ended, so gather errors from mobile
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);

			Main.debug.LOG("Test case fe" + (20 + line) + " passed.\n");
		}
	}
	
	/**
	 * Runs test cases fe25 through fe26
	 */
	public static void fe25_26Run() {
		String sheet = path + "25_26.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case fe" + (25 + line) + "...");
			
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
			MobileTests.startTest(assayLocator);
			Putty.killProcess("GxCellCoregateway");
			omniErrorMessage = OmniErrors.getErrorMessage(1);
			omniErrors = OmniErrors.getErrors(1);
			CompareErrors.compareErrorMessages(omniErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			Putty.restart();
			OmniErrors.omniErrorsExist(1);
							
			Main.debug.LOG("Test case fe" + (25 + line) + " passed!");
		}
	}
	
	/**
	 * Runs test case fe27
	 */
	public static void fe27Run() {
		String sheet = path + "27.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < 1; line++) { // only want it to loop once even though there are 2 lines
			Main.debug.LOG("Starting test case fe27...");
			
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
			MobileTests.startTest(assayLocator);
			Putty.killProcess("GxCellCoregateway");
			omniErrorMessage = OmniErrors.getErrorMessage(1);
			omniErrors = OmniErrors.getErrors(1);
			CompareErrors.compareErrorMessages(omniErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			Putty.start();
			omniErrorMessage = OmniErrors.getErrorMessage(1);
			// strange begin
			String omniText = OmniErrors.getOmniText(1);
			String omniErrorCodeText = OmniErrors.getErrorCodeText(1);
			omniErrors = omniErrorMessage.concat(omniText.concat(omniErrorCodeText));
			// strange end
			CompareErrors.compareErrorMessages(omniErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(omniErrors, sheetErrors);
			OmniReset.reset();
			
			Main.debug.LOG("Test case fe27 passed!");
		}
	}
	
	/**
	 * Runs test case fe28 -- REMOVED FROM PROTOCOL
	 */
	
	/**
	 * Runs test case fe29 -- REMOVED FROM PROTOCOL
	 */
	
	/**
	 * Runs test case fe30
	 */
	public static void fe30Run() {
		String sheet = path + "30.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case fe30");
			
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
			MobileTests.startTest(assayLocator);
			MobileWaits.testStarts();
			PostmanPut.sendError(sendCode);
			MobileWaits.testErrorAppears();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrors = MobileTestErrors.getErrors(1);
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			MobileTests.startTest(assayLocator);
			// TODO Error popup dialog should appear; handle it
			OmniReset.reset();
			
			Main.debug.LOG("Test case fe30 passed!");
		}
	}
	
	/**
	 * Runs test case fe31 -- REMOVED FROM PROTOCOL
	 */
	
	/**
	 * Runs test cases fe32 through fe38
	 */
	public static void fe32_38Run() {
		String sheet = path + "32_38.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case fe" + (32 + line) + "...");
			
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
			sampleID = MobileTests.sampleID;
			MobileWaits.testCompletion();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrors = MobileTestErrors.getErrors(1);
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID);
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case fe" + (32 + line) + " passed!");
		}
	}
	
	/**
	 * Runs test case fe39 - NOT TESTED
	 * Corrupt DB
	 */
	
	/**
	 * Runs test case fe40 - NOT TESTED
	 * GX Term
	 */
	
	/**
	 * Runs test case fe41 - NOT TESTED
	 * Information to be provided by Ming
	 */
	
	/**
	 * Runs test cases fe42 through fe51
	 */
	public static void fe42_51Run() {
		String sheet = path + "42_51.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case fe" + (42 + line) + "...");
			
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
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID;
			MobileWaits.testStarts();
			PostmanPut.sendError(sendCode);
			MobileWaits.testCompletion();
			mobileErrorMessage = MobileTestErrors.getErrorMessage(1);
			mobileErrors = MobileTestErrors.getErrors(1);
			postmanErrorCode = PostmanGet.getTestErrorCode(sampleID);
			CompareErrors.compareErrorMessages(mobileErrorMessage, sheetErrorMessage);
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			CompareErrors.compareErrorCode(postmanErrorCode, sheetErrorCode);
			OmniReset.reset();
			
			Main.debug.LOG("Test case fe" + (42 + line) + " passed!");
		}
	}
	
	/**
	 * Runs test case fe52 - NOT TESTED
	 * SMS connection
	 * access mobile db
	 * change access key
	 * start test
	 * send sms
	 * get error
	 * change access key back
	 */
}
