package models;

import java.util.Date;

public class Listing {

    private int listingID;
    private String title;
    private String description;
    private double price;
    private Date modificationDate;
    private int ownerID;

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
