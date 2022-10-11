package qbnb.models;

class Address {
    private int streetNumber;
    private String unitNumber;
    private String streetName;
    private String city;
    private String provState;
    private String country;

    protected Address() {
        
    }
    
    protected Address(int streetNum, String unitNum, String streetName, String city, String provState, String Country) throws IllegalArgumentException {
        if (!(setStreetNumber(streetNum) && setUnitNumebr(unitNum) && setStreetName(streetName) && setCity(city) && setProvState(provState) && setCountry(Country))) {
            throw new IllegalArgumentException("Please Check The Address Given");
        }
    }
    
    protected Boolean setStreetNumber(int number) {
        if(number > 0 ) {
            this.streetNumber = number;
            return true;
        }

        return false;
    }

    protected int getStreetNumber() {
        return this.streetNumber;
    }

    protected Boolean setUnitNumebr(String unitNumber) {
        this.unitNumber = unitNumber;

        return true;
    }

    protected String getUnitNumber() {
        return this.unitNumber;
    }

    protected Boolean setStreetName(String streetName) {
        this.streetName = streetName;

        return true;
    }

    protected String getStreetName() {
        return this.streetName;
    }

    protected Boolean setCity(String city) {
        this.city = city;
        
        return true;
    }

    protected String getCity() {
        return this.city;
    }

    protected Boolean setProvState(String provState) {
        this.provState = provState;

        return true;
    }

    protected String getProvState() {
        return this.provState;
    }

    protected Boolean setCountry(String country) {
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