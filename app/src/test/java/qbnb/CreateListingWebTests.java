package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.PROJECT_PATH;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
import qbnb.models.daos.ListingDao;

/**
 * Tests against the same specification criteria as CreateListingTests.java, only this time via
 * inputting values into createlisting.html and awaiting the server's response. Only tests Mac OS
 * and Windows devices correctly - all other operating systems will have these tests skipped, due to
 * compatibility issues with the selenium extension.
 */
public class CreateListingWebTests {

  // variables needed by each test that are automatically assigned by setUpClass()
  static String path;
  static String baseUrl;
  static String osCheck;
  static AppThread thread;
  static WebDriver driver;

  /**
   * Initialises the chrome driver and url path automatically before each test. Not sure if URL path
   * is specifically OS dependant but
   */
  @BeforeAll
  static void setupClass() {
    ListingDao.deserialize().clearJSON(); // clean ListingDAO between tests to ensure that nothing leaks!
    WebDriverManager.chromedriver().setup();
    osCheck = System.getProperty("os.name").split(" ")[0];
    if (osCheck.equals("Mac")) {
      path = PROJECT_PATH;
      baseUrl = "file://" + path + "/app/src/main/js/qbnb/createlisting.html";
    } else {
      path = WIN_PROJECT_PATH;
      baseUrl = "file://" + path + "\\app\\src\\main\\js\\qbnb\\createlisting.html";
    }

    // start server; start web driver
    thread = new AppThread();
    thread.start();
    driver = new ChromeDriver();
    driver.get(baseUrl);
  }

  /** A test just to test out selenium and make sure that we can create a listing successfully! */
  @Test
  void BasicCreationTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      boolean listingMade = false;
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      title.clear();
      desc.clear();
      price.clear();

      title.sendKeys("Test title innit");
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.BACK_SPACE, Keys.BACK_SPACE);
      price.sendKeys("200");
      submit.click();
      sleep(1000);

      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-1 for the website version. Utilises input boundary testing to split titles
   * into the following categories: All Caps Alpha, All Lower Case Alpha, All Numeric, All Symbolic,
   * Space @ Front, Space @ Mid, and Space @ End
   */
  @Test
  void R1WebTest() throws InterruptedException {
    // ListingDao.clearJSON();
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      title.clear();
      desc.clear();
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.clear();
      price.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.BACK_SPACE, Keys.BACK_SPACE);
      price.sendKeys("100");

      // Input 1: a title of all uppercase ascii letters. Expected outcome: success.
      boolean listingMade = false;
      title.sendKeys("ALLCAPSTEST");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();

      // Input 2: a title of all lowercase ascii letters. Expected outcome: success.
      listingMade = false;
      title.sendKeys("alllowertest");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();

      // Input 3: a title of all numeric characters 0-9. Expected outcome: success.
      listingMade = false;
      title.sendKeys("0123456789");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();

      // Input 4: a title of several symbolic characters. Expected output: failure.
      listingMade = false;
      title.sendKeys("!@£$%^&*()[]'?.,+-=");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
      title.clear();

      // Input 5: a title of uppercase and lowercase ASCII characters with a space at the front.
      // Expected output: failure.
      listingMade = false;
      title.sendKeys(" FrontSpaceTest");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
      title.clear();

      // Input 6: a title of uppercase and lowercase ASCII characters with a space in the middle.
      // Expected outcome: success.
      listingMade = false;
      title.sendKeys("MidSpace Test");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();

      // Input 7: a title of uppercase and lowercase ASCII characters with a space at the end.
      // Expected output: failure.
      listingMade = false;
      title.sendKeys("EndSpaceTest ");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);

      thread.interrupt();
    }
  }
}
