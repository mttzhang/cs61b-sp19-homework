package bearmaps.proj2ab;

import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/**
 * Array based min heap priority queue.
 * @param <T>
 * @author Mengting Zhang
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    /**
     * Node class.
     * @param <T>
     */
    private class Node<T> {

        /**
         * item stored in node.
         */
        private T item;

        /**
         * priority of the item.
         */
        private double priority;

        /**
         * constructor of Node Class.
         * @param item
         * @param priority
         */
        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        /**
         * helper method to get item of a Node.
         * @return item
         */
        private T getItem() {
            return item;
        }

        /**
         * helper method tho get the priority of the Node.
         * @return priority
         */
        private double getPriority() {
            return priority;
        }
    }

    /**
     * array list of Node.
     */
    private Node[] items;
    /**
     * size of nodes.
     */
    private int size;

    /**
     * constructor of ArrayHeapMinPQ.
     */
    public ArrayHeapMinPQ() {
        items = new Node[10];
        items[0] = null;
        size = 0;
    }

    /**
     * resize the array.
     */
    private void reSizeUp() {
        if ((double) size() / items.length > 0.7) {
            Node[] temp = new Node[2 * items.length];
            for (int i = 0; i <= size(); i++) {
                temp[i] = items[i];
            }
            items = temp;
        }
    }
    /**
     * resize the array.
     */
    private void reSizeDown() {
        if ((double) size() / items.length < 0.2) {
            Node[] temp = new Node[items.length / 2];
            for (int i = 0; i <= size(); i++) {
                temp[i] = items[i];
            }
            items = temp;
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }

        reSizeUp();
        Node node = new Node(item, priority);
        size++;
        items[size] = node;
        swim(size());
    }

    @Override
    public boolean contains(T item) {
        for (int i = 1; i <= size; i++) {
            if (items[i].item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return (T) items[1].item;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        reSizeDown();
        T result = (T) items[1].item;
        swap(1, size());
        size--;
        sink(1);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        for (int i = 1; i <= size(); i++) {
            if (items[i].getItem().equals(item)) {
                items[i].priority = priority;
                swim(i);
                sink(i);
            }
        }
    }

    /**
     * get the parent's index of the Node Tree.
     * @param i current node's index
     * @return parent's index
     */
    private int getParentIndex(int i) {
        return i / 2;
    }

    /**
     * get the leftChild's index of the Node Tree.
     * @param p current node's index
     * @return leftChild's index
     */
    private int getLeftChildIndex(int p) {
        return p * 2;
    }
    /**
     * get the rightChild's index of the Node Tree.
     * @param p current node's index
     * @return rightChild's index
     */
    private int getRightChildIndex(int p) {
        return p * 2 + 1;
    }

    /**
     * check if the index is valid.
     * @param i current index
     * @return boolean reflecting whether a index is valid or not
     */
    private boolean validIndex(int i) {
        if (i < 1 || i > size) {
            return false;
        }
        return true;
    }

    /**
     * swap two nodes.
     * @param i node 1
     * @param j node 2
     */
    private void swap(int i, int j) {
        Node temp = items[i];

        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * swim up.
     * @param i current node's index
     */
    private void swim(int i) {
        int p = getParentIndex(i);
        if (!validIndex(i) || !validIndex(p)) {
            return;
        }
        if (items[p].getPriority() > items[i].getPriority()) {
            swap(p, i);
            swim(p);
        }
    }

    /**
     * sink down.
     * @param p current node's index
     */
    private void sink(int p) {
        int lC = getLeftChildIndex(p);
        int rC = getRightChildIndex(p);
        int min;
        if (!validIndex(p) || !validIndex(lC)) {
            return;
        }
        if (!validIndex(rC)) {
            min = lC;
        } else {
            if (items[lC].getPriority() < items[rC].getPriority()) {
                min = lC;
            } else if (items[lC].getPriority() == items[rC].getPriority()) {
                if (StdRandom.uniform() < 0.5) {
                    min = lC;
                } else {
                    min = rC;
                }
            } else {
                min = rC;
            }
        }
        if (items[p].getPriority() > items[min].getPriority()) {
            swap(p, min);
            sink(min);
        }
    }
}
