package utils;

import java.util.Comparator;
import java.util.Date;
/**
 * This class compares two different date values while also ignoring the time for that particular day.
 * 
 * @author Sachin Suvarna
 *	
 */
public class TimeIgnoringComparator implements Comparator<Date> {
	/**
	 * @param date The first date to be compared
	 * @param date The second date to be compared
	 * @return the integer value if there is a difference in dates
	 */
	  public int compare(Date d1, Date d2) {
		  if(d1 == null || d2 == null) return 1;
	    if (d1.getYear() != d2.getYear()) 
	        return d1.getYear() - d2.getYear();
	    if (d1.getMonth() != d2.getMonth()) 
	        return d1.getMonth() - d2.getMonth();
	    return d1.getDate() - d2.getDate();
	  }
	}