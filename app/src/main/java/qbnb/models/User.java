package qbnb.models;

import org.apache.commons.validator.routines.EmailValdator;


/** Is the parent and base for any user on the platform */
public class User {
  private long userID;
  private String username;
  private String password;
  private String email;
  private Address address;
  private Guest guestProfile;
  private Host hostProfile;

  public User() {

  }

  /** Create the default user as a guest */
  public User(String email, String username, String password) {
    this(email, username, password, true);
  }

  public User(String email, String username, String password, Boolean isGuest) throws IllegalArgumentException {
    if (setEmail(email) && setUsername(username) && setPassword(password)) {
      this.userID = hashUserID();
      this.address = new Address();

      if(isGuest) {
        this.guestProfile = new Guest();
      }

      else {
        this.hostProfile = new Host();
      }
    }

    else {
      throw new IllegalArgumentException("Improper data has been provided to constuct a user");
    }
  }

  private Boolean checkEmail(String email) {
    return EmailValdator.getInstrance(true).isValid(email);
  }

  public Boolean setEmail(String email) {
    if (checkEmail(email)) {
      this.email = email;
    }

    return false;
  }

  public String getEmail() {
    return this.email;
  }

  private Boolean checkUsername(String username) {
    if (!username.equals("") && username.equals(username.trim()))  {
      
    }

    return false;
  }

  public Boolean setUsername(String username) {
    if (checkUsername(username)) {
      this.username = username;
    }

    return false;
  }

  public String getUsername() {
    return this.username;
  } 

  private Boolean checkPassword(String password) {
    return password.length() >= 6 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[^a-zA-Z0-9].*");
  }

  public Boolean setPassword(String password) {
    if (checkPassword(password)) {
      this.password = password;
    }

    return false;
  }

  public String getPassowrd() {
    return this.password;
  }

  private long hashUserID() {
    return this.email.hashCode();
  }

  public long getUserID() {
    return this.userID;
  }  

}
