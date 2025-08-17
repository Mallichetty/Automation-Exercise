package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utils.LoggerUtil;

public class LoginPageSteps {

    private LoginPage loginPage;

    public LoginPageSteps() {
        loginPage = new LoginPage();
    }

    @When("User login with username {string} and password {string}")
    public void loginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
        LoggerUtil.getLogger().info("Attempted login with username: " + username);
    }

    @Then("User should see {string} message")
    public void verifyLoginError(String message) {
        boolean isError = loginPage.isErrorDisplayed(message);
        Assert.assertTrue(message+" not displayed", isError);
        LoggerUtil.getLogger().info(message +" error message");
    }

}
