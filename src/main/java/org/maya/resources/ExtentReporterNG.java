package org.maya.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports initReport() {
		String reportPath = System.getProperty("user.dir")+"\\Report\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Maya Test");
		reporter.config().setDocumentTitle("Maya Test Results");
		ExtentReports extents = new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Tester", "MayaTru");
		return extents;
	}
}
