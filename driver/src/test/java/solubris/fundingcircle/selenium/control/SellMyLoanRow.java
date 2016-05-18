package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

/**
 * Created by eeo2 on 14/09/2014.
 */
public class SellMyLoanRow {
    private final WebElement row;

    public SellMyLoanRow(WebElement row) {
        this.row = row;

    }

    public void selectPremium(Float premium) {
        final String finalPremiumStr = formattedPremiumWithoutDecimalForWholeNumbers(premium);
        WebElement webElement = row.findElements(By.xpath(".//select/option"))
                .stream()
                .filter(e -> e.getText().equalsIgnoreCase(finalPremiumStr))
                .findFirst().orElseThrow(() -> new IllegalStateException("could not find premium " + premium + " for row"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webElement.click();
    }

    private String formattedPremiumWithoutDecimalForWholeNumbers(Float premium) {
        int premiumInt = premium.intValue();
        String premiumStr = "" + premium;
        if(premium == premiumInt) {
            premiumStr = "" + premiumInt;
        }
        return premiumStr + "%";
    }

    public void selectSell() {
        WebElement element = row.findElement(By.xpath(".//input[@type='checkbox']"));
        element.click();
    }

    public long determineLoanId() {
        String value = row.findElement(By.xpath("./td[@ng-bind='loanPart.auction_id']")).getText();
        return Long.parseLong(value);
    }

    public void selectDelist() {
        WebElement element = row.findElement(By.xpath(".//input[@type='checkbox']"));
        element.click();
    }

    public void dismissAlertIfPresent(WebDriverProvider provider) {
        try {
            row.findElement(By.xpath(".//select/option")).getText();
            Thread.sleep(2000);
            provider.getWebDriver().switchTo().alert().accept();
        } catch (UnhandledAlertException | NoAlertPresentException e) {
//            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
