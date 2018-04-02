package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ProductListingPage;

public class Steps {
	WebDriver driver;
	HomePage home;
	ProductListingPage productListingPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.shop.demoqa.com");
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String product) {
		home = new HomePage(driver);
		home.perform_Search(product);
	}

	@When("^choose to buy the first item$")
	public void choose_to_buy_the_first_item() {
		productListingPage = new ProductListingPage(driver);
		productListingPage.select_Product(0);
		productListingPage.clickOn_AddToCart();
	}

	@When("^moves to checkout from mini cart$")
	public void moves_to_checkout_from_mini_cart() {
		cartPage = new CartPage(driver);
		cartPage.clickOn_Cart();
		cartPage.clickOn_ContinueToCheckout();
	}

	@When("^enter personal details on checkout page$")
	public void enter_personal_details_on_checkout_page() throws Throwable {
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.fill_PersonalDetails();
	}


	@When("^select same delivery address$")
	public void select_same_delivery_address() throws Throwable {
		checkoutPage.check_ShipToDifferentAddress(false);
	}

	@When("^select payment method as \"([^\"]*)\" payment$")
	public void select_payment_method_as_payment(String arg1) throws Throwable {
		checkoutPage.select_PaymentMethod("CheckPayment");
	}

	@When("^place the order$")
	public void place_the_order() throws Throwable {
		checkoutPage.check_TermsAndCondition(true);
		checkoutPage.clickOn_PlaceOrder();
		
		driver.quit();
	}

}
