public class Main {
    public static void main(String[] args) {
        Account bobsAccount = new Account();
//        Account bobsAccount = new Account("12345", 1000.00, "Bob Brown", "bob@email.com", "(0232-260.194)");

        System.out.println(bobsAccount.getNumber());
        System.out.println(bobsAccount.getBalance());

//        bobsAccount.setNumber("12345");
//        bobsAccount.setBalance(1000.00);
//        bobsAccount.setCustomerName("Bob Brown");
//        bobsAccount.setCustomerEmail("bob@email.com");
//        bobsAccount.setCustomerPhone("0232.260.194");

        bobsAccount.withdrawFunds(100.0);
        bobsAccount.depositFunds(250);
        bobsAccount.withdrawFunds(50);
        System.out.println("Total funds " + bobsAccount.getBalance());

        bobsAccount.withdrawFunds(200);
        System.out.println("Total funds " + bobsAccount.getBalance());

        bobsAccount.depositFunds(100);
        bobsAccount.withdrawFunds(45.55);
        bobsAccount.withdrawFunds(54.46);
        bobsAccount.withdrawFunds(54.45);

        System.out.println();
        Account timsAccount = new Account("Tim", "tim@email.com", "0232260194");
        System.out.println("Account no: " + timsAccount.getNumber()  + "; name " + timsAccount.getCustomerName());
    }
}