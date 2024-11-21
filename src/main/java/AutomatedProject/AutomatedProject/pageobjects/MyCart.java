package AutomatedProject.AutomatedProject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AutomatedProject.AbstractComponents.AbstractComponent;

public class MyCart extends AbstractComponent {

	String expectedResult = "ADIDAS ORIGINAL";

	WebDriver driver;

	public MyCart(WebDriver driver) {

		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	WebElement getActualText;

	@FindBy(xpath = "//button[normalize-space()='Buy Now']")
	WebElement checkout;

	By getText = By.cssSelector(".cartSection h3");

	public String getMyCartText() {

		waitForElementToAppear(getText);
		String actualText = getActualText.getText();
		return actualText;

	}

	public void buyNow() {

		checkout.click();

	}

}
