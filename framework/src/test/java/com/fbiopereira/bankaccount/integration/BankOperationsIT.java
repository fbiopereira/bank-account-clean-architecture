package com.fbiopereira.bankaccount.integration;
import com.fbiopereira.bankaccount.data.BankOperationsImpl;
import model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class BankOperationsIT {


    BankOperationsImpl bankOperationsImpl = new BankOperationsImpl();

    @Test
    @DisplayName("Bank Reset Test - Clear all accounts in bank")
    void resetBankTest() {

        Account account1 = new Account("100");
        bankOperationsImpl.saveAccount(account1);
        assertEquals(1, bankOperationsImpl.getBank().getAccounts().size());

        Account account2 = new Account("200");
        bankOperationsImpl.saveAccount(account2);
        assertEquals(2, bankOperationsImpl.getBank().getAccounts().size());

        Account account3 = new Account("300");
        bankOperationsImpl.saveAccount(account3);
        assertEquals(3, bankOperationsImpl.getBank().getAccounts().size());


        bankOperationsImpl.resetBank();
        assertEquals(0, bankOperationsImpl.getBank().getAccounts().size());

    }

}
