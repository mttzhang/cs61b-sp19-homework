import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        arr.addFirst(0);
        assertEquals(0, (int) arr.get(0));
        arr.addFirst(1);
        assertEquals(0, (int) arr.get(1));
        assertEquals(1, (int) arr.get(0));


    }
    @Test
    public void testAddLast() {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        arr.addLast(0);
        assertEquals(0, (int) arr.get(0));
        arr.addLast(1);
        assertEquals(0, (int) arr.get(0));
        assertEquals(1, (int) arr.get(1));
    }
    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        assertEquals(true, arr.isEmpty());
        arr.addLast(0);
        assertEquals(false, arr.isEmpty());
        arr.removeFirst();
        assertEquals(true, arr.isEmpty());
    }

    @Test
    public void testSize() {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        assertEquals(0, arr.size());
        arr.addLast(0);
        assertEquals(1, arr.size());
        arr.removeFirst();
        assertEquals(0, arr.size());
    }

    @Test
    public void testPrintDeque() {
        System.out.println("testPrintDeque is running");
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        arr.printDeque();
        arr.addFirst(0);
        arr.addLast(1);
        arr.printDeque();

    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        assertEquals(null, arr.removeFirst());
        arr.addFirst(0);
        arr.addFirst(1);
        assertEquals(1, (int) arr.removeFirst());
        assertEquals(1, arr.size());


    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        assertEquals(null, arr.removeLast());
        arr.addFirst(0);
        arr.addFirst(1);
        assertEquals(0, (int) arr.removeLast());
        assertEquals(1, arr.size());
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        arr.addFirst(0);
        assertEquals(0, (int) arr.get(0));
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        arr.addFirst(0);
        arr.addFirst(1);
        arr.addFirst(2);
        arr.printDeque();
        arr.addFirst(2);
        arr.printDeque();
        arr.removeLast();
        arr.printDeque();
    }
}
