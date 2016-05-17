package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import static com.google.common.collect.Lists.newArrayList;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;

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
        row.findElements(By.xpath(".//select/option"))
                .stream()
                .filter(e -> e.getText().equalsIgnoreCase(finalPremiumStr))
                .findFirst().orElseThrow(() -> new IllegalStateException("could not find premium " + premium + " for row"))
                .click();
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
}
