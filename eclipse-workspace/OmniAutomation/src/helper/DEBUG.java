package helper;

import testcontrol.Main;

/**
 * Handles debug messages
 * @author Brendan Dolan
 * @date Created on: Feb 22, 2018
 */
public class DEBUG {

	private boolean enabled;
	
	/**
	 * Enables or disables debug mode based on DEBUG object intialized in {@link testcontrol.Main}
	 * @param enabled - sets local boolean true or false
	 */
	public DEBUG(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * Logs a stacktrace to std out
	 */
	private void threadOut() {
	    String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
	    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
	    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
	    int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
	    
	    System.out.print(className + "." + methodName + "():" + lineNumber);
	}
	
	
	/**
	 * Logs a debug messsage with a given string to results and std out.
	 * Format: "class.method():line# --- provided string"
	 * @param msg - message to log with info
	 */
	public void LOG(String msg) {
		Main.desktop.logInfo(msg);
		if (enabled) {
			threadOut();
			System.out.println(" --- " + msg);
		}
	}
	
	/**
	 * Logs a debug message to std out.
	 * Format: "class.method():line#
	 */
	public void LOG() {
		if (enabled) {
			threadOut();
		}
	}
	
	/**
	 * Logs an error message to results and std out.
	 * Format: "class.method():line# --- provided string"
	 * @param string - message to log with warning
	 */
	public void LOGError(String string) {
		Main.desktop.logError(string);
		if (enabled == true) {
		    String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		    int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		
		    System.out.println(className + "." + methodName + "():" + lineNumber + " --- " + string);
		}
	}
	
	/**
	 * Logs a warning message to results and std out.
	 * Format: "class.method():line# --- provided string"
	 * @param string - message to log with error
	 */
	public void LOGWarning(String string) {
		Main.desktop.logWarning(string);
		if (enabled == true) {
		    String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		    int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		
		    System.out.println(className + "." + methodName + "():" + lineNumber + " --- " + string);
		}
	}
}
