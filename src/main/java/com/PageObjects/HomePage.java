package com.PageObjects;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.AbstractComponents.AbstractComponents;

public class HomePage extends AbstractComponents {
	WebDriver driver;
	String file;
	String url;
	String window;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // created this to user driver in findby page factory
	}

	@FindBy(xpath = "//p[contains(text(),'Data Warehouse')]")
	WebElement dataWarehouse;

	@FindBy(xpath = "//button[normalize-space()='Add data source']")
	WebElement addDataSource;

	By addSource = By.xpath("//button[normalize-space()='Add data source']");

	@FindBy(xpath = "(//span[@type='radio'])[1]")
	WebElement readyData;
	By readyD = By.xpath("(//span[@type='radio'])[1]");

	@FindBy(xpath = "//*[text()='Upload own']")
	WebElement upload;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement next;

	@FindBy(xpath = " (//button[@type='submit'])[1]")
	WebElement submit;

	@FindBy(xpath = "(//*[@name='description'] )[2]")
	WebElement description;
	By desc = By.xpath("(//*[@name='description'] )[2]");

	@FindBy(xpath = "//div[@class='chakra-portal']/div/div/section/button")
	WebElement close;

	@FindBy(xpath = "//p[contains(text(),'Uploaded Successfully')]")
	WebElement message;

	@FindBy(xpath = "(//div[@class='chakra-stack css-1igwmid']/p)[1]")
	WebElement dataFile;

	@FindBy(xpath = "//tbody/tr[1]/td[7]/div[1]/button[1]")
	WebElement dataProfile;

	@FindBy(xpath = "//button[@aria-controls='menu-list-:rh:']//*[name()='svg']")
	WebElement options;

	@FindBy(xpath = "(//button[@id='menu-list-:rh:-menuitem-:ri:'])")
	WebElement download;

	@FindBy(css = ".chakra-input.css-6uu6dj")
	WebElement link;
	By link1 = By.cssSelector(".chakra-input.css-6uu6dj");

	public void selectMenu() {
		dataWarehouse.click();
		window = driver.getWindowHandle();

	}

	public String addDataSource() throws AWTException, InterruptedException {
		waitForElementToAppear(addSource);
		addDataSource.click();
		waitForElementToAppear(readyD);
		readyData.click();
		upload.click();
		Thread.sleep(2000);
		file = selectFile();
		next.click();
		Thread.sleep(2000);
		waitForElementToAppear(desc);
		description.sendKeys("Plans data");
		Thread.sleep(2000);
		submit.click();
		String msg = message.getText();
		Actions action = new Actions(driver);
		action.click(close).build().perform();
		return msg;
	}

	public String checkFile() {
		String fileName = dataFile.getText();
		return fileName;
	}

	public void downloadFile() throws InterruptedException, AWTException {
		Actions action = new Actions(driver);
		action.click(close).build().perform();
		driver.navigate().refresh();
		options.click();
		Thread.sleep(1000);
		download.click();
		Thread.sleep(1000);
		// copy link of file
		action.click(link).doubleClick().perform();
		action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
		// Opens a new Tab and switches to new Tab
		driver.switchTo().newWindow(WindowType.TAB);
		CopyAndPaste();
		Thread.sleep(4000);
	}

	public boolean compareFiles() throws IOException, AWTException, UnsupportedFlavorException, InterruptedException {
		// copy the file link to clipboard
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String x = (String) contents.getTransferData(DataFlavor.stringFlavor);// link

		String filename = x.split("//")[2].split("/")[2].split("\\?")[0]; // extract the data file name
		Thread.sleep(4000);
		// Compare the datafiles
		File file1 = new File(System.getProperty("user.dir") + "\\Download\\" + filename); //downloaded file
		File file2 = new File(System.getProperty("user.dir") + "\\Files\\Data.csv"); //original file
		boolean difference = FileUtils.contentEquals(file1, file2);
		return difference;
	}
}
