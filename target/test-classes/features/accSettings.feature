Feature: Account Settings Page Functionality

  Background: 
    Given the user is on the TDDBank login page
    And the user logs in using email "user@tddbank.com" and password "password"
    
    And the user navigates to "Account Settings" from the navbar

  Scenario: Verify UI layout and section visibility
    Then the user should be redirected to the settings page
    And the following sections should be visible:
      | Section Name      | Visibility |
      | Personal Details  | Visible    |
      | Security          | Visible    |
      | Account Type      | Visible    |
    
  Scenario Outline: Successfully update profile information
    When the user clicks the "Edit" option for "Personal and Contact Info"
    And the user updates the "<Field>" field with "<NewValue>"
    And the user clicks the "Save Changes" button
    Then a success message "Account details updated successfully!" should be displayed
    And the "<Field>" section should display "<NewValue>"

    Examples:
      | Field        | NewValue                      |
      | FULL NAME   | Demo User                      |
      | MOBILE NUMBER | +1 555-0123-456              |
      |  ADDRESS      | 123 Banking Lane, Test Colony |