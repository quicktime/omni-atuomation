package postman;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.IBaseState;
import com.borland.silktest.jtf.Window;

/**
 * Handles the BaseState, desktop, and object finds (all silk function) for Postman
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class BaseState {
	
	/**
	 * Postman's Desktop object
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
	 * @return desktop
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
	 * Finds the "Postman" Window locator
	 * @return the found Window object
	 */
	public static Window window() {
		return desktop.<Window>find("Postman");
	}

}
