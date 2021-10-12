package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.utilssection.ConstatantsClass;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accpage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accPageTitleTest() {
		String title = accpage.getAccPageTitle();
		System.out.println("Acc page title is : " + title);
		Assert.assertEquals(title, ConstatantsClass.Accounts_Page_title);
	}

	@Test
	public void accPageLogoutLinkTest() {
		Assert.assertTrue(accpage.isLogoutLinkExist());
	}

	@Test
	public void accPageSearchTest() {
		Assert.assertTrue(accpage.isSeachFieldExist());
	}
	
	@Test
	public void accPageSecHeaderTest() {
		List<String> actSecList = accpage.getAccountSecList();
		System.out.println(actSecList);
		Assert.assertEquals(actSecList, ConstatantsClass.exp_AccountHeaders);
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"macbook"},
			{"iMac"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resultpage = accpage.doSearch(productName);
		
	}
	

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultpage = accpage.doSearch(productName);
		resultpage.selectProduct(mainProductName);
	}
	
	
	
	
}