package com.qa.opencart.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.utilssection.ExcelUtils;

public class regestrationpageTest extends BaseTest{

	@BeforeClass
	public void regestratioSetUp() {
		regestrationpage =lp.navigateToRegestrationPage();
	}

	@DataProvider
	public void getData() {
	
	}
	
	@Test
	public void regestrationTest() {
		regestrationpage.fillRegestrationform("shiven","nhavi"," nhavi45@gmail.com", "5896932568", "yuri@455", "Yes");
	}
}
