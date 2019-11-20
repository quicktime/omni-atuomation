package mobile;

import com.borland.silktest.jtf.Utils;

import testcontrol.Main;

/**
 * Performs all actions under the Pending Commands screen
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class PendingCommands extends BaseState {
	
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
		PendingCommands.timer = timer;
	}

	/**
	 * Performs actions to install passed-in assay
	 * @param assayName - assay name to install
	 * @param assayLocator - locator to check if installed
	 */
	public static void installAssay(String assayName, String assayLocator) {
		boolean isAssayInstalled = false;
		
		Main.debug.LOG("Checking if assay is installed...");
		MobileWaits.instrumentReady(); // makes sure instrument is ready
		isAssayInstalled = MobileTests.assayIsInstalled(assayLocator); // check if assay is installed
		if (isAssayInstalled) { // assay is already installed
			Main.debug.LOG("Assay already installed");
			return; // return
		} // if it gets to here, assay is not installed
		Main.debug.LOG("Installing assay : " + assayName + "...");
		HandHeld.sync(); // sync with c360
		Navigation.toPendingCommands(); // goto pending commands
		if (!exists("Device.tools.pendingCommands.Install " + assayName)) { // assay is not downloaded yet
			while (!exists("Device.tools.pendingCommands.Install " + assayName)) { // while assay is not yet downloaded
				Main.debug.LOG("Assay : " + assayName + " is not downloaded yet, trying again...");
				if (getTimer() == 20) { // assay has not been ready for over 5 minutes 
					HandHeld.sync();
					Navigation.toPendingCommands();
				} if (getTimer() > 40) { // assay has not been ready for over 10 minutes
					Main.debug.LOGError("Assay : " + assayName + " could not be downloaded");
					return; // something is wrong, return, failing the test case
				}
				Utils.sleep(15000); // wait 15 seconds
				setTimer(getTimer() + 1); // timer++
			}
		} // if it gets to here, assay is downloaded and instrument is ready
		mobileObject("Device.tools.pendingCommands.Install " + assayName).click(); // attempt to install assay
		mobileButton("Device.global.button1").click(); // yes button appears, click it
		Utils.sleep(120000); // wait 2 minutes for assay to install
		setTimer(0); // reset timer
		Main.debug.LOG("Assay : " + assayName + " installed!");
	}
}
