package pages;

import base.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LoggerUtil;

import java.util.List;

public class ProductPage {
    private CommonFunctions common;

    private By viewCartBtn = By.xpath("//u[text()='View Cart']");
    private By productBtn = By.xpath("//*[text()=' Products']");
    private By enterSearchTxt = By.id("search_product");
    private By searchBtn = By.id("submit_search");

    public ProductPage() {
        common = new CommonFunctions();
    }

    public void productsClick() {
        common.click(productBtn);
    }

    public void enterSearchText(String text) {
        common.sendKeys(enterSearchTxt, text);
        common.click(searchBtn);
    }

    public void addProductToCart(String productName, String message) {
        By productLocator = By.xpath("//*[text()='" + productName + "']");
        List<WebElement> products = common.getElements(productLocator);

        if (!products.isEmpty()) {
            LoggerUtil.getLogger().info("Product found: " + productName + " Adding to cart...");
            By addToCartBtn = By.xpath("//*[text()='" + productName + "']/ancestor::div[@class='productinfo text-center']//a[contains(text(),'Add to cart')]");
            common.moveToElementAction(addToCartBtn);
            common.click(addToCartBtn);
        } else {
            LoggerUtil.getLogger().warn("Product not found: " + productName + " " + message);
        }
    }


    public void viewCartClick() {
        common.click(viewCartBtn);
    }


}
