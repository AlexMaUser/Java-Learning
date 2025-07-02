import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {
    private static int lastCustomerId = 10_000_000;
    private final String name;
    private final int customerID;
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerID = lastCustomerId++;
        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(BankAccount.AccountType.SAVINGS, savingsAmount));
    }

    public String getName() {
        return name;
    }

    public String getCustomerID() {
        return "%015d".formatted(customerID);
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    }

    public BankAccount getAccount(BankAccount.AccountType type) {
        for(var account : accounts) {
            if(account.getAccountType() == type) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n".formatted(name, customerID, String.join("\n\t", accountStrings));
    }
}
