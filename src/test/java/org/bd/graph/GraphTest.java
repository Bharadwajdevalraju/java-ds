package org.bd.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph graph;

    @BeforeEach
    void setup() {
        graph = new Graph();
    }

    // Helper to add edges (since addEdge is private in your code)
    void addEdge(Graph g, int u, int v) throws Exception {
        var m = Graph.class.getDeclaredMethod("addEdge", int.class, int.class);
        m.setAccessible(true);
        m.invoke(g, u, v);
    }

    @Test
    void testSingleNodeGraph() throws Exception {
        addEdge(graph, 0, 0); // Self loop
        assertEquals(List.of(0), graph.dfs());
        assertEquals(List.of(0), graph.bfs());
    }

    @Test
    void testTwoNodesOneEdge() throws Exception {
        addEdge(graph, 0, 1);
        // Possible DFS orders: [0,1] or [0,1] (since only one neighbor)
        assertEquals(List.of(0, 1), graph.dfs());
        assertEquals(List.of(0, 1), graph.bfs());
    }

    @Test
    void testThreeNodesLine() throws Exception {
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        // DFS: 0,1,2 (since 0->1->2)
        assertEquals(List.of(0, 1, 2), graph.dfs());
        assertEquals(List.of(0, 1, 2), graph.bfs());
    }

    @Test
    void testThreeNodesTriangle() throws Exception {
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);
        // DFS: 0,1,2 (depending on neighbor order)
        assertEquals(List.of(0, 2, 1), graph.dfs());
        assertEquals(List.of(0, 1, 2), graph.bfs());
    }

    @Test
    void testDisconnectedGraph() throws Exception {
        addEdge(graph, 0, 1);
        addEdge(graph, 2, 3);
        // Only nodes reachable from 0 are visited
        assertEquals(List.of(0, 1), graph.dfs());
        assertEquals(List.of(0, 1), graph.bfs());
    }

    @Test
    void testCycleGraph() throws Exception {
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);
        // Should not loop infinitely
        assertEquals(List.of(0, 2, 1), graph.dfs());
        assertEquals(List.of(0, 1, 2), graph.bfs());
    }

    @Test
    void testSquareGraph() throws Exception {
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 0);
        // DFS and BFS should visit all nodes starting from 0
        List<Integer> dfsResult = graph.dfs();
        List<Integer> bfsResult = graph.bfs();
        assertTrue(dfsResult.containsAll(List.of(0, 1, 2, 3)));
        assertTrue(bfsResult.containsAll(List.of(0, 1, 2, 3)));
        assertEquals(4, dfsResult.size());
        assertEquals(4, bfsResult.size());
    }

    @Test
    void testStarGraph() throws Exception {
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 0, 3);
        addEdge(graph, 0, 4);
        // DFS: 0, (some order of 1,2,3,4)
        List<Integer> dfsResult = graph.dfs();
        List<Integer> bfsResult = graph.bfs();
        assertEquals(5, dfsResult.size());
        assertEquals(5, bfsResult.size());
        assertEquals(0, dfsResult.get(0));
        assertEquals(0, bfsResult.get(0));
        assertTrue(dfsResult.containsAll(List.of(1,2,3,4)));
        assertTrue(bfsResult.containsAll(List.of(1,2,3,4)));
    }

    @Test
    void testSelfLoop() throws Exception {
        addEdge(graph, 0, 0);
        // Should visit node 0 only once
        assertEquals(List.of(0), graph.dfs());
        assertEquals(List.of(0), graph.bfs());
    }

    @Test
    void testMultipleComponents() throws Exception {
        addEdge(graph, 0, 1);
        addEdge(graph, 2, 3);
        addEdge(graph, 4, 5);
        // Only component with 0 and 1 is reachable from 0
        assertEquals(List.of(0, 1), graph.dfs());
        assertEquals(List.of(0, 1), graph.bfs());
    }

    @Test
    void testLargeGraph() throws Exception {
        // Build a graph with 10 nodes and various connections
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 5);
        addEdge(graph, 2, 6);
        addEdge(graph, 3, 7);
        addEdge(graph, 4, 7);
        addEdge(graph, 5, 8);
        addEdge(graph, 6, 8);
        addEdge(graph, 7, 9);
        addEdge(graph, 8, 9);

        // DFS and BFS both should visit all nodes starting from 0
        List<Integer> dfsResult = graph.dfs();
        List<Integer> bfsResult = graph.bfs();

        // All nodes from 0 to 9 should be visited
        for (int i = 0; i < 10; i++) {
            assertTrue(dfsResult.contains(i), "DFS missing node " + i);
            assertTrue(bfsResult.contains(i), "BFS missing node " + i);
        }
        assertEquals(10, dfsResult.size());
        assertEquals(10, bfsResult.size());

        // BFS from 0 should visit nodes in level order
        // For this graph, the expected BFS order is:
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), bfsResult);
    }


}
