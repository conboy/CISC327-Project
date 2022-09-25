package models;

import java.util.Date;

/** Acts as a record of booking and payment when a user books a listing. */
public class Transaction {

  private long id;
  private long listingId;
  private long clientId;
  private float ammount;
  private Booking booking;

  /**
   * Create a Transaction with the listing and client Ids as well as the price and dates for the
   * booking
   */
  public Transaction(long listing, long client, float price, Booking times) {
    id = new Date().getTime();
    listingId = listing;
    clientId = client;
    ammount = price;
    booking = times;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getListingId() {
    return listingId;
  }

  public void setListingId(long id) {
    this.listingId = id;
  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long id) {
    this.clientId = id;
  }

  public float getAmmount() {
    return ammount;
  }

  public void setAmmount(float ammount) {
    this.ammount = ammount;
  }

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }
}

/** Contains the time-period for a booking related to a transaction */
class Booking {

  private Date start;
  private Date end;

  /** Create a booking with the year, month, and day of 2 dates */
  public Booking(int y1, int m1, int d1, int y2, int m2, int d2) {

    start = new Date(y1, m1, d1);
    end = new Date(y2, m2, d2);
  }

  public Date getStart() {
    return start;
  }

  public void setStart(int y, int m, int d) {
    this.start = new Date(y, m, d);
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(int y, int m, int d) {
    this.end = new Date(y, m, d);
  }
}
