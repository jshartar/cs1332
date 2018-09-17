import java.util.List;

/**
 * The interface for a Binary Search Tree
 * DO NOT EDIT THIS FILE.
 * @version 1.0
 * @author CS 1332 TAs
 */
public interface BSTInterface<T extends Comparable<? super T>> {
    /**
     * Add the data as a leaf in the BST.  Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    void add(T data);

    /**
     * Removes the data from the tree.  There are 3 cases to consider:
     *
     * 1: the data is a leaf.  In this case, simply remove it.
     * 2: the data has one child.  In this case, simply replace it with its
     * child.
     * 3: the data has 2 children.  Use the predecessor to replace the data.
     * You may use iteration to find/remove the predecessor AFTER
     * you have found the node to be removed recursively (but don't start back
     * at the root to do so). We recommend you find and remove the predecessor
     * recursively, but there is no penalty for finding and removing the
     * predecessor iteratively.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree.  Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    T remove(T data);

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    T get(T data);

    /**
     * Returns whether or not data equivalent to the given parameter
     * is contained within the tree.
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    boolean contains(T data);

    /**
     * Should run in O(1).
     *
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    List<T> preorder();

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    List<T> postorder();

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    List<T> inorder();

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n).
     *
     * @return a level order traversal of the tree
     */
    List<T> levelorder();

    /**
     * Calculates the shortest distance between two elements in the tree.
     *
     * To do this, you must first find the deepest common ancestor of both data.
     * From there, find {@code data1} and {@code data2} in the BST, counting up
     * the number of nodes it took to get there. Please note that there
     * is no relationship between the data parameters in that they may not
     * belong to the same branch. You will most likely have to split off and
     * traverse the tree for each piece of data.
     *
     * This method should only need to traverse to the deepest common ancestor
     * once, and from that node to each data in one traversal each. Failure to
     * do so will result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * distanceBetween(40, 13) should return 5.
     * distanceBetween(37, 37) should return 0.
     * distanceBetween(25, 37) should return 1.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the shortest distance between the two data in the tree
     * @throws IllegalArgumentException if either data1 or data2 is null
     * @throws java.util.NoSuchElementException if data1 or data2 is not in
     * the tree
     */
    int distanceBetween(T data1, T data2);

    /**
     * Clear the tree.  Should be O(1).
     */
    void clear();

    /**
     * Calculate and return the height of the root of the tree.  A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    int height();

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    BSTNode<T> getRoot();
}