package mobile;

import com.borland.silktest.jtf.Utils;
import com.microfocus.silktest.jtf.mobile.MobileButton;
import com.microfocus.silktest.jtf.mobile.MobileObject;
import com.microfocus.silktest.jtf.mobile.MobileTextField;

import testcontrol.Main;
import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.mobile.MobileDevice;
import com.borland.silktest.jtf.common.types.Point;

/**
 * Handles all Omni Mobile provisioning steps
 * @author Brendan Dolan
 * @date Created on: Feb 23, 2018
 */
public class Provisioning extends BaseState {

	/**
	 * Performs site provisioning
	 * @param SITE	Unique string from QR Code, identifying specific site
	 * @param link	url for c360
	 */
	public static void siteProvisioning(String SITE, String link) {
		if (exists("Device.siteProvisioning.proceedInfoArrowIcon")) { // if mobile device needs to perform site provisioning
			mobileObject("Device.siteProvisioning.proceedInfoArrowIcon").click();
			mobileTextField("Device.siteProvisioning.secretEditText").setText(SITE); // enter site instead of scanning
			mobileTextField("Device.siteProvisioning.serverEditText").setText(link); // enter c360 link
			mobileObject("Device.siteProvisioning.verifyOneTimePasswordText").click();
			Utils.sleep(1000); // wait for OTC to pass/fail
			// TODO failure handling
			mobileObject("Device.siteProvisioning.completeProvisioningForwardIcon").click();
			mobileButton("Device.global.button1").click(); // yes button
			waitForObject("Device.omniApp", 60000); // wait 60 seconds for site provisioning to complete and app to close
			mobileObject("Device.omniApp").click(); // click omni app icon
			waitForObject("Device.userProvisioning.beginSetupIcon", 60000); // wait until userProvisioning icon appears to confirm success
			Main.debug.LOG("Test completed siteProvisioning.");
		} else { // mobile device does not need to perform site provisioning
			Main.debug.LOG("Test did not need to siteProvision.");
		}
	}
	
	/**
	 * Performs user provisioning
	 * @param user		username {@link Main#user}
	 * @param password	password of user {@link Main#password}
	 * @param OTC		one time code {@link Main#OTC}
	 */
	public static void userProvisioning(String user, String password, String OTC) {
		if (exists("Device.userProvisioning.beginSetupIcon")) { // if mobile device is on beginSetup screen
			mobileObject("Device.userProvisioning.beginSetupIcon").click();
		}
		if (exists("Device.userProvisioning.requestOneTimeCodeIcon")) { // if mobile device is on OTC request page
			mobileTextField("Device.userProvisioning.userNameEntry").setText(user); // enter user from Main
			mobileObject("Device.userProvisioning.requestOneTimeCodeIcon").click();
			mobileTextField("Device.userProvisioning.passwordEntry").setText(password); // enter password from Main
			mobileTextField("Device.userProvisioning.secondFactorEntry").setText(OTC); // enter OTC from Main
			mobileObject("Device.userProvisioning.secondFactorProceed").click();
			mobileObject("Device.global.text1").click();
			mobileObject("Device.userProvisioning.Brendan-EMG").click(); // click lab
			mobileObject("Device.userProvisioning.toSyncSettingsButton").click();
			waitForObject("Device.userProvisioning.goToHomeIcon", 1000000); // wait for userProv to complete
			mobileObject("Device.userProvisioning.goToHomeIcon").click();
			Main.debug.LOG("Test completed userProvisioning.");
		} else { // mobile device does not need to user provision
			Main.debug.LOG("Test did not need to userProvision.");
		}
	}
	
	/**
	 * Performs network setup after provisioning
	 * @param ssid			network ssid {@link Main#ssid}
	 * @param passphrase	network password {@link Main#passphrase}
	 */
	public static void networkProvisioning(String ssid, String passphrase) {
		Main.debug.LOG("Performing network setup");
		HandHeld.updateNetwork(ssid, passphrase);
		Main.debug.LOG("Waiting 1 minute for instruments to connect");
		Utils.sleep(60000);
	}
	
	/**
	 * Handles logins.
	 * @param user		username {@link Main#user}
	 * @param password	password of user {@link Main#password}
	 */
	public static void login(String user, String password) {
		if (exists("Device.userProvisioning.userNameEntry")) { // if mobile device is on login page
			mobileTextField("Device.userProvisioning.userNameEntry").setText(user); // enter use from Main
			mobileTextField("Device.userProvisioning.passwordEntry").setText(password); // enter password from Main
			mobileObject("Device.userProvisioning.passwordProceed").click();
			Main.debug.LOG("Test logged in.\n");
		} else { // mobile device does not need to log in
			Main.debug.LOG("Test did not need to login.\n");
		}
	}
}
