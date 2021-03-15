package compulsory;

public class Main {
    public static void main(String[] args) {
        Source S1=new Source("S1",10, Source.sourceType.FACTORY);
        Source S2=new Source("S2",35, Source.sourceType.WAREHOUSE);
        Source S3=new Source("S3",25, Source.sourceType.WAREHOUSE);

        Destination D1=new Destination("D1",20);
        Destination D2=new Destination("D2",25);
        Destination D3=new Destination("D3",25);

        Problem pb = new Problem(new Source[] {S1,S2,S3}, new Destination[] {D1, D2, D3}, new int[][] {{2,3,1},{5,4,8},{5,6,8}});
        pb.printProblem();
    }
}
