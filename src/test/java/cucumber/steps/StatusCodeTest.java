package cucumber.steps;

import BasePackage.BasePage;
import BasePackage.BaseTest;
import Pages.testCases.verifyStatusCode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StatusCodeTest {

	verifyStatusCode verifyStatusCodeObj;
    BasePage basePage;
    BaseTest baseTest = new BaseTest();
    

    public StatusCodeTest(BasePage basePage){
        this.basePage = basePage;
        this.verifyStatusCodeObj = new verifyStatusCode(basePage.getDriver());
    }

    @Given("User navigates to the url")
    public void userNavigatesToTheGiven() throws IOException {
        System.out.println("Test start execution successfully");
    }

    @And("User checks for hyperlink on the page {string}")
    public void userChecksForHyperlinkOnThePage(String url) throws IOException {
        verifyStatusCodeObj.findHyperlinks(url);
    }

    @Then("Webpage should return {int} or {int}x")
    public void webpage_should_return_or_x(Integer int1, Integer int2) throws FileNotFoundException {
        System.out.println("Test executed successfully");
    }
}