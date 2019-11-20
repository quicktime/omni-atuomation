package helper;

import mobile.MobileTestErrors;
import mobile.OmniErrors;
import testcontrol.Main;

import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/**
 * Class handles all string manipulations. Please put all string methods in this class.
 * Does not include declarations nor initializations.
 * @author Brendan Dolan
 * @date Created on: Mar 2, 2018
 */
public class StringManipulation {

	/**
	 * Formats the errorCodeText to match data sheets.
	 * @param input - String of errorCodeText to be formatted into errorCode
	 * @return formatted error code as string
	 */
	public static String errorCode(String input) {
		String output = input.substring(7); // cuts off "Code : "
		return output;
	}
	
	/**
	 * Formats the errorMessage to match data sheets.
	 * @param input
	 * @return
	 */
	public static String errorMessage(String input) {
		String output = input.concat(";");
		return output;
	}
	
	/**
	 * Formats the solution text to match data sheets. 
	 * @param raw solution text to format
	 * @return formatted solution text as string
	 */
	public static String solutionText(String input) {
		String noLeadingSpace = input.replaceFirst(" ", ""); // removes leading space
		String noBullets = noLeadingSpace.replaceAll(" \\n*•|\\n*•", ""); // Removes newlines and bullets.
		String output = noBullets.concat(";");
		return output;
	}
	
	/**
	 * Formats the omni text, nickname and serial number to match data sheets.
	 * @param input
	 * @return
	 */
	public static String omniText(String input) {
		String output = input.concat(";");
		return output;
	}
	
	/**
	 * Gets rid of any semi-colon at the start of the string.
	 * @param input
	 * @return
	 */
	public static String noLeadingSemi(String input) {
		String output = input.replaceFirst("^;", ""); // redundant regex, deletes first ^; (^ means beginning of string)
		return output;
	}
	
	/**
	 * Used to ignore {} characters from the data sheet. Allows for specific variables from Omni to not 
	 * affect data sheet verification.
	 * @param input
	 * @return
	 */
	public static String ignore(String input) {
		String output = input.replaceAll("\\{.*?}", "(.*)"); // check knowledgebase for more info
		return output;
	}
		
	/**
	 * Gets and formats the entire mobile errors line to match data sheets.
	 * @return entire errors line
	 */
	public static String mobileErrors(int whichError) {
		String sErrorMessage 	= MobileTestErrors.getErrorMessage(whichError);
		String sSolutionText 	= MobileTestErrors.getSolutionText(whichError);
		String sOmniText 		= MobileTestErrors.getOmniText(whichError);
		String sErrorCodeText	= MobileTestErrors.getErrorCodeText(whichError);
		
		String fErrorMessage 	= errorMessage(sErrorMessage);
		String fSolutionText 	= solutionText(sSolutionText);
		String fOmniText 		= omniText(sOmniText);
		String fErrors 			= fErrorMessage.concat(fSolutionText.concat(fOmniText.concat(sErrorCodeText)));
		return fErrors;
	}
	
	public static String omniErrors(int whichError) {
		String sErrorMessage	= OmniErrors.getErrorMessage(whichError);
		String sSolutionText	= OmniErrors.getSolutionText(whichError);
		String sOmniText		= OmniErrors.getOmniText(whichError);
		String sErrorCodeText	= OmniErrors.getErrorCodeText(whichError);
		
		String fErrorMessage	= errorMessage(sErrorMessage);
		String fSolutionText	= solutionText(sSolutionText);
		String fOmniText		= omniText(sOmniText);
		
		String fErrors			= fErrorMessage.concat(fSolutionText.concat(fOmniText.concat(sErrorCodeText)));
		return fErrors;
	}
	
	/**
	 * Formats provided sample ID's errors from {@link postman.PostmanGet#getTestErrors}
	 * Reason for splitting it up into different substrings, and not just put both indexes is that with multiple tests, the substring end index would always get the first
	 * instance, instead of the last instance, sometimes resulting in empty substrings
	 * @param sampleExternalID sampleID provided by caller of {@link postman.PostmanGet#getTestErrors}
	 * @param sResponse reponse from postman get
	 * @return postman errors as string
	 */
	public static String postmanTestErrors(String sampleExternalID, String sResponse) {
		String test = sResponse.substring(sResponse.indexOf("\"sampleExternalId\" : \"" + sampleExternalID + "\",")); // gets substring from "sampleExternalID... -> end of postmanGet
		String errors = test.substring(test.indexOf("\"errors\" : [")); // gets substring from "errors" : [ -> end of postmanGet
		String errorLimit = errors.substring(0, errors.indexOf("]") + 1); // gets substring from errors -> ]
		return errorLimit; // returns just the errors of the provided sampleID
	}
	
	/**
	 * Formats provided module errors from {@link postman.PostmanGet#getTestErrors}
	 * @param sResponse reponse from postman get
	 * @return postman errors as string
	 */
	public static String postmanModuleErrors(String sResponse) {
		String errors = sResponse.substring(sResponse.indexOf("\"errors\" : ["));
		String errorLimit = errors.substring(0, errors.indexOf("]") + 1);
		return errorLimit;
	}
	
	/**
	 * Formats postman error type param from error data
	 * @param sampleExternalID sampleID provided by caller of {@link helper.StringManipulation#postmanFullTestErrorCode}
	 * @param sResponse response from postman get
	 * @return error type as string
	 */
	public static String postmanTestErrorType(String sampleExternalID, String sResponse) {
		String errors = postmanTestErrors(sampleExternalID, sResponse); // sets errors as return of postmanTestErrors
		
		String errorTypeMessage = errors.substring(errors.indexOf("\"errorType\" : "), errors.indexOf("\"errorType\" : ") + 16); // gets "errorType" : 1,
		String errorType = errorTypeMessage.substring(14, errorTypeMessage.indexOf(",")); // gets 1
		return errorType;
	}
	
	/**
	 * Formats postman error type param from module
	 * @param sResponse response from postman get
	 * @return error type as string
	 */
	public static String postmanModuleErrorType(String sResponse) {
		String errors = postmanModuleErrors(sResponse);
		
		String errorTypeMessage = errors.substring(errors.indexOf("\"errorType\" : "), errors.indexOf("\"errorType\" : ") + 16);
		String errorType = errorTypeMessage.substring(14, errorTypeMessage.indexOf(","));
		return errorType;
	}
	
	/**
	 * Formats postman error code param from error data
	 * @param sampleExternalID sampleID provided by caller of {@link helper.StringManipulation#postmanFullErrorCode}
	 * @param sResponse response from postman get
	 * @return error code as string
	 */
	public static String postmanTestErrorCode(String sampleExternalID, String sResponse) {
		String errorCode = null;
		String errors = postmanTestErrors(sampleExternalID, sResponse);
		
		String errorCodeMessage = errors.substring(errors.indexOf("\"errorCode\" : "), errors.indexOf("\"errorCode\" : ") + 16); // gets "errorCode" : 6,
		String sErrorCode = errorCodeMessage.substring(14, errorCodeMessage.indexOf(",")); // gets 6
		int iErrorCode = Integer.parseInt(sErrorCode); // turns string "6" into int 6
		if (iErrorCode < 10) { // int is single digits
			errorCode = "0".concat(sErrorCode); // makes string 0X instead of X 
		} else { // int is double digits already
			errorCode = sErrorCode;
		}
		return errorCode;
	}
	
	/**
	 * Formats postman error code param from module
	 * @param sResponse response from postman get
	 * @return error code as string
	 */
	public static String postmanModuleErrorCode(String sResponse) {
		String errorCode = null;
		String errors = postmanModuleErrors(sResponse);
		
		String errorCodeMessage = errors.substring(errors.indexOf("\"errorCode\" : "), errors.indexOf("\"errorCode\" : ") + 16); // gets "errorCode" : 6,
		String sErrorCode = errorCodeMessage.substring(14, errorCodeMessage.indexOf(",")); // gets 6
		int iErrorCode = Integer.parseInt(sErrorCode); // turns string "6" into int 6
		if (iErrorCode < 10) { // int is single digits
			errorCode = "0".concat(sErrorCode); // makes string 0X instead of X (double digits) 
		} else { // int is already double digits
			errorCode = sErrorCode;
		}
		return errorCode;
	}

	/**
	 * Formats full error code from error data to match mobile device error code
	 * {
	 * 	ErrorCode : 6
	 * 	ErrorType : 1
	 * }
	 * @param sampleExternalID sampleID provided by caller
	 * @param sResponse response from postman get
	 * @return full error code as string
	 */
	public static String postmanFullTestErrorCode(String sampleExternalID, String sResponse) {
		String fullErrorCode = null;
		String errorType = postmanTestErrorType(sampleExternalID, sResponse); // sets errorType as return of postmanTestErrorType
		String errorCode = postmanTestErrorCode(sampleExternalID, sResponse); // sets errorCode as return of postmanTestErrorCode
		
		fullErrorCode = errorType.concat("0").concat(errorCode); // errorType+0+errorCode
		return fullErrorCode;
	}
	
	/**
	 * Formats full error code from module to match data sheet
	 * @param sResponse response from postman get
	 * @return full error code as string
	 */
	public static String postmanFullModuleErrorCode(String sResponse) {
		String fullErrorCode = new String();
		String errorType = postmanModuleErrorType(sResponse);
		String errorCode = postmanModuleErrorCode(sResponse);
		
		fullErrorCode = errorType.concat("0").concat(errorCode);
		return fullErrorCode;
	}
	
	/**
	 * Gets the last run test GUID from the ls -tr command
	 * @param log ArrayList containing elements (rows) from {@link sheet.PuttyLog#log()}
	 * @param rowFromBottom - How many elements up from the bottom
	 * @return string of test GUID
	 */
	public static String puttyTestGUID(ArrayList<String> log, int rowFromBottom) {
		String lastTest = log.get(log.size() - rowFromBottom);
		Main.debug.LOG(lastTest);
		String[] lastTestArray = lastTest.split("_");
		String GUID = lastTestArray[1]; // [0] is assayName [1] is GUID [2] is timestamp.extension
		return GUID;
	}
	
	/**
	 * Gets the last run test timestamp from the ls -tr command
	 * @param lsTR - ArrayList containing elements (rows) from {@link sheet.PuttyLog#log()}
	 * @param rowFromBottom - How many elements up from the bottom
	 * @return string of timestamp
	 */
	public static String puttyTestTimeStamp(ArrayList<String> lsTR, int rowFromBottom) {
		String lastTest = lsTR.get(lsTR.size() - rowFromBottom);
		String[] lastTestArray = lastTest.split("_", 3);
		String timeStampExtension = lastTestArray[2]; // [0] is assayName [1] is GUID [2] is timestamp.extension
		Main.debug.LOG(timeStampExtension);
		String[] extension = timeStampExtension.split(".(...)");
		String timeStamp = extension[0]; // [0] is timestamp [1] is extension
		Main.debug.LOG(timeStamp);
		return timeStamp;
	}
	
	/**
	 * Gets all the time file timestamps from the ls command 
	 * @param ls - ArrayList<String> containing elements (rows) from {@link sheet.PuttyLog#log()}
	 * @return ArrayList<String> of timestamps
	 */
	public static ArrayList<String> puttyGetFileTimeStamps(ArrayList<String> ls) {
		//-rw-r--r-- 1   root root 86016 Apr 10  14:17 epsilon.bak
		//[0]        [1] [2]  [3]  [4]   [5] [6] [7]   [8]
		ArrayList<String> timeStamps = new ArrayList<String>();
		for (int i = 0; i < ls.size(); i++) {
			String row = ls.get(i); // gets 1 row from the output
			String[] split = row.split(" +"); // split at space chars (or multiple space chars in a row)
			Main.debug.LOG(row);
			String month = split[5]; // sixth split is the month
			String day = split[6]; // seventh split is the day
			String time = split[7]; // eighth split is the time
			if (Integer.parseInt(day) < 10) { // if day is single digits
				day = "0".concat(day); // add a '0' in front
			}
			String concat = month + " " + day + " " + time;
			timeStamps.add(concat);
		}
		return timeStamps;
	}

	/**
	 * Gets all the self test data from postman
	 * @param sResponse response from postman get
	 * @return full self test data as string
	 */
	public static String postSelfTestData(String sResponse) {
		String selfTest = sResponse.substring(sResponse.indexOf("\"postSelfTestData\" : {"));
		String selfTestLimit = selfTest.substring(0, selfTest.indexOf("}") + 1);
		return selfTestLimit;
	}

	/**
	 * Gets the instrument state from postman
	 * @param sResponse response from postman get
	 * @return instrument state as string
	 */
	public static String postmanInstrumentState(String sResponse) {
		String state = sResponse.substring(sResponse.indexOf("\"state\" : "));
		String stateLimit = state.substring(state.indexOf(","));
		return stateLimit;
	}

	/**
	 * Gets the door status from postman
	 * @param sResponse response from postman get
	 * @return door status as string
	 */
	public static String postmanDoorStatus(String sResponse) {
		String doorStatus = sResponse.substring(sResponse.indexOf("\"doorStatus\" : "));
		String doorStatusLimit = doorStatus.substring(doorStatus.indexOf(","));
		return doorStatusLimit;
	}

	/**
	 * Gets the cartridge status from postman
	 * @param sResponse - response from postman get
	 * @return cartridge status as string
	 */
	public static String postmanCartStatus(String sResponse) {
		String cartStatus = sResponse.substring(sResponse.indexOf("\"cartridgeStatus\": "));
		String cartStatusLimit = cartStatus.substring(cartStatus.indexOf(","));
		return cartStatusLimit;
	}
}
