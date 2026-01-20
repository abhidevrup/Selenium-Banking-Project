package com.ibm.banking.pages;

import org.openqa.selenium.By;
import com.ibm.framework.base.BasePage;

public class BillPaymentsPage extends BasePage {

    // --- Locators ---
    private By pageTitle = By.xpath("//h2[normalize-space()='Bill Payments']");
    private By searchInput = By.cssSelector("input[placeholder='Search billers (e.g. Electricity, Airtel, Credit Card)...']");
    private By transactionHistoryBtn = By.xpath("//button[normalize-space()='Transaction History']");
    
    // Sections
    private By recentPayersHeader = By.xpath("//h3[normalize-space()='Recent Payers']");
    private By categoriesHeader = By.xpath("//h3[normalize-space()='Categories']");
    private By upcomingBillsHeader = By.xpath("//h3[normalize-space()='Upcoming Bills']");

    // --- Actions ---

    public String getBillPageTitle() {
        return getText(pageTitle);
    }

    public boolean isRecentPayersDisplayed() {
        return isDisplayed(recentPayersHeader);
    }

    public boolean isCategoriesDisplayed() {
        return isDisplayed(categoriesHeader);
    }

    public boolean isUpcomingBillsDisplayed() {
        return isDisplayed(upcomingBillsHeader);
    }

    public void searchForBiller(String query) {
        // Assuming BasePage has a method 'type' or 'sendKeys'
        type(searchInput, query); 
    }

    public boolean isCategoryVisible(String categoryName) {
        // Constructing dynamic By locator
        By dynamicCategory = By.xpath("//span[text()='" + categoryName + "']/parent::button");
        return isDisplayed(dynamicCategory);
    }

    public boolean isBillProviderVisible(String providerName) {
        By dynamicProvider = By.xpath("//p[text()='" + providerName + "']");
        return isDisplayed(dynamicProvider);
    }

    public String getBillAmount(String providerName) {
        By dynamicAmount = By.xpath("//p[text()='" + providerName + "']/following-sibling::div/span");
        return getText(dynamicAmount).trim();
    }
    
    public String getBillDueDate(String providerName) {
        // Based on the OCR, the due date is in the header of the bill card
        // This XPath finds the provider, goes up to the card container, then finds the 'Due in' text
        By dynamicDate = By.xpath("//p[text()='" + providerName + "']/parent::div//span[contains(text(),'Due in')]");
        // This will return "Due in 2 days", so we might need to strip "Due in" in the step definition
        return getText(dynamicDate);
    }

    public void clickTransactionHistory() {
        click(transactionHistoryBtn);
    }
    
    public String getCurrentUrl() {
        // Standard BasePage usually exposes driver or has getUrl, 
        // if not, we might need: DriverManager.getDriver().getCurrentUrl();
        // For now, assuming a wrapper exists or we can access driver:
        return com.ibm.framework.driver.DriverFactory.getDriver().getCurrentUrl();
    }
}