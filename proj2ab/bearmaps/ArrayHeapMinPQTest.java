package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        pq.add(3, 3);
        pq.add(4, 4);
        assertEquals(pq.size(), 4);

        ArrayHeapMinPQ<Integer> pq1 = new ArrayHeapMinPQ<>();
        pq1.add(3, 3);
        pq1.add(2, 2);
        pq1.add(1, 1);
        assertEquals((long) pq1.removeSmallest(), 1);

    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        pq.add(3, 3);
        pq.add(4, 4);
        assertTrue(pq.contains(1));
        assertFalse(pq.contains(5));
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        pq.add(3, 3);
        pq.add(4, 0);
        assertEquals(pq.size(), 4);
        assertEquals((long) pq.getSmallest(), 4);
        assertEquals(pq.size(), 4);

    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 0);
        pq.add(3, 3);
        pq.add(4, 2);

        assertEquals(pq.size(), 4);

        assertEquals((long) pq.removeSmallest(), 2);
        assertEquals(pq.size(), 3);

        assertEquals((long) pq.getSmallest(), 1);
        assertEquals(pq.size(), 3);

        assertEquals((long) pq.removeSmallest(), 1);
        assertEquals(pq.size(), 2);

        assertEquals((long) pq.removeSmallest(), 4);
        assertEquals(pq.size(), 1);

        assertEquals((long) pq.removeSmallest(), 3);
        assertEquals(pq.size(), 0);

    }

    @Test
    public void testSize() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        assertEquals(pq.size(), 0);
        pq.add(1, 1);
        pq.add(2, 0);
        pq.add(3, 3);
        pq.add(4, 2);
        assertEquals(pq.size(), 4);

        ArrayHeapMinPQ<Integer> pq1 = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i++) {
            pq1.add(i, i + 1);
        }
        assertEquals(pq1.size(), 20);
        assertEquals((long) pq1.removeSmallest(), 0);
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        assertEquals((long) pq.getSmallest(), 1);
        pq.changePriority(1, 5);
        assertEquals((long) pq.getSmallest(), 2);
    }
}
