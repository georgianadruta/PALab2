package optional;

public class Main {

    public static void main(String[] args) {
        long start = 0;
        long end = 0;
        start = System.nanoTime();
        Source[] sources = new Source[3];
        sources[0] = new Factory("S1", 20);
        sources[1] = new Warehouse("S2", 35);
        sources[2] = new Warehouse("S3", 25);
        Destination[] destinations = new Destination[3];
        destinations[0] = new Destination("D1", 10);
        destinations[1] = new Destination("D2", 25);
        destinations[2] = new Destination("D3", 25);
        Problem pb = new Problem(sources, destinations, new int[][]{{2, 3, 1}, {5, 4, 8}, {5, 6, 8}});
        pb.printProblem();
        if (pb.checkCorrectData()) {
            Solution sol = new Solution(pb);
            sol.computeCost();
        } else
            System.err.println("Invalid input.");
            end = System.nanoTime();
        System.out.println(end - start + " nanoseconds.");
    }
}
