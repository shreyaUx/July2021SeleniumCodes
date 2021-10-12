package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.utilssection.ConstatantsClass;
import com.qa.utilssection.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eu;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
    private By productMetaData = By.xpath("(//div[@id=\"content\"]//ul[@class=\"list-unstyled\"])[1]/li");
    private By productPriceMetaData = By.xpath("(//div[@id=\"content\"]//ul[@class=\"list-unstyled\"])[2]/li");
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eu= new ElementUtil(driver);
	}
	
	public int addQuantityProduct(int count) {
	return	eu.doSendKeys(quantity, count);
	}
	
	public String getProductHeaderText() {
		return eu.doGetText(productHeader);
	}
	
	public int getProductImagesCount() {
		return eu.waitForElementsVisible(productImages, ConstatantsClass.Default_TimeOut).size();
	}
	
	public Map<String, String> getProdMetaData() {
		Map<String,String>prodMap = new HashMap<String ,String>();
		String productName=eu.doGetText(productHeader);
		prodMap.put("productname", productName);
		getProdData(prodMap);
		getProdpriceData(prodMap);
		return prodMap;
	}

	private void getProdData(Map<String,String>prodMap) {
		List<WebElement>metaList =eu.getElements(productMetaData);
     for(WebElement e:metaList) {
    	String metatext=e.getText();
    	String metaKey =metatext.split(":")[0].trim();
    	String metaName=metatext.split(":")[1].trim();
    	prodMap.put(metaKey, metaName);
    	
     }}
	
     private void getProdpriceData(Map<String,String>prodMap) {
 		List<WebElement>priceList =eu.getElements(productPriceMetaData);
       String actualprice = priceList.get(0).getText().trim();
       String exTaxprice = priceList.get(1).getText().trim();//Ex Tax: $2,000.00
    	prodMap.put("price", actualprice);
    	prodMap.put("exTaxPrice", exTaxprice.split(":")[1].trim());
     	
      }
 	
	
}