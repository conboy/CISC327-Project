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

  /**
   * Adds a specified balance to the account
   * 
   * @param balance - balance to be added
   * @return Boolean
   */
  public Boolean addBalance(double balance) {
    if (balance > 0) {
      this.accountBalance += balance;
      return true;
    }

    return false;
  }

  /**
   * Charges an account a specified balance
   * 
   * @param amount - balance to be charged
   * @return Boolean
   */
  public Boolean chargeAccount(double amount) {
    if (amount > 0 && (accountBalance - amount) >= 0.00) {
      this.accountBalance -= amount;
      return true;
    }

    return false;
  }

  /**
   * Returns the balance of the account
   * 
   * @return double
   */
  public double getBalance() {
    return this.accountBalance;
  }

  /**
   * Adds a booking to the list of bookings
   * 
   * @TODO implement later
   * @param booking - booking to be added
   * @return Boolean
   */
  public Boolean addBooking(Listing booking) {
    return true;
  }

  /**
   * Removes a booking from the list of bookings
   * 
   * @TODO implement later
   * @param booking - booking to be removed
   * @return Boolean
   */
  public Boolean removeBooking(Listing booking) {
    return true;
  }

  /**
   * Returns a specified booking from the list of bookings
   * 
   * @TODO implement later
   * @param booking - booking to be returned
   * @return Boolean
   */
  public Boolean getBooking(Listing booking) {
    return true;
  }

  /**
   * Adds a review to the list of reviews
   * 
   * @TODO implement later
   * @param review - review to be added
   * @return Boolean
   */
  public Boolean addReview(Review review) {
    return true;
  }

  /**
   * Removes a review from the list of reviews
   * 
   * @TODO implement later
   * @param review - review to be removed
   * @return Boolean
   */
  public Boolean removeReview(Review review) {
    return true;
  }

  /**
   * retireves review from list of reviews
   * 
   * @TODO implement later
   * @param review
   * @return Boolean
   */
  public Boolean getReview(Review review) {
    return true;
  }
}
