package com.ibm.banking.pages;

import org.openqa.selenium.By;
import com.ibm.framework.base.BasePage;

public class MyCardsPage extends BasePage {

	// --- Locators ---
	// Changed access modifier to 'private' to match InsightsPage style
	private By totalSpendingText = By.xpath("//div[text()='Total Spending']/following-sibling::div");
	private By availableCreditText = By.xpath("//div[contains(text(),'₹50,000')]");

	// Add/Manage Card Elements
	private By addDebitButton = By.xpath("/html/body/div[2]/main/section[1]/div[2]/div[2]/button");
	private By provisionCardBtn = By.xpath("//button[normalize-space()='Provision Card']");
	private By cardArea = By.xpath("//div[@class='space-y-6']/div[2]/div");

	// Inputs
	private By cardNumberInput = By.cssSelector("input[placeholder='CARD NUMBER']");
	private By cardHolderInput = By.cssSelector("input[placeholder='CARDHOLDER FULL NAME']");
	private By expiryInput     = By.cssSelector("input[placeholder='MM/YY']");
	private By cvvInput        = By.cssSelector("input[placeholder='CVV']");

	// Error & Delete
	private By errorMessage    = By.xpath("//form/p");
	private By deleteCreditBtn = By.xpath("(//button[@class='absolute -top-3 -right-3 p-2.5 bg-red-500 text-white rounded-full shadow-xl opacity-0 group-hover:opacity-100 transition-all hover:bg-red-600 focus:opacity-100 scale-75 group-hover:scale-100 z-20 border-4 border-white'])[1]");
	private By addCreditSlot   = By.xpath("//div[@class='text-center']/span[1]");

	// Navigation (If not already in DashboardPage)
	private By myCardsLink     = By.xpath("//body//main//div[4]");

	// --- Actions ---

	public void clickMyCardsLink() {
		click(myCardsLink);
	}

	public String getTotalSpending() {
		// Using BasePage method for text retrieval
		return getText(totalSpendingText);
	}

	public String getAvailableCredit() {
		return getText(availableCreditText);
	}
	
	public String getCreditSlotMessage() {
		return getText(addCreditSlot);
	}

	public void clickAddDebit() {
		click(addDebitButton);
	}

	// Combined method for filling details (cleaner)
	public void enterCardNumber(String number) {
	    type(cardNumberInput, number);
	}

	public void enterHolderName(String holder) {
	    type(cardHolderInput, holder);
	}

	public void enterExpiry(String expiry) {
	    type(expiryInput, expiry);
	}

	public void enterCVV(String cvv) {
	    type(cvvInput, cvv);
	}

	public void clickProvisionButton() {
		click(provisionCardBtn);
	}

	public String getErrorMessage() {
		if (isDisplayed(errorMessage)) {
			return getText(errorMessage);
		}
		return null;
	}

	public void clickDeleteCreditCard() {
		hover(cardArea);
		click(deleteCreditBtn); 
	}

	
}