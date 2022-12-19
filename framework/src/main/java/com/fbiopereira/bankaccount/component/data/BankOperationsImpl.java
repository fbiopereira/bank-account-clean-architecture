package com.fbiopereira.bankaccount.component.data;

import exceptions.AccountNotFoundException;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static enums.AccountErrorMessages.ACCOUNT_DOES_NOT_EXIST;

@Component
public class BankOperationsImpl implements BankOperations {

    @Autowired
    Bank bank;

    @Override
    public void saveAccount(Account account) {
        this.bank.getAccounts().remove(account);
        this.bank.getAccounts().add(account);
    }

    @Override
    public Account findAccountByID(String accountId) {

        Account accountFound = bank.getAccounts().stream().filter(account -> accountId.equals(account.getId()))
                .findFirst()
                .orElse(null);

        if (accountFound == null) {
            throw new AccountNotFoundException(ACCOUNT_DOES_NOT_EXIST.getCode(), ACCOUNT_DOES_NOT_EXIST.getMessage());
        }
        return accountFound;
    }

    @Override
    public void resetBank() {
        bank.getAccounts().clear();
    }

    public Bank getBank(){
        return bank;
    }
}
