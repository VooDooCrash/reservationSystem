package rs.web;

import com.thoughtworks.selenium.SeleneseTestBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

public class GeneralIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(GeneralIntegrationTest.class.getName());

	private WebDriver driver;

	@Before
	public void login() {
		System.setProperty("webdriver.chrome.driver",
				"/home/ivan/Software/chromedriver");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);

		driver.get("http://localhost:8080/rs/users.do");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("email")));

		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("ivan.slavchev@gmail.com");

		WebElement lastName = driver.findElement(By.name("password"));
		lastName.sendKeys("123");

		WebElement form = driver.findElement(By.name("UserLoginForm"));
		form.submit();
	}

	@Test
	public void mainTest() {

		driver.get("http://localhost:8080/rs/users.do");
		
		addUser("Ivan", "Slavchev", "selenium@proxiad.bg", "123", "Guest");

		for (int i=1; i<3 ; i++) {
			
			String role = null;
			if (i%2 == 0) {
				role = "Guest";
			} else {
				role = "User";
			}
			addUser("Test"+i, "Test"+i, "selenium@proxiad.bg"+i, "123", role);
		}

		editUser("Ivan", "Slavchev", "selenium@proxiad.bg", "Guest", "Georgi",
				"Dimitrov", "User");
		
		addRole("TestRole");
		
		addResource("SeleniumTestResource");
				
		addReservation("SeleniumTestResource", "30/09/2013 7:00", "30/09/2013 8:00");
		
//		removeResource("SeleniumTestResource");
		
		removeUser("Georgi", "Dimitrov", "selenium@proxiad.bg", "User");
		
		removeRole("TestRole");
		
		for (int i=1; i<3 ; i++) {
			
			String role = null;
			if (i%2 == 0) {
				role = "Guest";
			} else {
				role = "User";
			}
			removeUser("Test"+i, "Test"+i, "selenium@proxiad.bg"+i, role);
		}
	}

	private void addResource(String resourceName) {
		
		WebElement rolesButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Resources')]"));
		rolesButton.click();
		
		WebDriverWait waitForButton = new WebDriverWait(driver, 10);
		waitForButton.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//button[@data-target='#add-resource-div']")));
		
		WebElement addReservationButton = driver.findElement(By
				.xpath("//button[@data-target='#add-resource-div']"));
		addReservationButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("name")));
		
		WebElement nameInput = driver.findElement(By.name("name"));
		nameInput.sendKeys(resourceName);
		
		WebElement addButton = driver.findElement(By
				.xpath("//input[@type='submit' and @value='Add']"));
		addButton.click();
		
		WebElement usersButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Users')]"));
		usersButton.click();
	}
	
	private void removeResource(String resourceName) {

		WebElement rolesButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Resources')]"));
		rolesButton.click();
		
		WebElement detailsButton = driver.findElement(By
				.xpath("//td[text()[contains(.,'" + resourceName
						+ "')]]/following-sibling::td/following-sibling::td/a"));
		detailsButton.click();
		
		WebElement removeRoleButton = driver.findElement(By
				.xpath("//button[contains(.,'Remove')]"));
		removeRoleButton.click();
		
		WebElement usersButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Users')]"));
		usersButton.click();
		
	}

	private void addReservation(String resourceName, String startTimeStr, String endTimeStr) {
		
		WebElement rolesButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Resources')]"));
		rolesButton.click();
		
		WebDriverWait waitForButton = new WebDriverWait(driver, 10);
		waitForButton.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//button[@data-target='#add-resource-div']")));
		
		WebElement reservationsButton = driver.findElement(By
				.xpath("//td[text()[contains(.,'" + resourceName
						+ "')]]/following-sibling::td/a"));
		reservationsButton.click();
		
		WebElement addReservationButton = driver.findElement(By
				.xpath("//button[@data-target='#add-reservation-div']"));
		addReservationButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("startTime")));
		
		WebElement startTimeInput = driver.findElement(By.name("startTime"));
		startTimeInput.clear();
		startTimeInput.sendKeys(startTimeStr);
		
		WebElement endTimeInput = driver.findElement(By.name("endTime"));
		endTimeInput.clear();
		endTimeInput.sendKeys(endTimeStr);
		
		WebElement addButton = driver.findElement(By
				.xpath("//input[@type='submit' and @value='Add']"));
		addButton.click();
		
		WebElement usersButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Users')]"));
		usersButton.click();
	}

	private void addRole(String name) {
		
		WebElement rolesButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Roles')]"));
		rolesButton.click();
		
		WebDriverWait waitForButton = new WebDriverWait(driver, 10);
		waitForButton.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//button[@data-target='#add-role-div']")));

		WebElement addUserButton = driver.findElement(By
				.xpath("//button[@data-target='#add-role-div']"));
		addUserButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("name")));

		WebElement element = driver.findElement(By.name("name"));
		element.sendKeys(name);
		
		WebElement form = driver.findElement(By.name("AddRoleForm"));
		form.submit();
		
		WebElement usersButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Users')]"));
		usersButton.click();
	}
	
	private void removeRole(String name) {
		
		WebElement rolesButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Roles')]"));
		rolesButton.click();
		
		WebElement detailsButton = driver.findElement(By
				.xpath("//td[text()[contains(.,'" + name
						+ "')]]/following-sibling::td/a"));
		detailsButton.click();
		
		WebElement removeRoleButton = driver.findElement(By
				.xpath("//button[contains(.,'Remove')]"));
		removeRoleButton.click();
		
		WebElement usersButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Users')]"));
		usersButton.click();
	}

	private void removeUser(String firstNameStr, String lastNameStr,
			String emailStr, String roleStr) {
		
		WebElement detailsButton = driver.findElement(By
				.xpath("//td[text()[contains(.,'" + firstNameStr
						+ "')]]/following-sibling::td[contains(.,'"
						+ lastNameStr
						+ "')]/following-sibling::td[contains(.,'" + emailStr
						+ "')]/following-sibling::td[contains(.,'" + roleStr
						+ "')]/following-sibling::td/a"));
		detailsButton.click();
		
		WebElement removeUserButton = driver.findElement(By
				.xpath("//button[contains(.,'Remove')]"));
		removeUserButton.click();
		
		WebElement usersButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Users')]"));
		usersButton.click();
	}

	private void editUser(String firstNameStr, String lastNameStr,
			String emailStr, String roleStr, String newFirstNameStr,
			String newLastNameStr, String newRoleStr) {

		WebElement detailsButton = driver.findElement(By
				.xpath("//td[text()[contains(.,'" + firstNameStr
						+ "')]]/following-sibling::td[contains(.,'"
						+ lastNameStr
						+ "')]/following-sibling::td[contains(.,'" + emailStr
						+ "')]/following-sibling::td[contains(.,'" + roleStr
						+ "')]/following-sibling::td/a"));
		detailsButton.click();

		WebElement editUserButton = driver.findElement(By
				.xpath("//button[contains(.,'Edit user')]"));
		editUserButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("firstName")));

		WebElement firstName = driver.findElement(By.name("firstName"));
		firstName.clear();
		firstName.sendKeys(newFirstNameStr);

		WebElement lastName = driver.findElement(By.name("lastName"));
		lastName.clear();
		lastName.sendKeys(newLastNameStr);

		WebElement roleSelect = driver
				.findElement(By
						.xpath("//label[text()[contains(.,'Role:')]]/following-sibling::div"));
		roleSelect.click();

		WebElement role = driver.findElement(By
				.xpath("//li/div[text()[contains(.,'" + newRoleStr + "')]]"));
		role.click();

		WebElement form = driver.findElement(By.name("EditUserForm"));
		form.submit();
		
		WebElement usersButton = driver.findElement(By
				.xpath("//li/a[contains(.,'Users')]"));
		usersButton.click();
	}

	private void addUser(String firstNameStr, String lastNameStr,
			String emailStr, String passwordStr, String roleStr) {

		WebDriverWait waitForButton = new WebDriverWait(driver, 10);
		waitForButton.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//button[@data-target='#add-user-div']")));

		WebElement addUserButton = driver.findElement(By
				.xpath("//button[@data-target='#add-user-div']"));
		addUserButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("firstName")));

		WebElement element = driver.findElement(By.name("firstName"));
		element.sendKeys(firstNameStr);

		WebElement lastName = driver.findElement(By.name("lastName"));
		lastName.sendKeys(lastNameStr);

		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys(emailStr);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(passwordStr);

		WebElement roleSelect = driver
				.findElement(By
						.xpath("//label[text()[contains(.,'Role:')]]/following-sibling::div"));
		roleSelect.click();

		WebElement role = driver.findElement(By
				.xpath("//li/div[text()[contains(.,'" + roleStr + "')]]"));
		role.click();

		WebElement form = driver.findElement(By.name("AddUserForm"));
		form.submit();

		WebElement userRow = driver.findElement(By
				.xpath("//td[text()[contains(.,'" + firstNameStr
						+ "')]]/following-sibling::td[contains(.,'"
						+ lastNameStr
						+ "')]/following-sibling::td[contains(.,'" + emailStr
						+ "')]/following-sibling::td[contains(.,'" + roleStr
						+ "')]/following-sibling::td/a"));

		SeleneseTestBase.assertEquals(userRow != null, true);

		LOGGER.info("Added user is found in the table");

	}


}