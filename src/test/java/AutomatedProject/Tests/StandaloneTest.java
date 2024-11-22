package AutomatedProject.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomatedProject.AutomatedProject.pageobjects.OrderPage;
import AutomatedProject.AutomatedProject.pageobjects.ProductCatalogue;

public class StandaloneTest {
	
	// stand alone test 

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		String expectedResult = "ADIDAS ORIGINAL";
		String expectedValidation = "THANKYOU FOR THE ORDER.";

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ffiii1@aol.com");
		driver.findElement(By.id("userPassword")).sendKeys("Esther!00");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(expectedResult))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));

		String actualText = driver.findElement(By.cssSelector(".cartSection h3")).getText();
		Assert.assertEquals(actualText, expectedResult);

		driver.findElement(By.xpath("//button[normalize-space()='Buy Now']")).click();

		// Enter card info

		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("4343434343434343");

		Select select = new Select(driver.findElement(By.xpath("(//select[@class='input ddl'])[1]")));
		select.selectByIndex(3);

		Select select2 = new Select(driver.findElement(By.xpath("(//select[@class='input ddl'])[2]")));
		select2.selectByIndex(24);

		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Fred Fraser");

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("United");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));

		List<WebElement> options = driver.findElements(By.cssSelector(".ta-item"));

		for (WebElement option : options) {

			if (option.getText().equals("United States")) {

				option.click();
				break;

			}

		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class*='action__submit']")));

		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();

		// final validation!

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));

		String finalValidation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(finalValidation, expectedValidation);
		
		// go to orders page
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(expectedResult));

		driver.quit();

	}

}
