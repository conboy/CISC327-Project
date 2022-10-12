package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;

/**
 * Runs tests on all requirements for usernames as specified for sprint 2.
 */
public class UsernameTest {
  User testUser = new User();

  /** Testing to see if proper username passes */
  @Test
  public void testCorrectUsername() {
    Assertions.assertTrue(testUser.setUsername("aidan leyne"));
  }

  /** Testing to see if failure if username is too short */
  @Test
  public void testShortUsername() {
    Assertions.assertFalse(testUser.setUsername("A"));
  }

  /** Testing to see if failure if username is too long */
  @Test
  public void testLongUsername() {
    Assertions.assertFalse(testUser.setUsername("abcdefghijklmnopqrstuvwxyz"));
  }

  /** Testing to see if failure if empty username */
  @Test
  public void testEmptyUsername() {
    Assertions.assertFalse(testUser.setUsername(""));
  }

  /** Testing to see if failure if starting with space */
  @Test
  public void testStartingSpace() {
    Assertions.assertFalse(testUser.setUsername(" aidanleyne"));
  }

  /** Testing to see if failure if ending with space */
  @Test
  public void testEndingSpace() {
    Assertions.assertFalse(testUser.setUsername("aidanleyne "));
  }
}
