/**
 * 
 */
package dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import airplane.Airplanes;
import airport.Airport;
import airport.Airports;
import flight.Flights;
import utils.QueryFactory;


/**
 * This class provides an interface to the CS509 server. It provides sample methods to perform
 * HTTP GET and HTTP POSTS
 *   
 * @author blake
 * @version 1.1
 * @since 2016-02-24
 *
 * @author Houjue Wang
 * @author Sachin Suvarna
 * @version 1.2
 * @since 2017-12-03
 */
public enum ServerInterface {
	INSTANCE;
	
	private final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

	/**
	 * Return a collection of all the airports from server
	 * 
	 * Retrieve the list of airports available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airports
	 * @return collection of Airports from server
	 */
	public Airports getAirports (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlAirports;
		Airports airports;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getAirports(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		xmlAirports = result.toString();
		airports = DaoAirport.addAll(xmlAirports);
		return airports;
		
	}
	
	/**
	 * Return a collection of all the airplanes from server
	 * 
	 * Retrieve the list of airplanes available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airplanes
	 * @return collection of airplanes from server
	 */
	public Airplanes getAirplanes (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlAirplanes;
		Airplanes airplanes;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getAirplanes(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		xmlAirplanes = result.toString();
		airplanes = DaoAirplane.addAll(xmlAirplanes);
		return airplanes;
		
	}
	
	
	/**
	 * Return a collection of all the flights departing an airport from server
	 * 
	 * Retrieve the list of flights departing an airport available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of flights
	 * @param airportCode identifies the code of airport the flights depart requesting the collection of flights
	 * @param date identifies the date when the flights depart requesting the collection of flights(format: yyyy_MM_hh)
	 * @return collection of flights from server
	 */
	public Flights getDepartingFlights (String teamName, String airportCode, String date, Airports airportList) {
		
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlFlights;
		Flights flights;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			
			
			
			url = new URL(mUrlBase + QueryFactory.getDepartingFlights(teamName, airportCode, date));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("Server Interface I/O issue for getDepartingFlights");
		} catch (Exception e) {
			System.out.println("Server Interface Exception for getDepartingFlights");
		}

		xmlFlights = result.toString();
		flights = DaoFlight.addAll(xmlFlights, airportCode, airportList);
		return flights;
		
	}
	
	/**
	 * Return a collection of all the flights arriving an airport from server
	 * 
	 * Retrieve the list of flights arriving an airport available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of flights
	 * @param airportCode identifies the code of airport the flights arrive requesting the collection of flights
	 * @param arrivingDate identifies the date when the flights arrive requesting the collection of flights(format: yyyy_MM_hh)
	 * @return collection of flights from server
	 */
	public Flights getArrivingFlights (String teamName, String airportCode, String arrivingDate, Airports airportList) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlFlights;
		Flights flights;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getArrivingFlights(teamName, airportCode, arrivingDate));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("Server Interface I/O issue for getArrivingFlights");
		} catch (Exception e) {
			System.out.println("Server Interface Exception for getArrivingFlights");
		}

		xmlFlights = result.toString();
		flights = DaoFlight.addAll(xmlFlights, airportCode, airportList);
		return flights;
		
	}
	/**
	 * Lock the database for updating by the specified team. The operation will fail if the lock is held by another team.
	 * 
	 * @param teamName is the name of team requesting server lock
	 * @return true if the server was locked successfully, else false
	 */
	public boolean lock (String teamName) {
		URL url;
		HttpURLConnection connection;

		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String params = QueryFactory.lock(teamName);
			
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to lock database");
			System.out.println(("\nResponse Code : " + responseCode));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
			
			System.out.println(response.toString());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Unlock the database previous locked by specified team. The operation will succeed if the server lock is held by the specified
	 * team or if the server is not currently locked. If the lock is held be another team, the operation will fail.
	 * 
	 * The server interface to unlock the server interface uses HTTP POST protocol
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return true if the server was successfully unlocked.
	 */
	public boolean unlock (String teamName) {
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			String params = QueryFactory.unlock(teamName);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
		    
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to unlock database");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Reserve a seat for a collection of flights.
	 * Update the database to increment the number of seats reserved in a collection of flights.
	 *
	 * @param teamName is the name of team requesting the updation.
	 * @param flightNumbers is the array of flight numbers of those flights to be reserved in the database.
	 * @param isFirstClass is the indication whether the seats to be reserved are first-class.
	 * @return true if flights are reserved successfully, false if not.
	 */
	public boolean reserveFlights(String teamName, String[] flightNumbers, boolean isFirstClass){
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			String params = QueryFactory.reserveFlights(teamName,flightNumbers,isFirstClass);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
		    
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to reserve flights");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
		
	
	
}
