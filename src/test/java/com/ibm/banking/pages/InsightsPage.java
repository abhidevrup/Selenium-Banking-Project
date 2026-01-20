package com.ibm.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ibm.framework.base.BasePage;
import com.ibm.framework.driver.DriverFactory;

public class InsightsPage extends BasePage {

    // Navbar
    private By navbar = By.tagName("nav");

    // View all transactions
    private By viewAllTransactionsBtn =
            By.xpath("//button[contains(text(),'View All Transactions')]");

    // Filters
    By filterByMonthSelect = By.xpath("//label[text()='Filter by Month:']/following-sibling::select");
    private By filterByYearSelect =
            By.xpath("//label[text()='Filter by Year:']/following-sibling::select");


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
        return DriverFactory.getDriver().getCurrentUrl().contains("transactions");
    }

    public void clickFilterByMonth() {
        click(filterByMonthSelect);
    }
    
    public void clickFilterByYear() {
        click(filterByYearSelect);
    }

    public void selectMonth(String month) {
        Select select = new Select(waitForVisible(filterByMonthSelect));
        select.selectByVisibleText(month);
    }
    
    public void selectYear(String year) {
        Select select = new Select(waitForVisible(filterByYearSelect));
        select.selectByVisibleText(year);
    }

    public boolean areTimeRangeButtonsClickable() {
        try {
            click(weekBtn);
            click(monthBtn);
            click(yearBtn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
