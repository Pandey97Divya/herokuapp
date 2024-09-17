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

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    // Positive Test Case 1: Verify initial state of checkboxes
    @Test(priority = 1)
    public void verifyInitialStateOfCheckboxes() {
        List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id='checkboxes']/input"));

        // Verify the first checkbox is not selected
        Assert.assertFalse(checkboxes.get(0).isSelected(), "First checkbox should not be selected initially.");

        // Verify the second checkbox is selected
        Assert.assertTrue(checkboxes.get(1).isSelected(), "Second checkbox should be selected initially.");
    }

    // Positive Test Case 2: Verify selecting and deselecting the first checkbox
    @Test(priority = 2)
    public void verifySelectAndDeselectFirstCheckbox() {
        WebElement firstCheckbox = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));

        // Select the first checkbox
        if (!firstCheckbox.isSelected()) {
            firstCheckbox.click();
        }
        Assert.assertTrue(firstCheckbox.isSelected(), "First checkbox should be selected after clicking.");

        // Deselect the first checkbox
        firstCheckbox.click();
        Assert.assertFalse(firstCheckbox.isSelected(), "First checkbox should not be selected after clicking again.");
    }

    // Positive Test Case 3: Verify selecting and deselecting the second checkbox
    @Test(priority = 3)
    public void verifySelectAndDeselectSecondCheckbox() {
        WebElement secondCheckbox = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));

        // Deselect the second checkbox
        if (secondCheckbox.isSelected()) {
            secondCheckbox.click();
        }
        Assert.assertFalse(secondCheckbox.isSelected(), "Second checkbox should not be selected after clicking.");

        // Select the second checkbox
        secondCheckbox.click();
        Assert.assertTrue(secondCheckbox.isSelected(), "Second checkbox should be selected after clicking again.");
    }

    // Negative Test Case 1: Verify selecting non-existent checkboxes
    @Test(priority = 4)
    public void verifyNoNonExistentCheckbox() {
        List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id='checkboxes']/input"));

        // Verify that there are exactly 2 checkboxes on the page
        Assert.assertEquals(checkboxes.size(), 2, "There should only be 2 checkboxes on the page.");
    }

    // Negative Test Case 2: Attempt to locate a non-existent element
    @Test(priority = 5)
    public void verifyNonExistentElement() {
        List<WebElement> nonExistentElement = driver.findElements(By.xpath("//nonexistent"));

        // There should be no such element on the page
        Assert.assertTrue(nonExistentElement.isEmpty(), "Non-existent element should not be found.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
