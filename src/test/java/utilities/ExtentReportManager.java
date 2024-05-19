package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReport;
	public ExtentTest extentTest;

	String reportName;

	public void onStart(ITestContext testContext) {

//		Generate time stamp
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date date = new Date();
//		String currentDateTimeStamp = dateFormat.format(date);
//		(OR)
		String currentDateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + currentDateTimeStamp + ".html";

//		Specify location of the report
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);

//		Title of the document
		sparkReporter.config().setDocumentTitle("E-Commerce Automation Report");
//		Name of the report
		sparkReporter.config().setReportName("E-Commerce Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Application", "E-Commerce");
		extentReport.setSystemInfo("Module", "Admin");
		extentReport.setSystemInfo("Sub Module", "Customers");
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extentReport.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extentReport.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {

			extentReport.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult testResult) {

		extentTest = extentReport.createTest(testResult.getTestClass().getName());
		extentTest.assignCategory(testResult.getMethod().getGroups());
		extentTest.log(Status.PASS, testResult.getName() + " got successfully executed");
	}

	public void onTestFailure(ITestResult testResult) {

		extentTest = extentReport.createTest(testResult.getTestClass().getName());
		extentTest.assignCategory(testResult.getMethod().getGroups());
		extentTest.log(Status.FAIL, testResult.getName() + " got failed");
		extentTest.log(Status.INFO, testResult.getThrowable().getMessage());

		try {
			String imagePath = new BaseTest().captureScreen(testResult.getName());
			extentTest.addScreenCaptureFromPath(imagePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult testResult) {

		extentTest = extentReport.createTest(testResult.getTestClass().getName());
		extentTest.assignCategory(testResult.getMethod().getGroups());
		extentTest.log(Status.SKIP, testResult.getName() + " got skipped");
		extentTest.log(Status.INFO, testResult.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {

		extentReport.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + reportName;
		File extentReportFile = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
