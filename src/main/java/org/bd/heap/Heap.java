package org.bd.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Heap<T> {

    private List<T> heap;
    private Comparator<T> comparator;

    public Heap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
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

    private T parent(int i) {
        return heap.get(parentIndex(i));
    }

    private T leftChild(int i) {
        return heap.get(leftChildIndex(i));
    }

    private T rightChild(int i) {
        return heap.get(rightChildIndex(i));
    }

    private boolean hasParent(int i) {
        return parentIndex(i) >= 0;
    }

    private boolean hasLeftChild(int i) {
        return leftChildIndex(i) < heap.size();
    }

    private boolean hasRightChild(int i) {
        return rightChildIndex(i) < heap.size();
    }

    private void swap(int i, int j) {
        var temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void add(T element) {
        heap.add(element);
        heapifyUp();
    }

    private void heapifyUp() {
        int i = heap.size()-1;
        while (hasParent(i) && comparator.compare(parent(i), heap.get(i)) > 0) {
            swap(i, parentIndex(i));
        }
    }

    public T peek() {
        if (heap.isEmpty()) throw new NoSuchElementException("");
        return heap.getFirst();
    }

    public T poll() {
        var ans = heap.getFirst();
        swap(0, heap.size()-1);
        heap.removeLast();
        heapifyDown();
        return ans;
    }

    private void heapifyDown() {
        int i = 0;
        while (hasLeftChild(i)) {
            int index = leftChildIndex(i);
            if (hasRightChild(i) && comparator.compare(leftChild(i), rightChild(i)) < 0) {
                index = rightChildIndex(i);
            }
            if (comparator.compare(heap.get(i), heap.get(index)) <= 0) {
                break;
            }
            swap(i, index);
            i = index;
        }
    }


}
