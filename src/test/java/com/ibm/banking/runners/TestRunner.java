package com.ibm.banking.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {
	        "com.ibm.banking.stepdefinitions",
	        "com.ibm.banking.hooks"
	    },
	    tags="@updateProfile",
	    plugin = {
	        "pretty",
	        "html:target/cucumber-report.html",
	        "json:target/cucumber.json"
	    },
	    monochrome = true
	)

public class TestRunner extends AbstractTestNGCucumberTests{

}
