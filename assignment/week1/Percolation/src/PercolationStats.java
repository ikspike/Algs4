import edu.princeton.cs.algs4.StdStats;
import java.util.Random;

public class PercolationStats {
    private static int[] probs;

    public PercolationStats(int n, int trials){
        // perform trials independent experiments on an n-by-n grid
        if(n <= 0 || trials <= 0)
            throw new IllegalArgumentException("N or trials less than 0!");

    }
    public double mean(){
        // sample mean of percolation threshold
        return 0;
    }
    public double stddev(){
        // sample standard deviation of percolation threshold
        return 0;
    }

    public double confidenceLo(){
        // low  endpoint of 95% confidence interval
        return 0;
    }

    public double confidenceHi(){
        // high endpoint of 95% confidence interval
        return 0;
    }

    public static void main(String[] args){
        // test client (described below)
        int n = 20;
        int trails = 1000;
        PercolationStats pstats = new PercolationStats(n, trails);
        probs = new int[trails];
        Percolation p = new Percolation(n);
        Random r = new Random();

        for (int i = 0; i < probs.length; i++)
        {
            while(!p.percolates()){
                p.open(r.nextInt(n-1) + 1, r.nextInt(n-1) + 1);
            }
            probs[i] = p.numberOfOpenSites() / n * n;
            p.reset();
        }
        System.out.println(StdStats.mean(probs));


    }


}
