package com.site.tests.testcases;

import static com.site.tests.Utilities.CommonFunctions.SYSPARAM;
import static com.site.tests.Utilities.CommonFunctions.csInitBrowser;
import static com.site.tests.Utilities.CommonFunctions.driver;
import static com.site.tests.Utilities.CommonFunctions.initData;
import static com.site.tests.Utilities.CommonFunctions.reporter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.site.pageRepo.ConsumerSite_LandingPage;
import com.site.pageRepo.ConsumerSite_PropertyPage;
import com.site.tests.Utilities.CommonFunctions;
import com.site.tests.Utilities.LoadConfigFile;
import com.site.tests.Utilities.Login;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ConsumerSite_Baseclass extends baseTest {

	@Parameters({ "report", "environment" })
	@BeforeSuite
	public void setupReport(String report, String env) {
		
		String currentDate = new SimpleDateFormat("MM-dd-yyyy")
				.format(new Date());
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		
		environment=env;			
		reportPath = report + "ConsumerSite\\results\\" + env + "\\" + currentDate + "\\ConsumerSiteResults" + timeStamp + ".html";
		rootReportFolder = report + "ConsumerSite\\results\\" + env + "\\" + currentDate;
		lenderName="Consumer";
		extent = CommonFunctions.ExtentManager.getReporterConsumerSite(reportPath);
		
		
		try {
			initData();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Parameters({ "browser", "environment", "username", "password" })
	@BeforeClass
	public void setup(String browser, String env, String username,
			String password) {
		
		if (username.length() > 1) {
			csUserType = "RegisteredUser";
		} else {
			csUserType = "GuestUser";
		}		
		

		ws_username = username;
		ws_password = password;
		environment=env;		
		csBrowserType=browser;
	
		try {
			csInitBrowser(csBrowserType);
			url = SYSPARAM.getProperty(environment.toUpperCase()
					+ "CONSUMERSITE_URL");
			Login.User_Login();
			reporter("INFO", "Logged in the application", "login completed");
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		extent.flush();
	

	}

	@AfterSuite
	public void tearup() {

		driver.close();
		driver.quit();
		
	}
	
	public static void buildMap(String data) {

		int size = listConsumerSite.size();
		listConsumerSite.put(size + 1, data);
	}

}
