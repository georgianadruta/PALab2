package optional;

/**
 * In this class is used The Least Cost Method. This method is used to obtain the initial feasible solution for the transportation problem.
 * Here, the allocation begins with the cell which has the minimum cost. The lower cost cells are chosen over the higher-cost cell with
 * the objective to have the least cost of transportation.
 * documentation: https://towardsdatascience.com/optimization-in-transportation-problem-f8137044b371
 */
public class Solution extends Problem {
    private int minimCost;
    private final Problem pb;

    /**
     * constructor
     *
     * @param pb an object from Problem class (contains the data of the problem: cost matrix, sources, destinations, supply and demand for each og them)
     */
    public Solution(Problem pb) {
        this.pb = new Problem(pb);
    }

    /**
     * a helpful method to find the minimum cost for each traverse in matrix after removing a row or column
     *
     * @return an array with index of the minimum cost from costMatrix
     */
    private int[] getIndex() {
        int min = 10_000;
        int[] index = new int[2];
        for (int i = 0; i < pb.costMatrix.length; i++) {
            for (int j = 0; j < pb.costMatrix.length; j++)
                if (pb.costMatrix[i][j] > 0 && pb.costMatrix[i][j] < min) {
                    index[0] = i;
                    index[1] = j;
                    min = pb.costMatrix[i][j];
                }
        }
        return index;
    }

    /**
     * checks if exists quantities to be transported from sources to destinations
     * if exist even one remained demand > 0
     * @return true exits at least one value left
     */
    private boolean continueCalculation() {
        for (int i = 0; i < pb.demandArray.length; i++) {
            if (pb.demandArray[i] > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * It does not eliminate it literally the row, just replaces values of the row with 0.
     *
     * @param row receive a row "to remove" it and modify the supplyArray
     */
    private void eliminateRow(int row) {
        for (int i = 0; i < pb.costMatrix.length; i++)
            pb.costMatrix[row][i] = 0;
        pb.supplyArray[row] = 0;
    }

    /**
     * It does not eliminate it literally the column, just replaces values of the column with 0.
     *
     * @param column receive a column "to remove" it and modify demandArray
     */
    private void eliminateColumn(int column) {
        for (int i = 0; i < pb.costMatrix.length; i++)
            pb.costMatrix[i][column] = 0;
        pb.demandArray[column] = 0;
    }

    /**
     * case: demand>supply
     * for the found cost is computed the partially cost and is modified the corresponding value of demand
     * then is eliminated the row
     *
     * @param row    element row
     * @param column element column
     */
    private void updateRow(int row, int column) {
        this.minimCost = this.minimCost + pb.costMatrix[row][column] * pb.supplyArray[row];
        System.out.println(pb.supplyArray[row] + " units * " + pb.costMatrix[row][column] + " cost = " + pb.supplyArray[row] * pb.costMatrix[row][column]);
        pb.demandArray[column] -= pb.supplyArray[row];
        eliminateRow(row); // Eliminate the row because supplyArray[row]=0 now
    }

    /**
     * case: supply<demand
     * for the found cost is computed the partially cost and is modified the corresponding value of supply
     * then is eliminated the column and demand
     *
     * @param row    element row
     * @param column element column
     */
    private void updateColumn(int row, int column) {
        this.minimCost = this.minimCost + pb.costMatrix[row][column] * pb.demandArray[column];
        System.out.println(pb.demandArray[column] + " units * " + pb.costMatrix[row][column] + " cost = " + pb.demandArray[column] * pb.costMatrix[row][column]);
        pb.supplyArray[row] -= pb.demandArray[column]; // Eliminate the column because demandArray[column]=0 now
        eliminateColumn(column);
    }

    /**
     * case: demand=supply
     * for the found cost is computed the partially cost and is modified the corresponding values of supply and demand
     * then is eliminated the row and column
     *
     * @param row    element row
     * @param column element column
     */
    private void updateRowColumn(int row, int column) {
        this.minimCost = this.minimCost + pb.costMatrix[row][column] * pb.supplyArray[row];
        System.out.println(pb.demandArray[column] + " units * " + pb.costMatrix[row][column] + " cost = " + pb.demandArray[column] * pb.costMatrix[row][column]);
        eliminateColumn(column); // Eliminate the row and column because supplyArray[row]=demandArray[column]
        eliminateRow(row);
    }

    /**
     * Compares the demand and supply of that cell and compute the cost for this transport.
     * If demand < supply then minimum cost+=cost of transporting * demand, else +=cost of transporting * supply
     * In function of supply and demand will eliminate one row and/or one column
     *
     * @param row    the row of the minimum cost
     * @param column the column of the minimum cost
     */
    private void updateCost(int row, int column) { //
        if (pb.demandArray[column] > pb.supplyArray[row]) {
            updateRow(row, column);
        } else {
            if (pb.demandArray[column] < pb.supplyArray[row]) {
                updateColumn(row, column);
            } else {
                updateRowColumn(row, column);
            }
        }
    }

    /**
     * computes the minimum cost using The Least Cost Method and display on the screen
     * S1 --> D3 : 10 units * 1 cost = 10
     * S2 --> D2 : 25 units * 4 cost = 100
     * S2 --> D1 : 10 units * 5 cost = 50
     * S3 --> D1 : 10 units * 5 cost = 50
     * S3 --> D3 : 15 units * 8 cost = 120
     * Total cost: 330
     */
    public void computeCost() {
        if (sumElements(pb.supplyArray) < sumElements(pb.demandArray))
            System.err.println("Total demand is too large.");
        else {
            while (continueCalculation()) { // continue the process if exists quantities to be transported from sources to destinations
                int[] index = getIndex();
                System.out.print(pb.sources[index[0]].getName() + " --> " + pb.destinations[index[1]] .getName()+ ": ");
                updateCost(index[0], index[1]); // Select the least value among all the costs
            }
            System.out.println("Total cost: " + this.minimCost);
        }
    }
}
