package solubris.fundingcircle.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"junit:target/junit.xml", "html:target/features"},
        strict = true,
        glue = "solubris.fundingcircle.cucumber",
        tags = "@CI"
)
//, features={"classpath:solubris/fundingcircle/CheckNewSales.feature"})
public class CukeRunner {

}
