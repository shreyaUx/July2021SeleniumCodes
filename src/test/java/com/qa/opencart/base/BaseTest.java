package com.qa.opencart.base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegestartionPage;
import com.qa.opencart.pages.ResultPage;

public class BaseTest {

	public WebDriver driver;
	public  DriverFactory df;
	public Properties prop;
	public LoginPage lp;
	public AccountsPage accpage;
	public ResultPage resultpage;
	 public ProductInfoPage productinfopage;
	 public RegestartionPage regestrationpage;
	 public SoftAssert softassert;
/**
 * will create object of driverfactory class so  that will acceess init method
 * here inside setUp method we are passsinh hardcode "chrome"so
 *  setup method will return chromedriver
 * and chromedriver will be returning from initdriver method will be storing inside
 *  driver refrence and will be passing
 * to class level Webdriver variable so that will use in whole classs methods
 *IOException 
 */
	@BeforeTest
	public void setUP() throws IOException {
		df = new DriverFactory();
	     prop=df.ininProp();
		driver =df.initDriver(prop);
		softassert=new SoftAssert();
		/**
		 * we created object of loginpage stored refrence at class level
		 * so that inherit variable in logipagetest  class and will use inside loginpagetest for callingmethods
		 * inside obj.of loginpage passing driver because created constructor loginpage class
		 * and this loginpage(driver) this driver pointing towards inidriver refrence
		 * which is already getting ch.romedriver from initmethod
		 */
		 lp = new LoginPage(driver);
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
