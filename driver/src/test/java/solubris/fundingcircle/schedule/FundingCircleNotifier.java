package solubris.fundingcircle.schedule;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import solubris.fundingcircle.cucumber.CukeRunner;

/**
 * Created by eeo2 on 16/09/2014.
 */
@Service
public class FundingCircleNotifier {

    @Scheduled(fixedRate=60000)
    public void checkAndNotify() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(CukeRunner.class);
    }

    public static void main(String args[]) throws InterruptedException {
        while (true) {
            Thread.sleep(10000);
        }
//        JUnitCore junit = new JUnitCore();
//        Result result = junit.run(CukeRunner.class);
//        org.junit.runner.JUnitCore.main("solubris.fundingcircle");
    }
}
