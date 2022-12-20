package com.fbiopereira.bankaccount.component.account;

import accountusecase.AccountOperationsImpl;
import com.fbiopereira.bankaccount.component.data.BankOperationsImpl;
import dto.BankOperationsRequest;
import dto.BankOperationsResponse;
import enums.AccountType;
import exceptions.AccountNotFoundException;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

import static enums.AccountErrorMessages.ACCOUNT_DOES_NOT_EXIST;
import static enums.AccountType.destination;
import static enums.AccountType.origin;

@Component
public class AccountEventProcessor {


    @Autowired
    BankOperationsImpl bankOperations;

    AccountOperationsImpl accountOperations = new AccountOperationsImpl();

    public Account deposit(String destinationAccountId, int amount) {

        Account account;

        try {
            account = this.bankOperations.findAccountByID(destinationAccountId);
        }
        catch (AccountNotFoundException exception){
            account = new Account(destinationAccountId);
        }
        account = accountOperations.deposit(account, amount);
        bankOperations.saveAccount(account);

        return account;

    }

    public Account withdraw(String originAccountId, int amount) {

        try{
            Account account = bankOperations.findAccountByID(originAccountId);
            account = accountOperations.withdraw(account, amount);
            return account;
        }
        catch (AccountNotFoundException exception){
            throw new AccountNotFoundException(ACCOUNT_DOES_NOT_EXIST.getCode(), ACCOUNT_DOES_NOT_EXIST.getMessage());
        }
    }

    public Map<AccountType,Account> transfer(String originAccountId, String destinationAccountId, int amount){


        try{
            Account originAccount = withdraw(originAccountId, amount);
            Account destinationAccount = deposit(destinationAccountId, amount);
            EnumMap<AccountType,Account> returnMap = new EnumMap<>(AccountType.class);
            returnMap.put(origin, originAccount);
            returnMap.put(destination, destinationAccount);
            return returnMap;
        }
        catch (AccountNotFoundException exception){
            throw new AccountNotFoundException(ACCOUNT_DOES_NOT_EXIST.getCode(), ACCOUNT_DOES_NOT_EXIST.getMessage());
        }







    }
}
