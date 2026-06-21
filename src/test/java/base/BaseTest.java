package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import utils.PdfReportUtil;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    // START PDF (runs once before all tests)
    @BeforeSuite
    public void startReport() {
        PdfReportUtil.startReport();
    }

    // Browser Setup
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) {
        switch (browser){
            case "chrome":driver=new ChromeDriver();
            break;
            case "edge":driver=new EdgeDriver();
            break;
            case "firefox":driver=new FirefoxDriver();
            break;
            default:System.out.println("Inavlid");
            return;

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.royalcaribbean.com/alaska-cruises");
    }

    // Close Browser
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    //END PDF (runs once after all tests)
    @AfterSuite
    public void endReport() {
        PdfReportUtil.endReport();
    }
}
