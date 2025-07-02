public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();
        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);

        System.out.println();
        Person joe = new Person("Joe", "01-01-1950");
        System.out.println(joe);

        Person joeCopy = new Person(joe);
        System.out.println(joeCopy);
        System.out.println();
        Generation g = Generation.BABY_BOOMER;

    }
}