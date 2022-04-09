package Pages.testCases;

import BasePackage.BasePage;
import BasePackage.BaseTest;
import Utilities.JSUtils;
import Utilities.ObjectRepositoryJsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class loginTestCase extends BasePage {

    public WebDriver webDriver;
    String receivedUrl;
    SoftAssert softAssert = new SoftAssert();

    public loginTestCase(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void getWebpage(String webPageName) {
        BaseTest.driver.get(webPageName);
    }

    public void userLogin(String userid, String password) throws InterruptedException {
        Actions actions = new Actions(BaseTest.driver);

        try {
            BaseTest.driver.switchTo().frame("privacy-iframe");
            WebElement cookiesOption = BaseTest.driver.findElement(By.xpath("//button[text()='Accept all and continue']"));
            actions.moveToElement(cookiesOption).click().build().perform();
            Thread.sleep(1000);
            BaseTest.driver.navigate().refresh();
            BaseTest.driver.switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement myAccount = ObjectRepositoryJsonParser.getObjectLocator("$.login.myAccount");
        JSUtils.clickElementByJS(myAccount);
        Thread.sleep(2000);

        WebElement emailTextBox = ObjectRepositoryJsonParser.getObjectLocator("$.login.emailTextBox");
        elementSendKeys(emailTextBox, userid);
        Thread.sleep(2000);

        WebElement passwordTextBox = ObjectRepositoryJsonParser.getObjectLocator("$.login.passwordTextBox");
        elementSendKeys(passwordTextBox, password);
        Thread.sleep(2000);

        WebElement loginButton = ObjectRepositoryJsonParser.getObjectLocator("$.login.loginButton");
        elementClick(loginButton);
        Thread.sleep(2000);

        WebElement welcomeMsg = ObjectRepositoryJsonParser.getObjectLocator("$.login.welcomeMsg");
        String welcomeMessage = welcomeMsg.getText();
        System.out.println(welcomeMessage);
        softAssert.assertTrue(welcomeMessage.contains("HELLO"), "Welcome Message is incorrect");
        Thread.sleep(2000);
    }

    public void validateLogin() {
        receivedUrl = BaseTest.driver.getCurrentUrl();
        System.out.println(receivedUrl);
        softAssert.assertEquals(receivedUrl, "https://www.mytheresa.com/int_en/customer/account/index/", "Login URL is incorrect");
        softAssert.assertAll();
    }
}