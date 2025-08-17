package pages;

import base.CommonFunctions;
import org.openqa.selenium.By;
import utils.LoggerUtil;

import java.util.List;

public class CartPage {
    private CommonFunctions common;

    private By cartBtn = By.xpath("//a[text()=' Cart']");
    private By proceedToChkOut = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    private By cartRows = By.xpath("//td[@class='cart_description']//a");

    public CartPage() {
        common = new CommonFunctions();
    }

    public void cartBtnClick() {
        common.click(cartBtn);
    }

    public boolean isProductInCart(String productName) {
        By productRow = By.xpath("//td[@class='cart_description']//a[contains(text(),'" + productName + "')]");
        return common.isDisplayed(productRow);
    }

    public void proceedToCheckout() {
        common.click(proceedToChkOut);
    }

    public boolean isCartEmpty() {
        List<?> items = common.getElements(cartRows);
        return items.isEmpty();
    }

    public boolean proceedToCheckoutIfNotEmpty() {
        if (isCartEmpty()) {
            LoggerUtil.getLogger().info("Cart is empty. Cannot proceed to checkout.");
            return false;
        } else {
            proceedToCheckout();
            return true;
        }
    }
}
