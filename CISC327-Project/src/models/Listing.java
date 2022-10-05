package models;

import java.util.Date;

public class Listing {

    //Subclass to store different aspects of an address, as opposed to saving everything in one string
    private class Address {
        private String line1;
        private String line2;
        private String city;
        private String province;
        private String postcode;

        /**
         * Creates and initialises a new Address.
         * TODO: Fully implement this method!
         */
        public Address() {

        }
    }

    private int listingID;
    private String title;
    private String description;
    private double price;
    private Date modificationDate;
    private int ownerID;

    /* Currently unused variables - may be implemented in the future? */
    //private Address listingAddress;

    /**
     * Creates and initialises a new Listing.
     * TODO: Fully implement this method!
     */
    public Listing(int id, String title, String description, double price, Date modDate, int owner) {
        this.listingID = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.modificationDate = modDate;
        this.ownerID = owner;
    }
}
