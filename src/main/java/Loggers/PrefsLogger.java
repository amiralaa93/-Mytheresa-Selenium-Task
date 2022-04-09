package Loggers;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import static Utilities.Utils.*;


public class PrefsLogger {

    public static LoggingPreferences logPrefs;

    public static LoggingPreferences getChromeLoggingPrefs() {
        fileInfo = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        fileLocation = System.getProperty("user.dir") + File.separator + "logs";
        fileName="ChromeDriver-";
        System.setProperty("webdriver.chrome.logfile", fileLocation + File.separator + fileName + fileInfo + ".log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        return logPrefs;
    }
}