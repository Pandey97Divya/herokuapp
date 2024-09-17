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

public class JQueryUIMenusTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testMenus() {
        driver.get("https://the-internet.herokuapp.com/jqueryui/menu");
        WebElement menuItem = driver.findElement(By.xpath("//a[text()='Enabled']"));
        menuItem.click();
        Assert.assertTrue(menuItem.isDisplayed(), "Menu item 'Enabled' is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
