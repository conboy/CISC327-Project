package qbnb;

import qbnb.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qbnb.models.User;


/**
 * Runs test on the function User.Login() These tests check if the function did these correctly:
 * R1-1: Email cannot be empty. password cannot be empty. R1-3: The email has to follow addr-spec
 * defined in RFC 5322 R1-4: Password has to meet the required complexity: minimum length 6, at
 * least one upper case, at least one lower case, and at least one special character. R1-5: User
 * name has to be non-empty, alphanumeric-only, and space allowed only if it is not as the prefix or
 * suffix. R1-6: User name has to be longer than 2 characters and less than 20 characters.
 */
public class LoginTest {

  // Create test user
  User userTest = new User(1, "johndoeshaggerman", "Password123#", "johndoe@gmail.com");

  /** Test login functionality. Pass correct email and password through Login function. */
  @Test
  public void correctLoginTest() {
    Assertions.assertTrue(userTest.Login("johndoe@gmail.com", "Password123#"));
  }

  /**
   * Test login functionality. Pass incorrect but valid email and password through Login function.
   */
  @Test
  public void incorrectLoginTest() {
    Assertions.assertFalse(userTest.Login("johndoe1@gmail.com", "Passwprd123#"));
  }

  /**
   * Test R1-1: Email cannot be empty. password cannot be empty. Try to login with an empty email
   * and password if the Login() returns false, test is passed.
   */
  @Test
  public void cannotBeEmptyTest() {
    Assertions.assertFalse(userTest.Login("", ""));
  }

  /**
   * Test R1-3: The email has to follow addr-spec defined in RFC 5322. Try to login with an invalid
   * email address.
   */
  @Test
  public void invalidEmailTest() {
    Assertions.assertFalse(userTest.Login("johndoegmail.com", "Password123#"));
  }

  /**
   * R1-4: Password has to meet the required complexity: minimum length 6, at least one upper case,
   * at least one lower case, and at least one special character. Try to login with an invalid
   * password.
   */
  @Test
  public void invalidPasswordTest() {
    Assertions.assertFalse(userTest.Login("johndoe@gmail.com", "Pass"));
  }

  /**
   * R1-5: User name has to be non-empty, alphanumeric-only, and space allowed only if it is not as
   * the prefix or suffix. Try to login with an invalid user name.
   */
  @Test
  public void invalidUserTest() {
    Assertions.assertFalse(userTest.Login(" %^`~= ", "Pass"));
  }

  /**
   * R1-6: User name has to be longer than 2 characters. Try to login with a user name shorter than
   * 2 characters
   */
  @Test
  public void shortUserTest() {
    Assertions.assertFalse(userTest.Login("a", "Pass"));
  }
}
