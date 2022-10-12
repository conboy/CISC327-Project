package qbnb.models;

/** Is the parent and base for any user on the platform */
public class User {
  private int userID;
  private String username;
  private String password;
  private String email;

  public User() {}

  public User(String email, String password) {}
}