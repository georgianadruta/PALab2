package compulsory;

import java.util.Arrays;

/**
 * Create and print on the screen the instance of the problem described in the example
 *           D1     D2     D3     Supply
 *   S1      2      3      1      10
 *   S2      5      4      8      35
 *   S3      5      6      8      25
 *   Demand  20     25     25
 */
public class Problem {

    private Source[] sources;
    private Destination[] destinations;

    private int[] supplyArray;
    private int[] demandArray;
    private int[][] costMatrix;

    // constructors

    /**
     * default constructor with one element for each field.
     */
    public Problem(){
        this.sources = new Source[1];
        this.sources[0]=new Source();
        this.supplyArray = new int[1];
        this.supplyArray[0]=this.sources[0].getSupply();
        this.destinations=new Destination[1];
        this.destinations[0]=new Destination();
        this.demandArray=new int[1];
        this.demandArray[0]=this.destinations[0].getDemand();
        this.costMatrix=new int[1][1];
        this.costMatrix[0][0]= 0;
    }

    /**
     * constructor
     * @param sources an array with objects of Source
     * @param destinations an array with objects of Destination
     * @param costMatrix the costs of transporting a unit of commodity from each source to each destination
     */
    public Problem(Source[] sources, Destination[] destinations, int[][] costMatrix) {
        this.sources = new Source[sources.length];
        this.supplyArray = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            this.sources[i] = sources[i];
            this.supplyArray[i] = sources[i].getSupply();
        }
        this.destinations = new Destination[destinations.length];
        this.demandArray = new int[destinations.length];
        for (int i = 0; i < destinations.length; i++) {
            this.destinations[i] = destinations[i];
            this.demandArray[i] = destinations[i].getDemand();
        }
        this.costMatrix = new int[costMatrix.length][costMatrix.length];
        for (int i = 0; i < costMatrix.length; i++)
            System.arraycopy(costMatrix[i], 0, this.costMatrix[i], 0, costMatrix.length);
    }

    // getters
    public void getSources() {
        for (Source source : this.sources) {
            System.out.print(source.getName() + " ");
        }
    }

    public void getDestinations() {
        for (Destination destination : this.destinations) {
            System.out.print(destination.getName() + "     ");
        }
    }

    public void getSupplyArray() {
        for (int j : this.supplyArray) {
            System.out.print(j + " ");
        }
    }

    public void getDemandArray() {
        for (int j : this.demandArray) {
            System.out.print(j + "     ");
        }
    }

    public void getCostMatrix() {
        for (int[] matrix : this.costMatrix) {
            for (int j = 0; j < this.costMatrix.length; j++) {
                System.out.print(matrix[j] + " ");
            }
            System.out.println();
        }
    }

    // setters
    public void setSources(Source[] sources) {
        this.sources = new Source[sources.length];
        System.arraycopy(sources, 0, this.sources, 0, sources.length);
    }

    public void setDestinations(Destination[] destinations) {
        this.destinations = new Destination[destinations.length];
        System.arraycopy(destinations, 0, this.destinations, 0, destinations.length);
    }

    public void setSupplyArray(Source[] sources) {
        this.supplyArray = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            this.supplyArray[i] = sources[i].getSupply();
        }
    }

    public void setDemandArray(Destination[] destinations) {
        this.demandArray = new int[destinations.length];
        for (int i = 0; i < destinations.length; i++) {
            this.demandArray[i] = destinations[i].getDemand();
        }
    }

    public void setCostMatrix(int[][] costMatrix) {
        this.costMatrix = new int[costMatrix.length][costMatrix.length];
        for (int i = 0; i < costMatrix.length; i++)
            System.arraycopy(costMatrix[i], 0, this.costMatrix[i], 0, costMatrix.length);
    }

    /** print the supplyArray, demandArray and costMatrix as a table.
     * Output:
     *         D1     D2     D3     Supply
     * S1      2      3      1      10
     * S2      5      4      8      35
     * S3      5      6      8      25
     * Demand  20     25     25
     */
    public void printProblem() {
        System.out.print("        ");
        getDestinations();
        System.out.print("Supply\n");
        for (int i = 0; i < this.costMatrix.length; i++) {
            System.out.print(this.sources[i].getName() + "      ");
            for(int j=0;j<this.costMatrix.length;j++){
                System.out.print(this.costMatrix[i][j] + "      ");
            }
            System.out.print(this.supplyArray[i] + "  \n");
        }
        System.out.print("Demand  ");
        getDemandArray();
    }

    @Override
    public String toString() {
        return "Problem{" +
                "sources=" + Arrays.toString(sources) +
                ", destinations=" + Arrays.toString(destinations) +
                ", supplyArray=" + Arrays.toString(supplyArray) +
                ", demandArray=" + Arrays.toString(demandArray) +
                ", costMatrix=" + Arrays.toString(costMatrix) +
                '}';
    }
}
