package mobile;

import com.borland.silktest.jtf.common.types.Point;

/**
 * Handles all navigation actions on the mobile app
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class Navigation extends BaseState {
	
	/**
	 * Navigates to tests screen
	 */
	public static void toTests() {
		waitForObject("Device.tests.testsButton");
		mobileObject("Device.tests.testsButton").click();
	}
	
	/**
	 * Navigates to view results screen
	 */
	public static void toViewResults() {
		toTests();
		waitForObject("Device.viewResults.viewResultsText");
		mobileObject("Device.viewResults.viewResultsText").click();
	}
	
	/**
	 * Nagivates to current user screen
	 */
	public static void toCurrentUser() {
		waitForObject("Device.currentUser.currentUserButton");
		mobileObject("Device.currentUser.currentUserButton").click();
	}
	
	/**
	 * Navigates to tools screen
	 */
	public static void toTools() {
		waitForObject("Device.tools.toolsButton");
		mobileObject("Device.tools.toolsButton").click();
	}
	
	/**
	 * Navigates to info screen
	 */
	public static void toInfo() {
		waitForObject("Device.info.infoButton");
		mobileObject("Device.info.infoButton").click();
	}
	
	/**
	 * Navigates to pending commands screen
	 */
	public static void toPendingCommands() {
		toTools();
		mobileObject("Device.tools.handHeldIcon").click(); // always click off first
		mobileObject("Device.tools.pendingCommandsIcon").click(); // click on
	}
	
	/**
	 * Navigates to processor screen
	 */
	public static void toProcessor() {
		toTools();
		mobileObject("Device.tools.handHeldIcon").click();
		mobileObject("Deviec.tools.processorIcon").click();
	}
	
	/**
	 * Nagivates to users screen
	 */
	public static void toUsers() {
		toTools();
		mobileObject("Device.tools.handHeldIcon").click();
		mobileObject("Device.tools.usersIcon").click();
	}
	
	/**
	 * Navigates to printer screen
	 */
	public static void toPrinter() {
		toTools();
		mobileObject("Device.tools.handHeldIcon").click();
		mobileObject("Device.tools.printerIcon").click();
	}
	
	/**
	 * Navigates to handheld screen
	 */
	public static void toHandHeld() {
		toTools();
		mobileObject("Device.tools.pendingCommandsIcon").click(); // always click off first
		mobileObject("Device.tools.handHeldIcon").click(); // click on
		while (!exists("Device.tools.handHeldIcon.androidText")) { // while screen is not at the top of the scrollingLayout
			dragDown(); // drag 100 pixels down, until it is
		}
	}
	
	/**
	 * Drags screen up (think scroll down) 100 pixels
	 */
	public static void dragUp() {
		mobileDevice().drag(new Point(360, 460), new Point(360, 360)); // drag 100 pixels (roughly one layout) up
	}
	
	/**
	 * Drags screen down (think scroll up) 100 piexels
	 */
	public static void dragDown() {
		mobileDevice().drag(new Point(360, 360), new Point(360, 460)); // drag 100 pixels (roughly one layout) down
	}

	/**
	 * Drags tools bar left 500 pixels
	 */
	public static void dragToolsLeft() {
		mobileDevice().drag(new Point(600, 1000), new Point(100, 1000)); // drag 500 pixels on the tools bar left
	}
	
	/**
	 * Drags tools bar right 500 pixels
	 */
	public static void dragToolsRight() {
		mobileDevice().drag(new Point(100, 1000), new Point(600, 1000)); // drag 500 pixels on the tools bar right
	}
}
