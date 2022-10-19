package qbnb.models;

import java.util.Date;
import java.time.LocalDate;
import org.javatuples.Pair;

/** Acts as a record of booking and payment when a user books a listing. */
public class Transaction {

  private long id;
  private long listingId;
  private long clientId;
  private float ammount;
  private Pair<LocalDate, LocalDate> booking;

  /**
   * Create a Transaction with the listing and client Ids as well as the price and dates for the
   * booking
   */
  public Transaction(long listing, long client, float price, LocalDate start, LocalDate end) {
    id = new Date().getTime();
    listingId = listing;
    clientId = client;
    ammount = price;
    booking = Pair.with(start, end);
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

  public Pair<LocalDate, LocalDate> getBooking() {
    return booking;
  }

  public void setBooking(LocalDate start, LocalDate end) {
    this.booking = booking;
  }
}
