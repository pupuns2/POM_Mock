package com.site.tests.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.site.pageRepo.VendorPortalFilterMessages;
import com.site.tests.Utilities.CommonFunctions;
import com.site.tests.Utilities.LoadConfigFile;
import com.site.tests.Utilities.Login;
import com.relevantcodes.extentreports.LogStatus;

import static com.site.tests.Utilities.CommonFunctions.*;

public class baseTest {
	
	/* VendorPortalFilterMessages vpf;
	 WebDriverWait wait;*/

	public static ExtentReports extent;
	public static ExtentTest test;
	public static String lenderName;        //<parameter name="lender_name" value="DEMOREO PRO"/>
	public static String lenderValue;        //<parameter name="lender_value" value="1597"/>

	public static String rootReportFolder;  //<parameter name="report" value="Z:\\QA\\Jenkins\\NIT_Checklist\\"/>
	public static String reportPath;
	public static String environment;       //<parameter name="environment" value="BETA"/>
	public static String release;           //<parameter name="release" value="9.07"/>
	public static String vendor_password;       //<parameter name="vendor_password" value="Password0"/>
	public static String vendor_username;   //<parameter name="vendor_username" value="Password0"/>
	public static String agent_username;        //<parameter name="agent_username" value="Password0"/>
	public static String agentname_displayed;
	public static String agent_password;        //<parameter name="agent_password" value="Password0"/>
	public static String ws_username;       // <parameter name="ws_username" value="satya.pany@site.com"/>
	public static String ws_password;       //<parameter name="ws_password" value="Password0"/>
	public static String browserType;           //<parameter name="browser" value="IE"/>
	public static String reportLendermks ;
	public static String finalTestStatus;
	public static int  defaultTimeout;
	public static String LoanNumber ;
	public static Map<String,String> lninfo = new HashMap<String,String>();
	public static String downloadPath;
	public static String MKS_Update;
	public static String MKS_UserName;
	public static String MKS_Password;
	public static String MKS_Query;
	public static String send_emailTo;
	public static String bp_username;       // <parameter name="ws_username" value="satya.pany@site.com"/>
	public static String bp_password;
	
	
	public static String csUserType;
	public static String csBrowserType;	
	public static String url;
	public static String zipcode;
	public static String address;
	public static Map<Integer, String> listConsumerSite = new HashMap<Integer, String>();


	
	

}
