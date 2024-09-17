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

public class SortableDataTablesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSortableDataTables() {
        driver.get("https://the-internet.herokuapp.com/tables");
        WebElement table = driver.findElement(By.id("table1"));
        Assert.assertTrue(table.isDisplayed(), "Sortable data table is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
