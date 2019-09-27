package travel;


import java.util.Comparator;


/**This class implements Comparator to provide a Comparator based on different kinds of order
 * @author Houjue Wang
 * @author xujam
 *
 */
public class SortTravelByOrder implements Comparator<Travel> {

	/**
	 * The sorting order is based on coach-seat price.
	 */
	public static final int COACH_PRICE = 0;
	/**
	 * The sorting order is based on first-class-seat price.
	 */
	public static final int FIRST_CLASS_PRICE = 1;
	/**
	 * The sorting order is based on departure time.
	 */
	public static final int DEPARTURE_TIME = 2;
	/**
	 * The sorting order is based on arriving time.
	 */
	public static final int ARRIVAL_TIME = 3;
	/**
	 * The sorting order is based on travel time.
	 */
	public static final int TRAVEL_TIME = 4;
	
	private int order;
	
	/**Initializing constructor
	 * @param order What kind of order the travels would be sorted by
	 * @throws RuntimeException is any parameter is invalid
	 */
	public SortTravelByOrder(int order) throws RuntimeException{
		if(order < 0 || order > 4) throw new RuntimeException();
		this.order = order;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Travel o1, Travel o2) { 
		switch(order){
		case COACH_PRICE:
			return o1.getCoachPrice() - o2.getCoachPrice();
		case FIRST_CLASS_PRICE:
			return o1.getFirstClassPrice() - o2.getFirstClassPrice();
		case DEPARTURE_TIME:
			return (int)(o1.getDepartureTime().getTime() - o2.getDepartureTime().getTime());
		case ARRIVAL_TIME:
			return (int)(o1.getArrivalTime().getTime() - o2.getArrivalTime().getTime());
		case TRAVEL_TIME:
			return (int)(o1.getTravelTime() - o2.getTravelTime());
		}
		return 0;
	}

}
