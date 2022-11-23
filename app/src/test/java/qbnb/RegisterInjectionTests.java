package qbnb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;

/** Runs test on the Register function against the SQL injection file db/Generic_SQLI.txt */
public class RegisterInjectionTests {
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

  /**
   * Runs test on the email parameter in the Register function against the SQL injection file
   * db/Generic_SQLI.txt None of the lines in the SQL injection file are a valid email so the
   * prediction is always going to be improper data
   */
  @Test
  public void emailInjectionTest() throws FileNotFoundException {
    File x = new File(formatForSystem("/db/Generic_SQLI.txt"));
    Scanner reader = new Scanner(x);

    while (reader.hasNextLine()) {
      String payload = reader.nextLine();
      System.out.printf("\"%s\": %s\n", payload, payload.getClass());
      String prediction = "Improper data has been provided to constuct a user";
      String error = null;

      try {
        User pUser = new User(payload, "JohnDoe", "JohnDoe123#");
      } catch (IllegalArgumentException e) {
        error = e.getMessage();
      }
      System.out.println(error);
      Assertions.assertEquals(prediction, error);
    }
  }
  /**
   * Runs test on the user parameter in the Register function against the SQL injection file
   * db/Generic_SQLI.txt
   */
  @Test
  public void userInjectionTest() throws FileNotFoundException {
    File x = new File(formatForSystem("/db/Generic_SQLI.txt"));
    Scanner reader = new Scanner(x);

    while (reader.hasNextLine()) {
      String payload = reader.nextLine();
      System.out.printf("\"%s\" %s\n", payload, payload.getClass());
      String prediction = "Improper data has been provided to constuct a user";
      String error = null;

      // predict if the payload will throw an error due to current input sanitization.
      // if the prediction and the actual result do not match, it could be a sign of injection
      // succeeding.
      if (payload.equals(payload.trim()) && (2 <= payload.length()) && (20 >= payload.length())) {
        if (payload.matches("[A-Za-z0-9]+")) {
          prediction = null;
        }
      }
      try {
        User pUser = new User("johndoe@gmail.com", payload, "JohnDoe123#");
      } catch (IllegalArgumentException e) {
        error = e.getMessage();
      }
      System.out.println(error);
      Assertions.assertEquals(prediction, error);
    }
  }
  /**
   * Runs test on the password parameter in the Register function against the SQL injection file
   * db/Generic_SQLI.txt
   */
  @Test
  public void passwordInjectionTest() throws FileNotFoundException {
    File x = new File(formatForSystem("/db/Generic_SQLI.txt"));
    Scanner reader = new Scanner(x);
    while (reader.hasNextLine()) {
      String payload = reader.nextLine();
      System.out.printf("\"%s\" %s\n", payload, payload.getClass());
      String prediction = "Improper data has been provided to constuct a user";
      String error = null;

      // predict if the payload will throw an error due to current input sanitization.
      // if the prediction and the actual result do not match, it could be a sign of injection
      // succeeding.
      if (payload.length() >= 6) {
        if (payload.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$")) {
          prediction = null;
        }
      }

      try {
        User pUser = new User("johndoe@gmail.com", "JohnDoe", payload);
      } catch (IllegalArgumentException e) {
        error = e.getMessage();
      }
      System.out.printf("%s %s\n", prediction, error);
      Assertions.assertEquals(prediction, error);
    }
  }
}
