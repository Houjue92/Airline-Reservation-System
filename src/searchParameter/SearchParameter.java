package searchParameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import airport.Airports;
import time.TimeConversion;

/**This class holds values the customer inputs before searching flights. Class attributes
 * are the important information of the flights the customer wants. This class also checks
 * whether the customer's input is correct or not.
 * @author Houjue Wang
 * @author Jishen Xu
 *
 */
public class SearchParameter {
	private boolean isRoundTrip; // Flag indicating whether the customer wants a round-trip travel 
	private String departureAirportCode; // Code of departure airport
	private String departureDateStringLocal;  // Departure date in String format in local time zone
	private String arrivalAirportCode;	// Code of arrival airport
	private String returnDateStringLocal; //Return date (if it's a round trip) in String format in local time zone
	private boolean isFirstClass;	//Flag indicating whether the customer chooses first-class seat
	
	private static final int DEPARTURE = 0;	// Some flags used by sub routines
	private static final int ARRIVAL = 1;
	private static final int RETURN = 2;
	
	public SearchParameter(){
		
	}
	
	/**Initializing constructor.
	 * 
	 * All attributes are initialized with input values.
	 * @param isRoundTrip Flag indicating whether the customer wants a round-trip travel
	 * @param departureAirportCode Code of departure airport
	 * @param departureDateString Departure date in String format
	 * @param arrivalAirportCode Code of arrival airport
	 * @param returnDateString Return date (if it's a round trip) in String format
	 * @param isFirstClass Flag indicating whether the customer chooses first-class seat
	 */
	public SearchParameter(boolean isRoundTrip, String departureAirportCode, String departureDateStringLocal,
			String arrivalAirportCode, String returnDateStringLocal, boolean isFirstClass) {
		this.isRoundTrip = isRoundTrip;
		this.departureAirportCode = departureAirportCode;
		this.departureDateStringLocal = departureDateStringLocal;
		this.arrivalAirportCode = arrivalAirportCode;
		this.returnDateStringLocal = returnDateStringLocal;
		this.isFirstClass = isFirstClass;
	}

	/**Check if the departure airport code is valid.
	 * If so, it can be figured out in the list of airports retrieved from the server
	 * @param airports List of airports retrieved from the server
	 * @return True if the departure airport code is valid, false if not
	 */
	public boolean isDepartureAirportCodeValid(Airports airports){
		return isAirportCodeValid(DEPARTURE,airports);
	}
	
	/**Check if the arrival airport code is valid.
	 * If so, it can be figured out in the list of airports retrieved from the server
	 * @param airports List of airports retrieved from the server
	 * @return True if the arrival airport code is valid, false if not
	 */
	public boolean isArrivingAirportCodeValid(Airports airports){
		return isAirportCodeValid(ARRIVAL,airports);
	}
	
	/** A sub routine used by "isDepartureAirportCodeValid()" and "isArrivingAirportCodeValid()"
	 * @param flag To indicate which airport code (the departure one or arrival one) needs to be checked
	 * @param airports List of airports retrieved from the server
	 * @return True if the target airport code is valid, false if not
	 */
	private boolean isAirportCodeValid(int flag, Airports airports){
		String airportCode = null;
		if(flag == DEPARTURE) airportCode = departureAirportCode;
		else if(flag == ARRIVAL) airportCode = arrivalAirportCode;
		for(int i = 0;i < airports.size();i++){
			if(airports.get(i).code().equals(airportCode)){
				return true;
			}
		}
		return false;
	}
	
	/**Check if the departure airport and arrival airport code are both valid.
	 * They must not be the same and can be figured out in the list of airports.
	 * @param airports List of airports retrieved from the server
	 * @return True if both airport codes are valid, false if not
	 */
	public boolean isAirportCodesValid(Airports airports){
		if(departureAirportCode == null) return false;
		if(arrivalAirportCode == null) return false;
		if(departureAirportCode.equals(arrivalAirportCode)) return false;
		
		boolean flag1 = false;
		boolean flag2 = false;
		for(int i = 0;i < airports.size();i++){
			if(airports.get(i).code().equals(departureAirportCode)){
				flag1 = true;
			}
			if(airports.get(i).code().equals(arrivalAirportCode)){
				flag2 = true;
			}
			if(flag1 && flag2) break;	
		}
	
		return flag1 && flag2;
	}
	
	/**Check if the departure date is valid. It must be in a correct date format.
	 * @return True if the departure date is valid, false if not
	 */
	public boolean isDepartureDateValid(){
		return isTravelDateValid(DEPARTURE);
	}
	
	/**Check if the return date is valid (in a round trip). It must be in a correct date format.
	 * @return True if the return date is valid, false if not
	 */
	public boolean isReturnDateValid(){
		return isTravelDateValid(RETURN);
	}
	
	/**A sub routine used by "isDepartureDateValid" and "isReturnDateValid".
	 * @param flag To indicate which date (the departure one or the return one) needs to be checked
	 * @return True if the target date is valid, false if not
	 */
	private boolean isTravelDateValid(int flag){
		String dateString = null;
		if(flag == DEPARTURE) dateString = departureDateStringLocal;
		else if(flag == RETURN) dateString = returnDateStringLocal;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		sdf.setLenient(false);
		
		try {
			Date date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**Check if both departure date and return date are valid. 
	 * They must be in a correct date format. Also the departure date must be ahead of the return date.
	 * @return True if both departure date and return date are valid, false if not
	 */
	public boolean isTravelDatesValid(){
		if(departureDateStringLocal == null) return false;
		if(isRoundTrip && returnDateStringLocal == null) return false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		sdf.setLenient(false);
		Date departureDate = null;
		Date returnDate = null;
		
		try {
			departureDate = sdf.parse(departureDateStringLocal);
			if(isRoundTrip){
				returnDate = sdf.parse(returnDateStringLocal);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
		if(isRoundTrip && departureDate.after(returnDate)) return false;
		return true;
	}
	
	/**
	 * @return the isRoundTrip
	 */
	public boolean isRoundTrip() {
		return isRoundTrip;
	}

	/**
	 * @return the departureAirportCode
	 */
	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	/**
	 * @return the departureDateStringLocal
	 */
	public String getDepartureDateStringLocal() {
		return departureDateStringLocal;
	}

	/**
	 * @return the arrivalAirportCode
	 */
	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}

	/**
	 * @return the returnDateStringLocal
	 */
	public String getReturnDateStringLocal() {
		return returnDateStringLocal;
	}

	/**
	 * @return the isFirstClass
	 */
	public boolean isFirstClass() {
		return isFirstClass;
	}

	/**Return the departure date in String and time zone of GMT
	 * @return the departure date in String and time zone of GMT
	 */
	public String getDepartureDateStringGMT(){
		return TimeConversion.convertLocalToGMT(departureDateStringLocal);
	}
	
	/**Return the return date in String and time zone of GMT
	 * @return the departure date in String and time zone of GMT
	 */
	public String getReturnDateStringGMT(){
		return TimeConversion.convertLocalToGMT(returnDateStringLocal);
	}
}
