package searchTravel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import airplane.Airplanes;
import airport.Airports;
import dao.ServerInterface;
import flight.Flight;
import flight.Flights;
import searchParameter.SearchParameter;
import travel.Travel;
import travel.Travels;
import utils.Saps;
import utils.TimeIgnoringComparator;

/**This class provides methods to search travels according to customer's requirement.
 * @author Houjue Wang
 * @author Jishen Xu
 * @author Sachin Suvarna
 */
public class SearchTravel {
	private static final long THIRTY_MINUTES_MILLISECONDS = 1000 * 60 * 30;
	private static final long FOUR_HOURS_MILLISECONDS = 1000 * 60 * 60 * 4;
	private static final int FIRST_TRAVEL = 0;
	private static final int SECOND_TRAVEL = 1;
	
	private SearchParameter searchParameter;
	private Airplanes airplanes;
	private Airports airports;
	/**Initializing constructor.
	 * @param searchParameter The information the customer inputs before searching
	 * @param airplanes The list of airplanes retrieved from data server.
	 */
	public SearchTravel(SearchParameter searchParameter,Airplanes airplanes, Airports airports) {
		this.searchParameter = searchParameter;
		this.airplanes = airplanes;
		this.airports = airports;
	}
	
	/**Get the departure travels meeting customer's requirement
	 * @return the departure travels meeting customer's requirement
	 */
	public Travels getFistTravels(){
		return getTravels(FIRST_TRAVEL);
	}
	
	/**Get the return travels meeting customer's requirement if the customer chooses a round-trip
	 * @return the return travels meeting customer's requirement
	 */
	public Travels getSecondTravels(){
		if(searchParameter.isRoundTrip()) return getTravels(SECOND_TRAVEL);
		else return new Travels();
	}
	
	/**Get travels meeting customer's requirement
	 * @param flag Determine whether departure travels or return travels are searched
	 * @return travels meeting customer's requirement
	 */
	private Travels getTravels(int flag){
		Travels travels = new Travels();
		String departureAirportCode = null;
		String arrivalAirportCode = null;
		String departureDateString = null;
		String nextDayofDepartureDateString = null;
		if(flag == FIRST_TRAVEL){
			departureAirportCode = searchParameter.getDepartureAirportCode();
			arrivalAirportCode = searchParameter.getArrivalAirportCode();
			departureDateString = searchParameter.getDepartureDateStringGMT();
			nextDayofDepartureDateString = nextDayofDate(departureDateString);
		}else if(flag == SECOND_TRAVEL){
			departureAirportCode = searchParameter.getArrivalAirportCode();
			arrivalAirportCode = searchParameter.getDepartureAirportCode();
			departureDateString = searchParameter.getReturnDateStringGMT();
			nextDayofDepartureDateString = nextDayofDate(departureDateString);
		}
		
		Date departureDate = null;
		//Date returnDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd"); 
		try {
			if(flag == FIRST_TRAVEL){
				departureDate = formatter.parse(searchParameter.getDepartureDateStringLocal());
			}else{
				departureDate = formatter.parse(searchParameter.getReturnDateStringLocal());
			}
		} catch (ParseException e) {
			System.out.println("Single way Journey");
		} 
		
		
		TimeIgnoringComparator dateComparator = new TimeIgnoringComparator();
		//One-flight travel; Code Mod here....added airports to arguments
		Flights departingFlights1 = ServerInterface.INSTANCE.getDepartingFlights(Saps.TEAM_NAME, departureAirportCode, departureDateString, airports);
		Flights departingFlights2 = ServerInterface.INSTANCE.getDepartingFlights(Saps.TEAM_NAME, departureAirportCode, nextDayofDepartureDateString, airports);
		Flights departingFlights = new Flights();
		
		for(Flight flight: departingFlights1){
			if(dateComparator.compare(flight.getDepartureTime(), departureDate) == 0){
				departingFlights.add(flight);
			}
		}
		
		for(Flight flight: departingFlights2){
			if(dateComparator.compare(flight.getDepartureTime(), departureDate) == 0){
				departingFlights.add(flight);
			}
		}
		
		for(Flight departingFlight : departingFlights){
			if(departingFlight.getArrivalCode().equals(arrivalAirportCode)){
				travels.add(new Travel(departingFlight));
			}else{
				Flights middleFlights1 = ServerInterface.INSTANCE.getDepartingFlights(Saps.TEAM_NAME, departingFlight.getArrivalCode(), convertDate(departingFlight.getArrivalTime()),airports);
				Flights middleFlights2 = ServerInterface.INSTANCE.getDepartingFlights(Saps.TEAM_NAME, departingFlight.getArrivalCode(), nextDayofDate(convertDate(departingFlight.getArrivalTime())),airports);
				Flights middleFlights = new Flights();
				middleFlights.addAll(middleFlights1);
				middleFlights.addAll(middleFlights2);

				for(Flight middleFlight:middleFlights){
					if(middleFlight.getArrivalCode().equals(arrivalAirportCode)){
						if(isTimeIntervalOK(departingFlight.getArrivalTime(),middleFlight.getDepartureTime())){
							travels.add(new Travel(departingFlight,middleFlight));
						}
					}else if(!middleFlight.getArrivalCode().equals(departingFlight.getDepartureCode())){
						if(isTimeIntervalOK(departingFlight.getArrivalTime(),middleFlight.getDepartureTime())){
							Flights arrivingFlights1 = ServerInterface.INSTANCE.getDepartingFlights(Saps.TEAM_NAME, middleFlight.getArrivalCode(), convertDate(middleFlight.getArrivalTime()),airports);
							Flights arrivingFlights2 = ServerInterface.INSTANCE.getDepartingFlights(Saps.TEAM_NAME, middleFlight.getArrivalCode(), nextDayofDate(convertDate(middleFlight.getArrivalTime())),airports);
							Flights arrivingFlights = new Flights();
							arrivingFlights.addAll(arrivingFlights1);
							arrivingFlights.addAll(arrivingFlights2);
							for(Flight arrivingFlight:arrivingFlights){
								if(arrivingFlight.getArrivalCode().equals(arrivalAirportCode)){
									if(isTimeIntervalOK(middleFlight.getArrivalTime(),arrivingFlight.getDepartureTime())){
										travels.add(new Travel(departingFlight,middleFlight,arrivingFlight));
									}
								}
								
								
							}
						}
						
					}
				}
				
				
				
			}
		}
		
		Travels newTravels = new Travels();
		for(Travel travel:travels){
			if(searchParameter.isFirstClass()){
				if(travel.isFirstClassSeatAvailable(airplanes)){
					newTravels.add(travel);
				}
			}else{
				if(travel.isCoachSeatAvailable(airplanes)){
					newTravels.add(travel);
				}
			}
		}
		return newTravels;
		
	}
	
	
	/** Check whether the interval between two dates is larger than 30 minutes and less than four hours
	 * @param date1 The first date
	 * @param date2 The second date
	 * @return true if the interval between two dates is larger than 30 minutes and less than four hours
	 */
	private boolean isTimeIntervalOK(Date date1, Date date2){
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long interval = time2 - time1;
		return interval >= THIRTY_MINUTES_MILLISECONDS && interval <= FOUR_HOURS_MILLISECONDS;
	}
	
	/**Return next day in String of given date
	 * @param dateString The date in String
	 * @return Next day in String of given date
	 */
	private String nextDayofDate(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return sdf.format(cal.getTime());
	}
	
	/**Convert date in Date to date in String
	 * @param date The date in Date
	 * @return The date in String
	 */
	private String convertDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(date);
	}
}
