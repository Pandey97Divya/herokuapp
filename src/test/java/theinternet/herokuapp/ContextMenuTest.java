package theinternet.herokuapp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ContextMenuTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/context_menu");
    }

    // Positive Test Case 1: Verify right-click triggers context menu and alert appears
    @Test(priority = 1)
    public void verifyContextMenuRightClick() {
        // Find the context menu box element
        WebElement contextBox = driver.findElement(By.id("hot-spot"));

        // Perform right-click (context click) on the box
        Actions actions = new Actions(driver);
        actions.contextClick(contextBox).perform();

        // Switch to the alert box that appears
        Alert alert = driver.switchTo().alert();

        // Verify the alert text is as expected
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "You selected a context menu", "Unexpected alert text after right-clicking.");

        // Accept the alert
        alert.accept();
    }

    // Positive Test Case 2: Verify alert appears and can be closed
    @Test(priority = 2)
    public void verifyAlertCanBeClosed() {
        // Find the context menu box element
        WebElement contextBox = driver.findElement(By.id("hot-spot"));

        // Perform right-click (context click) on the box
        Actions actions = new Actions(driver);
        actions.contextClick(contextBox).perform();

        // Switch to the alert box that appears
        Alert alert = driver.switchTo().alert();

        // Verify the alert is displayed
        Assert.assertNotNull(alert, "Alert should be displayed after right-click.");

        // Close the alert by accepting it
        alert.accept();
    }

    // Negative Test Case 1: Verify no alert appears when clicking outside the context box
    @Test(priority = 3)
    public void verifyNoAlertWhenClickingOutsideContextBox() {
        // Click outside the context box (e.g., on the page's body)
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).click().perform();

        // Verify no alert is present
        try {
            Alert alert = driver.switchTo().alert();
            Assert.fail("An unexpected alert was triggered when clicking outside the context menu.");
        } catch (Exception e) {
            // No alert should be present, and exception is expected
            Assert.assertTrue(true, "No alert was triggered when clicking outside the context menu.");
        }
    }

    // Negative Test Case 2: Verify interaction with a non-existent element
    @Test(priority = 4)
    public void verifyInteractionWithNonExistentElement() {
        // Try to interact with a non-existent element (invalid XPath)
        try {
            WebElement nonExistentElement = driver.findElement(By.id("non-existent"));
            Assert.fail("Non-existent element should not be found.");
        } catch (Exception e) {
            // Expected exception should occur since the element does not exist
            Assert.assertTrue(true, "Non-existent element was not found, as expected.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
