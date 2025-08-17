package stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.ProductPage;
import utils.LoggerUtil;

public class CartCheckoutSteps {

    private ProductPage productPage = new ProductPage();
    private CartPage cartPage = new CartPage();

    @When("User add product {string} to the cart {string}")
    public void addProductToCart(String productName, String message) {
        productPage.productsClick();
        productPage.enterSearchText(productName);
        productPage.addProductToCart(productName,message);
        LoggerUtil.getLogger().info("Added product to cart: " + productName);
    }

    @Then("the product {string} should be present in the cart")
    public void verifyProductInCart(String productName) {
        productPage.viewCartClick();
        boolean isPresent = cartPage.isProductInCart(productName);
        Assert.assertTrue("Product not found in cart: " + productName, isPresent);
        LoggerUtil.getLogger().info("Verified product in cart: " + productName);
    }

    @When("User proceed to checkout")
    public void proceedToCheckout() {
        cartPage.proceedToCheckoutIfNotEmpty();
        LoggerUtil.getLogger().info("Proceeded to checkout");
    }

    @Then("User should see the checkout page with order summary for {string}")
    public void verifyCheckoutPage(String productName) {
        boolean isDisplayed = cartPage.isProductInCart(productName);
        Assert.assertTrue("Checkout page not displayed or product missing: " + productName, isDisplayed);
        LoggerUtil.getLogger().info("Verified checkout page with product: " + productName);
    }

    @And("User proceed to cartPage")
    public void userProceedToCartPage() {
        cartPage.cartBtnClick();
    }

    @Then("User should see empty cart message")
    public void verifyEmptyCart() {
        boolean isEmpty = cartPage.isCartEmpty();
        Assert.assertTrue("Empty cart message is not displayed", isEmpty);
        LoggerUtil.getLogger().info("Verified empty cart message");
    }
}
