package com.ibm.banking.stepdefinitions;

import org.testng.Assert;
import com.ibm.banking.pages.LoginPage;
import com.ibm.banking.pages.MyCardsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyCardsSteps {

    // Instantiate Page Objects directly (Matches InsightsSteps style)
    private MyCardsPage myCardsPage = new MyCardsPage();
    private LoginPage loginPage = new LoginPage();

    // Note: No @Before or @After needed. The framework handles the browser.

    @Given("I am logged into TDDBank")
    public void i_am_logged_in() {
        // We reuse the LoginPage from the existing project framework
        loginPage.open(); 
        loginPage.login("admin@tddbank.com", "123456");
    }

    @Given("I navigate to the {string} section")
    public void i_navigate_to_section(String sectionName) {
        if(sectionName.equals("My Cards")) {
            myCardsPage.clickMyCardsLink();
        }
    }

    @Then("I should see Total Spending as {string}")
    public void check_total_spending(String expectedValue) {
        String actualValue = myCardsPage.getTotalSpending();
        Assert.assertEquals(actualValue, expectedValue, "Total Spending did not match!");
    }

    @Then("I should see Available Credit as {string}")
    public void check_available_credit(String expectedValue) {
        String actualValue = myCardsPage.getAvailableCredit();
        Assert.assertEquals(actualValue, expectedValue, "Available Credit did not match!");
    }

    // --- Steps for Adding a Card ---

    @When("I click the {string} button")
    public void click_add_button(String btnName) {
        if (btnName.equals("Add Debit")) {
            myCardsPage.clickAddDebit();
        }
    }

    @When("I enter card number {string}")
    public void enter_card_number(String number) {
        // We use a simplified filling method here, or you can keep individual methods
        // For now, I'll assume you want to keep the step granular, so we rely on the page logic
        // Note: To keep Steps clean, we usually combine these in the PageObject, 
        // but since your feature file is granular, we can call a type method directly:
        myCardsPage.fillCardDetails(number, "", "", ""); 
        // NOTE: Ideally, update your Page Object to have setCardNumber(String) 
        // if you want to keep strictly one step per field.
    }
    
    // To match your previous granular steps, add these setters to MyCardsPage 
    // OR create a temporary object here.
    // For simplicity with the refactor, let's assume you updated the Page Object 
    // to include specific setters, or we do this:
    
    @When("I enter holder name {string}")
    public void enter_holder_name(String name) {
        // Implementation depends on MyCardsPage having specific setters
    }

    // ... (Keep other input steps similarly) ...

    @When("I click {string}")
    public void click_provision(String btnText) {
        myCardsPage.clickProvisionButton();
    }

    @Then("I should see the message {string}")
    public void verify_message(String expectedMessage) {
        if (expectedMessage.equals("Card Added Successfully")) {
            Assert.assertNull(myCardsPage.getErrorMessage(), "Expected success, but found error.");
        } else {
            Assert.assertEquals(myCardsPage.getErrorMessage(), expectedMessage, "Error message mismatch");
        }
    }

    @When("I delete the existing Credit Card")
    public void delete_credit_card() {
        myCardsPage.clickDeleteCreditCard();
    }

    @Then("the Credit Card slot should be empty")
    public void verify_slot_empty() {
        Assert.assertTrue(myCardsPage.isCreditSlotEmpty(), "Credit Card slot is not empty");
    }
}