package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import java.util.List;

/**
 * Created by eeo2 on 14/09/2014.
 */
public class MyLending {
    private final WebDriver driver;

    public MyLending(WebDriverProvider provider) {
        this.driver = provider.getWebDriver();
    }

    public void acceptTermsIfPresent() {
        List<WebElement> elements = driver.findElements(By.cssSelector("a.btn"));
        if(elements.size() > 0) {
            elements.get(0).click();
        }
    }

    public void clickSell() {
        driver.findElement(By.xpath("//nav//a[contains(text(),'Sell')]")).click();
    }

    public void clickLogout() {
        driver.findElement(By.xpath("//nav//a[contains(text(),'Sign out')]")).click();
    }

    public void clickSubMenu() {
        List<WebElement> elements = driver.findElements(By.cssSelector("li.sub-menu"));
        if(elements.size() > 0) {
            elements.get(0).click();
        }
    }

    public void clickTransferMoney() {
        driver.findElement(By.xpath("//nav//a[contains(text(),'Transfer money')]")).click();
    }

    public double determineAvailableFunds() {
        String asString = driver.findElement(By.xpath("//*[@id='avail-funds-menu']//span[@class='val']")).getText().substring(1);
        return Double.parseDouble(asString);
    }
}
//*[@id="avail-funds-menu"]/li/span/span
