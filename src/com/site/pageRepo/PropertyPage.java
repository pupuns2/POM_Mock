package com.site.pageRepo;

import static com.site.tests.Utilities.CommonFunctions.*;
import static com.site.tests.testcases.baseTest.defaultTimeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.site.tests.Utilities.LoadConfigFile;
import com.site.tests.testcases.ConsumerSite;
import com.site.tests.testcases.ConsumerSite_Baseclass;

public class ConsumerSite_PropertyPage {

	public ConsumerSite_PropertyPage(WebDriver ldriver) {
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = ".//*[@id='photos']/div/div[2]/div[2]/div/div[3]/div[4]/button")
	public WebElement btnContactListingAgent;
	
	@FindBy(xpath = "//div[@class='contactAgentDetails']")
	public List<WebElement> sectionContactListingAgent;

	@FindBy(xpath = "//table[@class='ulPropertyData']")
	public List<WebElement> labelAddressArea;

	@FindBy(xpath = "//h1[@class='propertyAddressHeader']")
	public List<WebElement> labelPropertyHeader;

	@FindBy(xpath = "//div[@id='detailsFooterText']")
	public List<WebElement> labelFooterSection;
	
	
	@FindBy(xpath = "//a[contains(text(),'Back To Results')]")
	public List<WebElement> btnBackToResults;
	
	
	
	@FindBy(xpath = "//div[@id='viewletContactListingAgent-header']")
	public WebElement txtContactListingAgentHeader;

	@FindBy(xpath = "//div[@id='viewletContactListingAgent-header']")
	List<WebElement> txtContactListingAgentHeaderSize;

	@FindBy(xpath = "//select[@name='selContactType']")
	public WebElement ddAgentReason;

	@FindBy(xpath = "//button[text()='Send']")
	public WebElement btnAgentSend;

	@FindBy(xpath = "//button[text()='Send']/preceding-sibling::button[text()='Cancel']")
	public WebElement btnAgentCancel;
	
	@FindBy(xpath = "//button[@class='close']")
	public WebElement btnAgentSentMailClose;
	

	@FindBy(xpath = "//label[contains(text(),'Name')]/following-sibling::input")
	public WebElement txtName;

	@FindBy(xpath = "//label[contains(text(),'Email')]/following-sibling::input[@name='email']")
	public WebElement txtEmail;

	@FindBy(xpath = "//label[contains(text(),'Phone')]/following-sibling::input[@name='phone']")
	public WebElement txtPhone;

	@FindBy(id = "searchStringDesktop")
	public WebElement txtSearchBox;

	@FindBy(name = "search")
	public WebElement btnSearch;

	@FindBy(xpath = "//div[@class='listResults listViewScroll']/ul[1]/li[2]/a")
	public WebElement linkFirstSearchRecord;
	
	@FindBy(xpath = "//a[contains(text(),'Back')]")
	public WebElement linkBackToHome;
	
	@FindBy(xpath = "//a[text()='Map']")
	public WebElement linkMap;
	
	
	@FindBy(xpath = "//div[text()='Satellite']")
	public WebElement linkSatellite;
	
	
	
	

	public void verifyPropertyDetailsPage(String url) throws Exception {

		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");
		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeType(driver, txtSearchBox, txtToBeSearched);
			safeClick(driver, btnSearch);
			/*
			 * txtSearchBox.clear(); txtSearchBox.sendKeys(txtToBeSearched);
			 * btnSearch.click();
			 */
			waitForPageLoad(driver);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", linkFirstSearchRecord);
			waitForPageLoad(driver);
			
			
			boolean dflag = true;
			StringBuilder data = new StringBuilder();
			// String
			// address=driver.findElement(By.xpath("h1[@class='propertyAddressHeader']")).getText();
			String address = safeGetTextBy(driver, By.xpath("//*[@class='propertyAddressHeader']"));
			data.append("Property Used:<b>"+address+"</b>.;");
		
			
			
			if(btnBackToResults.size()>0){
				data.append("<b>Back to Results</b> button is available in Property Details page.;");
				
			}else{
				dflag=false;
				data.append("Back to Results button is ---NOT---available in Property Details page.;");
			}
			

			if(labelFooterSection.size()>0){
				data.append("<b>Footer Section</b> is available in Property Details page.;");
				
			}else{
				dflag=false;
				data.append("Footer Section is ---NOT---available in Property Details page.;");
			}
			
			
			if(labelAddressArea.size()>0){
				data.append("<b>Property Data section</b> is available in Property Details page.;");
				
			}else{
				dflag=false;
				data.append("Property Data section is ---NOT---available in Property Details page.;");
			}
			
			
			
			if(labelPropertyHeader.size()>0){
				data.append("<b>Property Header section</b> is available in Property Details page.;");
				
			}else{
				dflag=false;
				data.append("Property Header section is ---NOT---available in Property Details page.;");
			}
			
			
			if(sectionContactListingAgent.size()>0){
				data.append("<b>Contact Listing Agent section</b> is available in Property Details page.;");
				
			}else{
				dflag=false;
				data.append("Contact Listing Agent section section is ---NOT---available in Property Details page.;");
			}
			
					
			
			
			
			
			
			

		

			if (dflag) {

				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Details Page*Passed*"+data);

				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Navigated to Property Details Page successfully", "");

			} else {
				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Property Details Page*Failed*"+data);

				reporter("FAIL", "Navigation to Property Details Page failed", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Navigation to Property Details Page failed");
			}

		} catch (Exception e) {
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Navigation to Property Details Page failed", "");
		}

	}

	public void verifyContactListingAgent() throws InterruptedException {

		// btnContactListingAgent.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnContactListingAgent);

		waitForPageLoad(driver);
		boolean dflag = true;
		StringBuilder data = new StringBuilder();

		
		//Verify Header
		try {

			if (txtContactListingAgentHeader.getText().equals("Ask a Question or Make an Offer")) {
				data.append("Text <b>\"Ask a Question or Make an Offer\"</b> is verified successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Text \"Ask a Question or Make an Offer\" is displayed in \"Contact Listing Agent\" page header", "");
			} else {
				dflag = false;
				data.append("Text <b>\"Ask a Question or Make an Offer\"</b> is ---NOT--- verified successfully.;");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Text \"Ask a Question or Make an Offer\" is not displayed in \"Contact Listing Agent\" page header", "");
			}
		} catch (Exception e) {

		}
		
		
		
		
		//Verify Dropdown option

		try {
			String[] options = returnDropDownOptions(ddAgentReason);

			boolean flag = false;
			for (String st : options) {
				if (st.contains("Property Inquiry") || st.contains("Request Showing") || st.contains("Listing Dispute")) {
					flag = true;
				} else {
					flag = false;
				}

			}

			if (flag) {

				data.append("<b>Reason</b> Dropdown Options are verified successfully.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Verified \"Reason\" Dropdown options in Contact listing Agent page", "");
			} else {

				dflag = false;
				data.append("<b>Reason</b> Dropdown Options are ---NOT--- verified successfully.;");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Issue in the options displayed in \"Reason\" Dropdown options in Contact listing Agent page", "");
			}
		} catch (Exception e) {
			dflag = false;
			data.append("Reason Dropdown Options are ---NOT--- verified successfully.;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Issue in the options displayed in \"Reason\" Dropdown options in Contact listing Agent page", "");
		}
		
		
		
		
		//Verify WebElements in Contact Listing Page

		try {

			if (btnAgentSend.isDisplayed() && btnAgentCancel.isDisplayed() && txtName.isDisplayed() && txtEmail.isDisplayed() && txtPhone.isDisplayed()) {

				data.append("Verified WebElements in the page successfully.;");

				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Verified all the WebElements in Contact Agent Listing Page", "");

			} else {
				dflag = false;
				data.append("One of the WebElements is not displayed in the Contact Agent Listing.;");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Unable to Verify the WebElements in Contact Agent Listing Page", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Unable to Verify the WebElements in Contact Agent Listing Page");
			}

		} catch (Exception e) {
			dflag = false;
			data.append("One of the WebElements is not displayed in the Contact Agent Listing page.;");
			reporter("FAIL", "Unable to Verify the WebElements in Contact Agent Listing Page", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Unable to Verify the WebElements in Contact Agent Listing Page");
		}
		
		
		
		//Verify Cancel Functionality

		try {

			safeClick(driver, btnAgentCancel);
			// btnAgentCancel.click();
			waitForPageLoad(driver);
			if (txtContactListingAgentHeaderSize.size() == 0) {

				data.append("<b>Cancel</b> button is working as expected.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Cancel button click closed the Contact Listing Agent page", "");

			} else {

				dflag = false;
				data.append("<b>Cancel</b> button is ---NOT--- working as expected.;");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Contact Listing Agent page is open after clicking the cancel button ", "");

			}
		} catch (Exception e) {

			dflag = false;
			data.append("Cancel button is ---NOT--- working as expected.;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Contact Listing Agent page is open after clicking the cancel button ", "");

		}

				
		
		//Verify Send Email Functionality
		try {	
			
			js.executeScript("arguments[0].click();", btnContactListingAgent);	
			safeType(driver, txtName, "TestUs");
			safeType(driver, txtEmail, "Test@gmail.com");
			safeType(driver, txtPhone, "424-444-4444");
			js.executeScript("arguments[0].click();", btnAgentSend);
			//safeClick(driver, btnAgentSend);
			Thread.sleep(5000);
			String msg=safeGetTextBy(driver, By.xpath("//div[contains(@class,'contactAgentConfirmationMessage')]/p"));
			
			System.out.println("MSG:"+msg);
			if (msg.contains("Your information and message has been sent to the Listing Agent.")) {

				data.append("Mail Sent to Agent Functionality is working as expected.;");
				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Mail Sent to Agent Functionality is working as expected", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Mail Sent to Agent Functionality is working as expected");

			} else {

				dflag = false;
				data.append("Mail is ---NOT--- Sent to Agent Functionality as expected.;");
				reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Mail is not Sent to Agent Functionality as expected", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Mail is not Sent to Agent Functionality as expected");

			}
			
			safeClick(driver, btnAgentSentMailClose);
			Thread.sleep(2000);
			
		} catch (Exception e) {

			dflag = false;
			data.append("Mail is ---NOT--- Sent to Agent Functionality as expected.;");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Mail is not Sent to Agent Functionality as expected", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Mail is not Sent to Agent Functionality as expected");
		
		}
		
		
		//Verify Back to Home Functionality
				try {
				
					safeClick(driver, linkBackToHome);
					waitForPageLoad(driver);
					
					
					if(driver.findElements(By.xpath("//ul[@class='viewletListResultsItem']")).size()>0){
						data.append("On Clicking Back Button, Navigated back to Property Search Results page.;");
						reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Navigated back to Home Page successfully.", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "Navigated back to Home Page successfully.");
				
					}else{
						
						data.append("On Clicking Back Button,---NOT--- Navigated back to Property Search Results page.;");
						reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "NOT Navigated back to Home Page successfully.", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "NOT Navigated back to Home Page successfully.");
				
			
					}
					
					
				} catch (Exception e) {

					dflag = false;
					data.append("On Clicking Back Button,---NOT--- Navigated back to Property Search Results page.;");
					reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "NOT Navigated back to Home Page successfully.", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + "NOT Navigated back to Home Page successfully.");
					
				}
		
		
		

		String status;
		if (dflag) {
			status = "Passed";
		} else {
			status = "Failed";
		}

		ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Contact Listing Agent Page*" + status + "*" + data);

		
		
	}

	public void verifyMapInPropertyDetailsPage(String url) throws Exception {

		String txtToBeSearched = LoadConfigFile.getInstance().getValue("Consumer_City");
		try {
			driver.get(url);
			waitForPageLoad(driver);
			safeType(driver, txtSearchBox, txtToBeSearched);
			safeClick(driver, btnSearch);
			waitForPageLoad(driver);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", linkFirstSearchRecord);
			waitForPageLoad(driver);
			
			
			boolean dflag = true;
			StringBuilder data = new StringBuilder();
			// String
			// address=driver.findElement(By.xpath("h1[@class='propertyAddressHeader']")).getText();
			String address = safeGetTextBy(driver, By.xpath("//*[@class='propertyAddressHeader']"));
			data.append("Property Used:<b>"+address+"</b>.;");
		
			linkMap.click();
			waitForPageLoad(driver);
			
			
			if(driver.findElements(By.xpath("//div[@id='map' and contains(@class,'active')]")).size()>0){
				data.append("In Property Details page, <b>Map</b> is displayed after clicking Map link.;");
			}else{
				data.append("In Property Details page, <b>Map</b> is ---NOT--- displayed after clicking Map link.;");
			}

			if(driver.findElements(By.xpath("//div[@class='gmnoprint']/img")).size()>0){
				data.append("Verified property is displayed in the Map.;");
			}else{
				data.append("Property is ---NOT---displayed in the Map.;");
			}
			
				

			if(driver.findElements(By.xpath("//div[text()='Satellite']")).size()>0){
				data.append("In Property Details page, <b>Satellite View</b> is displayed after clicking Satellite link.;");
			}else{
				data.append("In Property Details page, <b>Satellite View</b> is ---NOT--- displayed after clicking Satellite link.;");
			}
			
			linkSatellite.click();
			waitForPageLoad(driver);
			
			
			if(driver.findElements(By.xpath("//div[@class='gmnoprint']/img")).size()>0){
				data.append("Verified property is displayed in the Satellite View.;");
			}else{
				data.append("Property is ---NOT---displayed in the Satellite View.;");
			}
			
						
			
			if (driver.findElements(By.xpath("//div[text()='Satellite']")).size()>0){
				data.append("In Property Details page, <b>Satellite</b> button is displayed in Map view of the property.;");
			}else{
				data.append("In Property Details page, <b>Satellite</b> button is ---NOT---displayed in Map view of the property.;");
			}
					if (dflag) {

				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Map Functionality in Property Details Page*Passed*"+data);

				reporter("PASS", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Map Functionality in Property Details Page is working successfully.", "");

			} else {
				ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Map Functionality in Property Details Page*Failed*"+data);

				reporter("FAIL", "Map Functionality in Property Details Page is NOT working successfully", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Map Functionality in Property Details Page is NOT working successfully.");
			}

		} catch (Exception e) {
			ConsumerSite_Baseclass.buildMap(ConsumerSite_Baseclass.csUserType + ":Map Functionality in Property Details Page*Failed*Exception happened during testing.");
			reporter("FAIL", ConsumerSite_Baseclass.csUserType + ":" + ConsumerSite_Baseclass.csBrowserType + " Map Functionality in Property Details Page is NOT working.", "");
		}

	}
	
	
	
	
}
