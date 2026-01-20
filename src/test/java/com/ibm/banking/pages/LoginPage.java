package com.ibm.banking.pages;

import org.openqa.selenium.By;

import com.ibm.framework.base.BasePage;
import com.ibm.framework.config.ConfigReader;
import com.ibm.framework.driver.DriverFactory;

public class LoginPage extends BasePage{
		
	private By username = By.id("email");
	private By password = By.id("password");
	private By login = By.cssSelector("button[type='submit']");
	
	private By accessDeniedMsg = By.xpath("//p[contains(text(),'Access Denied')]");	   
	
	public void open() {
		DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
	}
	
	public void login(String user,String pass) {
		type(username,user);
		type(password,pass);
		click(login);
	}
	
	public String getErrorMessage() {
        return getText(accessDeniedMsg);
    }

}
