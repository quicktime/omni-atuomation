package helper;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.common.TruelogScreenshotMode;

import db_browser.DBBrowser;
import dterm.CloseDoor;
import dterm.DTermNFC;
import mobile.BaseState;
import mobile.MobileTestErrors;
import mobile.MobileTests;
import mobile.MobileWaits;
import mobile.PendingCommands;
import nfc.NFCTools;
import postman.PostmanGet;
import postman.PostmanPut;
import putty.OmniReset;
import putty.Putty;
import sheet.DBBrowserExport;
import sheet.LineCount;
import sheet.OmniEpsilonLog;
import sheet.PuttyLog;
import sheet.SheetTestErrors;
import testcontrol.Main;
import testcontrol.D23308.Installation;
import testcontrol.D23308.ReInstallation;
import testcontrol.D27354.ResultsGeneration;
import testcontrol.D27354.RunningTestsOnOmni;
import winscp.Download;
import winscp.Upload;
import wordpad.WordPad;

/**
 * Helper class to debug certain pieces of code without running the whole project
 * @author Brendan Dolan
 * @date Created on: Mar 2, 2018
 */
public class QuickTests {
	
	public static void errors() throws IOException {
		String mobileErrors = MobileTestErrors.getErrors(1);
		String sheetErrors = SheetTestErrors.getErrors("./sheets/instrument error/instrument errors/ie1_ie7.csv", 0);
		
		String ignored = StringManipulation.ignore(sheetErrors);
		
		System.out.println(mobileErrors.matches(ignored));
		System.out.println(mobileErrors);
		System.out.println(sheetErrors);
		System.out.println(ignored);
		assertTrue(mobileErrors.matches(ignored));
	}
	
	public static void postman() throws IOException {
//		System.out.println(PostmanGet.getErrorCode("1519943748571"));
		PostmanPut.sendError("123");
	}
	
	public static void strings() {
		String hardcoded 	= "[-rw-r--r-- 1 root root 84880 Mar 23 15:44 Xpert HIV-1 Qual_6ce3f893-b7af-4156-b458-f63573141c16_2018.03.23_22.42.28.gxm, kjkjk]";
		String curly 		= "(.*) Xpert HIV-1 Qual_(.*)_(.*).gxm.*";
//		String ignored 		= StringManipulation.ignore(curly);
		
		Main.debug.LOG(hardcoded);
		Main.debug.LOG(curly);
		
		boolean matches = hardcoded.matches(curly);
		
		if (matches == false) {
			Main.debug.LOGError("Errors do not match!");
		} else {
			Main.debug.LOG("Errors match!");
		}
	}
	
	public static void logs() {
		Desktop desktop = BaseState.getDesktop();
		desktop.logInfo("This is a test info message");
		desktop.logWarning("This is a test warning message");
		desktop.logError("This is a test error message");
		desktop.logInfo("test", TruelogScreenshotMode.ActiveApplication);
	}
	
	public static void lines() {
		String sheet = "./sheets/instrument error/instrument errors/ie1_ie7.csv";
		int lines = LineCount.lineCount(sheet);
		System.out.println(lines);
		for (int line = 0; line < LineCount.lineCount(sheet); line++) {
			System.out.println(line);
			String sheetErrors = SheetTestErrors.getErrors(sheet, line);
			System.out.println(sheetErrors);
		}
	}
	
	public static void dterm() {
		CloseDoor.closeDoor();
	}
	
	public static void winscp() {
		String dbFilePath = Main.path + "files\\databases\\rg-1\\";
		Download.downloadDB(dbFilePath);
		Upload.uploadDB("Z:\\Omni_Automation_Master\\files\\databases\\rg-1\\");
	}
	
	public static void putty () {
		Putty.configuration();
	}
	
	public static void m46() {
		String sheet = "\\\\SWATTESTEXCC\\Omni_Validation_Scripts\\Omni_Automation_Master\\sheets\\D27351 Instrument Errors\\instrument errors\\ie46.csv";
		Main.debug.LOG("Starting test case ie46...");
			
		String sendCode			 	= SheetTestErrors.getErrorSendCode(sheet, 0);
		String sheetErrorMessage	= SheetTestErrors.getErrorMessage(sheet, 0);
		String sheetErrorCode		= SheetTestErrors.getErrorCode(sheet, 1);
		String sheetErrors 			= SheetTestErrors.getErrors(sheet, 1);
		
		Main.debug.LOG(sendCode);
		Main.debug.LOG(sheetErrorMessage);
		Main.debug.LOG(sheetErrorCode);
		Main.debug.LOG(sheetErrors);
		
		Main.debug.LOG("Test case ie46 passed!\n");
	}
	
	public static void mathRandom() {
		System.out.println("" + Math.random() * 1000);
	}
	
	public static void dbBrowser() {
		String dbFilePath = Main.path + "files\\";
		DBBrowser.open();
		DBBrowser.testGUID(dbFilePath);
		DBBrowser.testTimes(dbFilePath + "testTimes");
		String dbTestGUID = DBBrowserExport.testGUID(dbFilePath);
		Main.debug.LOG(dbTestGUID);
	}
	
	public static void omniEpsilon() {
		String log = Main.path + "files\\omni-epsilon.log";
		OmniEpsilonLog.hasTimeStamp(log);
		OmniEpsilonLog.hasMemUsage(log);
		OmniEpsilonLog.hasMemAllocation(log);
	}
	
	public static void wordPad() {
		WordPad.OpenFile(Main.path + "files\\README.txt");
	}
	
	public static void RGRun() {
		ResultsGeneration.rg1Run();
	}
	
	public static void RTRun() {
		RunningTestsOnOmni.rt1Run();
		RunningTestsOnOmni.rt3Run();
		RunningTestsOnOmni.rt5Run();
	}
	
	public static void INSTRun() {
		ReInstallation.reinst1Run();
	}
}
