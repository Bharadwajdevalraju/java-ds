package org.bd.tree;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    // Helper to access private dfs/bfs
    static List<Integer> callDfs(Tree tree) throws Exception {
        var m = Tree.class.getDeclaredMethod("dfs");
        m.setAccessible(true);
        return (List<Integer>) m.invoke(tree);
    }
    static List<Integer> callBfs(Tree tree) throws Exception {
        var m = Tree.class.getDeclaredMethod("bfs");
        m.setAccessible(true);
        return (List<Integer>) m.invoke(tree);
    }

    @Test
    void constructTree_BalancedBST() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(3, 1, 4, 2, 5);
        Tree root = t.constructTree(elements);
        assertNotNull(root);
        assertEquals(3, root.value); // Middle after sort [1,2,3,4,5]
    }

    @Test
    void inorderTraversal_SortedOrder() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(3, 1, 4, 2, 5);
        Tree root = t.constructTree(elements);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), root.inorder());
    }

    @Test
    void preorderTraversal() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(3, 1, 4, 2, 5);
        Tree root = t.constructTree(elements);
        // Construction yields: 3, 2, 1, 5, 4
        assertEquals(Arrays.asList(3, 2, 1, 5, 4), root.preorder());
    }

    @Test
    void postorderTraversal() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(3, 1, 4, 2, 5);
        Tree root = t.constructTree(elements);
        // Construction yields: 1, 2, 4, 5, 3
        assertEquals(Arrays.asList(1, 2, 4, 5, 3), root.postOrder());
    }


    @Test
    void bfsTraversal_LevelOrder() throws Exception {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(3, 1, 4, 2, 5);
        Tree root = t.constructTree(elements);
        // Level order: 3, 2, 5, 1, 4
        assertEquals(Arrays.asList(3, 2, 5, 1, 4), callBfs(root));
    }

    @Test
    void constructTree_EmptyList() {
        Tree t = new Tree(0);
        List<Integer> elements = new ArrayList<>();
        Tree root = t.constructTree(elements);
        assertNull(root);
    }

    @Test
    void traversals_SingleNode() {
        Tree t = new Tree(0);
        List<Integer> elements = Collections.singletonList(42);
        Tree root = t.constructTree(elements);
        assertEquals(List.of(42), root.inorder());
        assertEquals(List.of(42), root.preorder());
        assertEquals(List.of(42), root.postOrder());
    }

    @Test
    void dfsBfs_SingleNode() throws Exception {
        Tree t = new Tree(0);
        List<Integer> elements = Collections.singletonList(99);
        Tree root = t.constructTree(elements);
        assertEquals(List.of(99), callDfs(root));
        assertEquals(List.of(99), callBfs(root));
    }

    @Test
    void constructTree_DuplicateElements() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(2, 2, 2, 2);
        Tree root = t.constructTree(elements);
        assertEquals(List.of(2,2,2,2), root.inorder());
        assertEquals(List.of(2,2,2,2), root.preorder());
        assertEquals(List.of(2,2,2,2), root.postOrder());
    }

    @Test
    void constructTree_AlreadySorted() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(1,2,3,4,5,6,7);
        Tree root = t.constructTree(elements);
        assertEquals(elements, root.inorder());
        // Preorder: 4, 2, 1, 3, 6, 5, 7
        assertEquals(Arrays.asList(4, 2, 1, 3, 6, 5, 7), root.preorder());
        // Postorder: 1, 3, 2, 5, 7, 6, 4
        assertEquals(Arrays.asList(1, 3, 2, 5, 7, 6, 4), root.postOrder());
    }

    @Test
    void constructTree_ReverseSorted() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(7,6,5,4,3,2,1);
        Tree root = t.constructTree(elements);
        assertEquals(Arrays.asList(1,2,3,4,5,6,7), root.inorder());
        // Preorder: 4, 2, 1, 3, 6, 5, 7
        assertEquals(Arrays.asList(4, 2, 1, 3, 6, 5, 7), root.preorder());
    }


    @Test
    void traversals_ThreeElements() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(2,1,3);
        Tree root = t.constructTree(elements);
        assertEquals(List.of(1,2,3), root.inorder());
        assertEquals(List.of(2,1,3), root.preorder());
        assertEquals(List.of(1,3,2), root.postOrder());
    }

    @Test
    void bfs_DifferentShapes() throws Exception {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Tree root = t.constructTree(elements);
        // Level order: 5, 2, 8, 1, 3, 6, 9, 4, 7
        assertEquals(Arrays.asList(5, 3, 8, 2, 4, 7, 9, 1, 6), callBfs(root));
    }


    @Test
    void constructTree_NegativeNumbers() {
        Tree t = new Tree(0);
        List<Integer> elements = Arrays.asList(-10, -5, 0, 5, 10);
        Tree root = t.constructTree(elements);
        assertEquals(List.of(-10, -5, 0, 5, 10), root.inorder());
        // Preorder: 0, -10, -5, 5, 10
        assertEquals(Arrays.asList(0, -5, -10, 10, 5), root.preorder());
    }
}
