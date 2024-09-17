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
import org.openqa.selenium.JavascriptExecutor;

public class ShadowDOMTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testShadowDOM() {
        driver.get("https://the-internet.herokuapp.com/shadow_dom");
        WebElement shadowHost = driver.findElement(By.cssSelector("shadow-host-selector"));
        WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].shadowRoot", shadowHost);
        WebElement shadowElement = shadowRoot.findElement(By.cssSelector("shadow-element-selector"));
        Assert.assertTrue(shadowElement.isDisplayed(), "Shadow DOM element is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
