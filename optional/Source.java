package optional;
import java.util.Objects;

/**
 * Class used to instantiate sources with name, demand and type.
 * Contain constructors, getters, setter and overridden toString method.
 */
public abstract class Source {
    protected String name;
    protected int supply;

    // abstract method
    abstract void getType();

    // getters
    public String getName() {
        return this.name;
    }

    public int getSupply() {
        return this.supply;
    }

    // setters
    public void setName(String name) {
        this.name = name;
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
                '}';
    }

    /**
     * Override the equals method form the Object class for the Source class.
     * The problem should not allow adding the same source or destination twice.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // if both of them points the same address in memory
        if ( !(obj instanceof Source)) {  // if "obj" has not the same type
            return false;
        }
        Source source = (Source) obj;
        return Objects.equals(name, source.name) && Objects.equals(supply, source.supply);
    }
}