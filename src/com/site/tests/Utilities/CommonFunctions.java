package com.site.tests.Utilities;

import static com.site.runners.ConsumerSite_TestRunner.invoker;
import static com.site.tests.Utilities.Login.User_Login;
import static com.site.tests.testcases.baseTest.MKS_Password;
import static com.site.tests.testcases.baseTest.MKS_Query;
import static com.site.tests.testcases.baseTest.MKS_Update;
import static com.site.tests.testcases.baseTest.MKS_UserName;
import static com.site.tests.testcases.baseTest.defaultTimeout;
import static com.site.tests.testcases.baseTest.environment;
import static com.site.tests.testcases.baseTest.extent;
import static com.site.tests.testcases.baseTest.finalTestStatus;
import static com.site.tests.testcases.baseTest.lenderName;
import static com.site.tests.testcases.baseTest.lenderValue;
import static com.site.tests.testcases.baseTest.release;
import static com.site.tests.testcases.baseTest.reportLendermks;
import static com.site.tests.testcases.baseTest.reportPath;
import static com.site.tests.testcases.baseTest.rootReportFolder;
import static com.site.tests.testcases.baseTest.send_emailTo;
import static com.site.tests.testcases.baseTest.test;
import static com.site.tests.testcases.baseTest.csBrowserType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.util.StringUtil;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.site.tests.testcases.ConsumerSite_Baseclass;
import com.google.common.base.Throwables;
import com.google.common.collect.Ordering;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CommonFunctions {

	public static boolean browserAlreadyOpen = false;
	public static WebDriver driver = null;
	public static Properties SYSPARAM = null;
	static ExtentTest logger;

	public static void initData() throws IOException {

		SYSPARAM = new Properties();
		String path = new File("globalConfig.properties").getAbsolutePath();
		FileInputStream ist = new FileInputStream(path);
		SYSPARAM.load(ist);
	}

	// Check if the browser is already open , else open the browser
	public static void initBrowser(String browser) throws InterruptedException {

		if (!browserAlreadyOpen) {
			try {
				if (browser.equalsIgnoreCase("Firefox")) {
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("browser.download.dir", folder.getAbsolutePath());
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg, application/pdf, application/octet-stream");
					profile.setPreference("pdfjs.disabled", true);
					// driver = new FirefoxDriver(profile);

					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					String path = new File("chromedriver.exe").getAbsolutePath();
					System.setProperty("webdriver.chrome.driver", path);
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("IE")) {
					// Create object of DesiredCapabilities class

					DesiredCapabilities cap = DesiredCapabilities.internetExplorer();

					// Set ACCEPT_SSL_CERTS variable to true
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					cap.setCapability("ignoreZoomSetting", true);
					cap.setCapability("nativeEvents", false);
					cap.setCapability("unexpectedAlertBehaviour", "accept");
					cap.setCapability("ignoreProtectedModeSettings", true);
					cap.setCapability("enablePersistentHover", true);

					// Open browser with capability

					String path = new File("IEDriverServer.exe").getAbsolutePath();
					System.setProperty("webdriver.ie.driver", path);

					// driver = new InternetExplorerDriver(ieCapabilities);
					driver = new InternetExplorerDriver(cap);
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				}

			} catch (WebDriverException e) {
				// //System.out.println(e.getMessage());
			}
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		browserAlreadyOpen = true;
	}

	public static void csInitBrowser(String browser) throws InterruptedException {
		if (!browserAlreadyOpen) {
			File folder;
			folder = new File(UUID.randomUUID().toString());
			folder.mkdir();
			try {
				if (browser.equalsIgnoreCase("Firefox")) {
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("browser.download.dir", folder.getAbsolutePath());
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg, application/pdf, application/octet-stream");
					profile.setPreference("pdfjs.disabled", true);
					// driver = new FirefoxDriver(profile);

					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					String path = new File("chromedriver.exe").getAbsolutePath();
					System.setProperty("webdriver.chrome.driver", path);
					driver = new ChromeDriver();
					System.out.println("Creationi" + driver);
				} else if (browser.equalsIgnoreCase("iphone") || browser.equalsIgnoreCase("android")) {
					String DeviceName = null;
					if (browser.equalsIgnoreCase("iphone")) {
						DeviceName = "Apple iPhone 6 Plus";

					} else if (browser.equalsIgnoreCase("android")) {
						DeviceName = "Samsung Galaxy S4";
					}

					Map<String, String> mobileEmulation = new HashMap<String, String>();
					mobileEmulation.put("deviceName", DeviceName);
					Map<String, Object> chromeOptions = new HashMap<String, Object>();
					chromeOptions.put("mobileEmulation", mobileEmulation);

					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

					String path = new File("chromedriver.exe").getAbsolutePath();
					System.setProperty("webdriver.chrome.driver", path);
					driver = new ChromeDriver(capabilities);
				} else if (browser.equalsIgnoreCase("IE")) {
					// Create object of DesiredCapabilities class

					DesiredCapabilities cap = DesiredCapabilities.internetExplorer();

					// Set ACCEPT_SSL_CERTS variable to true
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					cap.setCapability("ignoreZoomSetting", true);
					cap.setCapability("nativeEvents", false);
					cap.setCapability("unexpectedAlertBehaviour", "accept");
					cap.setCapability("ignoreProtectedModeSettings", true);
					cap.setCapability("enablePersistentHover", true);

					// Open browser with capability

					String path = new File("IEDriverServer.exe").getAbsolutePath();
					System.setProperty("webdriver.ie.driver", path);

					driver = new InternetExplorerDriver(cap);

				}
			} catch (WebDriverException e) {
				// //System.out.println(e.getMessage());
			}

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			browserAlreadyOpen = true;
		}

	}

	public static void assertBoolean(ExtentTest log, boolean actual, boolean expected, String description, String faildescription, String fileName) {

		if (actual == expected) {
			log.log(LogStatus.PASS, description);
		} else {
			log.log(LogStatus.FAIL, faildescription);
			String workingDir = System.getProperty("user.dir");
			String fPath = fileName;
			captureScreenShot(driver, fPath);
			String img = log.addScreenCapture(fPath);
			log.log(LogStatus.FAIL, img);
		}
	}

	// Pro Workstation Login Function

	public static void assertBoolean(ExtentTest log, String testStatus, String description, String faildescription, String fileName) {
		String fPath = fileName;
		String img = log.addScreenCapture(fPath);
		captureScreenShot(driver, fPath);
		// System.out.println(testStatus);

		if (testStatus.equalsIgnoreCase("info")) {
			log.log(LogStatus.INFO, description);
			return;
		}
		if (testStatus.equalsIgnoreCase("pass")) {
			log.log(LogStatus.PASS, description, img);
			return;
		}
		if (testStatus.equalsIgnoreCase("warning")) {
			log.log(LogStatus.WARNING, faildescription, img);
			return;
		}
		if (testStatus.equalsIgnoreCase("fatal")) {

			log.log(LogStatus.FATAL, faildescription, img);
			return;
		} else {
			log.log(LogStatus.FAIL, faildescription, img);
			return;
		}
	}

	public static void captureScreenShot(WebDriver ldriver, String filePath) {

		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File(filePath));
		}

		catch (IOException e)

		{

			// System.out.println(e.getMessage());

		}

	}

	public static void reporter(String Status, String stepname, String description) {
		String fileName = new SimpleDateFormat("yyyyMMddhhmmssmm'.png'").format(new Date());

		switch (Status.toLowerCase()) {
		case "info":
			if (LoadConfigFile.getInstance().getValue("logLevel").equalsIgnoreCase("DEBUG")) {
				test.log(LogStatus.INFO, stepname, description);
			}
			break;

		case "pass":
		case "passed":
			fileName = rootReportFolder + "\\PASS_" + lenderName + "_" + fileName;
			assertBoolean(test, "PASS", stepname, "", fileName);
			break;

		case "fatal":
			fileName = rootReportFolder + "\\Fatal_" + lenderName + "_" + fileName;
			assertBoolean(test, "Fatal", "", stepname, fileName);
			break;

		case "warning":
			fileName = rootReportFolder + "\\Warning_" + lenderName + "_" + fileName;
			assertBoolean(test, "Warning", "", stepname, fileName);
			break;

		case "skip":
			fileName = rootReportFolder + "\\Warning_" + lenderName + "_" + fileName;
			assertBoolean(test, "Skip", stepname, "", fileName);
			break;

		case "fail":
		case "failed":
			fileName = rootReportFolder + "\\FAIL_" + lenderName + "_" + fileName;
			assertBoolean(test, "Fail", "", stepname, fileName);
			break;

		default:
			assertBoolean(test, "WARNING", "", stepname, description + "\n Note:[this status is not currently supported");
			break;
		}
		extent.flush();
	}

	public static boolean getRowCountOfHtmltable(String xpath, String expectedRow, String symbol, int headerRows) {

		int rowCount = -1;
		int expectedRowToVerify = 0;
		boolean bFlag = false;

		WebElement htmltable = driver.findElement(By.xpath(xpath));
		List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
		rowCount = rows.size() - headerRows;

		if (expectedRow == null) {
			expectedRowToVerify = 1;
		} else {
			expectedRowToVerify = Integer.parseInt(expectedRow);
		}

		switch (symbol.toLowerCase()) {
		case "greater":
		case "greaterthan": {
			if ((rowCount > expectedRowToVerify)) {
				reporter("PASS", "Row Matched", "Row Count condition matched. Actual Row Count in the Table is [" + rowCount + "] which is greater as expected");
				bFlag = true;
				return bFlag;
			}
			break;
		}

		case "greaterorequal":
		case "equalorgreater": {
			if ((rowCount > expectedRowToVerify)) {
				reporter("PASS", "Row Matched", "Row Count matched. Actual Row Count in the Table is [" + rowCount + "]");
				bFlag = true;
				return bFlag;
			} else {
				if ((rowCount == expectedRowToVerify)) {
					reporter("PASS", "Row Matched", "Row Count matched. Actual Row Count in the Table is [" + rowCount + "]");
					bFlag = true;
					return bFlag;
				}
			}
			break;
		}

		case "less":
		case "lessthan":
		case "smallerthan":
		case "small": {
			if ((rowCount < expectedRowToVerify)) {
				reporter("PASS", "Row Matched", "Row Count matched. Actual Row Count in the Table is [" + rowCount + "] is less as expected");
				bFlag = true;
				return bFlag;
			}
			break;
		}

		case "equals":
		case "equal":
		default: {
			if ((rowCount == expectedRowToVerify)) {
				reporter("PASS", "Row Matched", "Row Count matched. Actual Row Count in the Table is [" + rowCount + "]");
				bFlag = true;
				return bFlag;
			}
			break;
		}
		}

		return bFlag;
	}

	public static boolean checkContentPresentInRows(String table, String txtTosearch) {
		boolean bFlag = false;
		try {
			// int index = 0;
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);

			if (driver.findElements(By.xpath(table)).size() != 0) {
				WebElement baseTable = driver.findElement(By.xpath(table));
				List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));

				for (int i = 0; i < tableRows.size(); i++) {
					String textCell = tableRows.get(i).getText().toLowerCase();
					if (textCell.contains(txtTosearch.toLowerCase())) {
						reporter("INFO", "Got the required Text in the table", "Got the required Text[" + textCell + " at the row numbered [" + (i + 1) + "] content is present in the Row Resulted");
						bFlag = true;
						break;
					}
				}
			} else {
				reporter("Warning", "Table not identified", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!bFlag) {
			reporter("FAIL", "Failed to find the Text in the rows present in the table", "Failed to find the Text in the rows present in the table. Text searched is [" + txtTosearch + "]");
		}
		return bFlag;
	}

	public static List<String> getContentWebTable(String columnaName, By locator) {

		WebElement table = driver.findElement(locator);

		List<WebElement> th = table.findElements(By.tagName("th"));

		List<String> list = new ArrayList<String>(th.size());

		int col_position = 0;
		for (int i = 0; i < th.size(); i++) {
			if (columnaName.equalsIgnoreCase(th.get(i).getText())) {
				col_position = i + 1;
				break;
			}
		}
		List<WebElement> FirstColumns = table.findElements(By.xpath("//tr/td[" + col_position + "]"));
		for (WebElement e : FirstColumns) {
			list.add(e.getText());
		}

		return list;
	}

	public static boolean retryingFindClick(WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public static String getStateMapping(String state) {

		String stateMapped;

		switch (state.toString().toLowerCase()) {

		case "california":
			stateMapped = "CA";
			break;

		case "illinois":
			stateMapped = "IL";
			break;

		case "michigan":
			stateMapped = "MI";
			break;

		case "louisiana":
			stateMapped = "LA";
			break;

		case "nevada":
			stateMapped = "NV";
			break;

		case "new york":
			stateMapped = "NY";
			break;

		default:
			stateMapped = "CA";
			break;
		}

		return stateMapped;
	}

	public static String getCellValueFromWebTable(By tablePath, int rowNumber, int columnNumber) {

		// To locate table.
		WebElement mytable = driver.findElement(tablePath);
		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int rows_count = rows_table.size();

		String celtext = null;

		// Loop will execute till the last row of table.
		for (int row = 0; row < rows_count; row++) {

			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();

			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				if (column == columnNumber && row == rowNumber) {
					celtext = Columns_row.get(column).getText();
					return celtext;
				}
			}
		}
		return celtext;
	}

	public static void verifyForNull(String reportElem, String val, boolean checkFlag) {
		try {
			if (val.equalsIgnoreCase(null)) {
				if (checkFlag) {
					reporter("FAIL", "Element had the value NULL", "NULL Value is for the Element [" + reportElem + "]");
				} else {
					reporter("Warning", "Element had the value NULL", "Null Value is for the Element [" + reportElem + "]");
				}
			} else {
				reporter("PASS", "Element [" + reportElem + "] has the value [" + val + "].", "Element value is not NULL");
			}
		} catch (NullPointerException npe) {
			reporter("FATAL", "Null pointer expected [" + reportElem + "]," + val + "]", "verifyForNull method throws an error");
		}
	}

	public static void WaitForElementToExist(WebDriver ldriver, By by, String elementType) {

		try {

			switch (elementType.toLowerCase()) {

			case "weblist":
			case "dropdown":
			case "dd":
				/*
				 * final Select droplist = new Select(ldriver.findElement(by));
				 * new FluentWait<WebDriver>(ldriver) .withTimeout(120,
				 * TimeUnit.SECONDS) .pollingEvery(10, TimeUnit.MILLISECONDS)
				 * .until(new com.google.common.base.Predicate<WebDriver>() {
				 * public boolean apply(WebDriver d) { return (
				 * droplist.getOptions().size() > 2); } });
				 */
				break;

			case "webelement":
			case "element":
				new FluentWait<WebDriver>(ldriver).withTimeout(180, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class).until(new ExpectedCondition<Boolean>() {

					public Boolean apply(WebDriver wd) {
						try {
							wd.findElement(by);
							return true;
						} catch (InvalidSelectorException t) {
							return true;
						}
					}
				});
				break;

			case "ajaxelement":
			case "ajax":
				new FluentWait<WebDriver>(ldriver)

				.withTimeout(240, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver ld) {
						try {
							ld.findElement(By.xpath("./*//*img[@title='Loading']"));
						} catch (Exception t) {
							reporter("INFO", "No Loading Image Found.", "Exiting from the Wait function");
							return true;
						}

						// boolean bflag =
						// ldriver.findElements(By.xpath("./*//*img[@title='Loading']")).size()!=0;
						return true;
					}
				});
				break;
			}
		} catch (NoSuchElementException e) {
			reporter("INFO", "Got an Exception ", "");
		} finally {
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		}
	}

	public static boolean checkPageError(String key) throws Exception {

		// Check for Java Script Errors .
		boolean bflag_cacheerror = driver.getPageSource().toLowerCase().contains("this site website is temporarily unavailable");
		String currenturl = driver.getCurrentUrl();
		if (bflag_cacheerror) {
			// Cache issue. lets wait and loop through the errors.

			bflag_cacheerror = checkCacheError(currenturl);
		}

		String error = (String) ((JavascriptExecutor) driver).executeScript("return window.jsErrors");

		if (!(error == null || error.trim().length() == 0)) {
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());
			fileName = rootReportFolder + "\\Fail_JavaScript_" + lenderName + "_" + fileName;
			// assertBoolean(test,"Pass",pair.getKey() +
			// "Link Passed with No Errors","",fileName);
			assertBoolean(test, "Fail", "", key + " : Link with Java Errors Found.\n Page_URL: [" + currenturl + "]", fileName);
			return false;
		}

		// Check for Any Page Error.
		boolean bflag_logoutLink;
		boolean bflag = driver.getPageSource().toLowerCase().contains("unexpected error");
		boolean bflag_serverError = driver.getPageSource().toLowerCase().contains("server error");
		boolean bflag_UpdateError = driver.getPageSource().toLowerCase().contains("update issue");
		boolean bflag_sError = driver.getPageSource().toLowerCase().contains("500 - internal server error.");
		boolean bflag_serError = driver.getPageSource().toLowerCase().contains("service unavailable");
		boolean bflag_PageError = driver.getPageSource().toLowerCase().contains("404 - file or directory not found");
		boolean bflag_error = driver.getPageSource().toLowerCase().contains("error has occurred");
		bflag_logoutLink = driver.getPageSource().toLowerCase().contains("logout");
		boolean bflag_jsonerror = driver.getPageSource().toLowerCase().contains("caught exception in jsondataset");

		if (bflag_error) {
			int counter = 0;
			int errorCounter = 0;
			String str = driver.getPageSource().toLowerCase();
			// Get the occurrence of error has occurred.

			errorCounter = StringUtils.countMatches(str, "error has occurred");
			counter = StringUtils.countMatches(str, "error has occurred.</option>");

			int i = errorCounter - counter;
			if (i > 0) {
				bflag_error = true;
			} else {
				bflag_error = false;
			}
		}
		boolean blfag_Error = false;
		if (bflag_UpdateError == true && bflag_error == true) {
			blfag_Error = true;
		}

		if (bflag_jsonerror || bflag_error || bflag || bflag_serverError || bflag_sError || bflag_PageError || bflag_cacheerror || bflag_serError || !bflag_logoutLink || blfag_Error) {
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());
			fileName = rootReportFolder + "\\Fail_" + lenderName + "_" + fileName;
			assertBoolean(test, "Fail", "", key + " : Page with Error Found.\n Page_URL: [" + currenturl + "]", fileName);
			String u = driver.getCurrentUrl().split(Pattern.quote("?"))[0];
			driver.get(u);
			if (blfag_Error) {
				driver.navigate().back();
				Thread.sleep(10000);
				driver.navigate().refresh();
			}
			return false;
		}

		// boolean bflag_aperror =
		// driver.getPageSource().toLowerCase().contains("we apologize");
		/*
		 * if ( bflag_aperror){ String fileName = new
		 * SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date()); fileName
		 * = rootReportFolder+"\\Warning_"+ lenderName+"_"+fileName;
		 * assertBoolean(test,"Warning",key+
		 * " : Page with content 'we apologize' string found in the page.\n Page_URL: ["
		 * +currenturl+"]","",fileName); return false; }
		 */

		int logoutMessageCount = -1;
		int errorCount = -1;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		logoutMessageCount = driver.findElements(By.xpath(".//*[@class='error']")).size();
		errorCount = driver.findElements(By.xpath(".//*[@class='error']")).size();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		if (logoutMessageCount > 2 || errorCount > 2) {
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());
			fileName = rootReportFolder + "\\Fail_" + lenderName + "_" + fileName;
			assertBoolean(test, "Fail", "", key + " : Forceful logout when clicked on Link Found ..! \n Page_URL: [" + currenturl + "]", fileName);
			return false;
		}

		return true;
	}

	public static boolean checkCacheError(String currentURL) throws Exception {

		int bflag_counter = 0;
		boolean bflag = true;
		String url;

		// Check for the token Number remaining seconds.
		while (bflag_counter < 5 && bflag) {
			url = driver.getCurrentUrl();
			if (url.toLowerCase().contains("offline.html")) {
				Thread.sleep(100000);
				bflag_counter += 1;
			} else {
				bflag_counter = 10;
				bflag = false;
				return bflag;
			}
			driver.get(currentURL);
		}

		return true;
	}

	public static WebElement FindElementSafe(WebDriver driver, By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	

	public static void generateAndSendEmailConsumerSite() throws Exception {

		String emailServer = LoadConfigFile.getInstance().getValue("Email_Server");
		String emailTo = LoadConfigFile.getInstance().getValue("Email_to");
		String emailFrom = LoadConfigFile.getInstance().getValue("Email_from");
		String emailPort = LoadConfigFile.getInstance().getValue("emailPort");
		String sendEmail = LoadConfigFile.getInstance().getValue("sendEmail");

		String to = emailTo;
		String from = emailFrom;
		String host = emailServer;
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = null;
		try {
			session = Session.getDefaultInstance(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, to);
			
			
						

			// String url="http://remwww.site.com";
			String env = environment;
			String rel = getReleaseNumber(env.toUpperCase());
			String url = SYSPARAM.getProperty(environment.toUpperCase() + "CONSUMERSITE_URL");
			String portal = "Consumer Site";

			String report = reportPath;
			if (report.toLowerCase().contains("y:\\")) {
				report = "\\\\isilon-hq-dfw\\DeptShare\\COE_QSM\\Public" + reportPath.substring(3, reportPath.length());
			}
		
			report = "\"" + report + "\"";
			
			
			// Create the message part
			// MimeBodyPart messageBodyPart = new MimeBodyPart();
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			Template t = ve.getTemplate("./config/templates/Consumer.vm");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();
			
			
			String vm=InetAddress.getLocalHost().getHostName();
			String executedBy=System.getProperty("user.name");
			String testRail="No";
			String triggerMech=invoker;
			String browser=csBrowserType;
			
			context.put("vm", vm);
			context.put("executedBy", executedBy);
			context.put("testRail", testRail);
			context.put("triggerMech", triggerMech);
			context.put("browser", browser);

			context.put("env", env);
			context.put("url", url);
			context.put("lender", portal);
			context.put("reportPath", report);
			
			
			Map<String, String> lastRun = readTestRunResults(portal, env);

			String lastRunDate;
			String lastRunStatus;
			String lastRunFailedTask;

			if (lastRun.size() == 0) {
				lastRunDate = "Not Found";
				lastRunStatus = "Not Found";
				lastRunFailedTask = "Not Found";
				

			} else {
				lastRunDate = lastRun.get("lastRunDate");
				lastRunStatus = lastRun.get("lastRunStatus");
				lastRunFailedTask = lastRun.get("lastFailedTask");
									
				}
			
			context.put("lastRunDate", lastRunDate);
			context.put("lastRunStatus", lastRunStatus);
			context.put("lastRunFailedTask", lastRunFailedTask);
			

			List<Data> values = new ArrayList<Data>();

			int j = ConsumerSite_Baseclass.listConsumerSite.size();
			int x = 0;
			StringBuilder failedTask=new StringBuilder();
			for (int i = 1; i <= j; i++) {

				String ut = "";
				String sc = "";
				String status = "";
				String comments = "";
				
				String tc = "";

				String temp = (String) ConsumerSite_Baseclass.listConsumerSite.get(i);

				String[] tmp = temp.split("\\*");

				for (int k = 0; k < tmp.length; k++) {

					if (k == 0) {
						tc = tmp[k];
						ut = tmp[k].split(":")[0];
						sc = tmp[k].split(":")[1];

					} else if (k == 1) {

						status = tmp[k];

					} else if (k == 2) {
						comments = tmp[k];
						if (comments.contains(";")) {
							StringBuilder sb = new StringBuilder();
							String[] arr = comments.split(";");
							int num = 1;
							for (int z = 0; z < arr.length; z++) {
								if (arr[z].length() > 1) {
									if (arr[z].contains("NOT") || arr[z].contains("Error")) {
										sb.append("<font color=\"red\"><b>" + num + "." + arr[z] + "</b></font><br>");
										num = num + 1;
									} else {
										//sb.append(num + "." + arr[z] + "<br>");
									}

									
								}
							}
							comments = sb.toString();
						}

					}

				}
				
				UpdateDBExcel("./Testcases/ConsumerSite.xlsx", "Update Sheet1 set Status='" + status + "' where Testcases='" + tc + "'");

				if (status.equalsIgnoreCase("failed")) {
					x=x+1;
					failedTask.append("</br>"+x+"."+ut+" "+sc);
					status = "<font color=\"red\"><b>Failed</b></font>";
					values.add(new Data(x, ut, sc, status, comments));
				}

			}
		

			List<String> finalData = selectDBExcel_ColumnData("./Testcases/ConsumerSite.xlsx", "Select Testcases from Sheet1 where Status='Not Executed'");

			for (int y = 0; y < finalData.size(); y++) {

				try {
					x = x + 1;					
					String ut = "";
					String sc = "";
					ut = finalData.get(y).split(":")[0];
					sc = finalData.get(y).split(":")[1];
					failedTask.append("</br>"+x+"."+ut+" "+sc);
					values.add(new Data(x, ut, sc, "Not Executed", ""));
				} catch (Exception e) {

				}
			}
			
			
			if(x==0){
				values.add(new Data(1, "N/A", "N/A", "N/A", "N/A"));
			}

			context.put("values", values);

			int total = selectDBExcel_RowCount("./Testcases/ConsumerSite.xlsx", "select count(*) from Sheet1");
			int passed = selectDBExcel_RowCount("./Testcases/ConsumerSite.xlsx", "select count(*) from Sheet1 where Status='Passed'");
			int failed = selectDBExcel_RowCount("./Testcases/ConsumerSite.xlsx", "select count(*) from Sheet1 where Status='Failed'");
			int noExecution = selectDBExcel_RowCount("./Testcases/ConsumerSite.xlsx", "select count(*) from Sheet1 where Status='Not Executed'");

			String st;
			if((passed == total) && (failed==0) && (noExecution==0)){
				st="PASS";
			}else{
				st="FAILED";
			}
			
			
			context.put("totalTasks", total);
			context.put("passed", passed);
			context.put("failed", failed);
			context.put("noRun", noExecution);
			context.put("release", rel);
		
			String ft=failedTask.toString();
			if(ft.length()==0){
				ft="N/A";
			}
			
			writeToTestRunResults(st, env, portal,ft);
			
			
			Date date = new Date();
		    String DATE_FORMAT = "MM/dd/yyyy";
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		 		
			context.put("date",  sdf.format(date));
			/* now render the template into a StringWriter */
			StringWriter out = new StringWriter();
			t.merge(context, out);

			message.setContent(out.toString(), "text/html");
			
			String per = "PASS:0%|FAIL:0%";
			try{
				int passPer=getPercentage(passed, failed,noExecution);
				int failPer=getPercentage(failed, passed,noExecution);				
				
				if(passPer>0 ||failPer>0){
					per="PASS:"+passPer+"%|FAIL:"+failPer+"%";
				}	
			}catch(Exception e){
				
			}			
			

			message.setSubject("GIT|"+ env.toUpperCase() + "|" + rel + "|" + per + "|" +portal);
			//message.setSubject(env + "-"+rel+"  Consumer Site Automation Result");

			if (sendEmail.equalsIgnoreCase("YES")) {
				Transport.send(message);
				System.out.println("Mail sent Successfully with report");
			}
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static int getPercentage(int a, int b,int c) {
	    float proportion = ((float) a) / ((float) (a+b+c));
	    return (int)(proportion * 100);
	}


	public static void updatemks() {
		if (MKS_Update.equalsIgnoreCase("Yes")) {
			try {

				String theFirst = "C:\\Lenders\\Test\\temp.ps1 ";

				if (environment.equalsIgnoreCase("DR")) {
					theFirst = "C:\\DR\\Lenders\\Test\\temp.ps1 ";
				}

				String theUname = MKS_UserName;
				String theUPass = MKS_Password;

				String reportLenderm = getMKSLenderName(lenderValue);

				String theSecond = reportLenderm;
				// String first = "\"" + theFirst + "\"";
				// String second = "\"" + theSecond + "\"";
				String thethird = "NIT - Functional";
				// String third = "\"" + thethird + "\"";
				String theFourth = reportPath.replace("\\\\", "\\");
				// String fourth = "\"" + theFourth + "\"";
				String theFith = reportLendermks;
				// String fifth = "\"" + theFith + "\"";
				String thesix = "100";
				// String six = "\"" + thesix + "\"";
				String theSeven = "NIT - Functional Validation Execution completed, please refer results attached for more details";
				// String seven = "\"" + theSeven + "\\\"";
				String theEight = MKS_Query;
				// String eight = "\"" + theEight + "\\\"";

				String param = theFirst + " -MKSUser '" + MKS_UserName + "' -MKSPass '" + MKS_Password + "' -lendername '" + theSecond + "' -testSuite '" + thethird + "' -FilePath '" + theFourth + "'  -Filename '" + theFith + "'  -testPercent '" + thesix + "' -comments '" + theSeven + "' -query '" + theEight + "'";

				System.out.println(param);
				String command = "powershell.exe " + param;
				System.out.println(command);

				Process powerShellProcess = Runtime.getRuntime().exec(command);

				BufferedReader stdInput = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
				String line;
				System.out.println("Output :");
				Thread.sleep(10000);
				while ((line = stdInput.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getMKSLenderName(String lenderValue) {

		String mkslendername = "";

		switch (lenderValue) {

		case "1030":
			mkslendername = "BAC";
			break;

		case "1088":
			mkslendername = "BAC_SH2";
			break;

		case "1079":
			mkslendername = "BAC_Short_Sales";
			break;

		case "1255":
			/*
			 * String theSeven = "\"Capital One"; mkslendername = "\\" +
			 * theSeven + "\\\"";
			 */
			mkslendername = "Capital One";
			break;

		case "1071":
			mkslendername = "Carrington";
			break;

		case "1198":
			mkslendername = "EQ_HAFA";
			break;

		case "1000076":
			mkslendername = "EQ_HAFA_PRO";
			break;

		case "1000067":
			mkslendername = "EQ_REO_PRO";
			break;

		case "1078":
			mkslendername = "EQ_Short_Sales";
			break;

		case "1000075":
			mkslendername = "EQ_Short_Sales_PRO";
			break;

		case "1092":
			mkslendername = "Ocwen_Deed_in_Lieu";
			break;

		case "1091":
			mkslendername = "Ocwen_Negotiated_Settlement";
			break;

		case "1049":
			mkslendername = "Ocwen";
			break;

		case "1085":
			mkslendername = "Ocwen_Short_Sales";
			break;

		case "1010":
			mkslendername = "HSBC";
			break;

		case "1041":
			mkslendername = "SAX_AssetLink";
			break;
		case "1029":
			mkslendername = "SAX";
			break;

		case "1061":
			mkslendername = "SAX_Special";
			break;

		case "1104":
			mkslendername = "Caliber";
			break;

		case "1617":
			/*
			 * theSeven = "\"Fannie Mae"; mkslendername = "\\" + theSeven +
			 * "\\\"";
			 */
			mkslendername = "Fannie Mae";
			break;

		case "1110":
			mkslendername = "EQ_REO_PRO";
			break;

		case "1597":
			mkslendername = "BAC_Segmentation";
			break;

		case "1191":
			mkslendername = "Fannie Mae Inv";
			break;

		case "1702":
			mkslendername = "Chase_Short_Sales";
			break;

		case "1703":
			mkslendername = "Chase_HAFA";
			break;

		case "1688":
			mkslendername = "BAC_Deed_In_Lieu";
			break;

		case "1718":
			mkslendername = "Homeward_REO";
			break;

		case "1268":
			mkslendername = "EQ_Invoicing";
			break;

		case "1000090":
			mkslendername = "Homeward_HAFA";
			break;

		case "1674":
			mkslendername = "EQ_LoanManagement";
			break;

		case "1692":
			mkslendername = "Carrington_INV";
			break;

		case "1000058":
			mkslendername = "Nationstar_REO";
			break;

		case "1000060":
			mkslendername = "Nationstar_HAFA";
			break;

		case "1000059":
			mkslendername = "Nationstar_SS";
			break;

		case "1000089":
			mkslendername = "Homeward_SS";
			break;

		case "1675":
			mkslendername = "EQ_Foreclosure_Bankruptcy";
			break;

		case "1788":
			mkslendername = "Carrington_SS";
			break;

		case "2088":
			mkslendername = "Ocwen_FCBK";
			break;

		case "2089":
			mkslendername = "ASPS_CAPONE";
			break;

		case "2086":
			mkslendername = "ASPS_SS_Ocwen";
			break;

		case "2099":
			mkslendername = "ASPS_RESI";
			break;

		case "2100":
			mkslendername = "ASPS_Ocwen";
			break;

		case "2113":
			mkslendername = "ASPS_SS_CAPONE";
			break;

		case "1000117":
			mkslendername = "US_Bank_PROSS";
			break;

		case "2111":
			mkslendername = "ASPS_Auction_WF";
			break;

		case "2116":
			mkslendername = "ASPS_Auction";
			break;

		case "1789":
			mkslendername = "Carrington_LoanModification";
			break;

		case "1795":
			mkslendername = "Carrington_Solicitation";
			break;

		case "999999":
			mkslendername = "Vendor Portal";
			break;
		default:
			mkslendername = lenderName;
			break;
		}
		return mkslendername;
	}

	public static boolean checkElementForExist(WebDriver ldriver, By ele, String elementName) {

		ldriver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);

		boolean bflag = ldriver.findElements(ele).size() != 0;

		if (bflag) {
			reporter("INFO", "Element/s named [" + elementName.trim() + "] are present with the property [" + ele + "] in the current Page", "");
			return bflag;
		} else {
			reporter("INFO", "Element/s named [" + elementName.trim() + "] are not present with the property [" + ele + "] in the current Page", "");
			return bflag;
		}
	}

	public static String getElementProperty(WebDriver ldriver, By attr, WebElement elem, String eleName) {
		String addr = null;
		int sz = ldriver.findElements(attr).size();

		for (int i = 0; i < sz; i++) {
			addr = ldriver.findElements(attr).get(i).getText();
			if (StringUtil.isNotBlank(addr)) {
				reporter("INFO", "Got the Element [" + eleName + "]'s Value [" + addr + "]", "Got the Element Value in the page");
				break;
			}
		}

		if (StringUtils.isBlank(addr)) {
			addr = elem.getText();
		}
		return addr;

	}

	public static class ExtentManager {
		private static ExtentReports extent;

		public synchronized static ExtentReports getReporter(String filePath) {
			if (extent == null) {
				// extent.loadConfig(ExtentReports.class, "reportConfig",
				// "reportConfig.xml");
				// extent.loadConfig(ExtentReports.class,
				// "\\src\\test\\java\\Configurations\\reportConfig.xml");
				extent = new ExtentReports(filePath, true);

				extent.addSystemInfo("Host Name", System.getProperty("os.name").toLowerCase()).addSystemInfo("Environment", environment).addSystemInfo("Lender Name ", lenderName).addSystemInfo("Release Number", release);
			}
			return extent;
		}

		public synchronized static ExtentReports getReporterConsumerSite(String filePath) {
			if (extent == null) {
				// extent.loadConfig(ExtentReports.class, "reportConfig",
				// "reportConfig.xml");
				// extent.loadConfig(ExtentReports.class,
				// "\\src\\test\\java\\Configurations\\reportConfig.xml");
				extent = new ExtentReports(filePath, true);

				extent.addSystemInfo("Host Name", System.getProperty("os.name").toLowerCase()).addSystemInfo("Environment", environment);
				;
			}
			return extent;
		}

	}

	public static void checkLoginPage() throws InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.id(("btnLogin"))).click();
			Alert a = driver.switchTo().alert();
			a.accept();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			reporter("FAIL", "Forceful logged out for the URL[Agent Search Page]", "");
			User_Login();

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Select select;
			select = new Select(driver.findElement(By.cssSelector("select[name='change_lender_id']")));
			select.selectByValue(lenderValue); // selectByVisibleText(lenderName);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			// }
			// System.out.println(false);
		} catch (NoSuchElementException e) {
			// System.out.println(true);
			driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		}

	}

	public static void selectDropDownByValue(WebDriver ldriver, By eleme, String valueToSelect) {

		WebElement select = ldriver.findElement(eleme);
		Select dropDown = new Select(select);
		List<WebElement> Options = dropDown.getOptions();

		for (WebElement option : Options) {
			if (option.getText().equalsIgnoreCase(valueToSelect) || option.getAttribute("text").equalsIgnoreCase(valueToSelect)) {
				option.click();
				break;
			}
		}
	}

	public static void selectDropDownByValue(WebElement element, String valueToSelect) {
		WebElement select = element;
		Select dropDown = new Select(select);
		List<WebElement> Options = dropDown.getOptions();

		for (WebElement option : Options) {
			if (option.getText().equalsIgnoreCase(valueToSelect) || option.getAttribute("text").equalsIgnoreCase(valueToSelect)) {
				option.click();
				break;
			}
		}
	}

	public static void rowsValidation_forSearch(String tableId, String serachFieldName, String pageName) {

		try {
			List<WebElement> row = driver.findElements(By.xpath("//*[contains(@id, " + "'" + tableId + "'" + ")]/tbody/tr"));
			if (row.size() >= 1) {
				reporter("PASS", "Search Functionality is working for " + serachFieldName + " field on " + pageName + " page.", "");
			} else {
				reporter("FAIL", "Search Functionality is not working for " + serachFieldName + " field on " + pageName + " page.", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reporter("FAIL", "Filtered Table is not present on " + pageName + " page.", "");
		}

	}

	public static void datepicker(String dateString) {

		try {

			if (dateString.contains("/")) {

				String[] ss = dateString.toString().split("/");

				// Selecting Month
				Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]")));
				dropdown.selectByIndex(Integer.parseInt(ss[0]) - 1);

				// Selecting Year
				Select dropdown1 = new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]")));
				dropdown1.selectByVisibleText(ss[2]);

				// Selecting Date
				ss[1] = ss[1].replaceFirst("^0+(?!$)", "");
				driver.findElement(By.linkText(ss[1])).click();

			} else if (dateString.contains("AM") || dateString.contains("PM")) {

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date dateobj = new Date();
				String date = df.format(dateobj);

				String[] ss = date.toString().split("/");

				// Selecting Month
				ss[0] = ss[0].replaceFirst("^0+(?!$)", "");
				Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]")));
				dropdown.selectByIndex(Integer.parseInt(ss[0]) - 1);

				// Selecting Year
				Select dropdown1 = new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]")));
				dropdown1.selectByVisibleText(ss[2]);

				// Selecting Date
				ss[1] = ss[1].replaceFirst("^0+(?!$)", "");
				driver.findElement(By.linkText(ss[1])).click();

			} else {
				// If Date string got is in format e.g. Mar 1
				String[] ss = dateString.toString().split(" ");

				// Selecting Month
				Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]")));
				dropdown.selectByVisibleText(ss[0]);

				// Selecting Date
				driver.findElement(By.linkText(ss[1])).click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sortFunctionCheck(String tableId, int columnNo, String restXpath, String serachFieldName, String pageName) {

		try {

			driver.findElement(By.xpath("//*[contains(@id, " + "'" + tableId + "'" + ")]/thead/tr/th[" + columnNo + "]")).click();
			sortCheck(tableId, restXpath, serachFieldName, pageName, columnNo);
		} catch (Exception e) {
			reporter("FAIL", "Element Not present with TableID " + tableId, "");
		}
	}

	public static void sortCheck(String tableId, String path, String serachFieldName, String pageName, int col) {

		try {
			List<WebElement> row = driver.findElements(By.xpath("//*[contains(@id, " + "'" + tableId + "'" + ")]/tbody/tr"));
			int rows = row.size();
			// System.out.println("No. of Rows: "+rows);
			List<String> l1 = new ArrayList<String>();
			for (int i = 1; i <= rows / 2; i++) {
				String val = "";
				val = driver.findElement(By.xpath("//*[contains(@id, " + "'" + "header" + i + "'" + ")]/td[" + col + "]" + path)).getText();
				l1.add(val);
			}

			boolean sorted = Ordering.natural().isOrdered(l1);
			// if(isSorted(l1)){
			if (sorted) {
				reporter("PASS", "Sort Functionality is working for " + serachFieldName + " field on " + pageName + " page.", "");
			} else {
				reporter("FAIL", "Sort Functionality is not working for " + serachFieldName + " field on " + pageName + " page.", "");
			}
		} catch (Exception e) {
			reporter("FAIL", "Element not present for TableId " + tableId + " on " + pageName + " page.", "");
		}

	}

	public static boolean isSorted(List<String> list) {
		String previous = "";
		for (String current : list) {
			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}
		return true;
	}

	public static String removedots(String text) {
		if (text.endsWith("..")) {
			text = text.replaceAll("[\\.]+$", "");
			return text;
		} else {
			return text;
		}
	}

	public static void DeleteTempFiles() throws IOException {
		try {
			File file = new File(System.getProperty("java.io.tmpdir"));
			FileUtils.cleanDirectory(file);

		} catch (Exception e) {

		}
	}

	public static void tearDownProcess(Exception e) {
		try {
			generateAndSendEmailConsumerSite();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("");
		String s = Throwables.getStackTraceAsString(e);
		reporter("INFO", "Got an exception unexpected." + s, "");
	}

	public static void waitForPageLoad(WebDriver wdriver) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(wdriver, 5, 1000);
		try {
			wait.until(AdditionalConditions.angularHasFinishedProcessing());
		} catch (Exception e) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			wait.until(AdditionalConditions.pageLoadFinished());
		} catch (Exception ee) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Thread.sleep(2000);

	}

	public static String[] returnDropDownOptions(WebElement select) {

		Select dropDown = new Select(select);
		List<WebElement> Options = dropDown.getOptions();
		String[] st = new String[Options.size()];
		int i = 0;
		for (WebElement option : Options) {
			st[i] = option.getText();
			i++;
		}
		return st;
	}

	public static void fluentWait(WebDriver driver, final By by) {

		new FluentWait<WebDriver>(driver).withTimeout(180, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class).until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver wd) {
				try {
					wd.findElement(by);
					return true;
				} catch (Exception t) {
					return false;
				}
			}
		});
	};

	public static void safeClick(WebDriver driver, WebElement ele) {
		boolean element = elementVisible(driver, ele, 10);

		if (element) {

			ele.click();

		} else {

			System.out.println("Element is not Visible to Click ");

		}
	}

	public static void safeClickBy(WebDriver driver, By by) {
		boolean element = elementVisibleBy(driver, by, 10);

		WebElement we = driver.findElement(by);
		if (element) {

			we.click();

		} else {

			System.out.println("Element is not Visible to Click:" + by);

		}
	}

	public static boolean elementVisible(WebDriver driver, WebElement ele, int Time) {
		boolean element_withDelay = false;

		int second = 0;
		for (second = 0; second < Time; second++) {
			try {

				if (ele.isDisplayed() == true) {
					element_withDelay = true;
					break;
				}
			} catch (Exception ignore) {
				System.out.println("Element not displayed" + ele);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return element_withDelay;
	}

	public static boolean elementVisibleBy(WebDriver driver, By by, int Time) {
		boolean element_withDelay = false;

		int second = 0;
		for (second = 0; second < Time; second++) {
			try {

				if (driver.findElement(by).isDisplayed() == true) {
					element_withDelay = true;
					break;
				}
			} catch (Exception ignore) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return element_withDelay;
	}

	public static boolean elementPresent(WebDriver driver, By by, int Time) {
		boolean element_withDelay = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				WebElement we = driver.findElement(by);
				if (we != null) {
					element_withDelay = true;
					return element_withDelay;
				}
			} catch (NoSuchElementException e) {
				if (driver.getTitle().trim().toLowerCase().contains("error")) {

				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return element_withDelay;
	}

	public static void safeType(WebDriver driver, WebElement ele, String text) {
		boolean element = elementVisible(driver, ele, 10);
		if (element) {
			ele.clear();
			ele.sendKeys(text);
			System.out.println("Entered Text:[" + text + "] successfully");
		} else {
			System.out.println("Element is not visible to Enter Text:[" + text + "]");

		}
	}

	public static void safeTypeBy(WebDriver driver, By by, String text) {
		boolean element = elementVisibleBy(driver, by, 10);
		if (element) {

			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(text);
			System.out.println("Entered Text:[" + text + "] successfully");
		} else {
			System.out.println("Element is not visible to Enter Text:[" + text + "]");

		}
	}

	public static void safeSelect(WebDriver driver, By by, String value) {
		if (elementPresent(driver, by, 10)) {
			Select select = new Select(driver.findElement(by));
			select.selectByValue(value);
			System.out.println("Selected value [" + value + "]");

		} else {
			System.out.println("Unable to Select value [" + value + "]");
		}
	}

	public static void safeSelectByText(WebDriver driver, By by, String value) {
		if (elementPresent(driver, by, 10)) {
			Select select = new Select(driver.findElement(by));
			select.selectByVisibleText(value);
			System.out.println("Selected Text [" + value + "]");
		} else {
			System.out.println("Unable to Select text [" + value + "]");

		}
	}

	public static void safeSelectByIndex(WebDriver driver, By by, int value) {
		if (elementPresent(driver, by, 10)) {
			Select select = new Select(driver.findElement(by));
			select.selectByIndex(value);
			System.out.println("Selected item [" + value + "]");
		} else {
			System.out.println("Unable to Select text [" + value + "]");

		}
	}

	public static boolean elementNotVisible(WebDriver driver, By by, int Time) {
		boolean element_withDelay = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				if (!driver.findElement(by).isDisplayed()) {
					element_withDelay = true;
					break;
				}
			} catch (Exception ignore) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return element_withDelay;
	}

	public static boolean textPresent(WebDriver driver, String text, int Time) {
		boolean textFlag = false;
		int second = 0;
		for (second = 0; second < Time; second++) {
			try {
				if ((driver.getPageSource().contains(text))) {
					textFlag = true;
					break;
				}
			} catch (Exception ignore) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return textFlag;
	}

	public static String safeGetText(WebDriver driver, WebElement ele) {
		boolean element = elementVisible(driver, ele, 10);
		if (element) {
			return ele.getText().trim();
		} else {

		}
		return null;
	}

	public static String safeGetTextBy(WebDriver driver, By by) throws Exception {
		boolean element = elementVisibleBy(driver, by, 10);
		if (element) {
			return driver.findElement(by).getText().trim();
		} else {

		}
		return null;
	}

	public static void UpdateDBExcel(String path, String query) {

		Fillo fillo = new Fillo();
		Connection connection = null;
		try {
			connection = fillo.getConnection(path);
			connection.executeUpdate(query);
		} catch (Exception e) {

			// e.printStackTrace();
		}

		connection.close();
	}

	public static int selectDBExcel_RowCount(String path, String query) {

		Fillo fillo = new Fillo();
		Connection connection = null;
		int count = 0;
		try {
			connection = fillo.getConnection(path);
			Recordset result = connection.executeQuery(query);

			count = result.getCount();

		} catch (Exception e) {

			// e.printStackTrace();
		}

		connection.close();
		return count;
	}

	public static List<String> selectDBExcel_ColumnData(String path, String query) {

		Fillo fillo = new Fillo();
		Connection connection = null;
		List<String> list = new ArrayList<String>();

		try {
			connection = fillo.getConnection(path);
			Recordset result = connection.executeQuery(query);

			while (result.next()) {
				list.add(result.getField(result.getField(0).name()).toString());
			}

		} catch (Exception e) {

			// e.printStackTrace();
		}

		connection.close();
		return list;
	}
	
	public static String getReleaseNumber(String env){

		
		Date date = new Date();
	    String DATE_FORMAT = "MM/dd/yyyy";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    String tmp = sdf.format(date);
	    
		String sReleaseCalenderPath=System.getProperty("user.dir")+"\\config\\ReleaseCalender.xlsx";
		String Release_Number=null;
	
		try{
			
			List<String> rel = selectDBExcel_ColumnData(sReleaseCalenderPath, "SELECT "+env.toUpperCase().trim()+" FROM Calendar WHERE DATE='"+tmp+"'");
			Release_Number=rel.get(0);
		
		}catch(Exception e){
		}
		System.out.println("release Number from Release calendar : "+Release_Number);
		return Release_Number;
	}
	
	
	

	public static String generateDate() {
		DateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date();
		return sdf.format(date);
	}
	
	
	public static void writeToTestRunResults(String finalTestStatus, String env, String lender, String failedTask) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File file = new File("." + "\\results\\" + env.toUpperCase() + "_" + lender.toUpperCase() + ".txt");
			file.delete();
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(generateDate() + ";" + finalTestStatus + ";" + failedTask);
			bw.newLine();
			bw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> readTestRunResults(String lender, String env) {

		Map<String, String> map = new HashMap<String, String>();

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader("." + "\\results\\" + env.toUpperCase() + "_" + lender.toUpperCase() + ".txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] tmp = sCurrentLine.split(";");
				map.put("lastRunDate", tmp[0]);
				map.put("lastRunStatus", tmp[1]);
				map.put("lastFailedTask", tmp[2]);

			}
		} catch (IOException e) {

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

			}
		}

		return map;

	}


}
