package com.ibm.banking.hooks;

import org.openqa.selenium.Cookie;

import com.ibm.framework.config.ConfigReader;
import com.ibm.framework.driver.DriverFactory;
import com.ibm.framework.reports.ExtentManager;
import com.ibm.framework.reports.ExtentReport;
import com.ibm.framework.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	@Before(order = 0)
	public void initExtent() {
		ExtentReport.initReports();
	}
	
	
	@Before(order = 1)
	public void setUp(Scenario scenario) {
		DriverFactory.initDriver();
		ExtentReport.createTest(scenario.getName());
		 DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
	        Cookie bypassCookie = new Cookie(
	                "_vercel_share",
	                ConfigReader.get("vercelCookie")
	        );

	        DriverFactory.getDriver().manage().addCookie(bypassCookie);

	        // Refresh so cookie takes effect
	        DriverFactory.getDriver().navigate().refresh();
	}
	
	@After
	public void tearDown(Scenario scenario) {

	    if (scenario.isFailed()) {

	        System.out.println(
	            "[INFO] Scenario failed. Taking screenshot: "
	            + scenario.getName()
	        );

	        String screenshotPath =
	                ScreenshotUtils.takeScreenshot(
	                    scenario.getName().replaceAll(" ", "_")
	                );

	        ExtentManager.getTest()
	                .fail("Scenario failed: " + scenario.getName())
	                .addScreenCaptureFromPath(screenshotPath);

	    }

	    DriverFactory.quitDriver();
	    ExtentManager.unload();
	}

	@AfterAll
	public static void flushExtent() {
	    ExtentReport.flushReports();
	}

}
