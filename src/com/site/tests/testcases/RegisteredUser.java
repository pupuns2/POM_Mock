package com.site.tests.testcases;

import static com.site.tests.Utilities.CommonFunctions.driver;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.site.pageRepo.ConsumerSite_LandingPage;
import com.site.pageRepo.ConsumerSite_PropertyPage;

public class ConsumerSite_RegisteredUser extends ConsumerSite_Baseclass {
	
	
	@Test(priority=0,alwaysRun=true)
	public void propertySearchWithBlankData(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());		
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyErrorOnSearch(url);
		extent.endTest(test);
		extent.flush();
	}

	
	@Test(priority=1,alwaysRun=true)
	public void propertySearchViaCity(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyCitySearch(url);
	}
	
	@Test(priority=2,alwaysRun=true)
	public void propertySearchViaZip(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);		
		cs.verifyZipCodeSearch(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority=3,alwaysRun=true)
	public void propertySearchViaAddres(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);		
		cs.verifyAddressSearch(url);
	}
	
	@Test(priority=4,alwaysRun=true)
	public void verifyAutoSuggestTextSearh(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);		
		cs.verifyAutoSuggestTextSearch(url);
		extent.endTest(test);
		extent.flush();
	}
	
	
	@Test(priority=5,alwaysRun=true)
	public void verifyCarousel(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);		
		cs.verifyCarousel(url);
		extent.endTest(test);
		extent.flush();
	}

	
	@Test(priority=6,alwaysRun=true)
	public void verifyPropertyDetailsPage(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_PropertyPage cp = new ConsumerSite_PropertyPage(driver);		
		cp.verifyPropertyDetailsPage(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority=7,alwaysRun=true)
	public void verifyContactListingAgentPage(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_PropertyPage cp = new ConsumerSite_PropertyPage(driver);		
		cp.verifyContactListingAgent();
	}

	@Test(priority=8,alwaysRun=true)
	public void verifyTop25MarketCity(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);		
		cs.verifyTop25MarketCity(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority=9,alwaysRun=true)
	public void verifyCompanyLink(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);		
		cs.verifyCompany(url);
		extent.endTest(test);
		extent.flush();
	}
	
	

	@Test(priority=10,alwaysRun=true)
	public void verifySolutionLink(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);			
		cs.verifySolution(url);
		extent.endTest(test);
		extent.flush();
	}
	
	
	@Test(priority=11,alwaysRun=true)
	public void verifyPriceFilter(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);			
		cs.verifyPriceFilter(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority=12,alwaysRun=true)
	public void verifyBedFilter(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);			
		cs.verifyBedFilter(url);
	}
	
	@Test(priority=13,alwaysRun=true)
	public void verifyBathFilter(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);			
		cs.verifyBathFilter(url);
		extent.endTest(test);
		extent.flush();
	}
	
	
	@Test(priority=14,alwaysRun=true)
	public void verifySquareFeetFilter(Method method) throws Exception{
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);			
		cs.verifySquareFeetFilter(url);
		extent.endTest(test);
		extent.flush();
	}
	
	
	@Test(priority = 15, alwaysRun = true)
	public void verifyPropertyTypeDropdownSingleFamilyOption(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyPropertyTypeDropdownSingleFamilyOption(url);
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority = 16, alwaysRun = true)
	public void verifyPropertyTypeDropdownCondoOption(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyPropertyTypeDropdownCondoOption(url);
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority = 17, alwaysRun = true)
	public void verifyPropertyTypeDropdownLandOption(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyPropertyTypeDropdownLandOption(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 18, alwaysRun = true)
	public void verifyPropertyTypeDropdownOtherOption(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyPropertyTypeDropdownOtherOption(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 19, alwaysRun = true)
	public void verifyForeClosureToggle(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyForeClosureToggle(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 20, alwaysRun = true)
	public void verifyShortSaleToggle(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyShortSaleToggle(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 21, alwaysRun = true)
	public void verifyStandardToggle(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyStandardToggle(url);
		extent.endTest(test);
		extent.flush();
	}
	
	
	@Test(priority = 22, alwaysRun = true)
	public void verifySavedSearch(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifySavedSearch(url);
	}
	
	@Test(priority = 23, alwaysRun = true)
	public void verifySavedProperty(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifySavedProperty(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 24, alwaysRun = true)
	public void verifyResetFunctionality(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyResetFunctionality(url);
		extent.endTest(test);
		extent.flush();
	}	
	
	
	@Test(priority = 25, alwaysRun = true)
	public void verifyResetFunctionality2(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyResetFunctionality2(url);
	}
	
	@Test(priority = 26, alwaysRun = true)
	public void verifyMapView(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifyMapView(url);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 27, alwaysRun = true)
	public void verifyMapInPropertyDetailsPage(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_PropertyPage cp = new ConsumerSite_PropertyPage(driver);
		cp.verifyMapInPropertyDetailsPage(url);
	}
	
	
	
	@Test(priority = 28, alwaysRun = true)
	public void verifySolutionServicer(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifySolutionServicer(url);
		extent.endTest(test);
		extent.flush();
	}
	

	@Test(priority = 29, alwaysRun = true)
	public void verifySolutionAgent(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifySolutionAgent(url);
		extent.endTest(test);
		extent.flush();
	}
	

	@Test(priority = 30, alwaysRun = true)
	public void verifySolutionVendors(Method method) throws Exception {
		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifySolutionVendors(url);
		extent.endTest(test);
		extent.flush();
	}
	

	@Test(priority = 31, alwaysRun = true)
	public void verifySolutionConsumers(Method method) throws Exception {

		test = extent.startTest(csUserType+":"+method.getName(), csUserType+":"+method.getName());
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);
		cs.verifySolutionConsumers(url);
		extent.endTest(test);
		extent.flush();
	}
	
	
	@AfterClass
	public void signout(){
		ConsumerSite_LandingPage cs = new ConsumerSite_LandingPage(driver);	
		cs.signout();
		
	}
}
 