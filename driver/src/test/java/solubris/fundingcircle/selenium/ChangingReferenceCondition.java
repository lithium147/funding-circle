package solubris.fundingcircle.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * only return the new element when the reference changes
 */
class ChangingReferenceCondition implements ExpectedCondition<WebElement> {
    private static final Logger logger = LoggerFactory.getLogger(AjaxConnectionsCondition.class);

    private final By by;
    private WebElement before;

    public ChangingReferenceCondition(By by) {
        this.by = by;
    }

    public void prepare(WebDriver driver) {
        before = driver.findElement(by);
    }

    @Override
    public WebElement apply(WebDriver driver) {
        checkArgument(before!=null, "prepare must be called first");

        WebElement after = driver.findElement(by);
        logger.debug("before={}, after={}", before.hashCode(), after.hashCode());
        return after.hashCode() != before.hashCode() ? after : null;
    }
}
