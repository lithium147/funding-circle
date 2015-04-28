package solubris.fundingcircle.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

class AjaxConnectionsCondition implements ExpectedCondition<Boolean> {
    private static final Logger logger = LoggerFactory.getLogger(AjaxConnectionsCondition.class);

    private final long expectedActiveConnections;

    public AjaxConnectionsCondition(long expectedActiveConnections) {
        checkArgument(expectedActiveConnections >=0);
        this.expectedActiveConnections = expectedActiveConnections;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        long activeConnections = (Long)((JavascriptExecutor) driver).executeScript("return jQuery.active");
        logger.trace("activeConnections = {}", activeConnections);
        return activeConnections == expectedActiveConnections;
    }
}
