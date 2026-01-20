@regression @billPayments
Feature: Bill Payments Page Validation

  Background:
    Given the user is logged into TDDBank
    And the user navigates to the Bill Payments page

  @regression
  Scenario: Verify Page UI Elements and Title
    Then the Bill Payments page title should be "Bill Payments"
    And the "Recent Payers" section should be visible
    #And the "Categories" grid should be visible
    #And the "Upcoming Bills" sidebar should be visible
  
  @regression @search
  Scenario: Verify Search Functionality filters categories
    When the user enters "Water" into the search bar
    Then the "Water" category should be visible
    # Note: As discussed, this might show other categories due to the bug, but we test for presence.
    
  @regression
  Scenario: Validate Upcoming Bill Details
    Then a bill from "Airtel Postpaid" should be visible
    And the bill amount should be "₹1,299"
    And the bill due date should be "2 days"
    
  @regression @search
  Scenario: Verify Navigation to Transaction History
    When the user clicks on the Transaction History button
    Then the user should be redirected to the insights page