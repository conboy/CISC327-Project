package qbnb;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.*;

/**
 * All tests that involve the creation of Listing elements. Not currently based in any sort of
 * testing paradigm: in the future, should implement black & white box methods; input coverage
 * testing. IDs in the following tests are formatted as 10XX, where XX is the order of the given
 * listing in the overall file.
 */
public class CreateListingTests {

  /**
   * Can optionally clear the listings.json file prior to testing as an extra testing precaution.
   * Right now, everything seems to be passing, so i've left it commented out.
   */
  @Test
  public void preTestPrep() {
    // ListingDao.clearJSON();
    Assertions.assertTrue(true);
  }

  /**
   * Tests requirement R4-1: The title of the product has to be alphanumeric-only. Additionally,
   * spaces are allowed only if it is not as prefix and suffix. REQUIRES LISTING TO THROW ERRORS
   * WITH MESSAGE 'R4-1' TO FUNCTION.
   */
  @Test
  public void alphanumericTitleTest() {
    // checking non-numeric characters - error with message R4-1 should be thrown
    String message1 = "";
    try {
      Listing x =
          new Listing(
              1001,
              "Quantum guy's £$@$!@£$$@! factory",
              "A super fun factory of nonsense anc whimsy innit!",
              100,
              LocalDate.now(),
              404);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    Assertions.assertEquals("R4-1", message1);

    // checking space characters at the beginning of title - error with message R4-1 should be
    // thrown
    String message2 = "";
    try {
      Listing x =
          new Listing(
              1002,
              " Front space land",
              "A super fun land of nonsense anc whimsy innit!",
              100,
              LocalDate.now(),
              404);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    Assertions.assertEquals("R4-1", message2);

    // checking space characters at the end of title - error with message R4-1 should be thrown
    String message3 = "";
    try {
      Listing x =
          new Listing(
              1003,
              "End space land ",
              "A super fun land of nonsense anc whimsy innit!",
              100,
              LocalDate.now(),
              404);
    } catch (IllegalArgumentException e) {
      message3 = e.getMessage();
    }
    Assertions.assertEquals("R4-1", message3);
  }

  /**
   * Tests requirement R4-2: The title of the product is no longer than 80 characters. REQUIRES
   * LISTING TO THROW ERRORS WITH MESSAGE 'R4-2' TO FUNCTION.
   */
  @Test
  public void titleLengthTest() {
    String message = "";
    try {
      String desc = "a".repeat(200); // simple way of writing an arbitrarily long description
      Listing x =
          new Listing(
              1004,
              "The Really Really Long Named Place Of Absolute Joy And Wonder And Merriment And"
                  + " Amusement which is better known as The Ultimate Funkodome of the 31st"
                  + " Century",
              desc,
              100,
              LocalDate.now(),
              404);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    Assertions.assertEquals("R4-2", message);
  }

  /**
   * Tests requirement R4-3: The description is minimum length of 20 characters and a maximum of
   * 2000 characters. REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-3' TO FUNCTION.
   */
  @Test
  public void descriptionLengthTest() {
    // testing if error is thrown for the description length < 20 case - 'R4-3' is expected error
    // message.
    String message1 = "";
    try {
      Listing x = new Listing(1005, "Funland", "It's funland!", 100, LocalDate.now(), 404);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    Assertions.assertEquals("R4-3", message1);

    // testing if error is thrown for the description length > 2000 case.
    String message2 = "";
    try {
      String desc = "bee".repeat(1000); // simple way of writing an arbitrarily long description
      Listing x = new Listing(1006, "The entire bee movie script", desc, 10, LocalDate.now(), 404);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    Assertions.assertEquals("R4-3", message2);
  }

  /**
   * Tests requirement R4-4: Description has to be longer than the product's title. REQUIRES LISTING
   * TO THROW ERRORS WITH MESSAGE 'R4-4' TO FUNCTION.
   */
  @Test
  public void descriptionLongerThanTitleTest() {
    String message = "";
    try {
      Listing x =
          new Listing(
              1007, "R4 Test Ultimate Bogland", "it's a bog, not nice", 100, LocalDate.now(), 404);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    Assertions.assertEquals("R4-4", message);
  }

  /**
   * Tests requirement R4-5: Price has to be within the range [10, 10000]. REQUIRES LISTING TO THROW
   * ERRORS WITH MESSAGE 'R4-5' TO FUNCTION.
   */
  @Test
  public void priceWithinRangeTest() {
    // testing if correct error is thrown for the price less than 10 case.
    String message1 = "";
    try {
      Listing x =
          new Listing(
              1008,
              "R5 Test Funland",
              "It's funland! Super fun, ultra R5",
              1,
              LocalDate.now(),
              404);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    Assertions.assertEquals("R4-5", message1);

    // testing if error is thrown for the price greater than 10000 case.
    String message2 = "";
    try {
      Listing x =
          new Listing(
              1009,
              "R5 Test Bunland",
              "It's fun on the bun! - Futurama robot man",
              10000000,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    Assertions.assertEquals("R4-5", message2);
  }

  /**
   * Tests requirement R4-6: modified_date must be after 2021-01-02 and before 2025-01-02. REQUIRES
   * LISTING TO THROW ERRORS WITH MESSAGE 'R4-6' TO FUNCTION.
   */
  @Test
  public void dateWithinRangeTest() {
    // testing if correct error is thrown for the Date less than 2021-01-02 case.
    String message1 = "";
    try {
      Listing x =
          new Listing(
              1010,
              "R6 Test Funland",
              "It's funland! Omega price for a couple of rice",
              100,
              LocalDate.parse("2021-01-01"),
              404);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    Assertions.assertEquals("R4-6", message1);

    // testing if correct error is thrown for the Date greater than 2025-01-02 case.
    String message2 = "";
    try {
      Listing x =
          new Listing(
              1011,
              "R6 Test Funland",
              "It's funland! I swear all these tests used to pass",
              100,
              LocalDate.parse("2025-01-03"),
              404);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    Assertions.assertEquals("R4-6", message2);
  }

  /**
   * Tests requirement R4-7A: ownerID cannot be empty. REQUIRES LISTING TO THROW ERRORS WITH MESSAGE
   * 'R4-7' TO FUNCTION.
   */
  @Test
  public void ownerNonEmptyTest() {
    String message = "";
    try {
      Listing x =
          new Listing(
              1012,
              "R7A Tests Ultimate bogland",
              "it's a bog ".repeat(10),
              100,
              LocalDate.now(),
              -1);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    Assertions.assertEquals("R4-7", message);
  }

  /**
   * Tests requirement R4-7B: ownerID has to match an ID within the database. TEST ASSUMES THAT
   * USERS ARE SAVED TO DATABASE UPON SUCCESSFUL INITIALISATION.
   */
  @Test
  public void ownerExistsTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }

    // test that using a saved ID allows for listing to be created without errors.
    // updated to check whether 1013 pre-exists during execution.
    ListingDao DAO = new ListingDao();
    Listing y = DAO.getByID(1013);
    if (y == null) {
      y =
          new Listing(
              1013, "R7 lovely place", "aa".repeat(25), 100, LocalDate.now(), u.getUserID());
    }
    Assertions.assertEquals(y.getOwnerID(), u.getUserID());

    // test that an invalid owner ID causes an error of R4-8 to be thrown.
    String message = "";
    try {
      Listing x =
          new Listing(
              1014,
              "R7 Ultimate bogland",
              "it's a bog. one could say it boggles the mind hahaha",
              100,
              LocalDate.now(),
              767676873);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    Assertions.assertEquals("R4-7", message);
  }

  /**
   * Tests requirement R4-8: A user cannot create a listing with a title that is already in-use.
   * TEST ASSUMES THAT LISTINGS ARE SAVED TO DATABASE UPON SUCCESSFUL INITIALISATION
   */
  @Test
  public void sharedTitleTest() {
    User u = new User("punch@judy.com", "bringostar", "14LoversLane!", true);
    UserDao dao = new UserDao();
    if (!dao.getAll().contains(u)) {
      dao.save(u);
    }

    // test that upon creating our initial listing, no errors occur.
    // updated to check whether 1015 pre-exists during execution.
    ListingDao DAO = new ListingDao();
    Listing y = DAO.getByID(1015);
    if (y == null) {
      y =
          new Listing(
              1015, "R8 lovely place", "aa".repeat(25), 100, LocalDate.now(), u.getUserID());
    }
    Assertions.assertNotNull(y);

    // tests if an error is thrown if the same title is used again.
    String message = "";
    System.out.println("Something fishy...");
    try {
      Listing x =
          new Listing(
              1016,
              "R8 lovely place",
              "jk, it's a bog. one could say it boggles the mind hahaha",
              100,
              LocalDate.now(),
              u.getUserID());
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    Assertions.assertEquals("R4-8", message);
  }
}
