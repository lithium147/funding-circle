package solubris.fundingcircle.spring;

/**
 * Created by mwalters on 25/04/2015.
 */
public interface Profile {
    String getEmail();

    String getPassword();

    String[] getQuestions();

    String[] getAnswers();

    default String getQuestion(int i) {
        return getQuestions()[i];
    }

    default String getAnswer(int i) {
        return getAnswers()[i];
    }

    String[] getLoanPremiums();

    String getAccountName();

    long getAccountNumber();

    long getSortCode();

    Float getPremiumForLoanId(long l);
}
