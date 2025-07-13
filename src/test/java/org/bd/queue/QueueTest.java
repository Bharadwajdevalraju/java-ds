package org.bd.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class QueueTest {

    @Test
    void enqueueAndDequeue_MultipleElements() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.getSize());
        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void dequeueFromEmptyQueue_ThrowsException() {
        Queue<String> queue = new Queue<>();
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void isEmptyAndSizeBehavior() {
        Queue<Double> queue = new Queue<>();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());

        queue.enqueue(1.1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.getSize());

        queue.dequeue();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
    }

    @Test
    void singleElementQueueBehavior() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("OnlyOne");
        assertEquals(1, queue.getSize());
        assertFalse(queue.isEmpty());
        assertEquals("OnlyOne", queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
    }

    @Test
    void interleavedEnqueueDequeue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        queue.enqueue(3);
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
