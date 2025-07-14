package org.bd.heap;

import java.util.NoSuchElementException;

public class MinHeap {

    private int curSize;
    private final int size;
    private final int[] heap;

    public MinHeap(int size) {
        this.size = size;
        this.heap = new int[size];
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    private int leftIndex(int i) {
        return 2 * i + 1;
    }

    private int rightIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasParent(int i) {
        return parentIndex(i) >= 0;
    }

    private boolean hasLeftChild(int i) {
        return leftIndex(i) < curSize;
    }

    private boolean hasRightChild(int i) {
        return rightIndex(i) < curSize;
    }

    private int parent(int i) {
        return heap[parentIndex(i)];
    }

    private int leftChild(int i) {
        return heap[leftIndex(i)];
    }

    private int rightChild(int i) {
        return heap[rightIndex(i)];
    }

    public void add(int element) {
        if (curSize == size) {
            throw new IllegalStateException("Heap is full");
        }
        heap[curSize++] = element;
        heapifyUp();
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void heapifyUp() {
        int i = curSize - 1;
        while (hasParent(i) && parent(i) > heap[i]) {
            swap(i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    public int peek() {
        if (curSize == 0) throw new NoSuchElementException();
        return heap[0];
    }


    public int poll() {
        if (curSize == 0) throw new NoSuchElementException();
        var value = heap[0];
        heap[0] = heap[curSize - 1];
        curSize--;
        heapifyDown();
        return value;
    }

    private void heapifyDown() {
        int i = 0;
        while (hasLeftChild(i)) {
            int curSmall = leftIndex(i);
            if (hasRightChild(i) && leftChild(i) > rightChild(i)) {
                curSmall = rightIndex(i);
            }
            if (heap[curSmall] < heap[i]) {
                swap(curSmall, i);
                i = curSmall;
            } else {
                break;
            }
        }
    }


}
