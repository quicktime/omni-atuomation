package testcontrol.D23308;

import java.io.IOException;

import db_browser.DBBrowser;
import helper.CompareExpected;
import mobile.Navigation;
import mobile.Processor;
import putty.Putty;
import sheet.DBBrowserExport;
import sheet.GenericSheet;
import sheet.LineCount;
import sheet.PuttyLog;
import sheet.SheetNohup;
import testcontrol.Main;
import winscp.Download;

/**
 * Handles all test cases for the Installation Tab
 * @author Brendan Dolan
 * @date Created on: Apr 9, 2018
 */
public class Installation {
	
	public static final String sheetsPath = Main.sheetsPath + "D23308 Master Media\\Installation\\INST-1\\";
	public static final String filesPath = Main.filesPath + "D23308 Master Media\\Installation\\INST-1\\";
	public static final String databasesPath = Main.databasesPath + "D23308 Master Media\\Installation\\INST-1\\";

	/**
	 * Runs test case INST-1
	 */
	public static void inst1Run() {
		Main.debug.LOG("Starting test case INST-1...");
		
		String dbFilePath = databasesPath;
		String javaFiles = sheetsPath + "java files.csv";
		String configFiles = sheetsPath + "config files.csv";
		String dx_homeFiles = sheetsPath + "dx-home files.csv";
		String nohupSheet = sheetsPath + "nohup.csv";
		String schemaSheet = sheetsPath + "schema_version.csv";
		String javaVersionSheet = sheetsPath + "java version.csv";
		
		String actualSchemaVersion = new String();
		String actualJavaVersion = new String();
		String expectedSchemaVersion = GenericSheet.getRow(schemaSheet, 0);
		String expectedJavaVersion = GenericSheet.getRow(javaVersionSheet, 0);
		
		// TODO: Do an installation to place omni java onto an instrument
		Putty.configuration();
		Putty.goToJava();
		Putty.lsL();
		Putty.close();
		for (int i = 0; i < LineCount.lineCount(javaFiles); i++) {
			String file = GenericSheet.getRow(javaFiles, i);
			PuttyLog.logContains(file, 2);
		}
		Putty.configuration();
		Putty.typeKeys("cd /sandbox");
		Putty.lsL();
		Putty.close();
		PuttyLog.logContains("omni-data", 2);
		Download.downloadFile("nohup-run-epsilon-java.sh", filesPath, "/sandbox/epsilon-java/Epsilon_Java_jar-1.0/");
		for (int i = 0; i < LineCount.lineCount(nohupSheet); i++) {
			String param = GenericSheet.getRow(nohupSheet, i);
			SheetNohup.fileContains(param);
		}
		Putty.configuration();
		Putty.goToOmniData();
		Putty.lsL();
		Putty.close();
		PuttyLog.logContains("epsilon.db", 2);
		Download.downloadDB(dbFilePath);
		DBBrowser.schema_version(dbFilePath);
		DBBrowser.close();
		actualSchemaVersion = DBBrowserExport.dbvSchema_version(dbFilePath);
		CompareExpected.compare(actualSchemaVersion, expectedSchemaVersion);
		Putty.configuration();
		Putty.typeKeys("lsb_release -a");
		Putty.close();
		PuttyLog.logContains("ID:\tmel", 0);
		PuttyLog.logContains("Mentor Embedded Linux", 0);
		Putty.configuration();
		Putty.typeKeys("cd /sandbox/epsilon-java/Epsilon_Java_jar-1.0/config");
		Putty.lsL();
		Putty.close();
		for (int i = 0; i < LineCount.lineCount(configFiles); i++) {
			String file = GenericSheet.getRow(configFiles, i);
			PuttyLog.logContains(file, 2);
		}
		Putty.configuration();
		Putty.typeKeys("cd /sandbox/epsilon-java/Epsilon_Java_jar-1.0/dx-home");
		Putty.lsL();
		Putty.close();
		for (int i = 0; i < LineCount.lineCount(dx_homeFiles); i++) {
			String file = GenericSheet.getRow(dx_homeFiles, i);
			PuttyLog.logContains(file, 2);
		}
		PuttyLog.logNotContains("config", 2);
		// TODO: Open the pdfs on Omni Mobile App. Language should be in English
		Navigation.toProcessor();
		actualJavaVersion = Processor.getJavaVersion();
		CompareExpected.compare(actualJavaVersion, expectedJavaVersion);
		
		Main.debug.LOG("Test case INST-1 passed!");
	}
}
