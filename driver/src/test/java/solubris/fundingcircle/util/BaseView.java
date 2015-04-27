package solubris.fundingcircle.util;

import org.openqa.selenium.WebDriver;

/**
 * Created by eeo2 on 13/09/2014.
 */
public abstract class BaseView implements Viewable {
    protected final WebDriver driver;

    public BaseView(WebDriver driver) {
        this.driver = driver;
    }
}
