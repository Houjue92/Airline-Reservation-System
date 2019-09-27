package time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import airport.Airport;
import airport.Airports;
import time.TimeZoneMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
/**This class provides a routine to transform a date in String to a date in Date-class.
 * @author Jishen Xu
 * @author Sachin Suvarna
 */
public class TimeConversion {

	/**Transform a date(in GMT time zone) in String to a date in Date-class.
	 * @param dateString The date in String
	 * @return The date in Date-class
	 */
	public static Date parseDateInGMT(String dateString){
		
		String[] dateStrings = dateString.split(" ");
		String newDateString = dateStrings[0] + "-" + convertMonth(dateStrings[1]) + "-" + dateStrings[2] + " " + dateStrings[3];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));  
		Date date;
		try {
			date = sdf.parse(newDateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
		
	}
	
	/**Transform a date in String to a local date of a specified zone.
	 * @param dateString The date in String
	 * @param airportCode The airport code in String format
	 * @param airportList List of airports with their latitudes and longitudes
	 * @return The date specific to the time zone
	 */
	public static String convertGMTToLocal(String dateString, String airportCode, Airports airportList) {
		double latitude = 0; double longitude = 0;
		for (Airport airport : airportList) {
			if(airportCode.equals(airport.code())) {
				latitude = airport.latitude();
				longitude = airport.longitude();
				break;
			}
		}
		String[] dateStrings = dateString.split(" ");
		String newDateString = dateStrings[0] + "-" + convertMonth(dateStrings[1]) + "-" + dateStrings[2] + " " + dateStrings[3];
		TimeZoneMapper timeZoneMapper = new TimeZoneMapper();
		String timeZone = timeZoneMapper.tzNameAt(latitude, longitude);
	    Date date;
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	    LocalDateTime ldt = LocalDateTime.parse(newDateString, DATE_FORMAT);
        ZonedDateTime timeGMT = ldt.atZone(ZoneId.of("GMT"));
        ZoneId currentZoneId = ZoneId.of(timeZone);
        ZonedDateTime localDateTime = timeGMT.withZoneSameInstant(currentZoneId);
        String localTime = localDateTime.format(DATE_FORMAT);
        return localTime + "(" + currentZoneId.toString() + ")";
	}
	
	/**Transform a month's abbreviation to a numeric value.
	 * @param month The month's abbreviation
	 * @return The month in numeric value
	 */
	private static String convertMonth(String month){
		if(month.equals("Jan")) return "01";
		if(month.equals("Feb")) return "02";
		if(month.equals("Mar")) return "03";
		if(month.equals("Apr")) return "04";
		if(month.equals("May")) return "05";
		if(month.equals("June")) return "06";
		if(month.equals("July")) return "07";
		if(month.equals("Aug")) return "08";
		if(month.equals("Sept")) return "09";
		if(month.equals("Oct")) return "10";
		if(month.equals("Nov")) return "11";
		if(month.equals("Dec")) return "12";
		return null;
	}
	
	/**Convert a dateString in local time zone to a dateString in GMT time zone
	 * @param dateString in local time zone
	 * @return a dateString in GMT time zone
	 */
	public static String convertLocalToGMT(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		sdf.setLenient(false);
		Date date = new Date();
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(date);
	}
}
