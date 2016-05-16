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
        // XXX this agular selector might not be generic to all angular pages
        String angularElementSelector = "document.getElementsByClassName('ng-scope')[0]";
        long activeConnections = (Long)((JavascriptExecutor) driver).executeScript(
                "if(window.jQuery) { return jQuery.active; }" +
                "if(window.angular) { return angular.element(" + angularElementSelector + ").injector().get('$http').pendingRequests.length; }" +
                "return undefined;"
        );
        logger.debug("activeConnections = {}", activeConnections);
        return activeConnections == expectedActiveConnections;
    }
}
