package com.site.tests.testcases;

import com.site.tests.Utilities.CommonFunctions;
import com.site.tests.Utilities.LoadConfigFile;
import com.site.tests.Utilities.Login;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.mail.MessagingException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.site.tests.Utilities.ApplicationFunctions.runConsumerSiteChecks;
import static com.site.tests.testcases.baseTest.*;
import static com.site.tests.Utilities.CommonFunctions.*;

public class Consumer extends baseTest {

	
	public static String csUserType;
	public static String csBrowserType;
	public static String zipcode;
	public static String address;
	public static Map<Integer, String> listConsumerSite = new HashMap<Integer, String>();

	@Parameters({ "browser", "suite_name", "lender_name", "lender_value", "mks_update", "mks_username", "mks_password", "mks_query", "ws_username", "ws_password", "agent_username", "agent_password", "agentname_displayed", "vendor_username", "vendor_password", "report", "release", "environment", "mailing_list", "baselinedpath" })
	@BeforeTest
	public void StartBrowser_NavURL(String browser, String suiteName, String lender_name, String lenderVal, String mksFlag, String mksUname, String mksPassword, String mksQuery, String w_username, String w_password, String a_username, String a_password, String aname_displayed, String v_username, String v_password, String rootFolder, String rel, String env, String mailList, String baslineFlag) throws InterruptedException, IOException {

		lenderName = lender_name.trim();
		lenderValue = lenderVal;
		if (!((rootFolder.substring(rootFolder.length() - 1)) == "\\")) {
			StringBuffer sb = new StringBuffer(rootFolder);
			sb.append("\\");
			rootFolder = sb.toString();
		}
		rootReportFolder = rootFolder;

		environment = env;
		reportPath = rootReportFolder + "ConsumerSite_" + env + ".html";

		release = rel;
		ws_username = w_username;
		ws_password = w_password;
		send_emailTo = mailList;
		defaultTimeout = Integer.parseInt(LoadConfigFile.getInstance().getValue("defaultTimeout"));

		try {
			File fold = new File(rootFolder);
			if (!fold.exists()) {
				fold.mkdirs();
			}
			File file = new File(reportPath);

			if (file.createNewFile()) {
				Thread.sleep(1000);
				System.out.println("Report File [" + file.getName() + "] is created..! ");
			} else {
				System.out.println("File already [" + file.getName() + "]  exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		extent = CommonFunctions.ExtentManager.getReporter(reportPath);
		test = extent.startTest("NIT-Functional for ConsumerSite", "Objective: To verify all the possible search filter's, header information's and other basic checks in Vendor Portal");
		extent.flush();
		initData();

	}

	@DataProvider(name = "csData")
	public Object[][] getDataFromDataprovider() {

		return new Object[][] { { "GuestUser"/* , "Chrome" */},{"RegisteredUser"}
		/* { "GuestUser", "iPhone" }, { "GuestUser", "Android" }, */
		/*
		 * { "RegisteredUser", "Chrome" }, { "RegisteredUser", "iPhone" }, {
		 * "RegisteredUser", "Android" }
		 */
		};

	}

	@Test(dataProvider = "csData", alwaysRun = true)
	public void consumerSiteChecklist(String userType/* , String browserType */) throws InterruptedException {

		csUserType = userType;
		csBrowserType = LoadConfigFile.getInstance().getValue("Consumer_Browser");
		zipcode = "";
		address = "";
		try {
			csInitBrowser(csBrowserType);
			String url = SYSPARAM.getProperty(environment.toUpperCase() + "CONSUMERSITE_URL");
			// General Actions which needs to be run for all lenders.
			Login.User_Login();
			reporter("INFO", "Logged in the application", "login completed");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			extent.flush();

			runConsumerSiteChecks(url);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		// We can remove the below sections up to line 116 also, If we doesn't
		// want to use ITestResult.

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, " Test case skipped " + result.getThrowable());

		} else {
			test.log(LogStatus.PASS, "********------- Test case execution end's ----********");

		}
		extent.endTest(test);
		finalTestStatus = test.getRunStatus().toString();

		System.out.println("Aftermethod>>>>>>" + ConsumerSite.listConsumerSite);
	}

	@AfterTest(alwaysRun = true)
	protected void teardown() throws MessagingException {
		extent.close();
		driver.quit();

		System.out.println("Aftertest>>>>>>" + ConsumerSite.listConsumerSite);

	}

	public static void buildMap(String data) {

		int size = listConsumerSite.size();
		listConsumerSite.put(size + 1, data);
	}

}
