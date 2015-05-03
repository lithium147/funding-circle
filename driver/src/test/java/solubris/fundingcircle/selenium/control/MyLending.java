package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.Waiter;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import java.util.List;

import static solubris.fundingcircle.selenium.Waiter.aWaiter;

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
        WebElement element = driver.findElement(By.xpath("//nav//a[contains(text(),'Sell')]"));
        aWaiter(driver).clickAndWaitForAjaxToComplete(element);
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

    // div id=main
    public void clickTransferMoney() {
        WebElement element = driver.findElement(By.xpath("//nav//a[contains(text(),'Transfer money')]"));
        aWaiter(driver).clickAndWaitForNewBody(element);
    }

    public double determineAvailableFunds() {
        String asString = driver.findElement(By.xpath("//*[@id='avail-funds-menu']//span[@class='val']")).getText().substring(1);
        return Double.parseDouble(asString);
    }
}
//*[@id="avail-funds-menu"]/li/span/span
