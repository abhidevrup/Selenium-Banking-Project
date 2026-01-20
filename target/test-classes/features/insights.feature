Feature: Financial Insights Page validation

  Background:
    Given the user is logged into TDDBank
    And the user navigates to the Insights page

  Scenario: Navbar should be visible on Insights page
    Then the navbar should be displayed

  Scenario: View All Transactions button should work
    When the user clicks on View All Transactions
    Then the transactions section should be displayed

  Scenario: Filter by Month dropdown should open
    When the user clicks on Filter by Month
    Then the filter by month dropdown should be visible

  Scenario: Week Month Year buttons should be clickable
    Then the Week Month Year buttons should be clickable
