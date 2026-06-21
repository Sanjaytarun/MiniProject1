package testcases;

import base.BaseTest;
import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RoyalCaribbeanPage;
import utils.PdfReportUtil;
import utils.ScreenshotUtil;

import javax.swing.*;

public class RoyalCaribbeanTest extends BaseTest {

    RoyalCaribbeanPage page;

    @Test(priority = 1)
    public void verifySearchTest() {
        try {
            page = new RoyalCaribbeanPage(driver);
            page.verifySearch();

            PdfReportUtil.log("Step 1 Passed: Search button verified");

        } catch (Exception e) {
            PdfReportUtil.log("Step 1 Failed");
            ScreenshotUtil.takeScreenshot(driver, "searchError");
            Assert.fail();
        }
    }


    @Test(priority = 2, dependsOnMethods = "verifySearchTest",dataProvider = "sanjay")
    public void searchShipTest() {
        try {
            page.searchShip();
            Assert.assertTrue(driver.getTitle().contains("Rhapsody"));
            PdfReportUtil.log("Step 2 Passed: Ship searched");
        } catch (Exception e) {

            ScreenshotUtil.takeScreenshot(driver, "searchShipError");
            Assert.fail();
        }
    }

    @Test(priority = 3, dependsOnMethods = "searchShipTest")
    public void clickBookNowTest() {
        try {
            page.clickBookNow();
            PdfReportUtil.log("Step 3 Passed: Book Now clicked");
        } catch (Exception e) {

            ScreenshotUtil.takeScreenshot(driver, "bookNowError");
            Assert.fail();
        }
    }

    @Test(priority = 4, dependsOnMethods = "clickBookNowTest")
    public void applyFiltersTest() {
        try {
            page.applyFilters();
            PdfReportUtil.log("Step 4 Passed: Apply btn is click");
        } catch (Exception e) {

            ScreenshotUtil.takeScreenshot(driver, "filterError");
            Assert.fail();
        }
    }

    @Test(priority = 5, dependsOnMethods = "applyFiltersTest")
    public void sortTest() {
        try {
            page.sortResults();
            PdfReportUtil.log("Step 5 Passed: Sorting applied");
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "sortError");
            Assert.fail();
        }
    }
    @DataProvider(name = "sanjay")
    public Object[][] testDate(){
        Object[][] n={{"sanjay"},
                {"sanju"},
                {"taru"}};
        return n;
    }
}
