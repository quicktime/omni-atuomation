package helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import testcontrol.Main;

/**
 * Handles error comparison
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class CompareErrors {
	
	/**
	 * Compares mobile device error code and expected error code
	 * @param actual - error code pulled from device
	 * @param expected - error code pulled from data sheet
	 */
	public static void compareErrorCode(String actual, String expected) {
		Main.debug.LOG(	"Comparing Error Codes: "
				+ 		"\n\t Mobile Error Code  : " + actual 
				+ 		"\n\t Postman Error Code : " + expected);
		int compared = actual.compareTo(expected); // are they the same?
		if (compared == 0) {
			Main.debug.LOG("Error Codes are the same!");
		} else {
			Main.debug.LOGError("Error Codes are not the same!");
		}
	}
	
	/**
	 * Compares mobile device error message and expected error message
	 * @param actual - error messsage pulled from mobile device
	 * @param expected - error message pulled from data sheet
	 */
	public static void compareErrorMessages(String actual, String expected) {
		Main.debug.LOG(	"Comparing Error Messages: "
				+		"\n\t Mobile Error Message   : " + actual
				+ 		"\n\t Expected Error Message : " + expected);
		String ignored = StringManipulation.ignore(expected); // formats string to ignore {} parameters from the expected error message
		int compared = actual.compareTo(ignored); // are they the same? reason for .compareTo() over .equals() is so no runtime error is thrown
		if (compared == 0) { // they are the same
			Main.debug.LOG("Error Messages are the same!");
		} else {
			Main.debug.LOGError("Error Messages are not the same!");
		}
	}
		
	/**
	 * Compares mobile device errors against expected errors
	 * @param actual - error details from mobile device
	 * @param expected - error message pulled from data sheet
	 */
	public static void compareErrors(String actual, String expected) {
		Main.debug.LOG(	"Comparing Errors: "
				+ 		"\n\t Mobile Errors   : " + actual 
				+ 		"\n\t Expected Errors : " + expected);
		String ignored = StringManipulation.ignore(expected); // formats string to ignore {} parameters from the expected error message
		int compared = actual.compareTo(ignored); // are they the same? reason for .compareTo() over .equals() is so no runtime error is thrown
		if (compared == 0) { // they are the same
			Main.debug.LOG("Errors are the same!");
		} else {
			Main.debug.LOGError("Errors are not the same!");
		}
	}
	
	/**
	 * Compares mobile device error code against postman error code
	 * @param mobile - error code pulled from mobile device
	 * @param postman - error code pulled from postman
	 */
	public static void compareMobilePostman(String mobile, String postman) {
		Main.debug.LOG(	"Comparing Mobile & Postman Errors: "
				+ 		"\n\t Mobile Errors   : " + mobile 
				+ 		"\n\t Postman Errors : " + postman);
		int compared = mobile.compareTo(postman); // are they the same? reason for .compareTo() over .equals() is so no runtime error is thrown
		if (compared == 0) { // they are the same
			Main.debug.LOG("Error Codes are the same!");
		} else {
			Main.debug.LOGError("Error Codes are not the same!");
		}
	}

}
