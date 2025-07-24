@login
Feature: OrangeHRM Login

  Scenario Outline: Login attempt with various credentials
    Given the user is on the OrangeHRM login page
    When the user enters username "<username>" and password "<password>"
    And clicks the login button
    Then the user should see a message "<expectedResult>"

    Examples:
      | username  | password  | expectedResult      |
      | Admin     | admin123  | Dashboard visible   |
      | Admin     | wrongpass | Invalid credentials |
      | wronguser | admin123  | Invalid credentials |
