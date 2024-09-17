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

public class EntryAdTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testEntryAd() {
        driver.get("https://the-internet.herokuapp.com/entry_ad");
        WebElement closeButton = driver.findElement(By.xpath("//div[@class='modal']//a[text()='Close']"));
        Assert.assertTrue(closeButton.isDisplayed(), "Entry ad close button is not displayed.");
        closeButton.click();
        WebElement content = driver.findElement(By.id("content"));
        Assert.assertTrue(content.isDisplayed(), "Content is not displayed after closing the ad.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
