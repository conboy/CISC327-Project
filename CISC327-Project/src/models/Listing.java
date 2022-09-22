package models;

public class Listing {

    private class Address {
        public String line1;
        public String line2;
        public String city;
        public String province;
        public String postcode;

        private Address() {

        }
    }

    private int ownerID;
    private Address listingAddress;
    private int bedroomCount;
    private int bedCount;
    private double bathroomCount;

    public Listing() {

    }
}
