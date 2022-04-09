package Loggers;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static BasePackage.BaseTest.driver;
import static Utilities.Utils.*;
import static org.aspectj.bridge.MessageUtil.fail;

public class BrowserConsoleLogger {
    public static Logs logs;
    public static LogEntries logEntries;


    public static void getBrowserConsoleLogs() throws FileNotFoundException {
        logs = driver.manage().logs();
        logEntries = logs.get(LogType.BROWSER);
        fileInfo = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        fileLocation = System.getProperty("user.dir")+ File.separator+"logs";
        fileName="BrowserConsoleLogger";
        PrintStream myconsole = new PrintStream(new File(fileLocation+File.separator+fileName+"-"+fileInfo+".txt"));
        System.setOut(myconsole);
        List<LogEntry> errorLogs = logEntries.getAll();
        if (errorLogs.size() != 0) {
            for (LogEntry logEntry : logEntries) {
                myconsole.println("Found error in logs: " + logEntry.getMessage() );
            }
            fail(errorLogs.size() + " Console error found");
        }
    }
}
