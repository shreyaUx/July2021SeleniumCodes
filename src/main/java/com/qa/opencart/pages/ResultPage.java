package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.utilssection.ConstatantsClass;
import com.qa.utilssection.ElementUtil;

public class ResultPage {

	private WebDriver driver;
	private ElementUtil eu;
	
	//1.By loactor
	private By searchHeader = By.cssSelector("div#content h1");
	private By productResults = By.cssSelector("div.caption a");

	//2.constructor
	public ResultPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtil(driver);
	}
	
	//3methods/actions
	
	public String serachHeaderName() {
		return eu.doGetText(searchHeader);
	}
	
	public void getSearchProductCount() {
		 eu.waitForElementVisible(productResults,ConstatantsClass.Default_TimeOut).getSize();
	}
	
	public ProductInfoPage selectProduct(String mainproductname) {
		List<WebElement>productlist = eu.waitForElementsVisible(productResults,ConstatantsClass.Default_TimeOut);
	     for(WebElement e: productlist) {
	    	 String text = e.getText();
	    	 if(text.equalsIgnoreCase(mainproductname)) {
	    		 e.click();
	    		 break;
	    	 }
	     }
	    	 return new ProductInfoPage(driver);
	     
	
	}
}
