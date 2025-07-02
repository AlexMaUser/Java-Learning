import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < start + 15; i++) {
                bingoPool.add("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------------------");

//        List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));

        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(i -> {
            if (i.indexOf('G') == 0 || (i.indexOf('O') == 0)) {
                String updated = i.charAt(0) + "-" + i.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return i;
        });
        System.out.println("\n------------------------------------------------");

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------------------");

        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted(Comparator.naturalOrder());
//                .forEach(s -> System.out.print(s + " "));
        tempStream.forEach(s -> System.out.print(s + " "));
        System.out.println("\n------------------------------------------------");

        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
//                .forEach(s-> System.out.print(s + " "));

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(s -> s.toUpperCase());
//                .forEach(s -> System.out.print(s + " "));

        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }

        myMap.entrySet()
                .stream()
                .map(s -> s.getKey() + " has range: " + s.getValue()[0] + " - " + s.getValue()[s.getValue().length - 1])
                .forEach(System.out::println);

        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, i -> i + 1)
                .filter(i -> isPrime(i))
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, i -> i + 1)
                .limit(100)
                .filter(i -> isPrime(i))
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, i -> i + 1)
                .filter(i -> isPrime(i))
                .limit(100)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, i -> i <= 100, i -> i + 1)
                .filter(i -> isPrime(i))
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.range(1, 101)
                .filter(i -> isPrime(i))
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.rangeClosed(1, 101)
                .filter(i -> isPrime(i))
                .forEach(s -> System.out.print(s + " "));
    }

    public static boolean isPrime(int number) {
        if(number <= 2) {
            return true;
        }

        for(int divider = 2; divider < number; divider++) {
            if(number % divider == 0) {
                return false;
            }
        }

        return true;
    }
}