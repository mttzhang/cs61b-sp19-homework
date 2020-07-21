import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 * @author Mengting Zhang
 */
public class BnBSolver {
    private List<Bear> unsortedBears;
    private List<Bed> unsortedBeds;
    private List<Pair> sortedPairs;
    private List<Bear> lessBears;
    private List<Bear> biggerBears;
    private List<Bed> lessBeds;
    private List<Bed> biggerBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        unsortedBears = bears;
        unsortedBeds = beds;
        sortedPairs = new LinkedList<>();
        lessBears = new LinkedList<>();
        biggerBears = new LinkedList<>();
        lessBeds = new LinkedList<>();
        biggerBeds = new LinkedList<>();
        if (!unsortedBears.isEmpty()) {
            sortBeds(unsortedBears.get(0), unsortedBeds);
            BnBSolver newLessSolver = new BnBSolver(this.lessBears, this.lessBeds);
            BnBSolver newBiggerSolver = new BnBSolver(this.biggerBears, this.biggerBeds);
            sortedPairs.addAll(newLessSolver.sortedPairs);
            sortedPairs.addAll(newBiggerSolver.sortedPairs);
        }

    }

    private void sortBeds(Bear bear, List<Bed> beds) {
        List<Bed> biggerBed = new LinkedList<>();
        List<Bed> equalBed = new LinkedList<>();
        List<Bed> lessBed = new LinkedList<>();
        for (Bed bed: beds) {
            if (bear.compareTo(bed) < 0) {
                biggerBed.add(bed);
            } else if (bear.compareTo(bed) > 0) {
                lessBed.add(bed);
            } else {
                equalBed.add(bed);
                sortBears(bed, unsortedBears);
                Pair pairs = new Pair(bear, bed);
                sortedPairs.add(pairs);
            }
        }
        this.lessBeds = lessBed;
        this.biggerBeds = biggerBed;
    }

    private void sortBears(Bed bed, List<Bear> bears) {
        List<Bear> biggerBears = new LinkedList<>();
        List<Bear> equalBears = new LinkedList<>();
        List<Bear> lessBears = new LinkedList<>();
        for (Bear bear: bears) {
            if (bed.compareTo(bear) < 0) {
                biggerBears.add(bear);
            } else if (bed.compareTo(bear) > 0) {
                lessBears.add(bear);
            } else {
                equalBears.add(bear);
            }
        }
        this.biggerBears = biggerBears;
        this.lessBears = lessBears;
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        List<Bear> sortedBears = new LinkedList<>();
        for (Pair pair: this.sortedPairs) {
            sortedBears.add((Bear) pair.first());
        }
        return sortedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        List<Bed> sortedBeds = new LinkedList<>();
        for (Pair pair: this.sortedPairs) {
            sortedBeds.add((Bed) pair.second());
        }
        return sortedBeds;
    }
}
