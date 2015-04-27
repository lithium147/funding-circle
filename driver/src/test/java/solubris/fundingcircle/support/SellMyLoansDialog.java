package solubris.fundingcircle.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by eeo2 on 14/09/2014.
 */
public class SellMyLoansDialog {
    private final WebDriver driver;

    public SellMyLoansDialog(WebDriverProvider provider) {
        driver = provider.getWebDriver();
    }

    public void clickCheckbox() {
        WebElement element = driver.findElement(By.id("acceptLoan_agree_to_terms"));
        element.click();
    }

    public void acceptTerms() {
        WebElement element = driver.findElement(By.xpath("//button[@name='accept_terms_›_']"));
        element.click();
    }
}
