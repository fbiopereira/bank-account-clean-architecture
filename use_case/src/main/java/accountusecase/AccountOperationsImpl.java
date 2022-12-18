package accountusecase;

import enums.AccountType;
import exceptions.AccountNotFoundException;
import model.Account;

import java.util.EnumMap;
import java.util.Map;

import static enums.AccountErrorMessages.ACCOUNT_DOES_NOT_EXIST;
import static enums.AccountType.destination;
import static enums.AccountType.origin;
import static enums.OperationType.deposit;
import static enums.OperationType.withdraw;


public class AccountOperationsImpl implements AccountOperations {

    @Override
    public Account deposit(Account account, int amount) {

        account.doOperation(amount, deposit);
        return account;
    }

    @Override
    public Account withdraw(Account account, int amount) {

        if (account != null){
            account.doOperation(amount, withdraw);
            return account;
        }
        else{
            throw new AccountNotFoundException(ACCOUNT_DOES_NOT_EXIST.getCode(), ACCOUNT_DOES_NOT_EXIST.getMessage());
        }
    }


    @Override
    public Map<AccountType, Account> transfer(Account originAccount, Account destinationAccount, int amount) {
        if (originAccount != null) {
            originAccount = this.withdraw(originAccount, amount);
            destinationAccount = this.deposit(destinationAccount, amount);

            EnumMap<AccountType, Account> returnMap = new EnumMap<>(AccountType.class);

            returnMap.put(origin, originAccount);
            returnMap.put(destination, destinationAccount);

            return returnMap;
        }
        else{
            throw new AccountNotFoundException(ACCOUNT_DOES_NOT_EXIST.getCode(), ACCOUNT_DOES_NOT_EXIST.getMessage());
        }

    }


}
