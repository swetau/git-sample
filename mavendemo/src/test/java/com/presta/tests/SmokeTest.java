package com.presta.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.presta.pages.HomePage;
import com.presta.pages.LoginPage;
import com.presta.pages.SummaryPage;
import com.selenium.commons.Configuration;
import com.selenium.commons.Screenshot;

@Listeners(Screenshot.class)
public class SmokeTest {

	public WebDriver driver = Configuration.browser();
	public LoginPage login;
	public HomePage home;
	public SummaryPage summary;

	public SmokeTest() {
		login = new LoginPage();
		home = new HomePage();
		summary = new SummaryPage();
	}

	@BeforeSuite(alwaysRun = true)
	public void loginToApp() {
		driver.get(Configuration.URL);
		driver.manage().window().maximize();
		home.tapLoginLink();
		login.validateLogin();
		login.LoginToAPP(Configuration.userName, Configuration.password);
		home.validateHomePage();

	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		 driver.quit();
	}

	/*
	 * @AfterMethod(alwaysRun = true) public void navigateToHomePage() {
	 * home.navigateToHome();}
	 */

	@Test(priority = 1, testName = "login_Positive", description = "login_Positive", timeOut = 190000, enabled = true, groups = {
			"smoke", "1" })
	public void alogin_Positive() {
		Assert.assertTrue(false);

	}

	@Test(priority = 2, testName = "login_Negative", description = "login_Negative", timeOut = 190000, enabled = true, groups = {
			"smoke", "2" })
	public void login_Negative() {
		home.logoutLink();
		home.tapLoginLink();
		login.LoginToAPP("asdas", "asfas");
		login.validateErrorMessage();
		login.LoginToAPP(Configuration.userName, Configuration.password);
		home.validateHomePage();

	}

	@Test(priority = 3, testName = "searchProduct", description = "searchProduct", timeOut = 190000, enabled = true, groups = {
			"smoke", "3" })
	public void searchProduct() {
		home.searchProduct("ipod", "ResultsDisplayed", "iPod shuffle");

	}

	@Test(priority = 4, testName = "cartSummary", description = "cartSummary", timeOut = 190000, enabled = true, groups = {
			"smoke", "4" }, dependsOnGroups = { "3" })

	public void cartSummary() throws Exception {

		summary.addtoCart();
		summary.cartSummary();

	}

	@Test(priority = 5, testName = "deleteItem", description = "deleteItem", timeOut = 190000, enabled = false, groups = {
			"smoke", "5" })

	public void deleteItem() throws Exception {

		summary.deleteItem();
		home.navigateToHome();

	}

	@Test(priority = 6, testName = "cartAddress", description = "cartAddress", timeOut = 190000, enabled = true, groups = {
			"smoke", "6" })

	public void cartAddress() {
		summary.nextBtn();
		summary.cartAddress();
	}

	@Test(priority = 7, testName = "cartShipping", description = "cartShipping", timeOut = 190000, enabled = true, groups = {
			"smoke", "7" })

	public void cartShipping() {
		summary.cartShipping();
	}

	@Test(priority = 8, testName = "cartPayment", description = "cartPayment", timeOut = 190000, enabled = true, groups = {
			"smoke", "8" })

	public void cartPayment() {

		summary.cartPayment();
	}

}
