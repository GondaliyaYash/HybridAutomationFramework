@regression @filter
Feature: Product Filter Functionality

  Scenario: Verify user can apply all product filters and logout successfully
    Given user is on login page
    # Updated to match LoginSteps.java implementation
    When user logs in with "standard_user" and "secret_sauce"
    Then user should be redirected to home page

    When user applies filter "Name (A to Z)"
    When user applies filter "Name (Z to A)"
    When user applies filter "Price (low to high)"
    When user applies filter "Price (high to low)"

    Then all filters should be applied successfully
    # Matches the @Then in ProductFilterSteps.java
    And user logs out successfully