package qbnb;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.*;
import qbnb.models.daos.ListingDao;

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
    ListingDao DAO = ListingDao.deserialize();
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
    ListingDao DAO = ListingDao.deserialize();
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
    ListingDao DAO = ListingDao.deserialize();
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
    // checking non-numeric characters
    ListingDao DAO = new ListingDao();
    Listing validListing = DAO.getByID(2004);
    if (validListing == null) {
      validListing =
          new Listing(
              2004,
              "R1 lovely dovely zone",
              "I love to live in a lovely zone! You know?",
              100,
              LocalDate.now(),
              404);
    }

    // checking non-numeric characters
    Assertions.assertFalse(validListing.UpdateListing("%%%Land%%", null, 0));

    // checking space characters at the beginning of title
    Assertions.assertFalse(validListing.UpdateListing(" Space Land", null, 0));

    // checking space characters at the end of title - error with message R4-1 should be thrown
    Assertions.assertFalse(validListing.UpdateListing("Space Land ", null, 0));
  }

  /** Tests requirement R4-2: The title of the product is no longer than 80 characters. */
  @Test
  public void titleLengthTest() {
    ListingDao DAO = ListingDao.deserialize();
    Listing validListing = DAO.getByID(2005);
    if (validListing == null) {
      validListing =
          new Listing(
              2005,
              "R2 sucker ducker zone",
              "I love to live in a sucker ducker zone! You know?",
              100,
              LocalDate.now(),
              404);
    }
    Assertions.assertFalse(validListing.UpdateListing("Long".repeat(50), null, 0));
  }

  /**
   * Tests requirement R4-3: The description is minimum length of 20 characters and a maximum of
   * 2000 characters.
   */
  @Test
  public void descriptionLengthTest() {
    ListingDao DAO = ListingDao.deserialize();
    Listing validListing = DAO.getByID(2006);
    if (validListing == null) {
      validListing =
          new Listing(
              2006,
              "R3 pizza party zone",
              "I really don't believe that prince, you know?",
              100,
              LocalDate.now(),
              404);
    }

    // testing if error is thrown for the title length < 20 case.
    Assertions.assertFalse(validListing.UpdateListing(null, "tiny title", 0));

    // testing if error is thrown for the title length > 2000 case.
    Assertions.assertFalse(validListing.UpdateListing(null, "long description".repeat(250), 0));
  }

  /** Tests requirement R4-4: Description has to be longer than the product's title. */
  @Test
  public void descriptionLongerThanTitleTest() {
    ListingDao DAO = ListingDao.deserialize();
    Listing validListing = DAO.getByID(2007);
    if (validListing == null) {
      validListing =
          new Listing(
              2007,
              "R4 The zoney zone",
              "I love to live in a zoned zone! You know?",
              100,
              LocalDate.now(),
              404);
    }
    Assertions.assertFalse(
        validListing.UpdateListing(
            "R4 the ultimate frizbee area of dreams and legend",
            "i suck at frizbee, what about u",
            0));
  }

  /** Tests requirement R4-5: Price has to be within the range [10, 10000]. */
  @Test
  public void priceWithinRangeTest() {
    ListingDao DAO = ListingDao.deserialize();
    Listing validListing = DAO.getByID(2008);
    if (validListing == null) {
      validListing =
          new Listing(
              2008,
              "R5 The hippie girls zone",
              "I met a very cute farmer/hippie at the weekend, don't judge me",
              100,
              LocalDate.now(),
              404);
    }

    // testing if correct error is thrown for the price < 10 case.
    Assertions.assertFalse(validListing.UpdateListing(null, null, 9));

    // testing if error is thrown for the price > 10000 case.
    Assertions.assertFalse(validListing.UpdateListing(null, null, 99999999));
  }

  /** Tests requirement R4-8: A user cannot create a listing with a title that is already in-use. */
  @Test
  public void sharedTitleTest() {
    ListingDao DAO = ListingDao.deserialize();
    Listing x = DAO.getByID(2009);
    if (x == null) {
      x = new Listing(2009, "R8 sunland", "be".repeat(50), 204, LocalDate.now(), 404);
    }

    Listing y = DAO.getByID(2010);
    if (y == null) {
      y = new Listing(2010, "R8 loveplace", "ba".repeat(25), 100, LocalDate.now(), 404);
    }
    Assertions.assertFalse(x.UpdateListing("R8 loveplace", null, 900));
  }
}
