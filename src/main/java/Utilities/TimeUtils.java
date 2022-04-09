package Utilities;


import BasePackage.DriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static void explicitWait(WebElement element, int timeoutinseconds) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeoutinseconds);
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
    }



    public static void implicitlyWait(int timeout) {
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public static void pageLoadTimeout(int timeout) {
        DriverFactory.getDriver().manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
    }

    public static void setScriptTimeout(int timeout) {
        DriverFactory.getDriver().manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    public static void waitElement(WebElement element, int timeout) {
        new WebDriverWait(DriverFactory.getDriver(), timeout).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));

    }

    public static void shortWait() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mediumWait() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void longWait() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
