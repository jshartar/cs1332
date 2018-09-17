import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed queue.
 *
 * @author jordan shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.front = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you should
     * explicitly reset front to 0.
     *
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     *
     * See the homework pdf for more information on implementation details.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("queue is empty can't dequeue");
        }
        T data = backingArray[front];
        backingArray[front] = null;
        this.front++;
        this.size--;
        if (size == 0) {
            this.front = 0;
        }
        if (front == backingArray.length) {
            this.front = 0;
        }
        return data;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data entered is null/invalid");
        }
        if (size == backingArray.length) {
            T[] old = backingArray;
            this.backingArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < old.length; i++) {
                backingArray[i] = old[front];
                if (front == old.length - 1) {
                    front = 0;
                } else {
                    front++;
                }
            }
            this.front = 0;
        }
        if ((front + size) < backingArray.length) {
            backingArray[front + size] = data;
        } else {
            int back = (front + size) - backingArray.length;
            backingArray[back] = data;
        }
        this.size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return backingArray[front];
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}