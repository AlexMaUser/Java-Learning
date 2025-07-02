import java.util.Arrays;

public class LivingPerson extends PersonImmutable {
    public LivingPerson(PersonImmutable person) {
        super(person);
    }

    public LivingPerson(String name, PersonImmutable[] kids) {
        super(name, null, kids == null ? new PersonImmutable[10] : Arrays.copyOf(kids, 10));
    }

    @Override
    public String getDob() {
        return null;
    }

    public void addKid(PersonImmutable person) {
        for(int i = 0; i < kids.length; i++) {
            if(kids[i] == null) {
                kids[i] = person;
                break;
            }
        }
    }
}
