package bonus;

import optional.*;

/**
 * Implement an algorithm in order to minimize the total cost, using the Vogel's approximation method.
 */
public class Solution {
    private int minimCost;
    private final Problem pb;
    private int[] rowPenalty;
    private int[] columnPenalty;

    /**
     * constructor
     *
     * @param pb an object from Problem class (contains the data of the problem: cost matrix, sources, destinations, supply and demand for each og them)
     */
    public Solution(Problem pb) {
        this.minimCost = 0;
        Source[] sources = new Source[3];
        sources[0] = new Factory("S1", 10);
        sources[1] = new Warehouse("S2", 35);
        sources[2] = new Warehouse("S3", 25);
        Destination[] destinations = new Destination[3];
        destinations[0] = new Destination("D1", 20);
        destinations[1] = new Destination("D2", 25);
        destinations[2] = new Destination("D3", 25);
        this.pb = new Problem(pb);
    }

    /**
     * For each column find the least value and then the second least value and take the absolute difference
     * of these two least values then write it in the array
     */
    public void computeColumnPenalty() {
        this.columnPenalty = new int[pb.costMatrix.length];
        for (int i = 0; i < pb.costMatrix.length; i++) {
            int minColumn1 = 10_000, minColumn2 = 10_000;
            for (int j = 0; j < pb.costMatrix.length; j++) {
                if (pb.costMatrix[j][i] > 0) {
                    if (pb.costMatrix[j][i] < minColumn1) {
                        minColumn2 = minColumn1;
                        minColumn1 = pb.costMatrix[j][i];
                    } else if (pb.costMatrix[j][i] < minColumn2) {
                        minColumn2 = pb.costMatrix[j][i];
                    }
                }
                this.columnPenalty[i] = Math.abs(minColumn1 - minColumn2);
            }
        }
    }

    /**
     * For each row find the least value and then the second least value and take the absolute difference
     * of these two least values then write it in the array
     */
    public void computeRowPenalty() {
        this.rowPenalty = new int[pb.costMatrix.length];
        for (int i = 0; i < pb.costMatrix.length; i++) {
            int minRow1 = 10_000, minRow2 = 10_000;
            for (int j = 0; j < pb.costMatrix.length; j++) {
                if (pb.costMatrix[i][j] > 0) {
                    if (pb.costMatrix[i][j] < minRow1) {
                        minRow2 = minRow1;
                        minRow1 = pb.costMatrix[i][j];
                    } else if (pb.costMatrix[i][j] < minRow2) {
                        minRow2 = pb.costMatrix[i][j];
                    }
                }
            }
            this.rowPenalty[i] = Math.abs(minRow1 - minRow2);
        }
    }

    /**
     * helping method for compute the total cost
     *
     * @param array rowPenalty or columnPenalty
     * @return position of the largest element in the array
     */
    private int indexMaxValue(int[] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > index && array[i] > 0)
                index = i;
        }
        return index;
    }

    /**
     * helping method for compute the total cost
     *
     * @param n the number of row
     * @return position of the smallest cost in the cost matrix
     */
    private int indexMinCostRow(int n) {
        int min = 10_000, index = 0;
        for (int i = 0; i < pb.costMatrix.length; i++) {
            if (pb.costMatrix[n][i] > 0 && pb.costMatrix[n][i] < min) {
                index = i;
                min = pb.costMatrix[n][i];
            }
        }
        return index;
    }

    /**
     * helping method for compute the total cost
     *
     * @param n the number of column
     * @return position of the smallest cost in the cost matrix
     */
    private int indexMinCostColumn(int n) {
        int min = 10_000, index = 0;
        for (int i = 0; i < pb.costMatrix.length; i++) {
            if (pb.costMatrix[i][n] > 0 && pb.costMatrix[i][n] < min) {
                index = i;
                min = pb.costMatrix[i][n];
            }
        }
        return index;
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
     * calculate the sum of elements from an array given as an argument
     */
    private int sumElements(int[] array) {
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum;
    }

    /**
     * select the maximum penalty from row penalties and column penalties
     * and find the cell with the least cost in row or column and recalculate supply and demand for this cell
     */
    private void foundCell() {
        int row = indexMaxValue(rowPenalty);
        int column = indexMaxValue(columnPenalty);
        if (rowPenalty[row] > columnPenalty[column]) {
            int index = indexMinCostRow(row);
            updateCost(row, index);
        } else {
            int index = indexMinCostColumn(column);
            updateCost(index, column);
        }
    }

    /**
     * computes the minimum cost using the Vogel's approximation method and display on the screen the total cost
     * penalty =  the absolute difference of these two least values
     * Repeat while demand > 0
     * For each row find the least value and then the second least value and take the absolute difference of these two least values, same for columns
     * Select the maximum penalty from row penalties and column penalties
     * Then find the cell with the least cost for the row or column corresponding to the found penalty
     * Recalculate supply and demand for this cell, if supply=0 eliminate the row, else the column
     */
    public void computeCost() {
        if (sumElements(pb.supplyArray) < sumElements(pb.demandArray))
            System.err.println("Total demand is too large.");
        else {
            while (continueCalculation()) { // continue the process if exists quantities to be transported from sources to destinations
                computeRowPenalty(); // for each row find the least value and then the second least value and take the absolute difference of these two least values
                computeColumnPenalty(); // for each column find the least value and then the second least value and take the absolute difference of these two least values
                foundCell(); // found the cell with the maximum penalty and the minimum cost
            }
            System.out.println("Total cost: " + this.minimCost);
        }
    }
}
