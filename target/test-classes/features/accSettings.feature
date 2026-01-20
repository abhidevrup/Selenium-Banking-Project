Feature: Account Settings Page UI Verification

  As a registered user of TDD Bank
  I want to navigate to my Account Settings
  So that I can verify my personal details, security options, and account type are visible

  Background: 
    Given the user is on the TDDBank login page
    And the user logs in using email "user@tddbank.com" and password "password"

  Scenario: Verify UI layout and section visibility in Account Settings
    Given the user is on the dashboard
    When the user clicks on the "Test User" navbar dropdown
    And the user selects "Account Settings" from the dropdown menu
    Then the user should be redirected to the settings page
    And the following sections should be visible:
      | Section Name      | Visibility |
      | Personal Details  | Visible    |
      | Security          | Visible    |
      | Account Type      | Visible    |
    