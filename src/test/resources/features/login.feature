@smoke
Feature: SauceDemo Login

  Scenario: Valid user should login successfully using Excel data
    Given user is on login page
    When user enters valid credentials from excel
    Then user should be redirected to home page
    Then take manual screenshot
    