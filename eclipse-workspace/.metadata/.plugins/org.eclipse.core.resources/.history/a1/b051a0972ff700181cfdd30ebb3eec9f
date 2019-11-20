package db_browser;

import java.io.IOException;

import org.junit.Before;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Dialog;
import com.borland.silktest.jtf.IBaseState;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.win32.AccessibleControl;

import testcontrol.Main;

/**
 * Handles the BaseState, desktop, and object finds (all silk functions) for DB Browser
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class BaseState {
	
	/**
	 * DB Browser's Desktop object
	 */
	private static Desktop desktop = new Desktop();

	/**
	 * Sets the BaseState
	 */
	public void baseState() {
		BaseState baseState = new BaseState(); // new BaseState object
		((IBaseState) baseState).execute(getDesktop()); // executes the BaseState based on the getter
	}
	
	/**
	 * Gets the current desktop
	 * @return
	 */
	public static Desktop getDesktop() {
		return desktop;
	}

	/**
	 * Sets the desktop
	 * @param desktop - desktop to set BaseState.Desktop to
	 */
	public void setDesktop(Desktop desktop) {
		BaseState.desktop = desktop;
	}
	
	/**
	 * Finds the "DB Browser for SQLite" Window locator
	 * @return the found Window object
	 */
	public static Window window() {
		return desktop.<Window>find("DB Browser for SQLite");
	}
	
	/**
	 * Finds the Window locator of the passed-in locator
	 * @param locator - window locator to find
	 * @return the found Window object
	 */
	public static Window window(String locator) {
		return desktop.<Window>find(locator);
	}
	
	public static Dialog dialog(String locator) {
		return desktop.<Dialog>find(locator);
	}
	
	/**
	 * Finds the passed-in PushButton locator
	 * @param string - PushButton to find
	 * @return the found PushButton object
	 */
	public static PushButton pushButton(String string) {
		return desktop.<PushButton>find(string);
	}
	
	/**
	 * Finds the passed-in TextField locator
	 * @param string - TextField to find
	 * @return the found TextField object
	 */
	public static TextField textField(String string) {
		return desktop.<TextField>find(string);
	}
	
	/**
	 * Finds the passed-in AccessibleControl locator
	 * @param string - AccessibleControl locator to find
	 * @return the found AccessibleControl object
	 */
	public static AccessibleControl accessibleControl(String string) {
		return desktop.<AccessibleControl>find(string);
	}
	
	/**
	 * Determines whether passed-in locator exists on the current screen
	 * @param locator - locator to determine whether or not it exists
	 * @return boolean for if locator exists or not
	 */
	public static boolean exists(String locator) {
		return desktop.exists(locator);
	}
	
	/**
	 * Determines whether DB Browser window exists or not
	 * @return boolean for if locator exists or not
	 */
	public static boolean exists() {
		return desktop.exists("DB Browser for SQLite");
	}
	
	/**
	 * Opens DB Browser
	 */
	public static void run() {
		if (exists("DB Browser for SQLite - swattestexcc")) { // if DB Browser shell is already open
			window("DB Browser for SQLite - swattestexcc").close(); // close it
		}
		if (exists("DB Browser for SQLite")) { // if DB Browser window is open
			window("DB Browser for SQLite").close(); // close it
		}
		
		try { // to catch IOEXception from .exec()
			Process p = Runtime.getRuntime().exec(new String[] {Main.path + "programs\\DB Browser for SQLite\\DB Browser for SQLite.exe"});
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
	}
}
