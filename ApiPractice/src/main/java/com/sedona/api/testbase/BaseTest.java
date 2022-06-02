/**
 * 
 */
package com.sedona.api.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



/**
 * @author bkumar
 *
 */
public class BaseTest {
	public static ExtentSparkReporter sparkerReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Properties prop;
	public static String path = System.getProperty("user.dir");
	@BeforeSuite
	public void setUp() {
		sparkerReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/HtmlReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkerReporter);
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(path+ "\\src\\main\\java\\com\\sedona"
					+ "\\api\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.fail(MarkupHelper.createLabel(" Test case failed ", ExtentColor.RED));
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(" Test case passed ", ExtentColor.GREEN));
		}else {
			test.skip(MarkupHelper.createLabel(" Test case passed ", ExtentColor.GREEN));
		    test.skip(result.getThrowable());
		}
	}
   @AfterSuite
   public void tearDown() {
	   extent.flush();
   }
   
}
