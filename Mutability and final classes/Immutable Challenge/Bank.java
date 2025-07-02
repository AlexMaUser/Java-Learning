import java.util.HashMap;
import java.util.Map;

public class Bank {
    public final int routingNumber;
    private long lastTransactionId = 1;
    private final Map<String, BankCustomer> customers;

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
        this.customers = new HashMap<>();
    }

    public BankCustomer getCustomer(String id) {
        BankCustomer customer = customers.get(id);
        return customer;
    }

    public void addCustomer(String name, double checkInitialDeposit, double savingsInitialDeposit) {
        BankCustomer customer = new BankCustomer(name, checkInitialDeposit, savingsInitialDeposit);
        customers.put(customer.getCustomerID(), customer);
    }

    public boolean doTransaction(String id, BankAccount.AccountType type, double amount) {
        BankCustomer customer = customers.get(id);
        if(customer != null) {
            BankAccount account = customer.getAccount(type);
            if(account != null) {
                if((account.getBalance() + amount) < 0) {
                    System.out.println("Insufficient funds");
                } else {
                    account.commitTransaction(routingNumber, lastTransactionId++, id, amount);
                    return true;
                }
            }
        } else {
            System.out.println("Invalid customer id");
        }
        return false;
    }
}
