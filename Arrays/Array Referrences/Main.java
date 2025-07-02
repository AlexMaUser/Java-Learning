import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] myIntArray = new int[5];
        int[] anotherArray = myIntArray;

        myIntArray[2] = 100;
        modifyArray(myIntArray);
        System.out.println(Arrays.toString(anotherArray));


    }

    private static void modifyArray(int[] array) {
        array[1] = 2;

    }
}