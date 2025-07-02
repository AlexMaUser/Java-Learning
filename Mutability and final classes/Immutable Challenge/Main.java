public class Main {
    public static void main(String[] args) {
//        BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING, 500);
//        System.out.println(account);

        Bank bank = new Bank(32144567);
        bank.addCustomer("Joe", 500.00, 10000.00);

        BankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);

        if(bank.doTransaction(joe.getCustomerID(), BankAccount.AccountType.CHECKING, 35)) {
            System.out.println(joe);
        }

        if(bank.doTransaction(joe.getCustomerID(), BankAccount.AccountType.CHECKING, -535)) {
            System.out.println(joe);
        }

        BankAccount checking = joe.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

//        transactions.put(3L, new Transaction(1, 1, Integer.parseInt(joe.getCustomerID()), 500));
        System.out.println("-------------------------------");
        for(var tx : transactions.values()) {
            tx.setCustomerId(2);
            tx.setAmount(10000.00);
        }
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("------------------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions()
                .forEach((k, v) -> System.out.println(k + " : " + v));

    }
}