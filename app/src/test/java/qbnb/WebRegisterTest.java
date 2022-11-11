package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
      driver.quit();
    } catch (Exception e) {

    }
  }
  /** Test register with a valid email user and pass */
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
  /** Test an invalid email address */
  @Test
  void invalidEmailTest() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe");
      username.sendKeys("JohnDoe");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertFalse(isLogin);
    } catch (Exception e) {

    }
  }
  /** Test an invalid username */
  @Test
  void invalidUserTest() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("j");
      password.sendKeys("JohnDoe123#");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Success")) isLogin = true;
      Assertions.assertFalse(isLogin);
    } catch (Exception e) {

    }
  }
  /** Test an invalid password */
  @Test
  void invalidPassTest() throws InterruptedException {

    try {
      WebElement email = driver.findElement(By.id("email"));
      WebElement username = driver.findElement(By.id("user"));
      WebElement password = driver.findElement(By.id("pass"));
      WebElement submit = driver.findElement(By.id("submit"));
      email.sendKeys("johndoe@test.com");
      username.sendKeys("JohnDoe");
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
