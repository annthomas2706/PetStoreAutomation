package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;
	
	
	@Override
    public void onStart(ITestContext  context) {
		System.out.println("Running test");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportName="Test-Report_"+timestamp+"-.html";
        sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+ reportName);
        sparkReporter.config().setDocumentTitle("PetStore Report");
        sparkReporter.config().setReportName("PetStore Api Testing");
        sparkReporter.config().setTheme(Theme.DARK);
        
        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "PetStore");
        extent.setSystemInfo("UserName", System.getProperty("user.name"));
        extent.setSystemInfo("Operating System", "Windows");
        
        extent.setSystemInfo("Browser", "Chrome");
        
        
        
        		
    }
	
	@Override
    public void onTestSuccess(ITestResult result) {
        test=extent.createTest(result.getTestClass().getName());
        		test.log(Status.PASS,"Test executed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	test.log(Status.FAIL,result.getName()+" got failed");
    	test.log(Status.INFO,result.getThrowable().getMessage());
    	//String imgPath=new TestBase().captureScreenshot(result.getName());
    	//test.addScreenCaptureFromPath(imgPath);
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
    	test.log(Status.SKIP,result.getName()+" got skipped");
    	test.log(Status.INFO,result.getThrowable().getMessage());
    }
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
