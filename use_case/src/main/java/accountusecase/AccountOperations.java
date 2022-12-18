package accountusecase;

import enums.AccountType;
import model.Account;

import java.util.Map;

public interface AccountOperations {

    public Account deposit(Account account, int amount);
    public Account withdraw(Account account, int amount);
    public Map<AccountType, Account> transfer(Account originAccount, Account destinationAccount, int amount);


/*
    public void saveAccount(Account account);
    public Account findAccountByID(String id);
    public void resetBank();

 */

}
