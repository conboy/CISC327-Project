package models;

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
  }
}
