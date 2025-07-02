import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        boolean flag = true;
        ArrayList<String> groceries = new ArrayList<>();
        while (flag) {
            printActions();
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1 -> addItems(groceries);
                case 2 -> removeItems(groceries);
                default -> flag = false;
            }
            groceries.sort(Comparator.naturalOrder());
            System.out.println(groceries);
        }
    }

    private static void addItems(ArrayList<String> groceries) {
        System.out.println("Add item(s) separated by comma");
        String[] items = scanner.nextLine().split(",");

        for (String item : items) {
            String trimmed = item.trim();
            if (groceries.indexOf(trimmed) < 0) {
                groceries.add(trimmed);
            }
        }
//
//        groceries.addAll(List.of(items));
    }

    private static void removeItems(ArrayList<String> groceries) {
        System.out.println("Remove item(s) separated by comma");
        String[] items = scanner.nextLine().split(",");

        for (String item : items) {
            String trimmed = item.trim();
            groceries.remove(trimmed);
        }
    }

    private static void printActions() {
        String textBlock = """
                Available Actions: 
                
                0 - to shutdown
                
                1 - to add item(s) to list (separated by comma)
                
                2 - re move any item from the list (separated by comma);
                
                Enter an number for which action you want to perform""";
        System.out.print(textBlock + " ");
    }
}