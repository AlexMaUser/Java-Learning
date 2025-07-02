public class Parent {
    private final String name;
    private final String dob;
    protected final int siblings;

    static  {
        System.out.println("Static block ONLY ONCE AND ONCE ONLY PARENT");
    }

    {
//        name = "John Doe";
//        dob = "01/01/1900";

        System.out.println("In parent initializer");
    }

    public Parent() {
        this("John Doe", "01/01/1900", 4);
        System.out.println("In parent no args constructor");
    }

    public Parent(String name, String dob, int siblings) {
        this.name = name;
        this.dob = dob;
        this.siblings = siblings;
        System.out.println("In parent constructor");
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "name " + name + " dob " + dob;
    }
}
