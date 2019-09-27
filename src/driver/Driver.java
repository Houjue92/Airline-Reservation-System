/**
 * 
 */
package driver;

import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import airplane.Airplane;
import airplane.Airplanes;
import airport.Airport;
import airport.Airports;
import dao.ServerInterface;
import flight.Flight;
import flight.Flights;
import reserveTravel.ReserveTravel;
import searchParameter.SearchParameter;
import searchTravel.SearchTravel;
import time.TimeConversion;
import travel.SortTravelByOrder;
import travel.Travel;
import travel.Travels;
import utils.QueryFactory;

/**
 * @author blake
 *
 */
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will retrieve the list of airports from the CS509 server and print the list 
	 * to the console sorted by 3 character airport code
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public static void main(String[] args) {
		
		String teamName = "WorkingTogether";
		
		Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Airplanes airplanes = ServerInterface.INSTANCE.getAirplanes(teamName);
		
		
		String time = "2017 Dec 10 09:15 GMT";
		Date date = TimeConversion.parseDateInGMT(time);
		System.out.println(date);
		System.out.println(date.getTime());
		//Date date = TimeConversion.convertGMTToLocal(time, "BOS", airports);
		//System.out.println(date);
		/*
		String airportCode = "BOS";
		String departingDate = "2017_12_10";
		
		SearchParameter searchParameter = new SearchParameter(false,"BOS","2017_12_10","JFK","2017_12_15",true);
		SearchTravel searchTravel = new SearchTravel(searchParameter,airplanes, airports);
		Travels firstTravels = searchTravel.getFistTravels();
		//Travels secondTravels = searchTravel.getSecondTravels();
				
		System.out.println("First Travel:");
		for(Travel travel : firstTravels){
			System.out.println(travel);
		}
		*/
		/*
		Scanner scan = new Scanner(System.in);
		System.out.print("Choice:");
		int choice = scan.nextInt();
		Travel travelToReserve = firstTravels.get(choice);
		System.out.println("Travel to reserve:");
		System.out.println(travelToReserve);
		ReserveTravel reserveTravel = new ReserveTravel(false, travelToReserve, null, true);
		reserveTravel.reserveTravel();
		firstTravels = searchTravel.getFistTravels();
		System.out.println("First Travel:");
		for(Travel travel : firstTravels){
			System.out.println(travel);
		}
		*/
		
	}
}
