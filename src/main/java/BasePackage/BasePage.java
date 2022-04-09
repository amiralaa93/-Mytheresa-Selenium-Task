package BasePackage;

import Utilities.TimeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static BasePackage.BaseTest.driver;


public class BasePage {


    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void setTextElementText(WebElement textElement, String value) {
        textElement.sendKeys(value);
    }

    public static void swtichToFrame(WebElement element) {
        DriverFactory.getDriver().switchTo().frame(element);
    }

    public static void swtichTodefaultContent() {
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public static void switchToWindow() {
        String childWindow = DriverFactory.getDriver().getWindowHandle();
        DriverFactory.getDriver().switchTo().window(childWindow);
    }

    public static void switchWindow() {

        Set<String> urls = DriverFactory.getDriver().getWindowHandles();
        Iterator<String> it = urls.iterator();
        String parentWindowID = it.next();
        String childWindowID = it.next();

        //To switch to child window
        DriverFactory.getDriver().switchTo().window(childWindowID);

        // To switch back to parent ID after complete the test
        DriverFactory.getDriver().switchTo().window(parentWindowID);
    }

    public static String getPageCurrentURL() {
        String currentURL = null;
        try {
            currentURL = driver.getCurrentUrl();
        } catch (Exception e) {
            System.out.println("some exception occurred while getting the current url " + currentURL);
        }
        return currentURL;
    }

    public static String getPageTitle() {
        String title = null;
        try {
            title = driver.getTitle();
        } catch (Exception e) {
            System.out.println("some exception occurred while getting the title " + title);
        }
        return title;
    }

    public static WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("Some exception occured while creating webelement " + locator);
        }
        return element;
    }

    public static boolean elementIsDisplayed(WebElement element) {
        TimeUtils.waitElement(element, 15);
        return element.isDisplayed();
    }

    public static boolean elementIsEnabled(WebElement element) {
        TimeUtils.waitElement(element, 15);
        return element.isEnabled();
    }

    public static boolean elementIsSelected(WebElement element) {
        TimeUtils.waitElement(element, 15);
        return element.isSelected();
    }

    public static String getText(By locator) {
        TimeUtils.waitElement((WebElement) locator, 15);
        return getElement(locator).getText();
    }

    public static void elementClick(WebElement element) {
        element.click();
    }

    public static void elementClear(WebElement element) {
        element.clear();
    }

    public static void elementSendKeys(WebElement element, String value) {
        element.sendKeys(value);
    }

    public static void elementDoubleClick(WebElement element) {
        try {
            Actions action = new Actions(driver).doubleClick(element);
            action.build().perform();

            System.out.println("Double clicked the element");
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document "
                    + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + element + " was not found in DOM "
                    + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Element " + element + " was not clickable "
                    + e.getStackTrace());
        }
    }

    public static void elementHoverOver(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void uploadFileRobot(String filename) throws AWTException {

        String currentUsersHomeDir = System.getProperty("user.dir");
        String file = currentUsersHomeDir + File.separator + filename;

        Robot rb = new Robot();
        StringSelection str = new StringSelection(file);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        //press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        //Release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        //Pressing and releasing Enter key
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void uploadFileSendKeys(WebElement element, String filename) {
        String currentUsersHomeDir = System.getProperty("user.dir");
        String file = currentUsersHomeDir + File.separator + filename;
        elementSendKeys(element, file);

    }
}
