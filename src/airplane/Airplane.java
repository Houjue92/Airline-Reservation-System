package airplane;

import java.util.Comparator;
/**
 * This class holds values pertaining to a single Airplane. Class member attributes
 * are the same as defined by the CS509 server API and store values after conversion from
 * XML received from the server to Java primitives. Attributes are accessed via getter methods.
 * 
 * @author Houjue Wang
 * @author Jishen Xu
 * @version 1.2
 * @since 2017-12-1
 * 
 * 
 *
 */
public class Airplane implements Comparable<Airplane>, Comparator<Airplane> {
	
	/**
	 * Airplane attributes as defined by the CS509 server interface XML
	 */
	private String manufacturer;	//Name of manufacturer
	private String model;			//Name of model
	private int firstClassSeats;	//Number of First-Class Seats
	private int coachSeats;			//Number of Coach Seats
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 * 
	 * @param manufacturer Name of manufacturer
	 * @param model Name of model
	 * @param firstClassSeats Number of First-Class Seats
	 * @param coachSeats Number of Coach Seats
	 */
	public Airplane(String manufacturer, String model, int firstClassSeats, int coachSeats) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.firstClassSeats = firstClassSeats;
		this.coachSeats = coachSeats;
	}
	
	
	/**
	 * Compare two airplanes based on model
	 * 
	 * This implementation delegates to the case insensitive version of string compareTo
	 * @return results of String.compareToIgnoreCase
	 */
	public int compareTo(Airplane other) {
		return this.model.compareToIgnoreCase(other.model);
	}
	
	/**
	 * Compare two airplanes for sorting, ordering
	 * 
	 * Delegates to airpplane1.compareTo for ordering by model
	 * 
	 * @param airplane1 the first airplane for comparison
	 * @param airplane2 the second / other airplane for comparison
	 * @return -1 if airplane ordered before airplane2, +1 of airplane1 after airplane2, zero if no different in order
	 */
	public int compare(Airplane airplane1, Airplane airplane2) {
		return airplane1.compareTo(airplane2);
	}


	@Override
	public String toString() {
		return "Airplane [manufacturer=" + manufacturer + ", model=" + model + ", firstClassSeats=" + firstClassSeats
				+ ", coachSeats=" + coachSeats + "]";
	}


	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}


	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}


	/**
	 * @return the firstClassSeats
	 */
	public int getFirstClassSeats() {
		return firstClassSeats;
	}


	/**
	 * @return the coachSeats
	 */
	public int getCoachSeats() {
		return coachSeats;
	}
	
	
}
