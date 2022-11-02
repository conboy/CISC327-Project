package qbnb;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.*;

/**
 * Runs test on the function Listing.UpdateListing() These tests assume that UpdateListing() returns
 * a boolean value, such that true is only returned if all requirements are met and listing is
 * updated. Additionally, it is assumed that for integers = 0 and Strings = null, input is ignored
 * and the specified field is not updated.
 *
 * <p>All listings created in these tests will follow the ID format of 20XX, where XX is the
 * numerical order in which the tests are created. Each listing is searched for prior to creation as
 * to avoid errors with overlapping IDs/titles.
 */
public class UpdateListingTests {

  /**
   * Tests R5-1: owner ID cannot be altered through the update function. This is achieved through
   * simply updating every field we can and checking if the owner has changed!
   */
  @Test
  public void ownerIDImmutableTest() {
    ListingDao DAO = new ListingDao();
    Listing validListing = DAO.getByID(2001L);
    if (validListing == null) {
      validListing =
          new Listing(
              2001,
              "IDImmutable The lovely zone",
              "I love to live in a lovely zone! You know?",
              100,
              LocalDate.now(),
              404);
    }
    validListing.UpdateListing("IDImmutable newTitle", "very new and cool description", 101);
    Assertions.assertEquals(validListing.getOwnerID(), 404);
  }

  /** Tests R5-2: price can be increased but cannot be decreased. */
  @Test
  public void priceCannotDecreaseTest() {
    ListingDao DAO = new ListingDao();
    Listing validListing = DAO.getByID(2002L);
    if (validListing == null) {
      validListing =
          new Listing(
              2002,
              "NoDecrease The lovelier zone",
              "I love to live in a lovely zone! You know?",
              100,
              LocalDate.now(),
              404);
    }
    // testing that price can increase
    boolean valid = validListing.UpdateListing(null, null, 150);
    Assertions.assertTrue(valid);

    // testing that price cannot decrease
    valid = validListing.UpdateListing(null, null, 75);
    Assertions.assertFalse(valid);
  }

  /** Tests R5-3: modifiedDate changes automatically on update. */
  @Test
  public void modificationDateAutoUpdateTest() {
    LocalDate d = LocalDate.parse("2022-01-01");
    ListingDao DAO = new ListingDao();
    Listing validListing = DAO.getByID(2003);
    if (validListing == null) {
      validListing =
          new Listing(
              2003,
              "The loveliest zone",
              "I love to live in a lovely zone! You know?",
              100,
              d,
              404);
    }
    validListing.UpdateListing("AutoDate newlynewTitle", null, 0);
    Assertions.assertNotEquals(d, validListing.getModificationDate());
  }

  // R5-4: Make sure all the R4 criteria are also followed when updating!!
  // The following tests are the only possible testable criteria for R4 on this function.

  /**
   * Tests requirement R4-1: The title of the product has to be alphanumeric-only. Additionally,
   * spaces are allowed only if it is not as prefix and suffix.
   */
  @Test
  public void alphanumericTitleTest() {
    // Paste in updated test
    Assertions.assertTrue(true);
  }

  /** Tests requirement R4-2: The title of the product is no longer than 80 characters. */
  @Test
  public void titleLengthTest() {
    // Paste in updated test
    Assertions.assertTrue(true);
  }

  /**
   * Tests requirement R4-3: The description is minimum length of 20 characters and a maximum of
   * 2000 characters.
   */
  @Test
  public void descriptionLengthTest() {
    // Paste in updated test
    Assertions.assertTrue(true);
  }

  /** Tests requirement R4-4: Description has to be longer than the product's title. */
  @Test
  public void descriptionLongerThanTitleTest() {
    // Paste in updated test
    Assertions.assertTrue(true);
  }

  /** Tests requirement R4-5: Price has to be within the range [10, 10000]. */
  @Test
  public void priceWithinRangeTest() {
    // Paste in updated test
    Assertions.assertTrue(true);
  }

  /** Tests requirement R4-8: A user cannot create a listing with a title that is already in-use. */
  @Test
  public void sharedTitleTest() {
    // Paste in updated test
    Assertions.assertTrue(true);
  }
}
