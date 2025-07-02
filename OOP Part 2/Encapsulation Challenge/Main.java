public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(50, false);
        System.out.println("initial pages count = " + printer.getPagesPrinted());

        int pagesPrinted = printer.printPages(5);
        System.out.printf("Current job pages: %d, Printer total: %d%n", pagesPrinted, printer.getPagesPrinted());

        pagesPrinted = printer.printPages(10);
        System.out.printf("Current job pages: %d, Printer total: %d%n", pagesPrinted, printer.getPagesPrinted());
    }
}