package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private final WebDriverWait wait;

    private final By resultList = By.cssSelector("[data-test-id='flight-card']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean hasResults() {
        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultList));
        return !results.isEmpty();
    }

    public void selectFirstFlight() {
        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultList));
        if (!results.isEmpty()) {
            results.get(0).click();
        }
    }
}
