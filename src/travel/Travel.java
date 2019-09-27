package travel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import airplane.Airplanes;
import flight.Flight;

/**
 * This class holds values pertaining to a single Travel. A single travel contains no more than 3 flights.
 * Attributes are accessed via getter and setter methods.
 * 
 * @author Houjue Wang
 * @author Jishen Xu
 * @version 1.2
 * @since 2017-10-20
 * 
 * 
 *
 */
public class Travel {
	
	private int numOfFlights; //Number of flights in this travel
	private Flight[] flights; //Array of flights
	
	/**Initializing constructor for travel containing only 1 flight
	 * @param flight The only flight
	 */
	public Travel(Flight flight){
		numOfFlights = 1;
		flights = new Flight[numOfFlights];
		flights[0] = flight;
	}
	
	/**Initializing constructor for travel containing exactly 2 flights
	 * @param flight1 The first flight
	 * @param flight2 The second flight
	 */
	public Travel(Flight flight1, Flight flight2){
		numOfFlights = 2;
		flights = new Flight[numOfFlights];
		flights[0] = flight1;
		flights[1] = flight2;
	}
	
	/**Initializing constructor for travel containing exactly 3 flights
	* @param flight1 The first flight
	 * @param flight2 The second flight
	 * @param flight3 The third flight
	 */
	public Travel(Flight flight1, Flight flight2, Flight flight3){
		numOfFlights = 3;
		flights = new Flight[numOfFlights];
		flights[0] = flight1;
		flights[1] = flight2;
		flights[2] = flight3;
	}
	
	
	
	/**Get number of flights in the travel
	 * @return Number of flights in the travel
	 */
	public int getNumOfFlights() {
		return numOfFlights;
	}

	/**Get one flight in the travel
	 * @param i The index of the flight in the travel
	 * @return The flight whose index is i in the travel
	 */
	public Flight getFlight(int i){
		if(i < 0 || i >= numOfFlights) return null;
		return flights[i];
		
	}
	
	/**Get departure airport code of the travel
	 * @return Departure airport code of the travel
	 */
	public String getDepartureAirportCode(){
		return flights[0].getDepartureCode();
	}
	
	/**Get arrival airport code of the travel
	 * @return Arrival airport code of the travel
	 */
	public String getArrivalAirportCode(){
		return flights[numOfFlights - 1].getArrivalCode();
	}
	
	/**Get the first stopover's airport code in the travel
	 * @return The first stopover's airport code in the travel
	 */
	public String getFirstStopover(){
		if(numOfFlights == 1){
			return "/";
		}
		if(numOfFlights == 2 || numOfFlights == 3){
			return flights[0].getArrivalCode();
		}
		return "/";
	}
	
	/**Get the second stopover's airport code in the travel
	 * @return The second stopover's airport code in the travel
	 */
	public String getSecondStopover(){
		if(numOfFlights == 1 || numOfFlights == 2){
			return "/";
		}
		if(numOfFlights == 3){
			return flights[1].getArrivalCode();
		}
		return "/";
	}
	
	/**Get the price of the travel
	 * @param isFirstClass If the travel is booked with a first-class seat
	 * @return The price of the travel
	 */
	public int getTotalPrice(boolean isFirstClass){
		return isFirstClass ? getFirstClassPrice() : getCoachPrice();
	}
	
	/**Get the price of the travel booked with a coach seat
	 * @return The price of the travel booked with a coach seat
	 */
	public int getCoachPrice(){
		int price = 0;
		for(int i = 0;i < numOfFlights;i++){
			price += flights[i].getCoachPrice();
		}
		return price;
	}
	
	/**Get the price of the travel booked with a first-class seat
	 * @return The price of the travel booked with a first-class seat
	 */
	public int getFirstClassPrice(){
		int price = 0;
		for(int i = 0;i < numOfFlights;i++){
			price += flights[i].getFirstClassPrice();
		}
		return price;
	}
	
	/**Get departure time in Date of the travel
	 * @return Departure time in Date of the travel
	 */
	public Date getDepartureTime(){
		return flights[0].getDepartureTime();
		
	}
	
	/**Get departure time in String of the travel
	 * @return Departure time in String of the travel
	 */
	public String getDepartureTimeString(){
		return flights[0].getDepartureTimeString();
		
	}
	
	/**Get arrival time in Date of the travel
	 * @return Arrival time in Date of the travel
	 */
	public Date getArrivalTime(){
		return flights[numOfFlights - 1].getArrivalTime();
	}
	
	/**Get arrival time in String of the travel
	 * @return Arrival time in String of the travel
	 */
	public String getArrivalTimeString(){
		return flights[numOfFlights - 1].getArrivalTimeString();
	}
	
	/**Get travel time of the travel
	 * @return Travel time of the travel
	 */
	public long getTravelTime(){
		long time = flights[numOfFlights - 1].getArrivalTime().getTime() - flights[0].getDepartureTime().getTime();
		return time;
	}
	
	/**Get travel time of the travel in minutes
	 * @return Travel time of the travel in minutes
	 */
	public int getTravelTimeInMinutes(){
		return (int)(getTravelTime() / 1000 / 60);
	}
	
	/**Get array of flight numbers of the travel
	 * @return Array of flight numbers of the travel
	 */
	public String[] getFlightNumbers(){
		String[] flightNumbers = new String[numOfFlights];
		for(int i = 0;i < numOfFlights;i++){
			flightNumbers[i] = flights[i].getNumber();
		}
		return flightNumbers;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String str = "";
		for(int i = 0;i < numOfFlights;i++){
			str += "("+ i + ")" + flights[i].toString() + "\n";
		}
		return str;
	}
	
	/**Get travel information
	 * @return Travel information
	 */
	public String getTravelInformation(){
		String str = "";
		for(int i = 0;i < numOfFlights;i++){
			str += flights[i].getFlightInformation() + "\n";
		}
		return str;
	}
	
	/**Check whether the travel's coach seats are available to be booked in data server
	 * @param airplanes The list of airplanes retrieved from data server
	 * @return True if travel's coach seats are available to be booked
	 */
	public boolean isCoachSeatAvailable(Airplanes airplanes){
		for(int i = 0;i < numOfFlights;i++){
			if(!flights[i].isCoachSeatAvailable(airplanes)) return false;
		}
		return true;
	}
	
	/**Check whether the travel's first-class seats are available to be booked in data server
	 * @param airplanes The list of airplanes retrieved from data server
	 * @return True if travel's first-class seats are available to be booked
	 */
	public boolean isFirstClassSeatAvailable(Airplanes airplanes){
		for(int i = 0;i < numOfFlights;i++){
			if(!flights[i].isFirstClassSeatAvailable(airplanes)) return false;
		}
		return true;
	}
	
	/**Get number of remaining seats in one flight of the travel
	 * @param i The index of flight in the travel
	 * @param isFirstClass The seat type of the flight
	 * @param airplanes The list of airplanes retrieved from data server
	 * @return Number of remaining seats in one flight of the travel
	 */
	public int getNumOfRemainingSeats(int i,boolean isFirstClass,Airplanes airplanes){
		if(i >= numOfFlights || i < 0) return 0;
		if(isFirstClass) return flights[i].getNumOfAvailableFirstClassSeats(airplanes);
		else return flights[i].getNumOfAvailableCoachSeats(airplanes);
	}
	
	/**Add a number to the number of seats reserved in this travel
	 * @param isFirstClass is whether the travel is booked with a first-class seat or not
	 * @param i is how many is added
	 */
	public void addNumOfSeatsReserved(boolean isFirstClass, int i){
		for(Flight flight:flights){
			if(isFirstClass){
				flight.addNumOfFirstClassReserved(i);
			}else{
				flight.addNumOfCoachReserved(i);
			}
		}
	}
	
	/**Return departure time in String in local time zone
	 * @return departure time in String in local time zone
	 */
	public String getDepartureTimeInLocalTimeZone(){
		return flights[0].getDepartureTimeStringInLocalTimeZone();
	}
	
	/**Return arrival time in String in local time zone
	 * @return arrival time in String in local time zone
	 */
	public String getArrivalTimeInLocalTimeZone(){
		return flights[numOfFlights - 1].getArrivalTimeStringInLocalTimeZone();
	}
	
}
