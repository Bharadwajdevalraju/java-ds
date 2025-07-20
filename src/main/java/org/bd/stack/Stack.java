package org.bd.stack;

import org.bd.linkedlist.ListNode;

import java.util.NoSuchElementException;

public class Stack<T> {

    private ListNode<T> top;
    private int size;

    public void push(T element) {
        var newNode = new ListNode<T>(element);
        size++;
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    public T pop() {
        if (top == null) throw new NoSuchElementException("Stack is empty");
        var cur = top;
        top = top.next;
        size--;
        return cur.value;
    }

    public T peek() {
        if (top == null) throw new NoSuchElementException("Stack is empty");
        return top.value;
    }

    public boolean isEmpty() {
        return size != 0;
    }

    public int size() {
        return size;
    }
}
