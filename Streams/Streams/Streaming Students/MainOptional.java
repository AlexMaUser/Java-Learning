package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(1000)
                .collect(Collectors.toList());

        Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
        System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);
        o1.ifPresentOrElse(System.out::println, () -> System.out.println("Nu-i prezent o1"));

        Optional<Student> o2 = getStudent(students, "first");
        System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
        System.out.println(o2);

        if (o2.isPresent()) {
            System.out.println(o2.get());
        }
        o2.ifPresent(System.out::println);
        System.out.println();
//        Student firstStudent = (o2.isPresent() ? o2.get() : null);
//        Student firstStudent = o2.orElse(getDummyStudent(jmc));
        Student firstStudent = o2.orElseGet(() -> getDummyStudent(jmc));
        long id = firstStudent.getStudentId();
        System.out.println("firstStudent's id is " + id);

        List<String> countries = students.stream()
                .map(s -> s.getCountryCode())
                .distinct()
                .toList();

        Optional.of(countries)
                .map(l -> String.join(",", l))
                .filter(l -> l.contains("FR"))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Missing FR"));


    }

    private static Optional<Student> getStudent(List<Student> list, String type) {
        if (list == null || list.size() == 0) {
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.get(0));
        } else if (type.equals("last")) {
            return Optional.ofNullable(list.get(list.size() - 1));
        }
        return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
    }

    private static Student getDummyStudent(Course... courses) {
        System.out.println("Getting the dummy student");
        return new Student("NO", 1, 1, "U", false, courses);
    }
}
