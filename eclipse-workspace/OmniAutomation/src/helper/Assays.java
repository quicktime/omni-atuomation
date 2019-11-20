package helper;

import dterm.DTermNFC;
import gxterm.GxTerm;
import mobile.PendingCommands;
import nfc.NFCTools;

/**
 * Contains all assay names, nfc tags, & locators
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class Assays {
	
	/**
	 * Installs the assay, and writes to both nfc tags for testing
	 * @param assayName - Name of assay to set up
	 * @param nfcTag - NFC tag for assay
	 * @param assayLocator - locator of the assay
	 */
	public static void assaySetup(String assayName, String nfcTag, String assayLocator) {
		PendingCommands.installAssay(assayName, assayLocator); // install the assay to the omni instrument
		NFCTools.writeTag(nfcTag); // write the assay's nfc tag to the tag under the phone
		GxTerm.setCartNFC(nfcTag); // write the assay's nfc tag to the tag within the omni instrument
	}
	
	public final static String HIV_1_Qual_v2[] =	
		{	
			"HIV-1 Qual v2", 
			"0604641310220070247455521;f:06;p:464;l:131;r:5;an:spc,hiv-1,qc-1,qc-2,iqsh;np:0;nf:0,0,0,0,0;pl:432,199,221,332,1000;ph:127,68,51,76,1;ef:-9999.0,-9999.0,-9999.0,-9999.0,-9999.0;cs:f0901c58", 
			"Device.assays.Xpert HIV-1 Qual, Version 2"
		};
	public final static String MTB_RIF_Assay_G4_v5[] = 
		{
			"MTB-RIF Assay G4 v5", 
			"0403792220218460588822562;F:04;P:379;L:222;R:8;AN:Probe D,Probe C,Probe E,Probe B,SPC,Probe A,QC-1,QC-2;NF:194,284,486,188,568,172,76,81;PL:999,999,999,999,999,999,999,999;PH:0,0,0,0,0,0,0,0;EF:-9999.0,-9999.0,-9999.0,-9999.0,-9999.0,-9999.0,-9999.0,-9999.0;CS:CF04A6BF",
			"Device.assays.Xpert MTB-RIF Assay G4, Version:5"
		};
	public final static String MTB_RIF_Ultra_v1[] =
		{
			"MTB-RIF Ultra v1",
			"nfc",
			"Device.assays.Xpert MTB-RIF Ultra, Version:1"
		};
	public final static String NON_TERMINATION_ERROR[] =
		{
			"NON_TERMINATION1 Name",	// 0
			"NON_TERMINATION1 NFCTag",	// 1
			"NON_TERMINATION1 Locator",	// 2
			"NON_TERMINATION2 Name",	// 3
			"NON_TERMINATION2 NFCTag",	// 4
			"NON_TERMINATION2 Locator",	// 5
			"NON_TERMINATION3 Name",	// 6
			"NON_TERMINATION3 NFCTag",	// 7
			"NON_TERMINATION3 Locator",	// 8
			"NON_TERMINATION4 Name",	// 9
			"NON_TERMINATION4 NFCTag",	// 10
			"NON_TERMINATION4 Locator",	// 11
			"NON_TERMINATION5 Name",	// 12
			"NON_TERMINATION5 NFCTag",	// 13
			"NON_TERMINATION5 Locator"	// 14
		};
	public final static String TERMINATION_ERROR[] =
		{
			"TERMINATION1 Name",	// 0
			"TERMINATION1 NFCTag",	// 1
			"TERMINATION1 Locator",	// 2
			"TERMINATION2 Name",	// 3
			"TERMINATION2 NFCTag",	// 4
			"TERMINATION2 Locator",	// 5
			"TERMINATION3 Name",	// 6
			"TERMINATION3 NFCTag",	// 7
			"TERMINATION3 Locator",	// 8
			"TERMINATION4 Name",	// 9
			"TERMINATION4 NFCTag",	// 10
			"TERMINATION4 Locator"	// 11
		};
	public final static String SONICATION[] =
		{
			"SONICATION Name",
			"SONICATION NFCTag",
			"SONICATION Locator"
		};
	public final static String RG1[] =
		{
			"Xpert HIV-1 Qual",
			"",
			"",
		};
	public final static String Omni_Field_Test_v2[] =
		{
			"Omni Field Test 2 name",
			"",
			"",
		};
	public static final String[] ENC1 =
		/**
		 * 	has a command sequence that contains the following: 
			Log Preasure On, 
			Pressurize Tube, 
			Log Pressure Off, 
			Protocol that has 20 - 45 cycles with 1 sec OFF, 2 sec ON
			Depressurize Tube
		 */
		{ 
			"",
			"",
			"",
		};
}
