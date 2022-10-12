package qbnb.models;

/** Is the parent and base for any user on the platform */
public class User {
  private int userID;
  private String username;
  private String password;
  private String email;

  // default constructor
  public User() {}

  // actual constructor
  public User(int ID, String username, String password, String email) {
    this.userID = ID;
    this.username = username;
    this.password = password;
    this.email = email;
  }

  /** TODO */
  private Boolean checkEmail(String email) {
    return true;
  }

  public Boolean updateEmail(String email) {
    if (checkEmail(email)) {
      this.email = email;
      return true;
    }
    return false;
  }

  public String getEmail() {
    return this.email;
  }

  // TODO
  private Boolean checkUsername(String username) {
    return true;
  }

  public Boolean updateUsername(String username) {
    if (checkUsername(username)) {
      this.username = username;
      return true;
    }
    return false;
  }

  public String getUsername() {
    return this.username;
  }

  // TODO
  private Boolean checkPassword(String password) {
    return true;
  }

  public Boolean updatePassword(String password) {
    if (checkPassword(password)) {
      this.password = password;
      return true;
    }
    return false;
  }

  public String getPassowrd() {
    return this.password;
  }

  /**
   * A user can log in using her/his email address and the password. The login function checks if
   * the email/password requirements follow: R1-1: Email cannot be empty. password cannot be empty.
   * R1-3: The email has to follow addr-spec defined in RFC 5322 R1-4: Password has to meet the
   * required complexity: minimum length 6, at least one upper case, at least one lower case, and at
   * least one special character.
   */
  public boolean Login(String email, String password) {
    boolean loggedIn = false;
    if (checkEmail(email) && checkPassword(password)) {
      if (email.equals(this.email) && password.equals(this.password)) {
        loggedIn = true;
      }
    }
    return loggedIn;
  }
}
