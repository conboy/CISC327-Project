package qbnb.models;

public class Address {
    private int streetNumber;
    private String unitNumber;
    private String streetName;
    private String city;
    private String postalZip;
    private String provState;
    private String country;

    public Address() {
        this.streetNumber = -1;
        this.unitNumber = null;
        this.streetName = null;
        this.city = null;
        this.postalZip = null;
        this.provState = null;
        this.country = null;
    }

    public Address(int streetNum, String unitNum, String streetName, String city, String postalCode, String provState,
            String Country) throws IllegalArgumentException {
        if (!(setStreetNumber(streetNum) && setUnitNumber(unitNum) && setStreetName(streetName) && setCity(city)
                && setPostalZip(postalCode) && setProvState(provState) && setCountry(Country))) {
            throw new IllegalArgumentException("Please Check The Address Given");
        }
    }
  
    /** 
     * Sets the street number
     * 
     * @param number - desired street number
     * @return Boolean
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
     * @return int
     */
    public int getStreetNumber() {
        return this.streetNumber;
    }
  
    /** 
     * sets the desired unit number for the address
     * 
     * @TODO - any further logic that may be required
     * @param unitNumber - desired unit number
     * @return Boolean
     */
    public Boolean setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;

        return true;
    }
 
    /** 
     * returns the unit number if applicable
     * 
     * @return String
     */
    public String getUnitNumber() {
        return this.unitNumber;
    }
    
    /** 
     * Sets the street name of the address
     * 
     * @TODO - any further logic that might be needed
     * @param streetName
     * @return Boolean
     */
    public Boolean setStreetName(String streetName) {
        this.streetName = streetName;

        return true;
    }
    
    /** 
     * Returns the street name of the address
     * 
     * @return String
     */
    public String getStreetName() {
        return this.streetName;
    }
  
    /** 
     * Sets the city of the address
     * 
     * @TODO - if any further logic is needed
     * @param city
     * @return Boolean
     */
    public Boolean setCity(String city) {
        this.city = city;

        return true;
    }
 
    /** 
     * Returns the city of the address
     * 
     * @return String
     */
    public String getCity() {
        return this.city;
    }
    
    /** 
     * Sets the Postal code or the zip code for the address
     * 
     * @TODO - further logic to come later
     * @param postalZip - desired postal or zip code
     * @return Boolean
     */
    public Boolean setPostalZip(String postalZip) {
        this.postalZip = postalZip;

        return true;
    }

    /**
     * returns the current postal or zip code for the address
     *  
     * @return String
     */
    public String getPostalZip() {
        return this.postalZip;
    }
   
    /** 
     * Sets the province or state for the object
     * 
     * @TODO - further logic to come later
     * @param provState
     * @return Boolean
     */
    public Boolean setProvState(String provState) {
        this.provState = provState;

        return true;
    }
    
    /**
     * returns the province or state of the address
     *  
     * @return String
     */
    public String getProvState() {
        return this.provState;
    }
    
    /** 
     * Set the country for the address
     * 
     * @TODO - further implementation to come later
     * @param country - desired country
     * @return Boolean
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
            return this.streetNumber + "-" + this.unitNumber + " " + this.streetName + "\n" + this.city + ", "
                    + this.provState + "\n" + this.country;
        }

        return this.streetNumber + " " + this.streetName + "\n" + this.city + ", " + this.provState + "\n"
                + this.country;
    }
}