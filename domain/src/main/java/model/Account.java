package model;


import enums.OperationType;
import exceptions.InvalidOperationException;
import java.util.Objects;
import static enums.AccountErrorMessages.INVALID_ACCOUNT_ID;
import static enums.AccountErrorMessages.TRANSFER_DENIED_IN_DOMAIN;

public class Account {

private String id;
private int balance;

public Account(String id){

    if (id != null) {
        this.id = id;
        this.balance = 0;
    }
    else{
        throw new InvalidOperationException(INVALID_ACCOUNT_ID.getCode(), INVALID_ACCOUNT_ID.getMessage());
    }
}


    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void doOperation(int amount, OperationType operation) {
        switch (operation) {
            case deposit -> this.balance = this.balance + amount;
            case withdraw -> this.balance = this.balance - amount;
            case transfer ->
                    throw new InvalidOperationException(TRANSFER_DENIED_IN_DOMAIN.getCode(), TRANSFER_DENIED_IN_DOMAIN.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
