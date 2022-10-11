package qbnb.models;

import java.util.ArrayList;

/** The object used for a host as a user Child of the user class. */
class Host extends User {
  private ArrayList<Listing> listings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  protected Host() {
    this.accountBalance = 100.00;
    this.listings = new ArrayList<Listing>();
    this.reviews = new ArrayList<Review>();
  }
}
