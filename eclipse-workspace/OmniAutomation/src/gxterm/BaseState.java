package gxterm;

import java.io.IOException;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.Dialog;
import com.borland.silktest.jtf.IBaseState;
import com.borland.silktest.jtf.ListBox;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.TextField;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.win32.AccessibleControl;

import testcontrol.Main;

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
	 * @return current desktop
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
	 * Finds the "GeneXpert Interface - v1008" Window locator
	 * @return the found Window object
	 */
	public static Window window() {
		return desktop.<Window>find("GeneXpert Interface - v1008");
	}

	/**
	 * Finds the Window locator of the passed-in locator
	 * @param locator - window locator to find
	 * @return the found Window object
	 */
	public static Window window(String locator) {
		return desktop.<Window>find("GeneXpert Interface - v1008." + locator);
	}

	/**
	 * Finds the Dialog object of the passed-in locator
	 * @param locator
	 * @return
	 */
	public static Dialog dialog(String locator) {
		return desktop.<Dialog>find("GeneXpert Interface - v1008." + locator);
	}

	/**
	 * Finds the passed-in PushButton locator
	 * @param string - PushButton to find
	 * @return the found PushButton object
	 */
	public static PushButton pushButton(String string) {
		return desktop.<PushButton>find("GeneXpert Interface - v1008." + string);
	}

	/**
	 * Finds the passed-in TextField locator
	 * @param string - TextField to find
	 * @return the found TextField object
	 */
	public static TextField textField(String string) {
		return desktop.<TextField>find("GeneXpert Interface - v1008." + string);
	}

	/**
	 * Finds the passed-in AccessibleControl locator
	 * @param string - AccessibleControl locator to find
	 * @return the found AccessibleControl object
	 */
	public static AccessibleControl accessibleControl(String string) {
		return desktop.<AccessibleControl>find("GeneXpert Interface - v1008." + string);
	}
	
	/**
	 * Finds the ListBox object of the passed-in locator
	 * @param locator - locator of the ListBox
	 * @return the found ListBox object
	 */
	public static ListBox listBox(String locator) {
		return desktop.<ListBox>find("GeneXpert Interface - v1008." + locator);
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
	 * Determines whether Gxterm window exists or not
	 * @return boolean for if locator exists or not
	 */
	public static boolean exists() {
		return desktop.exists("GeneXpert Interface - v1008.");
	}

	/**
	 * Opens DB Browser
	 */
	public static void run() {
		if (exists("GeneXpert Interface - v1008")) { // if DB Browser shell is already open
			window("GeneXpert Interface - v1008").close(); // close it
		}
		try { // to catch IOEXception from .exec()
			Process p = Runtime.getRuntime()
					.exec(new String[] { Main.programsPath + "DTerm_1099\\interfaces\\iface_omni.exe" });
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
	}
}
