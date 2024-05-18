package com.Test;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.PageObjects.LandingPage;
import com.TestComponents.BaseTest;
import com.PageObjects.HomePage;

public class VerifyDataComparisionTest extends BaseTest {
	String msg = "Uploaded Successfully";
	String result = "[]";

	@Test(groups= {"DataComparision"}, priority = 2)
	public void verifyDataComparision()
			throws InterruptedException, AWTException, IOException, UnsupportedFlavorException {

		HomePage homePage = landingPage.loginApplication("tejashwaree.ajbale@gmail.com", "Teju@1234");
		homePage.selectMenu();
		String message = homePage.addDataSource();
		Assert.assertEquals(msg, message);
		String uploadedfile = homePage.checkFile();
		String actualFile = homePage.selectFile();
		Assert.assertEquals(actualFile, uploadedfile);
		Thread.sleep(2000);
		homePage.downloadFile();
		Assert.assertTrue(homePage.compareFiles(), "Both data files do not contain same data");
	}
}
