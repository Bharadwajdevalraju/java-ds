package org.bd.stack;

public class Stack<T> {

    private final T[] arr;
    private int top = -1;

    public Stack(final int size) {
        this.arr = (T[]) new Object[size];
    }

    public void push(T element) {
        if (top >= arr.length-1) {
            throw new StackOverflowException("Exceeded max size");
        }
        arr[++top] = element;
    }

    public T pop() {
        var element = peek();
        top--;
        return element;
    }

    public T peek() {
        return arr[top];
    }
}
