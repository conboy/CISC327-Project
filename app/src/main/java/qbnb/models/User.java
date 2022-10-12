package qbnb.models;

// import org.apache.commons.validator.routines.EmailValdator;

/** Is the parent and base for any user on the platform */
public class User {
  private long userID;
  private String username;
  private String password;
  private String email;
  private Address address;
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
      this.userID = hashUserID();
      this.address = new Address();

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
    // return EmailValdator.getInstrance(true).isValid(email);
    return true;
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
   * Checks the username for the user based on given parameters
   *
   * @param username - desired username
   * @return Boolean
   */
  private Boolean checkUsername(String username) {
    if (!username.equals("")
        && username.equals(username.trim())
        && (2 <= username.length())
        && (20 >= username.length())) {
      if (!username.matches(".*[^a-zA-Z0-9].*")) {
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
   * Validates the password given based on required criteria
   *
   * @param password - desired password
   * @return Boolean
   */
  private Boolean checkPassword(String password) {
    return password.length() >= 6
        && password.matches(".*\\d.*")
        && password.matches(".*[A-Z].*")
        && password.matches(".*[a-z].*")
        && password.matches(".*[^a-zA-Z0-9].*");
  }

  /**
   * Set a new password for the user
   *
   * @param password - desired password
   * @return Boolean
   */
  public Boolean setPassword(String password) {
    if (checkPassword(password)) {
      this.password = password;
    }

    return false;
  }

  /**
   * returns the user's passowrd
   *
   * @return String
   */
  public String getPassowrd() {
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

  /**
   * Sets adress based on given parameters @TODO: further implementation of this @TODO implement
   * later
   *
   * @param streetNum - street number
   * @param unitNum - unit number if applicable (null if not)
   * @param streetName - street name
   * @param city - city name
   * @param postalCode - postal orzip code
   * @param prov - province or state
   * @param country - country
   * @return Boolean - true if the adress was sucessfully set
   */
  public Boolean setAddress(
      int streetNum,
      String unitNum,
      String streetName,
      String city,
      String postalCode,
      String prov,
      String country) {
    if (this.address.getStreetNumber() == -1) {
      this.address = new Address(streetNum, unitNum, streetName, city, postalCode, prov, country);
      return true;
    }

    return false;
  }

  /**
   * Updates th address object for the user @TODO: to be implemented in full later
   *
   * @return Boolean - if address sucessefully updated
   */
  public Boolean updateAddress() {
    return true;
  }

  /**
   * gets and returns the address object belonging to the user
   *
   * @return Address
   */
  public Address getAddress() {
    return this.address;
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
}
