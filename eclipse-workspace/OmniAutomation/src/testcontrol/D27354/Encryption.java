package testcontrol.D27354;

import helper.Assays;
import mobile.MobileTests;
import testcontrol.Main;
import winscp.Download;
import winscp.Upload;

/**
 * Handles all test cases for the Encryption Tab
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class Encryption {

	/**
	 * Runs test case ENC-1
	 */
	public static void enc1Run() {
		Main.debug.LOG("Starting test case ENC-1...");
		
		String assay[] = Assays.ENC1;
		String directory = Main.path + "files\\RT-5\\";
		String localDirectory = "Z:\\Omni_Automation_Master\\files\\D27354 Results Generation\\Encryption\\ENC-1\\";
		String omniEpsilonLog = directory + "omni-epsilon.log";
		String Epsilon_Java_jar = "/sandbox/epsilon-java/Epsilon_Java_jar-1.0/";
		String gxxDirectory					= "/sandbox/omni-data/tests/";
		String nohup = "nohup-run-epsilon-java sh";
		
		String assayName = assay[0];
		String nfcTag = assay[1];
		String assayLocator = assay[2];
		
		// setup
		Main.debug.LOG("Setup");
		Upload.uploadFile(nohup, localDirectory, Epsilon_Java_jar);
		MobileTests.startTestWithInfo(assayLocator);
		
		// step 1
		Main.debug.LOG("Step 1");
		Download.downloadFile("gxx", localDirectory, gxxDirectory);
		
		// steps 2 - 10
		Main.debug.LOG("Steps 2-10");
		Main.debug.LOGError("Take the gxx file from " + localDirectory + " and retrieve in GX Dx 4.7c. Continue from step 1.");
	}
	
}