package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    // Positive Test Case 1: Verify default selection is "Please select an option"
    @Test(priority = 1)
    public void verifyDefaultSelection() {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        
        // Verify the default selected option is "Please select an option"
        String defaultOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(defaultOption, "Please select an option", "The default option is not as expected.");
    }

    // Positive Test Case 2: Select Option 1 from the dropdown
    @Test(priority = 2)
    public void selectOption1() {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        
        // Select Option 1 and verify
        select.selectByVisibleText("Option 1");
        String selectedOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption, "Option 1", "Failed to select 'Option 1'.");
    }

    // Positive Test Case 3: Select Option 2 from the dropdown
    @Test(priority = 3)
    public void selectOption2() {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        
        // Select Option 2 and verify
        select.selectByVisibleText("Option 2");
        String selectedOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption, "Option 2", "Failed to select 'Option 2'.");
    }

    // Negative Test Case 1: Attempt to select a non-existing option (Option 3)
    @Test(priority = 4, expectedExceptions = org.openqa.selenium.NoSuchElementException.class)
    public void selectInvalidOption() {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        
        // Try to select an invalid option (This will throw NoSuchElementException)
        select.selectByVisibleText("Option 3");
    }

    // Negative Test Case 2: Try to set an invalid value for dropdown through JavaScript (should fail)
    @Test(priority = 5)
    public void selectInvalidOptionViaJavaScript() {
        // Try to set an invalid value using JavaScript (No such option)
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        
        // Executing JS to set an invalid value (which doesn't exist in the dropdown)
        ((ChromeDriver) driver).executeScript("arguments[0].value='invalid';", dropdown);
        
        // Verify that the dropdown value has not changed
        Select select = new Select(dropdown);
        String selectedOption = select.getFirstSelectedOption().getText();
        
        // The selection should remain the default, as 'invalid' is not an option
        Assert.assertNotEquals(selectedOption, "invalid", "The dropdown should not allow an invalid option to be selected.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
