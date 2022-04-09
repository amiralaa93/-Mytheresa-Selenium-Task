package Utilities;

//import com.jayway.restassured.RestAssured;

import com.jayway.restassured.RestAssured;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class ConfigUtils {

    String title;
    String urlData;
    protected String sessionId;
    String receivedUrl;

    public boolean remote;
    protected String browserName;
    private RemoteWebDriver wdriver;
    protected boolean localMode = true;    //test are executed locally

    //Please provide your user id and access key to run the scripts on Sauce Labs
    String SAUCE_USER = "";
    String SAUCE_KEY = "";

    public ConfigUtils(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static HashMap<String, String> properties;


    public WebDriver webDriver;
    LogEntries logEntries;
    protected Properties propertiesData;

    public void getWebpage(String webPageName) {

        wdriver.get(webPageName);
    }

    public void findJavascriptErrors() {
        logEntries = wdriver.manage().logs().get(LogType.BROWSER);
        logConsoleEntries(logEntries);
    }

    public static boolean logConsoleEntries(LogEntries logEntries) {
        for (LogEntry logEntry : logEntries) {
            System.out.println(String.valueOf(" Time Stamp: " + logEntry.getTimestamp()));
            System.out.println(String.valueOf(" Log Level: " + logEntry.getLevel()));
            System.out.println(String.valueOf(" Log Message: " + logEntry.getMessage()));

            if (logEntry.getMessage().contains("TypeError") || logEntry.getMessage().contains("SyntaxError")) {
                return false;
            }
        }
        return true;
    }

    public void close() {

        wdriver.quit();
    }

    public void validateRenderingOfPage() {
        title = wdriver.getTitle();
        urlData = wdriver.getCurrentUrl();
        Assert.assertNotNull(title);
        Assert.assertNotNull(urlData);
    }

    public void validateStatus(String url) {
        try {
            int code = RestAssured.get(url).statusCode();
            Assert.assertEquals(200, code);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void findHyperlinks(String webUrl) throws IOException {
        Document doc = Jsoup.connect(webUrl).get();
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            if (link.attr("href").startsWith("https://www.mytheresa.com/en-de/men")) {
                validateStatus(link.attr("href"));
            }
        }
    }

    public boolean checkNewWIndow() {
        boolean flag = false;
        Stack<String> windowHandles = new Stack<>();
        String curWin = webDriver.getWindowHandle();
        for (String handle : wdriver.getWindowHandles()) {
            if (!handle.equals(curWin) && !windowHandles.contains(curWin)) {
                windowHandles.push(curWin);
                wdriver.switchTo().window(handle);
                wdriver.close();
                flag = true;
                break;
            }
        }

        return flag;
    }

//    public void setup(String webBrowser, String env) throws IOException {
//        if (env.equalsIgnoreCase("local")) {
//            if (webBrowser.equals("chrome")) {
//                setChrome();
//            } else if (webBrowser.equals("firefox")) {
//                setFirefox();
//            }
//        } else {
//            setupSaucelabs(webBrowser);
//        }
//
//        wdriver.manage().window().maximize();
//        wdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//    }

    public void handlePopUpWindow() {
        try {
            List<WebElement> buttons = wdriver.findElements(By.cssSelector("#acc-alert-close"));
            if (buttons.size() > 0) {
                System.out.println("Alert was found");
                buttons.get(0).click();
            }
        } catch (NullPointerException e) {
        }
    }

    public WebDriver getWebDriver() {
        return wdriver;
    }

    public void setChrome() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/Chrome/chromedriver");


        ChromeOptions options = new ChromeOptions();//Version 65.0.3325.181 (Official Build) (64-bit)
        // options.addExtensions(new File("src/test/resources/Chrome/extension_1_8.crx")); //'Css Helper' selectors extention
        options.addArguments("--disable-notifications");
        // options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        wdriver = (new ChromeDriver(options));
        //base.setDriver(wdriver);
    }

    public void setFirefox() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/Firefox/firefox.exe");
        wdriver = new FirefoxDriver();
    }

//    private void setupSaucelabs(String browser) throws IOException {
//
//        System.out.println("Setting sauce lab");
//        String sauceUser = SAUCE_USER;
//        String sauceKey = SAUCE_KEY;
//
//
//// Trying to add PROPERTY_FILE and DPROPERTY_PASSWORD
//
//        String rawFolder = Const.RESOURCE_PATH + Const.PLAIN_TEXT + File.separator;
//        System.setProperty("PROPERTY_FILE", "case_management.properties");
//        File rawProperties = new File(rawFolder + System.getProperty("PROPERTY_FILE"));
//        InputStream inputStream = new FileInputStream(rawProperties);
//        propertiesData = new Properties();
//        propertiesData.load(inputStream);
//        String pwd = propertiesData.getProperty("PROPERTY_PASSWORD");
//        System.setProperty("DPROPERTY_PASSWORD", pwd);
//
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PLATFORM, "Windows 10");
//        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
//        capabilities.setCapability(CapabilityType.VERSION, "latest");
//        capabilities.setCapability("seleniumVersion", "3.141.59");
////			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//        capabilities.setCapability("screenResolution", "2560x1600");
//        capabilities.setCapability("idleTimeout", 1000);
//        capabilities.setCapability("commandTimeout", 180);
//        capabilities.setCapability("recordScreenshots", false);
//        capabilities.setCapability("recordLogs", false);
//        capabilities.setCapability("captureHtml", true);
//        capabilities.setCapability("extendedDebugging", false);
//        capabilities.setCapability("recordVideo", true);
//        capabilities.setCapability("public", "private");
////		capabilities.setCapability("recordMp4", true);
//        capabilities.setCapability("name", getClass().getSimpleName());//Dysplays test name in Saucelab
//
//
//        if (browser.equalsIgnoreCase("chrome")) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--disable-notifications");
//            options.setCapability(CapabilityType.PLATFORM, "Windows 10");
//            capabilities.setCapability("screenResolution", "2560x1600");
//            options.setCapability(CapabilityType.VERSION, "latest");
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        }
//
//        capabilities.setCapability("build", System.getenv("BUILD_DISPLAY_NAME"));
//        System.out.println("https://****:****@ondemand.eu-central-1.saucelabs.com/wd/hub");
//
//        wdriver = (new RemoteWebDriver(
//                new URL("https://" + sauceUser + ":" + sauceKey
//                        + "@ondemand.eu-central-1.saucelabs.com/wd/hub"), capabilities));
//
//        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
//        sessionId = id;
//        wdriver.setFileDetector(new LocalFileDetector());//to upload from PC/Jenkins to SauceLabs
//    }

    public void doLogin(String userid, String password) throws InterruptedException {
        Actions actions = new Actions(wdriver);

        try {

            wdriver.switchTo().frame("privacy-iframe");
            WebElement cookiesOption = wdriver.findElement(By.xpath("//button[text()='Accept all and continue']"));
            actions.moveToElement(cookiesOption).click().build().perform();
            Thread.sleep(1000);
            wdriver.navigate().refresh();
            wdriver.switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement myAccount = wdriver.findElement(By.xpath("//a[@id='myaccount']"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(myAccount).click().build().perform();
        Thread.sleep(2000);
        WebElement email = wdriver.findElement(By.xpath("//div[@id='qa-login-email']"));
        actions.moveToElement(email).click().build().perform();
        actions.moveToElement(email).sendKeys(userid).build().perform();

        WebElement user_password = wdriver.findElement(By.xpath("//div[@id='qa-login-password']"));
        actions.moveToElement(user_password).click().build().perform();
        actions.moveToElement(user_password).sendKeys(userid).build().perform();

        WebElement loginButton = wdriver.findElement(By.xpath("//span[text()='Log in']"));
        actions.moveToElement(loginButton).click().build().perform();
        actions.moveToElement(loginButton).sendKeys(userid).build().perform();

        WebElement welcomeText = wdriver.findElement(By.xpath("//p[@class='hello hs1']"));
        String welcomeString = welcomeText.getText();
        System.out.print("HELLO, " + userid + "!");
        Assert.assertEquals(welcomeString, "HELLO, " + userid + "!");

        receivedUrl = wdriver.getCurrentUrl();
    }

    public void validateLogin() {
        validateStatus(receivedUrl);

    }

}
