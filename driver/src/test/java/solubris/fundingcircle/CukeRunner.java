package solubris.fundingcircle;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format = "html:target/surefire-reports", strict = true, tags="@Implemented", features={"classpath:solubris/fundingcircle/CheckNewSales.feature"})
public class CukeRunner {
	
}
