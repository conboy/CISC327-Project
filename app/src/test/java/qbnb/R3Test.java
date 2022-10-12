package qbnb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;

public class R3Test {
  User testUser = new User();

  /** Validates R3-1 -- user can edit the username, email, and password */
  @Test
  public void UpdateAccessTest() {
    Assertions.assertTrue(testUser.setUsername("aNewUser"));
    Assertions.assertTrue(testUser.setEmail("user@mail.com"));
    Assertions.assertTrue(
        testUser.updateAddress(
            123, "unit num", "easy street", "Kingston", "K7L 3T3", "Ontario", "Canada"));
  }

  /** Validates R3-2 and R3-3 -- postal code is a valid canadian one */
  @Test
  public void updatePostalCodeTest() {
    String zipGood = "V8E 0E8";
    Assertions.assertTrue(
        testUser.updateAddress(
            123, "unit num", "easy street", "Kingston", zipGood, "Ontario", "Canada"));

    String zipBad = "V8E 0E!";
    Assertions.assertFalse(
        testUser.updateAddress(
            123, "unit num", "easy street", "Kingston", zipBad, "Ontario", "Canada"));

    String zipWrong = "x";
    Assertions.assertFalse(
        testUser.updateAddress(
            123, "unit num", "easy street", "Kingston", zipWrong, "Ontario", "Canada"));

    String zipEmpty = "";
    Assertions.assertFalse(
        testUser.updateAddress(
            123, "unit num", "easy street", "Kingston", zipEmpty, "Ontario", "Canada"));
  }

  /** Validates R3-4 -- username must be only alphanumeric */
  @Test
  public void updateUsernameTest() {
    String nameGood = "UserName";
    Assertions.assertTrue(testUser.setUsername(nameGood));

    String nameBad = "User@#%";
    Assertions.assertFalse(testUser.setUsername(nameBad));
  }
}
