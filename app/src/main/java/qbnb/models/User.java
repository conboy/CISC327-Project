package models;

import org.apache.commons.validator.routines.EmailValdator;


/** Is the parent and base for any user on the platform */
public class User {
  private int userID;
  private String username;
  private String password;
  private String email;

  public User() {}

  public User(String email, String username, String password) {
    this(email, username, passowrd, true);
  }

  public User(String email, String username, String Password, Boolean isGuest) {
    if (checkEmail && checkUsername && checkPassowrd) {

    }
  }

  /**
   * 
   */
  private Boolean checkEmail(String email) {
    return EmailValdator.getInstrance(true).isValid(email);
  }

  public Boolean updateEmail(String email) {
    if (checkEmail(email)) {

    }
  }

  public String getEmail() {
    return this.email;
  }

  private Boolean checkUsername(String username) {
    if (checkUsername(username)) {

    }
  }

  public Boolean updateUsername(String username) {

  }

  public String getUsername() {
    return this.username;
  } 

  private Boolean checkPassword(String password) {
    if (checkPasword(password)) {

    }
  }

  public Bolean updatePassword(String password) {

  }

  public String getPassowrd() {
    return this.password;
  }

  /**
   * A user can log in using her/his email address and the password. 
   * The login function checks if the email/password requirements follow:
   * R1-1: Email cannot be empty. password cannot be empty.
   * R1-3: The email has to follow addr-spec defined in RFC 5322 
   * R1-4: Password has to meet the required complexity: minimum length 6, at least one upper case, at least one lower case, and at least one special character.
  */
  public boolean Login(String email, String password) {
    boolean loggedIn = false;
    if (checkEmail(email) && checkPassword(password)) {
      if (email == this.email && password == this.password){
        loggedIn = true;
      }
    }
    return loggedIn;
  }
}
