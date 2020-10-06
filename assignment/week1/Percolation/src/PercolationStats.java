import edu.princeton.cs.algs4.StdStats;

import java.util.Random;

public class PercolationStats
{
    private static int[] probs;

    public PercolationStats(int n, int trials)
    {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("N or trials less than 0!");

    }

    public double mean()
    {
        // sample mean of percolation threshold
        return 0;
    }

    public double stddev()
    {
        // sample standard deviation of percolation threshold
        return 0;
    }

    public double confidenceLo()
    {
        // low  endpoint of 95% confidence interval
        return 0;
    }

    public double confidenceHi()
    {
        // high endpoint of 95% confidence interval
        return 0;
    }

    public static void main(String[] args)
    {
        // test client (described below)
        int N = 20;
        int trails = 1;
        PercolationStats pstats = new PercolationStats(N, trails);
        probs = new int[trails];
        Percolation p = new Percolation(N);
        Random r = new Random();

        for (int i = 0; i < probs.length; i++)
        {
            while (!p.percolates())
            {
                p.open(r.nextInt(N - 1) + 1, r.nextInt(N - 1) + 1);
            }
            probs[i] = p.numberOfOpenSites() / N * N;
            p.reset();
        }
        System.out.println(StdStats.mean(probs));


    }


}
