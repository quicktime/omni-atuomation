package winscp;

import java.io.IOException;

import testcontrol.Main;

/**
 * Handles all WinSCP upload functionality
 * @author Brendan Dolan
 * @date Created on: Mar 27, 2018
 */
public class Upload extends BaseState {

	/**
	 * Uploads epsilon.db and epsilon.bak from provided filepath
	 * @param directory
	 */
	public static void uploadDB(String filePath) {
		Main.debug.LOG("Uploading database from " + filePath + "...");
		configuration();
		window().setActive();
		window().maximize();
		pushButton("Root Directory").click();
		pushButton("Root Directory2").click();
		control("TDirView").typeKeys("<Left Ctrl+o>");
		textField("Open directory.TextField").setText(filePath);
		pushButton("Open directory.OK").select();
		// Steps for uploading epsilon.db
		accessibleControl("epsilon db").click();
		control("TDirView").typeKeys("<F5>");
		textField("Upload.TextField").setText("/sandbox/omni-data/*.*");
		pushButton("Upload.OK").select();
		// If file already exists, popup will appear asking to overwrite
		while(exists("Uploading.Confirm.Yes")) {
			accessibleControl("Uploading.Confirm.Yes").click();
		}

		// Steps for uploading epsilon.bak
		accessibleControl("epsilon bak").click();
		control("TDirView").typeKeys("<F5>");
		textField("Upload.TextField").setText("/sandbox/omni-data/backup/*.*");
		pushButton("Upload.OK").select();
		// If file already exists, popup will appear asking to overwrite
		if (exists("Uploading.Confirm.Yes")) {
			accessibleControl("Uploading.Confirm.Yes").click();
		}
		window().close();
		Main.debug.LOG("Database uploaded.");
	}
	
	/**
	 * Uploads a large file to fill the instrument space
	 */
	public static void uploadLargeFile(String filePath) {
		Main.debug.LOG("Uploading large file from " + filePath + "...");
		configuration();
		window().setActive();
		window().maximize();
		pushButton("Root Directory").select();
		pushButton("Open Directory Bookmark").select();
		textField("Open directory.TextField").setText(filePath);
		pushButton("Open directory.OK").select();
		
		// Steps for uploading large file
		accessibleControl("large file").click();
		control("TDirView").typeKeys("<F5>");
		textField("Upload.TextField").setText("/sandbox/omni-data/*.*");
		pushButton("Upload.OK").select();
		// If file already exists, popup will appear asking to overwrite
		while(exists("Uploading.Confirm.Yes")) {
			accessibleControl("Uploading.Confirm.Yes").click();
		}
		window().close();
		Main.debug.LOG("Large file uploaded.");
	}
	
	/**
	 * Uploads a passed-in file to a passed-in directory
	 * @param file - the file to upload
	 * @param localFilePath - local file path where file exists
	 * @param remoteFilePath - remote file path where file will be uploaded
	 */
	public static void uploadFile(String file, String localFilePath, String remoteFilePath) {
		Main.debug.LOG("Uploading " + file + " from " + localFilePath + " to " + remoteFilePath + "...");
		configuration();
		window().setActive();
		window().maximize();
		pushButton("Root Directory").select();
		pushButton("Open Directory Bookmark").select();
		textField("Open directory.TextField").setText(localFilePath);
		pushButton("Open directory.OK").select();
		accessibleControl(file).click();
		control("TDirView").typeKeys("<F5>");
		textField("Upload.TextField").setText(remoteFilePath);
		pushButton("Upload.OK").select();
		// If file already exists, popup will appear asking to overwrite
		while (exists("Uploading.Confirm.Yes")) {
			accessibleControl("Uploading.Confirm.Yes").click();
		}
		window().close();
		Main.debug.LOG("File uploaded.");
	}
}
