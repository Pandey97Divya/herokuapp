package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InfiniteScrollTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testInfiniteScroll() {
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
        WebElement element = driver.findElement(By.xpath("//div[@class='jscroll-added']"));
        Assert.assertTrue(element.isDisplayed(), "Infinite scroll element is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
