package com.ibm.banking.pages;

import org.openqa.selenium.By;

import com.ibm.framework.base.BasePage;

public class InsightsPage extends BasePage {

    // Navbar
    private By navbar = By.tagName("nav");

    // View all transactions
    private By viewAllTransactionsBtn =
            By.xpath("//a[contains(text(),'View All Transactions')]");

    private By transactionsSection =
            By.xpath("//h2[contains(text(),'Recent Transactions')]");

    // Filters
    private By filterByMonthDropdown =
            By.xpath("//button[contains(text(),'All Months')]");

    private By filterDropdownPanel =
            By.xpath("//div[contains(@class,'dropdown')]");

    // Time range buttons
    private By weekBtn  = By.xpath("//button[text()='Week']");
    private By monthBtn = By.xpath("//button[text()='Month']");
    private By yearBtn  = By.xpath("//button[text()='Year']");

    /* Actions */

    public boolean isNavbarVisible() {
        return isDisplayed(navbar);
    }

    public void clickViewAllTransactions() {
        click(viewAllTransactionsBtn);
    }

    public boolean isTransactionsSectionVisible() {
        return isDisplayed(transactionsSection);
    }

    public void clickFilterByMonth() {
        click(filterByMonthDropdown);
    }

    public boolean isFilterDropdownVisible() {
        return isDisplayed(filterDropdownPanel);
    }

    public boolean areTimeRangeButtonsClickable() {
        return isDisplayed(weekBtn)
                && isDisplayed(monthBtn)
                && isDisplayed(yearBtn);
    }
}
