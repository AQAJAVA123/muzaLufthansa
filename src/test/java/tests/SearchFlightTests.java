package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchFlightTests extends BaseTest {
    private HomePage homePage;
    public static final String FROM_CITY = "Frankfurt";
    public static final String TO_CITY = "Albany";
    public static final String AUGUST = "August";
    public static final String TEST_RETURN_DATE = "2025-08-25";
    public static final String TEST_DEPARTURE_DATE = "2025-08-07";

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testValidFlightSearch() {
        homePage.enterFrom(FROM_CITY);
        homePage.enterTo(TO_CITY);
        homePage.enterDepartureDate(AUGUST, TEST_DEPARTURE_DATE);
        homePage.enterReturnDate(AUGUST, TEST_RETURN_DATE);
        homePage.clickSearch();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button/span[text()='Update']"))));

        assertTrue(driver.getCurrentUrl().contains("bestmatches"), "URL must include 'bestmatches'");
    }

    @Test
    public void testSearchWithoutDates() {
        homePage.enterFrom(FROM_CITY);
        homePage.enterTo(TO_CITY);
        driver.findElement(By.id("ReturnDate")).click();
        driver.findElement(By.id("ReturnDate")).sendKeys(Keys.DELETE);
        homePage.clickSearch();

        assertEquals(driver.getCurrentUrl(), "https://www.united.com/en/us", "Should be error message");
    }

    @Test
    public void testReturnDateBeforeDeparture() {
        homePage.enterFrom(FROM_CITY);
        homePage.enterTo(TO_CITY);
        homePage.enterDepartureDate(AUGUST, TEST_DEPARTURE_DATE);
        homePage.enterReturnDate(AUGUST, TEST_RETURN_DATE);
        homePage.clickSearch();

        assertTrue(driver.getPageSource().contains("Return date must be after departure") ||
                        driver.getCurrentUrl().contains("error"),
                "Expected an error when the return date is earlier than the departure date"
        );
    }
}
