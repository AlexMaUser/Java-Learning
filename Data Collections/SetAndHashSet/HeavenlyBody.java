import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;
    private final BodyType bodyType;

    public enum BodyType {
        STAR, PLANET, DWARF_PLANET, MOON, COMET, ASTEROID
    }

    public HeavenlyBody(String name, double orbitalPeriod, BodyType bodyType) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        satellites = new HashSet<>();
        this.bodyType = bodyType;
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(satellites);
    }

    public boolean addSatellite(HeavenlyBody moon) {
        return this.satellites.add(moon);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof HeavenlyBody) {
            HeavenlyBody theObject = (HeavenlyBody) obj;
            if (this.name.equals(theObject.getName())) {
                return this.bodyType == theObject.getBodyType();
            }
        }

        return false;

//        if(this == obj) {
//            return true;
//        }
//        System.out.println("obj.getClass() is " + obj.getClass());
//        System.out.println("this.getClass() is " + this.getClass());
//
//        if((obj == null) || (obj.getClass() != this.getClass())) {
//            return false;
//        }
//
//        String objName = ((HeavenlyBody) obj).getName();
//        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
//        System.out.println("hashCode() called");
        return this.name.hashCode() * 31 + this.bodyType.hashCode();
    }

    @Override
    public String toString() {
        return this.name + ": " + this.bodyType + ", " + this.orbitalPeriod;
    }
}
