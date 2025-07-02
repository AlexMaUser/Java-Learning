import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] numbers = readInteger();
        System.out.println(Arrays.toString(numbers));

        int minValue = minNumber(numbers);
        System.out.println("Minimum value is " + minValue);

//        reverse(numbers);
//        System.out.println("Final: " + Arrays.toString(numbers));

        int[] reverseCopy = reverseCopy(numbers);
        System.out.println("After reverse " + Arrays.toString(numbers));
        System.out.println("Reversed Copy + " + Arrays.toString(reverseCopy));
    }

    private static int[] readInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a list of integers, separated by comma");
        String input = scanner.nextLine();

        String[] splits = input.split(",");
        int[] values = new int[splits.length];
        for(int i = 0; i < splits.length; i++) {
            values[i] = Integer.parseInt(splits[i].trim());
        }
        return values;
    }

    private static int minNumber(int[] numbers) {
        int temp = Integer.MAX_VALUE;
        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] < temp) {
                temp = numbers[i];
            }
        }
        return temp;
    }

    private static void reverse(int[] array) {
        int maxIndex = array.length - 1;
        int half = array.length / 2;

        System.out.println("First = " + Arrays.toString(array));
        for(int i = 0; i < half; i++) {
            int temp = array[i];
            array[i] = array[maxIndex - i];
            array[maxIndex - i] = temp;
        }
    }

    private static int[] reverseCopy(int[] array) {
        int[] reverseArray = new int[array.length];
        int maxIndex = array.length - 1;
        for(int el : array) {
            reverseArray[maxIndex--] = el;
        }

        return reverseArray;
    }
}