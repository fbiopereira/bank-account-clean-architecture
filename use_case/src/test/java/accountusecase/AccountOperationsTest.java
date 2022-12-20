package accountusecase;

import exceptions.AccountNotFoundException;
import model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountOperationsTest {

    @Test
    @DisplayName("Account Deposit Test")
    void accountDepositTest() {

        AccountOperationsImpl accountOperations = new AccountOperationsImpl();
        Account account = new Account("100");

        account = accountOperations.deposit(account, 100);
        assertEquals(100, account.getBalance());

        account = accountOperations.deposit(account, 200);
        assertEquals(300, account.getBalance());

    }

    @Test
    @DisplayName("Account Withdraw Test - Success")
    void accountWithdrawTest() {

        AccountOperationsImpl accountOperations = new AccountOperationsImpl();
        Account account = new Account("100");

        account = accountOperations.deposit(account, 100);
        assertEquals(100, account.getBalance());

        account = accountOperations.withdraw(account, 200);
        assertEquals(-100, account.getBalance());

    }

    @Test
    @DisplayName("Account Withdraw Test - Nullable account passed throws AccountNotFoundException")
    void accountWithdrawAccountNotFoundExceptionTest() {

        AccountOperationsImpl accountOperations = new AccountOperationsImpl();
        assertThrows(AccountNotFoundException.class, () -> accountOperations.withdraw(null, 200));

    }

}
