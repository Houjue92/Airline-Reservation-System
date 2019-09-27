/**
 * 
 */
package dao;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import airport.Airport;
import airport.Airports;
import flight.Flight;
import flight.Flights;
import time.TimeConversion;

/**
 * @author blake
 * @author Houjue Wang
 * @author Sachin Suvarna
 */
public class DaoFlight {
	/**
	 * Builds collection of flights from flights described in XML
	 * 
	 * Parses an XML string to read each of the flights and adds each valid flight
	 * to the collection. The method uses Java DOM (Document Object Model) to
	 * convert from XML to Java primitives.
	 * 
	 * Method iterates over the set of flight nodes in the XML string and builds an
	 * flight object from the XML node string and add the flight object instance to
	 * the flights collection.
	 * 
	 * @param xmlFlights
	 *            XML string containing set of flights
	 * @return [possibly empty] collection of flights in the xml string
	 * @throws NullPointerException
	 *             included to keep signature consistent with other addAll methods
	 */
	private static double latitude;
	private static double longitude;

	public static Flights addAll (String xmlFlights, String depairportCode, Airports airportList) throws NullPointerException {
		Flights flights = new Flights();
		/*Airport airport = new Airport();
		Airports airports = new Airports();*/
		/*for(int i = 0;i < airportList.size();i++){
			if(airportList.get(i).code().equals(depairportCode)){
				airport.latitude(airportList.get(i).latitude());
				System.out.println("latitude is:"+airport.latitude());
				airport.longitude(airportList.get(i).longitude());
				airport.code(depairportCode);
				airport.name(depairportCode);
				airports.add(airport);
				
				}
			
		}*/
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airport to our collection
		Document docFlights = buildDomDoc (xmlFlights);
		NodeList nodesFlights = docFlights.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesFlights.getLength(); i++) {
			Element elementFlight = (Element) nodesFlights.item(i);
			Flight flight = buildFlight(elementFlight,airportList);
			flights.add(flight);
		}
		
		return flights;
	}

	/**
	 * Creates an Flight object from a DOM node
	 * 
	 * Processes a DOM Node that describes an Flight and creates an Flight object
	 * from the information
	 * 
	 * @param nodeFlight
	 *            is a DOM Node describing an Flight
	 * @return Flight object created from the DOM Node representation of the Flight
	 * 
	 * @pre nodeFlight is of format specified by CS509 server API
	 */
	static private Flight buildFlight(Node nodeFlight, Airports airportList) {
		Flight flight = new Flight();
		
		String airplaneModel;
		double flightTime;
		String number;
		String departureCode;
		Date departureTime;
		
		String arrivalCode;
		Date arrivalTime;
		
		double firstClassPrice;
		int numOfFirstClassReserved;
		double coachPrice;
		int numOfCoachReserved;

		String departureTimeString;
		String arrivalTimeString;
		String departureTimeStringInLocalTimeZone;
		String arrivalTimeStringInLocalTimeZone;
		
		Element elementFlight = (Element) nodeFlight;
		airplaneModel = elementFlight.getAttributeNode("Airplane").getValue();
		flightTime = Double.parseDouble(elementFlight.getAttributeNode("FlightTime").getValue());
		number = elementFlight.getAttributeNode("Number").getValue();

		Element elementDeparture = (Element) elementFlight.getElementsByTagName("Departure").item(0);
		Element elementDepartureCode = (Element) elementDeparture.getElementsByTagName("Code").item(0);
		Element elementDepartureTime = (Element) elementDeparture.getElementsByTagName("Time").item(0);
		departureCode = getCharacterDataFromElement(elementDepartureCode);
		departureTimeString = getCharacterDataFromElement(elementDepartureTime);

		Element elementArrival = (Element) elementFlight.getElementsByTagName("Arrival").item(0);
		Element elementArrivalCode = (Element) elementArrival.getElementsByTagName("Code").item(0);
		Element elementArrivalTime = (Element) elementArrival.getElementsByTagName("Time").item(0);
		arrivalCode = getCharacterDataFromElement(elementArrivalCode);
		arrivalTimeString = getCharacterDataFromElement(elementArrivalTime);

		Element elementSeating = (Element) elementFlight.getElementsByTagName("Seating").item(0);
		Element elementFirstClass = (Element) elementSeating.getElementsByTagName("FirstClass").item(0);
		Element elementCoach = (Element) elementSeating.getElementsByTagName("Coach").item(0);

		firstClassPrice = Double.parseDouble(elementFirstClass.getAttribute("Price").substring(1).replace(",", ""));
		numOfFirstClassReserved = Integer.parseInt(getCharacterDataFromElement(elementFirstClass));
		coachPrice = Double.parseDouble(elementCoach.getAttribute("Price").substring(1).replace(",", ""));
		numOfCoachReserved = Integer.parseInt(getCharacterDataFromElement(elementCoach));
		// Code Mod here
		/*System.out.println("Departure time string is::::::::::::"+departureTimeString);
		System.out.println("Arrival time string is::::::::::::"+arrivalTimeString);*/
		
		departureTime = TimeConversion.parseDateInGMT(departureTimeString);
		arrivalTime = TimeConversion.parseDateInGMT(arrivalTimeString);
		departureTimeStringInLocalTimeZone = TimeConversion.convertGMTToLocal(departureTimeString,departureCode,airportList);
		arrivalTimeStringInLocalTimeZone = TimeConversion.convertGMTToLocal(arrivalTimeString,arrivalCode,airportList);
		
		
		
		//departureTime = TimeConversion.parseDate(departureTimeString,airportList);
		//arrivalTime = TimeConversion.parseDate(arrivalTimeString,airportList);
		//Printtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt
		//System.out.println(departureTime1+"NON_GMT TIMES CAN BE SEEN HERE"+arrivalTime1);
		//Printtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt
		flight.setAirplaneModel(airplaneModel);
		flight.setFlightTime(flightTime);
		flight.setNumber(number);
		flight.setDepartureCode(departureCode);
		flight.setDepartureTimeString(departureTimeString);
		flight.setArrivalCode(arrivalCode);
		flight.setArrivalTimeString(arrivalTimeString);
		flight.setFirstClassPrice(firstClassPrice);
		flight.setNumOfFirstClassReserved(numOfFirstClassReserved);
		flight.setCoachPrice(coachPrice);
		flight.setNumOfCoachReserved(numOfCoachReserved);
		flight.setDepartureTime(departureTime);
		flight.setArrivalTime(arrivalTime);
		flight.setDepartureTimeStringInLocalTimeZone(departureTimeStringInLocalTimeZone);
		flight.setArrivalTimeStringInLocalTimeZone(arrivalTimeStringInLocalTimeZone);
		return flight;
	}

	/**
	 * Builds a DOM tree from an XML string
	 * 
	 * Parses the XML file and returns a DOM tree that can be processed
	 * 
	 * @param xmlString
	 *            XML String containing set of objects
	 * @return DOM tree from parsed XML or null if exception is caught
	 */
	static private Document buildDomDoc(String xmlString) {
		/**
		 * load the xml string into a DOM document and return the Document
		 */
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlString));

			return docBuilder.parse(inputSource);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieve character data from an element if it exists
	 * 
	 * @param e
	 *            is the DOM Element to retrieve character data from
	 * @return the character data as String [possibly empty String]
	 */
	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
}
