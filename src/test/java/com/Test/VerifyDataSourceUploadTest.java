package com.Test;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObjects.HomePage;
import com.PageObjects.LandingPage;
import com.TestComponents.BaseTest;

public class VerifyDataSourceUploadTest extends BaseTest{
	String msg = "Uploaded Successfully";
	
	@Test(groups= {"DataSourceUpload"}, priority = 1)
	public void verifyDataSourceUpload() throws InterruptedException, AWTException {
		
		HomePage homePage = landingPage.loginApplication("tejashwaree.ajbale@gmail.com","Teju@1234");
		homePage.selectMenu();
		String message = homePage.addDataSource();
		Assert.assertEquals(msg, message);
		//get the uploade file name
		String uploadedfile = homePage.checkFile();
		//get the actual file name on webpage
		String actualFile = homePage.selectFile();
		Assert.assertEquals(actualFile, uploadedfile);
		
	}
}
