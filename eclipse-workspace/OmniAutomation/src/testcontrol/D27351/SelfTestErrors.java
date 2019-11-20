package testcontrol.D27351;

import java.io.IOException;

import helper.CompareErrors;
import mobile.Processor;
import mobile.OmniErrors;
import postman.PostmanPut;
import putty.OmniReset;
import sheet.LineCount;
import sheet.SheetTestErrors;
import testcontrol.Main;

/**
 * Handles all test cases for the Self-Test Errors Tab
 * @author Brendan Dolan
 * @date Created on: Feb 22, 2018
 */
public class SelfTestErrors {
	
	public final static String path = "\\\\SWATTESTEXCC\\Omni_Validation_Scripts\\Omni_Automation_Master\\sheets\\D27351 Instrument Errors\\self test errors\\st";

	/**
	 * Runs test cases st1 through st21
	 */
	public static void st1_21Run() {
		String sheet = path + "1_21.csv";
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case st" + (1 + line) + "...");
			
			// literals
			String omniErrors			= null;
			String sendCode				= SheetTestErrors.getErrorSendCode(sheet, line);
			String sheetTestErrors		= SheetTestErrors.getErrors(sheet, line);
			
			// commands
			PostmanPut.sendError(sendCode);
			Processor.selfTest();
			omniErrors = OmniErrors.getErrors(1);
			CompareErrors.compareErrors(omniErrors, sheetTestErrors);
			OmniReset.reset();
			
			Main.debug.LOG("Test case st" + (1 + line) + " passed!");
		}
	}
}
