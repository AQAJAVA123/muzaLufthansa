package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.HomePage;
import pages.SearchResultsPage;

import static org.testng.Assert.assertTrue;
import static tests.SearchFlightTests.FROM_CITY;
import static tests.SearchFlightTests.TO_CITY;

public class BookingFlowTests extends BaseTest {
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private BookingPage bookingPage;
    public static final String AUGUST = "August";
    public static final String TEST_RETURN_DATE = "2025-08-25";
    public static final String TEST_DEPARTURE_DATE = "2025-08-07";

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testSearchRedirectToResults() {
        homePage.enterFrom(FROM_CITY);
        homePage.enterTo(TO_CITY);
        homePage.enterDepartureDate(AUGUST, TEST_DEPARTURE_DATE);
        homePage.enterReturnDate(AUGUST, TEST_RETURN_DATE);
        homePage.clickSearch();

        assertTrue(searchResultsPage.hasSearchResults(), "Search results should be displayed");
    }

    @Test
    public void testContinueToPassengerForm() {
        homePage.enterFrom(FROM_CITY);
        homePage.enterTo(TO_CITY);
        homePage.enterDepartureDate(AUGUST, TEST_DEPARTURE_DATE);
        homePage.enterReturnDate(AUGUST, TEST_RETURN_DATE);
        homePage.clickSearch();

        searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.selectFirstFlight();

        bookingPage = new BookingPage(driver);
        assertTrue(bookingPage.getCurrentUrl().contains("booking"), "URL must have 'booking'");
    }
}
