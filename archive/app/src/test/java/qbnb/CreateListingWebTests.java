package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.PROJECT_PATH;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import qbnb.models.Listing;
import qbnb.models.User;
import qbnb.models.daos.ListingDao;
import qbnb.models.daos.UserDao;

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

  // the web elements used in every test
  static WebElement title;
  static WebElement desc;
  static WebElement price;
  static WebElement submit;

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
      WebDriverManager.chromedriver().setup();
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

      // initialise web elements
      title = driver.findElement(By.id("listingtitle"));
      desc = driver.findElement(By.id("desc"));
      price = driver.findElement(By.id("price"));
      submit = driver.findElement(By.id("submit"));
    }
  }

  @BeforeEach
  void clearAll() {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      title.clear();
      desc.clear();
      price.clear();
    }
  }

  boolean evaluateAlert() throws InterruptedException {
    submit.click();
    sleep(1000);
    String alert = driver.switchTo().alert().getText();
    driver.switchTo().alert().accept();
    return alert.equals("Listing saved successfully!");
  }

  /** A test just to test out selenium and make sure that we can create a listing successfully! */
  @Test
  void basicCreationTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      title.sendKeys("Test title innit");
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.BACK_SPACE, Keys.BACK_SPACE);
      price.sendKeys("200");
      Assertions.assertTrue(evaluateAlert());
      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-1 for the website version. Utilises input boundary testing to split titles
   * into the following categories: All Caps Alpha, All Lower Case Alpha, All Numeric, All Symbolic,
   * Space @ Front, Space @ Mid, and Space @ End
   */
  @Test
  void r1WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.sendKeys("100");

      // Input 1: a title of all uppercase ascii letters. Expected outcome: success.
      title.sendKeys("ALLCAPSTEST");
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // Input 2: a title of all lowercase ascii letters. Expected outcome: success.
      title.sendKeys("alllowertest");
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // Input 3: a title of all numeric characters 0-9. Expected outcome: success.
      title.sendKeys("0123456789");
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // Input 4: a title of several symbolic characters. Expected outcome: failure.
      title.sendKeys("!@£$%^&*()[]'?.,+-=");
      Assertions.assertFalse(evaluateAlert());
      title.clear();

      // Input 5: a title of uppercase and lowercase ASCII characters with a space at the front.
      // Expected outcome: failure.
      title.sendKeys(" FrontSpaceTest");
      Assertions.assertFalse(evaluateAlert());
      title.clear();

      // Input 6: a title of uppercase and lowercase ASCII characters with a space in the middle.
      // Expected outcome: success.
      title.sendKeys("MidSpace Test");
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // Input 7: a title of uppercase and lowercase ASCII characters with a space at the end.
      title.sendKeys("EndSpaceTest ");
      Assertions.assertFalse(evaluateAlert());

      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-2 for the createlisting webite. Utilises input boundary testing to test
   * against title lengths of 0 (beyond min), 1 (minimum bound), 80 (maximum bound), and 81 (beyond
   * max).
   */
  @Test
  void r2WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.sendKeys("100");

      // Input 1: a title with 0 length
      // On terms of length alone this should pass, but we expect failure as this ignores the
      // rules of R1 - an empty title doesn't contain anything at all!
      title.sendKeys("");
      Assertions.assertFalse(evaluateAlert());
      title.clear();

      // Input 2: a title of length 1. Expected outcome: success.
      title.sendKeys("A");
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // Input 3: a title of length 80. Expected outcome: success.
      title.sendKeys("A".repeat(80));
      desc.clear();
      desc.sendKeys("long description ".repeat(10));
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // Input 4: a title of length 81. Expected outcome: failure.
      title.sendKeys("A".repeat(81));
      Assertions.assertFalse(evaluateAlert());

      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-3 for the createlisting webite. Utilises input boundary testing to test
   * against description lengths of 19 (beyond min), 20 (minimum bound), 2000 (maximum bound), and
   * 2001 (beyond max).
   */
  @Test
  void r3WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      price.sendKeys("100");

      // Input 1: a description of length 19. Expected outcome: failure.
      title.sendKeys("R3 19");
      desc.sendKeys("A".repeat(19));
      Assertions.assertFalse(evaluateAlert());
      title.clear();
      desc.clear();

      // Input 2: a description of length 20. Expected outcome: success.
      title.sendKeys("R3 20");
      desc.sendKeys("A".repeat(20));
      Assertions.assertTrue(evaluateAlert());
      title.clear();
      desc.clear();

      // Input 3: a description of length 2000. Expected outcome: success.
      title.sendKeys("R3 2000");
      desc.sendKeys("A".repeat(2000));
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // Input 4: a description of length 2001. Expected outcome: failure.
      title.sendKeys("R3 2001");
      desc.sendKeys("A");
      Assertions.assertFalse(evaluateAlert());

      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-4 for the createlisting website. Utilises shotgun testing to generate
   * random inputs. If the random description is not longer than the title, the test expects listing
   * creation to fail; otherwise, it expects it to succeed.
   */
  @Test
  void r4WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      price.sendKeys("200");
      Random rand = new Random();

      // randomly generate titles and descriptions of varied length.
      // random gen adheres to other rules: unique titles, title length <= 80, desc length >= 20.
      // if title.length >= desc.length, we expect a failure, otherwise we expect success.
      // since each test takes ~1 second to execute, we only run a small number.
      for (int i = 0; i < 15; i++) {
        title.clear();
        desc.clear();
        int randT = rand.nextInt(75) + 1;
        String rt = "T" + i + " " + "a".repeat(randT);
        int randD = rand.nextInt(60) + 18;
        String rd = "D" + i + " " + "a".repeat(randD);

        title.sendKeys(rt);
        desc.sendKeys(rd);
        if (rt.length() >= rd.length()) Assertions.assertFalse(evaluateAlert());
        else Assertions.assertTrue(evaluateAlert());
      }
      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-5 for the createlisting webite. Utilises input boundary testing to test
   * against prices of 9 (beyond min), 10 (minimum bound), 10000 (maximum bound), and 10001 (beyond
   * max).
   */
  @Test
  void r5WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      desc.sendKeys("Generic descirption. plum pudding yum yum.");

      // Input 1: a price of 9. Expected outcome: failure.
      title.sendKeys("R5 9");
      price.sendKeys("9");
      Assertions.assertFalse(evaluateAlert());
      title.clear();
      price.clear();

      // Input 2: a price of 10. Expected outcome: success.
      title.sendKeys("R5 10");
      price.sendKeys("10");
      Assertions.assertTrue(evaluateAlert());
      title.clear();
      price.clear();

      // Input 3: a price of 10000. Expected outcome: success.
      title.sendKeys("R5 10000");
      price.sendKeys("10000");
      Assertions.assertTrue(evaluateAlert());
      title.clear();
      price.clear();

      // Input 4: a price of 10001. Expected outcome: failure.
      title.sendKeys("R5 10001");
      price.sendKeys("10001");
      Assertions.assertFalse(evaluateAlert());

      thread.interrupt();
    }
  }

  /**
   * Implementation of R4-6 for the createlistings website. There's not really a way to edit
   * modification date as it is set automatically upon listing creation. I guess this is exhaustive
   * output testing, since there's only one possible expected result; the current date. We expect
   * this test to succeed until 2025-01-03.
   */
  @Test
  void r6WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      Optional<Listing> l = ListingDao.deserialize().get(1);
      if (l.isEmpty()) {
        ListingDao.deserialize().clearJSON();
        basicCreationTest();
        l = ListingDao.deserialize().get(1);
      }
      if (l.isPresent()) {
        Listing listing = l.get();
        LocalDate modDate = listing.getModificationDate();
        Assertions.assertTrue(modDate.isBefore(LocalDate.parse("2025-01-02")));
        Assertions.assertTrue(modDate.isAfter(LocalDate.parse("2021-01-02")));
      } else {
        Assertions.fail();
      }
    }
  }

  /**
   * Implementation of R4-7 for the createlistings website. Uses exhaustive output testing to test
   * if users must exist in the database in order to create a listing. Two possible outputs:
   * 'Listing Saved Successfully' or 'Error: x'. We expect the first case to be caused by the
   * registered user, and the second to be caused by the non-registered user. Does not test for
   * empty users.
   */
  @Test
  void r7WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.sendKeys("100");

      // Create two dummy users -> only save one of them to userdao to mimic registration.
      // No point testing for owner email being empty, as that would require a user with no email to
      // be registerable, which is a problem for user tests!
      UserDao uDao = UserDao.deserialize("/db/users.json");
      User validUser = new User("funny@man.com", "Funnyman9", "!Rh0mbus", false);
      User invalidUser = new User("hacker@man.com", "secretDevil42", "!Rh0mbus", false);
      // currently userDao does not serialize on save so we don't need to worry if validUser is
      // already present.
      uDao.save(validUser);
      AppServerEndpoint.setLoggedInUser(validUser);

      // Input 1: a listing created by a registered user (expected behaviour). Expected outcome:
      // success.
      title.sendKeys("R7 Valid User");
      Assertions.assertTrue(evaluateAlert());
      title.clear();

      // login to erroneous user
      AppServerEndpoint.setLoggedInUser(invalidUser);

      // Input 2: a listing created by a registered user (erroneous behaviour). Expected outcome:
      // failure.
      title.sendKeys("R7 Unregistered User");
      Assertions.assertFalse(evaluateAlert());

      // logout
      AppServerEndpoint.setLoggedInUser(null);
    }
  }

  /**
   * Implementation of R4-8 for the createlistings website. This requirement is a little vague but I
   * see it to mean 'no user may own two listings with the same name', meaning duplicate titles can
   * exist between different users. Uses input partition testing of categories 'unused' and 'used'
   * between user and title. Inputs are classified as 'used' if they appear in any saved listing,
   * and 'unused' if they do not. Of the four combinations, we expect only 'used user, used title'
   * to result in failure.
   */
  @Test
  void r8WebTest() throws InterruptedException {
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      title.sendKeys("R8 Same Title Test");
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      price.sendKeys("100");

      // Create & register (save) two dummy users.
      UserDao uDao = UserDao.deserialize("/db/users.json");
      User validUser = new User("funnier@man.com", "Funmun9", "!Rh0mbus", false);
      User validerUser = new User("funniest@man.com", "Fm9", "!Rh0mbus", false);
      uDao.save(validUser);
      uDao.save(validerUser);
      AppServerEndpoint.setLoggedInUser(validUser);

      // Input 1: unused user & unused title. Expected outcome: success.
      Assertions.assertTrue(evaluateAlert());

      // Input 2: used user, unused title.
      // Expected outcome: success.
      title.clear();
      title.sendKeys("R8 Title Test Same");
      Assertions.assertTrue(evaluateAlert());

      // change users
      AppServerEndpoint.setLoggedInUser(validerUser);

      // Input 3: unused user, used title.
      // Expected outcome: success
      Assertions.assertTrue(evaluateAlert());

      // change users
      AppServerEndpoint.setLoggedInUser(validUser);

      // Input 4: used user, used title.
      // Expected outcome: failure.
      Assertions.assertFalse(evaluateAlert());

      // logout
      AppServerEndpoint.setLoggedInUser(null);
    }
  }
}
