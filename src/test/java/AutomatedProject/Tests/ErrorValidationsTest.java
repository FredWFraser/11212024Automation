package AutomatedProject.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import AutomatedProject.AutomatedProject.pageobjects.MyCart;
import AutomatedProject.AutomatedProject.pageobjects.ProductCatalogue;
import AutomatedProject.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("ffiii1@aol.com", "Esther!0");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String expectedResult = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("fredfrasermusic@gmail.com", "Evalyne!00");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(products, expectedResult);
		MyCart myCart = productCatalogue.goToCartPage();
		Assert.assertNotEquals(myCart.getMyCartText(), "ADIDAS UN-ORIGINAL");

	}

}
