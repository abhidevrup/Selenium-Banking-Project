package com.ibm.banking.stepdefinitions;

import org.testng.Assert;

import com.ibm.banking.pages.DashboardPage;
import com.ibm.banking.pages.InsightsPage;
import com.ibm.banking.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InsightsSteps {

    private DashboardPage dashboardPage = new DashboardPage();
    private InsightsPage insightsPage = new InsightsPage();

    @Given("the user is logged into TDDBank")
    public void user_is_logged_in() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("admin@tddbank.com", "123456");

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard not displayed after login"
        );
    }

    @Given("the user navigates to the Insights page")
    public void user_navigates_to_insights() {
        dashboardPage.openInsights();
    }

    @Then("the navbar should be displayed")
    public void navbar_should_be_displayed() {
        Assert.assertTrue(
                insightsPage.isNavbarVisible(),
                "Navbar is not visible on Insights page"
        );
    }

    @When("the user clicks on View All Transactions")
    public void user_clicks_view_all_transactions() {
        insightsPage.clickViewAllTransactions();
    }

    @Then("the transactions section should be displayed")
    public void transactions_section_should_be_displayed() {
        Assert.assertTrue(
                insightsPage.isTransactionsSectionVisible(),
                "Transactions section not visible"
        );
    }

    @When("the user clicks on Filter by Month")
    public void user_clicks_filter_by_month() {
        insightsPage.clickFilterByMonth();
    }

    @Then("the filter by month dropdown should be visible")
    public void filter_dropdown_should_be_visible() {
        Assert.assertTrue(
                insightsPage.isFilterDropdownVisible(),
                "Filter by month dropdown not visible"
        );
    }

    @Then("the Week Month Year buttons should be clickable")
    public void week_month_year_buttons_should_be_clickable() {
        Assert.assertTrue(
                insightsPage.areTimeRangeButtonsClickable(),
                "Week / Month / Year buttons are not clickable"
        );
    }
}
