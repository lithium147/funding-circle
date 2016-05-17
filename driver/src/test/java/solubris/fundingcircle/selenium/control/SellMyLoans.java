package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static solubris.fundingcircle.selenium.Waiter.aWaiter;

public class SellMyLoans implements WebDriverProvider {
    private final WebDriver driver;

    public SellMyLoans(WebDriverProvider provider) {
        driver = provider.getWebDriver();
    }

    public void clickSellIndividually() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Sell Individually')]"));
        aWaiter(driver).clickAndWaitForCondition(element, frameToBeAvailableAndSwitchToIt(By.xpath(".//*[@id='sellable_bids']/iframe")));
    }

    public void clickLoanPartsForSale() {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Loan Parts for Sale')]"));
        aWaiter(driver).clickAndWaitForCondition(element, frameToBeAvailableAndSwitchToIt(By.xpath(".//*[@id='selling']/iframe")));
    }

    public void clickLoanPartsSold() {
        driver.findElement(By.xpath("//a[contains(text(),'Loan Parts Sold')]")).click();
        this.driver.switchTo().frame(1);
    }

    public Stream<SellMyLoanRow> rows() {
        return driver.findElements(By.xpath("//*[@id='loanpart-table']//tbody//tr")).stream().map(SellMyLoanRow::new);
    }

    public void clickDelistLoanParts() {
        WebElement element = driver.findElement(By.xpath("//*[@id='loanpart-table']/button[contains(text(),'De-list loan parts')]"));
        aWaiter(driver).clickAndWaitForCondition(element, ExpectedConditions.alertIsPresent());
    }

    public void clickSellLoanParts() {
        WebElement element = driver.findElement(By.xpath("//*[@id='loanpart-header']/button[@name='sell-individual-loan-parts']"));
        aWaiter(driver).clickAndWaitForCondition(element, ExpectedConditions.presenceOfElementLocated(By.className("ngdialog-open")));
    }

    public int determineRowCount() {
        return driver.findElements(By.xpath("//*[@id='loanpart-table']//tbody//tr")).size();
    }

    public void selectItemsPerPage(int items) {
        WebElement element = findOptionForItemsPerPage(items);
        aWaiter(driver).clickAndWaitForAjaxToComplete(element);
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

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }
}
