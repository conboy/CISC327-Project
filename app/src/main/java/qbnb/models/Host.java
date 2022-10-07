package qbnb.models;

import java.util.ArrayList;

/** The object used for a host as a user Child of the user class. */
public class Host extends User {
  private ArrayList<Listing> listings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  // temporary - for CreateListTests
  public Host(int id, String n, String p, String e) {
    super(id, n, p, e);
  }
}
