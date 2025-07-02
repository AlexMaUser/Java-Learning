public class Main {
    public static void main(String[] args) {
        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        System.out.println("----------------------------");
        childReferredToAsBase.recommendedMethod();
        System.out.println("----------------------------");
        child.recommendedMethod();

        System.out.println("=".repeat(30));
        parent.recommendedStatic();
        System.out.println("----------------------------");
        childReferredToAsBase.recommendedStatic();
        System.out.println("----------------------------");
        child.recommendedStatic();

        System.out.println("------------------------------------------------------");
        BaseClass.recommendedStatic();
        ChildClass.recommendedStatic();

        StringBuilder tracker = new StringBuilder("Step 1 is ABC");
        Logger.logToConsole(tracker.toString());
        tracker.append(", Step 2 is xyz.");
        Logger.logToConsole(tracker.toString());
        System.out.println("After logging, tracker = " + tracker);
    }
}