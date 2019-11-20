package mobile;

import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.mobile.MobileObject;

import helper.StringManipulation;
import testcontrol.Main;

import org.junit.Assert;

/**
 * Handles test errors from mobile device
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class MobileTestErrors extends BaseState {

	/**
	 * Gets error number text from Mobile Device error details
	 * @return error code text as string "Code : xxxx"
	 */
	public static String getErrorCodeText(int whichError) {
		mobileObject("Device.viewResults.result.errors.titleText" + whichError).click();
		String errorNumberText = mobileObject("Device.viewResults.result.errors.errorNumberText").getText();
		mobileObject("Device.global.global.backIcon").click();
		return errorNumberText;
	}
	
	/**
	 * Gets error code from Mobile Device
	 * @return formatted error code as string "xxxx"
	 */
	public static String getErrorCode(int whichError) {
		String errorNumberText = getErrorCodeText(whichError);
		return StringManipulation.errorCode(errorNumberText);
	}
	
	/**
	 * Gets error message from Mobile Device error details
	 * @param whichError - which # of errors to gather
	 * @return error message as string
	 */
	public static String getErrorMessage(int whichError) {
		String titleText1 = mobileObject("Device.viewResults.result.errors.titleText" + whichError).getText();
		return titleText1;
	}
	
	/**
	 * Gets solution text from Mobile Device error details
	 * @return solution text as string
	 */
	public static String getSolutionText(int whichError) {
		mobileObject("Device.viewResults.result.errors.titleText" + whichError).click();
		String solutionText = mobileObject("Device.viewResults.result.errors.solutionText").getText();
		mobileObject("Device.global.backIcon").click();
		return solutionText;
	}
	
	/**
	 * Gets omni text (nickname & serial number) from Mobile Device error details
	 * @return omni text as string
	 */
	public static String getOmniText(int whichError) {
		mobileObject("Device.viewResults.result.errors.titleText" + whichError).click();
		String omniText = mobileObject("Device.viewResults.result.errors.omniText").getText();
		mobileObject("Device.global.backIcon").click();
		return omniText;
	}
	
	/**
	 * Gets entire error string from {@link helper.StringManipulation#errors()}
	 * @return the formated error string
	 */
	public static String getErrors(int whichError) {
		return StringManipulation.mobileErrors(whichError);
	}
	
	/**
	 * Checks if errors are present
	 * @return true if errors exists, else false
	 */
	public static boolean errorsExist() {
		Main.debug.LOG("Checking if error(s) appeared...");
		if (mobileObject("Device.viewResults.result.errors.titleText1").exists()) {
			Main.debug.LOG("Error(s) appeared");
			return true;
		}
		Main.debug.LOGError("Error(s) did not appear.");
		return false;
	}
}
