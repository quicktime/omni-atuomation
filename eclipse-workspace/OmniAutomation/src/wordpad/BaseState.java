package wordpad;

import java.io.IOException;

import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Dialog;
import com.borland.silktest.jtf.IBaseState;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.win32.AccessibleControl;

import testcontrol.Main;

/**
 * Handles all actions in Wordpad
 * @author Brendan Dolan
 * @date Created on: Apr 3, 2018
 */
public class BaseState {
	
	/**
	 * Wordpad's Desktop object
	 */
	private static Desktop desktop = new Desktop();

	/**
	 * Sets the BaseState
	 */
	public static void baseState() {
		BaseState baseState = new BaseState(); // BaseState object
		((IBaseState) baseState).execute(getDesktop()); // executes the BaseState based on the getter
	}

	/**
	 * Gets the current desktop
	 * @return current desktop
	 */
	public static Desktop getDesktop() {
		return desktop;
	}

	/**
	 * Sets the desktop to the passed-in desktop
	 * @param desktop - desktop to set BaseState.Desktop to
	 */
	public void setDesktop(Desktop desktop) {
		BaseState.desktop = desktop;
	}
	
	/**
	 * Finds the "10 11 14 2 - WinSCP" Window locator 
	 * @return the found Window object
	 */
	public static Window window() {
		return desktop.<Window>find("Document - WordPad");
	}
	
	/**
	 * Finds the passed-in PushButton locator
	 * @param string - PushButton loactor to find
	 * @return the found PushButton object
	 */
	public static PushButton pushButton(String string) {
		return desktop.<PushButton>find("Document - WordPad." + string);
	}
	
	/**
	 * Finds the passed-in AccessibleControl locator
	 * @param string - AccessibleControl locator to find
	 * @return the found AccessibleControl object
	 */
	public static AccessibleControl accessibleControl(String string) {
		return desktop.<AccessibleControl>find("Document - WordPad." + string);
	}
	
	/**
	 * Finds the passed-in Control locator
	 * @param string - Control locator to find
	 * @return the found Control object
	 */
	public static Control control(String string) {
		return desktop.<Control>find("Document - WordPad." + string);
	}
	
	/**
	 * Finds the passed-in TextField locator
	 * @param string - TextField locator to find
	 * @return the found TextField object
	 */
	public static TextField textField(String string) {
		return desktop.<TextField>find("Document - WordPad." + string);
	}
	
	/**
	 * Finds the passed-in Dialog locator
	 * @param string - Dialog locator to find
	 * @return the found Dialog object
	 */
	public static Dialog dialog(String string) {
		return desktop.<Dialog>find("Document - WordPad." + string);
	}
	
	/**
	 * Determines whether passed-in locator exists on the current screen
	 * @param locator - locator to determine whether or not it exists
	 * @return boolean for if locator exists or not
	 */
	public static boolean exists(String locator) {
		return desktop.exists("Document - WordPad." + locator);
	}
	
	/**
	 * Determines whether WordPad window exists or not
	 * @return boolean for if locator exists or not
	 */
	public static boolean exists() {
		return desktop.exists("Document - WordPad");
	}
	
	/**
	 * Opens WordPad
	 */
	public static void run() {
		if (exists()) { // if WordPad shell is already open
			window().close(); // close it
		}
		
		try { // to catch IOEXception from .exec()
			Process p = Runtime.getRuntime().exec(new String[] {Main.path + "programs\\WordPad\\wordpad.exe"});
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
	}
}
