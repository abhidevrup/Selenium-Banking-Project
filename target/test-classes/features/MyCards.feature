Feature: TDDBank Card Dashboard
  # This feature file describes how a user interacts with the Cards page.

  Background:
  	Given I am logged into TDDBank
    #Given I open the TDDBank Dashboard
    And I navigate to the "My Cards" section

  Scenario: Verify Dashboard Numbers
    # We want to make sure the money shown on the screen is correct
    Then I should see Total Spending as "₹12,840"
    And I should see Available Credit as "₹50,000"

  Scenario Outline: Add a New Debit Card
    # We test adding a card with valid and invalid data
    When I click the "Add Debit" button
    And I enter card number "<CardNumber>"
    And I enter holder name "<HolderName>"
    And I enter expiry "<Expiry>"
    And I enter CVV "<CVV>"
    And I click "Provision Card"
    Then I should see the message "<ExpectedMessage>"

    Examples:
      | CardNumber       | HolderName | Expiry | CVV | ExpectedMessage          |
      | 5421889012345678 | John Doe   | 12/28  | 123 | Card Added Successfully  |
      | 1234             | John Doe   | 12/28  | 123 | Invalid card number length|
      | 5421889012345678 |            | 12/28  | 123 | Cardholder name required |
      | 5421889012345678 | John Doe   | 12/28  |     | CVV required              |

  Scenario: Delete the Credit Card
    When I delete the existing Credit Card
    Then the Credit Card slot should be empty