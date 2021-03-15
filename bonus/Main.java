package bonus;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long start = 0, end = 0;
        start = System.nanoTime();
        System.out.println("Enter a number.");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n < 0)
            System.err.println("Invalid number.");
        GenerateRandomInstances instance = new GenerateRandomInstances(n);
        if (instance.validateData()) {
            instance.displaySolution();
        } else {
            System.err.println("Invalid input.");
        }
        end = System.nanoTime();
        System.out.println(end - start + " nanoseconds.");
    }
}
