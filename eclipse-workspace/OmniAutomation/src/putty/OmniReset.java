package putty;

import static org.junit.Assert.assertFalse;

import mobile.MobileTestErrors;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.Navigation;
import postman.PostmanPut;
import testcontrol.Main;

/**
 * Reset Omni instrument, runs a test to completion and verifies no errors appear
 * @author Brendan Dolan
 * @date Created on: Mar 12, 2018
 */
public class OmniReset {
	
	/**
	 * Used as a timer, in order to handle unexpected omni issues
	 * If an action takes over a certain amount of time
	 */
	private static int timer;

	/**
	 * Gets current timer
	 * @return timer
	 */
	public static int getTimer() {
		return timer;
	}

	/**
	 * Sets timer
	 * @param timer int to set timer to
	 */
	public static void setTimer(int timer) {
		OmniReset.timer = timer;
	}

	/**
	 * Sends a 0 code to clear all errors, just in case. Restarts the omni
	 * instrument, waits until the omni communication failed popup appears, and
	 * handles it, runs a test to completion, and verifies that no errors are
	 * present
	 */
	public static void reset() {
		boolean errorsExist = true; // initially errors should exist when calling this function

		while (errorsExist) {
			if (getTimer() > 4) { // if instrument has reset 5 times, fail the test case
				Main.debug.LOGError("Error can not be cleared. Ending execution!");
				assertFalse(errorsExist);
			}
			PostmanPut.sendError("0");
			Putty.restart();
			MobileWaits.communicationFail();
			MobileTests.startTest();
			MobileWaits.testCompletion();
			errorsExist = MobileTestErrors.errorsExist();
		}
		Navigation.toTests();
	}

}
