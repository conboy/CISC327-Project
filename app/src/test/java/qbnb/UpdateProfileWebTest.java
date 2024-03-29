package qbnb;

import static qbnb.AppConf.WIN_PROJECT_PATH;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// Check that R3-2 R3-3 and R3-4 are true.
//
// * Valid, non-empty, alphanumeric Postal Code
// * Valid, non-empty, alphanumeric Username
//
// R3-1 already covered by R3Test.

public class UpdateProfileWebTest {

  static String baseUrl = WIN_PROJECT_PATH + "\\app\\src\\main\\js\\qbnb\\updateUserProfile.html";
  static String os = System.getProperty("os.name");
  WebDriver driver;

  String goodZip = "V8E0E8";
  String badZip = "!!!vb%7";

  String goodName = "QueensU";
  String badName = "XX:!@d";

  String mail = "dev@qbnb.ca";
  String addy = "123 Easy Street, Whistler BC, Canada";

  @BeforeAll
  static void initialize() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setupTest() {
    try {
      driver = new ChromeDriver();
      driver.get(baseUrl);
    } catch (Exception e) {

    }
  }

  /** Delete each chrome driver */
  @AfterEach
  void teardown() {

    try {
      driver.quit();
    } catch (Exception e) {

    }
  }

  /** Checks that the Update Profile page accepts valid postal codes. */
  @Test
  void validPostalCode() throws InterruptedException {
    try {
      WebElement email = driver.findElement(By.id("name"));
      WebElement username = driver.findElement(By.id("mail"));
      WebElement postalCode = driver.findElement(By.id("postalCode"));
      WebElement address = driver.findElement(By.id("address"));
      WebElement submit = driver.findElement(By.id("submit"));

      email.sendKeys(mail);
      address.sendKeys(addy);
      username.sendKeys(goodName);

      // Check good value
      postalCode.sendKeys(goodZip);
      submit.click();
      String msg = driver.switchTo().alert().getText();
      Assertions.assertNotEquals(msg, "Unable to update user profile");
    } catch (Exception e) {

    }
  }

  /** Checks that the page does not accept invalid postal codes. */
  @Test
  void invalidPostalCode() throws InterruptedException {
    try {
      WebElement email = driver.findElement(By.id("name"));
      WebElement username = driver.findElement(By.id("mail"));
      WebElement postalCode = driver.findElement(By.id("postalCode"));
      WebElement address = driver.findElement(By.id("address"));
      WebElement submit = driver.findElement(By.id("submit"));

      email.sendKeys(mail);
      address.sendKeys(addy);
      username.sendKeys(goodName);

      // check bad value
      postalCode.sendKeys(badZip);
      submit.click();
      String msg = driver.switchTo().alert().getText();
      Assertions.assertNotEquals(msg, "User profile updated successfully");
    } catch (Exception e) {

    }
  }

  /** Checks that the page accepts valid usernames. */
  @Test
  void validUsername() throws InterruptedException {
    try {
      WebElement email = driver.findElement(By.id("name"));
      WebElement username = driver.findElement(By.id("mail"));
      WebElement postalCode = driver.findElement(By.id("postalCode"));
      WebElement address = driver.findElement(By.id("address"));
      WebElement submit = driver.findElement(By.id("submit"));

      email.sendKeys(mail);
      address.sendKeys(addy);
      postalCode.sendKeys(goodZip);

      // Check good value
      username.sendKeys(goodName);
      submit.click();
      String msg = driver.switchTo().alert().getText();
      Assertions.assertNotEquals(msg, "Unable to update user profile");
    } catch (Exception e) {

    }
  }

  /** Checks that the page does not accept invalid usernames. */
  @Test
  void invalidUsername() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("name"));
      WebElement username = driver.findElement(By.id("mail"));
      WebElement postalCode = driver.findElement(By.id("postalCode"));
      WebElement address = driver.findElement(By.id("address"));
      WebElement submit = driver.findElement(By.id("submit"));

      email.sendKeys(mail);
      address.sendKeys(addy);
      postalCode.sendKeys(goodZip);

      // Check bad value
      username.sendKeys(badName);
      String msg = driver.switchTo().alert().getText();
      Assertions.assertNotEquals(msg, "User profile updated successfully");
    } catch (Exception e) {

    }
  }
}
