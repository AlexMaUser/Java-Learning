public class BaseClass {

    public final void recommendedMethod() {
        System.out.println("BaseClass. recommendedMethod");
        optionalMethod();
        mandatoryMethod();
    }

    protected void optionalMethod() {
        System.out.println("BaseClass. optionalMethod: customized method");
    }

    private void mandatoryMethod() {
        System.out.println("BaseClass. mandatory method: NON NEGOTIABLE");
    }

    public final static void recommendedStatic() {
        System.out.println("BaseClass. recommendedStatic: BEST WAY TO DO IT");
        optionalStatic();
        mandatoryStatic();
    }

    protected static void optionalStatic() {
        System.out.println("BaseClass optionalStatic: Optional");
    }

    private static void mandatoryStatic() {
        System.out.println("BaseClass mandatory static: Mandatory");
    }
}
