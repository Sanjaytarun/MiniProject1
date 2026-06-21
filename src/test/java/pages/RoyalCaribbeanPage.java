package pages;

import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;

public class RoyalCaribbeanPage {

    WebDriver driver;

    public RoyalCaribbeanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // STEP 1 LOCATORS
    @FindBy(xpath = "//div[@aria-label='Close']")
    WebElement closePopup;

    @FindBy(xpath = "//button[contains(@aria-label,'Search')]")
    WebElement searchBtn;

    //STEP 2 LOCATORS
    @FindBy(id = "search-bar-input")
    WebElement searchInput;

    @FindBy(xpath = "//a[contains(@href,'rhapsody')]")
    WebElement rhapsodyLink;

    // STEP 3
    @FindBy(id = "navigationMenuComponent-117388165-5")
    WebElement bookNowBtn;

    // STEP 4 LOCATORS
    @FindBy(xpath = "//button[contains(.,'All Filters')]")
    WebElement allFilters;

    @FindBy(xpath = "//li[.//span[text()='Destinations']]")
    WebElement destinationsTab;

    @FindBy(xpath = "//li[.//span[text()='Number of nights']]")
    WebElement nightsTab;

    @FindBy(xpath = "//li[.//span[text()='Departure Port']]")
    WebElement portTab;

    @FindBy(xpath = "//li[.//span[text()='Cruise dates']]")
    WebElement datesTab;

    @FindBy(xpath = "//button[@data-testid='mobile-all-filters-header-button']")
    WebElement backBtn;

    @FindBy(xpath = "//button[contains(.,'Apply')]")
    WebElement applyBtn;

    @FindBy(xpath = "//button[contains(@class,'PillButton_root')]")
    List<WebElement> pillButtons;

    @FindBy(xpath = "//button[contains(@class,'Year_eachMonth')]")
    List<WebElement> months;

    //STEP 5
    @FindBy(xpath = "//button[@class='MuiTypography-root MuiTypography-title2 styles__Select-sc-5d55c2e3-2 hMBXVN btn-style-text DROPDOWN_BUTTON css-13jcdqr']")
    WebElement sortDropdown;

    @FindBy(xpath = "//p[text()='Price lowest to highest']")
    WebElement lowestPrice;

    //STEP 1 METHOD
    public void verifySearch() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(closePopup));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closePopup);
        } catch (Exception e) {}

        if (!searchBtn.isDisplayed()) {
            throw new RuntimeException("Search button not visible");
        }
    }

    //STEP 2 METHOD
    public void searchShip() throws Exception {

        InputStream file = new FileInputStream(
                "C:\\Users\\2496241\\IdeaProjects\\MiniProject1\\ExcelFile\\Book2.xlsx");

        XSSFWorkbook book = new XSSFWorkbook(file);
        XSSFSheet sheet = book.getSheet("Sheet1");

        XSSFRow row = sheet.getRow(1);
        XSSFCell cell = row.getCell(1);

        book.close();
        file.close();

        searchBtn.click();
//        searchInput.sendKeys(cell.toString());
        searchInput.sendKeys();
        searchBtn.click();
        rhapsodyLink.click();
    }

    //STEP 3 METHOD
    public void clickBookNow() {
        bookNowBtn.click();
    }

    //STEP 4 METHOD
    public void applyFilters() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(.,'All Filters')]")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allFilters);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allFilters);

        Thread.sleep(2000);

        // DESTINATIONS
        destinationsTab.click();
        for (WebElement f : pillButtons) {
            if (f.isEnabled()) {
                f.click();
                break;
            }
        }
        backBtn.click();

        // NUMBER OF NIGHTS
        nightsTab.click();
        for (WebElement e : pillButtons) {
            if (e.isEnabled()) {
                e.click();
                break;
            }
        }
        backBtn.click();

        // DEPARTURE PORT
        portTab.click();
        for (WebElement f : pillButtons) {
            if (f.isEnabled()) {
                f.click();
                break;
            }
        }
        backBtn.click();

        // CRUISE DATES
        datesTab.click();
        int count = 0;
        for (WebElement m : months) {
            if (m.isEnabled() && count < 4) {
                m.click();
                count++;
            }
        }
        backBtn.click();

        applyBtn.click();
    }

    // STEP 5 METHOD
    public void sortResults() {
        sortDropdown.click();
        lowestPrice.click();
    }

}