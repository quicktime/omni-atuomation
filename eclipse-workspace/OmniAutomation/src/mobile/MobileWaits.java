package mobile;

import com.microfocus.silktest.jtf.mobile.MobileObject;

import putty.Putty;
import testcontrol.Main;

import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.mobile.MobileButton;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.common.ObjectNotFoundException;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.Utils;

import java.util.List;

import org.junit.Assert;

/**
 * Responsible for all sleeps and waits on the Mobile Device
 * @author Brendan Dolan
 * @date Created on: Feb 22, 2018
 */
public class MobileWaits extends BaseState {
	
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
		MobileWaits.timer = timer;
	}
	
	/**
	 * Waits up to 6 minutes from call for test to complete
	 */
	public static void testCompletion() {
		Main.debug.LOG("Waiting for test to complete...");
		waitForObject("Device.tests.endTimeImg", 360000); 
		Main.debug.LOG("Test completed.");
	}
	
	/**
	 * Waits up to 3 minutes from call for test to start
	 */
	public static void testStarts() {
		Main.debug.LOG("Waiting for test to start...");
		waitForObject("Device.tests.progressLevel", 180000); 
		Main.debug.LOG("Test started.");
	}
	
	/**
	 * Waits up to 6 minutes from call for error to appear
	 */
	public static void testErrorAppears() {
		Main.debug.LOG("Waiting for test error to appear...");
		waitForObject("Device.viewResults.result.errorIcon", 360000); 
		Main.debug.LOG("Test error appeared.");
	}
	
	/**
	 * Waits up to 3 minutes from call for omni error to appear
	 */
	public static void omniErrorAppears() {
		Main.debug.LOG("Waiting for omni error to appear...");
		waitForObject("Device.global.button1", 180000);
		mobileObject("Device.global.button1").click();
		mobileObject("Device.gloval.cancelButton").click();
		Main.debug.LOG("Omni error appeared.");
	}
	
	/**
	 * Waits until instrument is ready
	 */
	public static void instrumentReady() {
		Main.debug.LOG("Checking if instrument is ready to run tests...");
		Navigation.toTests();
		mobileObject("Device.tests.startNewTestImage").click();
		if (!exists("Device.global.cancelButton")) { // if cancel button does not appear, instrument is not ready
			while (!exists("Device.global.cancelButton")) { // while cancel button isn't present
				mobileButton("Device.global.button1").click(); // button1 is present if cancel button isn't, click to dismiss
				if (getTimer() == 20) { // instrument has not been ready for 5 minutes
					Main.debug.LOG("Instrument has not been ready for 5minutes+");
					Putty.restart(); // restart the omni
					communicationFail(); // wait for communication failed message to appear, and handle it
					Navigation.toTests();
				} if (getTimer() > 40) { // instrument has not been ready for 10 minutes
					Main.debug.LOGError("Instrument could not ready"); // log error
					return; // return, failing the test case
				}
				Main.debug.LOG("Instrument not yet ready, waiting 15 seconds to try again...");
				setTimer(getTimer() + 1); // increase timer += 1
				Utils.sleep(15000);
				mobileObject("Device.tests.startNewTestImage").click(); // retry
			}
		} // if it gets to here, cancel button has appeared 
		mobileObject("Device.global.cancelButton").click(); // click the cancel button, to reset
		mobileButton("Device.global.button1").click(); // click ok (button1)
		setTimer(0); // reset timer for the next time
		Main.debug.LOG("Instrument is ready to run tests.");
	}
	
	/**
	 * Waits until Omni communication failed message appears.
	 * Mainly used after {@link putty.Putty#restart()} command
	 */
	public static void communicationFail() {
		Main.debug.LOG("Waiting for Communication Failed popup...");
		try { // surround with try because ~sometimes~ comm fail popup does not popup, but test should not fail
			waitForObject("Device.global.dialogs.alertTitle", 120000); // wait 2 minutes for comm failed popup
			mobileButton("Device.global.button1").click(); // click ok on comm failed popup
			Utils.sleep(10000); // wait 10 seconds TODO: better wait
			Main.debug.LOG("Communication failed popup appeared, and dismissed.");
		} catch (ObjectNotFoundException e) { // popup doesn't appear
			Main.debug.LOGWarning(e.toString());
			Main.debug.LOGWarning("Communication failed popup never appeared.");
		}
	}
}
