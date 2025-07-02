import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Integer five = 5;
        Integer[] numbers = {3, 4, 6, 5, 7, 8, 9};
        for(int i = 0; i < numbers.length; i++) {
            System.out.println("Comparing 5 with " + numbers[i] + " = " + five.compareTo(numbers[i]));
        }

        String banana = "banana";
        String[] fruit = {"apples", "banana", "pear", "BANANA"};

        for(String f : fruit) {
            int val = banana.compareTo(f);
            System.out.printf("%s %s %s: compareTo=%d%n", banana, (val == 0 ? "==" : (val < 0 ? "<" : ">")), f, val);
        }

        Arrays.sort(fruit);
        System.out.println(Arrays.toString(fruit));

        Student tim = new Student("Tim");
        Student[] students = {new Student("Zach"), new Student("Tim"), new Student("Ann")};
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

        Comparator<Student> com = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
//                return (o1.gpa + o1.name).compareTo(o2.gpa + o2.name);
                if(o1.gpa < o2.gpa) {
                    return 1;
                } else if(o2.gpa < o1.gpa) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        Arrays.sort(students, com);
        System.out.println(Arrays.toString(students));
    }
}

class Student implements Comparable<Student>{
    private static int LAST_ID = 1000;
    private static Random random = new Random();
    String name;
    private int id;
    protected double gpa;

    public Student(String name) {
        this.name = name;
        id = LAST_ID++;
        gpa = random.nextDouble(1.0, 4.1);
    }

    @Override
    public String toString() {
        return "%d -%s (%.2f)".formatted(id, name, gpa);
    }

    @Override
    public int compareTo(Student o) {
//        return name.compareTo(o.name);
        return Integer.valueOf(id).compareTo(Integer.valueOf(o.id));
    }
}