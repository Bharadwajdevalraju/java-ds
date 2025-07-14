package org.bd.heap;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void addAndPeek_MinAtRoot() {
        MinHeap heap = new MinHeap(5);
        heap.add(10);
        heap.add(3);
        heap.add(7);
        assertEquals(3, heap.peek());
    }

    @Test
    void add_Poll_PeekOrder() {
        MinHeap heap = new MinHeap(5);
        heap.add(15);
        heap.add(4);
        heap.add(10);
        assertEquals(4, heap.poll());
        assertEquals(10, heap.peek());
        assertEquals(10, heap.poll());
        assertEquals(15, heap.poll());
    }

    @Test
    void heapMaintainsMinProperty() {
        MinHeap heap = new MinHeap(4);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(1, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(4, heap.poll());
    }

    @Test
    void add_ThrowsExceptionWhenFull() {
        MinHeap heap = new MinHeap(2);
        heap.add(1);
        heap.add(2);
        assertThrows(IllegalStateException.class, () -> heap.add(0));
    }

    @Test
    void peek_ThrowsExceptionWhenEmpty() {
        MinHeap heap = new MinHeap(1);
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void poll_ThrowsExceptionWhenEmpty() {
        MinHeap heap = new MinHeap(1);
        assertThrows(NoSuchElementException.class, heap::poll);
    }

    @Test
    void addNegativeNumbers() {
        MinHeap heap = new MinHeap(3);
        heap.add(-10);
        heap.add(-5);
        heap.add(-20);
        assertEquals(-20, heap.peek());
        assertEquals(-20, heap.poll());
        assertEquals(-10, heap.poll());
        assertEquals(-5, heap.poll());
    }

    @Test
    void addDuplicateValues() {
        MinHeap heap = new MinHeap(4);
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
        MinHeap heap = new MinHeap(1);
        heap.add(42);
        assertEquals(42, heap.poll());
        assertThrows(NoSuchElementException.class, heap::poll);
    }

    @Test
    void interleavedAddAndPoll() {
        MinHeap heap = new MinHeap(5);
        heap.add(4);
        heap.add(1);
        assertEquals(1, heap.poll());
        heap.add(3);
        heap.add(2);
        assertEquals(2, heap.peek());
        heap.add(0);
        assertEquals(0, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(4, heap.poll());
    }
}
