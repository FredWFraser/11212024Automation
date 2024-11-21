package AutomatedProject.AutomatedProject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AutomatedProject.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {

		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement getMessage;

	By thankYouMessage = By.cssSelector(".hero-primary");

	public String finalValidation() {

		waitForElementToAppear(thankYouMessage);
		String finalValidation = getMessage.getText();		
		return finalValidation;

	}

}
