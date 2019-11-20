package mobile;

import static org.junit.Assert.*;

import helper.StringManipulation;
import testcontrol.Main;

/**
 * Handles Omni Admin Actions -> Instrument Screen, and instrument error details
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class OmniErrors extends BaseState {
	
	/**
	 * Gets error message from instrument error
	 * @param whichError the error number to get
	 * @return the error message
	 */
	public static String getErrorMessage(int whichError) {
		omniErrorsExist(whichError);
		mobileObject("Device.viewResults.result.errors.titleText" + whichError).click();
		String errorMessage = mobileObject("Device.viewResults.result.errors.titleText" + whichError).getText();
		mobileObject("Device.global.backIcon").click();
		return errorMessage;
	}
	
	/**
	 * Gets solution text from instrument error
	 * @param whichError the error number to get
	 * @return the solution text
	 */
	public static String getSolutionText(int whichError) {
		omniErrorsExist(whichError);
		mobileObject("Device.viewResults.result.errors.titleText" + whichError).click();
		String solutionText = mobileObject("Device.viewResults.result.errors.solutionText").getText();
		mobileObject("Device.global.backIcon").click();
		return solutionText;
	}
	
	/**
	 * Gets omni text from instrument error
	 * @param whichError the error number to get
	 * @return the omni text
	 */
	public static String getOmniText(int whichError) {
		omniErrorsExist(whichError);
		mobileObject("Device.viewResults.result.errors.titleText" + whichError).click();
		String omniText = mobileObject("Device.viewResults.result.errors.omniText").getText();
		mobileObject("Device.global.backIcon").click();
		return omniText;
	}
	
	/**
	 * Gets error code text from instrument error
	 * @param whichError the error number to get
	 * @return the error code text
	 */
	public static String getErrorCodeText(int whichError) {
		omniErrorsExist(whichError);
		mobileObject("Device.viewResults.result.errors.titleText" + whichError).click();
		String codeText = mobileObject("Device.viewResults.result.errors.errorNumberText").getText();
		mobileObject("Device.global.backIcon").click();
		return codeText;
	}
	
	/**
	 * Gets error code from instrument error
	 * @param whichError the error number to get
	 * @return the error code
	 */
	public static String getErrorCode(int whichError) {
		omniErrorsExist(whichError);
		String errorNumberText = getErrorCodeText(whichError);
		return StringManipulation.errorCode(errorNumberText);
	}
	
	/**
	 * Gets formatted full errors from instrument error
	 * @param whichError the error number to get
	 * @return the formatted full errors line
	 */
	public static String getErrors(int whichError) {
		omniErrorsExist(whichError);
		return StringManipulation.omniErrors(whichError);
	}
	
	/**
	 * Gets whether or not instrument error(s) exist
	 * @param whichError the error number to get
	 */
	public static void omniErrorsExist(int whichError) {
		boolean omniErrorsExist = false;
		int timer = 0;
		
		Navigation.toProcessor();
		while (!exists("Device.viewResults.result.errors.titleText" + whichError) ) {
			timer++;
			Navigation.dragUp();
			if (timer > 10) {
				break;
			}
		}
		if (exists("Device.viewResults.result.errors.titleText" + whichError)) {
			omniErrorsExist = true;
		}
		try { // not good practice to catch asserts
			assertFalse(omniErrorsExist);
		} catch (AssertionError e) {
			Main.debug.LOGError("Error exists!");
			e.printStackTrace();
		}
		Main.debug.LOG("Error does not exist");
	}
}
