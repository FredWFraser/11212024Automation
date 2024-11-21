package AutomatedProject.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import AutomatedProject.AutomatedProject.pageobjects.CardInfo;
import AutomatedProject.AutomatedProject.pageobjects.ConfirmationPage;
import AutomatedProject.AutomatedProject.pageobjects.MyCart;
import AutomatedProject.AutomatedProject.pageobjects.OrderPage;
import AutomatedProject.AutomatedProject.pageobjects.ProductCatalogue;
import AutomatedProject.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String expectedResult = "ADIDAS ORIGINAL";

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		String expectedValidation = "THANKYOU FOR THE ORDER.";
		ProductCatalogue productCatalogue = landingPage.loginApplication("ffiii1@aol.com", "Esther!00");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(products, expectedResult);
		MyCart myCart = productCatalogue.goToCartPage();
		Assert.assertEquals(myCart.getMyCartText(), expectedResult);
		myCart.buyNow();
		CardInfo cardInfo = new CardInfo(driver);
		ConfirmationPage confirmationPage = cardInfo.enterCardInfo("4343434343434343", "123", "Fred Fraser", "United");
		cardInfo.submitOrder();
		Assert.assertEquals(confirmationPage.finalValidation(), expectedValidation);

	}
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() throws InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("ffiii1@aol.com", "Esther!00");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(expectedResult));
		
	}

}
