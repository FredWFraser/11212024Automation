package AutomatedProject.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AutomatedProject.AutomatedProject.pageobjects.CardInfo;
import AutomatedProject.AutomatedProject.pageobjects.ConfirmationPage;
import AutomatedProject.AutomatedProject.pageobjects.LandingPage;
import AutomatedProject.AutomatedProject.pageobjects.MyCart;
import AutomatedProject.AutomatedProject.pageobjects.ProductCatalogue;
import AutomatedProject.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	String expectedValidation = "THANKYOU FOR THE ORDER.";

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);

	}

	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(products, productName);
	}

	@When("^Checkout (.+) and submit the order and message is displayed on confirmation page$")
	public void checkout_submit_order_and_message_is_displayed(String productName) {

		MyCart myCart = productCatalogue.goToCartPage();
		Assert.assertEquals(myCart.getMyCartText(), productName);
		myCart.buyNow();
		CardInfo cardInfo = new CardInfo(driver);
		confirmationPage = cardInfo.enterCardInfo("4343434343434343", "123", "Fred Fraser", "United");
		cardInfo.submitOrder();
		Assert.assertEquals(confirmationPage.finalValidation(), expectedValidation);
		driver.close();

	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String strArg1) throws Throwable {
		
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}

}
