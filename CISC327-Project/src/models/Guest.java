package models;

import java.util.ArrayList;

public class Guest extends User {
	private ArrayList<Listing> bookings;
	private ArrayList<Review> reviews;
	private double accountBalance;
	
	public Guest() {
		
	}

}