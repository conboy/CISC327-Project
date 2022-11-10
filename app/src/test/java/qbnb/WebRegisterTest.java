package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.PROJECT_PATH;
import static qbnb.AppConf.WIN_PROJECT_PATH;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebRegisterTest {
  public String path;
  public boolean isLogin = false;
  WebDriver driver;
  String baseUrl;

  @BeforeEach
  void setupTest() {
    String osCheck = System.getProperty("os.name").split(" ")[0];
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      AppThread thread = new AppThread();
      thread.start();
      if (osCheck.equals("Mac")) {
        path = PROJECT_PATH;
        System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
        baseUrl = "file://" + path + "/app/src/main/js/qbnb/register.html";
      } else {
        path = WIN_PROJECT_PATH;
        System.setProperty("webdriver.chrome.driver", path + "/chromedriver.exe");
        baseUrl = "file://" + path + "\\app\\src\\main\\js\\qbnb\\register.html";
      }
      WebDriver driver = new ChromeDriver();
      driver.get(baseUrl);
    }
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
