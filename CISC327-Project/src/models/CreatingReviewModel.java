package models;

import java.util.Date; // For formating dates

/** Support for user reviews on stays. */
public class CreatingReviewModel {
  private int reviewID;
  private int reviewerID;
  private int listingID;
  private Date reviewDate;
  private int reviewRating;
  private String reviewBody;

  public CreatingReviewModel() {}
}
