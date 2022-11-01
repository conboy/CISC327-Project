package qbnb.models;

/** Is the parent and base for any user on the platform */
public class User {
  private long userID;
  private String username;
  private String password;
  private String email;
  private String address;
  private String postalCode;
  private Guest guestProfile;
  private Host hostProfile;

  public User() {}

  /** Create the default user as a guest */
  public User(String email, String username, String password) {
    this(email, username, password, true);
  }

  public User(String email, String username, String password, Boolean isGuest)
      throws IllegalArgumentException {
    if (setEmail(email) && setUsername(username) && setPassword(password)) {
      // creates user ID, user has no other method of doing this (R3-1)
      this.userID = hashUserID();
      this.address = "";
      this.postalCode = "";

      if (isGuest) {
        this.guestProfile = new Guest();
      } else {
        this.hostProfile = new Host();
      }
    } else {
      throw new IllegalArgumentException("Improper data has been provided to constuct a user");
    }
  }

  /**
   * Checks the desired email based on the RFC 5322 spec
   *
   * @param email - desired email
   * @return Boolean
   */
  private Boolean checkEmail(String email) {
    return (email.matches(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)[a-zA-Z]{2,7}$")
        && (email.substring(0, email.indexOf("@")).length() <= 64));
  }

  /**
   * Sets the desired email for the user
   *
   * @param email - desired email
   * @return Boolean
   */
  public Boolean setEmail(String email) {
    if (checkEmail(email)) {
      this.email = email;
      return true;
    }

    return false;
  }

  /**
   * Returns the unique email for the user
   *
   * @return String
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Checks that a string only has alphanumeric characters for a username (R3-4)
   *
   * @param username - desired username
   * @return Boolean
   */
  private Boolean checkUsername(String username) {
    if (username.equals(username.trim()) && (2 <= username.length()) && (20 >= username.length())) {
      if (username.matches("[A-Za-z0-9]+")) {
        return true;
      }
    }

    return false;
  }

  /**
   * Sets the desired username for the user
   *
   * @param username - desired username
   * @return Boolean
   */
  public Boolean setUsername(String username) {
    if (checkUsername(username)) {
      this.username = username;
      return true;
    }

    return false;
  }

  /**
   * returns the username of the user
   *
   * @return String
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Validates the password given based on R1
   *
   * <p>is public to be used for validation is tests
   *
   * @param password - desired password
   * @return Boolean
   */
  public Boolean checkPassword(String password) {
    return (password.length() >= 6
        && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$"));
  }

  /**
   * Set a new password for the user
   *
   * <p>Method is private because users should not change password once initialized (R3-1)
   *
   * @param password - desired password
   * @return Boolean
   */
  private Boolean setPassword(String password) {
    if (checkPassword(password)) {
      this.password = password;
      return true;
    }

    return false;
  }

  /**
   * returns the user's passowrd
   *
   * @return String
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Creates the userID for the user using the verified email and hashCode()
   *
   * @return long
   */
  private long hashUserID() {
    return this.email.hashCode();
  }

  /**
   * Returns the userID of the user
   *
   * @return long
   */
  public long getUserID() {
    return this.userID;
  }

  /** Returns the address object belonging to the user */
  public String getAddress() {
    return this.address;
  }

  /** Sets adress based on given parameters. */
  public Boolean setAddress(String address) {
    this.address = address;
    return true;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public boolean setPostalCode(String code) {
    if (code.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")) {
      this.postalCode = code;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Deletes the guest profile for the user
   *
   * @return Boolean
   */
  public Boolean deleteGuest() {
    this.guestProfile = null;
    return true;
  }

  /**
   * Adds a guest account for the user
   *
   * @return Boolean
   */
  public Boolean addGuest() {
    this.guestProfile = new Guest();
    return true;
  }

  /**
   * Gets and returns the guest account for the user
   *
   * @return Guest
   */
  public Guest getGuestAccount() {
    return this.guestProfile;
  }

  /**
   * Delete's the user's host profile
   *
   * @return Boolean
   */
  public Boolean deleteHost() {
    this.hostProfile = null;
    return true;
  }

  /**
   * Adds a blank host profile for the user
   *
   * @return Boolean
   */
  public Boolean addHost() {
    this.hostProfile = new Host();
    return true;
  }

  /**
   * Gets and retuns the host profile for the user
   *
   * @return Host
   */
  public Host getHostAccount() {
    return this.hostProfile;
  }

  public boolean update(String name, String mail, String address, String postalCode) {
    return this.setUsername(name)
        && this.setEmail(mail)
        && this.setAddress(address)
        && this.setPostalCode(postalCode);
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
