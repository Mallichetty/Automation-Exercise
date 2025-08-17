package stepDefinitions;

import io.cucumber.java.en.When;
import pages.HomePage;
import utils.LoggerUtil;

public class CommonSteps {

    private HomePage homePage = new HomePage();

    @When("User navigate to the login page")
    public void navigateToLoginPage() {
        homePage.goToLoginPage();
        LoggerUtil.getLogger().info("Navigated to login page");
    }
}
