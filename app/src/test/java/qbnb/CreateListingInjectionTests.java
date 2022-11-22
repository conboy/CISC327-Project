package qbnb;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qbnb.models.Listing;
import qbnb.models.daos.ListingDao;

public class CreateListingInjectionTests {

  @BeforeAll
  static void setup() {
    ListingDao.deserialize().clearJSON();
  }

  /**
   * Convert string to path with correct formatting for system. Shamelessly stolen from DAO.java :)
   */
  private String formatForSystem(String path) {
    String formattedPath;
    if (System.getProperty("os.name").contains("Windows")) {
      formattedPath = AppConf.WIN_PROJECT_PATH + path;
    } else {
      formattedPath = AppConf.PROJECT_PATH + path;
    }
    return formattedPath;
  }

  @Test
  public void titleInjectionTest() throws FileNotFoundException {
    String osCheck = System.getProperty("os.name").split(" ")[0];
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      File x = new File(formatForSystem("/db/Generic_SQLI.txt"));
      Scanner reader = new Scanner(x);
      int id = 10001;

      while (reader.hasNextLine()) {
        String payload = reader.nextLine();
        System.out.println(payload);
        String prediction = null;
        String error = null;

        // predict if the payload will throw an error due to current input sanitization.
        // if the prediction and the actual result do not match, it could be a sign of injection
        // succeeding.
        if (!payload.matches("[a-zA-z0-9 ]+")) prediction = "R4-1";
        else if (payload.charAt(0) == ' ' || payload.charAt(payload.length() - 1) == ' ')
          prediction = "R4-1";
        else if (payload.length() > 80) prediction = "R4-2";
        try {
          Listing pListing =
              new Listing(
                  id,
                  payload,
                  "A really long description to ensure that R4-4 does not occur :) - ".repeat(5),
                  100,
                  LocalDate.now(),
                  404);
        } catch (IllegalArgumentException e) {
          error = e.getMessage();
        }
        Assertions.assertEquals(prediction, error);
        id++;
      }
    } else Assertions.assertTrue(true);
  }

  @Test
  public void descriptionInjectionTest() throws FileNotFoundException {
    String osCheck = System.getProperty("os.name").split(" ")[0];
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      File x = new File(formatForSystem("/db/Generic_SQLI.txt"));
      Scanner reader = new Scanner(x);
      int id = 20001;

      while (reader.hasNextLine()) {
        String payload = reader.nextLine();
        System.out.println(payload);
        String prediction = null;
        String error = null;

        // predict if the payload will throw an error due to current input sanitization.
        if (payload.length() > 2000 || payload.length() < 20) prediction = "R4-3";
        else if (Integer.toString(id).length() >= payload.length()) prediction = "R4-4";
        try {
          Listing pListing =
              new Listing(id, Integer.toString(id), payload, 100, LocalDate.now(), 404);
        } catch (IllegalArgumentException e) {
          error = e.getMessage();
        }
        Assertions.assertEquals(prediction, error);
        id++;
      }
    } else Assertions.assertTrue(true);
  }

  @Test
  public void priceInjectionTest() throws FileNotFoundException {
    String osCheck = System.getProperty("os.name").split(" ")[0];
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      File x = new File(formatForSystem("/db/Generic_SQLI.txt"));
      Scanner reader = new Scanner(x);
      int id = 30001;

      while (reader.hasNextLine()) {
        String payload = reader.nextLine();
        System.out.println(payload);
        String prediction = null;
        String error = null;

        // predict if the payload will throw an error due to current input sanitization.
        try {
          int payint = Integer.parseInt(payload);
          if (payint < 10 || payint > 10000) prediction = "R4-5";
        } catch (Exception e) {
          prediction = "NaN";
        }

        try {
          Listing pListing =
              new Listing(
                  id,
                  Integer.toString(id),
                  "A kinda short description because the title is now a 5 digit integer :)",
                  Integer.parseInt(payload),
                  LocalDate.now(),
                  404);
        } catch (NumberFormatException e) {
          error = "NaN";
        } catch (IllegalArgumentException e) {
          error = e.getMessage();
        }

        Assertions.assertEquals(prediction, error);
        id++;
      }
    } else Assertions.assertTrue(true);
  }
}
