package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDropTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
    }

    // Positive Test Case 1: Verify drag-and-drop functionality
    @Test(priority = 1)
    public void verifyDragAndDropFunctionality() {
        // Locate both boxes (Box A and Box B)
        WebElement boxA = driver.findElement(By.id("column-a"));
        WebElement boxB = driver.findElement(By.id("column-b"));

        // Perform drag-and-drop action using Actions class
        Actions action = new Actions(driver);
        action.dragAndDrop(boxA, boxB).perform();

        // Verify the text of the boxes after swapping
        String boxATextAfter = driver.findElement(By.id("column-a")).getText();
        String boxBTextAfter = driver.findElement(By.id("column-b")).getText();

        // After drag and drop, the text of the boxes should be swapped
        Assert.assertEquals(boxATextAfter, "B", "Box A should contain 'B' after drag and drop.");
        Assert.assertEquals(boxBTextAfter, "A", "Box B should contain 'A' after drag and drop.");
    }

    // Positive Test Case 2: Verify box positions are swapped
    @Test(priority = 2)
    public void verifyBoxPositionSwapped() {
        // Perform drag and drop action
        WebElement boxA = driver.findElement(By.id("column-a"));
        WebElement boxB = driver.findElement(By.id("column-b"));
        
        Actions action = new Actions(driver);
        action.dragAndDrop(boxA, boxB).perform();

        // Verify that the location of box A and box B has changed
        String boxALocationAfter = driver.findElement(By.id("column-a")).getAttribute("innerHTML");
        String boxBLocationAfter = driver.findElement(By.id("column-b")).getAttribute("innerHTML");

        // Ensure that the boxes are swapped by checking innerHTML/text
        Assert.assertTrue(boxALocationAfter.contains("B"), "Box A should be replaced by Box B.");
        Assert.assertTrue(boxBLocationAfter.contains("A"), "Box B should be replaced by Box A.");
    }

    // Negative Test Case 1: Attempt to drag box A to an invalid area (non-drop zone)
    @Test(priority = 3)
    public void verifyInvalidDragOperation() {
        // Locate box A
        WebElement boxA = driver.findElement(By.id("column-a"));

        // Locate an invalid area (outside of the draggable area)
        WebElement pageTitle = driver.findElement(By.xpath("//h3[text()='Drag and Drop']"));

        // Try to drag box A to the title (invalid drop zone)
        Actions action = new Actions(driver);
        action.clickAndHold(boxA).moveToElement(pageTitle).release().perform();

        // Verify that box A has not been dropped into the invalid area (title should remain unchanged)
        String boxAText = driver.findElement(By.id("column-a")).getText();
        Assert.assertEquals(boxAText, "A", "Box A should not be moved to an invalid drop area.");
    }

    // Negative Test Case 2: Simulate failed drag-and-drop (release element before reaching target)
    @Test(priority = 4)
    public void verifyFailedDragAndDrop() {
        // Locate both boxes
        WebElement boxA = driver.findElement(By.id("column-a"));
        WebElement boxB = driver.findElement(By.id("column-b"));

        // Simulate failed drag-and-drop (release before reaching Box B)
        Actions action = new Actions(driver);
        action.clickAndHold(boxA).moveByOffset(100, 50).release().perform();

        // Verify that the boxes' text remains unchanged
        String boxAText = driver.findElement(By.id("column-a")).getText();
        String boxBText = driver.findElement(By.id("column-b")).getText();

        Assert.assertEquals(boxAText, "A", "Box A should remain unchanged after failed drag and drop.");
        Assert.assertEquals(boxBText, "B", "Box B should remain unchanged after failed drag and drop.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
