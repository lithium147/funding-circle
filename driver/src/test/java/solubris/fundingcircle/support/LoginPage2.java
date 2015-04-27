package solubris.fundingcircle.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import solubris.fundingcircle.spring.Profile;

public class LoginPage2 {
	private final WebDriver driver;
    private final Profile profile;

    public LoginPage2(WebDriverProvider provider, Profile profile) {
        this.driver = provider.getWebDriver();
        this.profile = profile;
    }

	public void clickSubmit() {
        driver.findElement(By.xpath("//form[@name='securityQuestions']//button[@type='submit']")).click();
	}

    public void answerQuestion() {
        String answer=determineAnswer();
        enterAnswer(answer);
    }

    private String determineAnswer() {
        String question = driver.findElement(By.xpath("//form[@name='securityQuestions']//label[@for='answer']")).getText();
        String[] knownQuestions = profile.getQuestions();

        for (int i = 0; i < knownQuestions.length; i++) {
            if(question.contains(knownQuestions[i])) {
                return profile.getAnswer(i);
            }
        }

        throw new IllegalStateException("unknown question " + question);
    }

    private void enterAnswer(String answer) {
        driver.findElement(By.id("answer")).sendKeys(answer);
    }
}
