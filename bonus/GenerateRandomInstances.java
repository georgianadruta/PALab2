package bonus;

import optional.Destination;
import optional.Factory;
import optional.Problem;
import optional.Source;
import optional.Warehouse;

public class GenerateRandomInstances {

    private final Source[] sources;
    private final Destination[] destinations;
    private final int[][] costMatrix;

    /**
     * constructor
     * generate random values for sources, destinations and cost Matrix
     * with the property: Total supply=Total demand
     *
     * @param n number of sources and destinations
     */
    public GenerateRandomInstances(int n) {
        this.sources = new Source[n];
        this.sources[0] = new Factory("S" + 0, (int) (Math.random() * 50) + 10);
        for (int i = 1; i < n; i++) { // generate random values for supply
            this.sources[i] = new Warehouse("S" + i, (int) (Math.random() * 50) + 10);
            //sum += this.sources[i].getSupply();
        }
        this.destinations = new Destination[n];
        for (int i = 0; i < n; i++) { // generate random values for supply
            this.destinations[i] = new Destination("D" + i, (int) (Math.random() * 50) + 10);
        }
        this.costMatrix = new int[n][n];
        for (int k = 0; k < n; k++) { // generate random values for cost matrix
            for (int j = 0; j < n; j++) {
                this.costMatrix[k][j] = (int) (Math.random() * 9) + 1;
            }
        }
    }

    /**
     * create the problem with sources, destinations and costMatrix
     * display the problem on the screen
     * create a solution using  the Vogel's approximation method
     * display on the screen the total cost
     */
    public void displaySolution() {
        Problem pb = new Problem(sources, destinations, costMatrix);
        pb.printProblem();
        Solution sol = new Solution(pb);
        sol.computeCost();
    }

    public boolean validateData() {
        for (int i = 0; i < this.costMatrix.length; i++)
            for (int j = 0; j < this.costMatrix.length; j++) {
                if (this.costMatrix[i][j] < 0)
                    return false;
            }
        return true;
    }
}
