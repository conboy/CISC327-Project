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

  /** Create a default Transaction. Used in testing */
  public Transaction() {
    id = new Date().getTime();
    listingId = 12345;
    clientId = 54321;
    ammount = 100;
    start = "2022-10-25";
    end = "2022-11-04";
  }

  /**
   * Returns the id of a transaction
   *
   * @return long - id of the transaction
   */
  public long getId() {
    return id;
  }

  /**
   * ID of the transaction is set to given
   *
   * @param id - ID to be used
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the id of the listing
   *
   * @return long - listing id
   */
  public long getListingId() {
    return listingId;
  }

  /**
   * Sets the ID of the listing to given
   *
   * @param id - id to be used
   */
  public void setListingId(long id) {
    this.listingId = id;
  }

  /**
   * Returns user id of the client
   *
   * @return long - client user id
   */
  public long getClientId() {
    return clientId;
  }

  /**
   * Sets the user id of the client
   *
   * @param id - id to be used
   */
  public void setClientId(long id) {
    this.clientId = id;
  }

  /**
   * Returns the value of the transaction
   *
   * @return float - value of the transaction
   */
  public float getAmmount() {
    return ammount;
  }

  /**
   * Sets the amount (value) of the transaction
   *
   * @param ammount - transaction amount
   */
  public void setAmmount(float ammount) {
    this.ammount = ammount;
  }

  /**
   * Sets start date of the transaction
   *
   * @param start - start date
   */
  public void setStart(LocalDate start) {
    this.start = start.toString();
  }

  /**
   * Returns the start date of the transaction
   *
   * @return LocalDate - start date of the transaction
   */
  public LocalDate getStart() {
    return LocalDate.parse(start);
  }

  /**
   * Sets the end date of the transaction
   *
   * @param end - end date of the transaction
   */
  public void setEnd(LocalDate end) {
    this.end = end.toString();
  }

  /**
   * Returns the end date of the transaction
   *
   * @return LocalDate - end date of the transaction
   */
  public LocalDate getEnd() {
    return LocalDate.parse(end);
  }

  public boolean equals(Transaction trans) {
    return ((this.getId() == trans.getId())
        && (this.getListingId() == trans.getListingId())
        && (this.getClientId() == trans.getClientId())
        && (this.getAmmount() == trans.getAmmount())
        && this.getStart().equals(trans.getStart())
        && this.getEnd().equals(trans.getEnd()));
  }
}
