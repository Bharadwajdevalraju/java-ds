package org.bd.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void pushAndPop_MultipleElements() {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void push_StackOverflow_ThrowsException() {
        Stack<Integer> stack = new Stack<>(2);
        stack.push(1);
        stack.push(2);
        assertThrows(StackOverflowException.class, () -> stack.push(3));
    }

    @Test
    void pop_OnEmptyStack_ThrowsException() {
        Stack<String> stack = new Stack<>(2);
        assertThrows(ArrayIndexOutOfBoundsException.class, stack::pop);
    }

    @Test
    void peek_OnEmptyStack_ThrowsException() {
        Stack<Double> stack = new Stack<>(1);
        assertThrows(ArrayIndexOutOfBoundsException.class, stack::peek);
    }

    @Test
    void singleElementStackBehavior() {
        Stack<String> stack = new Stack<>(1);
        stack.push("A");
        assertEquals("A", stack.peek());
        assertEquals("A", stack.pop());
        assertThrows(ArrayIndexOutOfBoundsException.class, stack::pop);
    }

    @Test
    void interleavedPushPop() {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertThrows(ArrayIndexOutOfBoundsException.class, stack::pop);
    }
}