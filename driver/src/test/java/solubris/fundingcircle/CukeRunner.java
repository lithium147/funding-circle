package solubris.fundingcircle;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = "html:target/surefire-reports", strict = true, tags="@Implemented")
//, features={"classpath:solubris/fundingcircle/CheckNewSales.feature"})
public class CukeRunner {
	
}
