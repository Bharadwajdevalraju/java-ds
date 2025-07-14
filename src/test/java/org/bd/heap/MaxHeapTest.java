package org.bd.heap;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {

    @Test
    void addAndPeek_MaxAtRoot() {
        MaxHeap heap = new MaxHeap(5);
        heap.add(10);
        heap.add(20);
        heap.add(5);
        assertEquals(20, heap.peek());
    }

    @Test
    void add_Poll_PeekOrder() {
        MaxHeap heap = new MaxHeap(5);
        heap.add(15);
        heap.add(30);
        heap.add(10);
        assertEquals(30, heap.poll());
        assertEquals(15, heap.peek());
        assertEquals(15, heap.poll());
        assertEquals(10, heap.poll());
    }

    @Test
    void heapMaintainsMaxProperty() {
        MaxHeap heap = new MaxHeap(4);
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        assertEquals(4, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(1, heap.poll());
    }

    @Test
    void add_ThrowsExceptionWhenFull() {
        MaxHeap heap = new MaxHeap(2);
        heap.add(1);
        heap.add(2);
        assertThrows(IllegalStateException.class, () -> heap.add(3));
    }

    @Test
    void peek_ThrowsExceptionWhenEmpty() {
        MaxHeap heap = new MaxHeap(1);
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void poll_ThrowsExceptionWhenEmpty() {
        MaxHeap heap = new MaxHeap(1);
        assertThrows(NoSuchElementException.class, heap::poll);
    }

    @Test
    void addNegativeNumbers() {
        MaxHeap heap = new MaxHeap(3);
        heap.add(-10);
        heap.add(-5);
        heap.add(-20);
        assertEquals(-5, heap.peek());
        assertEquals(-5, heap.poll());
        assertEquals(-10, heap.poll());
        assertEquals(-20, heap.poll());
    }

    @Test
    void addDuplicateValues() {
        MaxHeap heap = new MaxHeap(4);
        heap.add(7);
        heap.add(7);
        heap.add(7);
        heap.add(7);
        assertEquals(7, heap.poll());
        assertEquals(7, heap.poll());
        assertEquals(7, heap.poll());
        assertEquals(7, heap.poll());
    }

    @Test
    void pollAfterSingleAdd() {
        MaxHeap heap = new MaxHeap(1);
        heap.add(42);
        assertEquals(42, heap.poll());
        assertThrows(NoSuchElementException.class, heap::poll);
    }

    @Test
    void interleavedAddAndPoll() {
        MaxHeap heap = new MaxHeap(5);
        heap.add(4);
        heap.add(1);
        assertEquals(4, heap.poll());
        heap.add(3);
        heap.add(2);
        assertEquals(3, heap.peek());
        heap.add(5);
        assertEquals(5, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(1, heap.poll());
    }
}

