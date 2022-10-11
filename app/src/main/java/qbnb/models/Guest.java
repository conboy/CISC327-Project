package qbnb.models;

import java.util.ArrayList;

/** The object used for a guest as a user Child of the user class. */
public class Guest extends qbnb.models.User {
  private ArrayList<qbnb.models.Listing> bookings;
  private ArrayList<Review> reviews;
  private double accountBalance;

<<<<<<< HEAD
  // temporary method to fix build errors
  public Guest(int id, String user, String password, String email) {
    super(id, user, password, email);
=======
  // temporary - for CreateListTests
  public Guest(int id, String n, String p, String e) {
    super(id, n, p, e);
>>>>>>> 6bc926c1a65bac80755be60fa1b5e04651cc86d0
  }
}
