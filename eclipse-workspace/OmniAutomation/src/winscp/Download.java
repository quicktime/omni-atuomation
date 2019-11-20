package winscp;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.win32.AccessibleControl;

import helper.StringManipulation;
import testcontrol.Main;

import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;

import java.io.IOException;

import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.common.types.TextPosition;

/**
 * Handles all database interactions with WinSCP
 * @author Brendan Dolan
 * @date Created on: Mar 13, 2018
 */
public class Download extends BaseState {
	
	private static String testDirectory = Main.path + "files\\databases\\test\\";

	/**
	 * Downloads epsilon.db and epsilon.bak to the passed-in directory, and the test directory
	 * @param directory - the supplied directory to download the DB to
	 */
	public static void downloadDB(String directory) {
		configuration();
		window().setActive();
		window().maximize();
		pushButton("Root Directory").click();
		pushButton("Root Directory2").click();
		control("TUnixDirView").typeKeys("<Left Ctrl+o>");
		textField("Open directory.TextField").setText("/sandbox/omni-data/");
		pushButton("Open directory.OK").select();
		accessibleControl("epsilon db").click();
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(directory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to create it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}
		accessibleControl("epsilon db").click();
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(testDirectory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to create it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}

		accessibleControl("backup").doubleClick();
		accessibleControl("epsilon bak").click();
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(directory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to create it
		if (exists("Download.Confirm.OK")) {
			Main.debug.LOG("Download.Confirm.OK popped up");
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite.
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}
		window().close();
	}
	
	/**
	 * Downloads omni-epsilon.log file to the passed-in directory and the test directory
	 * @param directory - the supplied directory to download the log to
	 */
	public static void downloadLog(String directory) {
		configuration();
		window().setActive();
		window().maximize();
		pushButton("Root Directory").select();
		pushButton("Root Directory2").select();
		control("TUnixDirView").typeKeys("<Left Ctrl+o>");
		textField("Open directory.TextField").setText("/sandbox/epsilon-java/Epsilon_Java_jar-1.0/logs/");		
		pushButton("Open directory.OK").select();
		
		// Downloads to specific directory
		accessibleControl("omni-epsilon log").click();
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(directory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to creat it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}
		
		//Downloads to test directory
		accessibleControl("omni-epsilon log").click();
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(testDirectory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to creat it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}
		window().close();
	}
	
	/**
	 * Downloads gxmini/Logs/* to the specified directory, and the test directory
	 * @param directory - the local directory to download the log to
	 */
	public static void downloadGxMiniLog(String directory) {
		configuration();
		window().setActive();
		window().maximize();
		pushButton("Root Directory").select();
		pushButton("Root Directory2").select();
		control("TUnixDirView").typeKeys("<Left Ctrl+o>");
		textField("Open directory.TextField").setText("/sandbox/gxmini/Logs/");		
		pushButton("Open directory.OK").select();
		
		// Downloads to specific directory
		accessibleControl("LogFile").click();
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(directory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to create it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}
		
		// Downloads to test directory
		accessibleControl("LogFile").click();
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(testDirectory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to creat it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}
		window().close();
	}
	
	public static void downloadFile(String file, String localFilePath, String remoteFilePath) {
		configuration();
		window().setActive();
		window().maximize();
		pushButton("Root Directory").select();
		pushButton("Root Directory2").select();
		control("TUnixDirView").typeKeys("<Left Ctrl+o>");
		textField("Open directory.TextField").setText(remoteFilePath);
		pushButton("Open directory.OK").select();

		// Downloads to specific directory
		pushButton("Find Files").select();
		textField2("Find.TextField").setText(file);
		pushButton2("Find.Start").select();
		control2("Find.&Start").typeKeys("<Down>");
		control2("Find.Close").typeKeys("<Down>");
		control2("Find.&Help").typeKeys("<Down>");
		control2("Find.TIEListView").typeKeys("<Down>");
		pushButton2("Find.Focus").select();

		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(localFilePath);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to creat it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}

		//Downloads to test directory
		control("TUnixDirView").typeKeys("<F5>");
		textField("Download.TextField").setText(testDirectory);
		pushButton("Download.OK").select();
		// If directory doesn't exist, popup will appear asking to creat it
		if (exists("Download.Confirm.OK")) {
			pushButton("Download.Confirm.OK").select();
		}
		// If file already exists, popup will appear to overwrite
		if (exists("Downloading.Confirm.Yes")) {
			accessibleControl("Downloading.Confirm.Yes").click();
		}
		window().close();
	}
}
