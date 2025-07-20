package org.bd.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {

    @Test
    void testMinHeap() {
        Heap<Integer> heap = new Heap<>((Integer::compareTo));
        heap.add(4);
        heap.add(1);
        heap.add(2);
        assertEquals(1, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(4, heap.peek());
        assertEquals(4, heap.poll());
    }

    @Test
    void testMaxHeap() {
        Heap<Integer> heap = new Heap<>((i1,i2)->i2-i1);
        heap.add(4);
        heap.add(1);
        heap.add(2);
        assertEquals(4, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(1, heap.peek());
        assertEquals(1, heap.poll());
    }
}
