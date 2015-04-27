package solubris.fundingcircle.spring;

/**
 * Created by mwalters on 25/04/2015.
 */
public interface Profile {
    String getEmail();

    String getPassword();

    String[] getQuestions();

    String[] getAnswers();

    default String getAnswer(int i) {
        return getAnswers()[i];
    }

    String getAccountName();

    long getAccountNumber();

    long getSortCode();
}
