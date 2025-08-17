package pages;

import base.CommonFunctions;
import org.openqa.selenium.By;

public class LoginPage {
    private CommonFunctions common;

    private By emailField = By.xpath("//input[@data-qa='login-email']");
    private By passwordField = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");


    public LoginPage() {
        common = new CommonFunctions();
    }

    public void login(String email, String password) {
        common.sendKeys(emailField,email);
        common.sendKeys(passwordField,password);
        common.click(loginBtn);
    }

    public boolean isErrorDisplayed(String message) {
        By loginError = By.xpath("//*[contains(text(),'"+message+"')]");
        return common.isDisplayed(loginError);
    }
}
