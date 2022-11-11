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
 * Runs test on the function User.Login() for the frontend and backend to see if it will only login
 * on a valid login.
 */
public class WebLoginTest {
  WebDriver driver;
  boolean isLogin = false;
  String baseUrl = WIN_PROJECT_PATH + "\\app\\src\\main\\js\\qbnb\\register.html";
  String loginUrl = WIN_PROJECT_PATH + "\\app\\src\\main\\js\\qbnb\\login.html";

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

  /** Delete each chrome driver */
  @AfterEach
  void teardown() {

    try {
      driver.quit();
    } catch (Exception e) {

    }
  }
  /**
   * Test login functionality. Create a valid user and login in and return true if login successful.
   */
  @Test
  void validLoginTest() throws InterruptedException {

    try {
      // Create user
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("JohnDoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      driver.switchTo().alert().dismiss();
      // Login
      driver.get(loginUrl);
      sleep(1000);
      email = driver.findElement(By.id("email"));
      password = driver.findElement(By.id("pass"));
      submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Logged in successfully")) isLogin = true;
      Assertions.assertTrue(isLogin);
    } catch (Exception e) {

    }
  }
  /**
   * Test login functionality. Pass incorrect valid email and correct password through Login function.
   */
  @Test
  void incorrectEmailTest() throws InterruptedException {
    try {
      // Create user
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("JohnDoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      driver.switchTo().alert().dismiss();
      // Login
      driver.get(loginUrl);
      sleep(1000);
      email = driver.findElement(By.id("email"));
      password = driver.findElement(By.id("pass"));
      submit = driver.findElement(By.id("submit"));
      email.sendKeys("asd@test.com");
      password.sendKeys("JohnDoe13#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Logged in successfully")) isLogin = true;

    } catch (Exception e) {

    }
    Assertions.assertFalse(isLogin);
  }

  /**
   * Test login functionality. Pass correct email and valid but incorrect password through Login function.
   */
  @Test
  void incorrectPasstest() throws InterruptedException {
    try {
      // Create user
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("JohnDoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      driver.switchTo().alert().dismiss();
      // Login
      driver.get(loginUrl);
      sleep(1000);
      email = driver.findElement(By.id("email"));
      password = driver.findElement(By.id("pass"));
      submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      password.sendKeys("JohnDoe13#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Logged in successfully")) isLogin = true;

    } catch (Exception e) {

    }
    Assertions.assertFalse(isLogin);
  }
  /**
   * Test login functionality. Pass valid but incorrect login details through Login function.
   */
  @Test
  void incorrectLoginTest() throws InterruptedException {
    try {
      // Create user
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("JohnDoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      driver.switchTo().alert().dismiss();
      // Login
      driver.get(loginUrl);
      sleep(1000);
      email = driver.findElement(By.id("email"));
      password = driver.findElement(By.id("pass"));
      submit = driver.findElement(By.id("submit"));
      email.sendKeys("sasd@test.com");
      password.sendKeys("JohnDoe13#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Logged in successfully")) isLogin = true;

    } catch (Exception e) {

    }
    Assertions.assertFalse(isLogin);
  }
}
