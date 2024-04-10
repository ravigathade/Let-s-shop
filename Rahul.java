package Ecommerce;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
public class Rahul {

		public static void main(String[] args) throws InterruptedException {
			String productName = "ZARA COAT 3";
			//launching browser
			WebDriver driver = new ChromeDriver();
			driver.navigate().to("https://rahulshettyacademy.com/client");
			// driver.manage().window().maximize();
			driver.findElement(By.id("userEmail")).sendKeys("ravigathade@gmail.com");
			driver.findElement(By.id("userPassword")).sendKeys("ravi");
			driver.findElement(By.id("login")).click();
			System.out.println("login is successful");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			System.out.println("waited for 5 second");
			
			//selecting product 

			List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

			WebElement pro = productList.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
			System.out.println(pro.getText());

			pro.findElement(By.cssSelector(".card-body button:last-of-type")).click(); // for finding the element we go from
																						// "b to back add cart button"
			System.out.println(pro.findElement(By.cssSelector(".card-body button:last-of-type")).getText() + " button clicked");
           
			// waiting for toast container and .ng-animating webElement
			// Thread.sleep(3000);
			//or
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			// ng-animating class= 'ng-tns-c31-0 ng-star-inserted'
			
			//clicking on cart
                        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

			System.out.println(driver.findElement(By.cssSelector("[routerlink*='cart']")).getText() + " " + "button clicked");
			Thread.sleep(1000);
			
			// validating the product is present in cart section or not
			List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
			cartProducts.forEach(System.out::println);
			
		        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
			Assert.assertTrue(match);
			System.out.println(match);
			
			// clicked on checkout button
			driver.findElement(By.cssSelector(".totalRow button")).click();
			Actions a = new Actions(driver);
			a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
			//Thread.sleep(2000);
			//or
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//app-root//button[2]")));
			driver.findElement(By.xpath("//body//app-root//button[2]")).click();
			
			driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
			//getting the text THANKYOU FOR THE ORDER. after sucessful order place
			System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());
			driver.quit();
            
		}


	}




