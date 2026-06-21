package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File("C:\\Users\\2496241\\IdeaProjects\\MiniProject1\\screenshot\\" + name + ".png");

            FileHandler.copy(source, dest);
            System.out.println("Screenshot saved: " + name);
        } catch (Exception e) {
            System.out.println("Screenshot failed");
        }
    }
}
