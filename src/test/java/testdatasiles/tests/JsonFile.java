package testdatasiles.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.tools.io.JSONFileManager;

public class JsonFile {
	
	WebDriver driver;
	JSONFileManager jsonFileTestReader;
	
	@BeforeClass
	public void beforclass() {
		
		jsonFileTestReader = new JSONFileManager(System.getProperty("testDataFolderPath")+"JsonFile.json");
	}
  @Test
  public void jsonFile() {
	  driver = DriverFactory.getDriver();
	  BrowserActions.navigateToURL(driver, "https://www.google.com.eg/");
	  ElementActions.type(driver, By.name("q"),jsonFileTestReader.getTestData("searchData"));
  }
}
