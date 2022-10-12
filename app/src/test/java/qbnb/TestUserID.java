package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;

/**
 * Runs tests on all requirements for user ids as specified for sprint 2.
 */
public class TestUserID {
  User user1 = new User("testUser1@gmail.com", "testUser1", "Password1!", true);
  User user2 = new User("testUser2@gmail.com", "testUser2", "Password2!", true);

  /** Testing if USerID for user1 and user2 are different */
  @Test
  public void testMatchingUserID() {
    Assertions.assertFalse(user1.getUserID() == user2.getUserID());
  }
}
