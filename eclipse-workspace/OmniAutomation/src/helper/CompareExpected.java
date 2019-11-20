package helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import testcontrol.Main;

/**
 * Handles comparisons between expected results and actual results
 * @author Brendan Dolan
 * @date Created on: Mar 29, 2018
 */
public class CompareExpected {

	/**
	 * Comapres two ints
	 * @param actual - int gathered from Omni
	 * @param expected - int gathered from sheets or known values
	 */
	public static void compare(int actual, int expected) {
		if (actual == expected) { // ints are the same
			Main.debug.LOG(actual + " is the same as " + expected);
		} else { // ints are different
			Main.debug.LOGError(actual + " is not the same as " + expected);
		}
	}
	
	/**
	 * Compares two strings
	 * @param actual - string gathered from omni
	 * @param expected - string gathered from sheets or known values
	 */
	public static void compare(String actual, String expected) {
		int compared = actual.compareTo(expected);
		if (compared == 0) { // strings are the same
			Main.debug.LOG(actual + " is the same as " + expected);
		} else { // strings are different
			Main.debug.LOGError(actual + " is not the same as " + expected);
		}
	}
	
	/**
	 * Compares two string, expecting a difference
	 * @param actual - string gathered from omni / app
	 * @param expected - string of known value
	 */
	public static void different(String actual, String expected) {
		int compared = actual.compareTo(expected);
		if (compared != 0) {
			Main.debug.LOG(actual + " is not the same as " + expected);
		} else {
			Main.debug.LOGError(actual + " is the same as " + expected);
		}
	}
	
	/**
	 * Compares two ints, expecting a difference
	 * @param actual - string gathered from omni / app
	 * @param expected - string of known value
	 */
	public static void different(int actual, int expected) {
		if (actual != expected) {
			Main.debug.LOG(actual + " is not the same as " + expected);
		} else {
			Main.debug.LOGError(actual + " is the same as " + expected);
		}
	}
}
