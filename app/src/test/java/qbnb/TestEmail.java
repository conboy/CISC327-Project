package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;

/** Tests ! */
public class TestEmail {
  User testUser = new User();

  /** Testing to see if proper email passes */
  @Test
  public void testCorrectPassword() {
    Assertions.assertTrue(testUser.setEmail("aidan@leyne.com"));
  }

  /** Testing to see if failure if @ is missed */
  @Test
  public void testMissingAt() {
    Assertions.assertFalse(testUser.setEmail("aidan.leyne.com"));
  }

  /** Testing to see if failure if second at outside of quotes */
  @Test
  public void testMissingNum() {
    Assertions.assertFalse(testUser.setEmail("aid@n@leyne.com"));
  }

  /** Testing to see if failure if special characters are imputed outside of quotes */
  @Test
  public void testSpecialCharactersl() {
    Assertions.assertFalse(testUser.setEmail("(aidan)@leyne.com"));
  }

  /** Testing to see if failure if underscore in domain */
  @Test
  public void testUnserscore() {
    Assertions.assertFalse(testUser.setEmail("aidan@ley_ne.com"));
  }

  /** Testing to see if failure if space exists */
  @Test
  public void testSpace() {
    Assertions.assertFalse(testUser.setEmail("aid an @leyne.com"));
  }

  /** Testing to see if failure if icon characters exists */
  @Test
  public void testShortPassword() {
    Assertions.assertFalse(testUser.setEmail("aid[icon]an@leyne.com"));
  }

  /** Testing to see if failure if local is over 64 characters */
  @Test
  public void testLongLocal() {
    Assertions.assertFalse(
        testUser.setEmail("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz@leyne.com"));
  }

  /** Testing to see if failure if empty email */
  @Test
  public void testEmptyEmail() {
    Assertions.assertFalse(testUser.setEmail(""));
  }
}
