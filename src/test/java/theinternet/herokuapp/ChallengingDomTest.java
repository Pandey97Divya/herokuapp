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

public class ChallengingDomTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
    }

    // Positive Test Case 1: Verify that the buttons are displayed and functional
    @Test(priority = 1)
    public void verifyButtonsPresenceAndFunctionality() {
        // Find and verify the buttons are displayed
        WebElement blueButton = driver.findElement(By.xpath("//a[@class='button']"));
        WebElement redButton = driver.findElement(By.xpath("//a[@class='button alert']"));
        WebElement greenButton = driver.findElement(By.xpath("//a[@class='button success']"));

        Assert.assertTrue(blueButton.isDisplayed(), "Blue button is not displayed.");
        Assert.assertTrue(redButton.isDisplayed(), "Red button is not displayed.");
        Assert.assertTrue(greenButton.isDisplayed(), "Green button is not displayed.");

        // Click each button to ensure they are functional (no action is expected but they should be clickable)
        blueButton.click();
        redButton.click();
        greenButton.click();

        // Check that after clicking the buttons, the page still loads as expected
        Assert.assertTrue(driver.getCurrentUrl().contains("challenging_dom"), "Unexpected URL after clicking buttons.");
    }

    // Positive Test Case 2: Verify table structure (columns and rows)
    @Test(priority = 2)
    public void verifyTableStructure() {
        List<WebElement> tableHeaders = driver.findElements(By.xpath("//table/thead/tr/th"));
        List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr"));

        // Verify the table has 7 columns
        Assert.assertEquals(tableHeaders.size(), 7, "Table should have 7 columns.");

        // Verify the table has 10 rows
        Assert.assertEquals(tableRows.size(), 10, "Table should have 10 rows.");
    }

    // Positive Test Case 3: Verify content in a specific table row
    @Test(priority = 3)
    public void verifySpecificTableRowContent() {
        WebElement cell = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));
        String cellText = cell.getText();

        Assert.assertNotNull(cellText, "Cell content should not be null.");
        Assert.assertFalse(cellText.isEmpty(), "Cell content should not be empty.");
    }

    // Negative Test Case 1: Verify there are no extra buttons
    @Test(priority = 4)
    public void verifyNoExtraButtons() {
        List<WebElement> buttons = driver.findElements(By.xpath("//a[contains(@class, 'button')]"));

        // There should only be 3 buttons
        Assert.assertEquals(buttons.size(), 3, "There are more buttons than expected.");
    }

    // Negative Test Case 2: Try to find a non-existent element (invalid XPath)
    @Test(priority = 5)
    public void verifyNonExistentElement() {
        List<WebElement> nonExistentElement = driver.findElements(By.xpath("//nonexistent"));

        // There should be no such element on the page
        Assert.assertTrue(nonExistentElement.isEmpty(), "Non-existent element should not be found.");
    }

    // Negative Test Case 3: Verify the table does not contain more than 7 columns
    @Test(priority = 6)
    public void verifyNoExtraColumns() {
        List<WebElement> tableHeaders = driver.findElements(By.xpath("//table/thead/tr/th"));

        // The table should not contain more than 7 columns
        Assert.assertTrue(tableHeaders.size() <= 7, "The table contains extra columns.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
