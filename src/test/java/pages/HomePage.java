package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static tests.SearchFlightTests.FROM_CITY;
import static tests.SearchFlightTests.TO_CITY;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "bookFlightOriginInput")
    private WebElement fromInput;

    @FindBy(id = "bookFlightDestinationInput")
    private WebElement toInput;

    @FindBy(xpath = "//input[@placeholder='Departure']")
    private WebElement departureDateField;

    @FindBy(id = "ReturnDate")
    private WebElement returnDateInput;

    @FindBy(xpath = "//button[@type='submit']/span[text()='Find flights']")
    private WebElement searchButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterFrom(String From) {
        wait.until(ExpectedConditions.visibilityOf(fromInput));
        fromInput.sendKeys(FROM_CITY);
    }

    public void enterTo(String to) {
        wait.until(ExpectedConditions.visibilityOf(toInput));
        toInput.sendKeys(TO_CITY);
    }

    public void enterDepartureDate(String month, String date) {
        departureDateField.click();
        driver.findElement(By.xpath(returnDateXpath(month, date))).click();
    }

    /**
     * @param month as human-readable f.e. August
     * @param date  format YYYY-MM-DD
     * @return
     */
    public String returnDateXpath(String month, String date) {
        return String.format("//table[@aria-label = '%s 2025']//td[@data-day='%s']", month, date);
    }

    public void enterReturnDate(String month, String date) {
        returnDateInput.click();
        driver.findElement(By.xpath(returnDateXpath(month, date))).click();
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}
