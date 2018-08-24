package com.site.pageRepo;

import static com.site.tests.Utilities.CommonFunctions.*;
import static com.site.tests.testcases.baseTest.defaultTimeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.site.tests.Utilities.LoadConfigFile;
import com.site.tests.testcases.ConsumerSite;
import com.site.tests.testcases.ConsumerSite_Baseclass;

public class ConsumerSite_LandingPage {

	public ConsumerSite_LandingPage(WebDriver ldriver) {
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(id = "searchStringDesktop")
	public WebElement txtSearchBox;

	@FindBy(id = "txtSearchName")
	public WebElement txtSearchName;

	@FindBy(id = "btnSaveSearch")
	public WebElement btnSaveSearch2;

	@FindBy(xpath = "//span[text()='More Options']")
	public WebElement linkMoreOptions;

	@FindBy(xpath = "//div[@id='refinePriceRange']/button")
	public WebElement ddPriceRange;

	@FindBy(xpath = "//button[text()='Save Search']")
	public WebElement btnSaveSearch;

	@FindBy(xpath = "//div[@id='refineSqftRange']/button")
	public WebElement ddSqftRange;

	@FindBy(id = "price-range")
	public WebElement linkPriceRange;

	@FindBy(xpath = ".//*[@id='viewletSearchBox']/div[2]/div/div/div[1]/button")
	public WebElement btnSearch;

	@FindBy(xpath = "//div[@class='eqarousel_image_wrapper']/a")
	public WebElement carousel;

	@FindBy(xpath = "//span[@class='tt-suggestions']/div/p")
	public List<WebElement> labelAutoSuggestionText;

	@FindBy(xpath = "//div[@class='eqarousel_image_wrapper']/a")
	public WebElement linkCarousel;

	@FindBy(xpath = "//div[@id='fpcarousel']")
	public WebElement carouselContainer;

	@FindBy(className = "jcarousel-control-prev")
	public WebElement carouselPrevious;

	@FindBy(className = "jcarousel-control-next")
	public WebElement carouselNext;

	@FindBy(xpath = "//*[text()='Solutions']")
	public WebElement linkSolution;

	@FindBy(xpath = "//*[text()='Company']")
	public WebElement linkCompany;

	@FindBy(xpath = "//a[text()='About']")
	public WebElement linkAbout;

	@FindBy(xpath = "//a[text()='Leadership']")
	public WebElement linkLeaderShip;

	@FindBy(xpath = "//a[text()='Contact']")
	public WebElement linkContact;

	@FindBy(xpath = "//a[text()='Careers']")
	public WebElement linkCareers;

	@FindBy(xpath = "//ul[@class='navSecondary']/li/a[text()='Servicers']")
	public WebElement linkServicers;

	@FindBy(xpath = "//ul[@class='navSecondary']/li/a[text()='Agents']")
	public WebElement linkAgents;

	@FindBy(xpath = "//ul[@class='navSecondary']/li/a[text()='Vendors']")
	public WebElement linkVendors;

	@FindBy(xpath = "//ul[@class='navSecondary']/li/a[text()='Consumers']")
	public WebElement linkConsumers;

	@FindBy(xpath = "//div[@id='infoTop25Markets']/h3[text()='Homes for Sale in Your City']")
	public WebElement LabelTop25Markets;

	@FindBy(xpath = "//div[@class='ddWrap top25MarketsCls']//div/a")
	public List<WebElement> linkTotalCities;

	@FindBy(xpath = "//div[@for='searchStringDesktop' and @style='display: block;']")
	public WebElement labelErrorMessage;

	@FindBy(xpath = "(//a[@class='dropdown-toggle'])[1]")
	public WebElement linkUserSignout;

	@FindBy(xpath = "(//a[@class='personLogout'])[1]")
	public WebElement btnSignout;

	@FindBy(name = "minBedrooms")
	public WebElement ddBed;

	@FindBy(name = "minBathrooms")
	public WebElement ddBath;

	public void signout() {

		safeClick(driver, linkUserSignout);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		safeClick(driver, btnSignout);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyTop25MarketCity(String url) throws Exception {

		driver.get(url);
		waitForPageLoad(driver);
		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		// Scenario 1: Verifying Header \"Homes for Sale in Your City\" is
		// available in the Home Page

		try {

			if (LabelTop25Markets.isDisplayed()) {

				data.append("Header <b>\"Homes for Sale in Your City\"</b> is available in the Home Page.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Header \"Homes for Sale in Your City\" is available in the Home Page.", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Header \"Homes for Sale in Your City\" is available in the Home Page.");

			} else {

				dflag = false;
				data.append("Header \"Homes for Sale in Your City\" is ---NOT---displayed in Home page.;");

				reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Header \"Homes for Sale in Your City\" is ---NOT---displayed in Home page.", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Header \"Homes for Sale in Your City\" is ---NOT---displayed in Home page.");

			}

		} catch (Exception e) {
			dflag = false;
			data.append("Header \"Homes for Sale in Your City\" is ---NOT---displayed in Home page.;");

			reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Header \"Homes for Sale in Your City\" is ---NOT---displayed in Home page.", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Header \"Homes for Sale in Your City\" is ---NOT---displayed in Home page.");
		}

		// Scenario 2: Verifying top 25 cities link in under Homes for Sale in
		// Your City section
		try {

			int noOfCities = linkTotalCities.size();

			if (noOfCities == 25) {

				data.append("Total no of cities dsiplayed under Header \"Homes for Sale in Your City\":<b>" + 25 + "</b>.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Total no of cities dsiplayed under Header \"Homes for Sale in Your City\":" + 25, ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Total no of cities dsiplayed under Header \"Homes for Sale in Your City\":" + 25);

			} else {

				dflag = false;
				data.append("Total No of Cities displayed is ---NOT--- equal to <b>" + 25 + "</b>.;");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + "Total No of Cities displayed is ---NOT--- equal to " + 25 + ".;", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + "Total No of Cities displayed is ---NOT--- equal to " + 25 + ".;");
			}

		} catch (Exception e) {

		}

		// Scenario 3 verify city link functionality under Homes for Sale in
		// Your City section
		try {
			String city = driver.findElement(By.xpath("(//div[@class='ddWrap top25MarketsCls']//div/a)[1]")).getText().split(",")[0];
			// Creating the JavascriptExecutor interface object by Type casting
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,600)");

			safeClickBy(driver, By.xpath("(//div[@class='ddWrap top25MarketsCls']//div/a)[1]"));
			waitForPageLoad(driver);
			String exp = safeGetTextBy(driver, By.xpath(".//*[@id='resultContent']/div/div/div[1]/h1"));
			if (exp.contains(city) && exp.contains("Homes For Sale")) {

				data.append("Clicked on city link present in Homes for Sale in Your City section and it navigaed to Result page as expected.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Clicked on city link present in Homes for Sale in Your City section and it navigaed to Result page as expected.", "");

			} else {
				dflag = false;
				data.append("City Link under Homes for Sale in Your City section is ---NOT--- working as expected.;");

				reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " City Link under Homes for Sale in Your City section is ---NOT--- working as expected.", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " City Link under Homes for Sale in Your City section is ---NOT--- working as expected.");
			}
		} catch (Exception e) {

		}

		driver.get(url);
		waitForPageLoad(driver);

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Homes for Sale in Your City Functionality*" + status + "*" + data);

	}

	public void verifyCarousel(String url) throws Exception {

		driver.get(url);
		waitForPageLoad(driver);
		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scenario 1: Verifying Carousel section is available or not.

		try {

			if (carouselContainer.isDisplayed()) {

				data.append("Carousel is displayed in Home page.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel Section is available in Customer site Landing page", "");

			}

		} catch (Exception e) {
			dflag = false;
			data.append("Carousel is ---NOT---displayed in Home page.;");

			reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel Section is ****NOT Available****** in Customer site Landing page", "");
		}

		// Scenario 2: Verifying carousel WebLink is navigating to corresponding
		// target url or not
		try {
			String carouselURL = linkCarousel.getAttribute("href");
			js.executeScript("arguments[0].click();", linkCarousel);
			waitForPageLoad(driver);
			String carouselTargetURL = driver.getCurrentUrl();

			if (carouselTargetURL.contains(carouselURL)) {

				data.append("On clicking Carousel img, it navigated to correct URL: " + carouselTargetURL + ".;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel:WebLink worked as expected", "");

			} else {

				dflag = false;
				data.append("On clicking Carousel img, it is ---NOT--- to correct URL: " + carouselTargetURL + ".;");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel:WebLink ***NOT Working*** as expected", "");
			}

		} catch (Exception e) {

		}

		driver.get(url);
		waitForPageLoad(driver);

		// Scenario 3 verify carousel previous button functionality
		try {
			String valueBeforeClick = driver.findElement(By.xpath(".//*[@id='jcarousel']/ul/li[1]/div[2]/div/b")).getText();

			js.executeScript("arguments[0].click();", carouselPrevious);
			Thread.sleep(3000);

			String valueAfterClick = driver.findElement(By.xpath(".//*[@id='jcarousel']/ul/li[2]/div[2]/div/b")).getText();

			if (valueBeforeClick.equals(valueAfterClick)) {

				data.append("Carousel Previous Link is working as expected.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel:Previous Control worked as expected", "");

			} else {
				dflag = false;
				data.append("Carousel Previous Link is ---NOT--- working as expected.;");

				reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel:Previous Control ***NOT Working*** as expected", "");
			}
		} catch (Exception e) {

		}

		driver.get(url);
		waitForPageLoad(driver);

		// Scenario 4 verify carousel Next button functionality
		try {
			String valueBeforeClick = driver.findElement(By.xpath(".//*[@id='jcarousel']/ul/li[2]/div[2]/div/b")).getText();

			js.executeScript("arguments[0].click();", carouselNext);
			Thread.sleep(3000);

			String valueAfterClick = driver.findElement(By.xpath(".//*[@id='jcarousel']/ul/li[2]/div[2]/div/b")).getText();

			if (valueBeforeClick.equals(valueAfterClick)) {

				data.append("Carousel Next Link is working as expected.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel:Next Control worked as expected", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Carousel:Next Control worked as expected");

			} else {

				dflag = false;
				data.append("Carousel Next Link is ---NOT--- working as expected.;");

				reporter("FAIL", ConsumerSite_Baseclass.csUserType + " " + ConsumerSite_Baseclass.csBrowserType + " Carousel:Next Control ***NOT Working*** as expected", "");
			}
		} catch (Exception e) {

		}

		driver.get(url);
		waitForPageLoad(driver);

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Carousel Functionality*" + status + "*" + data);

	}

	public void verifyCitySearch(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");
		String cityCode = LoadConfigFile.getInstance().getValue("Consumer_City_Code");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			txtSearchBox.sendKeys(txtToBeSearched);
			Thread.sleep(1000);
			txtSearchBox.sendKeys(" ");
			Thread.sleep(1000);
			safeClick(driver, btnSearch);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);

			data.append("Test Data:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toLowerCase().contains(txtToBeSearched.toLowerCase() + " homes for sale")) {

				data.append("Navigated to " + txtToBeSearched + " Property Search results page successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is working as expected", "");

			} else {
				dflag = false;
				data.append("---NOT--- Navigated to " + txtToBeSearched + " Property Search results page .;");

				reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
			}

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				String proCount;

				ConsumerSite.address = driver.findElement(By.xpath("(//p[@class='propertyAddress'])[1]")).getText();

				ConsumerSite.zipcode = driver.findElement(By.xpath("(//p[@class='propertyCSZ'])[1]")).getText();
				ConsumerSite.zipcode = ConsumerSite.zipcode.split(",")[1].trim().split(" ")[1];
				proCount = record.split("-")[1].trim();

				List<WebElement> city = driver.findElements(By.xpath("//p[@class='propertyCSZ']"));
				ArrayList<String> list = new ArrayList<String>();

				for (WebElement web : city) {
					list.add(web.getText().split(",")[1].trim().split(" ")[0]);
				}

				Set<String> set = new HashSet<String>(list);

				if (set.size() == 1) {
					data.append("Verified Property Search results page displayed property belongs to the City code: <b>" + cityCode + "</b>.;");
				} else {
					dflag = false;
					List<String> tmp = new ArrayList<String>(set);
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < tmp.size(); i++) {
						sb.append(tmp.get(i));
						sb.append(" ");
					}

					data.append("---NOT Expected behaviour.---Property Search results displayed property from the following City codes:<b>[" + sb.toString() + "]</b>.;");
					reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " ---NOT Expected behaviour.---Property Search results displayed property from the following City codes:[" + sb.toString() + "]", "");

				}

				data.append("---INFO---Available Properties:<b>" + proCount + "</b>");

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via City*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via City*Failed*Test Data:" + txtToBeSearched);
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifyZipCodeSearch(String url) throws Exception {
		String txtToBeSearched;

		try {

			if (ConsumerSite.zipcode.equals("") || ConsumerSite.zipcode.isEmpty()) {
				txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_Zip");
			} else {
				txtToBeSearched = ConsumerSite.zipcode;
			}

		} catch (Exception e) {
			txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_Zip");
		}

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);

			safeType(driver, txtSearchBox, txtToBeSearched);
			safeClick(driver, btnSearch);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("Test Data:<b>" + txtToBeSearched + "</b>.;");

			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.contains("ZIP Code " + txtToBeSearched + " Homes For Sale")) {

					data.append("Navigated to Zip code <b>" + txtToBeSearched + "</b> property Search results page successfully.;");
					reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Zipcode search " + txtToBeSearched + " in Consumer Site is working as expected", "");

				} else {
					dflag = false;
					data.append("---NOT--- Navigated to ZipCode " + txtToBeSearched + " Property Search results page .;");

					reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Zipcode search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
				}

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					String proCount;

					ConsumerSite.zipcode = driver.findElement(By.xpath("(//p[@class='propertyCSZ'])[1]")).getText();
					ConsumerSite.zipcode = ConsumerSite.zipcode.split(",")[1].trim().split(" ")[1];
					proCount = record.split("-")[1].trim();

					List<WebElement> city = driver.findElements(By.xpath("//p[@class='propertyCSZ']"));
					ArrayList<String> list = new ArrayList<String>();

					for (WebElement web : city) {
						list.add(web.getText().split(",")[1].trim().split(" ")[1]);
					}

					Set<String> set = new HashSet<String>(list);

					if (set.size() == 1) {
						data.append("Verified Property Search results page displayed property belongs to the Zipcode: <b>" + txtToBeSearched + "</b>.;");
					} else {
						dflag = false;
						List<String> tmp = new ArrayList<String>(set);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < tmp.size(); i++) {
							sb.append(tmp.get(i));
							sb.append(" ");
						}

						data.append("---NOT Expected behaviour.---Property Search results displayed property from the following Zipcodes:<b>[" + sb.toString() + "]</b>.;");
						reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " ---NOT Expected behaviour.---Property Search results displayed property from the following Zipcodes:<b>[" + sb.toString() + "]</b>.", "");
					}

					data.append("---INFO---Available Properties:<b>" + proCount + "</b>");

				}
			} else {
				dflag = false;
				data.append("Property is ---NOT--- displayed for the Zipcode:<b>" + txtToBeSearched + "</b>");

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via ZipCode*" + status + "*" + data);

		} catch (Exception e) {

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via ZipCode*Failed**Test Data:" + txtToBeSearched);
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Zipcode search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
		}

	}

	public void verifyAddressSearch(String url) throws Exception {

		String txtToBeSearched;

		try {

			if (ConsumerSite.address.equals("") || ConsumerSite.address.isEmpty()) {
				txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_Address");
			} else {
				txtToBeSearched = ConsumerSite.address;
			}
		} catch (Exception e) {
			txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_Address");
		}

		try {

			driver.get(url);
			waitForPageLoad(driver);
			safeType(driver, txtSearchBox, txtToBeSearched);
			safeClick(driver, btnSearch);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);

			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {

				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toLowerCase().contains(txtToBeSearched.toLowerCase()) && record.contains("Homes For Sale")) {

					if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

						String proCount = record.split("-")[1].trim();
						ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via Address*Passed*1.Test Data:<b>" + txtToBeSearched + "</b>.<br>2.Navigated to Address " + txtToBeSearched + " Property Search results page successfully.<br>3.---INFO--- Available Properties for Address " + txtToBeSearched + " search:<b>" + proCount + "</b>.");

					} else {
						ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via Address*Passed*1.Test Data:" + txtToBeSearched + ".<br>2.Navigated to Address " + txtToBeSearched + " Property Search results page successfully.");

					}

					reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Address search " + txtToBeSearched + " in Consumer Site is working as expected", "");

				} else {
					ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via Address*Failed*Test Data:<b>" + txtToBeSearched + "</b>");
					reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Address search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
				}
			} else {

				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via Address*Failed*1.Test Data:<b>" + txtToBeSearched + "</b>.<br><font color=\"red\"><b>2. Property is ---NOT--- displayed for the address:" + txtToBeSearched + "</b></font>");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Address search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
			}

		} catch (Exception e) {

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via Address*Failed*1.Test Data:<b>" + txtToBeSearched + "</b>.<br><font color=\"red\"><b>2. Property is ---NOT--- displayed for the address:" + txtToBeSearched + "</b></font>");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Address search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
		}

	}

	public void verifyAutoSuggestTextSearch(String url) throws Exception {
		String txtToBeSearched = "Ne";
		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);

			safeType(driver, txtSearchBox, txtToBeSearched);

			Thread.sleep(2000);

			data.append("Text Data used for AutoSuggestion: <b>" + txtToBeSearched + "</b>.;");
			ArrayList<String> list = new ArrayList<String>();
			for (WebElement web : labelAutoSuggestionText) {

				if (web.getText().substring(0, 2).toLowerCase().equals("ne")) {
					list.add(web.getText());

				} else {
					dflag = false;
					data.append("Text captured on Autosuggestion: " + list + ".;");
					data.append("Autosuggestion does not start with " + txtToBeSearched + " for the city: " + web.getText() + ".;");
					reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Autosuggestion does not start with " + txtToBeSearched + " for the city: " + web.getText() + ".", "");
				}

			}

			if (dflag) {

				data.append("Text captured on Autosuggestion: <b>" + list + "</b>.;");
				data.append("All the text in Autosuggestion start with: <b>" + txtToBeSearched + "</b>.;");

			}

			for (int i = 1; i <= 1; i++) {

				safeType(driver, txtSearchBox, txtToBeSearched);

				Thread.sleep(2000);
				safeClickBy(driver, By.xpath("//p[text()='" + list.get(i) + "']"));

				waitForPageLoad(driver);
				String tmp = null;
				try {
					tmp = list.get(i).split(",")[0].trim();
				} catch (Exception e) {
					tmp = list.get(i);
				}

				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header') or contains(@class,'searchNoPropDetails')]/h1"));
				if (record.toLowerCase().contains("homes for sale") && record.contains(tmp)) {

					data.append("Selected option <b>" + tmp + "</b> in the autosuggestion and navigated to Property search results page of <b>" + tmp + "</b> successfully.;");
					reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " AutoSuggestion Text search " + list.get(i) + " in Consumer Site is working as expected", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " AutoSuggestion Text search " + list.get(i) + " in Consumer Site is working as expected");

				} else {

					data.append("Selected city " + tmp + " displayed in the autosuggestion and --NOT--navigated to Property search results page of " + tmp + " as expected.;");
					reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " AutoSuggestion Text search " + list.get(i) + " in Consumer Site is ***NOT Working*** as expected", "");
				}

				driver.get(url);
				waitForPageLoad(driver);

				String status;
				if (dflag) {
					status = "Passed";
				} else {
					status = "Failed";
				}

				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via AutoSuggestion*" + status + "*" + data);

			}

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search via AutoSuggestion*Failed*Test Data:" + txtToBeSearched);
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened during Property Search via AutoSuggestion ", "");

		}

	}

	public void verifyCompany(String url) throws Exception {

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		String actualURL = null;
		String expectedURL = null;

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/company/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/company/about/";
		}

		try {

			safeClick(driver, linkCompany);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.equalsIgnoreCase(expectedURL)) {

				data.append("On clicking Company Link, Navigated to URL <b>" + actualURL + "</b> successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Company Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Company Link. Navigated to:" + actualURL);

				if (driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim().equals("A Passion for Success")) {
					data.append("Verified Page Title in About Link:<b>A Passion for Success</b>.;");
				} else {

					dflag = false;
					data.append("Expected Page Title in About link:<b>A Passion for Success</b>. Actual Page Title: <b>" + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + "</b>.;");
					reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Expected Page Title in About link:A Passion for Success. Actual Page Title: " + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + ".", "");

				}

			} else {

				dflag = false;
				data.append("On clicking Company Link,Navigated to URL " + actualURL + ". Expected URL " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Company Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Company Link. Navigated to:" + actualURL);

			}

			String source = driver.getPageSource();

			if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
				dflag = false;
				data.append(actualURL + " contains Error in the page.;");

			}

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + actualURL + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		// LeaderShip url:

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/company/leadership/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/company/leadership/";
		}

		try {

			safeClick(driver, linkLeaderShip);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.equalsIgnoreCase(expectedURL)) {

				data.append("On clicking Company Leadership Link, Navigated to URL <b>" + actualURL + "</b> successfully.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Ledearship Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Leadership Link. Navigated to:" + actualURL);

				if (driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim().equals("Leadership Team")) {
					data.append("Verified Page Title in Leadership Link:<b>Leadership Team</b>.;");
				} else {

					dflag = false;
					data.append("Expected Page Title in Leadership link:<b>Leadership Team</b>. Actual Page Title: <b>" + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + "</b>.;");
					reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Expected Page Title in Leadership link:Leadership Team. Actual Page Title: " + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + ".", "");

				}

			} else {

				dflag = false;
				data.append("On clicking Leadership Link,Navigated to URL " + actualURL + ". Expected URL " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Leadership Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Leadership Link. Navigated to:" + actualURL);

			}

			String source = driver.getPageSource();

			if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
				dflag = false;
				data.append(actualURL + " contains Error in the page.;");

			}

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + actualURL + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		// Contact url:

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/company/contact/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/company/contact/";
		}

		try {

			safeClick(driver, linkContact);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.equalsIgnoreCase(expectedURL)) {

				data.append("On clicking Company Contact Link, Navigated to URL <b>" + actualURL + "</b> successfully.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Contact Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Leadership Link. Navigated to:" + actualURL);

				if (driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim().equals("Customer Support")) {
					data.append("Verified Page Title in Contact Link:<b>Customer Support</b>.;");
				} else {

					dflag = false;
					data.append("Expected Page Title in Contact link:<b>Customer Support</b>. Actual Page Title: <b>" + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + "</b>.;");
					reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Expected Page Title in Contact link:Customer Support. Actual Page Title: " + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + ".", "");

				}

			} else {

				dflag = false;
				data.append("On clicking Contact Link,Navigated to URL " + actualURL + ". Expected URL " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Contact Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Leadership Link. Navigated to:" + actualURL);

			}

			String source = driver.getPageSource();

			if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
				dflag = false;
				data.append(actualURL + " contains Error in the page.;");

			}

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + actualURL + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		// Careers URL:

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/company/careers/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/company/careers/";
		}

		try {

			safeClick(driver, linkCareers);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.equalsIgnoreCase(expectedURL)) {

				data.append("On clicking Company Careers Link, Navigated to URL <b>" + actualURL + "</b> successfully.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Careers Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Leadership Link. Navigated to:" + actualURL);

				if (driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim().equals("Job Openings")) {
					data.append("Verified Page Title in Careers Link:<b>Job Openings</b>.;");
				} else {

					dflag = false;
					data.append("Expected Page Title in Careers link:<b>Job Openings</b>. Actual Page Title: <b>" + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + "</b>.;");
					reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Expected Page Title in Careers link:Job Openings. Actual Page Title: " + driver.findElement(By.xpath("//h2[@class='pageTitle']")).getText().trim() + ".", "");

				}

			} else {

				dflag = false;
				data.append("On clicking Careers Link,Navigated to URL " + actualURL + ". Expected URL " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Careers Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Leadership Link. Navigated to:" + actualURL);

			}

			String source = driver.getPageSource();

			if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
				dflag = false;
				data.append(actualURL + " contains Error in the page.;");

			}

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + actualURL + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Company Link*" + status + "*" + data);

		driver.get(url);
		waitForPageLoad(driver);

	}

	public void verifySolution(String url) throws Exception {

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		String actualURL = null;
		String expectedURL = null;

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/solutions/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/solutions/";
		}

		try {

			safeClick(driver, linkSolution);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.contains(expectedURL)) {

				data.append("On clicking Solutions Link, Navigated to URL " + actualURL + " successfully.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);

				List<WebElement> links = driver.findElements(By.xpath("//ul[@class='navSecondary']/li/a"));

				if (links.size() == 4) {

					if (links.get(0).getText().equals("Servicers") && links.get(1).getText().equals("Agents") && links.get(2).getText().equals("Vendors") && links.get(3).getText().equals("Consumers")) {
						data.append("Links available under Solutions tab:<br><b>1.Servicers</b><br><b>2.Agents</b><br><b>3.Vendors</b><br><b>4.Consumers</b>;");
					}

				} else {

					dflag = false;
					StringBuilder temp = new StringBuilder();

					for (int i = 0; i < links.size(); i++) {
						int j = i + 1;
						temp.append("<br><b>" + j + "." + links.get(0).getText() + "</b>");
					}

					data.append("Expected Links available under Solutions tab:<br><b>1.Servicers</b><br><b>2.Agents</b><br><b>3.Vendors</b><br><b>4.Consumers</b>");
					data.append("Available Links available under Solutions tab:" + temp.toString());
				}

			} else {

				dflag = false;
				data.append("On clicking Solutions Link, Navigated to URL " + actualURL + ".But Expected URL" + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);

			}

			String source = driver.getPageSource();

			if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
				dflag = false;
				data.append(actualURL + " contains Error in the page.;");

			}

			driver.get(url);
			waitForPageLoad(driver);

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + actualURL + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Solutions Link*" + status + "*" + data);

	}

	public void verifyErrorOnSearch(String url) throws Exception {

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {

			safeNavigation2(url, "");
			

			String text = driver.findElement(By.xpath("//div[@generated='true' and @for='searchStringDesktop']")).getText();
			// safeGetTextBy(driver,
			// By.xpath("//div[@for='searchStringDesktop' and contains(@style,'block')]"));

			if (text.contains("Please enter a ZIP, neighborhood, city, or address.")) {

				data.append("Message \"<b>" + text + "</b>\" displayed successfully on searching with blank data.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Error Message <b>" + text + "</b> displayed successfully on searching with blank data", "");

			} else {
				dflag = false;
				data.append("Expected Error Message <b>" + text + "</b> is ---NOT--- displayed  on searching with blank data. Displayed Error Message <b>" + text + "</b>.;");
				reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + "Expected Error Message on Searching with blank data: <b>Please enter a ZIP, neighborhood, city, or address.</b>. <br> But displayed " + text + ".;", "");
			}

			/*
			 * driver.get(url); waitForPageLoad(driver);
			 */
			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Error Message with Blank Data Search*" + status + "*" + data);
		} catch (Exception e) {

		}

	}

	public void safeNavigation2(String url, String txtToBeSearched) {

		for (int i = 0; i < 7; i++) {
			try {
				if (driver.findElements(By.xpath("//div[@generated='true' and @for='searchStringDesktop']")).size()==0){
					driver.get(url);
					waitForPageLoad(driver);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", btnSearch);
					
				}else{
					break;
				}
				

			} catch (Exception e) {

			}
		}

	}
	
	
	
	public void safeNavigation(String url, String txtToBeSearched) {

		for (int i = 0; i < 7; i++) {
			try {

				if (btnSearch.isDisplayed()) {

					driver.get(url);
					waitForPageLoad(driver);
					txtSearchBox.sendKeys(txtToBeSearched);
					Thread.sleep(1000);
					//safeClick(driver, btnSearch);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", btnSearch);
					waitForPageLoad(driver);
					try {
						if (btnSearch.isDisplayed()) {
							txtSearchBox.clear();
							txtSearchBox.sendKeys(txtToBeSearched);
							Thread.sleep(3000);
							labelAutoSuggestionText.get(0).click();
							waitForPageLoad(driver);
						} else {
							break;
						}
					} catch (Exception e) {

					}

				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {

			}
		}

	}

	public void safePriceFilterNavigation(String url, String txtToBeSearched) {
		// String txtToBeSearched =
		// LoadConfigFile.getInstance().getValue("Consumer_City");
		for (int i = 0; i < 4; i++) {
			try {

				if (btnSearch.isDisplayed()) {

					driver.get(url);
					waitForPageLoad(driver);

					safeClick(driver, linkMoreOptions);
					safeClick(driver, ddPriceRange);
					WebElement Slider = driver.findElement(By.xpath("(.//*[contains(@id,'price')])[2]/a[2]"));
					for (int j = 0; j < 11; j++) {
						Slider.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(100);
					}

					txtSearchBox.sendKeys(txtToBeSearched);
					Thread.sleep(2000);
					txtSearchBox.sendKeys(" ");
					Thread.sleep(1000);
					safeClick(driver, btnSearch);
					waitForPageLoad(driver);

				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {

			}
		}

	}

	public void safeSquareFeetFilterNavigation(String url, String txtToBeSearched) {
		// String txtToBeSearched =
		// LoadConfigFile.getInstance().getValue("Consumer_City");
		for (int i = 0; i < 4; i++) {
			try {

				if (btnSearch.isDisplayed()) {

					driver.get(url);
					waitForPageLoad(driver);

					safeClick(driver, linkMoreOptions);
					safeClick(driver, ddSqftRange);
					WebElement Slider = driver.findElement(By.xpath("(.//*[contains(@id,'sqft')])[2]/a[2]"));
					for (int j = 0; j < 11; j++) {
						Slider.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(100);
					}

					txtSearchBox.sendKeys(txtToBeSearched);
					Thread.sleep(1000);
					txtSearchBox.sendKeys(" ");
					Thread.sleep(1000);
					safeClick(driver, btnSearch);
					waitForPageLoad(driver);

				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {

			}
		}

	}

	public void safeBedFilterNavigation(String url, String txtToBeSearched) {
		// String txtToBeSearched =
		// LoadConfigFile.getInstance().getValue("Consumer_City");
		for (int i = 0; i < 4; i++) {
			try {

				if (btnSearch.isDisplayed()) {

					driver.get(url);
					waitForPageLoad(driver);

					safeClick(driver, linkMoreOptions);
					safeSelect(driver, By.name("minBedrooms"), "3");
					txtSearchBox.sendKeys(txtToBeSearched);
					Thread.sleep(1000);
					txtSearchBox.sendKeys(" ");
					Thread.sleep(1000);
					safeClick(driver, btnSearch);
					waitForPageLoad(driver);

				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {

			}
		}

	}

	public void safeBathFilterNavigation(String url, String txtToBeSearched) {
		// String txtToBeSearched =
		// LoadConfigFile.getInstance().getValue("Consumer_City");
		for (int i = 0; i < 4; i++) {
			try {

				if (btnSearch.isDisplayed()) {

					driver.get(url);
					waitForPageLoad(driver);

					safeClick(driver, linkMoreOptions);
					safeSelect(driver, By.name("minBathrooms"), "3");
					txtSearchBox.sendKeys(txtToBeSearched);
					Thread.sleep(1000);
					txtSearchBox.sendKeys(" ");
					Thread.sleep(1000);
					safeClick(driver, btnSearch);
					waitForPageLoad(driver);

				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {

			}
		}

	}

	public void safeBedBathFilterNavigation(String url, String txtToBeSearched) {
		// String txtToBeSearched =
		// LoadConfigFile.getInstance().getValue("Consumer_City");
		for (int i = 0; i < 4; i++) {
			try {

				if (btnSearch.isDisplayed()) {

					driver.get(url);
					waitForPageLoad(driver);

					safeClick(driver, linkMoreOptions);
					safeSelect(driver, By.name("minBedrooms"), "2");
					safeSelect(driver, By.name("minBathrooms"), "2");
					txtSearchBox.sendKeys(txtToBeSearched);
					Thread.sleep(1000);
					txtSearchBox.sendKeys(" ");
					Thread.sleep(1000);
					safeClick(driver, btnSearch);
					waitForPageLoad(driver);

				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {

			}
		}

	}

	public void safePriceBedBathSqFtFilterNavigation(String url, String txtToBeSearched) {

		for (int i = 0; i < 4; i++) {
			try {

				if (btnSearch.isDisplayed()) {

					driver.get(url);
					waitForPageLoad(driver);

					safeClick(driver, linkMoreOptions);

					safeClick(driver, ddPriceRange);
					WebElement Slider = driver.findElement(By.xpath("(.//*[contains(@id,'price')])[2]/a[2]"));
					for (int j = 0; j < 11; j++) {
						Slider.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(100);
					}
					safeSelect(driver, By.name("minBedrooms"), "3");
					safeSelect(driver, By.name("minBathrooms"), "3");

					safeClick(driver, ddSqftRange);
					WebElement Slider2 = driver.findElement(By.xpath("(.//*[contains(@id,'sqft')])[2]/a[2]"));
					for (int j = 0; j < 11; j++) {
						Slider2.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(100);
					}
					txtSearchBox.sendKeys(txtToBeSearched);
					Thread.sleep(1000);
					// txtSearchBox.sendKeys(" ");
					// Thread.sleep(1000);
					safeClick(driver, btnSearch);
					waitForPageLoad(driver);

				} else {
					break;
				}

			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {

			}
		}

	}

	public void verifyPriceFilter(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		driver.get(url);
		waitForPageLoad(driver);

		try {

			safePriceFilterNavigation(url, txtToBeSearched);

			data.append("Price Filter:<b>$0 K to $500 K</b>.;");
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toLowerCase().contains(txtToBeSearched.toLowerCase() + " homes for sale")) {

				data.append("Navigated to <b>" + txtToBeSearched + "</b> Property Search results page successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is working as expected", "");

			} else {
				dflag = false;
				data.append("---NOT--- Navigated to " + txtToBeSearched + " Property Search results page .;");

				reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
			}

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				List<WebElement> price = driver.findElements(By.xpath("//span[@class='propertyPrice']"));
				ArrayList<String> list = new ArrayList<String>();

				boolean flag = false;
				for (WebElement web : price) {
					if (web.getText().contains("$")) {
						int tmp = Integer.parseInt(web.getText().replace("$", "").replace(",", ""));
						if (tmp > 500000) {
							flag = true;
							break;
						}
					}

				}

				if (flag) {
					dflag = false;
					data.append("Property Price Filter is ---NOT--- Working as expected. Property with Price more than $500 K is displayed.");
				} else {
					data.append("Property Price Filter is Working as expected. Properties displayed are with Price less than <b>$500 K</b>.");
				}

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Price Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Price Filter*Failed*Price Filter $0 K to $500 K");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Property Search with Price Filter $0 K to $500 K in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifyBedFilter(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		driver.get(url);
		waitForPageLoad(driver);

		try {

			safeBedFilterNavigation(url, txtToBeSearched);

			data.append("Bed Filter used:<b>3+ Bed</b>.;");
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toLowerCase().contains(txtToBeSearched.toLowerCase() + " homes for sale")) {

				data.append("Navigated to <b>" + txtToBeSearched + "</b> Property Search results page successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is working as expected", "");

			} else {
				dflag = false;
				data.append("---NOT--- Navigated to " + txtToBeSearched + " Property Search results page .;");

				reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
			}

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				List<WebElement> beds = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag = false;
				for (WebElement bed : beds) {
					int tmp = Integer.parseInt(bed.getText().split("Bed")[0].trim());
					// System.out.println("tmp bed:" + tmp);
					if (tmp < 3) {
						flag = true;
						break;
					}
				}

				if (flag) {
					dflag = false;
					data.append("Property Bed Filter is ---NOT--- Working as expected. Property with less than 3 beds is displayed.");
				} else {
					data.append("Property Bed Filter is Working as expected. Properties displayed are with <b>3+ bed</b>.");
				}

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Bed Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Bed Filter*Failed*Bed Filter 3+ beds");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Property Search with Bed Filter 3+ bed in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifyBathFilter(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		driver.get(url);
		waitForPageLoad(driver);

		try {

			safeBathFilterNavigation(url, txtToBeSearched);

			data.append("Bath Filter used:<b>3+ Bath</b>.;");
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toLowerCase().contains(txtToBeSearched.toLowerCase() + " homes for sale")) {

				data.append("Navigated to <b>" + txtToBeSearched + "</b> Property Search results page successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is working as expected", "");

			} else {
				dflag = false;
				data.append("---NOT--- Navigated to " + txtToBeSearched + " Property Search results page .;");

				reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
			}

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				List<WebElement> baths = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag = false;
				for (WebElement bath : baths) {
					int tmp = Integer.parseInt(bath.getText().split("Bath")[0].trim().split("/")[1].trim());
					// System.out.println("tmp bath:" + tmp);
					if (tmp < 3) {
						flag = true;
						break;
					}
				}

				if (flag) {
					dflag = false;
					data.append("Property Bath Filter is ---NOT--- Working as expected. Property with less than 3 bath is displayed.");
				} else {
					data.append("Property Bath Filter is Working as expected. Properties displayed are with <b>3+ bath</b>.");
				}

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Bath Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Bath Filter*Failed*Bath Filter 3+ baths");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Property Search with Bath Filter 3+ baths in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifySquareFeetFilter(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		driver.get(url);
		waitForPageLoad(driver);

		try {

			safeSquareFeetFilterNavigation(url, txtToBeSearched);

			data.append("Price Filter:<b>0 Sq Ft to 5000 Sq Ft</b>.;");
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toLowerCase().contains(txtToBeSearched.toLowerCase() + " homes for sale")) {

				data.append("Navigated to <b>" + txtToBeSearched + "</b> Property Search results page successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is working as expected", "");

			} else {
				dflag = false;
				data.append("---NOT--- Navigated to " + txtToBeSearched + " Property Search results page .;");

				reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " City search " + txtToBeSearched + " in Consumer Site is ***NOT Working*** as expected", "");
			}

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				List<WebElement> sqFts = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag = false;
				for (WebElement sqFt : sqFts) {

					String temp = sqFt.getText().split("Bath /")[1].trim().split("sq.ft")[0].trim();

					if (!temp.equals("--")) {
						int tmp = Integer.parseInt(temp);
						if (tmp > 5000) {
							flag = true;
							break;
						}
					}
				}

				if (flag) {
					dflag = false;
					data.append("Property Square Feet Filter is ---NOT--- Working as expected. Property with Square Feet more than 5000 is displayed.");
				} else {
					data.append("Property Square Feet Filter is Working as expected. Properties displayed are with Square Feet less than <b>5000</b>.");
				}

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Square Feet Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Search with Square Feet Filter*Failed*Square Feet Filter 0 to 5000");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Property Search with Square Feet Filter 0 to 5000 in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifyPropertyTypeDropdownSingleFamilyOption(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Refine Property Search Filter: <b>Single Family</b>.;");

			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					safeSelectByText(driver, By.name("propertyTypeId"), "Single Family");
					waitForPageLoad(driver);

					List<WebElement> singleFamilies = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[2]"));

					boolean flag1 = false;
					for (WebElement singleFamily : singleFamilies) {

						String tmp1 = singleFamily.getText().split("/")[0].trim();
						if (!tmp1.contains("Single Family")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Single Family Filter is ---NOT--- Working as expected. Property other than Single Family also displayed.");
					} else {
						data.append("Single Family Filter displays property only belongs to Single Family Type.Total Single Family Property <b>" + singleFamilies.size() + "</b>.");
					}

				} else {
					data.append("No Property available for the city:" + txtToBeSearched);
				}
			} else {
				data.append("No Property of type \"Single Family\" available for the city:" + txtToBeSearched);
			}

			String status;
			if (dflag) {
				status = "Passed";
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Single Family Filter in Consumer Site is working as expected", "");
			} else {
				status = "Failed";
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Single Family Filter in Consumer Site is  ---NOT---working as expected", "");
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Single Family Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Single Family Filter*Failed*Exception occured while testing");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Single Family Filter in Consumer Site is  ---NOT---working as expected", "");

		}

	}

	public void verifyPropertyTypeDropdownCondoOption(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Refine Property Search Filter: <b>Condo</b>.;");

			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					safeSelectByText(driver, By.name("propertyTypeId"), "Condo");
					waitForPageLoad(driver);

					List<WebElement> Condos = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[2]"));

					boolean flag1 = false;
					for (WebElement Condo : Condos) {

						String tmp1 = Condo.getText().split("/")[0].trim();
						if (!tmp1.contains("Condo")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Condo Filter is ---NOT--- Working as expected. Property other than Condo also displayed.");
					} else {
						data.append("Condo Filter displays property only belongs to Condo Type.Total Condo Property <b>" + Condos.size() + "</b>.");
					}

				} else {
					data.append("No Property available for the city:" + txtToBeSearched);
				}
			} else {
				data.append("No Property of \"Condo\" type available for the city:" + txtToBeSearched);
			}

			String status;
			if (dflag) {
				status = "Passed";
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Condo Filter in Consumer Site is working as expected", "");
			} else {
				status = "Failed";
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Condo Filter in Consumer Site is  ---NOT---working as expected", "");
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Condo Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Condo Filter*Failed*Exception occured while testing");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Condo Filter in Consumer Site is  ---NOT---working as expected", "");

		}

	}

	public void verifyPropertyTypeDropdownLandOption(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Refine Property Search Filter: <b>Land</b>.;");

			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					safeSelectByText(driver, By.name("propertyTypeId"), "Land");
					waitForPageLoad(driver);

					List<WebElement> Lands = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[2]"));

					boolean flag1 = false;
					for (WebElement Land : Lands) {

						String tmp1 = Land.getText().split("/")[0].trim();
						if (!tmp1.contains("Land")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Land Filter is ---NOT--- Working as expected. Property other than Land Type also displayed.");
					} else {
						data.append("Land Filter displays property only belongs to Land Type.Total Land Property <b>" + Lands.size() + "</b>.");
					}

				} else {
					data.append("No Property available for the city:" + txtToBeSearched);
				}
			} else {
				data.append("No Property of type \"Land\" available for the city:" + txtToBeSearched);
			}

			String status;
			if (dflag) {
				status = "Passed";
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Land Filter in Consumer Site is working as expected", "");
			} else {
				status = "Failed";
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Land Filter in Consumer Site is  ---NOT---working as expected", "");
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Land Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Land Filter*Failed*Exception occured while testing");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Land Filter in Consumer Site is  ---NOT---working as expected", "");

		}

	}

	public void verifyPropertyTypeDropdownOtherOption(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Refine Property Search Filter: <b>Other</b>.;");

			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					safeSelectByText(driver, By.name("propertyTypeId"), "Other");
					waitForPageLoad(driver);

					List<WebElement> Others = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[2]"));

					boolean flag1 = false;
					for (WebElement Other : Others) {

						String tmp1 = Other.getText().split("/")[0].trim();
						if (!tmp1.contains("Other")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Other Filter is ---NOT--- Working as expected. Property other than Other Type also displayed.");
					} else {
						data.append("Other Filter displays property only belongs to Other Type.Total Other Property <b>" + Others.size() + "</b>.");
					}

				} else {
					data.append("No Property available for the city:" + txtToBeSearched);
				}
			} else {
				data.append("No Property of \"Other\" typer available for the city:" + txtToBeSearched);
			}

			String status;
			if (dflag) {
				status = "Passed";
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Other Filter in Consumer Site is working as expected", "");
			} else {
				status = "Failed";
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Other Filter in Consumer Site is  ---NOT---working as expected", "");
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Other Filter*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Other Filter*Failed*Exception occured while testing");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Other Filter in Consumer Site is  ---NOT---working as expected", "");

		}

	}

	public void verifyForeClosureToggle(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Refine Property Search Filter: <b>Foreclosure Toggle</b>.;");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Short Sale']]/div/img[2]")));
			waitForPageLoad(driver);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Standard']]/div/img[2]")));
			waitForPageLoad(driver);
			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					List<WebElement> types = driver.findElements(By.xpath("//div[@class='transactionTypeWrapper']/span"));

					boolean flag1 = false;
					for (WebElement type : types) {

						String tmp1 = type.getText().trim();
						if (!tmp1.contains("Foreclosure")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Foreclosure Toggle is ---NOT--- Working as expected. Property other than Foreclosure Type also displayed.");
					} else {
						data.append("Foreclosure Toggle displays property only belongs to Foreclosure Type.Total Foreclosure Property <b>" + types.size() + "</b>.");
					}

				} else {
					data.append("No Property available for the city:" + txtToBeSearched);
				}
			} else {
				data.append("For Foreclosure Toggle Filter No Property available for the city:" + txtToBeSearched);
			}

			String status;
			if (dflag) {
				status = "Passed";
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Foreclosure Toggle in Consumer Site is working as expected", "");
			} else {
				status = "Failed";
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Foreclosure Toggle in Consumer Site is  ---NOT---working as expected", "");
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Foreclosure Toggle*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Foreclosure Toggle*Failed*Exception occured while testing");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Foreclosure Toggle in Consumer Site is  ---NOT---working as expected", "");

		}

	}

	public void verifyShortSaleToggle(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Refine Property Search Filter: <b>Short Sale Toggle</b>.;");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Foreclosure']]/div/img[2]")));
			waitForPageLoad(driver);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Standard']]/div/img[2]")));
			waitForPageLoad(driver);
			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					List<WebElement> types = driver.findElements(By.xpath("//div[@class='transactionTypeWrapper']/span"));

					boolean flag1 = false;
					for (WebElement type : types) {

						String tmp1 = type.getText().trim();
						if (!tmp1.contains("Short Sale")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Short Sale Toggle is ---NOT--- Working as expected. Property other than Short Sale Type also displayed.");
					} else {
						data.append("Short Sale Toggle displays property only belongs to Short Sale Type.Total Short Sale Property <b>" + types.size() + "</b>.");
					}

				} else {
					data.append("No Property available for the city:" + txtToBeSearched);
				}
			} else {
				data.append("For Short Sale Toggle Filter No Property available for the city:" + txtToBeSearched);
			}

			String status;
			if (dflag) {
				status = "Passed";
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Short Sale Toggle in Consumer Site is working as expected", "");
			} else {
				status = "Failed";
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Short Sale Toggle in Consumer Site is  ---NOT---working as expected", "");
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Short Sale Toggle*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Short Sale Toggle*Failed*Exception occured while testing");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Short Sale Toggle in Consumer Site is  ---NOT---working as expected", "");

		}

	}

	public void verifyStandardToggle(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Refine Property Search Filter: <b>Standard Toggle</b>.;");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Foreclosure']]/div/img[2]")));
			waitForPageLoad(driver);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Short Sale']]/div/img[2]")));
			waitForPageLoad(driver);

			if (driver.findElements(By.xpath("//div[@class='searchNoPropDetails']/h1")).size() == 0) {
				String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

					List<WebElement> types = driver.findElements(By.xpath("//div[@class='transactionTypeWrapper']/span"));

					boolean flag1 = false;
					for (WebElement type : types) {

						String tmp1 = type.getText().trim();
						if (!tmp1.contains("Standard")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Standard Toggle is ---NOT--- Working as expected. Property other than Standard Type also displayed.");
					} else {
						data.append("Standard Toggle displays property only belongs to Standard Type.Total Standard Property <b>" + types.size() + "</b>.");
					}

				} else {
					data.append("No Property available for the city:" + txtToBeSearched);
				}

			} else {
				data.append("For Standard Toggle Filter No Property available for the city:" + txtToBeSearched + ".");
			}

			String status;
			if (dflag) {
				status = "Passed";
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Standard Toggle in Consumer Site is working as expected", "");
			} else {
				status = "Failed";
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Standard Toggle in Consumer Site is  ---NOT---working as expected", "");
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Standard Toggle*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Refine Property Search with Standard Toggle*Failed*Exception occured while testing");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Refine Property Search with Standard Toggle in Consumer Site is  ---NOT---working as expected", "");

		}

	}

	public void verifySavedSearch(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		String searchName = RandomStringUtils.randomAlphanumeric(8);
		driver.get(url);
		waitForPageLoad(driver);

		try {

			safeBedBathFilterNavigation(url, txtToBeSearched);

			data.append("Bath Filter used:<b>2+ Bath</b>.;");
			data.append("Bed Filter used:<b>2+ Bed</b>.;");
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				btnSaveSearch.click();
				waitForPageLoad(driver);
				txtSearchName.sendKeys(searchName);
				btnSaveSearch2.click();
				data.append("Save Search Name:<b>" + searchName + "</b>.;");
				waitForPageLoad(driver);
				Thread.sleep(5000);

				safeClickBy(driver, By.xpath(".//*[@id='account-nav']/li/a"));
				safeClickBy(driver, By.xpath("(.//*[@id='account-nav']/li/div/div/a[2])[1]"));
				waitForPageLoad(driver);

				if (driver.findElements(By.xpath("//a[text()='" + searchName + "']")).size() > 0) {
					data.append("Save Search created a record successfully.;");
				} else {
					dflag = false;
					data.append("Save Search ---NOT--- created a record successfully.;");
				}

				safeClickBy(driver, By.xpath("//a[text()='" + searchName + "']"));
				waitForPageLoad(driver);

				if (new Select(ddBed).getFirstSelectedOption().getText().contains("2")) {
					data.append("No of Beds saved as 2 as expected.;");
				} else {
					dflag = false;
					data.append("No of Beds is ---NOT---saved as 2.;");
				}

				if (new Select(ddBath).getFirstSelectedOption().getText().contains("2")) {
					data.append("No of Baths saved as 2 as expected.;");
				} else {
					dflag = false;
					data.append("No of Baths is ---NOT---saved as 2.;");
				}

				String record2 = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

				if (record.equals(record2)) {
					data.append("On Clicking Save Search link " + searchName + ", it displays the same set of properties as expected. <br>Expected:<b>" + record + "</b>.<br> Actual:<b>" + record2 + "</b>;");
				} else {
					dflag = false;
					data.append("On Clicking Save Search link " + searchName + ", Properties displayed are---NOT---as expected one. <br>Expected:<b>" + record + "</b>.<br> Actual:<b>" + record2 + "</b>.;");
				}

				safeClickBy(driver, By.xpath(".//*[@id='account-nav']/li/a"));
				safeClickBy(driver, By.xpath("(.//*[@id='account-nav']/li/div/div/a[2])[1]"));
				waitForPageLoad(driver);

				safeClickBy(driver, By.xpath("//li[p[a[text()='" + searchName + "']]]/div/a[2]"));
				waitForPageLoad(driver);

				if (driver.findElements(By.xpath("//a[text()='" + searchName + "']")).size() == 0) {
					data.append("Save Search record deleted successfully.;");
				} else {
					dflag = false;
					data.append("Save Search record---NOT--- deleted successfully.;");
				}

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Saved Search Functionality*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Saved Search Functionality*Failed*Exception happened during testing");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Saved Search Functionality in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifySavedProperty(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		driver.get(url);
		waitForPageLoad(driver);

		try {

			safeNavigation(url, txtToBeSearched);
			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				String address = safeGetTextBy(driver, By.xpath("//li[div[a[contains(@class,'iconFavorite') and not(contains(@class,'active'))]]]/a/p[1]"));

				safeClickBy(driver, By.xpath("//a[contains(@class,'iconFavorite') and not(contains(@class,'active'))]"));
				waitForPageLoad(driver);
				Thread.sleep(5000);
				data.append("Property Saved Address:<b>" + address + "</b>.;");

				safeClickBy(driver, By.xpath(".//*[@id='account-nav']/li/a"));
				safeClickBy(driver, By.xpath("(.//*[@id='account-nav']/li/div/div/a[1])[1]"));
				waitForPageLoad(driver);

				if (driver.findElements(By.xpath("//p[contains(text(),'" + address + "')]")).size() > 0) {
					data.append("Save Property created a record successfully.;");
				} else {
					dflag = false;
					data.append("Save Property ---NOT--- created a record successfully.;");
				}

				safeClickBy(driver, By.xpath("//p[contains(text(),'" + address + "')]"));
				waitForPageLoad(driver);

				String address2 = safeGetTextBy(driver, By.xpath(".//*[@id='vwDetailsHeader']/div[3]/h1"));

				if (address2.toLowerCase().contains(address.toLowerCase())) {
					data.append("On clicking address link <b>" + address + "</b>, it navigated to correct Property Details page.;");
				} else {
					dflag = false;
					data.append("On clicking address link <b>" + address + "</b>, it did ---NOT navigate to correct Property Details page.;");
				}

				if (driver.findElements(By.xpath("//a[contains(text(),'<< Back To Saved Properties')]")).size() > 0) {
					data.append("<b>Back To Saved Properties</b> button is available in Property Details page.");
					driver.findElement(By.xpath("//a[contains(text(),'<< Back To Saved Properties')]")).click();
					waitForPageLoad(driver);
					if (driver.findElements(By.xpath("//p[contains(text(),'" + address + "')]")).size() > 0) {
						data.append("On Clicking <b>Back To Saved Properties</b> button, it navigated to Saved Property Page successfully.;");
					} else {
						dflag = false;
						data.append("On Clicking <b>Back To Saved Properties</b> button, it navigated to Saved Property Page successfully.;");
					}

				} else {
					dflag = false;
					data.append("<b>Back To Saved Properties</b> button is ---NOT--- available in Property Details page.");
				}

				safeClickBy(driver, By.xpath("//li[a[p[contains(text(),'" + address + "')]]]/div/a[3]"));
				waitForPageLoad(driver);

				if (driver.findElements(By.xpath("//p[contains(text(),'" + address + "')]")).size() == 0) {
					data.append("Saved Property record deleted successfully.;");
				} else {
					dflag = false;
					data.append("Saved Property record---NOT--- deleted successfully.;");
				}

			}

			String status;
			if (dflag) {
				status = "Passed";
			} else {
				status = "Failed";
			}

			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Saved Property Functionality*" + status + "*" + data);

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Saved Property Functionality*Failed*Exception happened during testing");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Saved Property Functionality in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	@FindBy(id = "price-range-indicator")
	public WebElement btnPriceIndicator;

	@FindBy(xpath = "//button[contains(@class,'propsize')]")
	public WebElement btnPropertySize;

	@FindBy(xpath = ".//*[@id='topnav']/a[1]")
	public WebElement linkReset1;

	public void verifyResetFunctionality(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		driver.get(url);
		waitForPageLoad(driver);

		try {

			safePriceBedBathSqFtFilterNavigation(url, txtToBeSearched);

			String priceBeforeReset = btnPriceIndicator.getText();
			String bedBeforeReset = new Select(ddBed).getFirstSelectedOption().getText();
			String bathBeforeReset = new Select(ddBath).getFirstSelectedOption().getText();
			String propertySizeBeforeReset = btnPropertySize.getText().split("Sq Ft.")[1];

			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Price Filter Before Reset:<b>" + priceBeforeReset + "</b>.;");
			data.append("Bed Filter Before Reset:<b>" + bedBeforeReset + "</b>.;");
			data.append("Bath Filter Before Reset:<b>" + bathBeforeReset + "</b>.;");
			data.append("Square Feet Filter Before Reset:<b>" + propertySizeBeforeReset + "Sq Ft</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				String cityCountBeforeReset;
				cityCountBeforeReset = record.split("-")[1].trim();
				data.append("Available Property for this Filter Before Reset:<b>" + cityCountBeforeReset + "</b>.;");

				List<WebElement> price = driver.findElements(By.xpath("//span[@class='propertyPrice']"));

				boolean flag = false;
				for (WebElement web : price) {
					if (web.getText().contains("$")) {
						int tmp = Integer.parseInt(web.getText().replace("$", "").replace(",", ""));
						if (tmp > 500000) {
							flag = true;
							break;
						}
					}

				}

				if (flag) {
					dflag = false;
					data.append("Property Price Filter is ---NOT--- Working as expected. Property with Price more than $500 K is displayed.;");
				} else {
					data.append("Property Price Filter is Working as expected. Properties displayed are with Price less than <b>$500 K</b>.;");
				}

				List<WebElement> beds = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag2 = false;
				for (WebElement bed : beds) {
					int tmp = Integer.parseInt(bed.getText().split("Bed")[0].trim());
					// System.out.println("tmp bed:" + tmp);
					if (tmp < 3) {
						flag2 = true;
						break;
					}
				}

				if (flag2) {
					dflag = false;
					data.append("Property Bed Filter is ---NOT--- Working as expected. Property with less than 3 beds is displayed.;");
				} else {
					data.append("Property Bed Filter is Working as expected. Properties displayed are with <b>3+ bed</b>.;");
				}

				List<WebElement> baths = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag3 = false;
				for (WebElement bath : baths) {
					int tmp = Integer.parseInt(bath.getText().split("Bath")[0].trim().split("/")[1].trim());
					// System.out.println("tmp bath:" + tmp);
					if (tmp < 3) {
						flag3 = true;
						break;
					}
				}

				if (flag3) {
					dflag = false;
					data.append("Property Bath Filter is ---NOT--- Working as expected. Property with less than 3 bath is displayed.;");
				} else {
					data.append("Property Bath Filter is Working as expected. Properties displayed are with <b>3+ bath</b>.;");
				}

				List<WebElement> sqFts = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag4 = false;
				for (WebElement sqFt : sqFts) {

					String temp = sqFt.getText().split("Bath /")[1].trim().split("sq.ft")[0].trim();

					if (!temp.equals("--")) {
						int tmp = Integer.parseInt(temp);
						if (tmp > 5000) {
							flag4 = true;
							break;
						}
					}
				}
				if (flag4) {
					dflag = false;
					data.append("Property Square Feet Filter is ---NOT--- Working as expected. Property with Square Feet more than 5000 is displayed.;");
				} else {
					data.append("Property Square Feet Filter is Working as expected. Properties displayed are with Square Feet less than <b>5000 Sq Ft</b>.;");
				}

				linkReset1.click();
				waitForPageLoad(driver);

				String priceAfterReset = btnPriceIndicator.getText();
				String bedAfterReset = new Select(ddBed).getFirstSelectedOption().getText();
				String bathAfterReset = new Select(ddBath).getFirstSelectedOption().getText();
				String propertySizeAfterReset = btnPropertySize.getText().split("Sq Ft.")[1];

				if (priceAfterReset.contains("$0 - $1,000,000+")) {
					data.append("After Reset Price filter range changed to <b>" + priceAfterReset + "</b>.;");
				} else {
					dflag = false;
					data.append("After Reset Price filter range ---NOT--- changed to <b>$0 - $1,000,000+</b>.But changed to <b>" + priceAfterReset + "</b>.;");
				}

				if (bedAfterReset.contains("Any Bed")) {
					data.append("After Reset Bed filter range changed to <b>" + bedAfterReset + "</b>.;");
				} else {
					dflag = false;
					data.append("After Reset Bed filter range ---NOT--- changed to <b>Any Bed</b>.But changed to <b>" + bedAfterReset + "</b>.;");
				}

				if (bathAfterReset.contains("Any Bath")) {
					data.append("After Reset Bath filter range changed to <b>" + bathAfterReset + "</b>.;");
				} else {
					dflag = false;
					data.append("After Reset Bath filter range ---NOT--- changed to <b>Any Bath</b>.But changed to <b>" + bathAfterReset + "</b>.;");
				}

				if (propertySizeAfterReset.contains("0 - 10,000+")) {
					data.append("After Reset Property Size filter range changed to <b>0 - 10,000+ Sq Ft</b>.;");
				} else {
					dflag = false;
					data.append("After Reset Property Size filter range ---NOT--- changed to <b>0 - 10,000+ Sq Ft</b>.But changed to <b>" + propertySizeAfterReset + "Sq Ft</b>.;");
				}

				String record2 = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));
				String cityCountAfterReset = record2.split("-")[1].trim();

				if (Integer.parseInt(cityCountAfterReset.split("Available")[0].trim()) > Integer.parseInt(cityCountBeforeReset.split("Available")[0].trim())) {
					data.append("After Reset Available Property count increased from <b>" + cityCountBeforeReset + "</b> to <b>" + cityCountAfterReset + "</b>.");
				} else {
					data.append("After Reset Available Property count ---NOT--- increased. But Decreased from <b>" + cityCountBeforeReset + "</b> to <b>" + cityCountAfterReset + "</b>.");
				}

				List<WebElement> price2 = driver.findElements(By.xpath("//span[@class='propertyPrice']"));
				boolean flag5 = false;
				for (WebElement web : price2) {

					if (web.getText().contains("$")) {
						int tmp = Integer.parseInt(web.getText().replace("$", "").replace(",", ""));
						if (tmp > 500000) {
							flag = true;
							break;
						}
					}

				}

				if (flag5) {

					data.append("After Reset Property Price Filter is Working as expected. Properties displayed are with Price More than <b>$500 K</b>.;");
				}

				List<WebElement> beds2 = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag6 = false;
				for (WebElement bed : beds2) {
					int tmp = Integer.parseInt(bed.getText().split("Bed")[0].trim());
					// System.out.println("tmp bed:" + tmp);
					if (tmp < 3) {
						flag6 = true;
						break;
					}
				}

				if (flag6) {

					data.append("After Reset  Property Bed Filter is Working as expected. Property with less than 3 beds is displayed.;");
				}

				List<WebElement> baths2 = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag7 = false;
				for (WebElement bath : baths2) {
					int tmp = Integer.parseInt(bath.getText().split("Bath")[0].trim().split("/")[1].trim());
					// System.out.println("tmp bath:" + tmp);
					if (tmp < 3) {
						flag7 = true;
						break;
					}
				}

				if (flag7) {

					data.append("After Reset  Property Bath Filter is Working as expected. Property with less than 3 baths is displayed.;");

				}

				List<WebElement> sqFts2 = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[1]"));

				boolean flag8 = false;
				for (WebElement sqFt : sqFts2) {

					String temp = sqFt.getText().split("Bath /")[1].trim().split("sq.ft")[0].trim();

					if (!temp.equals("--")) {
						int tmp = Integer.parseInt(temp);
						if (tmp > 5000) {
							flag8 = true;
							break;
						}
					}
				}
				if (flag8) {
					data.append("After Reset  Property Size Filter is Working as expected. Property size with more than <b>5000 Sq Ft</b>is displayed.;");
				}

				String status;
				if (dflag) {
					status = "Passed";
				} else {
					status = "Failed";
				}

				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Reset Functionality*" + status + "*" + data);

			}
		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Reset Functionality*Failed*Exception happened during testing");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Reset Functionality in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifyResetFunctionality2(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		try {

			safeNavigation(url, txtToBeSearched);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Foreclosure']]/div/img[2]")));
			waitForPageLoad(driver);
			safeSelectByText(driver, By.name("propertyTypeId"), "Single Family");
			waitForPageLoad(driver);

			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");
			data.append("Foreclosure Toggle Filter is OFF.;");
			data.append("In Property Type Dropdown option selected is<b>Single Family</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				String cityCountBeforeReset;
				cityCountBeforeReset = record.split("-")[1].trim();
				data.append("Available Property for this Filter Before Reset:<b>" + cityCountBeforeReset + "</b>.;");

				List<WebElement> types = driver.findElements(By.xpath("//div[@class='transactionTypeWrapper']/span"));

				boolean flag1 = false;
				for (WebElement type : types) {

					String tmp1 = type.getText().trim();
					if (tmp1.contains("Foreclosure")) {
						flag1 = true;
						break;
					}

				}

				if (driver.findElements(By.xpath("//li[span[text()='Foreclosure']]/div[contains(@class,'eqtoggle_on')]")).size() == 0) {
					data.append("Foreclosure Toggle is <b>OFF</b> before reset.;");
				} else {
					dflag = false;
					data.append("Foreclosure Toggle is ---NOT--- <b>OFF</b> before reset.;");
				}

				if (flag1) {
					dflag = false;
					data.append("Foreclosure Toggle is ---NOT--- Working as expected. Property Foreclosure Type also displayed.;");
				} else {
					data.append("Verified Foreclosure Toggle OFF displayed property belong to  other than Foreclosure Type.;");
				}

				List<WebElement> singleFamilies = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[2]"));

				boolean flag2 = false;
				for (WebElement singleFamily : singleFamilies) {

					String tmp1 = singleFamily.getText().split("/")[0].trim();
					if (!tmp1.contains("Single Family")) {
						flag2 = true;
						break;
					}

				}

				if (flag2) {
					dflag = false;
					data.append("Single Family Filter is ---NOT--- Working as expected. Property other than Single Family also displayed.");
				} else {
					data.append("Single Family Filter displays property only belongs to Single Family Type.;");
				}

				linkReset1.click();
				waitForPageLoad(driver);

				String propertyTypeDropOptionAfterReset = new Select(driver.findElement(By.name("propertyTypeId"))).getFirstSelectedOption().getText();

				if (propertyTypeDropOptionAfterReset.contains("All Property Type")) {
					data.append("After Reset Property Type Dropdown option is reset to <b>All Property Type</b>.;");
				} else {
					dflag = false;
					data.append("After Reset Property Type Dropdown option is ---NOT-- reset to <b>All Property Type</b>.;");
				}

				if (driver.findElements(By.xpath("//li[span[text()='Foreclosure']]/div[contains(@class,'eqtoggle_on')]")).size() > 0) {
					data.append("Foreclosure Toggle is <b>ON</b> after reset.;");
				} else {
					dflag = false;
					data.append("Foreclosure Toggle is ---NOT--- <b>ON</b> after reset.;");
				}

				String record2 = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));
				String cityCountAfterReset = record2.split("-")[1].trim();

				if (Integer.parseInt(cityCountAfterReset.split("Available")[0].trim()) > Integer.parseInt(cityCountBeforeReset.split("Available")[0].trim())) {
					data.append("After Reset Available Property count increased from <b>" + cityCountBeforeReset + "</b> to <b>" + cityCountAfterReset + "</b>.");
				} else {
					data.append("After Reset Available Property count ---NOT--- increased. But Decreased from <b>" + cityCountBeforeReset + "</b> to <b>" + cityCountAfterReset + "</b>.");
				}

				List<WebElement> types2 = driver.findElements(By.xpath("//div[@class='transactionTypeWrapper']/span"));

				boolean flag3 = false;
				for (WebElement type : types2) {

					String tmp1 = type.getText().trim();
					if (tmp1.contains("Foreclosure")) {
						flag3 = true;
						break;
					}

				}

				if (flag3) {
					data.append("After Reset <b>Foreclosure</b> property is also displayed as expected.;");
				}

				List<WebElement> singleFamilies2 = driver.findElements(By.xpath("//li[@class='propertyDetails']/p[2]"));

				boolean flag4 = false;
				for (WebElement singleFamily : singleFamilies2) {

					String tmp1 = singleFamily.getText().split("/")[0].trim();
					if (!tmp1.contains("Single Family")) {
						flag4 = true;
						break;
					}

				}

				if (flag4) {

					data.append("After Reset All property type properties are displayed as expected");
				}

				String status;
				if (dflag) {
					status = "Passed";
				} else {
					status = "Failed";
				}

				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Reset Functionality2*" + status + "*" + data);

			}
		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Reset Functionality2*Failed*Exception occurred while testing");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Reset functionality2  in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	@FindBy(id = "mapViewBtn")
	public WebElement btnMap;

	@FindBy(xpath = "//a[text()='Refine Search']")
	public WebElement linkRefineSearch;

	@FindBy(xpath = "//a[text()='Results']")
	public WebElement linkResultSearch;

	public void verifyMapView(String url) throws Exception {
		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {

			safeNavigation(url, txtToBeSearched);

			data.append("City Searched:<b>" + txtToBeSearched + "</b>.;");

			String record = safeGetTextBy(driver, By.xpath("//div[contains(@class,'comp_header')]/h1"));

			if (record.toUpperCase().contains("AVAILABLE") && driver.findElements(By.xpath("//p[@class='propertyAddress']")).size() > 0) {

				String cityCount;
				cityCount = record.split("-")[1].trim().split("Available")[0].trim();
				data.append("Available Property Before Navigating to Map:<b>" + cityCount + "</b>.;");

				// btnMap.click();
				js.executeScript("arguments[0].click();", btnMap);
				waitForPageLoad(driver);

				if (driver.findElements(By.xpath("//div[@id='viewletMapResultsMap']/div[@class='map_canvas']")).size() > 0) {

					data.append("After clicking on \"Map\" button, Map Section is displayed.>.;");
				} else {
					dflag = false;
					data.append("After clicking on \"Map\" button, Map Section is ---NOT---displayed.>.;");
				}

				String cityActual = driver.findElement(By.xpath("//div[@id='liMapResults']/ul/li[@class='listResultsProperties']")).getText();

				if (cityActual.contains(cityCount)) {

					data.append("Verified Same number of Properties displayed in <b>Map</b> view:" + cityCount + ".;");
				} else {
					dflag = false;
					data.append("Same number of Properties is ---NOT--- displayed in <b>Map</b> view. Expected Property Count:" + cityCount + " Availble. Actual Property count:" + cityActual + ".;");
				}

				linkRefineSearch.click();
				waitForPageLoad(driver);

				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Short Sale']]/div/img[2]")));
				waitForPageLoad(driver);
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[span[text()='Standard']]/div/img[2]")));
				waitForPageLoad(driver);
				linkResultSearch.click();
				waitForPageLoad(driver);

				int prop = Integer.parseInt(driver.findElement(By.xpath(".//*[@id='liMapResults']/ul[1]/li[1]")).getText().split("Properties Found")[0].trim());
				if (prop > 0) {

					List<WebElement> types = driver.findElements(By.xpath("//div[@id='liMapResults']/div/ul/li/span[@class='transactionTypeWrapper']/span"));

					boolean flag1 = false;
					for (WebElement type : types) {
						System.out.println(type.getText());
						String tmp1 = type.getText();
						if (!tmp1.contains("Foreclosure")) {
							flag1 = true;
							break;
						}

					}

					if (flag1) {
						dflag = false;
						data.append("Foreclosure Toggle is ---NOT--- Working as expected in <b>Map</b> View. Property other than Foreclosure Type also displayed.;");
					} else {
						data.append("Foreclosure Toggle displays property only belongs to Foreclosure Type in <b>Map</b> View .Total Foreclosure Property <b>" + types.size() + "</b>.;");
					}
					try {
						driver.findElement(By.xpath(".//*[@id='liMapResults']/div/ul/li[1]/span[3]/span/a/img")).click();
						waitForPageLoad(driver);

						ConsumerSite_PropertyPage cp = new ConsumerSite_PropertyPage(driver);

						if (cp.btnBackToResults.size() > 0 && cp.labelAddressArea.size() > 0 && cp.sectionContactListingAgent.size() > 0 && cp.labelAddressArea.size() > 0) {

							data.append("From Map view on clicking first result in Property search results page,it navigated to Property Details page.;");
						} else {
							dflag = false;
							data.append("From Map view on clicking first result in Property search results page,---NOT--- navigated to Property Details page.;");
						}
					} catch (Exception e) {

					}

				} else {
					data.append("For Foreclosure Toggle Filter No Property available for the city:" + txtToBeSearched + ".;");
				}

				String status;
				if (dflag) {
					status = "Passed";
				} else {
					status = "Failed";
				}

				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Map Functionality in Property Results Page*" + status + "*" + data);

			}
		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Map Functionality in Property Results Page*Failed*Exception occurred while testing");
			reporter("FAIL", "Step " + ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Map Functionality in Property Results Page  in Consumer Site is ***NOT Working*** as expected", "");

		}

	}

	public void verifySolutionServicer(String url) throws Exception {

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		String actualURL = null;
		String expectedURL = null;

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/solutions/servicers/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/solutions/servicers/";
		}

		try {

			safeClick(driver, linkSolution);
			waitForPageLoad(driver);
			safeClick(driver, linkServicers);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.contains(expectedURL)) {

				data.append("On clicking Solutions Servicers Link, Navigated to URL " + actualURL + " successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Servicers Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);
				String source = driver.getPageSource();

				if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
					dflag = false;
					data.append(actualURL + " contains Error in the page.;");

				}

				List<WebElement> links = driver.findElements(By.xpath("//ul[@class='navSecondary']/li/a[text()='Servicers']/following-sibling::ul/li/a"));

				String[] st = new String[] { "Loan Management", "Loan Modification", "Foreclosure Bankruptcy", "Short Sale", "Deed-In-Lieu", "Real Estate Segmentation", "REO", "Invoice Management", "Borrower Portal", "Investor Portal" };
				List<String> expected = new ArrayList<String>(Arrays.asList(st));

				List<String> list = new ArrayList<String>();
				for (int i = 0; i < links.size(); i++) {
					list.add(links.get(i).getText());
				}

				if (list.equals(expected)) {

					data.append("Verified Links available under Services Section:<b>" + list + "</b>;");

				} else {

					dflag = false;
					data.append("Expected Links under Servicers section is ---NOT---matching with Actual Links. Expected Links available under Servicers Section:<b>" + expected + "</b>.");
					data.append("Available Links available under Servicers Section:" + list + ";");
					reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Servicers Link. Expected:" + expected + ". Actual links:" + list, "");
				}

				for (int i = 0; i < links.size(); i++) {
					links = driver.findElements(By.xpath("//ul[@class='navSecondary']/li/a[text()='Servicers']/following-sibling::ul/li/a"));
					links.get(i).click();
					waitForPageLoad(driver);

					String url2 = driver.getCurrentUrl();
					links = driver.findElements(By.xpath("//ul[@class='navSecondary']/li/a[text()='Servicers']/following-sibling::ul/li/a"));
					String tmpUrl = null;
					try {

						if (links.get(i).getText().contains("Short Sale")) {
							tmpUrl = expectedURL + links.get(i).getText().replace(" ", "").toLowerCase() + "/";
						} else if (links.get(i).getText().contains("Real Estate Segmentation")) {
							tmpUrl = expectedURL + "real-estate-seg/";
						}

						else {
							tmpUrl = expectedURL + links.get(i).getText().replace(" ", "-").toLowerCase() + "/";
						}

					} catch (Exception e) {

					}

					if (!url2.equals(tmpUrl)) {
						dflag = false;
						data.append("On Clicking link " + links.get(i).getText() + ", it is ---NOT--- navigated to " + tmpUrl + ". But navigated to :" + url2 + ";");
					}

					String source2 = driver.getPageSource();

					if (source2.contains("404 - File or directory not found.") || source2.contains("Server Error")) {
						dflag = false;
						data.append(url2 + " contains Error in the page.;");

					}

				}

			} else {

				dflag = false;
				data.append("On clicking Solutions Servicers Link, Navigated to URL " + actualURL + ". But Expected URL: " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Servicers Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);

			}

			driver.get(url);
			waitForPageLoad(driver);

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + driver.getCurrentUrl() + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + driver.getCurrentUrl() + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Solutions Servicers Link*" + status + "*" + data);

	}

	public void verifySolutionAgent(String url) throws Exception {

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		String actualURL = null;
		String expectedURL = null;

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/solutions/agents/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/solutions/agents/";
		}

		try {

			safeClick(driver, linkSolution);
			waitForPageLoad(driver);
			safeClick(driver, linkAgents);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.contains(expectedURL)) {

				data.append("On clicking Solutions Agents Link, Navigated to URL " + actualURL + " successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Agents Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);
				String source = driver.getPageSource();

				if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
					dflag = false;
					data.append(actualURL + " contains Error in the page.;");

				}

				List<WebElement> links = driver.findElements(By.xpath("//ul[@class='navSecondary']/li/a[text()='Agents']/following-sibling::ul/li/a"));

				String[] st = new String[] { "Premium Placement ZIP codes", "Workflow Management", "EQ Certification" };
				List<String> expected = new ArrayList<String>(Arrays.asList(st));

				List<String> list = new ArrayList<String>();
				for (int i = 0; i < links.size(); i++) {
					list.add(links.get(i).getText());
				}

				if (list.equals(expected)) {

					data.append("Verified Links available under Agents Section:<b>" + list + "</b>;");

				} else {

					dflag = false;
					data.append("Expected Links under Agents section is ---NOT---matching with Actual Links. Expected Links available under Agents Section:<b>" + expected + "</b>.");
					data.append("Available Links available under Agents Section:" + list + ";");
					reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Agents Link. Expected:" + expected + ". Actual links:" + list, "");
				}

				for (int i = 0; i < links.size(); i++) {
					links = driver.findElements(By.xpath("//ul[@class='navSecondary']/li/a[text()='Agents']/following-sibling::ul/li/a"));
					links.get(i).click();
					waitForPageLoad(driver);

					String url2 = driver.getCurrentUrl();
					links = driver.findElements(By.xpath("//ul[@class='navSecondary']/li/a[text()='Agents']/following-sibling::ul/li/a"));
					String tmpUrl = null;
					try {

						if (links.get(i).getText().contains("Premium Placement ZIP codes")) {
							tmpUrl = expectedURL + "real-estate-search-advertising/";
						} else if (links.get(i).getText().contains("Workflow Management")) {
							tmpUrl = expectedURL + "agent-workstation/";
						} else if (links.get(i).getText().contains("EQ Certification")) {
							tmpUrl = expectedURL + "agent-certification/";
						}

					} catch (Exception e) {

					}

					if (!url2.equals(tmpUrl)) {
						dflag = false;
						data.append("On Clicking link " + links.get(i).getText() + ", it is ---NOT--- navigated to " + tmpUrl + ". But navigated to :" + url2 + ";");
					}

					String source2 = driver.getPageSource();

					if (source2.contains("404 - File or directory not found.") || source2.contains("Server Error")) {
						dflag = false;
						data.append(url2 + " contains Error in the page.;");

					}

				}

			} else {

				dflag = false;
				data.append("On clicking Solutions Agents Link, Navigated to URL " + actualURL + ". But Expected URL: " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Agents Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);

			}

			driver.get(url);
			waitForPageLoad(driver);

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + driver.getCurrentUrl() + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + driver.getCurrentUrl() + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Solutions Agents Link*" + status + "*" + data);

	}

	public void verifySolutionVendors(String url) throws Exception {

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		String actualURL = null;
		String expectedURL = null;

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/solutions/vendors/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/solutions/vendors/";
		}

		try {

			safeClick(driver, linkSolution);
			waitForPageLoad(driver);
			safeClick(driver, linkVendors);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.contains(expectedURL)) {

				data.append("On clicking Solutions Vendors Link, Navigated to URL " + actualURL + " successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Vendors Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);
				String source = driver.getPageSource();

				if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
					dflag = false;
					data.append(actualURL + " contains Error in the page.;");

				}

				if (driver.findElement(By.className("pageTitle")).getText().trim().contains("Real Estate Vendors")) {

				} else {
					dflag = false;
					data.append("Page Title is -NOT- equal to Real Estate Vendors. Actual Title displayed:" + driver.findElement(By.className("pageTitle")).getText().trim() + ".;");
				}

			} else {

				dflag = false;
				data.append("On clicking Solutions Vendors Link, Navigated to URL " + actualURL + " .But Expected URL: " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Vendors Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);

			}

			driver.get(url);
			waitForPageLoad(driver);

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + driver.getCurrentUrl() + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + driver.getCurrentUrl() + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Solutions Vendors Link*" + status + "*" + data);

	}

	public void verifySolutionConsumers(String url) throws Exception {

		boolean dflag = true;
		StringBuilder data = new StringBuilder();
		String actualURL = null;
		String expectedURL = null;

		if (ConsumerSite_Baseclass.environment.toLowerCase().contains("prod")) {
			expectedURL = "https://corporate.site.com/solutions/home-buyers/";
		} else {
			expectedURL = "https://" + ConsumerSite_Baseclass.environment.toLowerCase() + "corporate.site.com/solutions/home-buyers/";
		}

		try {

			safeClick(driver, linkSolution);
			waitForPageLoad(driver);
			safeClick(driver, linkConsumers);
			waitForPageLoad(driver);

			actualURL = driver.getCurrentUrl();

			if (actualURL.contains(expectedURL)) {

				data.append("On clicking Solutions Consumers Link, Navigated to URL " + actualURL + " successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Consumers Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);
				String source = driver.getPageSource();

				if (source.contains("404 - File or directory not found.") || source.contains("Server Error")) {
					dflag = false;
					data.append(actualURL + " contains Error in the page.;");

				}

				if (driver.findElement(By.className("pageTitle")).getText().trim().contains("Home Buyers and Sellers")) {
					data.append("Verified the PageTitle <b>Home Buyers and Sellers</b>.;");
				} else {
					dflag = false;
					data.append("Page Title is -NOT- equal to Home Buyers and Sellers. Actual Title displayed:" + driver.findElement(By.className("pageTitle")).getText().trim() + ".;");
				}

			} else {

				dflag = false;
				data.append("On clicking Solutions Consumers Link, Navigated to URL " + actualURL + ". But Expected URL: " + expectedURL + ".;");
				reporter("Fail", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Consumers Link. Navigated to:" + actualURL, ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Verify Solutions Link. Navigated to:" + actualURL);

			}

			driver.get(url);
			waitForPageLoad(driver);

		} catch (Exception e) {
			dflag = false;
			data.append("Exception happened in Page " + driver.getCurrentUrl() + ".;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + driver.getCurrentUrl() + " in Consumer Site", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Exception happened in " + actualURL + " in Consumer Site");
		}

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Verify Solutions Consumers Link*" + status + "*" + data);

	}
}
