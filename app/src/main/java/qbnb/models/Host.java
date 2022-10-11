package qbnb.models;

import java.util.ArrayList;

/** The object used for a host as a user Child of the user class. */
public class Host extends qbnb.models.User {
  private ArrayList<qbnb.models.Listing> listings;
  private ArrayList<Review> reviews;
  private double accountBalance;

<<<<<<< HEAD
  // temporary constructor to fix build errors
  public Host(int id, String user, String password, String email) {
    super(id, user, password, email);
=======
  // temporary - for CreateListTests
  public Host(int id, String n, String p, String e) {
    super(id, n, p, e);
>>>>>>> 6bc926c1a65bac80755be60fa1b5e04651cc86d0
  }
}
