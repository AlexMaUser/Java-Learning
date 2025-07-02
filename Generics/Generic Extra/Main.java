import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        students.add(new LPAStudent());
//        printList(students);
        printMoreLists(students);


        System.out.println("-".repeat(30));
        List<LPAStudent> lpaStudents = new ArrayList<>();
        for(int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
//        printList(lpaStudents);
        printMoreLists(lpaStudents);

        testList(new ArrayList<String>(List.of("Able", "Barry", "Charlie")));
        testList(new ArrayList<Integer>(List.of(1, 2, 3)));

        System.out.println("=".repeat(30));
        var queryList = new QueryList<LPAStudent>(lpaStudents);
        var matches = queryList.getMatches("Course", "Python");
        printMoreLists(matches);

        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
        printMoreLists(students2021);
    }

    public static void printMoreLists(List<? extends Student> students) {
        for(var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static void testList(List<?> list) {
        for(var element : list) {
            if(element instanceof String) {
                System.out.println("String: " + ((String) element).toUpperCase());
            } else if (element instanceof Integer) {
                System.out.println("Integer: " + ((Integer) element).floatValue());
            }
        }
    }

//    public static void testList(List<String> list) {
//        for(var element : list) {
//            System.out.println("String " + element.toUpperCase());
//        }
//    }
//
//    public static void testList(List<Integer> list) {
//        for(var element : list) {
//            System.out.println("Integer " + element.floatValue());
//        }
//    }

//    public static <T extends Student> void printList(List<T> students) {
//        for(var student : students) {
//            System.out.println(student);
//        }
//        System.out.println();
//    }
}

