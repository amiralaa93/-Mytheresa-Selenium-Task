package BasePackage;

import Utilities.TimeUtils;
import Utilities.WebEventListener;
import com.saucelabs.saucerest.DataCenter;
import com.saucelabs.saucerest.SauceREST;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static Loggers.Log4JLogger.logger;

public class BaseTest extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    private static EventFiringWebDriver firingWebDriver;
    private static WebEventListener eventListener;
    static final String HOST_URL = "http://localhost:4444/wd/hub";
    protected RemoteWebDriver rdriver;
    public TestRule watcher;
    private String testName;
    private String sessionId;
    private SauceREST sauceClient;

    public void setup(String browserName, String environment) throws IOException {
        logger.info("****************************** Starting test cases execution on " + environment + " environment " + "*****************************************");
        DriverFactory.setDriver(browserName);
        TimeUtils.pageLoadTimeout(150);
        TimeUtils.implicitlyWait(50);
        TimeUtils.setScriptTimeout(150);
        ChromeOptions opt = new ChromeOptions();
        if (environment.equalsIgnoreCase("Docker")) {
            try {
                driver = new RemoteWebDriver(new URL(HOST_URL), opt);
            } catch (MalformedURLException e) {
                System.out.println("Unable to Connect Remote Driver");
            }
        } else if (environment.equalsIgnoreCase("SauceLabs")) {
            setupSauceLabs();
        }
        DriverFactory.getDriver().manage().deleteAllCookies();
        driver = DriverFactory.getDriver();
        firingWebDriver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        firingWebDriver.register(eventListener);
        driver = firingWebDriver;
        driver.manage().window().maximize();
    }

    private void setupSauceLabs() throws IOException {

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("name", "LoginTestCase");
        sauceOptions.put("idleTimeout", "300");
        sauceOptions.put("commandTimeout", "300");
        sauceOptions.put("recordVideo", true);
        sauceOptions.put("recordScreenshots", true);
        options.setCapability("sauce:options", sauceOptions);

        String SAUCE_USER = "oauth-amir.alaa93-cdc7c";
        String SAUCE_KEY = "e6b594de-c1d6-4a2f-a018-7e2f2f49ac45";
        URL url = new URL("https://" + SAUCE_USER + ":" + SAUCE_KEY
                + "@ondemand.eu-central-1.saucelabs.com/wd/hub");
        driver = new RemoteWebDriver(url, options);
        sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        sauceClient = new SauceREST(SAUCE_USER, SAUCE_KEY, DataCenter.US);

        watcher = new TestWatcher() {
            @Override
            protected void starting(Description description) {
                super.starting(description);
                testName = description.getDisplayName();
            }

            @Override
            protected void succeeded(Description description) {
                super.succeeded(description);
                sauceClient.jobPassed(sessionId);
            }

            @Override
            protected void failed(Throwable e, Description description) {
                super.failed(e, description);
                sauceClient.jobFailed(sessionId);
                System.out.println(String.format("https://saucelabs.com/tests/%s", sessionId));
            }
        };
    }

    public synchronized void tearDown() throws FileNotFoundException {
        logger.info("****************************** Browser is closed *****************************************");
        DriverFactory.getDriver().quit();
    }
}
