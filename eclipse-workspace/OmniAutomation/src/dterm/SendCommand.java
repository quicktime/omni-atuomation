package dterm;

/**
 * Sends parametized commands to DTerm
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class SendCommand extends BaseState {

	/**
	 * Sends the passed-in command to DTerm
	 * @param command - passed-in command to send to DTerm
	 */
	public static void send(String command) {
		textField("DScript Terminal - v1009 002.TextField").setText(command);
		pushButton("DScript Terminal - v1009 002.Enter").select();
	}
}
