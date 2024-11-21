package AutomatedProject.AutomatedProject.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomatedProject.AbstractComponents.AbstractComponent;

public class CardInfo extends AbstractComponent {

	WebDriver driver;

	public CardInfo(WebDriver driver) {

		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory!

	@FindBy(xpath = "(//input[@type='text'])[1]")
	WebElement sendCardNo;

	@FindBy(xpath = "(//select[@class='input ddl'])[1]")
	WebElement expiryMonth;

	@FindBy(xpath = "(//select[@class='input ddl'])[2]")
	WebElement expiryYear;

	@FindBy(xpath = "(//input[@type='text'])[2]")
	WebElement cvv;

	@FindBy(xpath = "(//input[@type='text'])[3]")
	WebElement nameOnCard;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement searchForUnited;

	@FindBy(css = ".ta-item")
	List<WebElement> findCountries;

	@FindBy(css = "a[class*='action__submit']")
	WebElement submit;

	By countryList = By.cssSelector(".ta-item");
	By submitButton = By.cssSelector("a[class*='action__submit']");

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	public ConfirmationPage enterCardInfo(String cardNo, String cvvValue, String name, String country) {

		sendCardNo.sendKeys(cardNo);
		Select select = new Select(expiryMonth);
		select.selectByIndex(3);
		Select select2 = new Select(expiryYear);
		select2.selectByIndex(24);
		cvv.sendKeys(cvvValue);
		nameOnCard.sendKeys(name);
		searchForUnited.sendKeys(country);
		waitForElementToAppear(countryList);

		List<WebElement> options = findCountries;

		for (WebElement option : options) {

			if (option.getText().equals("United States")) {

				option.click();
				break;

			}

		}
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

	public void submitOrder() {

		waitForElementToAppear(submitButton);
		submit.click();

	}

}
