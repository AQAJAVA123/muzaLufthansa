package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    private final WebDriverWait wait;

    @FindBy(xpath = "//header[@data-test-id='global-header-container']")
    private List<WebElement> availableFlights;

    public SearchResultsPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean hasSearchResults() {
        return !availableFlights.isEmpty();
    }

    public void selectFirstFlight() {
        availableFlights.get(0).click();
    }
}
