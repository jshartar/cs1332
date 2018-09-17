import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Jordan Shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("stack is empty can't pop");
        }
        T data = head.getData();
        if (size == 1) {
            this.head = null;
        } else {
            this.head = head.getNext();
        }
        this.size--;
        return data;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data was null, invalid entry");
        }
        if (head == null) {
            this.head = new LinkedNode<T>(data, null);
        } else {
            LinkedNode<T> nhead = new LinkedNode<T>(data, head);
            this.head = nhead;
        }
        this.size++;
    }

    @Override
    public T peek() {
        if (head == null || size == 0) {
            return null;
        } else {
            return head.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
}