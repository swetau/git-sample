package com.presta.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class SummaryPage {

	public WebDriver driver = Configuration.browser();
	ReadExcel read;
    
	@FindBy(id="shopping_cart")
	public WebElement cart;
	
	
	
	@FindBy(xpath="(//a[@title='Add to cart'])[1]")
	public WebElement addto;
	
	
	@FindBy(id ="cart_title")
	public WebElement title;
	
	
	@FindBy(xpath ="//a[@id='2_2_0_40']")
	public WebElement del;
	
	
	
	@FindBy(xpath ="//a//span[text()='(empty)']")
	public WebElement emt;
	
	@FindBy(xpath ="//a[@title='Next']")
	public WebElement nxt;
	
	@FindBy(name ="processAddress")
	public WebElement nxtadd;
	
	@FindBy(id ="cgv")
	public WebElement chk;
	
	
	@FindBy(name ="processCarrier")
	public WebElement nxtship;
	
	
	@FindBy(xpath ="//a[@title='Pay by cheque']")
	public WebElement paychk;
	
	@FindBy(name ="submit")
	public WebElement sub;
	
	public SummaryPage() {
		PageFactory.initElements(driver, this);
	    read = new ReadExcel();
	}
	
public void addtoCart() throws Exception {
		
		addto.click();
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); 
Thread.sleep(4000);		

}

	
	public void cartSummary()
	{
		
		cart.click();
		
		Assert.assertEquals(title.getText(), read.readData("Shoppingcartsummary"));
		
	}
	public void nextBtn()
	{
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		nxt.click();
		
			}
	
	public void deleteItem() throws Exception
	{
		del.click();
		Thread.sleep(3000);
		Assert.assertEquals("Cart: "+emt.getText(),read.readData("CartEmpty"));
		
		
		
	}
	

	public void cartAddress()
	{
		
		nxtadd.click();
		
	}
	
	public void cartShipping()
	{
		chk.click();
		nxtship.click();
		
	}
	
	public void cartPayment()
	{
		paychk.click();
		sub.click();
		
	}
}
