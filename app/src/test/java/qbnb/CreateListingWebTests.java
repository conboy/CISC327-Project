package qbnb;

import static java.lang.Thread.sleep;
import static qbnb.AppConf.PROJECT_PATH;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateListingWebTests {

  public String path;

  /** A test just to test out selenium and make stuff work! */
  @Test
  void testTest() throws InterruptedException {
    String osCheck = System.getProperty("os.name").split(" ")[0];
    path = PROJECT_PATH;
    if (osCheck.equals("Mac") || osCheck.equals("Windows")) {
      AppThread thread = new AppThread();
      thread.start();
      boolean listingMade = false;
      if (osCheck.equals("Mac"))
        System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
      if (osCheck.equals("Windows"))
        System.setProperty("webdriver.chrome.driver", path + "/chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      String baseUrl = "file://" + path + "/app/src/main/js/qbnb/createlisting.html";
      driver.get(baseUrl);
      WebElement title = driver.findElement(By.id("listingtitle"));
      WebElement desc = driver.findElement(By.id("desc"));
      WebElement price = driver.findElement(By.id("price"));
      WebElement submit = driver.findElement(By.id("submit"));
      title.sendKeys("Test title innit");
      desc.sendKeys("Generic descirption. plum pudding yum yum.");
      // price.sendKeys("200");
      submit.click();
      sleep(1000);
      String alert = driver.switchTo().alert().getText();
      if (alert.equals("Listing saved successfully!")) listingMade = true;
      Assertions.assertTrue(listingMade);
    }
    // selenium runs into errors with less popular OS' like Ubuntu, so skip this test for them.
    else {
      Assertions.assertTrue(true);
    }
  }
}
