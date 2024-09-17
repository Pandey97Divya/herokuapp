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

public class ABTesting {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/abtest");
    }

    @Test
    public void verifyABTestPageTitle() {
        // Verify the page title is correct
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "The page title is incorrect.");
    }

    @Test
    public void verifyABTestHeader() {
        // Verify the page header is correct
        WebElement header = driver.findElement(By.tagName("h3"));
        String expectedHeader = "A/B Test Variation 1";
        String actualHeader = header.getText();
        Assert.assertEquals(actualHeader, expectedHeader, "The page header is incorrect.");
    }

    @Test
    public void verifyABTestLink() {
        // Verify the link to "A/B Test" redirects to the correct URL
        WebElement link = driver.findElement(By.linkText("A/B Test"));
        String expectedUrl = "https://the-internet.herokuapp.com/abtest";
        String actualUrl = link.getAttribute("href");
        Assert.assertEquals(actualUrl, expectedUrl, "The link URL is incorrect.");
    }

    @Test
    public void verifyABTestContent() {
        // Verify the content of the page (e.g., presence of text or specific elements)
        WebElement content = driver.findElement(By.id("content"));
        Assert.assertTrue(content.isDisplayed(), "Content section is not displayed.");
    }

    @Test
    public void verifyNonExistingElement() {
        // Verify that a non-existing element is not present on the page
        boolean isElementPresent = driver.findElements(By.id("non-existing-id")).size() > 0;
        Assert.assertFalse(isElementPresent, "Non-existing element should not be present on the page.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
