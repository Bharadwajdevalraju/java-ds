package org.bd.tree;

import org.bd.queue.Queue;
import org.bd.stack.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree {

    int value;
    public Tree left;
    public Tree right;

    public Tree(int value) {
        this.value = value;
    }

    public Tree constructTree(final List<Integer> elements) {
        Collections.sort(elements);
        return constructTree(0, elements.size(), elements);
    }

    private Tree constructTree(int i, int j, List<Integer> elements) {
        if (i >= j) return null;
        int mid = i + (j - i) / 2;
        var root = new Tree(elements.get(mid));
        root.left = constructTree(i, mid, elements);
        root.right = constructTree(mid + 1, j, elements);
        return root;
    }

    public List<Integer> inorder() {
        List<Integer> list = new ArrayList<>();
        inOrderHelper(list, this);
        return list;
    }

    public List<Integer> preorder() {
        List<Integer> list = new ArrayList<>();
        preOrderHelper(list, this);
        return list;
    }

    public List<Integer> postOrder() {
        List<Integer> list = new ArrayList<>();
        postOrderHelper(list, this);
        return list;
    }


    private void inOrderHelper(List<Integer> elements, Tree root) {
        if (root == null) return;
        inOrderHelper(elements, root.left);
        elements.add(root.value);
        inOrderHelper(elements, root.right);
    }

    private void preOrderHelper(List<Integer> elements, Tree root) {
        if (root == null) return;
        elements.add(root.value);
        preOrderHelper(elements, root.left);
        preOrderHelper(elements, root.right);
    }

    private void postOrderHelper(List<Integer> elements, Tree root) {
        if (root == null) return;
        postOrderHelper(elements, root.left);
        postOrderHelper(elements, root.right);
        elements.add(root.value);
    }

    private List<Integer> dfs() {
        List<Integer> result = new ArrayList<>();
        Stack<Tree> stack = new Stack<>(100);
        stack.push(this);
        while (!stack.isEmpty()) {
            var cur = stack.pop();
            result.add(cur.value);
            if(cur.left != null) stack.push(cur.left);
            if(cur.right != null) stack.push(cur.right);
        }
        return result;
    }

    private List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        Queue<Tree> queue = new Queue<>();
        queue.enqueue(this);
        while (!queue.isEmpty()) {
            var cur = queue.dequeue();
            result.add(cur.value);
            if(cur.left != null) queue.enqueue(cur.left);
            if(cur.right != null) queue.enqueue(cur.right);
        }
        return result;
    }

    public void printTree() {
        printTreeHelper(this, 0);
    }

    private void printTreeHelper(Tree node, int level) {
        if (node == null) return;
        printTreeHelper(node.right, level + 1);
        System.out.println("    ".repeat(level) + node.value);
        printTreeHelper(node.left, level + 1);
    }
}
