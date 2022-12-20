package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;

/** Tests ! */
public class PasswordTest {
  User testUser = new User();

  /** Testing to see if proper password passes */
  @Test
  public void testCorrectPassword() {
    Assertions.assertTrue(testUser.checkPassword("Password1#"));
  }

  /** Testing to see if special character is omited */
  @Test
  public void testMissingNonAphaNumCharacter() {
    Assertions.assertFalse(testUser.checkPassword("Password1"));
  }

  /** Testing to see if failure if num is omited */
  @Test
  public void testMissingNum() {
    Assertions.assertFalse(testUser.checkPassword("Password#"));
  }

  /** Testing to see if failure if capital is omited */
  @Test
  public void testMissingCapital() {
    Assertions.assertFalse(testUser.checkPassword("password1#"));
  }

  /** Testing to see if failure if lower case is omited */
  @Test
  public void testMissingLowerCase() {
    Assertions.assertFalse(testUser.checkPassword("PASSWORD1#"));
  }

  /** Testing to see if length req. is not met */
  @Test
  public void testShortPassword() {
    Assertions.assertFalse(testUser.checkPassword("PwD1$"));
  }

  /** Testing to see if failure if empty password */
  @Test
  public void testEmptyPassword() {
    Assertions.assertFalse(testUser.checkPassword(""));
  }
}
