package com.ibm.framework.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.framework.driver.DriverFactory;

public class BasePage {
	
	private final int WAIT_TIME = 10;
	
	protected WebElement waitForVisible(By locator) {
		return new WebDriverWait(DriverFactory.getDriver(),Duration.ofSeconds(WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected WebElement waitForClickable(By locator) {
		return new WebDriverWait(DriverFactory.getDriver(),Duration.ofSeconds(WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	protected void click(By locator) {
		waitForClickable(locator).click();
	}
	
	protected void type(By locator,String value) {
		WebElement element = waitForVisible(locator);
		element.clear();
		element.sendKeys(value);
	}
	
	protected String getText(By locator) {
		return waitForVisible(locator).getText();
	}
	
	protected Boolean isDisplayed(By locator) {
		try {
			return waitForVisible(locator).isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	protected void jsClick(By locator) {
		WebElement element = waitForVisible(locator);
		((JavascriptExecutor)DriverFactory.getDriver()).executeScript("arguments[0].click();",element);
	}
	
	protected void hover(By locator)
	{
		WebElement element=waitForVisible(locator);
		Actions act=new Actions(DriverFactory.getDriver());
		act.moveToElement(element).perform();
	}
	

}
