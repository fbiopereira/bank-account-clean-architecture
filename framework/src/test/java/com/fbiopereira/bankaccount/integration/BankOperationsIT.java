package com.fbiopereira.bankaccount.integration;
import com.fbiopereira.bankaccount.component.data.BankOperationsImpl;
import model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankOperationsIT {


    @Autowired
    BankOperationsImpl bankOperationsImpl;

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

    @Test
    @DisplayName("Bank Save One Account Test")
    void saveAccountTest() {
        Account account1 = new Account("100");
        bankOperationsImpl.saveAccount(account1);
        assertEquals(1, bankOperationsImpl.getBank().getAccounts().size());
    }

    @Test
    @DisplayName("Bank Save Same Account two times")
    void saveAccountTwoTimesTest() {
        Account account1 = new Account("100");
        bankOperationsImpl.saveAccount(account1);
        assertEquals(1, bankOperationsImpl.getBank().getAccounts().size());

        bankOperationsImpl.saveAccount(account1);
        assertEquals(1, bankOperationsImpl.getBank().getAccounts().size());
    }

}
