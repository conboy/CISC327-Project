package qbnb.models;

public class Address {
  private int streetNumber;
  private String unitNumber;
  private String streetName;
  private String city;
  private String postalCode;
  private String provState;
  private String country;

  public Address() {
    this.streetNumber = -1;
    this.unitNumber = null;
    this.streetName = null;
    this.city = null;
    this.postalCode = null;
    this.provState = null;
    this.country = null;
  }

  public Address(
      int streetNum,
      String unitNum,
      String streetName,
      String city,
      String postalCode,
      String provState,
      String Country)
      throws IllegalArgumentException {
    if (!(setStreetNumber(streetNum)
        && setUnitNumber(unitNum)
        && setStreetName(streetName)
        && setCity(city)
        && setPostalCode(postalCode)
        && setProvState(provState)
        && setCountry(Country))) {
      throw new IllegalArgumentException("Please Check The Address Given");
    }
  }

  /**
   * Sets the street number
   *
   * @param number - desired street number
   * @return Boolean - if street number set
   */
  public Boolean setStreetNumber(int number) {
    if (number > 0) {
      this.streetNumber = number;
      return true;
    }

    return false;
  }

  /**
   * returns the street number of the address
   *
   * @return int - street number
   */
  public int getStreetNumber() {
    return this.streetNumber;
  }

  /**
   * sets the desired unit number for the address @TODO - any further logic that may be required
   *
   * @param unitNumber - desired unit number
   * @return Boolean - if unit number set
   */
  public Boolean setUnitNumber(String unitNumber) {
    this.unitNumber = unitNumber;

    return true;
  }

  /**
   * returns the unit number if applicable
   *
   * @return String - unit number
   */
  public String getUnitNumber() {
    return this.unitNumber;
  }

  /**
   * Sets the street name of the address @TODO - any further logic that might be needed
   *
   * @param streetName - desired street name
   * @return Boolean - if street name set
   */
  public Boolean setStreetName(String streetName) {
    this.streetName = streetName;

    return true;
  }

  /**
   * Returns the street name of the address
   *
   * @return String - street name
   */
  public String getStreetName() {
    return this.streetName;
  }

  /**
   * Sets the city of the address
   *
   * @param city - desired city name
   * @return Boolean - if city name set
   */
  public Boolean setCity(String city) {
    this.city = city;
    return true;
  }

  /**
   * Returns the city of the address
   *
   * @return String - city name
   */
  public String getCity() {
    return this.city;
  }

  /**
   * Sets the Postal code or the zip code for the address @TODO - further logic to come later
   *
   * @param postalCode - desired postal or zip code
   * @return Boolean - if postal code set
   */
  public Boolean setPostalCode(String postal) {
    if (postal.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")) {
      this.postalCode = postal;
      return true;
    }

    return false;
  }

  /**
   * returns the current postal or zip code for the address
   *
   * @return String - postal code
   */
  public String getPostalCode() {
    return this.postalCode;
  }

  /**
   * Sets the province or state for the object @TODO - further logic to come later
   *
   * @param provState - desired prov or state
   * @return Boolean - if set
   */
  public Boolean setProvState(String provState) {
    this.provState = provState;

    return true;
  }

  /**
   * returns the province or state of the address
   *
   * @return String - prov or state name
   */
  public String getProvState() {
    return this.provState;
  }

  /**
   * Set the country for the address @TODO - further implementation to come later
   *
   * @param country - desired country
   * @return Boolean - if country set
   */
  public Boolean setCountry(String country) {
    this.country = country;

    return true;
  }

  /**
   * Returns a printable string of the address
   *
   * @return String
   */
  @Override
  public String toString() {
    if (this.unitNumber != null) {
      return this.streetNumber
          + "-"
          + this.unitNumber
          + " "
          + this.streetName
          + "\n"
          + this.city
          + ", "
          + this.provState
          + "\n"
          + this.country;
    }

    return this.streetNumber
        + " "
        + this.streetName
        + "\n"
        + this.city
        + ", "
        + this.provState
        + "\n"
        + this.country;
  }
}
