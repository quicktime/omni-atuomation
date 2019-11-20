package wordpad;

import java.io.IOException;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.Dialog;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.common.types.TextPosition;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.win32.AccessibleControl;

import testcontrol.Main;

import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;

/**
 * Handles all actions performed in WordPad
 * @author Brendan Dolan
 * @date Created on: Apr 3, 2018
 */
public class WordPad extends BaseState {
	
	/**
	 * Opens a passed-in file
	 * @param filePath - filePath of the file to open
	 */
	public static void OpenFile(String filePath) {
		run();
		window().setActive();
		control("Rich Text Window").typeKeys("<Left Ctrl+o>");
		dialog("Open").setActive();
		textField("Open.File name").setText(filePath);
		pushButton("Open.Open").select();
	}
	
	/**
	 * Closes wordpad.exe
	 */
	public static void close() {
		window().close();
	}
}