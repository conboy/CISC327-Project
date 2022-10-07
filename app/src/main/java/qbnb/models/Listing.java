package qbnb.models;

import org.junit.Assert;
import org.junit.Test;
import qbnb.models.ListingDao;

import java.time.LocalDate;

//TODO: implement database functionality!

/**
 * The class that handles Listings - objects which represent individual properties in reality.
 * Users browse Listings, and create bookings through them if they wish to stay at their location.
 * Each listing is linked to its owner via an ID, in order to simplify ownership and transactions.
 */
public class Listing {

    //The ID of the listing object - used to identify between individual listings.
    private int listingID;

    //The title of the listing that is shown to users. Alphanumeric only.
    private String title;

    //The description of the listing that is shown to users prior to booking. Must be longer than the title!
    private String description;

    //The price of the listing per night. Can range from 10$ to 10,000$.
    private double price;

    //The date of when the listing was last modified by its owner. Does not accept dates past 2025.
    private LocalDate modificationDate;

    //The ID of the user who created the listing and owns the property. Used to reference the owner's info when necessary.
    private int ownerID;

    /**
     * Creates and initialises a new Listing.
     * If any of the requirements as established in Sprint #2 R4 are broken, an Illegal Argument exception is thrown.
     */
    public Listing(int id, String title, String description, double price, LocalDate modDate, int owner) {
        this.listingID = id;

        if (!title.matches("[a-zA-z0-9 ]+")) throw new IllegalArgumentException("R4-1");
        else if (title.charAt(0) == ' ' || title.charAt(title.length() - 1) == ' ') throw new IllegalArgumentException("R4-1");
        else if (title.length() > 80) throw new IllegalArgumentException("R4-2");
        this.title = title;

        if (description.length() > 2000 || description.length() < 20) throw new IllegalArgumentException("R4-3");
        else if (title.length() >= description.length()) throw new IllegalArgumentException("R4-4");
        this.description = description;

        if (price < 10 || price > 10000) throw new IllegalArgumentException("R4-5");
        this.price = price;

        //not sure if this check is necessary - upon creation, shouldn't modification date be set to the current date?
        if (modDate.isBefore(LocalDate.parse("2021-01-02")) || modDate.isAfter(LocalDate.parse("2025-01-02"))) throw new IllegalArgumentException("R4-6");
        this.modificationDate = modDate;

        //requirements dictate owner email shouldn't be empty - since we're using an ID system, ID should be greater than zero!
        if (owner < 1) throw new IllegalArgumentException("R4-7");
        this.ownerID = owner;

        ListingDao DAO = new ListingDao();
        DAO.save(this);
    }

    /**
     * Updates various attributes of a given instance of Listing.
     * If attributes are less than 1 or Null they shall be ignored.
     * Adheres to the requirements as specified in Sprint #2 R5.
     * SHOULD ONLY BE CALLED VIA ListingDao.update() !!! The Dao interface is set up such that it cannot
     * @param newID The new ID number for the listing. Honestly don't think this should be alterable either tbh -> decide later.
     * @param newTitle The new title for the listing. Must adhere to the R4 guidelines.
     * @param newDesc The new description for the listing. Must adhere to the R4 guidelines.
     * @param newPrice The new price for the listing. Must adhere to the R4 guidelines. Cannot be lower than the current price :)
     * @return a boolean value representing the success of the update. If false, no attributes will have been updated.
     */
    public boolean UpdateListing (int newID, String newTitle, String newDesc, double newPrice) {
        
        if (newTitle != null) {
            if (!newTitle.matches("[a-zA-z0-9 ]+")) return false;
            else if (newTitle.charAt(0) == ' ' || newTitle.charAt(newTitle.length() - 1) == ' ') return false;
            else if (newTitle.length() > 80) return false;
            this.title = newTitle;
        }

        if (newDesc != null) {
            if (newDesc.length() > 2000 || newDesc.length() < 20) return false;
            else if (title.length() >= newDesc.length()) return false;
        }

        if (newPrice > 0) {
            if (newPrice < 10 || newPrice > 10000) return false;
            if (newPrice < price) return false;
        }

        //if the function is still executing, we can be sure all attributes fit requirements -> update their values now!
        if (newID > 0) this.listingID = newID;
        if (newTitle != null) this.title = newTitle;
        if (newDesc != null) this.description = newDesc;
        if (newPrice > 0) this.price = newPrice;
        this.modificationDate = LocalDate.now();
        ListingDao DAO = new ListingDao();
        DAO.update(this);
        return true;
    }

    /// == GETTER METHODS == ///

    /** Returns listing ID */
    public int getListingID () {
        return listingID;
    }

    /** Returns title */
    public String getTitle () {
        return title;
    }

    /** Returns description */
    public String getDescription () {
        return description;
    }

    /** Returns price */
    public double getPrice () {
        return price;
    }

    /** Returns modification date */
    public LocalDate getModificationDate () {
        return modificationDate;
    }

    /** Returns owner ID */
    public int getOwnerID () {
        return ownerID;
    }
}