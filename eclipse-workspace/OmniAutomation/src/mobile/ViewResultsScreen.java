package mobile;

import com.borland.silktest.jtf.common.types.Point;

import testcontrol.Main;

/**
 * Handles all actions in the View Results screen
 * @author Brendan Dolan
 * @date Created on: Mar 28, 2018
 */
public class ViewResultsScreen extends BaseState {
	
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
		ViewResultsScreen.timer = timer;
	}
	
	
	/**
	 * Determines if the specified test exists in the View Results space or not
	 * @param sampleID - specified test
	 * @return whether the test exists or not
	 */
	public static boolean testExists(String sampleID) {
		boolean exists = false;
		String txtSampleID = "Device.viewResults.txtSampleId.txtSampleId";
		String atTop = null;
		String atTop2 = null;
		
		Navigation.toViewResults();
		
		// Sets atTop to the top row
		if (exists(txtSampleID + "1")) {
			atTop = mobileObject(txtSampleID + "1").getText();
		}
		
		// checks if passed-in sampleID is in any row
		for (int i = 1; i < 9; i++) {
			String vrSampleID = mobileObject(txtSampleID + i).getText();
			if (sampleID.equals(vrSampleID)) {
				exists = true;
			}
		}
		
		mobileDevice().drag(new Point(360, 1200), new Point(360, 400)); // drag full list up
		
		// Sets atTop2 to the top row
		if (exists(txtSampleID + "1")) {
			atTop2 = mobileObject(txtSampleID + "1").getText();
		}
		
		if (!atTop.equals(atTop2)) { // if atTop is not atTop, there's more items in the list
			testExists(sampleID); // retry, but with the list dragged up
		}
		
		return exists;
	}
	
	/**
	 * Determines if the object exists or not
	 * @param locator - locator of the object
	 */
	public static void objectExists(String locator) {
		if (exists(locator)) {
			Main.debug.LOG("Object exists");
		} else {
			Main.debug.LOGError("Object does not exist");
		}
	}
	
	/**
	 * Determines if Results Text exists or not
	 */
	public static void resultsTextExists() {
		while (!exists("Device.viewResults.result.resultsText")) { // while Results Text is not in screen
			if (timer > 10) { // if its dragged down 10 times already, Results Text does not exist
				Main.debug.LOGError("Results Text does not exist!");
			}
			Navigation.dragDown(); // drag down
			setTimer(timer++); // timer++
		} // if it gets to here, Result Text exists
		setTimer(0);
		Main.debug.LOG("Results Text exists!");
	}
	
	/**
	 * Determines if Results Text exists or not
	 */
	public static void userNameTextExists() {
		while (!exists("Device.tools.users.userDetails.userNameTextView")) { // while User Name Text is not in screen
			if (timer > 10) { // if its dragged down 10 times already, User Name Text does not exist
				Main.debug.LOGError("User Name Text does not exist!");
			}
			Navigation.dragDown(); // drag down
			setTimer(timer++); // timer++
		} // if it gets to here, User Name Text exists
		setTimer(0);
		Main.debug.LOG("User Name Text exists!");
	}
	
	/**
	 * Determines if Results Text exists or not
	 */
	public static void processorNameExists() {
		while (!exists("Device.viewResults.result.processorNameTextView")) { // while Processor Name Text is not in screen
			if (timer > 10) { // if its dragged down 10 times already, Processor Name Text does not exist
				Main.debug.LOGError("Processor Name Text does not exist!");
			}
			Navigation.dragUp(); // drag down
			setTimer(timer++); // timer++
		} // if it gets to here, Processor Text exists
		setTimer(0);
		Main.debug.LOG("Processor Name Text exists!");
	}
	
	/**
	 * Determines if Results Text exists or not
	 */
	public static void endTimeExists() {
		while (!exists("Device.viewResults.result.endTimeTextView")) { // while End Time Text is not in screen
			if (timer > 10) { // if its dragged down 10 times already, End Time Text does not exist
				Main.debug.LOGError("End Time Text does not exist!");
			}
			Navigation.dragUp(); // drag down
			setTimer(timer++); // timer++
		} // if it gets to here, End Time Text exists
		setTimer(0);
		Main.debug.LOG("End Time Text exists!");
	}
	
	/**
	 * Clicks on Today header
	 */
	public static void headerToday() {
		mobileObject("Device.viewResults.todaysTest").click();
	}
	
	/**
	 * Clicks on Yesterday header
	 */
	public static void headerYesterday() {
		mobileObject("Device.viewResults.yesterdaysTest").click();
	}
	
	/**
	 * Clicks on This Week header
	 */
	public static void headerThisWeek() {
		mobileObject("Device.viewResults.thisWeeksTest").click();
	}
	
	/**
	 * Clicks on This Month header
	 */
	public static void headerThisMonth() {
		mobileObject("Device.viewResults.thisMonthsTest").click();
	}
	
	/**
	 * Click on Search header
	 */
	public static void headerSearch() {
		mobileObject("Device.viewResults.othersTest").click();
	}
	
	public static void sampleIDSearch(String sampleID) {
		headerSearch();
		mobileTextField("Device.viewResults.sampleIdSearchText").setText(sampleID);
		mobileButton("Device.global.button1").click();
		mobileObject("Device.viewResults.txtSampleId.txtSampleId1").click();
	}

	public static void clickErrors(int whichError) {
		while (!exists("Device.viewResults.result.errors.titleText" + whichError)) {
			if (timer > 10) { // if its dragged down 10 times already, Errors do not exist
				Main.debug.LOGError("Errors do not exist!");
			}
			Navigation.dragUp();
			setTimer(timer++);
		}
		setTimer(0);
		mobileObject("Device.viewResults.result.errors.titleText1").click();
		mobileObject("Device.global.backIcon").click();
	}
	
	/**
	 * Determines whether or not the currently running test in displayed on the View Results screen
	 * @param sampleID - sampleID of the currently running test
	 */
	public static void runningTest(String sampleID) {
		boolean step3 = ViewResultsScreen.testExists(sampleID);
		if (!step3) {
			Main.debug.LOG("Currently running test is not displayed on View Results screen!");
		} else {
			Main.debug.LOGError("Currently running test is displayed on View Results screen!");
		}
	}

	/**
	 * Determines if a test exists in the View Results screen or not.
	 * Searches for the test by sampleID
	 * @param sampleID - sampleID of the test to search for
	 */
	public static void testNotExists(String sampleID) {
		Main.debug.LOG("Determining whether or not the test exists...");
		sampleIDSearch(sampleID);
		if (!exists("Device.viewResults.txtSampleId.txtSampleId")) {
			Main.debug.LOG("Test does not exist!");
		} else {
			Main.debug.LOGError("Test does exist!");
		}
		
	}
}
