import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

public class PercolationStats
{
    private double[] probs;
    private int trials;

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;


    public PercolationStats(int N, int trials)
    {
        // perform trials independent experiments on an n-by-n grid
        if (N <= 0 || trials <= 0)
            throw new IllegalArgumentException("N or trials less than 0!");

        probs = new double[trials];
        this.trials = trials;
        Random r = new Random();

        for (int i = 0; i < trials; i++)
        {
            Percolation p = new Percolation(N);
            while (!p.percolates())
            {
                p.open(r.nextInt(N) + 1, r.nextInt(N) + 1);
            }
            probs[i] = p.numberOfOpenSites() / (double) (N * N);
        }

        mean = StdStats.mean(probs);
        stddev = StdStats.stddev(probs);
        confidenceLo = mean - (1.96 * stddev) / Math.sqrt(N);
        confidenceHi = mean + (1.96 * stddev) / Math.sqrt(N);
        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = " + "[" + confidenceLo + "," + confidenceHi + "]");

    }

    public double mean()
    {
        // sample mean of percolation threshold
        return mean;
    }

    public double stddev()
    {
        // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo()
    {
        // low endpoint of 95% confidence interval
        return confidenceLo;
    }

    public double confidenceHi()
    {
        // high endpoint of 95% confidence interval
        return confidenceHi;
    }

    public static void main(String[] args)
    {
        // test client (described below)
        int N = 200;
        int trials = 100;
        PercolationStats pstats = new PercolationStats(N, trials);

    }
}
