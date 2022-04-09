package Utilities;

import BasePackage.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSUtils {

    public static void clickElementByJS(WebElement element) {

        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("arguments[0].click();", element);

    }

    public static void doubleClickElementByJS(WebElement element) {

        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("arguments[0].dblclick;", element);

    }

    public static void setAttributesByJS(WebElement element,String value) {
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("arguments[0].setAttribute('value','" + value + "');", element);
    }

    public static void scrollPageDown() {
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollIntoView(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPoint() {
        WebDriver driver = null;
        // TODO Auto-generated method stub

        //Scroll to a point
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("window.scrollBy(0,10000)");
    }

    public static void drawBorder(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void refreshBrowserByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("history.go(0)");
    }

    public static String getTitleByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        String title = js.executeScript("return document.title;").toString();
        return title;
    }

    public static String getPageInnerText(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        String pageText = js.executeScript("return document.documentElement.innerText;").toString();
        return pageText;
    }
}
