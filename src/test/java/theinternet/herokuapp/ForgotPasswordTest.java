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

public class ForgotPasswordTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testForgotPassword() {
        driver.get("https://the-internet.herokuapp.com/forgot_password");
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("test@example.com");
        WebElement retrievePasswordButton = driver.findElement(By.id("form_submit"));
        retrievePasswordButton.click();
        WebElement confirmationMessage = driver.findElement(By.id("content"));
        Assert.assertTrue(confirmationMessage.getText().contains("Your e-mail's been sent!"), "Password reset message not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
