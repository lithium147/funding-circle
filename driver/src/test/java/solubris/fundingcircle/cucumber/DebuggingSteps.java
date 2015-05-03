package solubris.fundingcircle.cucumber;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import solubris.fundingcircle.util.BaseSteps;

/**
 * support debugging of the tests
 */
public class DebuggingSteps extends BaseSteps {

    @After
    private void embedScreenshot(Scenario scenario) {
        WebDriver underlyingDriver = driver;
        if(underlyingDriver instanceof EventFiringWebDriver) {
            underlyingDriver = ((EventFiringWebDriver) driver).getWrappedDriver();
        }
        if(underlyingDriver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

    @And("^a break point$")
    public void a_break_point() throws Throwable {
        // can add a breakpoint here
    }

    @Then("^i wait forever$")
    synchronized public void i_wait_forever() throws Throwable {
        this.wait();
    }

    @And("^I wait for (\\d+) seconds?$")
    public void I_wait_for_seconds(int arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        Thread.sleep(arg1*1000);
    }
}
