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
    
    public Address(int streetNum, String unitNum, String streetName, String city, String postalCode, String provState, String Country) throws IllegalArgumentException {
        if (!(setStreetNumber(streetNum) && setUnitNumebr(unitNum) && setStreetName(streetName) && setCity(city) && setPostalZip(postalCode) && setProvState(provState) && setCountry(Country))) {
            throw new IllegalArgumentException("Please Check The Address Given");
        }
    }
    
    public Boolean setStreetNumber(int number) {
        if(number > 0 ) {
            this.streetNumber = number;
            return true;
        }

        return false;
    }

    public int getStreetNumber() {
        return this.streetNumber;
    }

    public Boolean setUnitNumebr(String unitNumber) {
        this.unitNumber = unitNumber;

        return true;
    }

    public String getUnitNumber() {
        return this.unitNumber;
    }

    public Boolean setStreetName(String streetName) {
        this.streetName = streetName;

        return true;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public Boolean setCity(String city) {
        this.city = city;
        
        return true;
    }

    public String getCity() {
        return this.city;
    }

    public Boolean setPostalZip(String postalZip) {
        this.postalZip = postalZip;

        return true;
    }

    public String getPostalZip() {
        return this.postalZip;
    }

    public Boolean setProvState(String provState) {
        this.provState = provState;

        return true;
    }

    public String getProvState() {
        return this.provState;
    }

    public Boolean setCountry(String country) {
        this.country = country;

        return true;
    }

    @Override
    public String toString() {
        if(this.unitNumber != null) {
            return this.streetNumber + "-" + this.unitNumber + " " + this.streetName + "\n" + this.city + ", " + this.provState + "\n" + this.country;
        }

        return this.streetNumber + " " + this.streetName + "\n" + this.city + ", " + this.provState + "\n" + this.country;
    }
}