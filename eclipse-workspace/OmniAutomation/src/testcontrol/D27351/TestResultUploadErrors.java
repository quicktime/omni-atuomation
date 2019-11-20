package testcontrol.D27351;

import helper.Assays;
import helper.CompareErrors;
import helper.StringManipulation;
import mobile.MobileTestErrors;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.OmniErrors;
import mobile.PendingCommands;
import nfc.NFCTools;
import mobile.BaseState;
import mobile.HandHeld;
import postman.PostmanGet;
import postman.PostmanPut;
import putty.OmniReset;
import putty.Putty;
import sheet.LineCount;
import sheet.SheetTestErrors;
import testcontrol.Main;
import winscp.Download;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.borland.silktest.jtf.Utils;
import com.borland.silktest.jtf.internal.Agent;

import c360.c360Errors;
import dterm.DTermNFC;

import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.mobile.MobileObject;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.PushToolItem;
import com.borland.silktest.jtf.Tree;
import com.borland.silktest.jtf.VerticalScrollBar;
import com.borland.silktest.jtf.win32.AccessibleControl;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.PushButton;

/**
 * Handles all test cases for the Test Result Upload tab
 * @author Brendan Dolan
 * @date Created on: Sept 21, 2018
 */
public class TestResultUploadErrors {

	/**
	 * Path to the Instrument Errors sheets
	 */
	public final static String path = Main.sheetsPath + "D27351 Instrument Errors\\test result upload errors\\true";

	/**
	 * Runs test case TRUE-1
	 */
	public static void true1Run() {
		String sheet = path + "1.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID;
			String directory;
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// commands
			MobileTests.startTest(assayLocator);
			sampleID = MobileTests.sampleID;
			Putty.killProcess("java");
			// TODO: Save a copy of the "chunk" folder
			// TODO: Modify the "gxmsignature" of .hdr file
			Putty.restart();
			// TODO: Upload test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			// TODO: Set -Depsilon.remote_file_transfer=OFF in nohup
			// ^^^ Note: Do this by uploading a pre-built nohup file
			Putty.restart();
			// TODO: Put the fopy of the "chunk" folder from previous steps
			// TODO: Modify the "extensionFileSignature" of the .hdr file
			// TODO: Set -Depsilon.remote_file_transfer=ON
			Putty.restart();
			// TODO: Upload test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			directory = Main.filesPath + "D27351 Instrument Errors\\test result upload errors\\true\\1";
			Download.downloadLog(directory);
			// TODO: Verify errors from log file
			OmniReset.reset();
			
			Main.debug.LOG("Test case true1 passed!\n");
		}
	}
	
	/**
	 * Runs test case TRUE-2
	 */
	public static void true2Run() {
		String sheet = path + "2.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String mobileErrors			= new String();
			
			// commands
			// TODO: Turn off wifi
			MobileTests.startTest(assayLocator);
			// TODO: Navigate to Admin Actions -> Offline Mode -> Download -> Tap Download
			// TODO: In Windows explorer, navigate to "gxm_dir" and create folder "chunk"
			
			// TODO: Verify test results are in the <in> folder
			// TODO: Unzip the zip file in the <in> folder
			// TODO: Open one of the chunk files, edit the header signature, save to the <chunk> folder

			// TODO: Turn on wifi
			// TODO: Navigate to Admin Actions -> Offline Mode -> Upload -> Tap Upload
			// TODO: Verify error
			
			// TODO: Turn off wifi
			MobileTests.startTest(assayLocator);
			// TODO: Admin Actions -> Offline Mode -> Download -> Tap Download
			// TODO: Navigate to "gxm_dir" and create folder "chunk"
			// TODO: Verify test results are in the <in> folder
			// TODO: Unzip the zip file in the <in> folder
			// TODO: Open one of the chunk files, edit the header signature, save to the <chunk> folder
			
			// TODO: Decrypt the GXM files in the <no-phi> and <hh> folders
			// TODO: Edit the header signature of the two files, save and re-encrypt the files, place back into the folders
			
			// TODO: Turn on wifi
			// TODO: Admin Actions -> Offline Mode -> Upload -> Tap Upload
			// TODO: Verify error
			
			// TODO: Turn off wifi
			MobileTests.startTest(assayLocator);
			// TODO: Admin Actions -> Offline Mode -> Download -> Tap Download
			// TODO: Use WinSCP to verify test result is in the <hh> folder
			// TODO: Admin Actions -> Offline Mode -> Upload -> Tap Upload
			// TODO: Quickly kill the java
			// TODO: Check test result are in the <chunk> folder
			// TODO: Open one of the chunk files, edit the header signature, save to the <chunk> folder
	
			// TODO: Turn on wifi
			// TODO: Admin Actions -> Offline Mode -> Upload -> Tap Upload
			// TODO: Verify error
			
			// TODO: In c360 Admin as an implementation support user, go to institution, lab, mobile device, instrument, view event histroy
			// TODO: Verify errors appear in the instrument java log history
			
			OmniReset.reset();
			
			Main.debug.LOG("Test case true2 passed!\n");
		}
	}
	
	/**
	 * Runs test case TRUE-3
	 */
	public static void true3Run() {
		String sheet = path + "3.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// commands
			// TODO: Set -Depsilon.remote_file_transfer=OFF in the nohup script
			Putty.restart();
			MobileTests.startTest();
			sampleID = MobileTests.sampleID;
			// TODO: Use WinSCP to open the XML file
			// TODO: Delete the value from one of the fields. Save
			// TODO: Set -Depsilon.remote_file_transfrer=ON in the nohup script
			Putty.restart();
			// TODO: Attempt to upload the test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			// TODO: In c360 Admin as an implementation support user, go to institution, lab, mobile device, instrument, view event histroy
			// TODO: Verify MISSING_EXT_FIELD appears in the event history
		
			OmniReset.reset();
			
			Main.debug.LOG("Test case true3 passed!\n");
		}
	}
	
	/**
	 * Runs test case TRUE-4
	 */
	public static void true4Run() {
		String sheet = path + "4.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// commands
			// TODO: Set -Depsilon.remote_file_transfer=OFF in the nohup script
			Putty.restart();
			MobileTests.startTest();
			sampleID = MobileTests.sampleID;
			// TODO: Use WinSCP to open the XML file
			// TODO: Delete the value from one of the fields. Save, re-encrpyt. Place back in the appropriate folder
			// TODO: Set -Depsilon.remote_file_transfrer=ON in the nohup script
			Putty.restart();
			// TODO: Attempt to upload the test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			// TODO: In c360 Admin as an implementation support user, go to institution, lab, mobile device, instrument, view event histroy
			// TODO: Verify MISSING_GXM_FIELD appears in the event history
		
			OmniReset.reset();
			
			Main.debug.LOG("Test case true3 passed!\n");
		}
	}
	
	/**
	 * Runs test case TRUE-5
	 */
	public static void true5Run() {
		String sheet = path + "5.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// commands
			// TODO: Set -Depsilon.remote_file_transfer=OFF in the nohup script
			Putty.restart();
			MobileTests.startTest();
			sampleID = MobileTests.sampleID;
			// TODO: Use WinSCP to download the GXM file
			// TODO: Decrypt the GXM file
			// TODO: Enter "" in the start time field. Save, re-encrpyt. Place back in the appropriate folder
			// TODO: Set -Depsilon.remote_file_transfrer=ON in the nohup script
			Putty.restart();
			// TODO: Attempt to upload the test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			// TODO: In c360 Admin as an implementation support user, go to institution, lab, mobile device, instrument, view event histroy
			// TODO: Verify INVALID_START_TIME appears in the event history
		
			OmniReset.reset();
			
			Main.debug.LOG("Test case true5 passed!\n");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Runs test case TRUE-7
	 */
	public static void true7Run() {
		String sheet = path + "7.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// commands
			// TODO: Set -Depsilon.remote_file_transfer=OFF in the nohup script
			Putty.restart();
			MobileTests.startTest();
			sampleID = MobileTests.sampleID;
			// TODO: Use WinSCP to download the GXM file
			// TODO: Decrypt the GXM file
			// TODO: Enter an invalid GUID in the test section. Save, re-encrpyt. Place back in the appropriate folder
			// TODO: Set -Depsilon.remote_file_transfrer=ON in the nohup script
			Putty.restart();
			// TODO: Attempt to upload the test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			// TODO: In c360 Admin as an implementation support user, go to institution, lab, mobile device, instrument, view event histroy
			// TODO: Verify INVALID_TEST_RESULT_GUID appears in the event history
		
			OmniReset.reset();
			
			Main.debug.LOG("Test case true7 passed!\n");
		}
	}
	
	/**
	 * Runs test case TRUE-8
	 */
	public static void true8Run() {
		String sheet = path + "8.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// commands
			// TODO: Set -Depsilon.remote_file_transfer=OFF in the nohup script
			Putty.restart();
			MobileTests.startTest();
			sampleID = MobileTests.sampleID;
			// TODO: Use WinSCP to open the XML file
			// TODO: Delete the value from one of the fields. Save, re-encrpyt. Place back in the appropriate folder
			// TODO: Set -Depsilon.remote_file_transfrer=ON in the nohup script
			Putty.restart();
			// TODO: Attempt to upload the test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			// TODO: In c360 Admin as an implementation support user, go to institution, lab, mobile device, instrument, view event histroy
			// TODO: Verify MISSING_GXM_FIELD appears in the event history
		
			OmniReset.reset();
			
			Main.debug.LOG("Test case true8 passed!\n");
		}
	}
	
	/**
	 * Runs test case TRUE-9
	 */
	public static void true9Run() {
		String sheet = path + "9.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0]; // Assays.HIV_1_Qual_v2[0];
		String nfcTag					= assay[1]; // Assays.HIV_1_Qual_v2[1];
		String assayLocator				= assay[2]; // Assays.HIV_1_Qual_v2[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator); // performs assay setup steps
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) { // loops # of rows (excluding header)
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// commands
			// TODO: Set -Depsilon.remote_file_transfer=OFF in the nohup script
			Putty.restart();
			MobileTests.startTest();
			sampleID = MobileTests.sampleID;
			// TODO: Log in as a support user to remove the instrument from C360 Analytics
			// TODO: Set -Depsilon.remote_file_transfrer=ON in the nohup script
			Putty.restart();
			// TODO: Attempt to upload the test result to c360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			
			OmniReset.reset();
			
			Main.debug.LOG("Test case true9 passed!\n");
		}
	}
	
	/**
	 * Runs test case TRUE-10
	 */
	public static void true10Run() {
		String sheet = path + "10.csv";
		String assay[] = Assays.HIV_1_Qual_v2;
		
		String assayName 				= assay[0];
		String nfcTag					= assay[1];
		String assayLocator				= assay[2];
		
		Assays.assaySetup(assayName, nfcTag, assayLocator);
		
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			Main.debug.LOG("Starting test case true" + (1 + line) + "...");
			
			// literals
			String sampleID 			= new String();
			
			String sheetErrors			= SheetTestErrors.getErrors(sheet, line);
			String mobileErrors			= new String();
			
			// comamnds
			// TODO: Set -Depsilon.remote_file_transfer=OFF in the nohup scripts
			Putty.restart();
			// TODO: Edit the plaintext xml and modify the lab_id value, save the file to the <in> folder
			// TODO: Set -Depsilon.remote_file_transfer=ON in the nohup scripts
			Putty.restart();
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			// TODO: Turn off wifi
			// TODO: Use WinSCP to download the GXM file
			// TODO: Decrypt the GXM file
			// TODO: Enter a value lower than the valid format listed
			// TODO: Save and rencrypt the file. Place the gxm file in the <in> & <no-phi> folders
			// TODO: Download this test result to mobile
			// TODO: Turn wifi on
			// TODO: Sync with C360
			mobileErrors = ""; // TODO: Get error
			CompareErrors.compareErrors(mobileErrors, sheetErrors);
			
			OmniReset.reset();
			
			Main.debug.LOG("Test case true10 passed!\n");
		}
	}
}
