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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenImageTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/broken_images");
    }

    // Positive Test Case: Verify if all images are displayed properly by checking their status code
    @Test(priority = 1)
    public void verifyImagesAreDisplayedCorrectly() {
        List<WebElement> images = driver.findElements(By.tagName("img"));

        for (WebElement img : images) {
            String imgSrc = img.getAttribute("src");
            try {
                URL imageUrl = new URL(imgSrc);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                Assert.assertEquals(responseCode, 200, "Image at URL: " + imgSrc + " is broken.");
            } catch (Exception e) {
                Assert.fail("Exception occurred while checking image: " + imgSrc + ". Exception: " + e.getMessage());
            }
        }
    }

    // Negative Test Case: Verify if any image is broken (expecting broken images on this page)
    @Test(priority = 2)
    public void verifyBrokenImages() {
        List<WebElement> images = driver.findElements(By.tagName("img"));
        boolean hasBrokenImages = false;

        for (WebElement img : images) {
            String imgSrc = img.getAttribute("src");
            try {
                URL imageUrl = new URL(imgSrc);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    hasBrokenImages = true;
                    System.out.println("Broken image found: " + imgSrc + " (Status Code: " + responseCode + ")");
                }
            } catch (Exception e) {
                hasBrokenImages = true;
                System.out.println("Exception occurred while checking image: " + imgSrc + ". Exception: " + e.getMessage());
            }
        }

        Assert.assertTrue(hasBrokenImages, "No broken images were found, but the test expects them to exist.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
