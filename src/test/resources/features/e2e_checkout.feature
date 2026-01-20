@e2e @regression
Feature: End-to-End Web Shop Flow

  Scenario: User buys products and logs out
    Given user is logged in
    When user adds "Sauce Labs Backpack" to the cart
    And user adds "Sauce Labs Bike Light" to the cart
    And user opens the cart
    Then "Sauce Labs Backpack" should be visible in the cart
    And "Sauce Labs Bike Light" should be visible in the cart
    
    When user clicks checkout button
    And user enters shipping details as "John", "Doe", "12345"
    And user clicks finish button
    Then "Thank you for your order!" message should be displayed
    
    When user clicks back home button
    And user clicks logout
    Then user should be redirected to login page