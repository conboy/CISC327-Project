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

  @BeforeAll
  static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setupTest() {
    driver = new ChromeDriver();
    driver.get(baseUrl);
  }

  @AfterEach
  void teardown() {
    driver.quit();
  }

  @Test
  void validRegisterTest() throws InterruptedException {

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
  }

  @Test
  void invalidEmailTest() throws InterruptedException {
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
  }

  @Test
  void invalidUserTest() throws InterruptedException {
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
  }

  @Test
  void invalidPassTest() throws InterruptedException {
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
  }
}
