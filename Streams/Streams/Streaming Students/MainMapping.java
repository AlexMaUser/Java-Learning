package dev.lpa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainMapping {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);

        List<Student> students = IntStream.rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(pymc, jmc))
                .toList();

        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));
        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));
        System.out.println("------------------------------------");

        int minAge = 25;
        var youngerSet = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode,
                        Collectors.filtering(s -> s.getAge() <=  minAge, Collectors.toList())));
        youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));

        var experienced = students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgrammingExperience));
        System.out.println("experienced students = " + experienced.get(true).size());

        var expCount = students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgrammingExperience, Collectors.counting()));
        System.out.println("experienced students = " + expCount.get(true));

        var expCountAndActive = students.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.hasProgrammingExperience() && s.getMonthsSinceActive() == 0,
                        Collectors.counting()));
        System.out.println("experienced students = " + expCountAndActive.get(true));

        var multiLevel = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode, Collectors.groupingBy(Student::getGender)));

        multiLevel.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((key1, value1) -> {
                System.out.println("\t" + key1 + " " + value1.size());
            });
        });

        long studentBodyCount = 0;
        for(var list : experienced.values()) {
            studentBodyCount += list.size();
        }
        System.out.println("student body count = " + studentBodyCount);

        studentBodyCount = experienced.values().stream()
                .mapToInt(l -> l.size())
                .sum();
        System.out.println("student body count = " + studentBodyCount);

        studentBodyCount = experienced.values().stream()
                .map(l -> l.stream()
                        .filter(s -> s.getMonthsSinceActive() <= 3)
                        .count())
                .mapToLong(l -> l)
                .sum();
        System.out.println("student body count = " + studentBodyCount);

        long count = experienced.values().stream()
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students " + count);

        count = multiLevel.values().stream()
                .flatMap(m -> m.values().stream()
                        .flatMap(l -> l.stream()))
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students " + count);

        count = multiLevel.values().stream()
                .flatMap(m -> m.values().stream())
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students " + count);
    }
}
