package Utilities;

import BasePackage.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;

import static BasePackage.BaseTest.driver;


public class Utils {

    public static String fileLocation;
    public static String fileInfo;
    public static String fileName;

    boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");

    public static void takeAScreenshot(String methodNameofTest) throws IOException {
//        if (ITestResult.FAILURE==testResult.getStatus()) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime date = LocalDateTime.now();
        String formattedDateTime = date.format(formatter);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotLocation = System.getProperty("user.dir") + File.separator + "screenshots";
//      StackTraceElement[] methodNameofTest = Thread.currentThread().getStackTrace();
        FileUtils.copyFile(scrFile, new File(screenshotLocation + "/" + methodNameofTest + "_" + formattedDateTime + ".png"));
//        }
    }

    public static String takeSnapshot(WebDriver driver, String pictureName) throws IOException {

        File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        String Destination = pictureName + ".png";
        File FinalDestination = new File("AutoReport/" + Destination);
        FileUtils.copyFile(src, FinalDestination);
        return Destination;

    }
//
//    public static void startVideo(String method) throws Exception {
//        MonteScreenRecorder.startRecording(method);
//    }
}
