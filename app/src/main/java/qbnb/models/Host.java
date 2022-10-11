package qbnb.models;

import java.util.ArrayList;

/** The object used for a host as a user Child of the user class. */
class Host extends User {
  private ArrayList<Listing> listings;
  private ArrayList<Review> reviews;
  private double accountBalance;

  protected Host() {
    this.accountBalance = 100.00;
    this.listings = new ArrayList<Listing>();
    this.reviews = new ArrayList<Review>();
  }

  protected Boolean addBalance(double balance) {
    if (balance > 0) {
      this.accountBalance += balance;
      return true;
    }

    return false;
  }

  protected Boolean chargeAccount(double amount) {
    if (amount > 0 && (accountBalance - amount) >= 0.00) {
      this.accountBalance -= amount;
      return true;
    }

    return false;
  }

  //logic to be implemented later
  protected Boolean addBooking(Listing booking) {
    return true;
  }

  //logic to be implemented later
  protected Boolean removeBooking(Listing booking) {
    return true;
  }
  
  //logic to be implemented later
  protected Boolean getBooking(Listing booking) {
    return true;
  }

  //logic to be implemented later
  protected Boolean addReview(Review review) {
    return true;
  }

  //logic to be implemented later
  protected Boolean removeReview(Review review) {
    return true;
  }
  
  //logic to be implemented later
  protected Boolean getReview(Review review) {
    return true;
  }
}
