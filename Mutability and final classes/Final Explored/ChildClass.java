public class ChildClass extends BaseClass {
    @Override
    protected void optionalMethod() {
        System.out.println("CHILD CLASS EXTRA STUFF");
    }

    private void mandatoryMethod() {
        System.out.println("CHILD CLASS My own important stuff");
    }
}
