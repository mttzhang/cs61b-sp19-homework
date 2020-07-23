import java.util.ArrayList;
import java.util.List;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        List<Integer> negList = new ArrayList<>();
        List<Integer> posList = new ArrayList<>();
        for (int i: arr) {
            if (i < 0) {
                negList.add(-i);
            } else {
                posList.add(i);
            }
        }
        int[] negArr = new int[negList.size()];
        int[] posArr = new int[posList.size()];
        int index = 0;
        for (int j: negList) {
            negArr[index] = j;
            index++;
        }
        index = 0;
        for (int p: posList) {
            posArr[index] = p;
            index++;
        }
        int[] sortedNeg = new int[negArr.length];
        if (negArr.length != 0) {
            int[] sortedNegNeg = naiveCountingSort(negArr);
            sortedNeg = new int[sortedNegNeg.length];
            for (int k = sortedNegNeg.length - 1; k >= 0; k--) {
                sortedNeg[sortedNegNeg.length - 1 - k] = -sortedNegNeg[k];
            }
        }
        int[] sortedPos = new int[posArr.length];
        if (posArr.length != 0) {
            sortedPos = naiveCountingSort(posArr);
        }

        int[] result = new int[arr.length];
        index = 0;
        for (int x: sortedNeg) {
            result[index] = x;
            index++;
        }
        for (int y: sortedPos) {
            result[index] = y;
            index++;
        }
        return result;
    }
}
