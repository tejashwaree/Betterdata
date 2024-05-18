package com.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		// PageFactory.initElements(driver, this); //created this to user driver in
		// findby page factory
	}

	public HomePage loginApplication(String email, String pasword) throws InterruptedException {
		driver.findElement(By.cssSelector("#email")).sendKeys(email);
		driver.findElement(By.cssSelector("#password")).sendKeys(pasword);
		driver.findElement(By.cssSelector(".chakra-button")).click();
		HomePage homePage = new HomePage(driver);
		return homePage;
	}

	public void goTo() {
		driver.get(
				"http://af8d2bdc267e54e139a6ccc817f0a8f6-1149838503.ap-southeast-1.elb.amazonaws.com:8080/betterdata/signin");
	}

}
