package seleniumTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class E2E_Test {

	public static void main(String[] args) throws InterruptedException {
	
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.shop.demoqa.com");
 
		driver.navigate().to("http://shop.demoqa.com/?s=" + "dress" + "&post_type=product");
 
	
		List<WebElement> items = driver.findElements(By.cssSelector(".noo-product-inner"));
		items.get(0).click();
		
		WebElement addToCart = driver.findElement(By.cssSelector("button.single_add_to_cart_button"));
//		Actions actions = new Actions(driver);
		
//		actions.moveToElement(addToCart).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(addToCart));
		addToCart.click();		
		
		
		WebElement cart = driver.findElement(By.cssSelector(".cart-button"));
		cart.click();
		
		WebElement continueToCheckout = driver.findElement(By.cssSelector(".checkout-button.alt"));
		continueToCheckout.click();		
		
		
		Thread.sleep(5000);
		WebElement firstName = driver.findElement(By.cssSelector("#billing_first_name"));
		firstName.sendKeys("Lakshay");
		
		WebElement lastName = driver.findElement(By.cssSelector("#billing_last_name"));
		lastName.sendKeys("Sharma");
		
		WebElement emailAddress = driver.findElement(By.cssSelector("#billing_email"));
		emailAddress.sendKeys("test@gmail.com");
		
		WebElement phone = driver.findElement(By.cssSelector("#billing_phone"));
		phone.sendKeys("07438862327");
				
		WebElement countryDropDown = driver.findElement(By.cssSelector("#billing_country_field .select2-arrow"));
		countryDropDown.click();
		Thread.sleep(2000);
		
		List<WebElement> countryList = driver.findElements(By.cssSelector("#select2-drop ul li"));
		for(WebElement country : countryList){
			if(country.getText().equals("India")) {
				country.click();	
				Thread.sleep(3000);
				break;
			}		
		}
						
		WebElement city = driver.findElement(By.cssSelector("#billing_city"));
		city.sendKeys("Delhi");
		
		WebElement address = driver.findElement(By.cssSelector("#billing_address_1"));
		address.sendKeys("Shalimar Bagh");
		
		WebElement postcode = driver.findElement(By.cssSelector("#billing_postcode"));
		postcode.sendKeys("110088");		
		
		WebElement shipToDifferetAddress = driver.findElement(By.cssSelector("#ship-to-different-address-checkbox"));
		shipToDifferetAddress.click();
		Thread.sleep(3000);
		
//		WebElement paymentMethod = driver.findElement(By.cssSelector("#payment_method_cheque"));
//		Thread.sleep(5000);
//		wait.until(ExpectedConditions.elementToBeClickable(paymentMethod));
//		paymentMethod.click();
//		WebElement shippingState = driver.findElement(By.cssSelector("#shipping_state"));
		List<WebElement> shippingList = driver.findElements(By.cssSelector("#billing_state option"));
		System.out.println("############# Shipping list size: "+shippingList.size());
		for(WebElement shipping : shippingList){
			System.out.println("Shipping text: "+shipping.getText());
			if(shipping.getText().equals("Bihar")) {
				shipping.click();	
				Thread.sleep(3000);
				break;
			}		
		}
//		shippingState.sendKeys("Bihar");
		
		Thread.sleep(5000);
		WebElement acceptTC = driver.findElement(By.cssSelector("#terms.input-checkbox"));
//		wait.until(ExpectedConditions.elementToBeClickable(acceptTC));
		acceptTC.click();
		
		WebElement placeOrder = driver.findElement(By.cssSelector("#place_order"));
		placeOrder.submit();
		Thread.sleep(3000);
		
		driver.quit();

	}

}
