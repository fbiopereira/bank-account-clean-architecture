package com.fbiopereira.bankaccount.integration.bddstep;
import com.fbiopereira.bankaccount.component.account.AccountEventProcessor;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class BankSteps {

    @Autowired
    AccountEventProcessor accountEventProcessor;


    @Given("I create an account with {string} and amount {int}")
    public void json_body(String accountId, int amount) {
        accountEventProcessor.deposit(accountId, amount);
    }

}
