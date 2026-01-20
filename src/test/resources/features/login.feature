@smoke @login
Feature: SauceDemo Login

  Scenario Outline: Valid user login using scenario outline
    Given user is on login page
    When user logs in with "<username>" and "<password>"
    Then user should be redirected to home page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
