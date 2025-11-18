package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static org.project.WebSiteAutomate.driver;

public class TakeScreenShot  {
    public void captureScreenshot() throws IOException {
        Calendar calendar=Calendar.getInstance();
        int minute=calendar.get(Calendar.MINUTE);
        int sec=calendar.get(Calendar.SECOND);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshotFile,new File(".//screenshot//"+minute+"_"+sec+".jpg"));
    }
}
