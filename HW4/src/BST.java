import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of a binary search tree.
 *
 * @author jordan shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/ invalid entry");
        }
        for (T item : data) {
            add(item);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/ can not add "
                    + "to the bst");
        }
        if (size == 0) {
            root = new BSTNode<T>(data);
        } else {
            addH(root, data);
        }
        size++;
    }
    /**
     * Recursive helper for add()
     *
     * @param node the starting node
     * @param data the data to add to the bst
     * @return The next node, left or right from node,
     * to search the bst
     */
    private BSTNode<T> addH(BSTNode<T> node, T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/ can not add "
                    + "to the bst");
        }
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                return addH(node.getLeft(), data);
            }
            node.setLeft(new BSTNode<>(data));
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() != null) {
                return addH(node.getRight(), data);
            }
            node.setRight(new BSTNode<>(data));
        } else if (data.compareTo(node.getData()) == 0) {
            size--;
            return null;
        }
        return null;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/ can not remove "
                    + "from the bst");
        }
        if (size == 0) {
            throw new NoSuchElementException("size is 0 no data "
                    + "can be removed");
        }
        BSTNode<T> out = new BSTNode<>(null);
        size--;
        root = removeH(root, data, out);
        return out.getData();
    }

    /**
     * recursive helper for remove()
     *
     * @param nodeC node being checked
     * @param data The data to remove
     * @param nodeD node data is stored
     * @return Node
     */
    private BSTNode<T> removeH(BSTNode<T> nodeC, T data, BSTNode<T> nodeD) {
        BSTNode<T> temp = nodeC;
        if (data.compareTo(nodeC.getData()) == 0) {
            nodeD.setData(nodeC.getData());
            if (nodeC.getLeft() == null && nodeC.getRight() == null) {
                nodeC = null;
                return nodeC;
            } else if (nodeC.getRight() != null && nodeC.getLeft() == null) {
                nodeC = nodeC.getRight();
                return nodeC;
            } else if (nodeC.getLeft() != null && nodeC.getRight() == null) {
                nodeC = nodeC.getLeft();
                return nodeC;
            } else {
                BSTNode<T> leaf = nodeC.getLeft();
                if (leaf.getRight() != null) {
                    while (leaf.getRight().getRight() != null) {
                        leaf = leaf.getRight();
                    }
                    nodeC.setData(leaf.getRight().getData());
                    if (leaf.getRight().getLeft() != null) {
                        leaf.setRight(leaf.getRight().getLeft());
                    } else {
                        leaf.setRight(null);
                    }
                } else {
                    BSTNode<T> right = nodeC.getRight();
                    nodeC = leaf;
                    nodeC.setRight(right);
                }
                return nodeC;
            }
        } else if (data.compareTo(nodeC.getData()) < 0) {
            if (nodeC.getLeft() != null) {
                temp = removeH(nodeC.getLeft(), data, nodeD);
                nodeC.setLeft(temp);
                return nodeC;
            }
        } else if (data.compareTo(nodeC.getData()) > 0) {
            if (nodeC.getRight() != null) {
                temp = removeH(nodeC.getRight(), data, nodeD);
                nodeC.setRight(temp);
                return nodeC;
            }
        }
        throw new NoSuchElementException("data was not in tree");
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/ can not get "
                    + "from the bst");
        }
        if (size == 0) {
            throw new NoSuchElementException("size is 0 no data "
                    + "in the bst");
        }
        return getH(root, data).getData();
    }
    /**
     * get() Recursive Helper
     *
     * @param node node to search the bst
     * @param data the data to look for
     * @return node with the data
     */
    private BSTNode<T> getH(BSTNode<T> node, T data) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() != null) {
                node = node.getRight();
                return getH(node, data);
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                node = node.getLeft();
                return getH(node, data);
            }
        } else if (data.compareTo(node.getData()) == 0) {
            return node;
        }
        throw new NoSuchElementException("data not in the bst");
    }

    @Override
    public boolean contains(T data) {
        try {
            return get(data) != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        if (size == 0) {
            return list;
        }
        return preorderH(root, list);
    }
    /**
     * preorder() recursive helper
     *
     * @param node node being looked at
     * @param list array of data
     * @return the list of data in preorder
     */
    private List<T> preorderH(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return null;
        } else {
            list.add(node.getData());
            preorderH(node.getLeft(), list);
            preorderH(node.getRight(), list);
        }
        return list;
    }
    @Override
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        if (size == 0) {
            return list;
        }
        return postorderH(root, list);
    }
    /**
     * postorder() recursive helper
     *
     * @param node node being looked at
     * @param list array of data
     * @return the list of data in postorder
     */
    private List<T> postorderH(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return null;
        } else {
            postorderH(node.getLeft(), list);
            postorderH(node.getRight(), list);
            list.add(node.getData());
        }
        return list;
    }


    @Override
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        if (size == 0) {
            return list;
        }
        return inorderH(root, list);
    }
    /**
     * inorder() recursive helper
     *
     * @param node node being looked at
     * @param list array of data
     * @return the list of data in inorder
     */
    private List<T> inorderH(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return null;
        } else {
            inorderH(node.getLeft(), list);
            list.add(node.getData());
            inorderH(node.getRight(), list);
        }
        return list;
    }
    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList<>();
        if (size == 0) {
            return list;
        }
        LinkedList<BSTNode<T>> nodes = new LinkedList<>();
        nodes.addLast(root);
        while (!nodes.isEmpty()) {
            BSTNode<T> temp = nodes.removeFirst();
            if (temp != null) {
                list.add(temp.getData());
                nodes.addLast(temp.getLeft());
                nodes.addLast(temp.getRight());
            }
        }
        return list;
    }

    @Override
    public int distanceBetween(T data1, T data2) {
        if (data1 == null) {
            throw new IllegalArgumentException("data1 is null/ not in "
                    + "the bst");
        }
        if (data2 == null) {
            throw new IllegalArgumentException("data2 is null/ not in "
                    + "the bst");
        }
        List<T> dOne = new ArrayList<>();
        List<T> dTwo = new ArrayList<>();
        BSTNode<T> one = distanceBetweenH(root, dOne, data1);
        BSTNode<T> two = distanceBetweenH(root, dTwo, data2);
        int count = dOne.size() + dTwo.size();
        for (T item : dOne) {
            if (dTwo.contains(item)) {
                count--;
                count--;
            }
        }
        return count;

    }
    /**
     * distanceBetween() recursive helper
     *
     * @param node node being looked at
     * @param list array of data
     * @param data data to look for
     * @return node being looked for
     */
    private BSTNode<T> distanceBetweenH(BSTNode<T> node, List<T> list, T data) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() != null) {
                list.add(node.getData());
                node = node.getRight();
                return distanceBetweenH(node, list, data);
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                list.add(node.getData());
                node = node.getLeft();
                return distanceBetweenH(node, list, data);
            }
        } else if (data.compareTo(node.getData()) == 0) {
            list.add(node.getData());
            return node;
        }
        throw new NoSuchElementException("data not in the bst");
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int height() {
        return heightH(root);
    }
    /**
     * height() recursive helper
     *
     * @param node the node being looked at
     * @return the height of the tree
     */
    private int heightH(BSTNode<T> node) {
        int l;
        int r;
        if (node == null) {
            return -1;
        }
        l = heightH(node.getLeft());
        r = heightH(node.getRight());
        return Math.max(l, r) + 1;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
