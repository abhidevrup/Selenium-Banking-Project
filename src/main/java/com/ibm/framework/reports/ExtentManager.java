package com.ibm.framework.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
	 private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	 
	 private ExtentManager() {
	 }
	 
	 static void setTest(ExtentTest test) {
		 extentTest.set(test);
	 }
	 
	 public static ExtentTest getTest() {
		 return extentTest.get();
	 }
	 
	 public static void unload() {
		 extentTest.remove();
	 }
}
