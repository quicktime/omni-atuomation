package helper;

/**
 * Handles all time calculations
 * @author Brendan Dolan
 * @date Created on: Apr 4, 2018
 */
public class Time {
	
	/**
	 * Converts a UNIX time stamp into a double of hours, to do further calculations against
	 * @param string - UNIX time stamp
	 * @return double of hours since Jan 01 2018 00:00
	 */
	public static double UNIXTimeStamp(String string) {
		String[] split = string.split(" ");
		// Mar 07 15:25
		// [0] [1] [2]
		String[] timeSplitter = split[2].split(":");
		// 15:25
		// [0][1]
		
		int month = 0;
		int day = Integer.parseInt(split[1]); // days
		int hr = Integer.parseInt(timeSplitter[0]); // hrs
		int minute = Integer.parseInt(timeSplitter[1]); // minutes
		
		switch (split[0]) { // month
			case "Jan" :
				month = 1;
				break;
			case "Feb" :
				month = 2;
				break;
			case "Mar" :
				month = 3;
				break;
			case "Apr" :
				month = 4;
				break;
			case "May" :
				month = 5;
				break;
			case "Jun" :
				month = 6;
				break;
			case "Jul" :
				month = 7;
				break;
			case "Aug" :
				month = 8;
				break;
			case "Sep" :
				month = 9;
				break;
			case "Oct" :
				month = 10;
				break;
			case "Nov" :
				month = 11;
				break;
			case "Dec" :
				month = 12;
				break;
		}

		// calculations based on hrs
		int monthValue = month * 30 * 24;
		int dayValue = day * 24;
		int hrValue = hr;
		double minuteValue = minute / 60;
	
		double time = monthValue + dayValue + hrValue + minuteValue;
		
		
		// Mar 07  15:25							Mar 07 12:52							Apr 07 15:25
		// monthValue = 3 * 30 * 24 = 2160			monthValue = 3 * 30 * 24 = 2160			monthValue = 4 * 30 * 24 = 2880
		// dayValue = 7 * 24	 	= 168			dayValue = 7 * 24 		 = 168			dayValue = 7 * 24		 = 168
		// hrValue = 15 			= 15			hrValue = 12 			 = 12			hrValue = 15			 = 15
		// minuteValue = 25 / 60 	= .41667		minuteValue = 52 / 60 	 = .8667		minuteValue = 25 / 60 	 = .41667
		// time 					= 2343.41667	time					 = 2340.8667	time 					 = 3063.41667
		return time;
	}

}
