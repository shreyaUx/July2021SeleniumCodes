package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.utilssection.ElementUtil;

public class LoginPage {
	/**
	 * will pass driver in loginpage from
	 */
	private WebDriver driver;
	private ElementUtil eu;
	
	//1.By Locators
	private By emid = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink =By.linkText("Forgotten Password");
	private By regeLink= By.linkText("Register");
	
	
	//2.Page Contructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		 eu = new ElementUtil(driver);
	}
	
	//3.page actions/METHOD/FEATURE and this will be called by TestNG CLASS
	
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean isForgotPwdLinkExist() {
		return eu.doIsDiplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String un,String pwd) {//returntype classname:Accountspage
		eu.doSendKeys(emid, un);
		eu.doSendKeys(password, pwd);
		eu.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	public RegestartionPage navigateToRegestrationPage() {
		eu.doClick(regeLink);
		return new RegestartionPage(driver);
	}
}
