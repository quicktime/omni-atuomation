package sheet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import helper.DEBUG;
import helper.StringManipulation;
import testcontrol.Main;

/**
 * Handles sheet errors
 * Sheets must be in format sendCode;errorCode;errorMessage;solutionText;omniText;errorCodeText
 * @author Brendan Dolan
 * @date Created on: Feb 21, 2018
 */
public class SheetTestErrors {
	
	/**
	 * Gets the error code to send to Postman or InitError.txt
	 * @param sheet - data sheet
	 * @param line - line number
	 * @return error code to send to Postman or InitError.txt
	 */
	public static String getErrorSendCode(String sheet, int line) {
		String sendCode = new String();
		try {
			BufferedReader error = new BufferedReader(new FileReader(sheet)); // creates BufferedReader of passed-in sheet
			error.readLine(); // skip headers
			for (int i = 0; i < line; i++) {
				error.readLine(); // skips # of lines based on what line is passed-in
			}
			String fullLine = error.readLine(); // reads the full row
			Scanner errorLine = new Scanner(fullLine); // surround string with Scanner, giving ability for useDelimiter
			errorLine.useDelimiter(";"); // sets delimiter to ;
			sendCode = errorLine.next(); // gets the next segment between delimiters (in this case it's always the first segment in the row)
			error.close(); // always close BufferedReaders
			errorLine.close(); // always close Scanners
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		return sendCode.toString(); // returns the send code (first segment) as a string
	}

	/**
	 * Gets error message from passed in data sheet
	 * @param sheet - data sheet
	 * @param line - line number
	 * @return error message
	 * @throws IOException 
	 */
	public static String getErrorMessage(String sheet, int line) {
		String message = new String();
		try { // to catch IOExceptions
			BufferedReader error = new BufferedReader(new FileReader(sheet)); // creates BufferedReader of passed-in sheet
			error.readLine(); // skip header
			for (int i = 0; i < line; i++) {
				error.readLine(); // skips # of lines based on what line is passed-in
			}
			String fullLine = error.readLine(); // reads the full row
			Scanner errorLine = new Scanner(fullLine); // surround String with Scanner to useDelimiter
			errorLine.useDelimiter(";"); // sets delimiter to ;
			String skipSendCode = errorLine.next(); // gets the next segment between delimiters (in this case the first segment in the row aka sendCode)
			message = errorLine.next(); // gets the next segment (in this case the 2nd segment)
			error.close(); // always close BufferedReaders
			errorLine.close(); // always close Scanners
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		return message.toString(); // returns the error message (2nd segment) as a string
	}
	
	/**
	 * Gets error solution text from passed in data sheet
	 * @param sheet - data sheet
	 * @param line - line number
	 * @return error solution text
	 * @throws IOException 
	 */
	public static String getErrorSolutionText(String sheet, int line) throws IOException {
		BufferedReader error = new BufferedReader(new FileReader(sheet)); // creates BufferedReader of passed-in sheet
		error.readLine(); // skip header
		for (int i = 0; i < line; i++) {
			error.readLine(); // skips # of lines based on what line is passed-in
		}
		String fullLine = error.readLine(); // reads the full row
		Scanner errorLine = new Scanner(fullLine); // surround String with Scanner to use delimiters
		errorLine.useDelimiter(";"); // sets delimiter to ;
		String skipSendCode = errorLine.next(); // gets the next segment (in this case 1st segment, sendCode)
		String skipMessage = errorLine.next(); // gets the next segment (in this case 2nd segment, errorMessage)
		String errorSolutionText = errorLine.next(); // gets the next segment (in this case 3rd segment, errorSolutionText)
		error.close(); // always close BufferedReaders
		errorLine.close(); // always close Scanners
		return errorSolutionText.toString(); // returns the error solution text (3rd segment) as a string
	}
	
	/**
	 * Gets omni text from passed in data sheet
	 * @param sheet - data sheet
	 * @param line - line number
	 * @return error omni text
	 * @throws IOException
	 */
	public static String getErrorOmniText(String sheet, int line) throws IOException {
		BufferedReader error = new BufferedReader(new FileReader(sheet)); // creates BufferedReader of passed-in sheet
		error.readLine(); // skip header
		for (int i = 0; i < line; i++) {
			error.readLine(); // skips # of lines based on what line is passed-in
		}
		String fullLine = error.readLine(); // reads the full row
		Scanner errorLine = new Scanner(fullLine); // surround String with Scanner to use delimiters
		errorLine.useDelimiter(";"); // sets delimiter to ;
		String skipSendCode = errorLine.next(); // gets the next segment (1st segment, sendCode)
		String skipMessage = errorLine.next(); // gets the next segment (2nd segment, errorMessage)
		String skipSolution = errorLine.next(); // gets the next segment (3rd segment, errorSolutionText)
		String omniText = errorLine.next(); // gets the next segment (4th segment, errorOmniText)
		error.close(); // always close BufferedReaders
		errorLine.close(); // always close Scanners
		return omniText.toString(); // return the error omni text (4th segment) as a string
	}
	
	/**
	 * Gets error code text from passed in data sheet
	 * @param sheet - data sheet
	 * @param line - line number
	 * @return error code text
	 * @throws IOException
	 */
	public static String getErrorCodeText(String sheet, int line) throws IOException {
		BufferedReader error = new BufferedReader(new FileReader(sheet)); // creates BufferedRead of passed-in sheet
		error.readLine(); // skip header
		for (int i = 0; i < line; i++) {
			error.readLine(); // skips # of lines based on passed-in line number
		}
		String fullLine = error.readLine(); // reads the full row
		Scanner errorLine = new Scanner(fullLine); // surround String with Scanner to use delimiters
		errorLine.useDelimiter(";"); // sets delimiter to ;
		String skipSendCode = errorLine.next(); // gets the next segment (1st segment, sendCode)
		String skipMessage = errorLine.next(); // gets the next segment (2nd segment, errorMessage)
		String skipSolution = errorLine.next(); // gets the next segment (3rd segment, errorSolutionText)
		String omniText = errorLine.next(); // gets the next segment (4th segment, errorOmniText)
		String errorCodeText = errorLine.next(); // gets the next segment (5th segment, errorCodeText)
		error.close(); // always close BufferedReaders
		errorLine.close(); // always close Scanners
		return errorCodeText.toString(); // return the error code text (5th segment) as a string
	}
	
	/**
	 * Gets error code from {@link sheet.SheetTestErrors#getErrorCodeText(String, int)}
	 * @param sheet - data sheet
	 * @param line - line number
	 * @return error code
	 */
	public static String getErrorCode(String sheet, int line) {
		String input = null; // declares string to use later
		try { // surround with try to catch IOException from getErrorCodeText()
			input = getErrorCodeText(sheet, line); // input gets set to return from getErrorCodeText(String, int)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String code = StringManipulation.errorCode(input); // format input to code by calling StringManipulation.errorCode(String)
		return code; // return formatted error code as string
	}
	
	/**
	 * Gets full error message from passed in data sheet
	 * @param sheet - data sheet
	 * @param line - line number
	 * @return full error message
	 * @throws IOException
	 */
	public static String getErrors(String sheet, int line) {
		String errors = new String();
		try {
			BufferedReader error = new BufferedReader(new FileReader(sheet)); // creates BufferedReader  of passed-in sheet
			error.readLine(); // skip header
			for (int i = 0; i < line; i++) {
				error.readLine(); // skips # of lines based passed-in line number
			}
			String fullLine = error.readLine(); // reads the full row
			Scanner errorLine = new Scanner(fullLine); // surround String with Scanner to use delimiters
			errorLine.useDelimiter(";"); // sets delimiter to ;
			String skipSendCode = errorLine.next(); // gets the next segment (1st segment, sendCode)
			errorLine.useDelimiter("~~~~!!~~~~"); // very uncommon sequence, making .next() grab the rest of the line
			String input = errorLine.next(); // gets the next segment (2nd to last, 5th)
			errors = StringManipulation.noLeadingSemi(input); // format input properly by calling StringManipulation.noLeadingSemi(String)
			error.close(); // always close BufferedReaders
			errorLine.close(); // always close Scanners
		} catch (IOException e) {
			Main.debug.LOGWarning(e.toString());
		}
		return errors; // return segments 2 through last, 5th as single String
	}
}
