// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssertDownloadsTitleTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void test() {
    driver.get("https://www.selenium.dev/");
    driver.manage().window().setSize(new Dimension(0, 0));
    driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
    driver.findElement(By.cssSelector(".nav-item:nth-child(2) span")).click();
    assertThat(driver.getTitle(), is("Downloads | Selenium"));
  }
}