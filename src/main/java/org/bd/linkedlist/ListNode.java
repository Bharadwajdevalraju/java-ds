package org.bd.linkedlist;

public class ListNode<T> {
    public T value;
    public ListNode<T> next;
    public ListNode(T value) {
        this.value = value;
    }


    public void addLast(T element) {
        var head = this;
        while (head.next != null) {
            head=head.next;
        }
        head.next = new ListNode<>(element);
    }

    public void addFirst(T element) {
        var cur = new ListNode<T>(element);
        cur.next = this;
    }

    public ListNode<T> reverse() {
        ListNode<T> prev = null;
        var head = this;
        while (head != null) {
            var next = head.next;
            head.next = prev;
            prev= head;
            head= next;
        }
        return prev;
    }
}
