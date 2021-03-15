package compulsory;

/**
 * Class used to instantiate sources with name, demand and type.
 * Contain constructors, getters, setter and overridden toString method.
 */
public class Source {
    private String name;
    private int supply;
    private sourceType type;

    public enum sourceType {
        WAREHOUSE, FACTORY
    }

    // constructors

    /**
     * default constructor
     */
    public Source() {
        this.name = "S1";
        this.supply = 1;
        this.type = sourceType.FACTORY;
    }

    /**
     * @param name   source name.
     * @param supply how many units of a commodity it is able to supply to the destinations.
     * @param type   a source can be a factory or a warehouse.
     */
    public Source(String name, int supply, sourceType type) {
        this.name = name;
        this.supply = supply;
        this.type = type;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public sourceType getType() {
        return this.type;
    }

    public int getSupply() {
        return this.supply;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(sourceType type) {
        this.type = type;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    //  overridden toString method
    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", supply=" + supply +
                ", type=" + type +
                '}';
    }
}
