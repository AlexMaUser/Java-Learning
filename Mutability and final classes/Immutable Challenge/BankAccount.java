import java.util.LinkedHashMap;
import java.util.Map;

public class BankAccount {
    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;
    private double balance;
    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();

    public BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Long, String> getTransactions() {
        return Map.copyOf(transactions);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        balance += amount;
        transactions.put(transactionId, new Transaction(routingNumber, transactionId, Integer.parseInt(customerId), amount));
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }
}
