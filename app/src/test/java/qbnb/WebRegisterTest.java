package qbnb;

import static java.lang.Thread.sleep;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebRegisterTest {
  WebDriver driver;
  boolean isLogin = false;

  @BeforeAll
  static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setupTest() {
    try {
      driver = new ChromeDriver();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  void teardown() {
    driver.quit();
  }

  @Test
  void validRegisterTest() throws InterruptedException {
    String baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
    System.out.println(baseURL);
    driver.get(baseURL);
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
    String baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
    System.out.println(baseURL);
    driver.get(baseURL);
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
    String baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
    System.out.println(baseURL);
    driver.get(baseURL);
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
    String baseURL = System.getProperty("user.dir") + "\\src\\main\\js\\qbnb\\register.html";
    System.out.println(baseURL);
    driver.get(baseURL);
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
