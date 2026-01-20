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
    public void enter_card_number(String number) throws InterruptedException {
       
    	
        myCardsPage.enterCardNumber(number); 
        
    }
    
    
    
    @When("I enter holder name {string}")
    public void enter_holder_name(String name) {
       
    	myCardsPage.enterHolderName( name); 
    }

   
    @When("I enter expiry {string}")
    public void i_enter_expiry(String string) {
        
    	myCardsPage.enterExpiry(string); 
        
    }

    @When("I enter CVV {string}")
    public void i_enter_cvv(String string) {
        
    	myCardsPage.enterCVV(string); 
        
    }


    @When("I click {string}")
    public void click_provision(String btnText){
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
        Assert.assertEquals(myCardsPage.getCreditSlotMessage(),"ADD CREDIT", "Credit Card slot is not empty");
    }
}