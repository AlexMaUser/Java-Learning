package dev.lpa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
//        Student tim = new Student("AU", 2019, 30, "M",
//                true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

//        Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
//                .limit(10)
//                .forEach(System.out::println);

        Student[] students = new Student[1000];
        Arrays.setAll(students, i -> Student.getRandomStudent(jmc, pymc));
//        var maleStudents = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
//                .limit(1000)
//                .filter(s -> s.getGender().equals("M"))
//                .count();

        var maleStudents = Arrays.stream(students)
                .filter(s -> s.getGender().equals("M"))
                .count();
        System.out.println("# male students = " + maleStudents);

        for (String gender : List.of("M", "F", "U")) {
            var myStudents = Arrays.stream(students)
                    .filter(s -> s.getGender().equals(gender));
            System.out.println("# of " + gender + " students " + myStudents.count());
        }

        List<Predicate<Student>> list = List.of(
                (s) -> s.getAge() < 30,
                (Student s) -> s.getAge() >= 30 && s.getAge() < 60);

        long total = 0;
        for (int i = 0; i < list.size(); i++) {
            var myStudents = Arrays.stream(students)
                    .filter(list.get(i));
            long count = myStudents.count();
            total += count;
            System.out.printf("# of students (%s) = %d%n", i == 0 ? " < 30" : " >= 30 & < 60", count);
        }
        System.out.println("# of students > 60 = " + (students.length - total));

        var ageStream = Arrays.stream(students)
                .mapToInt(s -> s.getAgeEnrolled());
        System.out.println("Stats for Enrollment Age = " + ageStream.summaryStatistics());

        var currentAgeStream = Arrays.stream(students)
                .mapToInt(s -> s.getAge());
        System.out.println("Stats for Enrollment Age = " + currentAgeStream.summaryStatistics());

        var countryCode = Arrays.stream(students)
                .map(s -> s.getCountryCode())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> (a + " " + b));
        System.out.println("Country code " + countryCode);

        System.out.println();

        boolean longTerm = Arrays.stream(students)
                .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12));
        System.out.println("long term students " + longTerm);

        long longTermCount = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .count();
        System.out.println("long term students " + longTermCount);

        var longTimeLearners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toArray(Student[]::new);

        var learners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList());
        Collections.shuffle(learners);
    }
}
