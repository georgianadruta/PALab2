package optional;

public class Warehouse extends Source {

    public Warehouse() {
        this.name = "S0";
        this.supply = 1;
    }

    public Warehouse(String name, int supply) {
        this.name = name;
        this.supply = supply;
    }

    @Override
    public void getType() {
        System.out.println("Factory");
    }
}
