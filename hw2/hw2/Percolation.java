package hw2;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private int n;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF gridOnlyTop;
    private boolean[] isOpen;
    private int upDotIndex;
    private int downDotIndex;
    private int size;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        n = N;
        size = 0;

        grid = new WeightedQuickUnionUF(N * N + 2);
        gridOnlyTop = new WeightedQuickUnionUF(N * N + 1);
        upDotIndex = N * N;
        downDotIndex = upDotIndex + 1;

        isOpen = new boolean[N * N];
        for (int i = 0; i < isOpen.length; i++) {
            this.isOpen[i] = false;
        }


    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col)   {
        if (!(0 <= row && row < n) || !(0 <= col && col < n)) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(row, col)) {
            return;
        }

        int index = xyTo1D(row, col);
        isOpen[index] = true;
        size++;
        if (index < n) {
            grid.union(index, upDotIndex);
            gridOnlyTop.union(index, n * n);
        }
        if ((n - 1) * n <= index) {
            grid.union(index, downDotIndex);
        }

        int[] neighbor = findNeighborIndex(row, col);
        for (int i : neighbor) {
            if (i > -1 && isOpen(i)) {
                grid.union(index, i);
                gridOnlyTop.union(index, i);
            }
        }
    }

    //return the index
    private int xyTo1D(int r, int c) {
        return r * n + c;
    }

    private int[] findNeighborIndex(int r, int c) {
        int i = xyTo1D(r, c);
        int [] neighbor  = {-1, -1, -1, -1};
        if (n <= i) {
            neighbor[0] = i - n;
        }
        if (i < n * (n - 1)) {
            neighbor[1] = i + n;
        }
        if (c != 0) {
            neighbor[2] = i - 1;
        }
        if (c != (n - 1)) {
            neighbor[3] = i + 1;
        }
        return neighbor;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = xyTo1D(row, col);
        return isOpen[index];
    }

    private boolean isOpen(int index) {
        return isOpen[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = xyTo1D(row, col);
        //grid.
        boolean isFull = gridOnlyTop.connected(index, upDotIndex);

        return isFull;
    }

    // number of open sites, constant time
    public int numberOfOpenSites() {
        return size;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(upDotIndex, downDotIndex);
    }

    public static void main(String[] args) {

    }

}
