package org.bd.queue;

import org.bd.linkedlist.ListNode;

import java.util.NoSuchElementException;

public class Queue<T> {

    private ListNode<T> front;
    private ListNode<T> rear;
    private int size;

    public void enqueue(T element) {
        var newNode = new ListNode<T>(element);
        size++;
        if (front == null && rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public T dequeue() {
        if (front == null) throw new NoSuchElementException("");
        var next = front.value;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

}
