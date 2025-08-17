Feature: Shopping Cart and Checkout

  Background:
    Given User is on the home page
    When User navigate to the login page

  @Positive
  Scenario: Add products to checkout and verify its summary
    And User login with username "madhushree0521@gmail.com" and password "123"
    Then User should see "Logged in as testuser"
    When User add product "Blue Top" to the cart ""
    Then the product "Blue Top" should be present in the cart
    And User proceed to checkout
    Then User should see the checkout page with order summary for "Blue Top"

  @Negative
  Scenario Outline: Verify error message for invalid credentials
    And User login with username "<username>" and password "<password>"
    Then User should see "<loginMessage>" message

    Examples:
      | username              | password      | loginMessage                        |
      | wronguser@example.com | wrongpassword | Your email or password is incorrect |

  @Negative
  Scenario Outline: Verify error message for invalid product
    And User login with username "<username>" and password "<password>"
    Then User should see "<loginMessage>"
    When User add product "<product>" to the cart "<errorMessage>"

    Examples:
      | username                 | password | loginMessage          | product | errorMessage      |
      | madhushree0521@gmail.com | 123      | Logged in as testuser | Test    | Product not found |

  @Negative
  Scenario Outline: Verify error messages for invalid actions
    When User proceed to cartPage
    Then User should see "<errorMessage>" message

    Examples:
      | errorMessage   |
      | Cart is empty! |