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


}
