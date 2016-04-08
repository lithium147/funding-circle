package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static solubris.fundingcircle.selenium.Waiter.aWaiter;

/**
 * Created by eeo2 on 14/09/2014.
 */
public class TransferMoney {
    private final WebDriver driver;

    public TransferMoney(WebDriverProvider provider) {
        driver = provider.getWebDriver();
    }

    public void clickTransferOut() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Transfer out')]"));
        aWaiter(driver).clickAndWaitForNewBody(element);
    }

    public void clickTransferFunds() {
        WebElement element = driver.findElement(By.xpath("//*[@id='new_transfer_out']//input[@name='commit']"));
        element.click();
    }

//    Thank you, your transfer request for £81.53 has been sent.

    public void enterAmount(double amount) {
        driver.findElement(By.id("transfer_out_amount")).sendKeys(String.valueOf(amount));
    }

    public void enterAccountName(String accountName) {
        driver.findElement(By.id("transfer_out_payee_name")).sendKeys(accountName);
    }

    public void enterAccountNumber(long accountNumber) {
        driver.findElement(By.id("transfer_out_payee_account_number")).sendKeys(String.valueOf(accountNumber));
    }

    public void enterSortCode(long sortCode) {
        driver.findElement(By.id("transfer_out_payee_sort_code")).sendKeys(String.valueOf(sortCode));
    }

    public boolean hasSuccessNotification() {
        List<WebElement> elements = driver.findElements(By.cssSelector("div.notification--success"));
        return elements.size() > 0;
    }
}
