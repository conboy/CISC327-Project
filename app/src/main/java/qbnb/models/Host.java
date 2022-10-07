package qbnb.models;

import java.util.ArrayList;

/** The object used for a host as a user Child of the user class. */
public class Host extends qbnb.models.User {
  private ArrayList<qbnb.models.Listing> listings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  //temporary constructor to fix build errors
  public Host(int id, String user, String password, String email) {
    super(id, user, password, email);
  }
}
