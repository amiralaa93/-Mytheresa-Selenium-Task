package cucumber.steps;

import BasePackage.BasePage;
import BasePackage.BaseTest;
import Pages.testCases.loginTestCase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginTestCaseTest {

    BasePage basePage;
    BaseTest baseTest = new BaseTest();
    loginTestCase loginTestCaseObj;

    public LoginTestCaseTest(BasePage basePage){
        this.basePage = basePage;
        this.loginTestCaseObj =  new loginTestCase(basePage.getDriver());
    }

    @Given("User opens the webbrowser {string} with environment {string}")
    public void user_opens_the_webbrowser_with_environment(String browser, String env) throws IOException {
        baseTest.setup(browser,env);
        System.out.println("The browser is " + browser + " and the environment is " + env);
    }

    @Given("User navigates to the given url  {string}")
    public void user_navigates_to_the_given_url(String webPage) {
        loginTestCaseObj.getWebpage(webPage);
    }

    @When("User provides {string} and {string}")
    public void user_provides_and(String Userid, String Password) throws InterruptedException {
        loginTestCaseObj.userLogin(Userid, Password);
    }

    @Then("User Login should happened successfully")
    public void user_Login_should_happened_successfully() throws FileNotFoundException {
        loginTestCaseObj.validateLogin();
        baseTest.tearDown();
    }
}
