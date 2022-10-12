package qbnb;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import qbnb.models.*;

/**
 * Runs test on the function Listing.UpdateListing() These tests assume that UpdateListing() returns
 * a boolean value, such that true is only returned if all requirements are met and listing is
 * updated. Additionally, it is assumed that for integers = 0 and Strings = null, input is ignored
 * and the specified field is not updated.
 */
public class UpdateListingTests {

  /**
   * Tests R5-1: owner ID cannot be altered through the update function. This is achieved through
   * simply updating every field we can and checking if the owner has changed!
   */
  @Test
  public void ownerIDImmutableTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    dao.save(u);
    Listing validListing =
        new Listing(
            1,
            "The lovely zone",
            "I love to live in a lovely zone! You know?",
            100,
            LocalDate.now(),
            u.getUserID());
    validListing.UpdateListing("newTitle", "very new and cool description", 101);
    assertEquals(validListing.getOwnerID(), u.getUserID());
  }

  /** Tests R5-2: price can be increased but cannot be decreased. */
  @Test
  public void priceCannotDecreaseTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    Listing validListing =
        new Listing(
            2,
            "The lovelier zone",
            "I love to live in a lovely zone! You know?",
            100,
            LocalDate.now(),
            u.getUserID());

    // testing that price can increase
    boolean valid = validListing.UpdateListing(null, null, 150);
    assertTrue(valid);

    // testing that price cannot decrease
    valid = validListing.UpdateListing(null, null, 75);
    assertFalse(valid);
  }

  /** Tests R5-3: modifiedDate changes automatically on update. */
  @Test
  public void modificationDateAutoUpdateTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    LocalDate d = LocalDate.parse("2022-01-01");
    Listing validListing =
        new Listing(
            3,
            "The loveliest zone",
            "I love to live in a lovely zone! You know?",
            100,
            d,
            u.getUserID());
    validListing.UpdateListing("newlynewTitle", null, 0);
    assertNotEquals(d, validListing.getModificationDate());
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
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    Listing validListing =
        new Listing(
            4,
            "The lovely dovely zone",
            "I love to live in a lovely zone! You know?",
            100,
            LocalDate.now(),
            u.getUserID());

    // checking non-numeric characters
    assertFalse(validListing.UpdateListing("%%%Land%%", null, 0));

    // checking space characters at the beginning of title
    assertFalse(validListing.UpdateListing(" Space Land", null, 0));

    // checking space characters at the end of title - error with message R4-1 should be thrown
    assertFalse(validListing.UpdateListing("Space Land ", null, 0));
  }

  /** Tests requirement R4-2: The title of the product is no longer than 80 characters. */
  @Test
  public void titleLengthTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    Listing validListing =
        new Listing(
            5,
            "The sucker ducker zone",
            "I love to live in a lovely zone! You know?",
            100,
            LocalDate.now(),
            u.getUserID());
    assertFalse(validListing.UpdateListing("Long".repeat(50), null, 0));
  }

  /**
   * Tests requirement R4-3: The description is minimum length of 20 characters and a maximum of
   * 2000 characters.
   */
  @Test
  public void descriptionLengthTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    Listing validListing =
        new Listing(
            6,
            "The pizza party zone",
            "I love to live in a lovely zone! You know?",
            100,
            LocalDate.now(),
            u.getUserID());

    // testing if error is thrown for the title length < 20 case.
    assertFalse(validListing.UpdateListing(null, "tiny title", 0));

    // testing if error is thrown for the title length > 2000 case.
    assertFalse(validListing.UpdateListing(null, "long description".repeat(250), 0));
  }

  /** Tests requirement R4-4: Description has to be longer than the product's title. */
  @Test
  public void descriptionLongerThanTitleTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    Listing validListing =
        new Listing(
            7,
            "The zoney zone",
            "I love to live in a lovely zone! You know?",
            100,
            LocalDate.now(),
            u.getUserID());
    assertFalse(
        validListing.UpdateListing(
            "the ultimate frizbee area of dreams and legend",
            "i suck at frizbee, what about u",
            0));
  }

  /** Tests requirement R4-5: Price has to be within the range [10, 10000]. */
  @Test
  public void priceWithinRangeTest() {
    // testing if correct error is thrown for the price < 10 case.
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    Listing validListing =
        new Listing(
            43294,
            "The hippie girls zone",
            "I met a very cute farmer/hippie at the weekend, don't judge me",
            100,
            LocalDate.now(),
            u.getUserID());

    // testing if correct error is thrown for the price < 10 case.
    assertFalse(validListing.UpdateListing(null, null, 9));

    // testing if error is thrown for the price > 10000 case.
    assertFalse(validListing.UpdateListing(null, null, 99999999));
  }

  /** Tests requirement R4-8: A user cannot create a listing with a title that is already in-use. */
  @Test
  public void sharedTitleTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }
    Listing x =
        new Listing(83237249, "sunland", "be".repeat(50), 204, LocalDate.now(), u.getUserID());
    Listing y =
        new Listing(843294, "loveplace", "ba".repeat(25), 100, LocalDate.now(), u.getUserID());
    assertFalse(x.UpdateListing("loveplace", null, 900));
  }
}
