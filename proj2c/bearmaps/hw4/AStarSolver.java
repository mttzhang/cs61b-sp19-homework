package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private int numStatesExplored;
    private double explorationTime;
    private ArrayHeapMinPQ<Vertex> queue;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    private ArrayList<Vertex> marked;
    private boolean find;

    /**
     * Constructor which finds the solution, computing everything necessary for all other methods
     * Results are returned in constant time.
     * Note that timeout passed in is in seconds.
     * @param input input graph
     * @param start source vertex
     * @param end goal
     * @param timeout time limited
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        find = false;
        numStatesExplored = 0;
        queue = new ArrayHeapMinPQ<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        marked = new ArrayList<>();

        distTo.put(start, 0.0);
        queue.add(start, 0 + input.estimatedDistanceToGoal(start, end));
        while ((queue.size() != 0) && (sw.elapsedTime() < timeout)) {
            Vertex curr = queue.removeSmallest();
            marked.add(curr);
            numStatesExplored++;

            if (curr.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                find = true;
                break;
            }
            List<WeightedEdge<Vertex>> neighborsEdge = input.neighbors(curr);
            for (WeightedEdge<Vertex> ve: neighborsEdge) {
                if (marked.contains(ve.to())) {
                    continue;
                }
                if (!distTo.containsKey(ve.to())) {
                    distTo.put(ve.to(), (double) Integer.MAX_VALUE);
                }
                if (distTo.get(curr) + ve.weight() < distTo.get(ve.to())) {
                    distTo.put(ve.to(), distTo.get(curr) + ve.weight());
                    edgeTo.put(ve.to(), curr);
                }
                if (queue.contains(ve.to())) {
                    queue.changePriority(ve.to(), distTo.get(ve.to()) + input.estimatedDistanceToGoal(ve.to(), end));
                } else {
                    queue.add(ve.to(), distTo.get(ve.to()) + input.estimatedDistanceToGoal(ve.to(), end));
                }
            }
        }
        solution = new ArrayList<>();
        if (queue.size() == 0 && !find) {
            outcome = SolverOutcome.UNSOLVABLE;
            solutionWeight = 0;
            explorationTime = sw.elapsedTime();
            return;
        }

        if (!(sw.elapsedTime() < timeout)) {
            outcome = SolverOutcome.TIMEOUT;
            solutionWeight = 0;
            explorationTime = sw.elapsedTime();
            return;
        }

        solution.add(start);
        if (numStatesExplored == 1) {
            return;
        }
        List<Vertex> solutionReverse = new ArrayList<>();

        Vertex v = edgeTo.get(end);
        while (v != start) {
            solutionReverse.add(v);
            v = edgeTo.get(v);
        }
        for (int i = solutionReverse.size() - 1; i >= 0; i--) {
            solution.add(solutionReverse.get(i));
        }
        solution.add(end);

        solutionWeight = distTo.get(end);
        explorationTime = sw.elapsedTime();

    }

    /**
     * Returns one of SolverOutcome.SOLVED, ***.TIMEOUT, or ***.UNSOLVABLE.
     * SOLVED if the AStarSolver could complete all work in given time.
     * UNSOLVABLE if the priority queue became empty.
     * TIMEOUT if the solver ran out of time.
     * Should check to see if you have run out of time every time you dequeue.
     * @return
     */
    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    /**
     * A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE
     * @return
     */
    @Override
    public List<Vertex> solution() {
        return solution;
    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    /**
     * The total number of priority queue dequeue operations.
     * @return
     */
    @Override
    public int numStatesExplored() {
        return numStatesExplored - 1;
    }

    /**
     * The total time spent in seconds by the constructor.
     * @return
     */
    @Override
    public double explorationTime() {
        return explorationTime;
    }
}
