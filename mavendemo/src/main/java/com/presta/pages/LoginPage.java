package com.presta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class LoginPage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	@FindBy(id = "email")
	public WebElement userNameTextBox;

	@FindBy(id = "passwd")
	public WebElement passwdTextBox;

	@FindBy(id = "SubmitLogin")
	public WebElement loginBtn;
	
	@FindBy(xpath = "//div[@class='error']//li")
	public WebElement errorMessagea;
	
	

	public LoginPage() {
		PageFactory.initElements(driver, this);
		read = new ReadExcel();
	}

	public void validateLogin() {
		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));
	}

	public void LoginToAPP(String userName, String password) {
		userNameTextBox.clear();
		userNameTextBox.sendKeys(userName);
		passwdTextBox.clear();
		passwdTextBox.sendKeys(password);
		loginBtn.click();
	}
	
	public void validateErrorMessage() {
		Assert.assertEquals(errorMessagea.getText(), read.readData("Login_Error"));
	}

}
