package qbnb.models;

/** Is the parent and base for any user on the platform */
public class User {
  private int userID;
  private String username;
  private String password;
  private String email;

  // temporary constructor to help with CreateListingTests
  public User(int id, String n, String p, String e) {
    userID = id;
    username = n;
    password = p;
    email = e;
  }
}
