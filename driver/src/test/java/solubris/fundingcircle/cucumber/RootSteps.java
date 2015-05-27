package solubris.fundingcircle.cucumber;

import com.google.common.base.Joiner;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import solubris.fundingcircle.util.MailService;
import solubris.fundingcircle.spring.Profile;
import solubris.fundingcircle.selenium.control.*;
import solubris.fundingcircle.util.BaseSteps;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 * for spring setup followed this:
 * http://techblog.kataru.nl/?p=121
 */
public class RootSteps extends BaseSteps {

    @Autowired
    Map<String, Profile> config;

//    @Autowired
    MailService mailService;

    List<LoanPart> newlySoldParts;

//    @Autowired
//    public RootSteps() {
////        super(new SharedWebDriver());
//    }

    @Given("^i open funding circle$")
    public void I_open_priority_moments_web_app() throws Throwable {
        new FundingCircleWebApp(this).open();
    }

    @Given("^i enter email and password$")
    public void login() throws Throwable {
    }

    @Given("^i enter email and password for \"([^\"]*)\"$")
    public void i_enter_email_and_password_for(String profile) throws Throwable {
        LoginPage1 loginPage1 = new LoginPage1(this);
        loginPage1.show();
        loginPage1.enterEmail(config.get(profile).getEmail());
        loginPage1.enterPassword(config.get(profile).getPassword());
        loginPage1.clickSubmit();
    }

    @Given("^i answer the secret question for \"([^\"]*)\"$")
    public void i_answer_the_secret_question_for(String profile) throws Throwable {
        LoginPage2 loginPage2 = new LoginPage2(this, config.get(profile));
        loginPage2.answerQuestion();
        loginPage2.clickSubmit();
    }

    @Given("^i accept the conditions$")
    public void i_accept_the_conditions() throws Throwable {
        MyLending myLending = new MyLending(this);
        myLending.acceptTermsIfPresent();
    }

    @Given("^i goto sell view$")
    public void i_goto_sell_view() throws Throwable {
        MyLending myLending = new MyLending(this);
        myLending.clickSell();
    }

    @Given("^i goto sell individually view$")
    public void i_goto_sell_individually_view() throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        sellMyLoans.clickSellIndividually();
    }

    @Given("^i select a premium of ([0-9.]+) for record (\\d+)$")
    public void i_select_a_premium_of_for_record(float premium, int row) throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        sellMyLoans.selectPremium(premium, row);
        sellMyLoans.selectSell(row);
    }

    @Given("^i sell the selected parts$")
    public void i_sell_the_selected_parts() throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        sellMyLoans.clickSellLoanParts();
        SellMyLoansDialog sellMyLoansDialog = new SellMyLoansDialog(this);
        sellMyLoansDialog.clickCheckbox();
        sellMyLoansDialog.acceptTerms();
    }

    @Given("^i select a premium of ([0-9.]+) for record (\\d+) to (\\d+)$")
    public void i_select_a_premium_of_for_record_to(float premium, int rowStart, int rowEnd) throws Throwable {
        for (int i = rowStart; i <= rowEnd; i++) {
            i_select_a_premium_of_for_record(premium, i);
        }
    }

    @Given("^i choose a premium of (-?[0-9.]+) for all records in the view and select these records$")
    public void i_select_a_premium_of_for_all_records_in_the_view(float premium) throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        int rowCount = sellMyLoans.determineRowCount();

        if (rowCount <= 0) {
            throw new IllegalStateException("could not find any records in view");
        }

        for (int i = 1; i <= rowCount; i++) {
            i_select_a_premium_of_for_record(premium, i);
        }
    }


    @Given("^i choose appropriate premiums for loan parts defined by \"([^\"]*)\"$")
    public void i_choose_appropriate_premiums_for_loan_parts_defined_by(String profile) throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        int rowCount = sellMyLoans.determineRowCount();

        if (rowCount <= 0) {
            throw new IllegalStateException("could not find any records in view");
        }

        for (int row = 1; row <= rowCount; row++) {
            long loanPartId = sellMyLoans.determineLoanIdFor(row);
            Float premium = config.get(profile).getPremiumForLoanId(loanPartId);
            if(premium != null) {
                sellMyLoans.selectPremium(premium, row);
//                sellMyLoans1.selectSell(row);
            }
        }
    }

    @Given("^i select (\\d+) items per page$")
    public void i_select_items_per_page(int items) throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        sellMyLoans.selectItemsPerPage(items);
        Thread.sleep(5000);
    }

    @Given("^there are items to sell$")
    public void there_are_items_to_sell() throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        Thread.sleep(5000);
        assertThat(sellMyLoans.determineRowCount()).isNotEqualTo(0).as("There must be items to sell");
    }

    @Given("^i goto loan parts sold view$")
    public void i_goto_loan_parts_sold_view() throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        sellMyLoans.clickLoanPartsSold();
    }

    @Given("^i sort by time sold desc$")
    public void i_sort_by_time_sold_desc() throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        sellMyLoans.clickTimeSoldColumn();
        Thread.sleep(1000);
        sellMyLoans.clickTimeSoldColumn();
        Thread.sleep(1000);
    }

    @Given("^i remember newly sold parts that have not been seen before$")
    public void i_remember_newly_sold_parts_that_have_not_been_seen_before() throws Throwable {
        SellMyLoans sellMyLoans = new SellMyLoans(this);
        newlySoldParts = sellMyLoans.findNewlySoldParts();
    }

    @Given("^if found, send an email notification for remembered parts$")
    public void if_found_send_an_email_notification_for_remembered_parts() throws Throwable {
        if(newlySoldParts.size() > 0) {
            mailService.sendMail(Joiner.on("\n").join(newlySoldParts));
        }
    }

    @Given("^save remembered parts for next time$")
    public void save_remembered_parts_for_next_time() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @After
    public void i_logout() throws Throwable {
        driver.manage().deleteAllCookies();
//        MyLending myLending = new MyLending(this);
//        myLending.clickSubMenu();
//        myLending.clickLogout();
    }

    @Given("^i goto transfer money$")
    public void i_goto_transfer_money() throws Throwable {
        MyLending myLending = new MyLending(this);
        myLending.clickTransferMoney();
    }

    @Given("^i goto transfer out$")
    public void i_goto_transfer_out() throws Throwable {
        TransferMoney transferMoney = new TransferMoney(this);
        transferMoney.clickTransferOut();
    }

    @When("^i enter the available funds in the amount$")
    public void i_enter_the_available_funds_in_the_amount() throws Throwable {
        TransferMoney transferMoney = new TransferMoney(this);
        MyLending myLending = new MyLending(this);
        transferMoney.enterAmount(myLending.determineAvailableFunds());
    }

    @And("^i enter account details for \"([^\"]*)\"$")
    public void i_enter_account_details_for(String profile) throws Throwable {
        TransferMoney transferMoney = new TransferMoney(this);
        transferMoney.enterAccountName(config.get(profile).getAccountName());
        transferMoney.enterAccountNumber(config.get(profile).getAccountNumber());
        transferMoney.enterSortCode(config.get(profile).getSortCode());
    }

    @And("^i click to transfer funds$")
    public void i_click_to_transfer_funds() throws Throwable {
        TransferMoney transferMoney = new TransferMoney(this);
        transferMoney.clickTransferFunds();
        assertThat(transferMoney.hasSuccessNotification()).isEqualTo(true);
    }

    @Then("^i have available funds equal to £(\\d+)$")
    public void i_have_available_funds_equal_to_£(long amount) throws Throwable {
        MyLending myLending = new MyLending(this);
        assertThat(myLending.determineAvailableFunds()).isEqualTo(amount);
    }

    @Given("^i have available funds between £(\\d+) and £(\\d+)$")
    public void i_have_available_funds_between_£_and_£(int min, int max) throws Throwable {
        MyLending myLending = new MyLending(this);
        assertThat(myLending.determineAvailableFunds()).isGreaterThan(min).isLessThan(max).as("Must have available funds");
    }
}
