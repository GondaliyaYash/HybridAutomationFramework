Feature: Logout

  @smoke
  Scenario: User logs out successfully
    Given user is logged in
    When user clicks logout
    Then user should be redirected to login page
