package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import static solubris.fundingcircle.selenium.Waiter.aWaiter;

public class LoginPage1 {
	private final WebDriver driver;

	public LoginPage1(WebDriver driver) {
		this.driver = driver;
	}

    public LoginPage1(WebDriverProvider provider) {
        this(provider.getWebDriver());
    }

    public void show() {
        driver.get("http://www.fundingcircle.com/login");
    }

    public void enterEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);

    }

    public void clickSubmit() {
        WebElement element = driver.findElement(By.xpath("//form[@name='login']/button[@type='submit']"));
        aWaiter(driver).clickAndWaitForNewBody(element);
    }
}
