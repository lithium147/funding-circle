package solubris.fundingcircle.selenium.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import solubris.fundingcircle.selenium.driver.WebDriverProvider;
import solubris.fundingcircle.spring.Profile;

import static solubris.fundingcircle.selenium.Waiter.aWaiter;

public class LoginPage2 {
	private final WebDriver driver;
    private final Profile profile;

    public LoginPage2(WebDriverProvider provider, Profile profile) {
        this.driver = provider.getWebDriver();
        this.profile = profile;
    }

	public void clickSubmit() {
        WebElement element = driver.findElement(By.xpath("//form[@action='/security-questions']//input[@type='submit']"));
        aWaiter(driver).clickAndWaitForNewBody(element);
	}

    public void answerQuestion() {
        String answer=determineAnswer();
        enterAnswer(answer);
    }

    private String determineAnswer() {
        String question = driver.findElement(By.xpath("//form[@action='/security-questions']//label[@for='user_input']")).getText();
        String[] knownQuestions = profile.getQuestions();

        for (int i = 0; i < knownQuestions.length; i++) {
            if(question.contains(knownQuestions[i])) {
                return profile.getAnswer(i);
            }
        }

        throw new IllegalStateException("unknown question " + question);
    }

    private void enterAnswer(String answer) {
        driver.findElement(By.id("user_input")).sendKeys(answer);
    }
}
