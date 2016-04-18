package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.Waiter;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import java.util.List;

import static org.openqa.selenium.By.xpath;
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
        //        aWaiter(driver).clickAndWaitForAjaxToComplete(element);
        aWaiter(driver).clickAndWaitForNewBody(xpath("//nav//a[contains(text(),'Sell')]"));
    }

    public void clickLogout() {
        driver.findElement(xpath("//nav//a[contains(text(),'Sign out')]")).click();
    }

    public void clickSubMenu() {
        List<WebElement> elements = driver.findElements(By.cssSelector("li.sub-menu"));
        if(elements.size() > 0) {
            elements.get(0).click();
        }
    }

    // div id=main
    public void clickTransferMoney() {
        aWaiter(driver).clickAndWaitForNewBody(xpath("//nav//a[contains(text(),'Transfer money')]"));
    }

    public double determineAvailableFunds() {
//        String asString = driver.findElement(By.xpath("//*[@id='avail-funds-menu']//span[@class='val']")).getText().substring(1);
        String asString = driver.findElement(xpath("//span[@class='header__user-funds']")).getText();
        asString = asString.replace("Available funds £", "");
        asString = asString.replace(",", "");
        return Double.parseDouble(asString);
    }
}