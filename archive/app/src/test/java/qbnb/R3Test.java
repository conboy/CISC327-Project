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
    Assertions.assertTrue(testUser.setAddress("123 easy street, Whistler BC, Canada"));
  }

  /** Validates R3-2 and R3-3 -- postal code is a valid canadian one */
  @Test
  public void updatePostalCodeTest() {
    String zipGood = "V8E 0E8";
    Assertions.assertTrue(testUser.setPostalCode(zipGood));

    String zipBad = "V8E 0E!";
    Assertions.assertFalse(testUser.setPostalCode(zipBad));

    String zipWrong = "x";
    Assertions.assertFalse(testUser.setPostalCode(zipWrong));

    String zipEmpty = "";
    Assertions.assertFalse(testUser.setPostalCode(zipWrong));
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
