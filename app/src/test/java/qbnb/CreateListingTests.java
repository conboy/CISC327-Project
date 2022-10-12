package qbnb;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import qbnb.models.*;

/**
 * Runs all tests that correspond to the R4 requirements in sprint 1. Each time a Listing creation
 * fails due to a specific criteria not being met, this code expects an error to be thrown with a
 * message corresponding to the requirement broken. ALL TESTS SHOULD PASS!
 */
public class CreateListingTests {

  /**
   * Tests requirement R4-1: The title of the product has to be alphanumeric-only. Additionally,
   * spaces are allowed only if it is not as prefix and suffix. REQUIRES LISTING TO THROW ERRORS
   * WITH MESSAGE 'R4-1' TO FUNCTION.
   */
  @Test
  public void alphanumericTitleTest() {
    // checking non-numeric characters - error with message R4-1 should be thrown
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    String message1 = "";
    try {
      Listing x =
          new Listing(
              1,
              "Quantum guy's $@?$!@$($@! factory",
              "A super fun factory of nonsense anc whimsy innit!",
              100,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    assertEquals("R4-1", message1);

    // checking space characters at the beginning of title - error with message R4-1 should be
    // thrown
    String message2 = "";
    try {
      Listing x =
          new Listing(
              2,
              "     Front space land",
              "A super fun land of nonsense anc whimsy innit!",
              100,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    assertEquals("R4-1", message2);

    // checking space characters at the end of title - error with message R4-1 should be thrown
    String message3 = "";
    try {
      Listing x =
          new Listing(
              3,
              "End space land    ",
              "A super fun land of nonsense anc whimsy innit!",
              100,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message3 = e.getMessage();
    }
    assertEquals("R4-1", message3);
  }

  /**
   * Tests requirement R4-2: The title of the product is no longer than 80 characters. REQUIRES
   * LISTING TO THROW ERRORS WITH MESSAGE 'R4-2' TO FUNCTION.
   */
  @Test
  public void titleLengthTest() {
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    String message = "";
    try {
      String desc = "a".repeat(200); // simple way of writing an arbitrarily long description
      Listing x =
          new Listing(
              4,
              "The Really Really Long Named Place Of Absolute Joy And Wonder And Merriment And Amusement which is better known as The Ultimate Funkodome of the 31st Century",
              desc,
              100,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    assertEquals("R4-2", message);
  }

  /**
   * Tests requirement R4-3: The description is minimum length of 20 characters and a maximum of
   * 2000 characters. REQUIRES LISTING TO THROW ERRORS WITH MESSAGE 'R4-3' TO FUNCTION.
   */
  @Test
  public void descriptionLengthTest() {
    // testing if error is thrown for the title length < 20 case - 'R4-3' is expected error message.
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    String message1 = "";
    try {
      Listing x = new Listing(5, "Funland", "It's funland!", 100, LocalDate.now(), 1);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    assertEquals("R4-3", message1);

    // testing if error is thrown for the title length > 2000 case.
    String message2 = "";
    try {
      String desc = "bee".repeat(1000); // simple way of writing an arbitrarily long description
      Listing x = new Listing(6, "The entire bee movie script", desc, 10, LocalDate.now(), 1);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    assertEquals("R4-3", message2);
  }

  /**
   * Tests requirement R4-4: Description has to be longer than the product's title. REQUIRES LISTING
   * TO THROW ERRORS WITH MESSAGE 'R4-4' TO FUNCTION.
   */
  @Test
  public void descriptionLongerThanTitleTest() {
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    String message = "";
    try {
      Listing x =
          new Listing(
              7,
              "Ultimate bogland of supreme boggieness innit",
              "it's a bog, just sucks tbh",
              100,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    assertEquals("R4-4", message);
  }

  /**
   * Tests requirement R4-5: Price has to be within the range [10, 10000]. REQUIRES LISTING TO THROW
   * ERRORS WITH MESSAGE 'R4-5' TO FUNCTION.
   */
  @Test
  public void priceWithinRangeTest() {
    // testing if correct error is thrown for the price < 10 case.
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    String message1 = "";
    try {
      Listing x =
          new Listing(8, "Funland", "It's funland! Fun fun fun, mega good!", 1, LocalDate.now(), 1);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    assertEquals("R4-5", message1);

    // testing if error is thrown for the price > 10000 case.
    String message2 = "";
    try {
      Listing x =
          new Listing(
              9,
              "Bunland",
              "It's fun on the bun! - Futurama robot man",
              10000000,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    assertEquals("R4-5", message2);
  }

  /**
   * Tests requirement R4-6: modified_date must be after 2021-01-02 and before 2025-01-02. REQUIRES
   * LISTING TO THROW ERRORS WITH MESSAGE 'R4-6' TO FUNCTION.
   */
  @Test
  public void dateWithinRangeTest() {
    // testing if correct error is thrown for the Date < 2021-01-02 case.
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    String message1 = "";
    try {
      Listing x =
          new Listing(
              10,
              "Funland",
              "It's funland! I have to update all of these because the description is too short!",
              100,
              LocalDate.parse("2021-01-01"),
              1);
    } catch (IllegalArgumentException e) {
      message1 = e.getMessage();
    }
    assertEquals("R4-6", message1);

    // testing if correct error is thrown for the Date > 2025-01-02 case.
    String message2 = "";
    try {
      Listing x =
          new Listing(
              11,
              "Funland",
              "It's funland! The land of descriptions longer than 20 characters!",
              10,
              LocalDate.parse("2025-01-03"),
              1);
    } catch (IllegalArgumentException e) {
      message2 = e.getMessage();
    }
    assertEquals("R4-6", message2);
  }

  /**
   * Tests requirement R4-7A: ownerID cannot be empty. REQUIRES LISTING TO THROW ERRORS WITH MESSAGE
   * 'R4-7' TO FUNCTION.
   */
  @Test
  public void ownerNonEmptyTest() {
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    String message = "";
    try {
      Listing x =
          new Listing(
              12,
              "Ultimate bogland",
              "it's a bog. one could say it boggles the mind hahaha",
              100,
              LocalDate.now(),
              -1);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    assertEquals("R4-7", message);
  }

  /**
   * Tests requirement R4-7B: ownerID has to match an ID within the database. TEST ASSUMES THAT
   * USERS ARE SAVED TO DATABASE UPON SUCCESSFUL INITIALISATION.
   */
  @Test
  public void ownerExistsTest() {
    // test that using a saved ID allows for listing to be created without errors.
    User u = new User(4053, "bringostar", "14LoversLane!", "punch@judy.com");
    Listing y = new Listing(13, "lovdplace", "a".repeat(25), 100, LocalDate.now(), 4053);
    assertEquals(y.getOwnerID(), 4053);

    // test that an invalid owner ID causes an error of R4-8 to be thrown.
    String message = "";
    try {
      Listing x =
          new Listing(
              20,
              "Ultimate bogland",
              "it's a bog. one could say it boggles the mind hahaha",
              100,
              LocalDate.now(),
              20);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    assertEquals("R4-7", message);
  }

  /**
   * Tests requirement R4-8: A user cannot create a listing with a title that is already in-use.
   * TEST ASSUMES THAT LISTINGS ARE SAVED TO DATABASE UPON SUCCESSFUL INITIALISATION
   */
  @Test
  public void sharedTitleTest() {
    // tests that the first time a title is used, no errors are thrown.
    User u = new User(1, "bringostar", "14LoversLane!", "punch@judy.com");
    Listing y = new Listing(100, "loveplace", "a".repeat(25), 100, LocalDate.now(), 1);
    assertEquals(y.getOwnerID(), 1);

    // tests if an error is thrown if the same title is used again.
    String message = "";
    try {
      Listing x =
          new Listing(
              200,
              "loveplace",
              "it's a bog. one could say it boggles the mind hahaha",
              100,
              LocalDate.now(),
              1);
    } catch (IllegalArgumentException e) {
      message = e.getMessage();
    }
    assertEquals("R4-8", message);
  }
}