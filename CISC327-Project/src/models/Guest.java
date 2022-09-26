package models;

import java.util.ArrayList;

/**The object used for a guest as a user
 * Child of the user class.
 */
public class Guest extends User {
	private ArrayList<Listing> bookings;
	private ArrayList<Review> reviews;
	private double accountBalance;
	
	public Guest() {
		
	}

}