package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SlowResourcesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSlowResources() {
        driver.get("https://the-internet.herokuapp.com/slow");
        WebElement slowContent = driver.findElement(By.id("slow-content"));
        Assert.assertTrue(slowContent.isDisplayed(), "Slow resources content is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

