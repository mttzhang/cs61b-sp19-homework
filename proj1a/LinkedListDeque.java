public class LinkedListDeque<T> {

    private class TNode<T> {
        private T item;
        private TNode front;
        private TNode next;

        private TNode(T i, TNode f, TNode n) {
            this.item = i;
            this.front = f;
            this.next = n;
        }
    }

    /**
     * instance variable
     */

    private TNode sentinel;
    private int size;

    /**
     * constructor
     */

    /**
     * Creates an empty linked list deque
     */
    public LinkedListDeque() {
        sentinel = new TNode(null, sentinel, sentinel);
        size = 0;
    }

    /**
     * Creates a deep copy of other
     * @param other the LinkedListDeque needed to be copied
     */
    private LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode(null, sentinel, sentinel);
        this.size = 0;
        int s = other.size;
        TNode otherNode = other.sentinel;
        while (s != 0) {
            otherNode = otherNode.next;
            this.addLast((T) otherNode.item);
            s--;
        }
    }

    /**
     * Deque API
     */

    /**
     * Adds an item of type T to the front of the deque.
     * @param item the T item needed to be added
     */
    public void addFirst(T item) {
        if (isEmpty()) {
            TNode first = new TNode(item, sentinel, sentinel);
            sentinel.front = first;
            sentinel.next = first;
            size++;
            return;
        }
        TNode nextNode = sentinel.next;
        TNode first = new TNode(item, sentinel, nextNode);
        sentinel.next = first;
        nextNode.front = first;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item the T type item needed to be added
     */
    public void addLast(T item) {
        if (isEmpty()) {
            TNode last = new TNode(item, sentinel, sentinel);
            sentinel.front = last;
            sentinel.next = last;
            size++;
            return;
        }
        TNode formerNode = sentinel.front;
        TNode last = new TNode(item, formerNode, sentinel);
        sentinel.front = last;
        formerNode.next = last;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return a boolean
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the number of items in the deque.
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int s = size;
        TNode current = sentinel;
        while (s != 0) {
            current = current.next;
            System.out.print(current.item + " ");
            s--;
        }
        System.out.println("\n");

    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return the item at the front of the deque
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        T firstItem = (T) sentinel.next.item;
        sentinel.next.next.front = sentinel;
        sentinel.next = sentinel.next.next;
        return firstItem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return item at the back of the deque
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        T lastItem = (T) sentinel.front.item;
        sentinel.front.front.next = sentinel;
        sentinel.front = sentinel.front.front;
        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * @param index the given position
     * @return the item at the given index
     */
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        TNode theNode = sentinel.next;
        while (index != 0) {
            theNode = theNode.next;
            index--;
        }
        return (T) theNode.item;
    }

    /**
     * get the item in the given position, using recursive way
     * @param index the given position
     * @return the item at the given index
     */
    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }

        if (index == 0) {
            return (T) sentinel.next.item;
        }

        LinkedListDeque copy = new LinkedListDeque(this);

        copy.removeFirst();
        return (T) copy.getRecursive(index - 1);
    }
}
