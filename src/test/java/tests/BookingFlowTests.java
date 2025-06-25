package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertTrue;

public class BookingFlowTests extends BaseTest {
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private BookingPage bookingPage;

    @BeforeMethod
    public void setUp() {
        driver.get("https://www.lufthansa.com/xx/en/homepage");
        homePage = new HomePage(driver);
        homePage.acceptPrivacyIfPresent();
//        homePage.closeLoginModalIfPresent();

    }

    @Test
    public void testSearchRedirectToResults() {
        homePage.getShadowElement();
        homePage.enterFrom("Frankfurt");
        homePage.enterTo("New York");
        homePage.enterDepartureDate();
        homePage.enterReturnDate("20.07.2025");
        homePage.clickSearch();

        searchResultsPage = new SearchResultsPage(driver);
        assertTrue(searchResultsPage.hasResults(), "Результаты поиска должны отображаться");
    }

    @Test
    public void testContinueToPassengerForm() {
        homePage.enterFrom("Frankfurt");
        homePage.enterTo("New York");
        homePage.enterDepartureDate();
        homePage.enterReturnDate("20.07.2025");
        homePage.clickSearch();

        searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.selectFirstFlight();

        bookingPage = new BookingPage(driver);
        assertTrue(bookingPage.getCurrentUrl().contains("booking"), "URL должен содержать 'booking'");
    }
}
