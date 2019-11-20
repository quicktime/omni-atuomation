package dterm;

/**
 * Runs DTerm commands to close the Omni door
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class CloseDoor extends BaseState {

	/**
	 * loads omni iface, connects to the device and closes the door
	 */
	public static void closeDoor() {
		window().setActive();
		SendCommand.send("load interface omni");
		SendCommand.send("discover");
		SendCommand.send("device 1");
		SendCommand.send("closedoor");
	}
}
