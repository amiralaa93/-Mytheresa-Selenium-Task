package Utilities;

import com.jayway.jsonpath.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static BasePackage.BaseTest.driver;

public class ObjectRepositoryJsonParser {

    private static File jsonFile;
    private static String filename = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"ObjRepo.json";

    public static WebElement getObjectLocator(String jsonpath)
    {

        jsonFile = new File(filename);
        String locatorProperty = null;

        try {
            locatorProperty = JsonPath.read(jsonFile, jsonpath).toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
        String locatorType = locatorProperty.split(";")[0];
        String locatorValue = locatorProperty.split(";")[1];

        WebElement Element = null;
        switch(locatorType)
        {
            case "id":
                Element = driver.findElement(By.id(locatorValue));
                break;
            case "name":
                Element = driver.findElement(By.name(locatorValue));
                break;
            case "class":
                Element = driver.findElement(By.className(locatorValue));
                break;
            case "css":
                Element = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "link":
                Element = driver.findElement(By.linkText(locatorValue));
                break;
            case "partial":
                Element = driver.findElement(By.partialLinkText(locatorValue));
                break;
            case "tag":
                Element = driver.findElement(By.tagName(locatorValue));
                break;
            case "xpath":
                Element = driver.findElement(By.xpath(locatorValue));
                break;
        }
        return Element;
    }

    public static List<WebElement> getObjectLocatorList(String jsonpath)
    {

        jsonFile = new File(filename);
        String locatorProperty = null;

        try {
            locatorProperty = JsonPath.read(jsonFile, jsonpath).toString();
        } catch (IOException e) {

            e.printStackTrace();
        }
        String locatorType = locatorProperty.split(";")[0];
        String locatorValue = locatorProperty.split(";")[1];

        List<WebElement> Elements = null;
        switch(locatorType)
        {
            case "id":
                Elements = driver.findElements(By.id(locatorValue));
                break;
            case "name":
                Elements = driver.findElements(By.name(locatorValue));
                break;
            case "class":
                Elements = driver.findElements(By.className(locatorValue));
                break;
            case "css":
                Elements = driver.findElements(By.cssSelector(locatorValue));
                break;
            case "link":
                Elements = driver.findElements(By.linkText(locatorValue));
                break;
            case "partial":
                Elements = driver.findElements(By.partialLinkText(locatorValue));
                break;
            case "tag":
                Elements = driver.findElements(By.tagName(locatorValue));
                break;
            case "xpath":
                Elements = driver.findElements(By.xpath(locatorValue));
                break;
        }
        return Elements;
    }

}