package testcontrol;
import java.io.IOException;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.borland.silktest.jtf.Desktop;

import helper.DEBUG;
import helper.QuickTests;
import mobile.BaseState;
import mobile.Provisioning;
import putty.Putty;
import testcontrol.D23304.ActiveResults;
import testcontrol.D23304.ViewResults;
import testcontrol.D23308.Installation;
import testcontrol.D23308.ReInstallation;
import testcontrol.D27351.FatalErrors;
import testcontrol.D27351.InitializationErrors;
import testcontrol.D27351.InstrumentErrors;
import testcontrol.D27351.SelfTestErrors;
import testcontrol.D27354.Encryption;
import testcontrol.D27354.ResultsGeneration;
import testcontrol.D27354.RunningTestsOnOmni;
import testcontrol.D27354.SelfTestGeneration;
import testcontrol.D27355.InstrumentStatus;
import testcontrol.D27355.Maintenance;

/**
 * Main class for Omni Automation. Class contains all Omni test protocols, tabs, and test cases.
 * Silk Central handles control over which tests are run. Using the methods paramater eg. D27351_InitializationErrors
 * Workflow: Main -> Test Protocol Package (D#####) -> Tab -> Test cases
 * Naming Convention: D#####_TabName() { TabName.testCaseRun(); }
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class Main {
	
	public final static DEBUG debug = new DEBUG(true);
	public final static Desktop desktop = BaseState.getDesktop();
	
	public final static String path 			= "\\\\swattestexcc\\Omni_Validation_Scripts\\Omni_Automation_Master\\";
	public final static String sheetsPath		= path + "sheets\\";
	public final static String filesPath 		= path + "files\\";
	public final static String programsPath 	= path + "programs\\";
	public final static String databasesPath	= filesPath + "databases\\";
	
	public final static String SITE 			= "LGVSM7U4YLJRBCIP";
	public final static String link				= "https://c360admin-stg12.cepheid.com:9082";
	public final static String user 			= "c360.brendan2@gmail.com";
	public final static String password 		= "Cphd123!";
	public final static String OTC 				= "003549"; // TODO get static 101010 from c360 team?
	public final static String ssid 			= "epsilon";
	public final static String passphrase		= "sn00py11";
	
	/**
	 * Performs initial app setup
	 */
//	@Before
	public void setup() {
		Provisioning.siteProvisioning(SITE, link);
		Provisioning.userProvisioning(user, password, OTC);
		Provisioning.networkProvisioning(ssid, passphrase);
		Provisioning.login(user, password);
		Putty.configuration();
	}
	
//	@Test
	public void quick() {
		QuickTests.INSTRun();
	}
	
// ======================================================================================== //
//                              D23304 Test Results                          	            //
//	Tabs: ->																				//
//			Active Results																	//
//			Manage Results																	//  
//			Reporting and Printing															//
//			View Results																	//
// ======================================================================================== //

// ================================== //
// 		Active Results 		   	      //
// ================================== //
	
	@Test
	public void om_var1() {
		ActiveResults.om_var1Run();
	}
	@Test
	public void om_var2() {
		ActiveResults.om_var2Run();
	}
	
// ================================== //
//		Manage Results 		   	      //
// ================================== //	

// ================================== //
//		Reporting and Printing 	      //
// ================================== //
	
// ================================== //
//		View Results 		   	      //
// ================================== //
	
	@Test
	public void om_vr1() {
		ViewResults.om_vr1Run();
	}
	@Test
	public void om_vr3() {
		ViewResults.om_vr3Run();
	}
	@Test
	public void om_vr4() {
		ViewResults.om_vr4Run();
	}
	
// ======================================================================================== //
//                              D23305 User Types                              				//
//	Tabs: ->																				//
//			Guest User																		//
//			User Login and Logout															//  
//			User Management																	//
//			User Type Privileges															//
// ======================================================================================== //

// ================================== //
//		Guest User	 		   	      //
// ================================== //

// ================================== //
//		User Login and Logout  	      //
// ================================== //	

// ================================== //
//		User Management		 	      //
// ================================== //

// ================================== //
//		User Type Privileges   	      //
// ================================== //
	
// ======================================================================================== //
//                              D23306 Ordering and Running Tests              				//
//	Tabs: ->																				//
//			Barcode Validation																//
//			Cancel Test																		//  
//			Edit Process																	//
//			Order Process																	//
//			Order Status																	//
// ======================================================================================== //

// ================================== //
//		Barcode Validation	   	      //
// ================================== //

// ================================== //
//		Cancel Test			  	      //
// ================================== //	

// ================================== //
//		Edit Process		 	      //
// ================================== //

// ================================== //
//		Order Process		   	      //
// ================================== //
	
// ================================== //
//		Order Status		   	      //
// ================================== //
	
// ======================================================================================== //
//                              D23307 Admin Actions			              				//
//	Tabs: ->																				//
//			Mobile Device Actions															//
//			Omni Instrument																	//  
//			Printer																			//
// ======================================================================================== //

// ================================== //
//		Mobile Device Actions  	      //
// ================================== //

// ================================== //
//		Omni Instrument		  	      //
// ================================== //	

// ================================== //
//		Printer				 	      //
// ================================== //
	
// ======================================================================================== //
//  							D23308 Master Media				              				//
//	Tabs: ->																				//
//			Installation																	//
//			Re-Installation																	//  
// ======================================================================================== //

// ================================== //
//		Installation		  	      //
// ================================== //
	
	@Test
	public void inst1() {
		Installation.inst1Run();
	}

// ================================== //
//		Re-Installation		  	      //
// ================================== //
	
	@Test
	public void reinst1() {
		ReInstallation.reinst1Run();
	}
	
// ======================================================================================== //
//                              D24415 Site Provisioning			              			//
//	Tabs: ->																				//
//			Documentation Tab																//
//			GX Omni Instrument																//  
//			Omni Mobile Device Registration													//
//			Site Config Sync																//
//			Site Provisioning																//
// ======================================================================================== //

// ================================== //
//		Documentation Tab	  	      //
// ================================== //

// ================================== //
//		GX Omni Instrument	  	      //
// ================================== //	

// ================================== //
//	Omni Mobile Device Registration	  //
// ================================== //
	
// ================================== //
//		Site Config Sync	  	      //
// ================================== //

// ================================== //
//		Site Provisioning	   	      //
// ================================== //

	
// ======================================================================================== //
//                              D27351 Instrument Errors									//
//	Tabs: ->																				//
//			Initializaiton Errors															//
//			Miscellaneous Errors															//  
//			Instrument Errors																//
//			Fatal Errors																	//
//			Self-Test Errors																//
// ======================================================================================== //

// ================================== //
//      Initialization Errors Tab     //
// ================================== //
	
	@Test
	public void inite2_6() {
		InitializationErrors.inite2_6Run();
	}
	@Test
	public void inite7() {
		InitializationErrors.inite7Run();
	}
	@Test
	public void inite8() {
		InitializationErrors.inite8Run();
	}
	@Test
	public void inite9() {
		InitializationErrors.inite9Run();
	}
	@Test
	public void inite10_33() {
		InitializationErrors.inite10_33Run();
	}
	
// ================================== //
//      Miscellaneous Errors Tab      //
// ================================== //
	
// ================================== //
//      Instrument Errors Tab         //
// ================================== //
	
	@Test
	public void ie1_7() {
		InstrumentErrors.ie1_7Run();
	}
	@Test
	public void ie8_12() {
		InstrumentErrors.ie8_12Run();
	}
	@Test
	public void ie14() {
		InstrumentErrors.ie14Run();
	}
	@Test
	public void ie16() {
		InstrumentErrors.ie16Run();
	}
	@Test
	public void ie17() {
		InstrumentErrors.ie17Run();
	}
	@Test
	public void ie18_35() {
		InstrumentErrors.ie18_35Run();
	}
	@Test
	public void ie36() {
		InstrumentErrors.ie36Run();
	}
	@Test
	public void ie37_44() {
		InstrumentErrors.ie37_44Run();
	}
	@Test
	public void ie45() {
		InstrumentErrors.ie45Run();
	}
	@Test
	public void ie46() {
		InstrumentErrors.ie46Run();
	}
	
// ================================== //
//      Fatal Errors Tab              //
// ================================== //
	
	@Test
	public void fe1() {
		FatalErrors.fe1Run();
	}
	@Test
	public void fe2_18() {
		FatalErrors.fe2_18Run();
	}
	@Test
	public void fe20_24() {
		FatalErrors.fe20_24Run();
	}
	@Test
	public void fe25_26() {
		FatalErrors.fe25_26Run();
	}
	@Test
	public void fe27() {
		FatalErrors.fe27Run();
	}
	@Test
	public void fe30() {
		FatalErrors.fe30Run();
	}
	@Test
	public void fe32_38() {
		FatalErrors.fe32_38Run();
	}
	@Test
	public void fe42_51() {
		FatalErrors.fe42_51Run();
	}
	
// ================================== //
//      Self-Test Errors Tab          //
// ================================== //
	
	@Test
	public void st1_21() {
		SelfTestErrors.st1_21Run();
	}
	
// ======================================================================================== //
//                              D27354 Results Generation									//
//	Tabs: ->																				//
//			Encryption																		//
//			NFC Tag Generator																//  
//			Reading a Cartridge Tag															//
//			Results Generation																//
//			Running Tests on Omni															//
//			Self-Test Generation															//
// ======================================================================================== //

// ================================== //
//		Encryption					  //
// ================================== //
	
	@Test
	public void enc1Run() {
		Encryption.enc1Run();
	}
	
// ================================== //
//		NFC Tag Generator	          //
// ================================== //
	
// ================================== //
//		Reading a Cartridge Tag       //
// ================================== //
	
// ================================== //
//		Results Generation	          //
// ================================== //
	
	@Test
	public void rg1() {
		ResultsGeneration.rg1Run();
	}
	@Test
	public void rg3() {
		ResultsGeneration.rg3Run();
	}
	
// ================================== //
//		Running Tests On Omni	      //     
// ================================== //
	
	@Test
	public void rt1() {
		RunningTestsOnOmni.rt1Run();
	}
	@Test
	public void rt2() {
		RunningTestsOnOmni.rt2Run();
	}
	@Test
	public void rt3() {
		RunningTestsOnOmni.rt3Run();
	}
	@Test
	public void rt5() {
		RunningTestsOnOmni.rt5Run();
	}
	
// ================================== //
//		Self-Test Generation          //
// ================================== //
	
	@Test
	public void stg1() {
		SelfTestGeneration.stg1Run();
	}
	@Test
	public void stg2() {
		SelfTestGeneration.stg2Run();
	}
	
// ======================================================================================== //
//                              D27355 Instrument Status and Maintenance					//
//	Tabs: ->																				//
//			Instrument Status																//
//			Maintenance																		//  
// ======================================================================================== //

// ================================== //
//		Instrument Status			  //
// ================================== //
	
	@Test
	public void ist3() {
		InstrumentStatus.ist3Run();
	}
	@Test
	public void ist4() {
		InstrumentStatus.ist4Run();
	}
	@Test
	public void ist5() {
		InstrumentStatus.ist5Run();
	}

// ================================== //
//		Maintenance			          //
// ================================== //
	
	@Test
	public void mtn1() {
		Maintenance.mtn1Run();
	}
	@Test
	public void mtn2() {
		Maintenance.mtn2Run();
	}
	
// ======================================================================================== //
//                              D27505 Events												//
//	Tabs: ->																				//
//			Handheld Events																	//
// ======================================================================================== //

// ================================== //
//		Handheld Events				  //
// ================================== //
	
// ======================================================================================== //
//                              D27623 Offline Mode											//
//	Tabs: ->																				//
//			Downloading Test Results														//
//			Running and Printing Tests														//
//			Uploading Test Results															//
// ======================================================================================== //

// ================================== //
//		Downloading Test Results	  //
// ================================== //
	
// ================================== //
//		Running and Printing Tests	  //
// ================================== //
	
// ================================== //
//		Uploading Test Results		  //
// ================================== //
	
}