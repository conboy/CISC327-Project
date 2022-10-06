package models;

import java.time.LocalDate;
import java.util.Date;

public class Listing {

    private int listingID;
    private String title;
    private String description;
    private double price;
    private LocalDate modificationDate;
    private int ownerID;

    /**
     * Creates and initialises a new Listing.
     * If any of the requirements as established in Sprint #2 R4 are broken, an Illegal Argument exception is thrown.
     */
    public Listing(int id, String title, String description, double price, LocalDate modDate, int owner) {
        this.listingID = id;

        if (!title.matches("[a-zA-z0-9 ]+")) throw new IllegalArgumentException("Title is not alphanumeric!");
        else if (title.charAt(0) == ' ' || title.charAt(title.length() - 1) == ' ') throw new IllegalArgumentException("Title uses space in invalid location.");
        else if (title.length() > 80) throw new IllegalArgumentException("Title is too long!");
        this.title = title;

        if (description.length() > 2000 || description.length() < 20) throw new IllegalArgumentException("Description length is outside valid range.");
        else if (title.length() >= description.length()) throw new IllegalArgumentException("Description is not longer than title!");
        this.description = description;

        if (price < 10 || price > 10000) throw new IllegalArgumentException("Price is outside of valid range!");
        this.price = price;

        if (modDate.isBefore(LocalDate.parse("2021-01-02")) || modDate.isAfter(LocalDate.parse("2025-01-02"))) throw new IllegalArgumentException("Modification date is outside of valid range!");
        this.modificationDate = modDate;

        //requirements dictate owner email shouldn't be empty - since we're using an ID system, ID should be greater than zero!
        if (owner < 1) throw new IllegalArgumentException("Owner ID is invalid!");
        this.ownerID = owner;
    }
}
