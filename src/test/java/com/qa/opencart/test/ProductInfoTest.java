package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.utilssection.ConstatantsClass;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accpage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void productHeaderTest() {
		resultpage = accpage.doSearch("macbook");
		productinfopage = resultpage.selectProduct("MacBook Pro");
		String actHeader = productinfopage.getProductHeaderText();

		Assert.assertEquals(actHeader, "MacBook Pro");
	}

	@DataProvider
	public Object[][] getImageData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 } };
	}

	@Test(dataProvider = "getImageData")
	public void productImageCountTest(String productName, String mainProductName, int imageCount) {
		resultpage = accpage.doSearch(productName);
		productinfopage = resultpage.selectProduct(mainProductName);
		Assert.assertEquals(productinfopage.getProductImagesCount(), imageCount);
	}

	@Test
	public void productMetatadataTest() {
		resultpage = accpage.doSearch("macbook");
		productinfopage = resultpage.selectProduct("MacBook Pro");
		Map<String, String> actualproductMap = productinfopage.getProdMetaData();
		actualproductMap.forEach((k, v) -> System.out.println(k + ":" + v));
		softassert.assertEquals(actualproductMap.get("productname"), "MacBook Pro");
		softassert.assertEquals(actualproductMap.get("Brand"), "Apple");
		softassert.assertEquals(actualproductMap.get("Product Code"), "Product 18");
		softassert.assertEquals(actualproductMap.get("Price"), "$2,000.00");
		softassert.assertAll();
	}

}
