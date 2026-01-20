package com.ibm.banking.stepdefinitions;

import org.testng.Assert;

import com.ibm.banking.pages.BillPaymentsPage;
import com.ibm.banking.pages.DashboardPage;
import com.ibm.banking.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.ibm.framework.driver.DriverFactory;

public class BillPaymentSteps {

	// Initialize pages (BasePage handles the driver internally)
	private BillPaymentsPage billPage = new BillPaymentsPage();
	private DashboardPage dashboardPage = new DashboardPage();
	private LoginPage loginPage = new LoginPage();

	@Given("the user navigates to the Bill Payments page")
	public void user_navigates_to_bills() {
		// Ideally, DashboardPage should have a method clickBillPayments()
		// If it doesn't exist yet, we can force navigation via URL for now
		// or add the method to DashboardPage.

		// Option A: If DashboardPage has the method
		// dashboardPage.clickBillPayments(); 

		// Option B: Direct URL (Safe fallback for this task)
		com.ibm.framework.driver.DriverFactory.getDriver().get("https://tdd-banking-app.vercel.app/bills");
	}

	@Then("the Bill Payments page title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		Assert.assertEquals(billPage.getBillPageTitle(), expectedTitle);
	}

	@Then("the {string} section should be visible")
	public void section_should_be_visible(String section) {
		boolean isVisible = false;
		if (section.equals("Recent Payers")) {
			isVisible = billPage.isRecentPayersDisplayed();
		} else if (section.equals("Categories")) {
			isVisible = billPage.isCategoriesDisplayed();
		} else if (section.equals("Upcoming Bills")) {
			isVisible = billPage.isUpcomingBillsDisplayed();
		}
		Assert.assertTrue(isVisible, section + " section is not visible");
	}
	

	@When("the user enters {string} into the search bar")
	public void user_enters_search(String query) {
		billPage.searchForBiller(query);
	}

	@Then("the {string} category should be visible")
	public void category_should_be_visible(String categoryName) {
		Assert.assertTrue(billPage.isCategoryVisible(categoryName), 
				"Category " + categoryName + " is not visible");
	}

	@Then("a bill from {string} should be visible")
	public void bill_from_provider_should_be_visible(String provider) {
		Assert.assertTrue(billPage.isBillProviderVisible(provider), 
				"Bill provider " + provider + " not found");
	}

	@Then("the bill amount should be {string}")
	public void bill_amount_should_be(String expectedAmount) {
		// We assume the context is "Airtel Postpaid" based on the previous step
		String actualAmount = billPage.getBillAmount("Airtel Postpaid");
		Assert.assertEquals(actualAmount, expectedAmount);
	}

	@Then("the bill due date should be {string}")
	public void bill_due_date_should_be(String expectedDays) {
		String fullText = billPage.getBillDueDate("Airtel Postpaid"); // Returns "Due in 2 days"
		Assert.assertTrue(fullText.contains(expectedDays), 
				"Expected date to contain " + expectedDays + " but found " + fullText);
	}

	@When("the user clicks on the Transaction History button")
	public void user_clicks_transaction_history() {
		billPage.clickTransactionHistory();
	}

	@Then("the user should be redirected to the insights page")
	public void user_redirected_to_insights() {
	    // 1. Get the driver
	    WebDriver driver = DriverFactory.getDriver();
	    
	    // 2. Create a wait (explicit wait)
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    
	    try {
	        // 3. Wait until the URL contains "insights"
	        wait.until(ExpectedConditions.urlContains("insights"));
	        
	        // 4. Double check (Assertion)
	        String currentUrl = driver.getCurrentUrl();
	        Assert.assertTrue(currentUrl.contains("insights"), 
	            "URL did not change to insights. Current URL: " + currentUrl);
	            
	    } catch (Exception e) {
	        // This catches the error if 5 seconds pass and URL hasn't changed
	        Assert.fail("Timed out waiting for URL to contain 'insights'. verify the button click worked.");
	    }
	}
}