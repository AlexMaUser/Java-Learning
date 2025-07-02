package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class MainChallenge {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jGames = new Course("JGAME", "Create games in Java");

        List<Student> students = IntStream.rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(pymc, jmc))
                .toList();

        double totalPercent = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
//                .sum();
                .reduce(0, (a, b) -> a + b);
        System.out.println("total percent = " + totalPercent);

        double avgPercent = totalPercent / students.size();
        System.out.println("avg percent = " + avgPercent);

        int topPercent = (int) (1.25 * avgPercent);
        System.out.println("Best percent " + topPercent);


        Comparator<Student> longTermStudent = Comparator.comparing(Student::getYearEnrolled);

        List<Student> hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .toList();

        hardWorkers.forEach(s -> {
            s.addCourse(jGames);
            System.out.print(s.getStudentId() + " ");
        });
        System.out.println();

        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
//                .toList()
//                .collect(Collectors.toList())
//                .collect(Collectors.toSet())
                .collect(() -> new TreeSet<>(longTermStudent.thenComparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll)
                .forEach(s -> {
                    s.addCourse(jGames);
                    System.out.print(s.getStudentId() + " ");
                });
    }
}
