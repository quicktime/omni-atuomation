package winscp;

import java.io.IOException;

import com.borland.silktest.jtf.Control;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.IBaseState;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.win32.AccessibleControl;

import testcontrol.Main;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.common.types.TextPosition;
import com.borland.silktest.jtf.Dialog;

/**
 * Handles the BaseState, desktop, and object finds (all silk functions) for WinSCP
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class BaseState {
	
	/**
	 * WinSCP's Desktop object
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
		return desktop.<Window>find("10 11 14 2 - WinSCP");
	}
	
	/**
	 * Finds the passed-in PushButton locator
	 * @param string - PushButton loactor to find
	 * @return the found PushButton object
	 */
	public static PushButton pushButton(String string) {
		return desktop.<PushButton>find("10 11 14 2 - WinSCP." + string);
	}
	
	/**
	 * Finds the passed-in AccessibleControl locator
	 * @param string - AccessibleControl locator to find
	 * @return the found AccessibleControl object
	 */
	public static AccessibleControl accessibleControl(String string) {
		return desktop.<AccessibleControl>find("10 11 14 2 - WinSCP." + string);
	}
	
	/**
	 * Finds the passed-in Control locator
	 * @param string - Control locator to find
	 * @return the found Control object
	 */
	public static Control control(String string) {
		return desktop.<Control>find("10 11 14 2 - WinSCP." + string);
	}
	
	/**
	 * Finds the passed-in TextField locator
	 * @param string - TextField locator to find
	 * @return the found TextField object
	 */
	public static TextField textField(String string) {
		return desktop.<TextField>find("10 11 14 2 - WinSCP." + string);
	}
	
	/**
	 * Determines whether passed-in locator exists on the current screen
	 * @param locator - locator to determine whether or not it exists
	 * @return boolean for if locator exists or not
	 */
	public static boolean exists(String locator) {
		return desktop.exists("10 11 14 2 - WinSCP." + locator);
	}
	
	/**
	 * Determines whether WinSCP window exists or not
	 * @return boolean for if locator exists or not
	 */
	public static boolean exists() {
		return desktop.exists("10 11 14 2 - WinSCP");
	}
	
	/**
	 * Opens WinSCP
	 */
	public static void open() {
		if (exists()) { // if WinSCP is already open
			window().close(); // close it
		}
		
		try { // to catch IOEXception from .exec()
			Process p = Runtime.getRuntime().exec(new String[] {Main.path + "programs\\WinSCP\\WinSCP.exe"});
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
	}
	
	public static void configuration() {
		open();
//		desktop.<Window>find("Login").setActive();
//		desktop.<PushButton>find("Login.Tools").select();
//		desktop.<Control>find("Login.&Tools").typeKeys("<Down><Down><Enter>");
//		desktop.<PushButton>find("Login.Warning.OK PushButton").select();
//		desktop.<TextField>find("Login.Select file to import configuration from.File name")
//				.setText(Main.path + "programs\\WinSCP\\WinSCP.ini");
//		desktop.<PushButton>find("Login.Select file to import configuration from.Open").select();
//		desktop.<AccessibleControl>find("Login.outline").click();
		desktop.<Control>find("Login.TTreeView").typeKeys("<Down><Enter>");
	}
	
	public static TextField textField2(String locator) {
		return desktop.<TextField>find(locator);
	}
	
	public static PushButton pushButton2(String locator) {
		return desktop.<PushButton>find(locator);
	}
	
	public static Control control2(String locator) {
		return desktop.<Control>find(locator);
	}
}
