package testcontrol.D27354;

import java.util.ArrayList;

import com.borland.silktest.jtf.Utils;

import db_browser.DBBrowser;
import dterm.CloseDoor;
import helper.Assays;
import helper.CompareExpected;
import helper.StringManipulation;
import mobile.MobileTests;
import mobile.MobileWaits;
import putty.Putty;
import sheet.Export;
import sheet.OmniEpsilonLog;
import sheet.PuttyLog;
import testcontrol.Main;
import winscp.Download;
import winscp.Upload;
import wordpad.WordPad;

/**
 * Handles all test cases for the Running Tests on Omni Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class RunningTestsOnOmni {

	/**
	 * Runs test case RT-1
	 */
	public static void rt1Run() {
		Main.debug.LOG("Starting test case RT-1");
		
		// step 1
		Putty.psEfGrep("Java");
		PuttyLog.logContains("Java", 0);
		
		// step 2
		Putty.psEfGrep("GxNext");
		PuttyLog.logContains("GxNext", 0);
		
		// step 0
		Putty.psEfGrep("GxCellCoreGateway");
		PuttyLog.logContains("GxCellCoreGateway", 0);
		
		// step 3
		Putty.configuration();
		Putty.goToLogs();
		
		// step 4
		Putty.lsL();
		
		// step 5
		Putty.typeKeys("grep 127.0.0.1 omni-epsilon.log");
		Putty.close();
		PuttyLog.logContains("127.0.0.1", 3);
		
		// step 6
		Putty.configuration();
		Putty.goToLogs();
		Putty.typeKeys("grep 'Remote Port Base:' omni-epsilon.log");
		Putty.close();
		PuttyLog.logContains("Remote Port Base:", 3);
		
		Main.debug.LOG("Test case RT-1 passed!");
	}
	
	/**
	 * Runs test case RT-2
	 */
	public static void rt2Run() {
		Main.debug.LOG("Starting test case RT-2...");
		String assay[] = Assays.HIV_1_Qual_v2;
		
		// literals
		String actualTestStatus				= new String();
		String assayName 					= assay[0];
		String nfcTag						= assay[1];
		String assayLocator					= assay[2];
		String expectedTestStatus			= "Starting";
		
		// steps 1 - 19
		Main.debug.LOG("Steps 1-19");
		MobileTests.startTestWithInfo(assayLocator);
		Putty.configuration();
		Putty.goToLogs();
		Putty.typeKeys("tail omni-epsilon.log | more");
		
		// step 20
		Main.debug.LOG("Step ");
		MobileTests.selectOmniToRunTest();
		
		// step 21
		Main.debug.LOG("Step 21");
		Putty.typeKeys("<Enter>");
		
		// step 22
		Main.debug.LOG("Step 22");
		// TODO Door close command
		
		// step 23
		Main.debug.LOG("Step 23");
		Putty.typeKeys("<Enter>");
		
		// step 24 & 25
		Main.debug.LOG("Steps 24-25 ");
		// TODO: Putty output
		
		// step 26
		Main.debug.LOG("Step 26");
		actualTestStatus = MobileTests.testStatus();
		CompareExpected.compare(actualTestStatus, expectedTestStatus);
		
		// step 27
		Main.debug.LOG("Step 27");
		MobileWaits.testStarts();
		
		// step 28
		Main.debug.LOG("Step 28");
		// TODO: Putty output
		
		Main.debug.LOG("Test case RT-2 passed!");
	}
	
	/**
	 * Runs test case RT-3
	 */
	public static void rt3Run() {
		Main.debug.LOG("Starting test case RT-3...");
		String filepath = Main.path + "files\\databases\\RT-3\\";
		
		ArrayList<String> testTimes = new ArrayList<String>();

		// setup
		Main.debug.LOG("Setup");
		Download.downloadDB(filepath);
		
		// step 1
		Main.debug.LOG("Step 1");
		DBBrowser.open();
		
		// step 2
		Main.debug.LOG("Step 2");
		DBBrowser.testTable();
		
		// step 3
		Main.debug.LOG("Step 3");
		DBBrowser.testTimes(Main.path + "files\\");
		DBBrowser.close();
		testTimes = Export.testTimes(Main.path + "files\\testTimes.csv");
		
		// step 4-6
		Main.debug.LOG("Steps 4-6");
		Export.test_timeIsNull(testTimes);
		
		Main.debug.LOG("Test case RT-3 passed!");
	}
	
	/**
	 * Runs test case RT-4 - NOT TESTED
	 * Manual cartridge tag errors
	 */
	
	/**
	 * Runs test case RT-5
	 */
	public static void rt5Run() {
		Main.debug.LOG("Starting test case RT-5...");
		
		String assay[] = Assays.HIV_1_Qual_v2;
		String directory = Main.path + "files\\RT-5\\";
		String localDirectory = "Z:\\Omni_Automation_Master\\files\\RT-5\\";
		String omniEpsilonLog = directory + "omni-epsilon.log";
		String Epsilon_Java_jar = "/sandbox/epsilon-java/Epsilon_Java_jar-1.0/";
		String nohup = "nohup-run-epsilon-java sh";
		
		// literals
		ArrayList<String> ls				= new ArrayList<String>();
		ArrayList<String> logTimeStamps		= new ArrayList<String>();
		String sampleID						= new String();
		String sampleID2					= new String();
		String assayName 					= assay[0];
		String nfcTag						= assay[1];
		String assayLocator					= assay[2];
		
		MobileTests.startTest(assayLocator);
		sampleID = MobileTests.sampleID;
		MobileWaits.testCompletion();
		MobileTests.startTest(assayLocator);
		sampleID2 = MobileTests.sampleID;
		MobileWaits.testCompletion();
		
		// step 1
		Main.debug.LOG("Step 1");
		Putty.configuration();
		Putty.goToLogs();
		
		// step 2
		Main.debug.LOG("Step 2");
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains("omni-epsilon.log", 2);
		
		// step 3
		Main.debug.LOG("Step 3");
		ls = PuttyLog.log(2); // ArrayList of ls output
		logTimeStamps = StringManipulation.puttyGetFileTimeStamps(ls); // ArrayList of log time stamps
		PuttyLog.moreThan4HrsApart(logTimeStamps); // are the log time stamps at least 4 hrs apart?
		
		// step 4
		Main.debug.LOG("Step 4");
		Download.downloadLog(directory);
		WordPad.OpenFile(omniEpsilonLog);
		
		// step 5-6
		Main.debug.LOG("Steps 5-6");
		OmniEpsilonLog.hasTimeStamp(omniEpsilonLog);
		OmniEpsilonLog.hasMemUsage(omniEpsilonLog);
		OmniEpsilonLog.hasMemAllocation(omniEpsilonLog);
		
		// step 7
		Main.debug.LOG("Step 7");
		Putty.configuration();
		Putty.typeKeys("cd " + Epsilon_Java_jar);
		Upload.uploadFile(nohup, localDirectory + "Step-7", Epsilon_Java_jar);
		Putty.goToLogs();
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains(".log", 3);
		
		// step 8
		Main.debug.LOG("Step 8");
		Putty.configuration();
		Putty.goToLogs();
		Putty.typeKeys("rm *");
		
		// step 9
		Main.debug.LOG("Step 9");
		Putty.restart();
		Utils.sleep(100000);
		Putty.configuration();
		
		// step 10
		Main.debug.LOG("Step 10");
		Putty.goToLogs();
		Putty.lsLTr();
		Putty.close();
		PuttyLog.logContains(".log", 2);
		
		// step 11
		Main.debug.LOG("Step 11");
		Download.downloadLog(directory);
		OmniEpsilonLog.hasGxMini(omniEpsilonLog);
		Putty.configuration();
		Putty.typeKeys("cd " + Epsilon_Java_jar);
		Putty.close();
		Upload.uploadFile(nohup, localDirectory + "Step-11", Epsilon_Java_jar);
		Main.debug.LOG("Test case RT-5 passed!");
	}
}
