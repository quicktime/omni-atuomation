package mobile;

import com.borland.silktest.jtf.Utils;

/**
 * Handles all actions under the Hand Held screen
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class HandHeld extends BaseState {
	
	/**
	 * Gets software ID
	 * @return software ID
	 */
	public static String getSoftwareID() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.androidIdText")) {
			Navigation.dragUp(); // drag up until androidIdText is in screen
		}
		return mobileObject("Device.tools.handHeld.androidIdText").getText();
	}
	
	/**
	 * Gets hardware ID
	 * @return hardware ID
	 */
	public static String getHardwareID() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.imeiText")) {
			Navigation.dragUp(); // drag up until imeiText is in screen
		}
		return mobileObject("Device.tools.handHeld.imeiText").getText();
	}
	
	/**
	 * Gets app version
	 * @return app version
	 */
	public static String getVersion() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.versionText")) {
			Navigation.dragUp(); // drag up until versionText is in screen
		}
		return mobileObject("Device.tools.handHeld.versionText").getText();
	}
	
	/**
	 * Gets release date
	 * @return release date
	 */
	public static String getReleaseDate() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.releaseDateText")) {
			Navigation.dragUp(); // dragup until releaseDateText is in screen
		}
		return mobileObject("Device.tools.handHeld.releaseDateText").getText();
	}
	
	/**
	 * Perfoms actions to reset the site
	 */
	public static void resetSite() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.unlockSiteImg")) {
			Navigation.dragUp(); // drag up until unlockSiteImg is in screen
		}
		mobileObject("Device.tools.handHeld.unlockSiteImg").click();
		mobileButton("Device.global.button1").click();
	}
	
	/**
	 * Performs actions to update network settings
	 */
	public static void updateNetwork(String ssid, String passphrase) {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.ssidEditText")) { // while ssidEditText isn't on the screen
			Navigation.dragUp(); // drag up until ssidEditText is
		}
		mobileTextField("Device.tools.handHeld.ssidEditText").click();
		mobileTextField("Device.tools.handHeld.ssidEditText").setText(ssid); // set ssid from Main
		mobileTextField("Device.tools.handHeld.ssidPassphraseEditText").setText(passphrase); // set passphrase from Main
		mobileButton("Device.global.button1").click();
	}
	
	/**
	 * Performs actions to turn on mobile hotspot
	 */
	public static void mobileHotspot(String ssid, String passphrase) {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.enableHotspotButton")) {
			Navigation.dragUp(); // drag up until enableHotspotButton is in screen
		}
		mobileObject("Device.tools.handHeld.enableHotspotButton").click();
		mobileButton("Device.global.button1").click();
		Utils.sleep(5000); // waits 5 seconds until hotspot is ready
		mobileObject("Device.tools.handHeld.ssidEditText").click();
		mobileTextField("Device.tools.handHeld.ssidEditText").setText(ssid);
		mobileTextField("Device.tools.handHeld.ssidPassphraseEditText").setText(passphrase);
		mobileButton("Device.global.button1").click();
	}
	
	/**
	 * Performs actions to sync with c360
	 */
	public static void sync() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.syncNowImg")) { // while sync button isn't on screen
			Navigation.dragUp(); // drag up until syncNowImg is in screen
		}
		mobileObject("Device.tools.handHeld.syncNowImg").click(); // click it
	}
	
	/**
	 * Performs actions to change lockout time
	 * @param time - time to set lockout
	 */
	public static void idleTimeOut(String time) {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.idleTimeOutValue")) {
			Navigation.dragUp(); // drag up until idleTimeOutValue is in screen
		}
		mobileTextField("Device.tools.handHeld.idleTimeOutValue").setText(time);
	}
	
	/**
	 * Performs actions to download test results from instrument
	 */
	public static void downloadResults() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.downloadGxmRightImg")) {
			Navigation.dragUp(); // drag up until downloadGxmRightImg is in screen
		}
		mobileObject("Device.tools.handHeld.downloadGxmRightImg").click();
	}
	
	/**
	 * Performs actions to upoad test results to c360
	 */
	public static void uploadResults() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.uploadGxmRightImg")) {
			Navigation.dragUp(); // drag up until uploadGxmRightImg is in screen
		}
		mobileObject("Device.tools.handHeld.uploadGxmRightImg").click();
	}
	
	/**
	 * Performs actions to restart Omni app
	 */
	public static void restartApp() {
		Navigation.toHandHeld();
		while (!exists("Device.tools.handHeld.restartRightAppImg")) {
			Navigation.dragUp(); // drag up until restartRightAppImg is in screen
		}
		mobileObject("Device.tools.handHeld.restartRightAppImg").click();
	}
}
