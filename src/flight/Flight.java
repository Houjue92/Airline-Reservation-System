package flight;

import java.util.Date;

import airplane.Airplane;
import airplane.Airplanes;

/**
 * This class holds values pertaining to a single Flight. Class member attributes
 * are the same as defined by the CS509 server API and store values after conversion from
 * XML received from the server to Java primitives. Attributes are accessed via getter and 
 * setter methods.
 * 
 * @author Houjue Wang
 * @author Jishen Xu
 * @version 1.2
 * @since 2017-10-20
 * 
 * 
 *
 */
public class Flight {
	/**
	 * Flight attributes as defined by the CS509 server interface XML
	 */
	private String airplaneModel; 	// Model of airplane of this flight.
	private double flightTime;	// Duration of this flight.
	private String number;			// Flight Number.
	private String departureCode;	// Departure Airport Code
	private Date departureTime;		// Departure Time
	private String arrivalCode;		// Arrival Airport Code
	private Date arrivalTime;		// Arrival Time
	private double firstClassPrice;		//Price of first-class seat
	private int numOfFirstClassReserved;	// Number of first-class seats reserved
	private double coachPrice;			//Price of coach seat
	private int numOfCoachReserved;			// Number of coach seats reserved
	private String departureTimeString;	//Departure time in String format
	private String arrivalTimeString; //Arrival time in String format
	private String departureTimeStringInLocalTimeZone; //Departure time in String format in local time zone
	private String arrivalTimeStringInLocalTimeZone; //Arrival time in String format in local time zone
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods 
	 */	
	public Flight(){
		airplaneModel = "";
		departureCode = "";
		arrivalCode = "";
		departureTimeString = "";
		arrivalTimeString = "";
	}

	/**Set the airplaneModel
	 * @param airplaneModel The model of this flight's airplane
	 */
	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	/**Set flight time
	 * @param flightTime The duration of this flight
	 */
	public void setFlightTime(double flightTime) {
		this.flightTime = flightTime;
	}

	/**Set flight number
	 * @param number The flight number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**Set departure airport code
	 * @param departureCode The code of departure airport of this flight
	 */
	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}

	/**Set departure time
	 * @param departureTime The departure time of this flight
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	/**Set arrival airport code
	 * @param arrivalCode The code of arrival airport of this flight
	 */
	public void setArrivalCode(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	/**Set arrival time
	 * @param arrivalTime The arrival time of this flight
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**Set price of first-class seats
	 * @param firstClassPrice The price of first-class seats
	 */
	public void setFirstClassPrice(double firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	/**Set number of first-class seats reserved
	 * @param numOfFirstClassReserved The number of first-class seats reserved
	 */
	public void setNumOfFirstClassReserved(int numOfFirstClassReserved) {
		this.numOfFirstClassReserved = numOfFirstClassReserved;
	}

	/**Set price of coach seats
	 * @param coachPrice The price of coach seats
	 */
	public void setCoachPrice(double coachPrice) {
		this.coachPrice = coachPrice;
	}

	/**Set number of coach seats reserved
	 * @param numOfCoachReserved The number of coach seats reserved
	 */
	public void setNumOfCoachReserved(int numOfCoachReserved) {
		this.numOfCoachReserved = numOfCoachReserved;
	}

	/**Set departure time in String format
	 * @param departureTimeString Departure time in String format
	 */
	public void setDepartureTimeString(String departureTimeString) {
		this.departureTimeString = departureTimeString;
	}

	/**Set arrival time in String format
	 * @param arrivalTimeString Arrival time in String format
	 */
	public void setArrivalTimeString(String arrivalTimeString) {
		this.arrivalTimeString = arrivalTimeString;
	}

	/**
	 * Convert object to printable string of format
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		return "Flight [airplaneModel=" + airplaneModel + ", flightTime=" + flightTime + ", number=" + number + ", departureCode="
				+ departureCode + ", departureTime=" + departureTime + ", arrivalCode=" + arrivalCode + ", arrivalTime="
				+ arrivalTime + ", firstClassPrice=" + firstClassPrice + ", numOfFirstClassReserved="
				+ numOfFirstClassReserved + ", coachPrice=" + coachPrice + ", numOfCoachReserved=" + numOfCoachReserved
				+ ", departureTimeString=" + departureTimeString + ", arrivalTimeString=" + arrivalTimeString + "]";
	}
	
	
	/**Get flight information
	 * @return the flight information formatted as String
	 */
	public String getFlightInformation() {
		return "Flight Number=" + number + ", departureCode=" + departureCode + ", departureTime=" + departureTimeStringInLocalTimeZone
				+ ", arrivalCode=" + arrivalCode + ", arrivalTime=" + arrivalTimeStringInLocalTimeZone;
	}

	

	/**Get airplane name
	 * @return airplane name
	 */
	public String getAirplaneModel() {
		return airplaneModel;
	}

	/**Get duration of this flight
	 * @return duration of this flight
	 */
	public double getFlightTime() {
		return flightTime;
	}

	/**Get flight number
	 * @return flight number
	 */
	public String getNumber() {
		return number;
	}

	/**Get departure airport code
	 * @return departure airport code
	 */
	public String getDepartureCode() {
		return departureCode;
	}

	/**Get departure time
	 * @return departure time
	 */
	public Date getDepartureTime() {
		return departureTime;
	}

	/**Get arrival airport code
	 * @return arrival airport code
	 */
	public String getArrivalCode() {
		return arrivalCode;
	}
	
	/**Get arrival time
	 * @return arrival time
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**Get price of first-class seats
	 * @return price of first-class seats
	 */
	public double getFirstClassPrice() {
		return firstClassPrice;
	}

	/**Get number of first-class seats reserved
	 * @return number of first-class seats reserved
	 */
	public int getNumOfFirstClassReserved() {
		return numOfFirstClassReserved;
	}

	/**Get price of coach seats
	 * @return price of coach seats
	 */
	public double getCoachPrice() {
		return coachPrice;
	}

	/**Get number of coach seats reserved
	 * @return number of coach seats reserved
	 */
	public int getNumOfCoachReserved() {
		return numOfCoachReserved;
	}

	/**Get departure time in String format
	 * @return departure time in String format
	 */
	public String getDepartureTimeString() {
		return departureTimeString;
	}

	/**Get arrival time in String format
	 * @return arrival time in String format
	 */
	public String getArrivalTimeString() {
		return arrivalTimeString;
	}
	
	
	/**Check if coach seat is available in this flight
	 * @param airplanes the list of airplanes retrieved from data server
	 * @return true if coach seat is available in this flight
	 */
	public boolean isCoachSeatAvailable(Airplanes airplanes){
		int maxNumOfCoachSeats = 0;
		for(Airplane airplane : airplanes){
			if(airplaneModel.equals(airplane.getModel())){
				maxNumOfCoachSeats = airplane.getCoachSeats();
				break;
			}
			
		}
		return maxNumOfCoachSeats > numOfCoachReserved;
	}
	
	/**Check if first-class seat is available in this flight
	 * @param airplanes the list of airplanes retrieved from data server
	 * @return true if first-class seat is available in this flight
	 */
	public boolean isFirstClassSeatAvailable(Airplanes airplanes){
		int maxNumOfFirstClassSeats = 0;
		for(Airplane airplane : airplanes){
			if(airplaneModel.equals(airplane.getModel())){
				maxNumOfFirstClassSeats = airplane.getFirstClassSeats();
				break;
			}
			
		}
		return maxNumOfFirstClassSeats > numOfFirstClassReserved;
	}
	
	/**Get number of coach seats available in the flight
	 * @param airplanes the list of airplanes retrieved from data server
	 * @return number of coach seats available in the flight
	 */
	public int getNumOfAvailableCoachSeats(Airplanes airplanes){
		int maxNumOfCoachSeats = 0;
		for(Airplane airplane : airplanes){
			if(airplaneModel.equals(airplane.getModel())){
				maxNumOfCoachSeats = airplane.getCoachSeats();
				break;
			}
			
		}
		return maxNumOfCoachSeats - numOfCoachReserved;
	}
	
	/**Get number of first-class seats available in the flight
	 * @param airplanes the list of airplanes retrieved from data server
	 * @return number of first-class seats available in the flight
	 */
	public int getNumOfAvailableFirstClassSeats(Airplanes airplanes){
		int maxNumOfFirstClassSeats = 0;
		for(Airplane airplane : airplanes){
			if(airplaneModel.equals(airplane.getModel())){
				maxNumOfFirstClassSeats = airplane.getFirstClassSeats();
				break;
			}
			
		}
		return maxNumOfFirstClassSeats - numOfFirstClassReserved;
	}
	
	/**Add a number the number of first-class seats reserved
	 * @param i is how many is added
	 */
	public void addNumOfFirstClassReserved(int i){
		this.numOfFirstClassReserved += i;
	}
	
	/**Add a number the number of coach seats reserved
	 * @param i is how many is added
	 */
	public void addNumOfCoachReserved(int i){
		this.numOfCoachReserved += i;
	}

	/**
	 * @return the departureTimeStringInLocalTimeZone
	 */
	public String getDepartureTimeStringInLocalTimeZone() {
		return departureTimeStringInLocalTimeZone;
	}

	/**
	 * @param departureTimeStringInLocalTimeZone the departureTimeStringInLocalTimeZone to set
	 */
	public void setDepartureTimeStringInLocalTimeZone(String departureTimeStringInLocalTimeZone) {
		this.departureTimeStringInLocalTimeZone = departureTimeStringInLocalTimeZone;
	}

	/**
	 * @return the arrivalTimeStringInLocalTimeZone
	 */
	public String getArrivalTimeStringInLocalTimeZone() {
		return arrivalTimeStringInLocalTimeZone;
	}

	/**
	 * @param arrivalTimeStringInLocalTimeZone the arrivalTimeStringInLocalTimeZone to set
	 */
	public void setArrivalTimeStringInLocalTimeZone(String arrivalTimeStringInLocalTimeZone) {
		this.arrivalTimeStringInLocalTimeZone = arrivalTimeStringInLocalTimeZone;
	}
	
	
}
