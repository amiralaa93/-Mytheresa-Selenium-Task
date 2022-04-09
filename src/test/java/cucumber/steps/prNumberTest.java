package cucumber.steps;

import BasePackage.BasePage;
import BasePackage.BaseTest;
import Pages.testCases.prNumber;
import com.codoid.products.exception.FilloException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;
import java.io.IOException;

public class prNumberTest {

    BasePage basePage;
    prNumber prNumber;
    BaseTest baseTest = new BaseTest();

    public prNumberTest(BasePage basePage){
        this.basePage = basePage;
        this.prNumber = new prNumber(basePage.getDriver());
    }


    @Given("User open the browser {string} with environment {string}")
    public void user_open_the_browser_with_environment(String browser, String env) throws IOException {
        baseTest.setup(browser,env);
        System.out.println("The browser is " + browser + " and the environment is " + env);
    }

    @Given("User navigate to the given  {string}")
    public void userNavigateToTheGiven(String webPage) {
        prNumber.getWebpage(webPage);
    }

    @When("User checks the number of PR")
    public void userChecksTheNumberOfPR() throws IOException, FilloException {
        prNumber.findPRNumbers();
    }

    @Then("Number of PR displayed")
    public void numberOfPRDisplayed() throws FileNotFoundException {
        baseTest.tearDown();
    }
}
