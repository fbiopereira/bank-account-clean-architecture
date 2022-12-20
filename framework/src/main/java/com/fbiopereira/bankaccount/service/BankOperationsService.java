package com.fbiopereira.bankaccount.service;

import accountusecase.AccountOperationsImpl;
import com.fbiopereira.bankaccount.component.account.AccountEventProcessor;
import com.fbiopereira.bankaccount.component.data.BankOperationsImpl;
import dto.BankOperationsResponse;
import dto.BankOperationsRequest;
import exceptions.AccountNotFoundException;
import model.Account;
import enums.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class BankOperationsService {



    @Autowired
    BankOperationsImpl bankOperations;

    @Autowired
    AccountEventProcessor accountEventProcessor;

    public void resetBank() {
        bankOperations.resetBank();
    }

    public ResponseEntity<Integer> balance(String accountId)  {

        try {
            Account account = bankOperations.findAccountByID(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(account.getBalance());
        }
        catch (AccountNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }

    }

    public ResponseEntity<Object> depositService(BankOperationsRequest bankOperationsRequest) {

        Account account;

       try {
           account = this.bankOperations.findAccountByID(bankOperationsRequest.getDestination());
       }
       catch (AccountNotFoundException exception){
           account = new Account(bankOperationsRequest.getDestination());
       }

       account = accountEventProcessor.deposit(bankOperationsRequest.getDestination(), bankOperationsRequest.getAmount());
       bankOperations.saveAccount(account);

       return ResponseEntity.status(HttpStatus.CREATED).body(new BankOperationsResponse(null, account));

    }
    public ResponseEntity<Object> withdrawService(BankOperationsRequest bankOperationsRequest){
        try{
            Account account = accountEventProcessor.withdraw(bankOperationsRequest.getOrigin(), bankOperationsRequest.getAmount());
            return ResponseEntity.status(HttpStatus.CREATED).body(new BankOperationsResponse(account, null));
        }
        catch (AccountNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }

    public ResponseEntity<Object> transferService(BankOperationsRequest bankOperationsRequest){

        try{
            Map<AccountType,Account> returnMap = accountEventProcessor.transfer(bankOperationsRequest.getOrigin(), bankOperationsRequest.getDestination(), bankOperationsRequest.getAmount());

            return ResponseEntity.status(HttpStatus.CREATED).body(new BankOperationsResponse(returnMap.get(AccountType.origin), returnMap.get(AccountType.destination)));
        }
        catch (AccountNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }

    }

}
