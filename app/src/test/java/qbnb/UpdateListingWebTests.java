package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.PROJECT_PATH;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.LocalDate;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import qbnb.models.Listing;
import qbnb.models.User;
import qbnb.models.daos.ListingDao;
import qbnb.models.daos.UserDao;

public class UpdateListingWebTests {

  // variables needed by each test that are automatically assigned by setUpClass()
  static String path;
  static String baseUrl;
  static String osCheck;
  static AppThread thread;
  static WebDriver driver;
  static User testUser;
  static UserDao userDao;

  /**
   * Initialises the chrome driver and url path automatically before each test. Not sure if URL path
   * is specifically OS dependant but ???
   */
  @BeforeAll
  static void setupClass() {
    osCheck = System.getProperty("os.name").split(" ")[0];
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      ListingDao.deserialize()
          .clearJSON(); // clean ListingDAO between tests to ensure that nothing leaks!
      userDao = UserDao.deserialize("/db/users.json");
      testUser = new User("updatetest@user.com", "pikablu", "!Rh0mbus", false);
      userDao.save(testUser);

      WebDriverManager.chromedriver().setup();
      if (osCheck.equals("Mac")) {
        path = PROJECT_PATH;
        baseUrl = "file://" + path + "/app/src/main/js/qbnb/updatelisting.html";
      } else {
        path = WIN_PROJECT_PATH;
        baseUrl = "file://" + path + "\\app\\src\\main\\js\\qbnb\\updatelisting.html";
      }

      // start server; start web driver
      thread = new AppThread();
      thread.start();
      driver = new ChromeDriver();
      driver.get(baseUrl);
      AppServerEndpoint.setLoggedInUser(testUser);
    }
  }

  /**
   * Implements R5-1 for the updatelisting website. This uses input partition testing to prove that
   * ownerID, listingID, and modificationDate are not updatable, while all other factors of a
   * listing are. This achieved through partitioning inputs into categories of 'valid' and
   * 'invalid', and testing all combinations, where 'valid' inputs adhere to all R4 rules and
   * 'invalid' ones do not. We expect that only three valid inputs will succeed - all other inputs
   * will result in failure. After each update attempt, the values of modificationdate, ownerID, and
   * listingID will be checked as to ensure they remain consistent.
   */
  @Test
  void r1WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      boolean updated = false;
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      ogTitle.clear();
      title.clear();
      desc.clear();
      price.clear();

      // create a new listing and get its id, modDate and ownerID
      Assertions.assertTrue(
          makeListing("R5 1 many tests", "uh oh oh no so many combinations!", 10));
      Listing l1 = ListingDao.deserialize().getByTitle("R5 1 many tests", testUser.getUserID());
      if (l1 == null) Assertions.fail();
      LocalDate md = l1.getModificationDate();
      long id = l1.getListingID();

      // setup valid and invalid inputs
      String validT = "R5 1 valid title";
      String invalidT = "!";
      String validD = "A valid description. ".repeat(3);
      String invalidD = "hehe";
      String validP = "10";
      String invalidP = "0";

      // Input 1: valid, valid, valid
      ogTitle.sendKeys("R5 1 many tests");
      title.sendKeys(validT);
      desc.sendKeys(validD);
      price.sendKeys(validP);
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertTrue(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
      ogTitle.clear();
      ogTitle.sendKeys(validT);
      title.clear();
      desc.clear();
      price.clear();

      // Input 2: invalid, valid, valid
      updated = false;
      title.sendKeys(invalidT);
      desc.sendKeys(validD);
      price.sendKeys(validP);
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertFalse(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
      title.clear();
      desc.clear();
      price.clear();

      // Input 3: valid, invalid, valid
      updated = false;
      title.sendKeys(validT);
      desc.sendKeys(invalidD);
      price.sendKeys(validP);
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertFalse(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
      title.clear();
      desc.clear();
      price.clear();

      // Input 4: valid, valid, invalid
      updated = false;
      title.sendKeys(validT);
      desc.sendKeys(validD);
      price.sendKeys(invalidP);
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertFalse(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
      title.clear();
      desc.clear();
      price.clear();

      // Input 5: valid, invalid, invalid
      updated = false;
      title.sendKeys(validT);
      desc.sendKeys(invalidD);
      price.sendKeys(invalidP);
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertFalse(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
      title.clear();
      desc.clear();
      price.clear();

      // Input 6: invalid, valid, invalid
      updated = false;
      title.sendKeys(invalidT);
      desc.sendKeys(validD);
      price.sendKeys(invalidP);
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertFalse(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
      title.clear();
      desc.clear();
      price.clear();

      // Input 7: invalid, invalid, valid
      updated = false;
      title.sendKeys(invalidT);
      desc.sendKeys(invalidD);
      price.sendKeys(validP);
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertFalse(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
      title.clear();
      desc.clear();
      price.clear();

      // Input 8: invalid, invalid, invalid
      updated = false;
      title.sendKeys(invalidT);
      desc.sendKeys(invalidD);
      price.sendKeys(invalidP);
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertFalse(updated);
      l1 = ListingDao.deserialize().getByTitle(validT, testUser.getUserID());
      if (l1 == null) Assertions.fail();
      Assertions.assertEquals(l1.getModificationDate(), md);
      Assertions.assertEquals(l1.getOwnerID(), testUser.getUserID());
      Assertions.assertEquals(l1.getListingID(), id);
    }
  }

  /**
   * Implements R5-2 for the updatelisting website. This implements shotgun testing to randomly
   * generate prices to update to. If the generated price is less than the current saved price, we
   * expect failure; otherwise, we expect success.
   */
  @Test
  void r2WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      boolean updated = false;
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      ogTitle.clear();
      title.clear();
      desc.clear();
      desc.sendKeys("uh oh he's using shotgun testing, watch out!");
      price.clear();
      makeListing("R5 2 priceIncrease", "uh oh he's using shotgun testing, watch out!", 500);

      int curPrice = 500;
      Random rand = new Random();
      String prevT = "R5 2 priceIncrease";

      // randomly generate a price between 10 and 10000.
      // if randP < curPrice, we expect a failure, otherwise we expect success, and update the
      // current price to randP.
      // since each test takes ~1 second to execute, we only run a small number.
      for (int i = 0; i < 15; i++) {
        updated = false;
        ogTitle.clear();
        ogTitle.sendKeys(prevT);
        title.clear();
        title.sendKeys("R2 priceIncrease " + i);
        price.clear();
        int randP = rand.nextInt(9990) + 10;

        price.sendKeys(Double.toString(randP));
        submit.click();
        sleep(1000);

        String alert = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        if (alert.equals("Listing updated successfully!")) updated = true;
        if (randP < curPrice) Assertions.assertFalse(updated);
        else {
          Assertions.assertTrue(updated);
          curPrice = randP;
          prevT = "R2 priceIncrease " + i;
        }
      }
    }
  }

  /**
   * Implements R5-3 for the updatelisting website. This uses exhaustive output testing; while
   * updates made on different days will have different modDates, all of these dates will have been
   * the current date at the time of the update. Thus, we only expect a single output to be possible
   * during any test run: the current date. We expect successful updates for all dates from
   * 2021-01-02 up until 2025-01-02.
   */
  @Test
  void r3WebTest() throws InterruptedException {
    if (LocalDate.now().isBefore(LocalDate.parse("2021-01-02"))) Assertions.fail();
    if (LocalDate.now().isAfter(LocalDate.parse("2025-01-02"))) Assertions.fail();
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      boolean updated = false;
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      ogTitle.clear();
      title.clear();
      desc.clear();
      desc.sendKeys("uh oh oh no i hate it when mod date is updated!");
      price.clear();
      price.sendKeys("100");

      // create a new listing and set its modification date to a previous time.
      makeListing("R5 3 update moddate", "uh oh oh no i hate it when mod date is updated!", 100);
      Listing l = ListingDao.deserialize().getByTitle("R5 3 update moddate", testUser.getUserID());
      if (l == null) Assertions.fail();
      l.setModificationDate(LocalDate.now().minusDays(5));

      ogTitle.sendKeys("R5 3 update moddate");
      title.sendKeys("R5 3 mod date updated");
      submit.click();
      sleep(1000);

      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) updated = true;
      Assertions.assertTrue(updated);
      l = ListingDao.deserialize().getByTitle("R5 3 mod date updated", testUser.getUserID());
      if (l == null) Assertions.fail();
      Assertions.assertEquals(l.getModificationDate(), LocalDate.now());
    }
  }

  // The remaining tests are updated versions of tests from CreateListingWebTests, re-worked to
  // function for testing the updating process instead of creation.
  // As with UpdateListingTests, not all of these criteria are testable for the update process.
  // Specifically, R4-6 (which is covered by R5-3) and R4-7 (as an owner must be logged in
  // in order to update their listings)

  /**
   * Implementation of R4-1 for the updatelistings website. Utilises input boundary testing to split
   * titles into the following categories: All Caps Alpha, All Lower Case Alpha, All Numeric, All
   * Symbolic, Space @ Front, Space @ Mid, and Space @ End
   */
  @Test
  void r5R4_1WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      Assertions.assertTrue(makeListing("R5 R4 1", "bebbebebebeebebebebeb", 100));
      ogTitle.clear();
      title.clear();
      desc.clear();
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.clear();
      price.sendKeys("100");

      // Input 1: a title of all uppercase ascii letters. Expected outcome: success.
      boolean listingMade = false;
      ogTitle.sendKeys("R5 R4 1");
      title.sendKeys("ALLCAPSTEST");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      ogTitle.clear();
      title.clear();

      // Input 2: a title of all lowercase ascii letters. Expected outcome: success.
      listingMade = false;
      ogTitle.sendKeys("ALLCAPSTEST");
      title.sendKeys("alllowertest");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      ogTitle.clear();
      title.clear();

      // Input 3: a title of all numeric characters 0-9. Expected outcome: success.
      listingMade = false;
      ogTitle.sendKeys("alllowertest");
      title.sendKeys("0123456789");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();
      ogTitle.clear();

      // Input 4: a title of several symbolic characters. Expected outcome: failure.
      listingMade = false;
      ogTitle.sendKeys("0123456789");
      title.sendKeys("!@£$%^&*()[]'?.,+-=");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
      title.clear();

      // Input 5: a title of uppercase and lowercase ASCII characters with a space at the front.
      // Expected outcome: failure.
      listingMade = false;
      title.sendKeys(" FrontSpaceTest");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
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
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();
      ogTitle.clear();

      // Input 7: a title of uppercase and lowercase ASCII characters with a space at the end.
      // Expected outcome: failure.
      listingMade = false;
      ogTitle.sendKeys("MidSpace Test");
      title.sendKeys("EndSpaceTest ");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
    }
  }

  /**
   * Implementation of R4-2 for the updatelisting website. Utilises input boundary testing to test
   * against title lengths of 0 (beyond min), 1 (minimum bound), 80 (maximum bound), and 81 (beyond
   * max).
   */
  @Test
  void r5R4_2WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      Assertions.assertTrue(makeListing("R5 R4 2", "bebbebebebeebebebebeb", 100));
      ogTitle.clear();
      title.clear();
      desc.clear();
      desc.sendKeys("Generic description. plum pudding yum yum.");
      price.clear();
      price.sendKeys("100");

      // Input 1: a title with 0 length
      // On terms of length alone this should pass, but we expect failure as this ignores the
      // rules of R1 - an empty title doesn't contain anything at all!
      boolean listingMade = false;
      ogTitle.sendKeys("R5 R4 2");
      title.sendKeys("");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
      title.clear();

      // Input 2: a title of length 1. Expected outcome: success.
      listingMade = false;
      title.sendKeys("A");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();
      ogTitle.clear();

      // Input 3: a title of length 80. Expected outcome: success.
      listingMade = false;
      ogTitle.sendKeys("A");
      title.sendKeys("A".repeat(80));
      desc.clear();
      desc.sendKeys("long description ".repeat(10));
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();
      ogTitle.clear();

      // Input 4: a title of length 81. Expected outcome: failure.
      listingMade = false;
      ogTitle.sendKeys("A".repeat(80));
      title.sendKeys("A".repeat(81));
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
    }
  }

  /**
   * Implementation of R4-3 for the createlisting webite. Utilises input boundary testing to test
   * against description lengths of 19 (beyond min), 20 (minimum bound), 2000 (maximum bound), and
   * 2001 (beyond max).
   */
  @Test
  void r5R4_3WebTest() throws InterruptedException {
    if (osCheck.equals("Mac")) {
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      title.clear();
      desc.clear();
      price.clear();
      ogTitle.clear();
      price.sendKeys("100");

      Assertions.assertTrue(makeListing("R5 R4 3", "bebbebebebeebebebebeb", 100));
      ogTitle.sendKeys("R5 R4 3");
      title.sendKeys("R5 R4 3");

      // Input 1: a description of length 19. Expected outcome: failure.
      boolean listingMade = false;
      desc.sendKeys("A".repeat(19));
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
      desc.clear();

      // Input 2: a description of length 20. Expected outcome: success.
      listingMade = false;
      desc.sendKeys("A".repeat(20));
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      desc.clear();

      // Input 3: a description of length 2000. Expected outcome: success.
      listingMade = false;
      desc.sendKeys("A".repeat(2000));
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);

      // Input 4: a description of length 2001. Expected outcome: failure.
      listingMade = false;
      desc.sendKeys("A"); // increases desc length by 1 to 2001
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);

      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-4 for the updatelisting website. Utilises shotgun testing to generate
   * random inputs. If the random description is not longer than the title, the test expects listing
   * creation to fail; otherwise, it expects it to succeed.
   */
  @Test
  void r5R4_4WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      boolean listingMade;
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      price.clear();
      price.sendKeys("200");
      Random rand = new Random();

      Assertions.assertTrue(makeListing("R5 R4 4", "bebbebebebeebebebebeb", 100));
      ogTitle.clear();
      ogTitle.sendKeys("R5 R4 4");

      // randomly generate titles and descriptions of varied length.
      // random gen adheres to other rules: unique titles, title length <= 80, desc length >= 20.
      // if title.length >= desc.length, we expect a failure, otherwise we expect success.
      // since each test takes ~1 second to execute, we only run a small number.
      for (int i = 0; i < 15; i++) {
        listingMade = false;
        title.clear();
        desc.clear();
        int randT = rand.nextInt(75) + 1;
        String rt = "T" + i + " " + "a".repeat(randT);
        int randD = rand.nextInt(60) + 18;
        String rd = "D" + i + " " + "a".repeat(randD);

        title.sendKeys(rt);
        desc.sendKeys(rd);
        submit.click();
        sleep(1000);

        String alert = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        if (alert.equals("Listing updated successfully!")) listingMade = true;
        if (rt.length() >= rd.length()) Assertions.assertFalse(listingMade);
        else {
          Assertions.assertTrue(listingMade);
          ogTitle.clear();
          ogTitle.sendKeys(rt);
        }
      }
    }
  }

  /**
   * Implementation of R4-5 for the updatelisting website. Utilises input boundary testing to test
   * against prices of 9 (beyond min), 10 (minimum bound), 10000 (maximum bound), and 10001 (beyond
   * max).
   */
  @Test
  void r5R4_5WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      ogTitle.clear();
      title.clear();
      desc.clear();
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.clear();

      Assertions.assertTrue(makeListing("R5 R4 5", "bebbebebebeebebebebeb", 10));
      ogTitle.sendKeys("R5 R4 5");
      title.sendKeys("R5 R4 5");

      // Input 1: a price of 9. Expected outcome: failure.
      boolean listingMade = false;
      price.sendKeys("9");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
      price.clear();

      // Input 2: a price of 10. Expected outcome: success.
      listingMade = false;
      price.sendKeys("10");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      price.clear();

      // Input 3: a price of 10000. Expected outcome: success.
      listingMade = false;
      price.sendKeys("10000");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      price.clear();

      // Input 4: a price of 10001. Expected outcome: failure.
      listingMade = false;
      price.sendKeys("10001");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);
    }
  }

  /**
   * Implementation of R4-8 for the updatelistings website. While this still implements input
   * partition testing, it has been heavily altered from the CreateLisitingWebTests version. This is
   * due to the fact that updating listings to the same title they already have makes no change, and
   * is thus redundant to test. Therefore only 3 assertions are made: unused user & title, unused
   * user & used title, and used user & title. We expect the first two inputs to succeed and the
   * final to fail.
   */
  @Test
  void r5R4_8WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      WebElement ogTitle = driver.findElement(By.id("oldtitle"));
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      ogTitle.clear();
      title.clear();
      desc.clear();
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.clear();
      price.sendKeys("100");
      boolean listingMade = false;

      // Create & register an extra user.
      User validUser = new User("rfive@rfour.com", "maNman9", "!Rh0mbus", false);
      userDao.save(validUser);
      AppServerEndpoint.setLoggedInUser(testUser);
      Assertions.assertTrue(makeListing("R5 R4 8 A", "bebbebebebeebebebebeb", 10, testUser));

      // Input 1: update the title to an usused title. Expected outcome: success.
      ogTitle.sendKeys("R5 R4 8 A");
      title.sendKeys("R5 R4 8 B");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();
      ogTitle.clear();

      // change users & make a new listing
      AppServerEndpoint.setLoggedInUser(validUser);
      Assertions.assertTrue(makeListing("R5 R4 8 A", "bebbebebebeebebebebeb", 10, validUser));

      // Input 2: update to a title that is in use, but by a different user.
      // Expected outcome: success.
      listingMade = false;
      ogTitle.sendKeys("R5 R4 8 A");
      title.sendKeys("R5 R4 8 B");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
      title.clear();
      ogTitle.clear();

      // change users & make a new listing
      AppServerEndpoint.setLoggedInUser(testUser);
      Assertions.assertTrue(makeListing("R5 R4 8 A", "bebbebebebeebebebebeb", 10, testUser));

      // Input 3: update to a title that is already in use for the current user.
      // Expected outcome: failure.
      listingMade = false;
      ogTitle.sendKeys("R5 R4 8 A");
      title.sendKeys("R5 R4 8 B");
      submit.click();
      sleep(1000);
      alert = driver.switchTo().alert().getText();
      driver.switchTo().alert().accept();
      if (alert.equals("Listing updated successfully!")) listingMade = true;
      Assertions.assertFalse(listingMade);

      // ensure we are logged in to the test user
      AppServerEndpoint.setLoggedInUser(testUser);
    }
  }

  /**
   * A quick and easy way to initialise listings in order to update them. All listing creation rules
   * apply.
   *
   * @param title the desired title.
   * @param desc the desired description.
   * @param price the desired price.
   * @return true if user is created successfully; false otherwise.
   */
  boolean makeListing(String title, String desc, double price) {
    try {
      Listing l =
          new Listing(
              (long) ((ListingDao.deserialize().getAll().size()) + 1),
              title,
              desc,
              price,
              LocalDate.now(),
              testUser.getUserID());
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  /**
   * An overload of makeListing which allows for users other than testUser to make listings.
   * Essential for r5R4_8WebTest.
   */
  boolean makeListing(String title, String desc, double price, User user) {
    try {
      Listing l =
          new Listing(
              (long) ((ListingDao.deserialize().getAll().size()) + 1),
              title,
              desc,
              price,
              LocalDate.now(),
              user.getUserID());
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
}
