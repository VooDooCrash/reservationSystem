package rs.web;

import com.thoughtworks.selenium.SeleneseTestBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

public class LogginTest {
	
	private static final Logger LOGGER = Logger.getLogger(LogginTest.class.getName());
	
	private WebDriver driver;
	
	@Before
	public void login() {
		System.setProperty("webdriver.chrome.driver", "D:\\Programs\\ChromeSeleniumDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://localhost:8080/users.do");

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("email")));

		WebElement element = driver.findElement(By.name("email"));
		element.sendKeys("ivan.slavchev@gmail.com");

		WebElement lastName = driver.findElement(By.name("password"));
		lastName.sendKeys("123");

		WebElement form = driver.findElement(By.name("UserLoginForm"));
		form.submit();	
	}
	
	@Test
	public void loginTest() throws InterruptedException {

		driver.get("http://localhost:8080/users.do");		

		WebElement userRow = driver
				.findElement(By
						.xpath("*//label[text()[contains(.,'Status:')]]/following-sibling::p[contains(.,'USER')]"));

		SeleneseTestBase.assertEquals("USER",userRow.getText());

		LOGGER.info("Logged in (from Test)");
		LOGGER.info("Status: " + userRow.getText());
	}
}
