/**
 * 
 */
package utils;

/**This class provides a list of methods generating xml strings that can be passes to HTTP URL to do query or update data server
 * @author blake
 * @version 1.2
 *
 */
public class QueryFactory {
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirports(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airports";
	}
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airplanes
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirplanes(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airplanes";
	}
	
	
	/**Return a query string that can be passed to HTTP URL to request list of flights departing an airport
	 * @param teamName is the name of the team to specify the data copy on server
	 * @param airportCode is the code of airport the flights depart
	 * @param departingDate is the date when flights depart
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getDepartingFlights(String teamName, String airportCode, String departingDate){
		return "?team=" + teamName + "&action=list&list_type=departing&airport=" + airportCode + "&day=" + departingDate;
	}
	
	/**Return a query string that can be passed to HTTP URL to request list of flights arriving an airport
	 * @param teamName is the name of the team to specify the data copy on server
	 * @param airportCode is the code of airport the flights arrive
	 * @param arrivingDate is the date when flights arrive
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getArrivingFlights(String teamName, String airportCode, String arrivingDate){
		return "?team=" + teamName + "&action=list&list_type=arriving&airport=" + airportCode + "&day=" + arrivingDate;
	}
	
	
	/**
	 * Lock the server database so updates can be written
	 * 
	 * @param teamName is the name of the team to acquire the lock
	 * @return the String written to HTTP POST to lock server database 
	 */
	public static String lock (String teamName) {
		return "team=" + teamName + "&action=lockDB";
	}
	
	/**
	 * Unlock the server database after updates are written
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return the String written to the HTTP POST to unlock server database
	 */
	public static String unlock (String teamName) {
		return "team=" + teamName + "&action=unlockDB";
	}
	
	/**Return a query string that can be passed to HTTP URL to reserve a list of flights
	 * @param teamName is the name of the team to specify the data copy on server
	 * @param flightNumbers is the array of flight numbers belonging to those flights to be reserved 
	 * @param isFirstClass is whether the flights are booked with first-class seats
	 * @return the String written to the HTTP POST to reserve the flights
	 */
	public static String reserveFlights(String teamName, String[] flightNumbers, boolean isFirstClass){
		String seatType = isFirstClass?"FirstClass":"Coach";
		StringBuffer xmlFlights = new StringBuffer();
		xmlFlights.append("<Flights>");
		for(int i = 0;i < flightNumbers.length;i++){
			xmlFlights.append("<Flight number=\"" + flightNumbers[i] + "\" seating=\"" + seatType + "\"/>");
		}
		xmlFlights.append("</Flights>");
		return "team=" + teamName + "&action=buyTickets&flightData=" + xmlFlights.toString();
	}
	

}
