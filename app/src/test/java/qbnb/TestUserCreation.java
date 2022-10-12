package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;

/** Tests ! */
public class TestUserCreation {
  User testUser1 = new User("testUser1@gmail.com", "testUser1", "Password1!", true);

  /** Test if user is given $100.00 when they are created */
  @Test
  public void testStartingBalance() {
    Assertions.assertEquals(testUser1.getGuestAccount().getBalance(), 100);
  }

  /** Test if user has empty postal code at account opening */
  @Test
  public void testStartingPostalCode() {
    Assertions.assertEquals(testUser1.getAddress().getPostalZip(), null);
  }

  /** Test if user has empty address at account opening */
  @Test
  public void testStartingAddress() {
    Assertions.assertEquals(testUser1.getAddress().getStreetNumber(), -1);
    Assertions.assertEquals(testUser1.getAddress().getUnitNumber(), null);
    Assertions.assertEquals(testUser1.getAddress().getStreetName(), null);
  }
}
