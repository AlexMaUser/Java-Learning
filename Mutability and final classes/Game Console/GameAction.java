import java.util.function.Predicate;

public record GameAction(Character key, String prompt, Predicate<Integer> action) {
}
