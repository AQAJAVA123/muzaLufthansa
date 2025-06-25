package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

public class SearchFlightTests extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver.get("https://www.lufthansa.com/xx/en/homepage");
        homePage = new HomePage(driver);
        homePage.acceptPrivacyIfPresent();
       // homePage.closeLoginModalIfPresent();
    }

    @Test
    public void testValidFlightSearch() {
        //homePage.enterFrom("Frankfurt");
        //homePage.enterTo("New York");
        //homePage.enterDepartureDate();

        WebElement shadowHost = driver.findElement(By.cssSelector("maui-modal"));
        // 2. Get shadow root
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // 3. Find element inside shadow root (e.g., the close button)
        shadowRoot.findElement(By.cssSelector("input[placeholder='Departure - Return']"));
        driver.findElement(homePage.datePicker("July", "10"));
//        homePage.enterReturnDate("20.07.2025");
//        homePage.clickSearch();

        // Простая проверка, что перешли на страницу с результатами
        //assertTrue(driver.getCurrentUrl().contains("flight-search"), "URL должен содержать 'flight-search'");
    }
    @Test
    public void testSearchWithoutDates() {
        homePage.enterFrom("Frankfurt");
        homePage.enterTo("New York");
        homePage.clickSearch();

        assertTrue(driver.getPageSource().contains("Please select a date") ||
                        driver.getCurrentUrl().contains("error"),
                "Ожидалась ошибка при отсутствии дат");
    }

    @Test
    public void testReturnDateBeforeDeparture() {
        homePage.enterFrom("Frankfurt");
        homePage.enterTo("New York");
        homePage.enterDepartureDate();
        homePage.enterReturnDate("20.07.2025");
        homePage.clickSearch();

        assertTrue(driver.getPageSource().contains("Return date must be after departure") ||
                        driver.getCurrentUrl().contains("error"),
                "Ожидалась ошибка при дате возврата раньше даты вылета");
    }
}
