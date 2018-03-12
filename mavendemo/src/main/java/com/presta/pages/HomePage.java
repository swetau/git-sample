package com.presta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class HomePage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	@FindBy(className = "login")
	public WebElement loginLink;

	@FindBy(className = "logout")
	public WebElement logoutLink;

	@FindBy(id = "search_query_top")
	public WebElement searchTextBox;

	@FindBy(name = "submit_search")
	public WebElement searchBtn;

	@FindBy(xpath = "//h3//span[@class='big']")
	public WebElement searchResult;
	
	@FindBy(className="logo")
	public WebElement logoLink ;
	
	

	public HomePage() {
		PageFactory.initElements(driver, this);
		read = new ReadExcel();
	}

	public void tapLoginLink() {
		loginLink.click();
	}

	
	public void navigateToHome() {
		logoLink.click();
	}
	public void validateHomePage() {
		Assert.assertEquals(driver.getTitle(), read.readData("MyAccount_Title"));

	}

	public void logoutLink() {
		logoutLink.click();

	}

	public void searchProduct(String ProductName, String productResult, String resultProduct) {
		searchTextBox.sendKeys(ProductName);
		searchBtn.click();
		Assert.assertEquals(searchResult.getText(), read.readData(productResult));
		driver.findElement(By.xpath("//h3//a[text()='"+resultProduct+"']")).isDisplayed();

	}
	
	
}
