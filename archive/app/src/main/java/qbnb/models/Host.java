package qbnb.models;

import java.util.ArrayList;

/** The object used for a host as a user Child of the user class. */
class Host extends User {
  private ArrayList<Listing> listings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  public Host() {
    this.accountBalance = 100.00;
    this.listings = new ArrayList<Listing>();
    this.reviews = new ArrayList<Review>();
  }

  /**
   * Adds a balance to the account
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
   * Charges and deducts from an account
   *
   * @param amount - amount to be deducted
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
   * Adds booking to list of bookings
   *
   * @param booking - booking to be added
   * @return Boolean
   */
  public Boolean addBooking(Listing booking) {
    this.listings.add(booking);
    return true;
  }

  /**
   * removes a booking from the list of bookings
   *
   * @param booking - booking to be removed
   * @return Boolean
   */
  public Boolean removeBooking(Listing booking) {
    this.listings.remove(booking);
    return true;
  }

  /**
   * Add a review to the list of reviews
   *
   * @param review - review to be added
   * @return Boolean
   */
  public Boolean addReview(Review review) {
    this.reviews.add(review);
    return true;
  }

  /**
   * Removes a review from the list of reviews
   *
   * @param review - review to be removed
   * @return Boolean
   */
  public Boolean removeReview(Review review) {
    this.reviews.remove(review);
    return true;
  }
}
