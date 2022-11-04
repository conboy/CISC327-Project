package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.PROJECT_PATH;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebRegisterTest {
  boolean isLogin = false;
  private static String OS = System.getProperty("os.name").toLowerCase();
  @Test
  void correctRegisterTest() throws InterruptedException {
    String baseUrl = getURL();
    WebDriver driver = new ChromeDriver();
    driver.get(baseUrl);
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
    String baseUrl = getURL();
    WebDriver driver = new ChromeDriver();
    driver.get(baseUrl);
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
    String baseUrl = getURL();
    WebDriver driver = new ChromeDriver();
    driver.get(baseUrl);
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
    String baseUrl = getURL();
    WebDriver driver = new ChromeDriver();
    driver.get(baseUrl);
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

  public String getURL() {
    if (isWindows()) {
      System.setProperty("webdriver.chrome.driver", WIN_PROJECT_PATH + "chromedriver.exe");
      String baseUrl = WIN_PROJECT_PATH + "app\\src\\main\\js\\qbnb\\register.html";
      return baseUrl;
    } else {
      System.setProperty("webdriver.chrome.driver", PROJECT_PATH + "chromedriver");
      String baseUrl = PROJECT_PATH + "app\\src\\main\\js\\qbnb\\register.html";
      return baseUrl;
    }
  }

  public static boolean isWindows() {
    return OS.contains("win");
  }
}
