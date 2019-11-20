package putty;

import com.borland.silktest.jtf.Utils;

import testcontrol.Main;

import java.io.IOException;
import java.util.ArrayList;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Tree;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.common.types.TextPosition;
import com.borland.silktest.jtf.common.types.TextRange;

import sheet.PuttyLog;

import com.borland.silktest.jtf.RadioList;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.Dialog;

/**
 * putty class handles all putty control
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class Putty extends BaseState {

	/**
	 * Runs the putty executable
	 */
	public static void run() {
		try { // to catch IOEXception from .exec()
	        Process p = Runtime.getRuntime().exec(new String[] {Main.path + "programs\\Putty\\putty.exe"}); // run putty
	    } catch (IOException e) {
	        Main.debug.LOGWarning(e.toString());
	    }
	}

	/**
	 * Loads the omni IP address and port
	 */
	public static void configuration() {
		close();
		run();
		window("PuTTY Configuration").setActive();
		textField("PuTTY Configuration.Host Name (or IP address)").setText("10.11.14.2");
		textField("PuTTY Configuration.Port").setText("22");
		radioList("PuTTY Configuration.SSH").select("SSH");
		tree("PuTTY Configuration.Category").select("/Session/Logging");
		radioList("PuTTY Configuration.All session output").select("All session output");
		textField("PuTTY Configuration.Log file name").setText(Main.path + "programs\\Putty\\putty.log");
		radioList("PuTTY Configuration.Always overwrite it").select("Always overwrite it");
		pushButton("PuTTY Configuration.Open").select();
		if (exists("10 11 14 2 - PuTTY.PuTTY Security Alert")) { // if security popup appears
			dialog("10 11 14 2 - PuTTY.PuTTY Security Alert").setActive();
			pushButton("10 11 14 2 - PuTTY.PuTTY Security Alert.Yes").select();
		}
		window().setActive();
		window().typeKeys("root<Enter>"); // user
		window().typeKeys("adytum<Enter>"); // pw TODO: hash the pw
	}
	
	/**
	 * Kills the passed-in process
	 * @param process - process name to kill
	 */ 
	public static void killProcess(String process) {
		Main.debug.LOG("Attempting to kill GxCellCoregateway process...");
		String log = Main.path + "programs\\Putty\\putty.log";
		String PID = null;
		
		window().setActive();
		window().typeKeys("ps -ef|grep " + process + "<Enter>");
		close();
		PID = PuttyLog.grepOutput(log); // sets PID to the return of grepOutput on the log file
		configuration();
		window().typeKeys("kill -9 " + PID + "<Enter>");
	}

	/**
	 * initErrors 'cd's to the proper directory, creates and edits InitError.txt file with code param
	 * @param code - code to enter into InitError.txt file
	 */
	public static void initErrors(String code) {
		window().setActive();
		window().typeKeys("cd /sandbox/home<Enter>");
		window().typeKeys("vi InitError.txt<Enter>");
		window().typeKeys("i" + code + "<Esc>");
		Utils.sleep(500);
		window().typeKeys("ZZ");
		window().typeKeys("dos2unix InitError.txt<Enter>");
	}
	
	/**
	 * starts the epsilon service
	 */
	public static void start() {
		window().typeKeys("service epsilon start<Enter>");
	}
	
	/**
	 * stops the epsilon service
	 */
	public static void stop() {
		window().typeKeys("service epsilon stop<Enter>");
	}
	
	/**
	 * restarts the epsilon service
	 */
	public static void restart() {
		Main.debug.LOG("Restarting instrument...");
		window().typeKeys("service epsilon restart<Enter>");
		Utils.sleep(1000);
		Main.debug.LOG("Instrument restarted.");
	}
	
	/**
	 * deletes epsilon.bak
	 */
	public static void deleteBak() {
		Main.debug.LOG("Deleting backup...");
		typeKeys("cd /sandbox/omni-data/backup/");
		typeKeys("rm epsilon.bak");
	}
	
	/**
	 * deletes epsilon.db
	 */
	public static void deleteDB() {
		Main.debug.LOG("Deleting database...");
		typeKeys("cd /sandbox/omni-data/");
		typeKeys("rm epsilon.db");
	}
	
	/**
	 * deletes epsilon.db and epsilon.bak
	 */
	public static void deleteDBBak() {
		Main.debug.LOG("Deleting database & backup...");
		typeKeys("cd /sandbox/omni-data/");
		typeKeys("rm epsilon.db");
		typeKeys("cd backup");
		typeKeys("rm epsilon.bak");
	}
	
	/**
	 * Gets the passed-in process name's PID
	 * @param process - process name to get PID of
	 */
	public static void psEfGrep(String process) {
		configuration();
		typeKeys("cd /");
		typeKeys("ps -ef|grep " + process);
		close();
	}
	
	/**
	 * cd's to logs directory
	 */
	public static void goToLogs() {
		typeKeys("cd /sandbox/epsilon-java/Epsilon_Java_jar-1.0/logs");
	}
	
	public static void goToJava() {
		typeKeys("cd /sandbox/epsilon-java/Epsilon_Java_jar-1.0");
	}
	
	public static void goToOmniData() {
		typeKeys("cd /sandbox/omni-data");
	}
	/**
	 * Sends a command to putty
	 * @param command
	 */
	public static void typeKeys(String command) {
		window().typeKeys(command + "<Enter>");
	}
	
	public static void lsL() {
		typeKeys("ls -l");
	}
	
	/**
	 * Sends an ls -l -tr command to the current directory
	 */
	public static void lsLTr() {
		typeKeys("ls -l -tr");
	}
	
	/**
	 * Sends an ls -tr command to the current directory
	 */
	public static void lsTR() {
		typeKeys("ls -tr");
	}

	/**
	 * Closes existing windows of putty
	 */
	public static void close() {
		if (exists("10 11 14 2 - PuTTY")) { // if putty shell is already open
			window("10 11 14 2 - PuTTY").close(); // close it
			pushButton("10 11 14 2 - PuTTY.PuTTY Exit Confirmation.OK").select();
		}
		if (exists("PuTTY Configuration")) {
			window("PuTTY Configuration").close();
		}
	}
}
