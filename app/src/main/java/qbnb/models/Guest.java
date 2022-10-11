package qbnb.models;

import java.util.ArrayList;

/** The object used for a guest as a user Child of the user class. */
class Guest extends User {
  private ArrayList<Listing> bookings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  protected Guest() {
    this.accountBalance = 100.00;
    this.bookings = new ArrayList<Listing>();
    this.reviews = new ArrayList<Review>();
  }
}
