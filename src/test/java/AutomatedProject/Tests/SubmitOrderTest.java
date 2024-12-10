package AutomatedProject.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomatedProject.AutomatedProject.pageobjects.CardInfo;
import AutomatedProject.AutomatedProject.pageobjects.ConfirmationPage;
import AutomatedProject.AutomatedProject.pageobjects.MyCart;
import AutomatedProject.AutomatedProject.pageobjects.OrderPage;
import AutomatedProject.AutomatedProject.pageobjects.ProductCatalogue;
import AutomatedProject.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	// comment for test 12/10/2024
	
	String expectedResult = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		String expectedValidation = "THANKYOU FOR THE ORDER.";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
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
	
	
	@Test(dataProvider="getData",dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest(HashMap<String, String> input) throws InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(expectedResult));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//AutomatedProject//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}};
	}

}
