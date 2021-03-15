package optional;
import java.util.Objects;

/**
 * Class used to instantiate destinations with name and demand.
 * Contain constructors, getters, setter and overridden toString method.
 */
public class Destination {
    private String name;
    private int demand;

    // constructors

    /**
     * default constructor
     */
    public Destination() {
        this.name = "D0";
        this.demand = 1;
    }

    /**
     * @param name   destination name
     * @param demand each destination demands a certain amount of commodities.
     */
    public Destination(String name, int demand) {
        this.name = name;
        this.demand = demand;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public int getDemand() {
        return this.demand;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    // overridden toString method
    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", demand=" + demand +
                '}';
    }

    /**
     * Override the equals method form the Object class for the Source class.
     * The problem should not allow adding the same source or destination twice.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // if both of them points the same address in memory
        if (!(obj instanceof Destination)) {// if "obj" has not the same type
            return false;
        }
        Destination destination = (Destination) obj;
        return Objects.equals(name, destination.name) && Objects.equals(demand,destination.demand);
    }
}