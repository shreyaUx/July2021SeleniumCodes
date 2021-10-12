package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.utilssection.ConstatantsClass;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String title = lp.getTitle();
		Assert.assertEquals(title, ConstatantsClass.Login_Page_Title);
	}

	@Test
	public void loginPageUrlTest() {
		String url = lp.getLoginPageUrl();
		System.out.println("ac page urlis: " + url);
		Assert.assertTrue(url.contains(ConstatantsClass.Login_Page_URL));
	}

	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(lp.isForgotPwdLinkExist());

	}

	

	@Test
	public void loginPageTest() {
		lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
}
