package solubris.fundingcircle.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IframeExistsCondition implements ExpectedCondition<WebElement> {
    private static final Logger logger = LoggerFactory.getLogger(AjaxConnectionsCondition.class);

    private final String id;

    public IframeExistsCondition(String id) {
        this.id = id;
    }

    @Override
    public WebElement apply(WebDriver driver) {
        final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            if (iframe.getAttribute("id").equals(id)) {
                return iframe;
            }
        }

        return null;
    }
}
