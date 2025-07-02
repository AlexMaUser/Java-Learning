import java.util.Arrays;

record Person (String name, String dob, Person[] kids) {
    public Person(Person p) {
        this(p.name(), p.dob(), p.kids == null ? null : Arrays.copyOf(p.kids, p.kids.length));
    }

    @Override
    public String toString() {
        return name + " kids " + Arrays.toString(kids);
    }
}
public class Main {
    public static void main(String[] args) {
        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[] {joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill  = new Person("Jill", "05/05/1965", new Person[] {joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};
//        Person[] personCopy = Arrays.copyOf(persons, persons.length);
        Person[] personCopy = new Person[5];
        Arrays.setAll(personCopy, i -> new Person(persons[i]));  // VARIANTA 3 DE DEEP COPY
//        for(int i = 0; i < 5; i++) {
//
//            // VARIANTA 1
////            Person current = persons[i];
////            var kids = current.kids() == null ? null : Arrays.copyOf(current.kids(), current.kids().length);
////            personCopy[i] = new Person(current.name(), current.dob(), kids);
//
////            VARIANTA 2
////            personCopy[i] = new Person(persons[i]);
//        }

        var jillsKids = personCopy[4].kids();
        jillsKids[1] = jane;
        for(int i = 0; i < 5; i++) {
            if(persons[i] == personCopy[i]) {
                System.out.println("Equal references " + persons[i]);
            }
        }

        System.out.println(persons[4]);
        System.out.println(personCopy[4
                ]);
    }
}