package BasePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public synchronized static WebDriver getDriver() {
        return driver.get();
    }

    public synchronized static void setDriver(String browserName) {

        if (browserName.equalsIgnoreCase("Chrome")) {
            System.out.println("Chrome browser will start!");
            driver = ThreadLocal.withInitial(() -> new ChromeDriver(BrowserOptions.chromeOptions()));
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            System.out.println("FireFox browser will start!");
            driver = ThreadLocal.withInitial(() -> new FirefoxDriver(BrowserOptions.firefoxOptions()));
        }else {
            throw new RuntimeException("Unsupported browser! will not start any browser");
        }
    }
}
