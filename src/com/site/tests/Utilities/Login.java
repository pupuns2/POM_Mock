package com.site.tests.Utilities;

import com.site.pageRepo.AdminPage;
import com.site.pageRepo.InvestorSetupPage;
import com.site.tests.testcases.ConsumerSite;
import com.site.tests.testcases.ConsumerSite_Baseclass;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.site.pageRepo.HomePage.selectLender;
import static com.site.pageRepo.InvestorSetupPage.updateInvestorInfo;
import static com.site.tests.Utilities.CommonFunctions.driver;
import static com.site.tests.Utilities.CommonFunctions.safeClick;
import static com.site.tests.Utilities.CommonFunctions.waitForPageLoad;
import static com.site.tests.testcases.baseTest.*;

public class Login extends CommonFunctions {
	// String Environment;
	// static String lenderName;

	// String Workstation_URL;

	public static void ProWS_Login(String Workstation_URL) throws InterruptedException {

		driver.get(Workstation_URL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("auth_username")).sendKeys(ws_username);
		driver.findElement(By.id("auth_password")).sendKeys(ws_password);
		driver.findElement(By.id("btnLogin")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public static void WS_Login(String Workstation_URL) throws InterruptedException {

		if (lenderName.equalsIgnoreCase("lendersone")) {

		} else {
			driver.get(Workstation_URL);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(By.id("enter_username")).sendKeys(ws_username);
			driver.findElement(By.id("enter_password")).sendKeys(ws_password);
			Thread.sleep(10);
			driver.findElement(By.id("btnLogin")).click();

		}

		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);

		// Check the element 'MY PROFILE.
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());

		try {
			boolean bflag_logoutLink = driver.getPageSource().toLowerCase().contains("logout");

			if (!bflag_logoutLink) {
				fileName = rootReportFolder + "\\Fatal_" + lenderName + "_" + fileName;
				assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + ws_username + "] and Password[" + ws_password + "]", fileName);
				Assert.assertEquals(bflag_logoutLink, true, "Workstation Portal Failed with the username [" + ws_username + "] and Password[" + ws_password + "].");
			} else {
				fileName = rootReportFolder + "\\PASS_" + lenderName + "_" + fileName;
				assertBoolean(test, "PASS", "Login into Workstation Portal successfully with the username [" + ws_username + "] and Password[xxxxxxxxxx] was Successful", "", fileName);
				reporter("PASS", "Execution started for NIT - Functional, Please verify for 'Test case execution end's' message at the bottom after execution.", "");
			}

		} catch (Exception e) {
			fileName = rootReportFolder + "\\Failed_" + lenderName + "_" + fileName;
			e.printStackTrace();
			assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + agent_username + "] and Password[" + agent_password + "]", fileName);
		}
	}

	public static void Consumer_Login(String Workstation_URL) throws InterruptedException {

		if (csUserType.equalsIgnoreCase("guestUser")) {

			if (csBrowserType.equalsIgnoreCase("iphone") || csBrowserType.equalsIgnoreCase("android")) {

				driver.manage().window().setSize(new Dimension(290, 600));
			}

			driver.get(Workstation_URL);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		} else if (csUserType.equalsIgnoreCase("registeredUser")) {

			driver.get(Workstation_URL);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			if (csBrowserType.equalsIgnoreCase("iphone") || csBrowserType.equalsIgnoreCase("android")) {

				driver.manage().window().setSize(new Dimension(290, 600));
				driver.findElement(By.xpath("//*[text()='SIGN UP']")).click();
				CommonFunctions.waitForPageLoad(driver);
			} else {

				safeClickBy(driver, By.id("lnkSignin"));

				// driver.findElement(By.id("lnkSignin")).click();
				WaitForElementToExist(driver, By.id("btnLogin"), "WebElement");
			}

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			try {

				// safeSelectByText(driver, By.id("sLoginAs"), "Buyer/Seller");
				safeSelectByIndex(driver, By.id("sLoginAs"), 1);
				Thread.sleep(2000);
				
				Select sel = new Select(driver.findElement(By.id("sLoginAs")));
				sel.selectByIndex(1);
				
				Thread.sleep(2000);
				
				String selOption = sel.getFirstSelectedOption().getText();

				if (!selOption.toLowerCase().contains("buyer")) {

					safeSelectByIndex(driver, By.id("sLoginAs"), 1);
					Thread.sleep(2000);
					sel.selectByIndex(1);
					Thread.sleep(2000);					

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			safeTypeBy(driver, By.name("auth_username"), ws_username);
			safeTypeBy(driver, By.name("auth_password"), ws_password);
			safeClickBy(driver, By.id("btnLogin"));
			waitForPageLoad(driver);
			Thread.sleep(5000);

			for (int i = 0; i < 4; i++) {
				try {

					int j=driver.findElements(By.xpath("//p[@class='errorValidation' and contains(text(),'Please ensure your login info is correct')]")).size();
					
					if (j == 0) {

						boolean blogout = driver.getPageSource().toLowerCase().contains("logout");

						if (!blogout) {

							safeSelectByIndex(driver, By.id("sLoginAs"), 1);
							safeSelectByText(driver, By.id("sLoginAs"), "Buyer/Seller");
							safeTypeBy(driver, By.name("auth_username"), ws_username);
							safeTypeBy(driver, By.name("auth_password"), ws_password);
							safeClickBy(driver, By.id("btnLogin"));
							waitForPageLoad(driver);
							Thread.sleep(1000);

						} else {
							break;
						}
					} 
					
					if (j == 1) {

						safeClickBy(driver, By.id("lnkRegister"));
						safeSelectByIndex(driver, By.name("new_user_type"), 1);
						safeSelectByText(driver, By.name("new_user_type"), "Buyer/Seller");

						safeTypeBy(driver, By.name("new_user_first_name"), "TestFirstName");
						safeTypeBy(driver, By.name("new_user_last_name"), "TestLastName");

						safeTypeBy(driver, By.name("new_user_email"), ws_username);
						safeTypeBy(driver, By.name("new_user_email_confirm"), ws_username);

						safeTypeBy(driver, By.name("new_user_password"), ws_password);
						safeTypeBy(driver, By.name("new_user_password_confirm"), ws_password);
						safeClickBy(driver, By.id("new_user_button"));
						waitForPageLoad(driver);
						Thread.sleep(1000);
						safeClickBy(driver, By.xpath("//Strong[text()='TEST ENVIRONMENT:']/following-sibling::a[text()='Click here']"));

						waitForPageLoad(driver);
						//Thread.sleep(3000);

						safeClickBy(driver, By.id("lnkSignin"));
						waitForPageLoad(driver);
						safeSelectByIndex(driver, By.id("sLoginAs"), 1);
						safeSelectByText(driver, By.id("sLoginAs"), "Buyer/Seller");
						safeTypeBy(driver, By.name("auth_username"), ws_username);
						safeTypeBy(driver, By.name("auth_password"), ws_password);
						safeClickBy(driver, By.id("btnLogin"));
						waitForPageLoad(driver);
						Thread.sleep(3000);					

						safeSelectByIndex(driver, By.name("securityquestion1"), 1);
						safeTypeBy(driver, By.name("securityanswer1"), "Test");

						safeSelectByIndex(driver, By.name("securityquestion2"), 2);
						safeTypeBy(driver, By.name("securityanswer2"), "Test");

						safeClickBy(driver, By.xpath("//button[text()='Save']"));
						waitForPageLoad(driver);
						Thread.sleep(1000);

					}

				} catch (Exception e) {

				}
			}

			/*boolean bflag_logoutLink = driver.getPageSource().toLowerCase().contains("logout");

			String fileName = new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
			fileName = rootReportFolder + "RegisteredUser_" + fileName;
			try {

				if (!bflag_logoutLink) {
					assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + ws_username + "] and Password[" + ws_password + "]", fileName);
					Assert.assertEquals(bflag_logoutLink, true, "Workstation Portal Failed with the username [" + ws_username + "] and Password[" + ws_password + "].");

				} else {
					assertBoolean(test, "PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "******Login into Workstation Portal successfully with the username [" + ws_username + "] and Password[xxxxxxxxxx] was Successful", "", fileName);
					reporter("PASS", "Execution started for NIT Consumer Site - Functional Validation, Please verify for 'Test case execution end's' message at the bottom after execution.", "Logged into consumer site successfully with the username [" + ws_username + "] and Password[" + ws_password + "].");
				}

			} catch (Exception e) {
				e.printStackTrace();
				assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + ws_username + "] and Password[" + ws_password + "]", fileName);
			}*/

		}

	}

	public static void Agent_Login(String Workstation_URL) throws InterruptedException {

		driver.get(Workstation_URL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("enter_username")).sendKeys(agent_username);
		driver.findElement(By.id("enter_password")).sendKeys(agent_password);
		driver.findElement(By.id("btnLogin")).click();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		// Check the element 'MY PROFILE.
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());

		try {
			boolean agentFlag = driver.findElement(By.xpath(".//*[@id='overviewAgentProfile']")).isDisplayed();

			if (!agentFlag == true) {
				fileName = rootReportFolder + "\\Fatal_" + lenderName + "_" + fileName;
				assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + agent_username + "] and Password[" + agent_password + "]", fileName);
				Assert.assertEquals(agentFlag, true, "Agent Portal Failed with the username [" + agent_username + "] and Password[" + agent_password + "].");
			} else {
				fileName = rootReportFolder + "\\Pass_" + lenderName + "_" + fileName;
				assertBoolean(test, "PASS", "Login into Agent Portal with the username [" + agent_username + "] and Password[xxxxxxxxxx] was Successful", "", fileName);
			}
		} catch (Exception e) {
			fileName = rootReportFolder + "\\Failed_" + lenderName + "_" + fileName;
			e.printStackTrace();
			assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + agent_username + "] and Password[" + agent_password + "]", fileName);
		}
	}

	public static void Vendor_Login(String Workstation_URL) throws InterruptedException {

		driver.get(Workstation_URL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("enter_username")).sendKeys(vendor_username);
		driver.findElement(By.id("enter_password")).sendKeys(vendor_password);
		driver.findElement(By.id("btnLogin")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());

		try {
			boolean vendorFlag = driver.findElement(By.xpath(" .//*[@id='overviewAccountDashboard']")).isDisplayed();
			if (!vendorFlag == true) {
				fileName = rootReportFolder + "\\Fatal_" + lenderName + "_" + fileName;
				assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + vendor_username + "] and Password[" + vendor_password + "]", fileName);
				Assert.assertEquals(vendorFlag, true, "Agent Portal Failed with the username [" + vendor_username + "] and Password[" + vendor_password + "].");
			} else {
				fileName = rootReportFolder + "\\Pass_" + lenderName + "_" + fileName;
				assertBoolean(test, "PASS", "Login into Vendor Portal with the username [" + vendor_username + "] and Password[xxxxxxxxxx] was Successful", "", fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fileName = rootReportFolder + "\\Failed_" + lenderName + "_" + fileName;
			assertBoolean(test, "FATAL", "", "Failed to Login into Agent Portal with the username [" + vendor_username + "] and Password[" + vendor_password + "]", fileName);
		}
	}

	public static boolean InvestorPortal_Login(String INVESTORPORTAL_URL) throws InterruptedException {

		/*
		 * try{ if(
		 * LoadConfigFile.getInstance().getValue("Create_Investor").trim
		 * ().equalsIgnoreCase("Yes") ) { CreateInvestor(); } }catch
		 * (NullPointerException e){ CreateInvestor(); }
		 */

		driver.get(INVESTORPORTAL_URL);
		/*
		 * ws_username = lninfo.get("ws_username"); ws_password =
		 * lninfo.get("ws_password"); lninfo.put("ws_username", "Password1");
		 */
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("enter_username")).sendKeys(ws_username);
		driver.findElement(By.id("enter_password")).sendKeys(ws_password);
		driver.findElement(By.id("btnLogin")).click();

		try {
			int isPresent = driver.findElements(By.name("sOld_password")).size();

			if (isPresent > 0) {
				reporter("PASS", "No Investor Password change page present", "");
				InvestorSetupPage in = new InvestorSetupPage(driver);
				in.updateInvestorInfo();
			}
		} catch (Exception ee) {
			reporter("Warning", "No Investor Password change page present", "");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		if (driver.findElement(By.linkText("Logout")).isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

	private static void CreateInvestor() throws InterruptedException {
		lenderName = "ASPS REO UNIVERSAL";
		lenderValue = "1799";
		User_Login();
		selectLender();

		createWorkStationUser();

		lenderName = "investor";
		lenderValue = "88888";
	}

	private static void createWorkStationUser() {

		// Create user for ASPS REO UNIVERSAL.

		AdminPage ap = new AdminPage(driver);
		InvestorSetupPage in = new InvestorSetupPage(driver);

		ap.getAdminHomePage();
		ap.getAddInvestorPage();

		in.getNewInvestorCreated();
	}

	public static void User_Login() throws InterruptedException {

		switch (environment.toUpperCase()) {

		case ("ALPHA"):
			if (lenderName.toUpperCase().contains("PRO"))
				ProWS_Login(SYSPARAM.getProperty("ALPHAPRO_URL"));
			else if (lenderName.toUpperCase().contains("AGENT"))
				Agent_Login(SYSPARAM.getProperty("ALPHAAGENT_URL"));
			else if (lenderName.toUpperCase().contains("VENDOR"))
				Vendor_Login(SYSPARAM.getProperty("ALPHAVENDOR_URL"));
			else if (lenderName.toUpperCase().contains("CONSUMER"))
				Consumer_Login(SYSPARAM.getProperty("ALPHACONSUMERSITE_URL"));
			else if (lenderName.trim().equalsIgnoreCase("INVESTOR_PORTAL") || lenderName.trim().equalsIgnoreCase("INVESTOR"))
				InvestorPortal_Login(SYSPARAM.getProperty("BETAINVESTORPORTAL_URL"));
			/*
			 * else if (lenderName.toUpperCase().contains("SAX"))
			 * V4_Login(SYSPARAM.getProperty("ALPHAV4_URL"));
			 */
			else
				WS_Login(SYSPARAM.getProperty("ALPHAWS_URL"));
			break;

		case ("BETA"):
			if (lenderName.toUpperCase().contains("PRO"))
				ProWS_Login(SYSPARAM.getProperty("BETAPRO_URL"));
			else if (lenderName.toUpperCase().contains("AGENT"))
				Agent_Login(SYSPARAM.getProperty("BETAAGENT_URL"));
			else if (lenderName.toUpperCase().contains("VENDOR"))
				Vendor_Login(SYSPARAM.getProperty("BETAVENDOR_URL"));
			else if (lenderName.trim().equalsIgnoreCase("INVESTOR_PORTAL") || lenderName.toUpperCase().contains("INVESTOR"))
				InvestorPortal_Login(SYSPARAM.getProperty("BETAINVESTORPORTAL_URL"));
			else if (lenderName.toUpperCase().contains("CONSUMER"))
				Consumer_Login(SYSPARAM.getProperty("BETACONSUMERSITE_URL"));
			/*
			 * else if (lenderName.toUpperCase().contains("SAX"))
			 * V4_Login(SYSPARAM.getProperty("BETAV4_URL"));
			 */
			else
				WS_Login(SYSPARAM.getProperty("BETAWS_URL"));
			break;

		case ("PERF"):
			if (lenderName.toUpperCase().contains("PRO"))
				ProWS_Login(SYSPARAM.getProperty("PERFPRO_URL"));
			else if (lenderName.toUpperCase().contains("AGENT"))
				Agent_Login(SYSPARAM.getProperty("PERFAGENT_URL"));
			else if (lenderName.toUpperCase().contains("VENDOR"))
				Vendor_Login(SYSPARAM.getProperty("PERFVENDOR_URL"));
			else if (lenderName.trim().equalsIgnoreCase("INVESTOR_PORTAL") || lenderName.toUpperCase().contains("INVESTOR"))
				InvestorPortal_Login(SYSPARAM.getProperty("PERFINVESTORPORTAL_URL"));
			else if (lenderName.toUpperCase().contains("CONSUMER"))
				Consumer_Login(SYSPARAM.getProperty("PERFCONSUMERSITE_URL"));
			/*
			 * else if (lenderName.toUpperCase().contains("SAX"))
			 * V4_Login(SYSPARAM.getProperty("BETAV4_URL"));
			 */
			else
				WS_Login(SYSPARAM.getProperty("PERFWS_URL"));
			break;

		case ("STAGE"):
			if (lenderName.toUpperCase().contains("PRO"))
				ProWS_Login(SYSPARAM.getProperty("STAGEPRO_URL"));
			else if (lenderName.toUpperCase().contains("AGENT"))
				Agent_Login(SYSPARAM.getProperty("STAGEAGENT_URL"));
			else if (lenderName.toUpperCase().contains("VENDOR"))
				Vendor_Login(SYSPARAM.getProperty("STAGEVENDOR_URL"));
			else if (lenderName.trim().equalsIgnoreCase("INVESTOR_PORTAL") || lenderName.toUpperCase().contains("INVESTOR"))
				InvestorPortal_Login(SYSPARAM.getProperty("STAGEINVESTORPORTAL_URL"));
			else if (lenderName.toUpperCase().contains("CONSUMER"))
				Consumer_Login(SYSPARAM.getProperty("STAGECONSUMERSITE_URL"));
			/*
			 * else if (lenderName.toUpperCase().contains("SAX"))
			 * V4_Login(SYSPARAM.getProperty("STAGEV4_URL"));
			 */
			else
				WS_Login(SYSPARAM.getProperty("STAGEWS_URL"));
			break;

		case ("REM"):
			if (lenderName.toUpperCase().contains("PRO"))
				ProWS_Login(SYSPARAM.getProperty("REMPRO_URL"));
			else if (lenderName.toUpperCase().contains("AGENT"))
				Agent_Login(SYSPARAM.getProperty("REMAGENT_URL"));
			else if (lenderName.toUpperCase().contains("VENDOR"))
				Vendor_Login(SYSPARAM.getProperty("REMVENDOR_URL"));
			else if (lenderName.trim().equalsIgnoreCase("INVESTOR_PORTAL") || lenderName.toUpperCase().contains("INVESTOR"))
				InvestorPortal_Login(SYSPARAM.getProperty("REMINVESTORPORTAL_URL"));
			else if (lenderName.toUpperCase().contains("CONSUMER"))
				Consumer_Login(SYSPARAM.getProperty("REMCONSUMERSITE_URL"));
			/*
			 * else if (lenderName.toUpperCase().contains("SAX"))
			 * V4_Login(SYSPARAM.getProperty("REMV4_URL"));
			 */
			else
				WS_Login(SYSPARAM.getProperty("REMWS_URL"));
			break;

		case ("PRODUCTION"):
		case ("PROD"):
			if (lenderName.toUpperCase().contains("PRO"))
				ProWS_Login(SYSPARAM.getProperty("PRODUCTIONPRO_URL"));
			else if (lenderName.toUpperCase().contains("AGENT"))
				Agent_Login(SYSPARAM.getProperty("PRODUCTIONAGENT_URL"));
			else if (lenderName.toUpperCase().contains("VENDOR"))
				Vendor_Login(SYSPARAM.getProperty("PRODUCTIONVENDOR_URL"));
			else if (lenderName.trim().equalsIgnoreCase("INVESTOR_PORTAL") || lenderName.toUpperCase().contains("INVESTOR"))
				InvestorPortal_Login(SYSPARAM.getProperty("PRODINVESTORPORTAL_URL"));
			else if (lenderName.toUpperCase().contains("CONSUMER"))
				Consumer_Login(SYSPARAM.getProperty("PRODCONSUMERSITE_URL"));
			/*
			 * else if (lenderName.toUpperCase().contains("SAX"))
			 * V4_Login(SYSPARAM.getProperty("PRODUCTIONV4_URL"));
			 */
			else
				WS_Login(SYSPARAM.getProperty("PRODUCTIONWS_URL"));
			break;

		case ("DR"):
			if (lenderName.toUpperCase().contains("PRO"))
				ProWS_Login(SYSPARAM.getProperty("PRODUCTIONPRO_URL"));
			else if (lenderName.toUpperCase().contains("AGENT"))
				Agent_Login(SYSPARAM.getProperty("PRODUCTIONAGENT_URL"));
			else if (lenderName.toUpperCase().contains("VENDOR"))
				Vendor_Login(SYSPARAM.getProperty("PRODUCTIONVENDOR_URL"));
			else if (lenderName.toUpperCase().contains("CONSUMER"))
				Consumer_Login(SYSPARAM.getProperty("PROD_CONSUMER_SITE"));
			else
				WS_Login(SYSPARAM.getProperty("PRODUCTIONWS_URL"));
			break;
		}

	}

	public static void loginToBuyerPortal() throws InterruptedException {

		// driver.get(Workstation_URL);
		driver.findElement(By.xpath("html/body/header/article/div[2]/a[1]"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("enter_username")).sendKeys(bp_username);
		driver.findElement(By.id("enter_password")).sendKeys(bp_password);
		driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

}