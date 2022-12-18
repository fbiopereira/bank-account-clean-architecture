package model;
import java.util.HashSet;
import java.util.Set;

public class Bank {


    private final Set<Account> accounts;

    public Bank() {
        accounts = new HashSet<>();
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

}



