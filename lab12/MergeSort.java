import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> result = new Queue<>();
        for (Item item: items) {
            Queue<Item> itemQ = new Queue<>();
            itemQ.enqueue(item);
            result.enqueue(itemQ);
        }
        return result;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> result = new Queue<>();
        int count = q1.size() + q2.size();
        for (int size = 0; size < count; size++) {
            Item item = getMin(q1, q2);
            result.enqueue(item);
        }
        return result;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest.
     *  the original Queue should remain unchanged.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {

        Queue<Queue<Item>> singleItemQ = makeSingleItemQueues(items);
        Queue<Item> firstHalf = new Queue<>();
        Queue<Item> lastHalf = new Queue<>();

        if (items.size() <= 1) {
            return items;
        }
        for (int i = 0; i < items.size(); i++) {
            if (i < items.size() / 2) {
                firstHalf.enqueue(singleItemQ.dequeue().dequeue());
                //firstHalf.enqueue(items.dequeue());
            } else {
                lastHalf.enqueue(singleItemQ.dequeue().dequeue());
            }

        }
        firstHalf = mergeSort(firstHalf);
        lastHalf = mergeSort(lastHalf);
        return mergeSortedQueues(firstHalf, lastHalf);
    }

    private static void main(String[] args) {
        Queue<String> students = new Queue<>();
        students.enqueue("Zac");
        students.enqueue("Alice");
        students.enqueue("Bob");
        students.enqueue("Eric");
        Queue<String> newOne = mergeSort(students);
        System.out.println(students);
        System.out.println(newOne);

        Queue<Integer> a = new Queue<>();
        a.enqueue(0);
        a.enqueue(2);
        a.enqueue(3);

        Queue<Integer> b = new Queue<>();
        b.enqueue(10);
        b.enqueue(11);

        Queue<Integer> c = mergeSortedQueues(a, b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        Queue<Integer> z = new Queue<>();
        z.enqueue(4);
        z.enqueue(3);
        z.enqueue(0);
        z.enqueue(4);
        System.out.println(z);
        System.out.println(mergeSort(z));

    }
}
