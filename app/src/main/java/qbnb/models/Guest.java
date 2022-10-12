package qbnb.models;

import java.util.ArrayList;

/** The object used for a guest as a user Child of the user class. */
public class Guest extends User {
  private ArrayList<Listing> bookings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  public Guest() {
    this.accountBalance = 100.00;
    this.bookings = new ArrayList<Listing>();
    this.reviews = new ArrayList<Review>();
  }

  public Boolean addBalance(double balance) {
    if (balance > 0) {
      this.accountBalance += balance;
      return true;
    }

    return false;
  }

  public Boolean chargeAccount(double amount) {
    if (amount > 0 && (accountBalance - amount) >= 0.00) {
      this.accountBalance -= amount;
      return true;
    }

    return false;
  }

  public double getBalance() {
    return this.accountBalance;
  }

  // logic to be implemented later
  public Boolean addBooking(Listing booking) {
    return true;
  }

  // logic to be implemented later
  public Boolean removeBooking(Listing booking) {
    return true;
  }

  // logic to be implemented later
  public Boolean getBooking(Listing booking) {
    return true;
  }

  // logic to be implemented later
  public Boolean addReview(Review review) {
    return true;
  }

  // logic to be implemented later
  public Boolean removeReview(Review review) {
    return true;
  }

  // logic to be implemented later
  public Boolean getReview(Review review) {
    return true;
  }
}
