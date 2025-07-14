package org.bd.heap;

import java.util.NoSuchElementException;

public class MaxHeap {

    private int size;
    private final int capacity;
    private final int[] heap;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    private int leftChildIndex(int i) {
        return 2 * i + 1;
    }

    private int rightChildIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasParent(int i) {
        return parentIndex(i) >= 0;
    }

    private boolean hasLeftChild(int i) {
        return leftChildIndex(i) < size;
    }

    private boolean hasRightChild(int i) {
        return rightChildIndex(i) < size;
    }

    private int parent(int i) {
        return heap[parentIndex(i)];
    }

    private int leftChild(int i) {
        return heap[leftChildIndex(i)];
    }

    private int rightChild(int i) {
        return heap[rightChildIndex(i)];
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void add(int element) {
        if (size == capacity) throw new IllegalStateException("Heap Full");
        heap[size++] = element;
        heapifyUp();
    }

    private void heapifyUp() {
        int i = size - 1;
        while (hasParent(i) && parent(i) < heap[i]) {
            swap(parentIndex(i), i);
            i = parentIndex(i);
        }
    }

    public int peek() {
        if (size == 0) throw new NoSuchElementException();
        return heap[0];
    }

    public int poll() {
        if (size == 0) throw new NoSuchElementException();
        int value = heap[0];
        heap[0] = heap[size-1];
        size--;
        heapifyDown();
        return value;
    }

    private void heapifyDown() {
        int i = 0;
        while (hasLeftChild(i)) {
            int bigIndex = leftChildIndex(i);
            if(hasRightChild(i) && leftChild(i) < rightChild(i)) {
                bigIndex = rightChildIndex(i);
            }

            if(heap[i] >= heap[bigIndex]) {
                break;
            }
            swap(i, bigIndex);
            i = bigIndex;
        }
    }
}
