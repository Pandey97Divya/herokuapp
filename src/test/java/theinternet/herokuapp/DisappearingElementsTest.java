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

import java.util.List;

public class DisappearingElementsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
    }

    // Positive Test Case 1: Verify that at least 4 elements are visible
    @Test(priority = 1)
    public void verifyAtLeastFourElementsVisible() {
        List<WebElement> menuItems = driver.findElements(By.cssSelector("ul > li > a"));

        // Verify that there are at least 4 elements present
        Assert.assertTrue(menuItems.size() >= 4, "There should be at least 4 elements visible.");
    }

    // Positive Test Case 2: Verify all 5 elements can appear after multiple refreshes
    @Test(priority = 2)
    public void verifyAllElementsAppearAfterRefresh() {
        boolean allElementsPresent = false;

        for (int i = 0; i < 10; i++) {
            driver.navigate().refresh();
            List<WebElement> menuItems = driver.findElements(By.cssSelector("ul > li > a"));

            // Check if all 5 elements are present
            if (menuItems.size() == 5) {
                allElementsPresent = true;
                break;
            }
        }

        Assert.assertTrue(allElementsPresent, "All 5 elements did not appear after multiple refreshes.");
    }

    // Positive Test Case 3: Verify that each element navigates to the correct page
    @Test(priority = 3)
    public void verifyNavigationOfElements() {
        List<WebElement> menuItems = driver.findElements(By.cssSelector("ul > li > a"));

        for (WebElement menuItem : menuItems) {
            String originalUrl = driver.getCurrentUrl();
            String linkText = menuItem.getText();
            menuItem.click();

            // Verify if the page navigates to a different URL
            String newUrl = driver.getCurrentUrl();
            Assert.assertNotEquals(originalUrl, newUrl, linkText + " did not navigate to a new page.");

            // Navigate back to the original page to test the next element
            driver.navigate().back();
        }
    }

    // Negative Test Case 1: Verify fewer than 5 elements appear due to random disappearance
    @Test(priority = 4)
    public void verifyLessThanFiveElementsCanAppear() {
        boolean fewerThanFive = false;

        for (int i = 0; i < 5; i++) {
            driver.navigate().refresh();
            List<WebElement> menuItems = driver.findElements(By.cssSelector("ul > li > a"));

            // Check if fewer than 5 elements are present
            if (menuItems.size() < 5) {
                fewerThanFive = true;
                break;
            }
        }

        Assert.assertTrue(fewerThanFive, "Fewer than 5 elements did not appear during the refresh cycles.");
    }

    // Negative Test Case 2: Attempt to interact with a non-existent element
    @Test(priority = 5)
    public void verifyInteractionWithMissingElement() {
        boolean elementNotFound = false;

        for (int i = 0; i < 5; i++) {
            driver.navigate().refresh();
            List<WebElement> menuItems = driver.findElements(By.cssSelector("ul > li > a"));

            // If only 4 elements are present, try to interact with the 5th one (Gallery, typically the disappearing element)
            if (menuItems.size() == 4) {
                try {
                    driver.findElement(By.linkText("Gallery")).click();
                    Assert.fail("Gallery element should not be found.");
                } catch (Exception e) {
                    elementNotFound = true;
                }
                break;
            }
        }

        Assert.assertTrue(elementNotFound, "The missing element was unexpectedly found.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
