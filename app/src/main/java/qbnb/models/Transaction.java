package qbnb.models;

import java.time.LocalDate;
import java.util.Date;

/** Acts as a record of booking and payment when a user books a listing. */
public class Transaction {

  private long id;
  private long listingId;
  private long clientId;
  private float ammount;
  private String start;
  private String end;

  /**
   * Create a Transaction with the listing and client Ids as well as the price and dates for the
   * booking
   */
  public Transaction(long listing, long client, float price, LocalDate start, LocalDate end) {
    id = new Date().getTime();
    listingId = listing;
    clientId = client;
    ammount = price;
    // start & end are strings to make serialization work. They are converted
    // from and to LocalDate when getting and setting respectively.
    this.start = start.toString();
    this.end = end.toString();
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

  public void setStart(LocalDate start) {
    this.start = start.toString();
  }

  public LocalDate getStart() {
    return LocalDate.parse(start);
  }

  public void setEnd(LocalDate end) {
    this.end = end.toString();
  }

  public LocalDate getEnd() {
    return LocalDate.parse(end);
  }
}
