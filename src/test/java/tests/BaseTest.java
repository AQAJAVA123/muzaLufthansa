package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.united.com";
    protected static final String FROM_CITY = "Frankfurt";
    protected static final String TO_CITY = "Albany";

    @BeforeSuite
    public void setUpClass() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterSuite
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
