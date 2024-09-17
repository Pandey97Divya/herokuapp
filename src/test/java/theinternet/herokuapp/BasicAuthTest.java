package theinternet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicAuthTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // DataProvider for valid and invalid credentials
    @DataProvider(name = "authCredentials")
    public Object[][] getAuthCredentials() {
        return new Object[][] {
            {"admin", "admin", true},  // Correct credentials (positive case)
            {"wrongUser", "admin", false},  // Incorrect username
            {"admin", "wrongPass", false},  // Incorrect password
            {"wrongUser", "wrongPass", false},  // Both incorrect
            {"", "", false}	//Blank
        };
    }

    // Positive & Negative Test Cases with Priority and DataProvider
    @Test(dataProvider = "authCredentials", priority = 1)
    public void testBasicAuthentication(String username, String password, boolean isSuccessExpected) {
        String url = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
        driver.get(url); // Navigating with Basic Authentication in the URL
        
        if (isSuccessExpected) {
            // Positive case: Correct credentials should display success message
            WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]"));
            Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed with correct credentials.");
        } else {
            // Negative case: Invalid credentials should not allow access
            WebElement errorPage = driver.findElement(By.tagName("body"));
            Assert.assertTrue(errorPage.getText().contains("not authorized"), "Expected not authorized message for invalid credentials.");
        }
    }

    // Positive Test Case to verify page title after successful authentication
    @Test(priority = 2)
    public void verifyPageTitleAfterSuccessfulAuth() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title is incorrect after successful authentication.");
    }

    // Negative Test Case to verify access is not granted with incorrect credentials
    @Test(priority = 3)
    public void verifyUnauthorizedAccessWithIncorrectCredentials() {
        driver.get("https://wrongUser:wrongPass@the-internet.herokuapp.com/basic_auth");
        WebElement body = driver.findElement(By.tagName("body"));
        Assert.assertTrue(body.getText().contains("not authorized"), "Expected unauthorized access message not found with incorrect credentials.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
