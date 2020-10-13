// Dev Log
// 2019.3.10
// Need: a function to parse coordinate(grid) to index(uf)
// !DONE
// Debug: Percolation construction method: Access can be private
// 2019.3.11
// Debug: Vacancy probability is lower than expected

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private static final boolean BLOCK = false;
    private static final boolean OPEN = true;
    // private static final boolean SHOW_GRAPH = true;

    private final WeightedQuickUnionUF uf;
    private final boolean[][] grid;
    private final int sideLength;    // Side length of the square
    private final int top;    // top virtual node
    private final int bottom; // bottom virtual node
    private int numOfOpenSites = 0;

    public Percolation(int n)
    {
        sideLength = n;
        // Initialize WQU with one top and one bottom site.
        uf = new WeightedQuickUnionUF(n * n + 2);
        top = n * n;
        bottom = n * n + 1;

        // Initialize n by n gird with all sites blocked
        // By convention, (1, 1) is the upper-left site
        grid = new boolean[n][n];
        for (int row = 0; row < n; row++)
            for (int col = 0; col < n; col++)
                grid[row][col] = BLOCK;
    }


    public void open(int row, int col)
    {
        int index = coor2index(row, col);

        if (!isOpen(row, col))
        {
            grid[row - 1][col - 1] = OPEN;
            numOfOpenSites++;

            // Connect the opened site to its neighbors.
            if (row == 1)   // First row
            {
                uf.union(index, index + sideLength);
                uf.union(index, top);
            }
            if (row == sideLength)  // Last row
            {
                uf.union(index, index - sideLength);
                uf.union(index, bottom);
            }

            connectIfOpen(index, row + 1, col);
            connectIfOpen(index, row - 1, col);
            connectIfOpen(index, row, col + 1);
            connectIfOpen(index, row, col - 1);
        }
    }

    public boolean isOpen(int row, int col)
    {
        // is site (row, col) open?
        return grid[row - 1][col - 1];
    }

    private void connectIfOpen(int currentIndex, int row, int col)
    {
        try
        {
            if (isOpen(row, col))
            {
                int neighborIndex = coor2index(row, col);
                uf.union(currentIndex, neighborIndex);
            }
        } catch (IndexOutOfBoundsException e)
        {
            // Don't connect field with field outside grid
        }
    }


    public boolean isFull(int row, int col)
    {
        int index = coor2index(row, col);
        return uf.find(top) == uf.find(index);
    }

    public int numberOfOpenSites()
    {
        // number of open sites
        return numOfOpenSites;

    }

    public boolean percolates()
    {
        // does the system percolate?
        return uf.find(top) == uf.find(bottom);
    }

    private int coor2index(int row, int col)
    {
        return sideLength * (row - 1) + (col - 1);
    }

    public static void main(String[] args)
    {
        // test client (optional)
        int inputN;
        if (args.length == 1)
            inputN = Integer.parseInt(args[0]);
        else
            inputN = StdIn.readInt();

        Percolation p = new Percolation(inputN);
        System.out.println("Percolate? " + p.percolates());

        while (!p.percolates())
        {
            p.open(StdRandom.uniform(inputN) + 1, StdRandom.uniform(inputN) + 1);
        }
        System.out.println("numberOfOpenSites: " + p.numberOfOpenSites());
        System.out.println("Percolate? " + p.percolates());
        double rate = p.numberOfOpenSites() * 1.0 / (inputN * inputN);
        System.out.println("Rate = " + rate);
    }
}
