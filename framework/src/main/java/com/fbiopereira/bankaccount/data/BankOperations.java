package com.fbiopereira.bankaccount.data;

import model.Account;

public interface BankOperations {

    public void saveAccount(Account account);
    public Account findAccountByID(String id);
    public void resetBank();



}
