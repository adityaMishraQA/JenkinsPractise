package utility;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListnersSetup extends TakeScreenShot implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        try {
            captureScreenshot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
