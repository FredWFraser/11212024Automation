package AutomatedProject.AutomatedProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomatedProject.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory!

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartaccess;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By goToCart = By.cssSelector("[routerlink*='cart']");

	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);

		return products;
	}

	public WebElement getProductByName(List<WebElement> products, String expectedResult) {

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(expectedResult))
				.findFirst().orElse(null);
		return prod;

	}

	public void addProductToCart(List<WebElement> products, String expectedResult) throws InterruptedException {

		WebElement prod = getProductByName(products, expectedResult);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		waitForElementToAppear(goToCart);
		waitForElementToBeClickable(goToCart);
		
	}

}
