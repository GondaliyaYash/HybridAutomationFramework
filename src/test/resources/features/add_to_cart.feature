Feature: Add product to cart and logout

  @smoke @cart @logout
  Scenario: User adds multiple products to cart and logs out successfully
    Given user is logged in
    When user adds "Sauce Labs Backpack" to the cart
    And "Sauce Labs Backpack" add button should change to "Remove"
    And user adds "Sauce Labs Bike Light" to the cart
    And "Sauce Labs Bike Light" add button should change to "Remove"
    Then cart badge should display "2"
    And "Sauce Labs Backpack" should be visible in the cart
    And "Sauce Labs Bike Light" should be visible in the cart
    When user clicks logout
    Then user should be redirected to login page