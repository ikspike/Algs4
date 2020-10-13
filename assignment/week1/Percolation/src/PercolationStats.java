import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;


    public PercolationStats(int n, int trials)
    {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n or trials less than 0!");

        double[] probs = new double[trials];

        for (int i = 0; i < trials; i++)
        {
            Percolation p = new Percolation(n);
            while (!p.percolates())
            {
                p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            probs[i] = p.numberOfOpenSites() / (double) (n * n);
        }

        mean = StdStats.mean(probs);
        stddev = StdStats.stddev(probs);
        double confidenceFraction = (1.96 * stddev()) / Math.sqrt(n);
        confidenceLo = mean - confidenceFraction;
        confidenceHi = mean + confidenceFraction;
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

    private void show()
    {
        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = " + "[" + confidenceLo + "," + confidenceHi + "]");
    }

    public static void main(String[] args)
    {
        // test client (described below)
        int n = 200;
        int trials = 100;
        PercolationStats pstats = new PercolationStats(n, trials);
        pstats.show();

    }
}
