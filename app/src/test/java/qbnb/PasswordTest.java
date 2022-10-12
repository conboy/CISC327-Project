package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import qbnb.models.User;

public class PasswordTest {
  User testUser = new User();

  /** Testing to see if proper password passes */
  @Test
  public void testCorrectPassword() {
    Assertions.assertTrue(testUser.setPassword("Password1$"));
  }

  /** Testing to see if special character is omited */
  @Test
  public void testMissingNonAphaNumCharacter() {
    Assertions.assertFalse(testUser.setPassword("Password1"));
  }

  /** Testing to see if failure if num is omited */
  @Test
  public void testMissingNum() {
    Assertions.assertFalse(testUser.setPassword("Password#"));
  }

  /** Testing to see if failure if capital is omited */
  @Test
  public void testMissingCapital() {
    Assertions.assertFalse(testUser.setPassword("password1#"));
  }

  /** Testing to see if failure if lower case is omited */
  @Test
  public void testMissingLowerCase() {
    Assertions.assertFalse(testUser.setPassword("PASSWORD1#"));
  }

  /** Testing to see if length req. is not met */
  @Test
  public void testShortPassword() {
    Assertions.assertFalse(testUser.setPassword("PwD1$"));
  }

  /** Testing to see if failure if empty password */
  @Test
  public void testEmptyPassword() {
    Assertions.assertFalse(testUser.setPassword(""));
  }
}
