package Pages.testCases;

import BasePackage.BasePage;
import BasePackage.BaseTest;
import Utilities.ObjectRepositoryJsonParser;
import Utilities.TimeUtils;
import Utilities.WriteExcel;
import com.codoid.products.exception.FilloException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class prNumber {

    public WebDriver webDriver;
    String currentUsersHomeDir = System.getProperty("user.dir");
    String file = currentUsersHomeDir + File.separator + "prFile.csv";
    public static String prTitle;
    public static String prCreatedDate;

    public prNumber(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void getWebpage(String webPageName) {
        BaseTest.driver.get(webPageName);
    }

    public void findPRNumbers() throws IOException, FilloException {
        WriteExcel.delete();
        try {
            WebElement nextBtn = ObjectRepositoryJsonParser.getObjectLocator("$.prPage.nextButton");
            boolean nextStatus = nextBtn.isDisplayed();
            System.out.println("Next button status : " + nextBtn.isEnabled());
            while (nextStatus) {
                WebElement allPullRequestsCount = ObjectRepositoryJsonParser.getObjectLocator("$.prPage.allPullRequestsCount");
                String pullRequestsCount = allPullRequestsCount.getText();
                System.out.println("Total Number of PR: " + pullRequestsCount);

                List<WebElement> openPR = ObjectRepositoryJsonParser.getObjectLocatorList("$.prPage.prList");
                int prCount = openPR.size();
                System.out.println("Number of PR: " + prCount);

                List<WebElement> prDate = ObjectRepositoryJsonParser.getObjectLocatorList("$.prPage.prDate");
                int ptDateCreated = prDate.size();

                Iterator<WebElement> it = openPR.iterator();
                for (int i = 0; i < openPR.size(); i++) {
                    WebElement pr;
                    pr = it.next();
                    prTitle = pr.getText();
//                    System.out.println("PR Details: " + prTitle);

                    WebElement prTime = prDate.get(i);
                    prCreatedDate = prTime.getText();
//                    System.out.println("PR Created Date: " + prCreatedDate);
                    WriteExcel.write();
                }
                nextBtn = ObjectRepositoryJsonParser.getObjectLocator("$.prPage.nextButton");
                TimeUtils.explicitWait(nextBtn, 10);
                TimeUtils.mediumWait();
                nextBtn = ObjectRepositoryJsonParser.getObjectLocator("$.prPage.nextButton");
                nextBtn.click();
                System.out.println(BasePage.getPageCurrentURL());
                TimeUtils.mediumWait();
            }
        } catch (NoSuchElementException e) {
            System.out.println("You're in the final page of the pull requests");
        }
    }
}