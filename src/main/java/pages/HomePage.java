package pages;

import base.CommonFunctions;
import org.openqa.selenium.By;

public class HomePage {
    private CommonFunctions common;

    private By loginLink = By.xpath("//a[@href='/login']");
    private By loggedInUser = By.xpath("//a[contains(text(),'Logged in as')]");

    public HomePage() {
        common = new CommonFunctions();
    }

    public void goToLoginPage() {
        common.click(loginLink);
    }

    public String getLoggedInUser() {
        return common.getText(loggedInUser);
    }

    public boolean isUserLoggedIn() {
        return common.isDisplayed(loggedInUser);
    }
}
