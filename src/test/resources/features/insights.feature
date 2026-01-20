@regression @insights
Feature: Financial Insights Page validation

  Background:
    Given the user is logged into TDDBank
    And the user navigates to the Insights page

  @regression
  Scenario: Navbar should be visible on Insights page
    Then the navbar should be displayed
    
  @regression @transactions
  Scenario: View All Transactions button should work
    When the user clicks on View All Transactions
    Then the transactions section should be displayed
    
  @regression @filter
  Scenario: Filter by Month dropdown should open
    When the user clicks on Filter by Month
    Then the user should be able to select a month
    
  @regression @filter
  Scenario:Filter by Year dropdown should open
  	When the user clicks on Filter by Year
  	Then the user should be able to select a year

  @regression @timerange
  Scenario: Week Month Year buttons should be clickable
    Then the Week Month Year buttons should be clickable
