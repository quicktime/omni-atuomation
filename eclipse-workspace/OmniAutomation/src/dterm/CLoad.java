package dterm;

/**
 * Performs all actions necesssary for CLoad
 * @author Brendan Dolan
 * @date Created on: Mar 22, 2018
 */
public class CLoad extends BaseState {

	/**
	 * Performs cload with default params (103 0 64)
	 */
	public static void cload() {
		window().setActive();
		SendCommand.send("load interface omni");
		SendCommand.send("device 1");
		SendCommand.send("cload 103 0 64");
	}
	
	/**
	 * Performs cload with specified params
	 * @param params - optional params for cload
	 */
	public static void cload(String params) {
		window().setActive();
		SendCommand.send("load interface omni");
		SendCommand.send("device 1");
		SendCommand.send("cload " + params);
	}
}
