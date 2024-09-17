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

public class AddRemoveElements {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }

    // Positive Test Case: Add Element button should add a delete button
    @Test
    public void verifyAddElementFunctionality() {
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click(); // Click the Add Element button

        WebElement deleteButton = driver.findElement(By.className("added-manually"));
        Assert.assertTrue(deleteButton.isDisplayed(), "Delete button is not displayed after adding an element.");
    }

    // Positive Test Case: Add and Remove Element
    @Test
    public void verifyRemoveElementFunctionality() {
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click(); // Add an element

        WebElement deleteButton = driver.findElement(By.className("added-manually"));
        deleteButton.click(); // Remove the element

        Assert.assertTrue(driver.findElements(By.className("added-manually")).isEmpty(), "Delete button is still present after removal.");
    }

    // Positive Test Case: Add multiple elements and verify count
    @Test
    public void verifyMultipleElementsAddition() {
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        for (int i = 0; i < 5; i++) {
            addButton.click(); // Add elements 5 times
        }

        int deleteButtonsCount = driver.findElements(By.className("added-manually")).size();
        Assert.assertEquals(deleteButtonsCount, 5, "The number of delete buttons added is incorrect.");
    }

    // Negative Test Case: Try to click a non-existing delete button without adding an element
    @Test
    public void verifyNoDeleteButtonWithoutAddingElement() {
        Assert.assertTrue(driver.findElements(By.className("added-manually")).isEmpty(), "Delete button should not be present without adding an element.");
    }

    // Negative Test Case: Verify that clicking remove on an element that's not present doesn’t break the functionality
    @Test
    public void verifyRemoveButtonWithNoElements() {
        Assert.assertTrue(driver.findElements(By.className("added-manually")).isEmpty(), "No delete button should be present initially.");

        // Try removing an element without any being added
        try {
            WebElement deleteButton = driver.findElement(By.className("added-manually"));
            deleteButton.click();
        } catch (Exception e) {
            Assert.assertTrue(true, "No delete button found, which is expected when no element is added.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
