package putty;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Dialog;
import com.borland.silktest.jtf.IBaseState;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.RadioList;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.Tree;

/**
 * Handles the BaseState, desktop, and object finds (silk functions) for putty
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class BaseState {
	/**
	 * Putty's desktop object
	 */
	private static Desktop desktop = new Desktop();

	/**
	 * Sets the BaseState
	 */
	public static void baseState() {
		BaseState baseState = new BaseState();
		((IBaseState) baseState).execute(getDesktop());
	}

	/**
	 * Gets the current desktop
	 * @return desktop
	 */
	public static Desktop getDesktop() {
		return desktop;
	}

	/**
	 * Sets the desktop
	 * @param desktop - desktop to set BaseState.desktop to
	 */
	public void setDesktop(Desktop desktop) {
		BaseState.desktop = desktop;
	}
	
	public static Window window(String string) {
		return desktop.<Window>find(string);
	}
	
	public static Tree tree(String string) {
		return desktop.<Tree>find(string);
	}
	
	public static TextField textField(String string) {
		return desktop.<TextField>find(string);
	}
	
	public static RadioList radioList(String string) {
		return desktop.<RadioList>find(string);
	}
	
	public static PushButton pushButton(String string) {
		return desktop.<PushButton>find(string);
	}
	
	public static Dialog dialog(String string) {
		return desktop.<Dialog>find(string);
	}
	
	/**
	 * Finds the "10 11 14 2 - PuTTY" Window locator
	 * @return the found Window object
	 */
	public static Window window() {
		return desktop.<Window>find("10 11 14 2 - PuTTY");
	}
	
	public static boolean exists(String locator) {
		return desktop.exists(locator);
	}
}