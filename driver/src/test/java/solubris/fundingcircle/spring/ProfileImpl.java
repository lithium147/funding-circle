package solubris.fundingcircle.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class ProfileImpl implements Profile {

    @Value("${email}")
    private String email;

    @Value("${password}")
    private String password;

    @Value("${questions}")
    private String []questions;

    @Value("${answers}")
    private String []answers;

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
}
