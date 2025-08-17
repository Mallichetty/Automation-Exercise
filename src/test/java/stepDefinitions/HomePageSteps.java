package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.HomePage;
import utils.LoggerUtil;

public class HomePageSteps {

    private HomePage homePage = new HomePage();

    @Given("User is on the home page")
    public void userOnHomePage() {
        LoggerUtil.getLogger().info("Navigated to home page");
    }

    @Then("User should see {string}")
    public void userShouldSee(String expectedText) {
        String actualText = homePage.getLoggedInUser();
        Assert.assertEquals("Login verification failed", expectedText, actualText);
        LoggerUtil.getLogger().info("Verified text on page: " + expectedText);
    }
}
