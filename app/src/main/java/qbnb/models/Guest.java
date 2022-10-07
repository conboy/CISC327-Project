package qbnb.models;

import java.util.ArrayList;

/** The object used for a guest as a user Child of the user class. */
public class Guest extends qbnb.models.User {
  private ArrayList<qbnb.models.Listing> bookings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  // temporary method to fix build errors
  public Guest(int id, String user, String password, String email) {
    super(id, user, password, email);
  }
}
