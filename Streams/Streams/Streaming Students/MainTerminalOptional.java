package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MainTerminalOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(1000)
                .toList();

        int minAge  = 17;
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findAny()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("No student found under the age of " + minAge));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findFirst()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("No student found under the age of " + minAge));

        // cel mai bun. sortezi si pe urma gasesti primul
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .sorted(Comparator.comparing(Student::getAge))
                .findFirst()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("No student found under the age of " + minAge));

        // cel mai bun a2lea caz setezi  .min
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("No student found under the age of " + minAge));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("No student found under the age of " + minAge));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .mapToInt(s -> s.getAge())
                .average()
                .ifPresentOrElse(a -> System.out.printf("Avg age under 21: %.2f%n", a), () -> System.out.println("Didn't found anyone under 21"));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .map(s -> s.getCountryCode())
                .distinct()
                .reduce((a, b) -> String.join(",", a, b))
                .ifPresentOrElse(System.out::println, () -> System.out.println("none found"));

    }
}
