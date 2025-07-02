import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DayOfTheWeek weekDay = DayOfTheWeek.TUES;
        System.out.println(weekDay);
        System.out.println(weekDay.name());
        System.out.println(weekDay.ordinal());

        System.out.println("-".repeat(30));
        for(var day : DayOfTheWeek.values()) {
            System.out.println(day + " ordinal + " + day.ordinal() + " name " + day.name());
        }

        for(Topping topping : Topping.values()) {
            System.out.println(topping.name() + " : " + topping.getPrice());
        }
    }

    private static DayOfTheWeek getRandomDay() {
        int randomInteger = new Random().nextInt(7);
        var allDays = DayOfTheWeek.values();
        return allDays[randomInteger];
    }
}