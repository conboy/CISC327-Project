package models;

public class Listing {

  // Subclass to store different aspects of an address, as opposed to saving everything in one
  // string
  private class Address {
    private String line1;
    private String line2;
    private String city;
    private String province;
    private String postcode;

    /** Creates and initialises a new Address. TODO: Fully implement this method! */
    public Address() {}
  }

  private int ownerID;
  private Address listingAddress;
  private int bedroomCount;
  private int bedCount;
  private double bathroomCount;

  /** Creates and initialises a new Listing. TODO: Fully implement this method! */
  public Listing() {}
}
