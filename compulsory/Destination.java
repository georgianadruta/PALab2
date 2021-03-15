package compulsory;

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
        this.name = "D1";
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

    //  overridden toString method
    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", demand=" + demand +
                '}';
    }
}
