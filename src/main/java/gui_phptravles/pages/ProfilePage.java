package gui_phptravles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

public class ProfilePage {
	private WebDriver driver;

	// Create constructor
	public ProfilePage(WebDriver driver) {
		this.driver = driver;

	}

	// Element Locators

	// methods and actions
	public String getcurrenturl() {
		return BrowserActions.getCurrentURL(driver);
	}



}
