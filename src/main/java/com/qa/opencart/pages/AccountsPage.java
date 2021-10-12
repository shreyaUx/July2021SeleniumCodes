package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListCellRenderer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.utilssection.ConstatantsClass;
import com.qa.utilssection.ElementUtil;

import bsh.org.objectweb.asm.Constants;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eu;

	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search span");
	private By logoutLink = By.linkText("Logout");
	private By accSecHeaders = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eu= new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		return eu.waitForTitleToBe(ConstatantsClass.Default_TimeOut, ConstatantsClass.Accounts_Page_title);
	}

	public boolean isLogoutLinkExist() {
		return eu.doIsDiplayed(logoutLink);
	}

	public boolean isSeachFieldExist() {
		return eu.doIsDiplayed(search);
	}

	public List<String> getAccountSecList() {
		List<WebElement> secList = eu.getElements(accSecHeaders);
		List<String> secHeaderList = new ArrayList<String>();
		for (WebElement e : secList) {
			secHeaderList.add(e.getText());
		}
		return secHeaderList;
	}
	
	public ResultPage doSearch(String productName) {
		System.out.println("product name: " + productName);
		eu.doSendKeys(search, productName);
		eu.doClick(searchIcon);
		return new ResultPage(driver);
	}
	

}