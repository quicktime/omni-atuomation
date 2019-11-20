package gxterm;

import com.borland.silktest.jtf.Desktop;
import org.junit.Test;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.common.types.TextPosition;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.ListBox;

/**
 * Handles all actions in GxTerm
 * @author Brendan Dolan
 * @date Created on: Apr 12, 2018
 */
public class GxTerm extends BaseState {
	
	/**
	 * Presses the send button
	 */
	public static void send() {
		pushButton("Send").select();
	}

	/**
	 * Send a passed-in command to gxterm
	 * @param command - command to send
	 */
	public static void typeKeys(String command) {
		window().setActive();
		textField("TextField").setText(command);
		send();
	}
	
	/**
	 * Sends the discover command followed by the device 1 command
	 */
	public static void connect() {
		run();
		typeKeys("discover");
		typeKeys("device 1");
	}
	
	/**
	 * Sets the cartridge nfc tag to the passed-in tag
	 * @param nfc - nfc tag to write to cartridge
	 */
	public static void setCartNFC(String nfc) {
		connect();
		typeKeys("setcartnfc " + nfc);
	}

}
