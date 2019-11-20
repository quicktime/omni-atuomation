package mobile;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.TestObject;
import com.borland.silktest.jtf.Utils;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.common.types.Rect;
import com.microfocus.silktest.jtf.mobile.MobileObject;

import java.util.List;

import org.junit.Assert;
import com.microfocus.silktest.jtf.mobile.MobileTextField;

import dterm.CLoad;
import dterm.CloseDoor;
import testcontrol.Main;
import testcontrol.D27351.InstrumentErrors;
import com.microfocus.silktest.jtf.mobile.MobileDevice;

/**
 * Handles test workflows
 * @author Brendan Dolan
 * @date Created on: Feb 22, 2018
 */
public class MobileTests extends BaseState {
	
	/**
	 * String to hold the generated sampleID
	 */
	public static String sampleID = new String();
	
	/**
	 * Gets sample ID after sample ID generation
	 */
	public static void setSampleID() {
		sampleID = mobileTextField("Device.tests.sampleIdText").getText();
	}

	/**
	 * Generically starts test. Randomly generates sample ID, picks the first installed assay, description "automation"
	 */
	public static void startTest() {
		MobileWaits.instrumentReady();
		Main.desktop.logInfo("Starting test...");
		Navigation.toTests();
		mobileObject("Device.tests.startNewTestImage").click();
		if (exists("Device.global.button1")) { // if button1 appears, instrument is not ready again (sometimes happens)
			mobileButton("Device.global.button1").click();
			MobileWaits.instrumentReady();
		}
		mobileObject("Device.tests.sampleBarcodeGenerateIcon").click(); // click generate sample ID
		setSampleID(); // sets sample ID based on what was generated
		Main.desktop.logInfo(sampleID);
		mobileObject("Device.global.text1").click();
		mobileObject("Device.global.text1").click();
		mobileTextField("Device.tests.sampleTypeOtherDescription").setText("automation");
		mobileObject("Device.tests.toPatientDetailsButton").click();
		mobileObject("Device.tests.toCartridgeDetailsButton").click();
		mobileDevice().sleep();
		mobileDevice().wakeUp();
		Utils.sleep(1000); // TODO enhance sleep (waitForObject)
		mobileDevice().swipe(new Point(200, 900), new Point(700, 900));
		// in between these steps, phone scans the NFC tag automatically
		waitForObject("Device.tests.expirationDateImg", 2000000);
		mobileObject("Device.tests.toSamplePrepButton").click();
		mobileObject("Device.tests.toProcessDetailsButton").click();
		mobileObject("Device.global.RelativeLayout16").click();
		/**
		 * TODO Add cartridge insertion commands
		 * * Suppress blocking errors (specifically door test)
		 * * Java / Firmware Command
		 * * GXTerm / DTerm
		 * * * latchless, doorless, cload, doorclose
		 * * Ardunio / RPi - linear servo with 3 positions (in, home, out)
		 * * * linearServo.setPos(5); // bar gets set to home, to calibrate the bar against the door
		 * * * linearServo.setPos(0); // bar gets set to in, pushing cartridge
		 * * * linearServo.setPos(10); // bar gets set to out, letting cart out after testCompletion
		 */		
	}
	
	/**
	 * Starts test with a specified assay
	 * @param assay - assay to run test with
	 */
	public static void startTest(String assayLocator) {
		Navigation.toTests();
		mobileObject("Device.tests.startNewTestImage").click();
		if (exists("Device.global.button1")) { // if button 1 appears, instrument not ready
			mobileButton("Device.global.button1").click();
			MobileWaits.instrumentReady();
		}
		mobileObject("Device.tests.sampleBarcodeGenerateIcon").click();
		setSampleID();
		mobileObject("Device.global.text1").click();
		mobileObject(assayLocator).click();
		mobileTextField("Device.tests.sampleTypeOtherDescription").setText("automation");
		mobileObject("Device.tests.toPatientDetailsButton").click();
		mobileObject("Device.tests.toCartridgeDetailsButton").click();
		mobileDevice().sleep();
		mobileDevice().wakeUp();
		Utils.sleep(1000); // TODO enhance sleep (waitForObject)
		mobileDevice().swipe(new Point(200, 900), new Point(700, 900));
		waitForObject("Device.tests.expirationDateImg", 2000000);
		mobileObject("Device.tests.toSamplePrepButton").click();
		mobileObject("Device.tests.toProcessDetailsButton").click();
		mobileObject("Device.global.RelativeLayout16").click();
	}
	
	/**
	 * Almost starts test with a specified assay, but does not put the cartridge into the instrument
	 * @param assay - assay to run test with
	 */
	public static void almostStartTest(String assayLocator) {
		Navigation.toTests();
		mobileObject("Device.tests.startNewTestImage").click();
		if (exists("Device.global.button1")) {
			mobileButton("Device.global.button1").click();
			MobileWaits.instrumentReady();
		}
		mobileObject("Device.tests.sampleBarcodeGenerateIcon").click();
		setSampleID();
		mobileObject("Device.global.text1").click();
		mobileObject(assayLocator).click();
		mobileTextField("Device.tests.sampleTypeOtherDescription").setText("automation");
		mobileObject("Device.tests.toPatientDetailsButton").click();
		mobileObject("Device.tests.toCartridgeDetailsButton").click();
		mobileDevice().sleep();
		mobileDevice().wakeUp();
		Utils.sleep(1000); // TODO enhance sleep (waitForObject)
		mobileDevice().swipe(new Point(200, 900), new Point(700, 900));
		waitForObject("Device.tests.expirationDateImg", 2000000);
		mobileObject("Device.tests.toSamplePrepButton").click();
		mobileObject("Device.tests.toProcessDetailsButton").click();
	}
	
	/**
	 * Starts a test with filled out information
	 */
	public static void startTestWithInfo(String assayLocator) {
		Navigation.toTests();
		mobileObject("Device.tests.startNewTestImage").click();
		if (exists("Device.global.button1")) {
			mobileButton("Device.global.button1").click();
			MobileWaits.instrumentReady();
		}
		mobileObject("Device.tests.sampleBarcodeGenerateIcon").click();
		setSampleID();
		mobileObject("Device.global.text1").click();
		mobileObject(assayLocator).click();
		mobileObject("Device.tests.specimen").click();
		mobileObject("Device.tests.specimenList.negativeControl1").click();
		mobileTextField("Device.tests.sampleTypeOtherDescription").setText("automation");
		mobileTextField("Device.tests.collectionDateEditText").click();
		mobileButton("Device.global.button1").click();
		mobileTextField("Device.tests.notesEditText").setText("automation notes");
		mobileObject("Device.tests.toPatientDetailsButton").click();
		// patient info
		mobileTextField("Device.tests.patient.patientIdEditText").setText("" + "" + Math.round(Math.random() * 1000000000));
		mobileTextField("Device.tests.patient.familyNameEditText").setText("Smith");
		mobileTextField("Device.tests.patient.givenNameEditText").setText("John");
		mobileTextField("Device.tests.patient.addressEditText").setText(" 904 Caribbean Drive");
		mobileObject("Device.global.text1").click();
		mobileObject("Device.global.text1").click();
		// nfc tag scan
		mobileObject("Device.tests.toCartridgeDetailsButton").click();
		mobileDevice().sleep();
		mobileDevice().wakeUp();
		Utils.sleep(1000); // TODO enhance sleep (waitForObject)
		mobileDevice().swipe(new Point(200, 900), new Point(700, 900));
		waitForObject("Device.tests.expirationDateImg", 2000000);
		mobileObject("Device.tests.toSamplePrepButton").click();
		mobileObject("Device.tests.toProcessDetailsButton").click();
	}
	
	/**
	 * Selects the omni to run the test
	 */
	public static void selectOmniToRunTest() {
		mobileObject("Device.global.RelativeLayout16").click();
	}
	
	/**
	 * Gets the current test status
	 * @return test status as string
	 */
	public static String testStatus() {
		return mobileObject("Device.viewResults.result.resultsText").getText();
	}
	
	/**
	 * Checks if assay is already installed by going through the start test sequence up to assay selection
	 * @param locator - assay to check if its installed
	 * @return assay is installed or not
	 */
	public static boolean assayIsInstalled(String assayLocator) {
		Navigation.toTests();
		mobileObject("Device.tests.startNewTestImage").click();
		// check if instrument is ready
		if (exists("Device.global.button1")) { // if dialog appears, instrument is not ready
			mobileButton("Device.global.button1").click();
			MobileWaits.instrumentReady();
		}
		mobileObject("Device.tests.sampleBarcodeGenerateIcon").click(); // click on generate sample barcode button
		mobileObject("Device.global.text1").click(); // click on assay dropdown
		if (exists(assayLocator)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Stops the currently running tests
	 */
	public static void stopTest() {
		mobileObject("Device.global.cancelButton").click();
		mobileButton("Device.global.button1").click();
	}
	
	/**
	 * Counts the number of active tests under the Home button
	 * @return number of active tests
	 */
	public static int activeTestCount() {
		String text = mobileObject("Device.tests.activeTestList").textCapture();
		List<TestObject> activeTestList = mobileObject("Device.tests.activeTestList").getChildren();
		int activeTestCount = activeTestList.size() / 3;
		return activeTestCount;
	}
	
	/**
	 * Clicks on the previously run test in the Active Tests list
	 */
	public static void clickActiveTest() {
		Navigation.toTests();
		mobileObject("Device.tests.sampleIdColumnValue").click();
	}

	/**
	 * Inserts cartridge into the omni instrument
	 */
	public static void insertCart() {
		// TODO Auto-generated method stub
	}

	/**
	 * Determines if there are no active tests
	 */
	public static void noActiveTests() {
		Main.debug.LOG("Checking if there are no active tests...");
		if (activeTestCount() == 0) {
			Main.debug.LOG("There are no active tests!");
		} else {
			Main.debug.LOGError("There are active tests!");
		}
		
	}
}
