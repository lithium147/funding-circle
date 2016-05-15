package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import solubris.fundingcircle.selenium.IFrameExistsCondition;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static solubris.fundingcircle.selenium.Waiter.aWaiter;

/**
 * Created by eeo2 on 14/09/2014.
 */
public class SellMyLoans {
    private final WebDriver driver;

    public SellMyLoans(WebDriverProvider provider) {
        driver = provider.getWebDriver();
    }

    public void clickSellIndividually() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Sell Individually')]"));
        IFrameExistsCondition iFrameExistsCondition = new IFrameExistsCondition("sell-individual-loan-parts");
        WebElement sellLoanPartsFrame = aWaiter(driver).clickAndWaitForCondition(element, iFrameExistsCondition);
        driver.switchTo().frame(sellLoanPartsFrame);

//        WebElement parent = element.findElement(By.xpath(".."));
//        WebElement iframeWrapper = driver.findElement(By.id("sellable_bids"));
//        aWaiter(driver).clickAndWaitForCondition(element, driver -> "active".equals(parent.getAttribute("class")));
//        aWaiter(driver).clickAndWaitForCondition(element, driver -> "active".equals(iframeWrapper.getAttribute("class")));
//        driver.switchTo().frame("sell-individual-loan-parts");
//        driver.switchTo().frame(1);
    }

    public void clickLoanPartsForSale() {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Loan Parts for Sale')]"));
        aWaiter(driver).clickAndWaitForCondition(element, frameToBeAvailableAndSwitchToIt(By.xpath(".//*[@id='selling']/iframe")));
    }

    public void clickLoanPartsSold() {
        driver.findElement(By.xpath("//a[contains(text(),'Loan Parts Sold')]")).click();
        this.driver.switchTo().frame(1);
    }

    public void selectPremium(Float premium, int row) {
        final String finalPremiumStr = formattedPremiumWithoutDecimalForWholeNumbers(premium);
        driver.findElements(By.xpath("//*[@id='loanpart-table']//tbody//tr[" + row + "]//select/option"))
                .stream()
                .filter(e -> e.getText().equalsIgnoreCase(finalPremiumStr))
                .findFirst().orElseThrow(() -> new IllegalStateException("could not find premium " + premium + " for row " + row))
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

    public long determineLoanIdFor(int row) {
        String value = driver.findElement(By.xpath("//*[@id='loanpart-table']//tbody//tr[" + row + "]/td[@ng-bind='loanPart.auction_id']")).getText();
        return Long.parseLong(value);
    }

    public void selectSell(int row) {
        WebElement element = driver.findElement(By.xpath("//*[@id='loanpart-table']//tbody//tr[" + row + "]//input[@type='checkbox']"));
        element.click();
    }

    public void selectDelist(int row) {
        WebElement element = driver.findElement(By.xpath("//*[@id='loanpart-table']//tbody//tr[" + row + "]//input[@type='checkbox']"));
        element.click();
    }

    public void clickDelistLoanParts() {
        WebElement element = driver.findElement(By.xpath("//*[@id='loanpart-table']/button[contains(text(),'De-list loan parts')]"));
        element.click();
    }

    public void clickSellLoanParts() {
        WebElement element = driver.findElement(By.xpath("//*[@id='loanpart-header']/button[@name='sell-individual-loan-parts']"));
        element.click();
    }

    public int determineRowCount() {
        return driver.findElements(By.xpath("//*[@id='loanpart-table']//tbody//tr")).size();
    }

    public void selectItemsPerPage(int items) {
        WebElement element = findOptionForItemsPerPage(items);
        aWaiter(driver).clickAndWaitForFixedTime(element, 5000l);
        // TODO Wait for ajax to complete
    }

    private WebElement findOptionForItemsPerPage(int items) {
        return driver.findElements(By.xpath("//*[@id='loanpart-table']/*[@class='loanparts-perpage']/select/option"))
                .stream()
                .filter(e -> e.getText().equals("" + items))
                .findFirst().orElseThrow(() -> new IllegalStateException("could not find page size option " + items));
    }

    public void clickTimeSoldColumn() {
        driver.findElement(By.xpath("//a[contains(text(),'Time Sold')]")).click();
    }

    public Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public List<LoanPart> findNewlySoldParts() {
        List<LoanPart> result = newArrayList();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat percentFormat = new DecimalFormat("0.00%");
        DecimalFormat currencyFormat = new DecimalFormat("£0.00");

        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='loanpart-table']//tr//td[contains(@ng-bind,'sold_date')]"));
        for (WebElement element : elements) {
            try {
                Date dateSold = format.parse(element.getText());
                Date startOfDay = getStartOfDay(new Date());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startOfDay);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                Date startOfYesterdayDay = calendar.getTime();
                if(dateSold.after(startOfYesterdayDay) || dateSold.equals(startOfYesterdayDay)) {
                    WebElement rowElement = element.findElement(By.xpath(".."));
                    String title = rowElement.findElement(xpathForBindElement("title")).getText();
                    long id = Long.parseLong(rowElement.findElement(xpathForBindElement("loan_part_id")).getText());
                    long loanId = Long.parseLong(rowElement.findElement(xpathForBindElement("auction_id")).getText());
                    String risk = rowElement.findElement(xpathForBindElement("risk_band")).getText();
                    double markUpPercent = percentFormat.parse(rowElement.findElement(xpathForBindElement("mark_up_percent")).getText()).doubleValue();
                    double markUpAmount = currencyFormat.parse(rowElement.findElement(xpathForBindElement("mark_up_amount")).getText()).doubleValue();
                    double salePrice = currencyFormat.parse(rowElement.findElement(xpathForBindElement("price")).getText()).doubleValue();
                    double buyerRate = percentFormat.parse(rowElement.findElement(xpathForBindElement("interest_rate")).getText()).doubleValue();
                    String buyer = rowElement.findElement(xpathForBindElement("buyer")).getText();
                    LoanPart loanPart = new LoanPart(id, title, loanId, risk, markUpPercent, markUpAmount, salePrice, buyerRate, dateSold, buyer);
                    result.add(loanPart);
                }
            } catch(ParseException pe) {
                throw new IllegalStateException("could not parse date sold " + element.getText());
            }
        }

        return result;
    }

    private By xpathForBindElement(final String varName) {
        return By.xpath("//*[contains(@ng-bind,'" + varName + "')]");
    }

    public void clickAccept() {
        driver.switchTo().alert().accept();
    }
}
