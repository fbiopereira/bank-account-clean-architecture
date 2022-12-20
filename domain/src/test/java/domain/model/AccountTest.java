package domain.model;

import enums.OperationType;
import exceptions.InvalidOperationException;
import model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    @DisplayName("Account Creation Test - Success")
    void AccountCreation(){

        Account account = new Account("100");
        assertEquals("100", account.getId());
        assertEquals(0, account.getBalance());

        account.doOperation(100,OperationType.deposit);
        assertEquals(100, account.getBalance());

    }

    @Test
    @DisplayName("Account Operation Test - Deposit Success")
    void AccountOperationDepositSuccess(){

        Account account = new Account("100");
        assertEquals("100", account.getId());
        assertEquals(0, account.getBalance());

        account.doOperation(100,OperationType.deposit);
        assertEquals(100, account.getBalance());

    }

    @Test
    @DisplayName("Account Operation Test - Withdraw Success - negative number")
    void AccountOperationWithdrawSuccessNegativeNumber(){

        Account account = new Account("100");
        assertEquals("100", account.getId());
        assertEquals(0, account.getBalance());

        account.doOperation(100,OperationType.withdraw);
        assertEquals(-100, account.getBalance());

    }

    @Test
    @DisplayName("Account Operation Test - Transfer - Throws InvalidOperationException")
    void AccountOperationTransferInvalidOperationException(){

        Account account = new Account("100");

        assertEquals("100", account.getId());
        assertEquals(0, account.getBalance());

        assertThrows(InvalidOperationException.class, () -> {
            account.doOperation(100, OperationType.transfer);
        });
    }


}
