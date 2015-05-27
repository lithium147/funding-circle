package solubris.fundingcircle.spring;

import org.springframework.beans.factory.annotation.Value;

public class ProfileImpl implements Profile {

    @Value("${email}")
    private String email;

    @Value("${password}")
    private String password;

    @Value("${questions}")
    private String []questions;

    @Value("${answers}")
    private String []answers;

    @Value("${loanPremiums}")
    private String [] loanPremiums;

    @Value("${accountName}")
    private String accountName;

    @Value("${accountNumber}")
    private long accountNumber;

    @Value("${sortCode}")
    private long sortCode;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String[] getQuestions() {
        return questions;
    }

    @Override
    public String[] getAnswers() {
        return answers;
    }

    @Override
    public String[] getLoanPremiums() {
        return loanPremiums;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }

    @Override
    public long getAccountNumber() {
        return accountNumber;
    }

    @Override
    public long getSortCode() {
        return sortCode;
    }

    @Override
    public Float getPremiumForLoanId(long l) {
        for (String loanPartPremium : loanPremiums) {
            String[] s = loanPartPremium.split(":", 2);
            long loanId = Long.parseLong(s[0]);
            if(loanId == l) {
                return Float.parseFloat(s[1]);
            }
        }

        return null;
    }
}
