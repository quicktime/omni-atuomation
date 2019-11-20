package dterm;

/**
 * Handles all NFC actions taken in DTerm
 * @author Brendan Dolan
 * @date Created on: Mar 26, 2018
 */
public class DTermNFC extends BaseState {
	
	/**
	 * Sets the cartridge nfc tag to the passed in value
	 * @param nfc
	 */
	public static void setCartNFC(String nfc) {
		window().setActive();
		SendCommand.send("load interface omni");
		SendCommand.send("device 1");
		SendCommand.send("setcartnfc " + nfc);
	}
}
