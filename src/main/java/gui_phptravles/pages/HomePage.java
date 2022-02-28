package gui_phptravles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class HomePage {
	private WebDriver driver;

	// Create constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	// Element Locators

	private By SignUp_Button = By.xpath("//a [@class = 'theme-btn theme-btn-small waves-effect']");

	// methods and actions
	public void NavigatetoSignUpPage() {
		ElementActions.click(driver, SignUp_Button);

	}

}
