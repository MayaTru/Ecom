package org.maya.testcomponents;

import java.io.IOException;

import org.maya.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extents = ExtentReporterNG.initReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) {
		System.out.println("New Test Started : " + result.getName());
		System.out.println("Method : " + result.getMethod().getMethodName());
		test = extents.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess Method : " + result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure Method : " + result.getName());

		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		test.log(Status.FAIL, "Test Failed");
		test.fail(result.getThrowable());
		String filepath = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

	}

	public void onFinish(ITestContext context) {
		System.out.println("onFinish method started");
		extents.flush();
	}

}
