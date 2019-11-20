package mobile;

import com.borland.silktest.jtf.Utils;

/**
 * Handles all actions under the Processor screen
 * @author Brendan Dolan
 * @date Created on: Mar 12, 2018
 */
public class Processor extends BaseState {
	
	/**
	 * Performs the Staus LED functions
	 */
	public static void statusLED() {
		Navigation.toProcessor();
		Navigation.dragToolsRight();
		mobileObject("Device.tools.instruments.identifyProcessorIcon").click();
		mobileButton("Device.global.button1").click();
	}
	
	/**
	 * Performs a self-test
	 */
	public static void selfTest() {
		Navigation.toProcessor();
		Navigation.dragToolsRight();
		mobileObject("Device.tools.instruments.processorSelfTestIcon").click();
		mobileObject("Device.tools.instruments.selfTest.startSelfTestIcon").click();
		mobileObject("Device.tools.instruments.entryBackButton").click();
	}
	
	/**
	 * Performs weekly maintenance functions
	 */
	public static void weeklyMaintenance() {
		Navigation.toProcessor();
		Navigation.dragToolsRight();
		mobileObject("Device.tools.instruments.processorMaintenanceIcon").click();
		mobileObject("Device.tools.instruments.weeklyMaintenanceIcon").click();
		mobileObject("Device.tools.instruments.completeIcon").click();
		mobileObject("Device.tools.instruments.taskBackButton").click();
		mobileObject("Device.tools.instruments.entryBackButton").click();
	}
	
	/**
	 * Performs monthly maintenance functions
	 */
	public static void monthlyMaintenance() {
		Navigation.toProcessor();
		Navigation.dragToolsRight();
		mobileObject("Device.tools.instruments.processorMaintenanceIcon").click();
		mobileObject("Device.tools.instruments.monthlyMaintenanceIcon").click();
		mobileObject("Device.tools.instruments.completeIcon").click();
		mobileObject("Device.tools.instruments.taskBackButton").click();
		mobileObject("Device.tools.instruments.entryBackButton").click();
	}
	
	/**
	 * Performs actions to restart instrument
	 */
	public static void restartInstrument() {
		Navigation.toProcessor();
		Navigation.dragToolsLeft();
		mobileObject("Device.tools.instruments.processorRestartSwitchIcon").click();
		mobileButton("Device.global.button1").click();
	}
	
	/**
	 * Performs actions to shutdown instrument
	 */
	public static void shutdownInstrument() {
		Navigation.toProcessor();
		Navigation.dragToolsLeft();
		mobileObject("Device.tools.instruments.processorOffSwitchIcon").click();
		mobileButton("Device.global.button1").click();
	}
	
	/**
	 * Performs actions to remove instrument
	 */
	public static void removeInstrument() {
		Navigation.toProcessor();
		Navigation.dragToolsLeft();
		mobileObject("Device.tools.instruments.disconnectProcessorIcon").click();
		mobileButton("Device.global.button1").click();
	}

	/**
	 * Gets status of the omni instrument
	 * @return status of the omni instrument as string
	 */
	public static String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets java version of the omni instrument
	 * @return java version of the instrument as string
	 */
	public static String getJavaVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
