package dterm;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.IBaseState;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.common.types.TextPosition;

/**
 * Handles the BaseState, desktop, and object finds (all silk functions for DTerm
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class BaseState {
	
	/**
	 * DTerm's desktop object
	 */
	private static Desktop desktop = new Desktop();

	/**
	 * Sets the BaseState
	 */
	public void baseState() {
		BaseState baseState = new BaseState();
		((IBaseState) baseState).execute(desktop);
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
	public static void setDesktop(Desktop desktop) {
		BaseState.desktop = desktop;
	}
	
	/**
	 * Finds the passed-in Window locator
	 * @param locator - Window locator to find
	 * @return the found Window object
	 */
	public static Window window() {
		return desktop.<Window>find("DScript Terminal - v1009 002");
	}
	
	/**
	 * Finds the passed-in TextField locator
	 * @param locator - TextField locator to find
	 * @return the found TextField object
	 */
	public static TextField textField(String locator) {
		return desktop.<TextField>find(locator);
	}
	
	/**
	 * Finds the passed-in PushButton locator
	 * @param locator - PushButton locator to find
	 * @return the found PushButton object
	 */
	public static PushButton pushButton(String locator) {
		return desktop.<PushButton>find(locator);
	}
}
