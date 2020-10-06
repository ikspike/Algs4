// Dev Log
// 2019.3.10
// Need: a function to parse coordinate(grid) to index(uf)
// !DONE
// Debug: Percolation construction method: Access can be private
// 2019.3.11
// Debug: Vacancy probability is lower than expected

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;
import java.util.Random;

public class Percolation
{
    private WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int SideLength;    // Side length of the square
    private int Top;    // Top virtual node
    private int Bottom; // Bottom virtual node
    private int NumOfOpenSites = 0;

    private static final boolean BLOCK = false;
    private static final boolean OPEN = true;

    private static final boolean show_graph = true;

    public Percolation(int n)
    {
        SideLength = n;
        // Initialize WQU with one top and one bottom site.
        uf = new WeightedQuickUnionUF(n * n + 2);
        Top = n * n;
        Bottom = n * n + 1;

        // Initialize n by n gird with all sites blocked
        // By convention, (1, 1) is the upper-left site
        grid = new boolean[n][n];
        for (boolean[] row : grid)
        {
            Arrays.fill(row, BLOCK);
        }

    }


    public void open(int row, int col)
    {
        int index = coor2index(row, col);

        if (!isOpen(row, col))
        {
            grid[row - 1][col - 1] = OPEN;
            NumOfOpenSites++;

            // Connect the opened site to its neighbors.
            if (row == 1)   // First row
            {
                uf.union(index, index + SideLength);
                uf.union(index, Top);
            }
            if (row == SideLength)  // Last row
            {
                uf.union(index, index - SideLength);
                uf.union(index, Bottom);
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

    private void connectIfOpen(int current_index, int row, int col)
    {
        try
        {
            if (isOpen(row, col))
            {
                int neighbor_index = coor2index(row, col);
                uf.union(current_index, neighbor_index);
            }
        } catch (IndexOutOfBoundsException e)
        {
            // Don't connect field with field outside grid
        }
    }


    public boolean isFull(int row, int col)
    {
        int index = coor2index(row, col);
        return uf.connected(index, Top);
    }

    public int numberOfOpenSites()
    {
        // number of open sites
        return NumOfOpenSites;

    }

    public boolean percolates()
    {
        // does the system percolate?
        return uf.connected(Top, Bottom);
    }

    private int coor2index(int row, int col)
    {
        return SideLength * (row - 1) + (col - 1);
    }

    public void reset()
    {
        for (boolean[] sites : grid)
        {
            for (boolean site : sites)
            {
                site = BLOCK;
            }
        }
    }

    private int mapRow(int row)
    {
        return row - 1;
    }

    private int mapCol(int col)
    {
        return col - 1;
    }

    public WeightedQuickUnionUF getUf()
    {
        return uf;
    }

    public static void main(String[] args)
    {
        // test client (optional)
        int N;
        if (args.length == 1)
            N = Integer.parseInt(args[0]);
        else
            N = StdIn.readInt();

        Percolation p = new Percolation(N);
        System.out.println("Percolate? " + p.percolates());
        Random r = new Random();
        while (!p.percolates())
        {
            p.open(r.nextInt(N) + 1, r.nextInt(N) + 1);
        }
        System.out.println("numberOfOpenSites: " + p.numberOfOpenSites());
        System.out.println("Percolate? " + p.percolates());
        double rate = p.numberOfOpenSites() * 1.0 / (N * N);
        System.out.println("Rate = " + rate);
    }
}
