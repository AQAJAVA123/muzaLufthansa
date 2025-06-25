package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@placeholder='From']")
    private WebElement fromInput;

    @FindBy(xpath = "//input[@placeholder='To']")
    private WebElement toInput;

    @FindBy(xpath = "//maui-input[@placeholder='Departure - Return']")
    private WebElement departureDateField;



    @FindBy(id = "flightSearchReturnDateInput")
    private WebElement returnDateInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='cm-acceptAll']")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//div[@class='date-input'][1]")
    private WebElement shadowElement;


    public WebElement getShadowElement() {

        WebElement shadowHost = driver.findElement(By.cssSelector("maui-modal"));
        // 2. Get shadow root
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // 3. Find element inside shadow root (e.g., the close button)
        return shadowRoot.findElement(By.cssSelector("input[placeholder='Departure - Return']"));
    }



    public void acceptPrivacyIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
        } catch (Exception ignored) {
            // если баннер не появился — игнорируем
        }
    }
//    public void closeLoginModalIfPresent() {
//        try {
//            // Находим первый maui-modal (можно уточнить, если их несколько)
//            WebElement modalHost = driver.findElement(By.cssSelector("maui-modal"));
//            SearchContext shadowRoot = modalHost.getShadowRoot();
//
//            // Находим кнопку с классом, содержащим 'close'
//            WebElement closeButton = shadowRoot.findElement(By.cssSelector("button.close"));
//
//            closeButton.click();
//            System.out.println("Модальное окно входа закрыто");
//        } catch (Exception e) {
//            System.out.println("Модального окна не было или оно уже закрыто");
//        }
//    }
//public void closeLoginModalIfPresent() {
//    try {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Дожидаемся появления shadow host
//        WebElement modalHost = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("maui-modal")));
//
//        // Получаем shadow root
//        SearchContext shadowRoot = modalHost.getShadowRoot();
//
//        // Явное ожидание кнопки внутри shadow root
//        WebElement closeButton = wait.until(driver -> shadowRoot.findElement(By.cssSelector("button.close")));
//
//        closeButton.click();
//        System.out.println("Модальное окно входа закрыто");
//    } catch (Exception e) {
//        System.out.println("Модального окна не было или оно не закрылось: " + e.getMessage());
//    }
//}


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterFrom(String from) {
        wait.until(ExpectedConditions.visibilityOf(fromInput)).clear();
        fromInput.sendKeys(from);
    }
    public By datePicker(String month, String day) {
        wait.until(ExpectedConditions.visibilityOf(departureDateField));
        return By.xpath(String.format("//div[@class='DayPickerMonthLabel' and contains(text(), '%s')]/../following-sibling::table[@class='CalendarMonth_table CalendarMonth_table_1']//div[@class='calendar-day-item' and text() = '%s']", month, day));
    }

    public void enterTo(String to) {
        wait.until(ExpectedConditions.visibilityOf(toInput)).clear();
        toInput.sendKeys(to);
    }

    public void enterDepartureDate() {
        wait.until(ExpectedConditions.visibilityOf(departureDateField));
        departureDateField.click();
    }

    public void enterReturnDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(returnDateInput)).clear();
        returnDateInput.sendKeys(date);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}
