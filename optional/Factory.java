package optional;

public class Factory extends Source {

    public Factory(){
        this.name="S0";
       this.supply=1;
    }
    public Factory(String name, int supply){
        this.name=name;
        this.supply=supply;
    }

    @Override
    public void getType(){
        System.out.println("Factory");
    }
}
