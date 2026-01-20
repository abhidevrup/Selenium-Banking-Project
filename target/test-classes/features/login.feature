@smoke @regression @login
Feature: Login Functionality for TDD Bank

  Background: 
    Given the user is on the TDDBank login page

  @smoke @regression @positive
  Scenario Outline: Successful login with valid role-based credentials
    When the user logs in using email "<email>" and password "<password>"
    Then the user should be redirected to the dashboard

    Examples:
      | email               | password     |
      | admin@tddbank.com   | 123456       |
      | manager@tddbank.com | bankmanager  |
      | user@tddbank.com    | password     |
  
  @regression @negative
  Scenario Outline: Unsuccessful login with invalid credentials
    When the user logs in using email "<email>" and password "<password>"
    Then the user should see an error message 

    Examples:
      | email                   | password     |
      | admin@tddbank.com       | password     |  # Correct email, wrong pass
      | unknown@tddbank.com     | bankmanager  |  # Unregistered email
      | user1@tddbank.com       | 123456       |  # Non-existent user