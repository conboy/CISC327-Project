package qbnb.models;

import java.time.LocalDate;

// TEMPORARY CLASS TO HELP WITH BUILD ERRORS
public class Listing {

  private int listingID;
  private String title;
  private String desc;
  private double price;
  private LocalDate md;
  private int ownerID;

  /** Creates and initialises a new Listing. TODO: Fully implement this method! */
  public Listing(int id, String t, String d, double pr, LocalDate date, int owner) {
    listingID = id;
    title = t;
    desc = d;
    price = pr;
    md = date;
    ownerID = owner;
  }

  /**
   * Temporary function to assist with UpdateListingTests - full code implemented in feature/327-26
   */
  public boolean UpdateListing(String newTitle, String newDesc, double newPrice) {
    return false;
  }

  public int getOwnerID() {
    return ownerID;
  }

  public LocalDate getModificaionDate() {
    return md;
  }
}
