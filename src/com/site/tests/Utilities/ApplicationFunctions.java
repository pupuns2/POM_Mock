package com.site.tests.Utilities;

import static com.site.pageRepo.HomePage.selectLender;
import static com.site.tests.Utilities.CommonFunctions.WaitForElementToExist;
import static com.site.tests.Utilities.CommonFunctions.checkPageError;
import static com.site.tests.Utilities.CommonFunctions.driver;
import static com.site.tests.Utilities.CommonFunctions.getContentWebTable;
import static com.site.tests.Utilities.CommonFunctions.reporter;
import static com.site.tests.testcases.baseTest.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.site.pageRepo.AgentSearch;
import com.site.pageRepo.BACSegRuleAdminPage;
import com.site.pageRepo.BP_LoginPage;
import com.site.pageRepo.BP_Messages;
import com.site.pageRepo.BP_PropertiesHomePage;
import com.site.pageRepo.BP_PropertyDetails;
import com.site.pageRepo.ConsumerSite_LandingPage;
import com.site.pageRepo.ConsumerSite_PropertyPage;
import com.site.pageRepo.Expenses;
import com.site.pageRepo.InvestorPropertySearch;
import com.site.pageRepo.InvestorSearchPage;
import com.site.pageRepo.LibrarySearch;
import com.site.pageRepo.LoanInfoPage;
import com.site.pageRepo.MessageSearch;
import com.site.pageRepo.OfferSearchPage;
import com.site.pageRepo.PropertyMessage;
import com.site.pageRepo.ReportSearch;
import com.site.pageRepo.SearchPage;
import com.site.pageRepo.TaskSearchPage;
import com.site.pageRepo.UserSearchPage;
import com.site.pageRepo.VendorPortalFilterMessages;
import com.site.pageRepo.VendorPortalSearchProperties;
import com.site.pageRepo.VendorSearchPage;
import com.site.tests.testcases.ConsumerSite;

import static com.site.tests.Utilities.CommonFunctions.SYSPARAM;

public class ApplicationFunctions {

	public static boolean SelectValueFromWebList(WebElement dropDownList, String webListValue) {
		boolean bFlag = false;

		reporter("INFO", "Weblist Select Value ", "webListselectValue: Selecting the Specific WebList Value");

		// new Select (dropDownList).selectByVisibleText("INACTIVE");

		if (webListValue.equalsIgnoreCase("NA") || StringUtils.isBlank(webListValue) || webListValue.equalsIgnoreCase("")) {
			reporter("INFO", "WebList value is not present, Hence skipped", "");
			return true;

		} else {

			Select select = new Select(dropDownList);

			List<WebElement> options = select.getOptions();// will get all
															// options as
															// List<WebElement>

			for (WebElement option : options) {

				if (webListValue.equalsIgnoreCase(option.getText().trim())) {
					option.click();
					bFlag = true;
					break;
				}
			}
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
			if (bFlag) {
				reporter("INFO", "Value selected from drop down", "Successfully Selected the value [" + webListValue + "] for the Drop Down [" + dropDownList.getTagName() + "]");
				return true;
			}
			reporter("FAIL", "Failed to select the Value from Drop down [" + webListValue + "]", "Failed to Select the value [" + webListValue + "] for the Drop Down [" + dropDownList.getTagName() + "]");
			return bFlag;
		}
	}

	public static void SelectValueFromWebList(WebElement dropDownList, int webListValue) {

		reporter("INFO", "SelectValueFromWebList: Weblist Select Value ", "webListselectValue: Selecting the Specific WebList Value");

		try {
			Select listbox = new Select(dropDownList);
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
			WaitForElementToExist(driver, By.id(dropDownList.getAttribute("id")), "dropdown");
			listbox.selectByIndex(webListValue);
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
			dropDownList.click();
		} catch (Exception e) {
			e.printStackTrace();
			reporter("FATAL", "SelectValueFromWebList: Got an exception ", "Got an exception while selecting the index [" + webListValue + "]");
		}
		reporter("INFO", "SelectValueFromWebList: Weblist Select Value [" + webListValue + "]", "webListselectValue: Selecting the Specific WebList Value with the Index [" + webListValue + "]");
	}

	public static boolean runProdCheckListChecks() throws Exception {

		boolean bFlag = false;

		// Check for the flags.
		if (LoadConfigFile.getInstance().getValue("Search_Available").equalsIgnoreCase("Yes")) {
			try {

				boolean blag_search = SearchCheck();
				if (!blag_search) {

				} else {
					reporter("PASS", "Search Filter Verification Passed", "Search Filter Verification Completed");
				}
			} catch (Exception e) {
				reporter("FAIL", "Search Filter Verification Failed [" + e.getMessage() + "]", "Search Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();
			}
		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Offer_Search_Available").equalsIgnoreCase("Yes")) {
			try {
				boolean bFlag_Offer = OfferCheck();
				if (!bFlag_Offer) {
					reporter("FAIL", "Offer Verification Failed", "Offer Verification Failed");
				} else {
					reporter("PASS", "Offer Verification Passed", "Offer Verification Completed");
				}
			} catch (Exception e) {
				reporter("FAIL", "Offer Search Filter Verification Failed [" + e.getMessage() + "]", "Offer Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();

			}
		}
		extent.flush();
		if (LoadConfigFile.getInstance().getValue("RuleAdmin_Available").equalsIgnoreCase("Yes")) {
			BACSegRuleAdminPage bsr = new BACSegRuleAdminPage(driver);
			boolean bFlag_RuleAdmin = bsr.Rulecheck();
			if (!bFlag_RuleAdmin) {
				reporter("FAIL", "'Rule Admin Page' Verification Failed", "'Rule Admin Page' Verification Failed");
			} else {
				reporter("PASS", "'Rule Admin Page' Verification completed with No Errors", "'Rule Admin Page' Verification completed with No Errors");
			}
		}
		extent.flush();
		if (LoadConfigFile.getInstance().getValue("Investor_Search_Available").equalsIgnoreCase("Yes")) {
			try {

				InvestorSearchPage isp = new InvestorSearchPage(driver);
				boolean bFlag_investorSearch = isp.InvestorSearch();
				if (!bFlag_investorSearch) {
					reporter("FAIL", "'Investor  Search Page' Verification Failed", "'Investor  Search Page' Verification Failed");
				} else {
					reporter("PASS", "'Rule Admin Page' Verification completed with No Errors", "'Rule Admin Page' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "Investor Search Filter Verification Failed [" + e.getMessage() + "]", "Offer Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();

			}
		}
		extent.flush();
		if (LoadConfigFile.getInstance().getValue("Report_Search_Available").equalsIgnoreCase("Yes")) {
			try {
				ReportSearch rs = new ReportSearch(driver);
				boolean bFlag_report = rs.Report();
				if (!bFlag_report) {
					reporter("FAIL", "'Report  Search Page' Verification Failed", "'Report  Search Page' Verification Failed");
				} else {
					reporter("PASS", "'Report Search Page' Verification completed with No Errors", "'Report Search Page' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "Report Search Filter Verification Failed", "Report Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();

			}
		}
		extent.flush();
		if (LoadConfigFile.getInstance().getValue("Broader_TaskSearch_Available").trim().equalsIgnoreCase("Yes")) {
			try {

				TaskSearchPage ts = new TaskSearchPage(driver);
				boolean bFlag_roadtasksearch = ts.BroaderTaskSearch();
				if (!bFlag_roadtasksearch) {
					reporter("FAIL", "'Broader task Search' Verification Failed", "'Broader task Search' Verification Failed");
				} else {
					reporter("PASS", "'Broader task Search' Verification completed with No Errors", "'Broader task Search' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "Task Search Filter Verification Failed [" + e.getMessage() + "]", "Task Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();

			}
		}
		extent.flush();
		if (LoadConfigFile.getInstance().getValue("Broader_UserSearch_Available").trim().equalsIgnoreCase("Yes")) {
			try {

				UserSearchPage us = new UserSearchPage(driver);
				boolean bFlag_userSearch = us.UserSearch();
				if (!bFlag_userSearch) {
					reporter("FAIL", "'User Search' Verification Failed", "'User Search' Verification Failed");
				} else {
					reporter("PASS", "'User Search' Verification completed with No Errors", "'User Search' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "Broader Search Filter Verification Failed [" + e.getMessage() + "]", "Broder Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();
			}

		}
		extent.flush();
		if (LoadConfigFile.getInstance().getValue("Agent_Search_Available").trim().equalsIgnoreCase("Yes")) {
			try {

				AgentSearch ag = new AgentSearch(driver);
				boolean bFlag_AgentSearch = ag.searchWithAgentName();
				if (!bFlag_AgentSearch) {
					reporter("FAIL", "'Agent Search' Verification Failed", "'User Search' Verification Failed");
				} else {
					reporter("PASS", "'Agent Search' Verification completed with No Errors", "'User Search' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "Agent Search Filter Verification Failed [" + e.getMessage() + "]", "Agent Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();
			}

		}
		extent.flush();
		if (LoadConfigFile.getInstance().getValue("Vendor_Search_Available").trim().equalsIgnoreCase("Yes")) {
			try {

				VendorSearchPage vs = new VendorSearchPage(driver);
				boolean bFlag_VendorSearch = vs.VendorSearch();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'Vendor Search' Verification Failed", "'Vendor Search' Verification Failed");
				} else {
					reporter("PASS", "'Vendor Search' Verification completed with No Errors", "'Vendor Search' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "Vendor Search Filter Verification Failed [" + e.getMessage() + "]", "Vendor Filter Verification Failed");
				String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
				driver.get(u);
				selectLender();

			}
		}

		return bFlag;

	}

	public static String getLoanNumberFromSearchResult() {
		List<String> LoanList = new ArrayList<>();
		try {
			LoanList = getContentWebTable("Loan Number", By.className("tableHeader"));
		} catch (Exception e) {
			LoanList = getContentWebTable("Loan Number", By.xpath("//*/thead/tr"));

		}
		// System.out.println(LoanList);

		// Choose the right Loan Number.
		/*
		 * if(! lenderValue.equalsIgnoreCase("1000059")) { LoanList.removeIf(p
		 * -> p.contains("-")); }
		 */
		LoanList.removeIf(p -> p.contains(","));
		LoanList.removeIf(p -> p.contains(" "));

		Collections.sort(LoanList);
		ArrayList<String> exList;
		exList = getLoanNumbersInExceptionList();
		int exListSize = LoanList.size();
		LoanList.removeAll(exList);

		int i = 0;
		// Get the first Loan Number.
		if ((LoanList.size() == 0)) {
			if (exListSize > 0) {
				reporter("Fatal", "Loan Numbers present in the page are also present in exeception List. Hence Looking for other loan Number with different ", "No Valid Loan Number Present in the Resultant Page");
			} else {
				reporter("FAIL", "size of the Loan Number generated is NULL [0] ", "No Valid Loan Number Present in the Resultant Page");
			}
			return null;
		} else {
			while (LoanList.get(i).contentEquals("")) {
				i = i + 1;
				// Check whether loan number is present in the exception list.
				if (LoanList.get(i).contentEquals("")) {
					break;
				}
			}
			return LoanList.get(i);
		}
	}

	private static ArrayList<String> getLoanNumbersInExceptionList() {

		ArrayList<String> exLoanList = new ArrayList<>();

		// Read the txt file.
		String SkipfileFlag = LoadConfigFile.getInstance().getValue("SkipLoanNumberFlag");
		String filePath = LoadConfigFile.getInstance().getValue("SkipLoanNumberFilePath");
		if (SkipfileFlag.equalsIgnoreCase("Yes")) {
			try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
				stream.forEach(str -> exLoanList.add(str));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return exLoanList;
	}

	public static WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			int i = 0;
			while (++i == 50) {
				if (driver.findElements(locator).size() != 0) {
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static boolean BrowseTabsInAPage(WebDriver driver, By by, boolean checkerror, String identifier) throws Exception {

		boolean bFlag = false;

		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);

		List<WebElement> tabs = driver.findElements(by);
		List<String> ids = new ArrayList<>(tabs.size());

		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).getAttribute("id").contains("tab_")) {
				ids.add(tabs.get(i).getText());
			} else {
				if (StringUtil.isBlank(tabs.get(i).getAttribute("id"))) {
					ids.add(tabs.get(i).getText());
				} else {
					ids.add(tabs.get(i).getAttribute("id"));
				}

			}
		}

		for (int j = 0; j < ids.size(); j++) {

			if (!ids.get(j).contains("tab") && StringUtil.isNotBlank(ids.get(j)) && !ids.get(j).trim().contains("InactiveTab")) {
				tabs = driver.findElements(by);
				try {
					tabs.get(j).click();
					checkPageError(ids.get(j).toLowerCase());
				} catch (Exception e) {
					if (!ids.get(j).trim().contains("_")) {
						driver.findElement(By.linkText(ids.get(j))).click();
					} else {
						driver.findElement(By.id(ids.get(j).trim())).click();
					}
					checkPageError(ids.get(j).toLowerCase());
				}

			} else if (StringUtil.isNotBlank(driver.findElement(By.id(ids.get(j).trim())).getText())) {
				if (!ids.get(j).trim().contains("_")) {
					driver.findElement(By.linkText(ids.get(j).trim())).click();

				} else {
					driver.findElement(By.id(ids.get(j).trim())).click();
				}
				checkPageError(ids.get(j).toLowerCase());
			}

			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
			if (checkerror) {
				String u = driver.getCurrentUrl();
				try {
					driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
					Thread.sleep(10000);
					bFlag = checkPageError(ids.get(j).toLowerCase());
				} catch (Exception e) {
					reporter("FAIL", "Got an Exception during the navigation to different tab", "Got an exception");
					e.printStackTrace();
				}
			}
		}
		if (!bFlag) {
			return false;
		}
		return true;
	}

	public static boolean SearchCheck() throws InterruptedException {

		SearchPage sg = new SearchPage(driver);
		LoanInfoPage lp = new LoanInfoPage(driver);

		// String propertyStatus =
		// LoadConfigFile.getInstance().getValue("Property_Status");

		// Get the Run time value of the status column.

		// String propertyState =
		// LoadConfigFile.getInstance().getValue("Property_State");

		// All the Steps of execution are written here.
		try {
			LoanNumber = sg.getExistingLoanNumber();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		reporter("INFO", "Got the Loan Number", "The Loan number to be used for the execution is [" + LoanNumber + "]");

		boolean bFlaglNumber = sg.searchWithLoanNumber(LoanNumber);
		if (bFlaglNumber) {
			reporter("PASS", "Search with the specific Loan Number[" + LoanNumber + "] Completed", "Search with the Loan Number[" + LoanNumber + "] was successfully");
		} else {
			reporter("FAIL", "Search with the specific Loan Number[" + LoanNumber + "] Completed with NO results", "Search with the Loan Number[" + LoanNumber + "] was successfully");
		}

		// Get the elements required from the loan number.

		reporter("INFO", "Reading the loan related information.", "Extracted required Information from a loan");

		try {
			sg.searchFilterVerification();
			reporter("INFO", "Search with Filters option is verified ", "search Filter verficiation completed.");
			lp.getLoanInformationDetails(LoanNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean OfferCheck() {

		OfferSearchPage op = new OfferSearchPage(driver);
		// Offer Status and State.
		// String offerStatus =
		// LoadConfigFile.getInstance().getValue("Offer_Status");
		// String offerState =
		// LoadConfigFile.getInstance().getValue("Offer_State");
		try {
			op.offerFilterVerfication();
			reporter("INFO", "Offer Filters verification completed.", "Offer Filters verification completed.");
		} catch (Exception e) {
			e.printStackTrace();
			reporter("FAILED", "Offer Filters verification Failed with an exception.[" + e.getMessage() + "]", "Offer Filters verification Failed with an exception.");
			return false;

		}

		try {
			op.offerPageVerification();
			reporter("INFO", "Offer Page verification completed.", "Offer Page verification completed.");
		} catch (Exception e) {

			e.printStackTrace();
			reporter("FAILED", "Offer Page verification Failed with an exception.[" + e.getMessage() + "]", "Offer Page verification Failed with an exception.");
			return false;
		}
		return true;
	}

	public static String selectPropertyHasStatus(WebElement propStatus, String typeSearch) throws InterruptedException {

		WebElement mySelectElement = propStatus;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Select dropdown = new Select(mySelectElement);
		try {
			new Select(driver.findElement(By.xpath(".//*[@id='PoolGroup']"))).selectByIndex(1); // /
																								// Included
																								// due
																								// to
																								// application
																								// bug.
		} catch (Exception e) {

		}

		List<WebElement> dd_Status = dropdown.getOptions();

		String property_status = null;
		By locater = null;

		for (int i = 1; i < dd_Status.size(); i++) {
			dropdown.selectByIndex(i);
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
			if (typeSearch.equalsIgnoreCase("Search")) {
				locater = By.id("propertySearchSubmit");
			} else if (typeSearch.equalsIgnoreCase("OfferSearch")) {
				locater = By.id("btnSearch");
			} else if (typeSearch.equalsIgnoreCase("InvestorPropertySearch")) {
				locater = By.id("propertySearch_searchButton");
			}
			driver.findElement(locater).click();
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
			WebElement option = dropdown.getFirstSelectedOption();
			property_status = option.getText();
			Thread.sleep(50000);
			WaitForElementToExist(driver, null, "ajaxElement");
			// Thread.sleep(50000);

			// Check for the Results.
			boolean bExist;
			if (typeSearch.equalsIgnoreCase("InvestorPropertySearch")) {
				bExist = driver.getPageSource().contains("No data available in table");
			} else {

				bExist = driver.getPageSource().contains("No matching data found!");
			}
			if (bExist) {
				reporter("INFO", "Records found in the results", "");

			} else {
				if (StringUtils.isNotBlank(property_status)) {
					if (typeSearch.equalsIgnoreCase("Search")) {
						lninfo.put("Property_Status", property_status);
					} else {
						lninfo.put("Offer_Status", property_status);
					}

					reporter("PASS", "Got the 'Property Status' value from the drop down [" + property_status + "]", "Got the Property Value.");
					break;
				}

			}
		}
		return property_status;

	}

	public static boolean runInvestorPortalChecks() throws Exception {

		// Check for the investor Portal .

		boolean bflag_propertySearch = true;
		// Check Search Property.
		// boolean bflag_propertySearch = SearchInvestorProperty();
		InvestorPropertySearch ips = new InvestorPropertySearch(driver);
		ips.linkProperties.click();
		String status = selectPropertyHasStatus(ips.selectPropertyStatus, "InvestorPropertySearch");
		if (StringUtils.isBlank(status)) {
			reporter("FAIL", "Failed to select the drop down value which has values", "");
		}

		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		checkPageError("Search Property Page -> Selected Drop down");

		// get the First Loan Number.
		// Get the Count of a WebTable.
		// String tableXpath = "//*[@id='UDFs_Table']";
		String tableXpath = "//table[contains(@id,'eqDataTable')]";
		String loanNumber = "";
		String address = "";

		WaitForElementToExist(driver, null, "ajaxElement");
		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);

		boolean bflag_table = driver.findElements(By.xpath(tableXpath)).size() != 0;

		if (bflag_table) {

			/*
			 * boolean bFlagrowCount = false; bFlagrowCount =
			 * getRowCountOfHtmltable(tableXpath, "1", "GreaterOrEqual", 2);
			 * assertBoolean(test, bFlagrowCount, true,
			 * "Found the required number of Rows",
			 * "Failed to find the required number of Rows", rootReportFolder +
			 * "\\SearchPage_rowCount.png"); ln =
			 * getLoanNumberFromSearchResult();
			 * 
			 * String url = driver.getCurrentUrl(); reporter("INFO",
			 * "getExistingLoanNumber: Clicked on 'Search Button' in Search page"
			 * , "Verifying the Search Page for errors.");
			 * checkPageError("Search Page"); reporter("PASS",
			 * "Clicked on the 'Search Button' link information Page completed for the URL ["
			 * + url + "]",
			 * "Verifying the 'List date' Linked Page for errors.");
			 */

			String loanNumberColXpath = "//table[contains(@id,'eqDataTable')]/tbody[1]//a[contains(@href,'property_id')][2]";
			String addressColXpath = "//table[contains(@id,'eqDataTable')]/tbody[1]//td[2]";
			List<WebElement> loanNumberElements = driver.findElements(By.xpath(loanNumberColXpath));
			List<WebElement> addressElements = driver.findElements(By.xpath(addressColXpath));
			if (loanNumberElements != null && loanNumberElements.size() == 0) {
				reporter("FAILED", "Search returned empty table.", "Verify the Filters again");
			} else {
				loanNumber = loanNumberElements.get(0).getText().trim();
				lninfo.put("INVESTOR_LOAN_NUMBER", loanNumber);
				address = addressElements.get(0).getText().trim();
				lninfo.put("LOAN_ADDRESS", address);
				reporter("PASS", "Fetched loan number - " + loanNumber, "Verifying the Search Page for errors.");
			}

			// downloadFile(By.cssSelector(".excel.byTaskExcelLink"),"C:\\1a.exe");

		} else {
			reporter("FAILED", "No results found when clicked on the search button , after applying the filters.", "Verify the Filters again");
		}

		if (loanNumber != null && !loanNumber.trim().equals("")) {

			ips = new InvestorPropertySearch(driver);
			ips.verifyInvestorPropertySearch();

			PropertyMessage pm = new PropertyMessage(driver);
			pm.checkPropertyMessage();

			MessageSearch ms = new MessageSearch(driver);
			ms.searchMessage(lninfo.get("LOAN_ADDRESS"), lninfo.get("INVESTOR_LOAN_NUMBER"));

			LibrarySearch ls = new LibrarySearch(driver);
			ls.checkForTabsInLibrary();

			Expenses exp = new Expenses(driver);
			exp.checkExpenses();

		}

		return bflag_propertySearch;

	}

	public static boolean openAProperty(String loanNumber, String module) {
		String menuSearchMainXPath = "";
		String menuSearchPropertiesXPath = "(//a[text()='Search Properties'])[1]";
		String txtSearchFieldXPath = "";
		String btnSearchXPath = "";
		String loanNumberElementsCollXPath = "";
		String linkTasksXPath = "";
		String btnResetXPath = "";
		boolean blnStatus = false;
		if (module.toLowerCase().equals("investorportal")) {
			menuSearchMainXPath = "(//a[starts-with(text(),'Properties')])[1]";
			txtSearchFieldXPath = ".//*[@id='SearchText']";
			btnSearchXPath = ".//*[@id='propertySearch_searchButton']";
			loanNumberElementsCollXPath = "//table[contains(@id,'eqDataTable')]/tbody[1]//a[contains(@href,'property_id')][2]";
			linkTasksXPath = "//a[@title='Tasks']";
			btnResetXPath = ".//*[@id='propertySearch_resetButton']";
		}
		driver.findElement(By.xpath(menuSearchMainXPath)).click();
		// WebElement searchProperties =
		// driver.findElement(By.xpath(menuSearchPropertiesXPath));
		// Actions actions = new Actions(driver);
		// actions.moveToElement(searchProperties);
		// actions.click().build().perform();
		driver.findElement(By.xpath(btnResetXPath)).click();
		driver.findElement(By.xpath(txtSearchFieldXPath)).sendKeys(loanNumber);
		driver.findElement(By.xpath(btnSearchXPath)).click();
		List<WebElement> loanNumberElementsColl = driver.findElements(By.xpath(loanNumberElementsCollXPath));

		if (loanNumberElementsColl != null && loanNumberElementsColl.size() != 0) {
			loanNumberElementsColl.get(0).click();
			if (driver.findElement(By.xpath(linkTasksXPath)).isDisplayed()) {
				blnStatus = true;
				reporter("PASS", "openAProperty : Opened property successsfully - " + loanNumber, "");
			} else {
				reporter("FAILED", "openAProperty : Failed in opening the searched out property - " + loanNumber, "");
			}
		} else {
			reporter("FAILED", "openAProperty : No results on searching with the provided laon number - " + loanNumber, "");
		}
		return blnStatus;
	}

	/*
	 * public static boolean SearchInvestorProperty(){
	 * 
	 * PropertyMessage pmessage = new PropertyMessage(driver);
	 * 
	 * 
	 * 
	 * }
	 */

	public static boolean runBuyerPortalChecks() throws InterruptedException {
		boolean blnStatus = false;
		String url = "";
		String ws_url = "";
		switch (environment.toUpperCase()) {

		case "ALPHA":
			url = SYSPARAM.getProperty("ALPHABUYERPORTAL_URL");
			ws_url = SYSPARAM.getProperty("ALPHAWS_URL");
			break;

		case "BETA":
			url = SYSPARAM.getProperty("BETABUYERPORTAL_URL");
			ws_url = SYSPARAM.getProperty("BETAWS_URL");
			break;

		case "STAGE":
			url = SYSPARAM.getProperty("STAGEBUYERPORTAL_URL");
			ws_url = SYSPARAM.getProperty("STAGEWS_URL");
			break;

		case "REM":
			url = SYSPARAM.getProperty("REMBUYERPORTAL_URL");
			ws_url = SYSPARAM.getProperty("REMWS_URL");
			break;

		}

		lninfo.put("WS_URL", ws_url);
		driver.get(url);

		BP_LoginPage bpLoginPage = new BP_LoginPage(driver);

		bpLoginPage.performLoginPageChecks();

		Login.loginToBuyerPortal();

		BP_PropertiesHomePage bpPropertiesPage = new BP_PropertiesHomePage(driver);

		bpPropertiesPage.performPropertiesPageChecks();

		BP_PropertyDetails bpPropertiesDetails = new BP_PropertyDetails(driver);

		bpPropertiesDetails.performPropertyDetailsPageChecks();

		BP_Messages bpMessages = new BP_Messages(driver);

		bpMessages.performMessagesPageChecks();

		return blnStatus;
	}

	public static void runVendorCheckListChecks() {
		VendorPortalSearchProperties vs = new VendorPortalSearchProperties(driver);

		if (LoadConfigFile.getInstance().getValue("Vendor_Property_Tab").trim().equalsIgnoreCase("Yes")) {
			try {
				boolean bFlag_VendorSearch = vs.searchPropertyfromVendorPortal();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'Vendor Search' Verification Failed", "'Vendor Search' Verification Failed");
				} else {
					reporter("PASS", "'Vendor Search' Verification completed with No Errors", "'Vendor Search' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "Vendor Search Filter Verification Failed [" + e.getMessage() + "]", "Vendor Filter Verification Failed");
			}
		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Vendor_Workflow_Tab").trim().equalsIgnoreCase("Yes")) {
			try {

				boolean bFlag_VendorSearch = vs.workflowTabVerification();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'WorkFlow' tab Verification Failed", "''WorkFlow' tab' Verification Failed");
				} else {
					reporter("PASS", "'WorkFlow' tab Verification completed with No Errors", "''WorkFlow' tab' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "'WorkFlow' tab Filter Verification Failed [" + e.getMessage() + "]", "'WorkFlow' tab Verification Failed");

			}

		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Vendor_Overview_Tab").trim().equalsIgnoreCase("Yes")) {
			try {

				boolean bFlag_VendorSearch = vs.overviewTabVerification();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'Overview' tab Verification Failed", "'Overview tab' Verification Failed");
				} else {
					reporter("PASS", "'Overview' tab Verification completed with No Errors", "''Overview' tab' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "'Overview' tab Filter Verification Failed [" + e.getMessage() + "]", "'Overview' tab Verification Failed");
			}
		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Vendor_Messages_Tab").trim().equalsIgnoreCase("Yes")) {
			try {

				boolean bFlag_VendorSearch = vs.messagesTabVerification();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'Messages' tab Verification Failed", "''Messages' tab' Verification Failed");
				} else {
					reporter("PASS", "'Messages' tab Verification completed with No Errors", "''Messages' tab' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "'Messages' tab Filter Verification Failed [" + e.getMessage() + "]", "'Messages' tab Verification Failed");

			}
		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Vendor_MyAccount_Tab").trim().equalsIgnoreCase("Yes")) {
			try {

				boolean bFlag_VendorSearch = vs.myAccountTabVerification();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'MyAccount' tab Verification Failed", "''MyAccount' tab' Verification Failed");
				} else {
					reporter("PASS", "'MyAccount' tab Verification completed with No Errors", "''MyAccount' tab' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "'MyAccount' tab Filter Verification Failed [" + e.getMessage() + "]", "'MyAccount' tab Verification Failed");

			}
		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Vendor_Admin_Tab").trim().equalsIgnoreCase("Yes")) {
			try {

				boolean bFlag_VendorSearch = vs.adminTabVerification();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'Admin' tab Verification Failed", "''Admin' tab' Verification Failed");
				} else {
					reporter("PASS", "'Admin' tab Verification completed with No Errors", "''Admin' tab' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "'Admin' tab Filter Verification Failed [" + e.getMessage() + "]", "'Admin' tab Verification Failed");

			}
		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Vendor_Reports_Tab").trim().equalsIgnoreCase("Yes")) {
			try {

				boolean bFlag_VendorSearch = vs.reportsTabVerification();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'Reports' tab Verification Failed", "''Reports' tab' Verification Failed");
				} else {
					reporter("PASS", "'Reports' tab Verification completed with No Errors", "''Reports' tab' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "'Reports' tab Filter Verification Failed [" + e.getMessage() + "]", "'Reports' tab Verification Failed");

			}
		}
		extent.flush();

		if (LoadConfigFile.getInstance().getValue("Vendor_Expense_Tab").trim().equalsIgnoreCase("Yes")) {
			try {

				boolean bFlag_VendorSearch = vs.expenseTabVerification();
				if (!bFlag_VendorSearch) {
					reporter("FAIL", "'Expense' tab Verification Failed", "''Expense' tab' Verification Failed");
				} else {
					reporter("PASS", "'Expense' tab Verification completed with No Errors", "''Expense' tab' Verification completed with No Errors");
				}
			} catch (Exception e) {
				reporter("FAIL", "'Expense' tab Filter Verification Failed [" + e.getMessage() + "]", "'Expense' tab Verification Failed");

			}
		}
		extent.flush();
	}

	public static void runVendorFilterforMessages() {

		if (LoadConfigFile.getInstance().getValue("Filter_Messages").trim().equalsIgnoreCase("Yes")) {
			try {
				runVendorFilterforInbox();
			} catch (Exception e) {
				reporter("FAIL", "Filter Functionality for Inbox not working.", "");
			}
		}
	}

	public static void runVendorFilterforInbox() {
		VendorPortalFilterMessages vpf = new VendorPortalFilterMessages(driver);
		WebDriverWait wait = new WebDriverWait(driver, 60);

		vpf.openInbox(driver);

		String fromtext = driver.findElement(By.xpath("//*[@id='header1']/td[4]/div[1]/span")).getText();
		String roletext = driver.findElement(By.xpath("//*[@id='header1']/td[5]/div")).getText();
		String categorytext = driver.findElement(By.xpath("//*[@id='header1']/td[7]/div")).getText();
		String subjecttext = driver.findElement(By.xpath("//*[@id='header1']/td[8]/div/span[1]")).getText();
		String loannotext = driver.findElement(By.xpath("//*[@id='header1']/td[9]/div/a[2]")).getText();
		String addresstext = driver.findElement(By.xpath("//*[@id='header1']/td[10]/div/a[2]/span")).getText();
		String servicertext = driver.findElement(By.xpath("//*[@id='header1']/td[11]/div")).getText();
		String datetext = driver.findElement(By.xpath("//*[@id='header1']/td[12]/div/span[1]")).getText();

		// From Search Field Check
		fromtext = CommonFunctions.removedots(fromtext);
		vpf.filterFieldCheck(vpf.getFromSearch(), fromtext, "messagesTable", "From", "Inbox");

		// Role Search Field Check
		roletext = CommonFunctions.removedots(roletext);
		vpf.filterFieldCheck(vpf.getRoleSearch(), roletext, "messagesTable", "Role", "Inbox");

		// Category Search Field Check
		categorytext = CommonFunctions.removedots(categorytext);
		vpf.filterFieldCheck(vpf.getCategorySearch(), categorytext, "messagesTable", "Category", "Inbox");

		// Subject Search Field Check
		subjecttext = CommonFunctions.removedots(subjecttext);
		vpf.filterFieldCheck(vpf.getSubjectSearch(), subjecttext, "messagesTable", "Subject", "Inbox");

		// Loan Number Search Field Check
		loannotext = CommonFunctions.removedots(loannotext);
		vpf.filterFieldCheck(vpf.getLoanNoSearch(), loannotext, "messagesTable", "Loan Number", "Inbox");

		// Address Search Field Check
		addresstext = CommonFunctions.removedots(addresstext);
		vpf.filterFieldCheck(vpf.getAddressSearch(), addresstext, "messagesTable", "Address", "Inbox");

		// Servicer Search Field Check
		vpf.filterFieldCheck(vpf.getServicerSearch(), servicertext, "messagesTable", "Servicer", "Inbox");

		// Servicer Search Field Check
		vpf.getDateSearchInbox().click();
		CommonFunctions.datepicker(datetext);
		vpf.getBtnSearch().click();
		CommonFunctions.rowsValidation_forSearch("messagesTable", "Date", "Inbox");

		vpf.openInbox(driver);

		// Sort Func for From Field
		CommonFunctions.sortFunctionCheck("messagesTable", 4, "/div[1]/span", "From", "Inbox");

		// Sort Func for Role Field
		CommonFunctions.sortFunctionCheck("messagesTable", 5, "/div", "Role", "Inbox");

		// Sort Func for Category Field
		CommonFunctions.sortFunctionCheck("messagesTable", 7, "/div", "Category", "Inbox");

		// Sort Func for Subject Field
		CommonFunctions.sortFunctionCheck("messagesTable", 8, "/div/span[1]", "Subject", "Inbox");

		// Sort Func for Loan Number Field
		CommonFunctions.sortFunctionCheck("messagesTable", 9, "/div/a[2]", "Loan Number", "Inbox");

		// Sort Func for Address Field
		CommonFunctions.sortFunctionCheck("messagesTable", 10, "/div/a[2]/span", "Address", "Inbox");

		// Sort Func for Servicer Field
		CommonFunctions.sortFunctionCheck("messagesTable", 11, "/div", "Servicer", "Inbox");

		// Sort Func for Date Field
		CommonFunctions.sortFunctionCheck("messagesTable", 12, "/div/span[1]", "Date", "Inbox");

	}

	public static void runConsumerSiteChecks(String url) {

		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		ConsumerSite_PropertyPage cp = new ConsumerSite_PropertyPage(driver);

		try {
			System.out.println("****City Search");

			cs.verifyCitySearch(url);

		} catch (Exception e) {

			reporter("FAIL", ConsumerSite.csUserType + " " + ConsumerSite.csBrowserType + " City search in Consumer Site is ***NOT Working*** as expected", "");
			e.printStackTrace();
		}

		try {
			System.out.println("****Pincode Search");
			cs.verifyZipCodeSearch(url);
		} catch (Exception e) {

			reporter("FAIL", ConsumerSite.csUserType + " " + ConsumerSite.csBrowserType + " Pincode search in Consumer Site is ***NOT Working*** as expected", "");
			e.printStackTrace();
		}

		try {
			System.out.println("****Address Search");
			cs.verifyAddressSearch(url);
		} catch (Exception e) {

			reporter("FAIL", ConsumerSite.csUserType + " " + ConsumerSite.csBrowserType + " Address search in Consumer Site is ***NOT Working*** as expected", "");
			e.printStackTrace();
		}

		try {

			System.out.println("****AutoSug Search");
			cs.verifyAutoSuggestTextSearch(url);
		} catch (Exception e) {

			reporter("FAIL", ConsumerSite.csUserType + ":" + ConsumerSite.csBrowserType + " AutoSuggestion Text search in Consumer Site is ***NOT Working*** as expected", "");
			e.printStackTrace();
		}

		try {
			System.out.println("****Carousel");
			cs.verifyCarousel(url);
		} catch (Exception e) {

			reporter("FAIL", ConsumerSite.csUserType + ":" + ConsumerSite.csBrowserType + " Carousel functionality in Consumer Site is ***NOT Working*** as expected", "");
			e.printStackTrace();
		}

		try {
			System.out.println("****PropertyDetailspage");
			//cp.verifyPropertyDetailsPage();
		} catch (Exception e) {

			reporter("FAIL", ConsumerSite.csUserType + ":" + ConsumerSite.csBrowserType + " Exception occured in verifyPropertyDetailsPage method ", "");
			e.printStackTrace();
		}

		try {
			System.out.println("****Contact Listing Agent");
			cp.verifyContactListingAgent();
		} catch (Exception e) {

			reporter("FAIL", ConsumerSite.csUserType + ":" + ConsumerSite.csBrowserType + " Exception occured in verifyPropertyDetailsPage method", "");
			e.printStackTrace();
		}

	}
}
