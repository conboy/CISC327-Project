package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Runs test on the function Register function on the frontend and backend These tests check if the
 * function did these correctly: R1-1: Email cannot be empty. password cannot be empty. R1-3: The
 * email has to follow addr-spec defined in RFC 5322 R1-4: Password has to meet the required
 * complexity: minimum length 6, at least one upper case, at least one lower case, and at least one
 * special character. R1-5: User name has to be non-empty, alphanumeric-only, and space allowed only
 * if it is not as the prefix or suffix. R1-6: User name has to be longer than 2 characters and less
 * than 20 characters.
 */
public class WebRegisterTest {
  WebDriver driver;
  boolean isLogin = false;
  String baseUrl = WIN_PROJECT_PATH + "\\app\\src\\main\\js\\qbnb\\register.html";

  /** Setup up chrome driver */
  @BeforeAll
  static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  /** Setup up chrome driver */
  @BeforeEach
  void setupTest() {
    try {
      driver = new ChromeDriver();
      driver.get(baseUrl);
    } catch (Exception e) {

    }
  }

  /** Close chrome driver */
  @AfterEach
  void teardown() {

    try {
      // driver.quit();
    } catch (Exception e) {

    }
  }
  /**
   * Test register functionality. Pass correct email user and password through Register function.
   */
  @Test
  void validRegisterTest() throws InterruptedException {
    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("JohnDoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertTrue(isLogin);
    } catch (Exception e) {

    }
  }

  /**
   * Test R1-1: Email cannot be empty. password cannot be empty. Try to register with an empty email
   * and password if the Register() returns false, test is passed.
   */
  @Test
  void emptyTest() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("");
      username.sendKeys("JohnDoe");
      password.sendKeys("");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertFalse(isLogin);
    } catch (Exception e) {

    }
  }
  /**
   * Test R1-3: The email has to follow addr-spec defined in RFC 5322. Try to login with an invalid
   * email address.
   */
  @Test
  void invalidEmailTest() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe.com");
      username.sendKeys("johndoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertFalse(isLogin);
    } catch (Exception e) {

    }
  }
  /**
   * R1-5: User name has to be non-empty, alphanumeric-only, and space allowed only if it is not as
   * the prefix or suffix. Try to login with an invalid user name.
   */
  @Test
  void invalidUserTest() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("(*!&@#*()");
      password.sendKeys("JohnDoe12");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertFalse(isLogin);
    } catch (Exception e) {

    }
  }
  /**
   * R1-6: User name has to be longer than 2 characters. Try to login with a user name shorter than
   * 2 characters
   */
  @Test
  void shortUserTest() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("a");
      password.sendKeys("JohnDoe12");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertFalse(isLogin);
    } catch (Exception e) {

    }
  }
}
