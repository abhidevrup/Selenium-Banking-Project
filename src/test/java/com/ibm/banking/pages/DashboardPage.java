package com.ibm.banking.pages;

import org.openqa.selenium.By;

import com.ibm.framework.base.BasePage;

public class DashboardPage extends BasePage {
	
    private By totalAvailableBalanceLabel =
            By.xpath("//p[text()='Total Available Balance']");
    
    private By insightsCard =
            By.xpath("//h3[text()='Insights']/ancestor::div[contains(@class,'cursor-pointer')]");
	
    public boolean isDashboardDisplayed() {
        return isDisplayed(totalAvailableBalanceLabel);
    }
    public void openInsights() {
        click(insightsCard);
    }
}
