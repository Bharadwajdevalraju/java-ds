package org.bd.graph;

import org.bd.stack.Stack;

import java.util.*;

public class Graph {

    private HashMap<Integer, List<Integer>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    private void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int src, int dest) {
        addVertex(src);
        addVertex(dest);
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    public List<Integer> dfs() {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>(adjList.size());
        stack.push(0);
        visited.add(0);
        while (!stack.isEmpty()) {
            var cur = stack.pop();
            result.add(cur);
            for (var n : adjList.get(cur)) {
                if (!visited.contains(n)) {
                    stack.push(n);
                    visited.add(n);
                }
            }
        }
        return result;
    }

    public List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        org.bd.queue.Queue<Integer> queue = new org.bd.queue.Queue<>();
        queue.enqueue(0);
        visited.add(0);
        while (!queue.isEmpty()) {
            var cur = queue.dequeue();
            result.add(cur);
            for (var n : adjList.get(cur)) {
                if (!visited.contains(n)) {
                    queue.enqueue(n);
                    visited.add(n);
                }
            }
        }
        return result;
    }
}
