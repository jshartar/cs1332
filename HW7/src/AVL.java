import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Jordan Shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/invalid");
        }
        for (T item : data) {
            add(item);
        }
    }
    /**
     * helps with height and balance factor of avl tree
     * @param node the current node that is being checked
     */
    private void factor(AVLNode<T> node) {
        int left = -1;
        int right  = -1;
        if (node.getRight() != null) {
            right = node.getRight().getHeight();
        }
        if (node.getLeft() != null) {
            left = node.getLeft().getHeight();
        }
        node.setBalanceFactor(left - right);
        node.setHeight(Math.max(left, right) + 1);
    }
    /**
     * checks if rotation is needed
     * @param node the current node that is being checked
     *
     * @return AVLNode when finished
     */
    private AVLNode<T> rotCheck(AVLNode<T> node) {
        if (node.getBalanceFactor() == 2 && node.getLeft() != null
                && (node.getLeft().getBalanceFactor() == 1
                || node.getLeft().getBalanceFactor() == 0)) {
            return right(node);
        } else if (node.getBalanceFactor() == -2 && node.getRight() != null
                && (node.getRight().getBalanceFactor() == -1
                || node.getRight().getBalanceFactor() == 0)) {
            return left(node);
        } else if (node.getBalanceFactor() == 2 && node.getLeft() != null
                && node.getLeft().getBalanceFactor() == -1) {
            return right(leftRight(node));
        } else if (node.getBalanceFactor() == -2 && node.getRight() != null
                && node.getRight().getBalanceFactor() == 1) {
            return left(rightLeft(node));
        }
        return node;
    }
    /**
     * does left rotate
     * @param node the node that is being rotated
     *
     * @return AVLNode when finished
     */
    private AVLNode<T> left(AVLNode<T> node) {
        AVLNode<T> left = node.getLeft();
        AVLNode<T> right = node.getRight().getLeft();
        AVLNode<T> out = new AVLNode<>(node.getData());
        out.setLeft(left);
        out.setRight(right);
        node.getRight().setLeft(out);
        factor(out);
        factor(node.getRight());
        return node.getRight();
    }
    /**
     * does right rotate
     * @param node the node that is being rotated
     *
     * @return AVLNode when finished
     */
    private AVLNode<T> right(AVLNode<T> node) {
        AVLNode<T> left = node.getLeft().getRight();
        AVLNode<T> right = node.getRight();
        AVLNode<T> out = new AVLNode<>(node.getData());
        out.setLeft(left);
        out.setRight(right);
        node.getLeft().setRight(out);
        factor(out);
        factor(node.getLeft());
        return node.getLeft();
    }

    /**
     * does left right rotation
     * @param node the node that is being rotated
     *
     * @return AVLNode when finished
     */
    private AVLNode<T> leftRight(AVLNode<T> node) {
        AVLNode<T> right = node.getLeft().getRight().getLeft();
        AVLNode<T> left = node.getLeft().getLeft();
        AVLNode<T> mid = node.getLeft().getRight().getRight();
        AVLNode<T> out = new AVLNode<>(node.getLeft().getData());
        out.setLeft(left);
        out.setRight(right);
        node.getLeft().setData(node.getLeft().getRight().getData());
        node.getLeft().setRight(mid);
        node.getLeft().setLeft(out);
        factor(out);
        factor(node.getLeft());
        return node;
    }
    /**
     * does right left rotation
     * @param node the node that is being rotated
     *
     * @return AVLNode when finished
     */
    private AVLNode<T> rightLeft(AVLNode<T> node) {
        AVLNode<T> right = node.getRight().getRight();
        AVLNode<T> left = node.getRight().getLeft().getRight();
        AVLNode<T> mid = node.getRight().getLeft().getLeft();
        AVLNode<T> out = new AVLNode<>(node.getRight().getData());
        out.setLeft(left);
        out.setRight(right);
        node.getRight().setData(node.getRight().getLeft().getData());
        node.getRight().setRight(out);
        node.getRight().setLeft(mid);
        factor(out);
        factor(node.getRight());
        return node;
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/invalid cant add");
        }
        if (size == 0) {
            root = new AVLNode<T>(data);
            root.setHeight(0);
            root.setBalanceFactor(0);
        } else {
            root = addH(root, data);
            factor(root);
        }
        size++;
    }
    /**
     * Helper method to add to the AVL tree
     * @param current the current node that is being checked
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data is null
     * @return AVLNode of the new AVL tree
     */
    private AVLNode<T> addH(AVLNode<T> current, T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/invalid cant add");
        }
        if (current == null) {
            return new AVLNode<>(data);
        }
        if (data.compareTo(current.getData()) > 0) {
            current.setRight(addH(current.getRight(), data));
            factor(current);
            return rotCheck(current);
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(addH(current.getLeft(), data));
            factor(current);
            return rotCheck(current);
        } else if (data.compareTo(current.getData()) == 0) {
            size--;
            return current;
        }
        return current;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/invalid "
                    + "can't remove");
        }
        if (size == 0) {
            throw new NoSuchElementException("size is 0 no data exists "
                    + "can not remove");
        }
        if (size == 1 && data.compareTo(root.getData()) == 0) {
            T output = root.getData();
            root = null;
            size = 0;
            return output;
        }
        AVLNode<T> out = new AVLNode<>(null);
        root = removeH(root, data, out);
        size--;
        factor(root);
        return out.getData();
    }

    /**
     * remove helper for the avl tree
     * @param node the node that is being checked
     * @param data the data to be removed
     * @param out node data is stored in
     * @return AVLNode when finished
     */
    private AVLNode<T> removeH(AVLNode<T> node, T data, AVLNode<T> out) {
        AVLNode<T> temp = node;
        out.setData(node.getData());
        if (data.compareTo(node.getData()) == 0) {
            out.setData(node.getData());
            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
                return node;
            } else if (node.getRight() != null && node.getLeft() == null) {
                node = node.getRight();
                factor(node);
                return rotCheck(node);
            } else if (node.getLeft() != null && node.getRight() == null) {
                node = node.getLeft();
                factor(node);
                return rotCheck(node);
            } else {
                AVLNode<T> leaf = node.getRight();
                if (leaf.getLeft() != null) {
                    while (leaf.getLeft().getLeft() != null) {
                        leaf = leaf.getLeft();
                    }
                    node.setData(leaf.getLeft().getData());
                    if (leaf.getLeft().getRight() != null) {
                        leaf.setLeft(leaf.getLeft().getRight());
                    } else {
                        leaf.setLeft(null);
                    }
                } else {
                    AVLNode<T> left = node.getLeft();
                    node = leaf;
                    node.setLeft(left);
                }
                factor(node);
                return rotCheck(node);
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() != null) {
                temp = removeH(node.getRight(), data, out);
                node.setRight(temp);
                factor(node);
                return rotCheck(node);
            }
        } else if (data.compareTo(node.getData()) < 0) {
            temp = removeH(node.getLeft(), data, out);
            node.setLeft(temp);
            factor(node);
            return rotCheck(node);
        }
        throw new NoSuchElementException("data is not in the avl tree");
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/ can not get "
                    + "from the AVL");
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
     * @param node node to search the avl
     * @param data the data to look for
     * @return node with the data
     */
    private AVLNode<T> getH(AVLNode<T> node, T data) {
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
        throw new NoSuchElementException("data not in the AVL");
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
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {
        if (size <= 1) {
            throw new NoSuchElementException("tree has one or less elements "
            + "can not get second largest element");
        }
        if (size == 2) {
            if (root.getRight() != null) {
                return root.getData();
            }
            return root.getLeft().getData();
        }
        return secH(root).getData();
    }

    /**
     * getSecondLargest helper, finds second largest data in avl tree
     *
     * @param node node to search the avl
     * @return node with the second largest data
     */
    private AVLNode<T> secH(AVLNode<T> node) {
        AVLNode<T> second = node;
        while (node.getRight().getRight() != null) {
            node = node.getRight();
        }
        if (node.getRight().getLeft() == null) {
            second = node;
        } else if (node.getRight().getLeft() != null) {
            AVLNode<T> temp = node.getRight().getLeft();
            while (temp.getRight() != null) {
                temp = temp.getRight();
            }
            second = temp;
        }
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AVL) {
            if (((AVL) obj).size != size) {
                return false;
            }
            boolean is = true;
            is = check(((AVL) obj).getRoot(), root, is);
            return is;
        }
        return false;
    }
    /**
     * method to check if nodes are equal
     * @param obj being looked at for pre order
     * @param it the root node
     * @param is boolean if equal
     * @return the list of data in preorder
     */
    private boolean check(AVLNode<T> obj, AVLNode<T> it, boolean is) {
        if (!is) {
            return false;
        }
        if (obj != null && it != null) {
            try {
                if (obj.getData().compareTo(it.getData()) != 0 && obj != null
                        && it != null) {
                    is = false;
                    return is;
                } else if (obj.getData().compareTo(it.getData()) == 0) {
                    if (obj != null && it != null) {
                        check(obj.getLeft(), it.getLeft(), is);
                        check(obj.getRight(), it.getRight(), is);
                    }
                }
            } catch (ClassCastException e) {
                return false;
            }

        } else if ((obj == null && it != null) || (obj != null && it == null)) {
            is = false;
            return is;
        }
        return is;

    }


    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
