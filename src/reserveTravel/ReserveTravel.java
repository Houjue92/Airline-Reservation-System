package reserveTravel;

import airplane.Airplanes;
import dao.ServerInterface;
import travel.Travel;
import utils.Saps;

/**This class holds information of travels the customer wants to reserve. 
 * It checks whether the seats of travel are available. 
 * It locks database, makes the reservation and unlocks database after the customer starts the reservation.
 * @author Jishen
 *
 */
public class ReserveTravel {
	private boolean isRoundTrip;	//If the reservation contains a round trip.
	private Travel firstTravel;		//The departure travel in the reservation.
	private Travel secondTravel;	//The return travel in the reservation if it contains a round trip.
	private boolean isFirstClass;	//If the seats to be reserved is first-class

	/**Initializing constructor.
	 * 
	 * All attributes are initialized with input values.
	 * @param isRoundTrip If the reservation contains a round trip
	 * @param firstTravel The departure travel in the reservation
	 * @param secondTravel The return travel in the reservation if it contains a round trip
	 * @param isFirstClass If the seats to be reserved is first-class
	 */
	public ReserveTravel(boolean isRoundTrip,Travel firstTravel, Travel secondTravel,boolean isFirstClass) {
		this.isRoundTrip = isRoundTrip;
		this.firstTravel = firstTravel;
		this.secondTravel = secondTravel;
		this.isFirstClass = isFirstClass;
	}
	
	/**Check if seats in the travels to be reserved are available
	 * @param airplanes The list of airplanes retrieved from data server
	 * @return true if seats are available in all travels of this reservation, false if not
	 */
	public boolean isSeatAvailable(Airplanes airplanes){
		if(isRoundTrip){
			return isSeatAvailable(firstTravel,airplanes) && isSeatAvailable(secondTravel,airplanes);
		}else{
			return isSeatAvailable(firstTravel,airplanes);
		}
	}
	
	/**Check if seats in one travel of to be reserved are available
	 * @param travel The travel to be checked
	 * @param airplanes The list of airplanes retrieved from data server
	 * @return true if such a travel's seats are available, false if not
	 */
	private boolean isSeatAvailable(Travel travel,Airplanes airplanes){
		if(isFirstClass) return travel.isFirstClassSeatAvailable(airplanes);
		else return travel.isCoachSeatAvailable(airplanes);
	}
	
	/**Reserve the travels in this reservation
	 * @return true if travels are reserved successfully
	 */
	public boolean reserveTravel(){
		boolean result;
		while(!ServerInterface.INSTANCE.lock(Saps.TEAM_NAME));
		result = ServerInterface.INSTANCE.reserveFlights(Saps.TEAM_NAME, firstTravel.getFlightNumbers(), isFirstClass);
		firstTravel.addNumOfSeatsReserved(isFirstClass,1);
		if(isRoundTrip){
			result = ServerInterface.INSTANCE.reserveFlights(Saps.TEAM_NAME, secondTravel.getFlightNumbers(), isFirstClass);
			secondTravel.addNumOfSeatsReserved(isFirstClass, 1);
		}
		while(!ServerInterface.INSTANCE.unlock(Saps.TEAM_NAME));
		
		return result;
	}
	
}
