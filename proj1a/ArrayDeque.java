public class ArrayDeque<T> {
    /**
     * instance variable
     */
    private T[] array;
    private int size;
    private int front;
    private int rear;
    private double ratio;

    /**
     * constructor
     */

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    /* autograder 2018 doesn't allow this constructor :(
    /**
     * Creates a deep copy of other.
     * @param other the array deque needed to be copied
     */
    /*
    public ArrayDeque(ArrayDeque other) {
        System.arraycopy(other.array, 0, array, 0, other.array.length);
        size = other.size;
        front = other.front;
        rear = other.rear;
    }
    */

    private void checkReSizeUp() {
        ratio = (double) size / (double) array.length;
        if (ratio > 0.8) {
            T[] newArray = (T[]) new Object[array.length * 2];
            for (int index = 0; index < size; index++) {
                newArray[index] = get(index);
            }
            front = 0;
            rear = size - 1;
            this.array = newArray;
        }
    }

    private void checkReSizeDown() {
        ratio = (double) size / (double) array.length;
        if (ratio < 0.3) {
            T[] newArray = (T[]) new Object[array.length / 2];
            for (int index = 0; index < size; index++) {
                newArray[index] = get(index);
            }
            front = 0;
            rear = size - 1;
            this.array = newArray;
        }
    }

    private void updatePointer() {
        if (rear >= array.length || front < 0 || front >= array.length || rear < 0) {
            this.front = Math.floorMod(this.front, array.length);
            this.rear = Math.floorMod(this.rear, array.length);
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
        if (size == 0) {
            array[front] = item;
            size++;
            return;
        }

        this.checkReSizeUp();
        front--;
        this.updatePointer();
        array[front] = item;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item the T type item needed to be added
     */
    public void addLast(T item) {
        if (size == 0) {
            array[rear] = item;
            size++;
            return;
        }

        this.checkReSizeUp();
        rear++;
        this.updatePointer();
        array[rear] = item;
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
        for (int index = 0; index < size; index++) {
            System.out.print(get(index) + " ");
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
        if (size == 1) {
            size--;
            return array[front];
        }
        this.checkReSizeDown();
        size--;
        front++;
        this.updatePointer();
        return array[Math.floorMod(front - 1, array.length)];
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
        if (size == 1) {
            size--;
            return array[rear];
        }
        this.checkReSizeDown();
        size--;
        rear--;
        this.updatePointer();
        return array[Math.floorMod(rear + 1, array.length)];
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * @param index the given position
     * @return the item at the given index
     */
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        if (index < 0 || index > size - 1) {
            return null;
        }
        index = Math.floorMod(index + front, array.length);
        return array[index];
    }
}
