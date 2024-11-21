package AutomatedProject.AutomatedProject.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomatedProject.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	String expectedResult = "ADIDAS ORIGINAL";

	public OrderPage(WebDriver driver) {

		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;

	By getText = By.cssSelector(".cartSection h3");

	public Boolean VerifyOrderDisplay(String productName) throws InterruptedException {

		Thread.sleep(Duration.ofSeconds(3));

		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
