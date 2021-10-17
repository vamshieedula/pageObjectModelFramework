package com.pageObjectEcom.vr.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	

	public static ExtentReports getInstance() {
		if (extent == null) {
			return createInstance("test-output/extent reports/extent.html");
		} else {
			return extent;
		}
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automtion Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Vamshi");
		extent.setSystemInfo("Organization", "vr");
		extent.setSystemInfo("Build no", "vr-1234");

		return extent;
	}

}
