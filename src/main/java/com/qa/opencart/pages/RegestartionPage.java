package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.utilssection.ConstatantsClass;
import com.qa.utilssection.ElementUtil;

public class RegestartionPage {

	private ElementUtil eu;

	//1.By locator
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	//2.Constructor
	public RegestartionPage(WebDriver driver) {
		eu = new ElementUtil(driver);
	}
	
	
	//3.Actions/methods
	public boolean fillRegestrationform(String firstName, String lastName,
		                        	String email,String telephone,
			                                 String password,String subscribe) {
		
		 rege(firstName,  lastName,  email,telephone,password) ;
		 selectSubscriptionOption(subscribe);
		 selectAgreementAndContinue();
		 return getRegistrationStatus();
	}
	
	private void selectSubscriptionOption(String subscribe) {
		if(subscribe.equalsIgnoreCase("Yes")) {
		eu.doClick(subscribeYes);
		}
		else {
			eu.doClick(subscribeNo);
		}
		
	}
	
	private void selectAgreementAndContinue() {
		eu.doClick(agreeCheckBox);
		eu.doClick(continueButton);
	}
	
	
	private boolean getRegistrationStatus() {
		String mesg = eu.doGetText(sucessMessg);
		if (mesg.contains(ConstatantsClass.REGISTER_SUCCESS_MESSAGE)) {
			eu.doClick(logoutLink);
			eu.doClick(registerLink);
			return true;
		}
		return false;
	}
	
	
	private void rege(String firstName, String lastName, String email,String telephone,String password) {
		eu.doSendKeys(this.firstName, firstName);
		eu.doSendKeys(this.lastName, lastName);
		eu.doSendKeys(this.email, email);
		eu.doSendKeys(this.telephone,telephone);
		eu.doSendKeys(this.password, password);
		eu.doSendKeys(this.confirmpassword, password);
	}
}
