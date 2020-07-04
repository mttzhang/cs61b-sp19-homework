package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] thresholds;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        thresholds = new double[T];
        for (int t = 0; t < T; t++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            double threshold = p.numberOfOpenSites() / N * N;
            thresholds[t] = threshold;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (double threshold : thresholds) {
            sum += threshold;
        }
        return sum / thresholds.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();

        double sum = 0;

        for (double threshold : thresholds) {
            sum += Math.pow((threshold - mean), 2);
        }
        return sum / (thresholds.length - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mean = mean();
        double sigma = stddev();
        return mean - (1.96 * sigma) / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(thresholds.length);
    }


}
