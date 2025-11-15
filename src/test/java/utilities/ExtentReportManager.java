package utilities;

import java.awt.Desktop;
import java.io.File;
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

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext context) {

		//SimpleDateFormat df = new SimpleDateFormat();
	    System.out.println("Listener Started!");

		//Date dt = new Date();
		//String timeStamp = df.format(dt);
		//System.out.println(timeStamp);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operation system", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report
		test.log(Status.PASS, result.getName() + "got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report

		test.log(Status.FAIL, result.getName() + "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			BaseClass bc = new BaseClass();
			String imgPath = bc.captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report

		test.log(Status.SKIP, result.getName() + "got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		
		//Open the Report in the Desktop
		String pathofReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathofReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
