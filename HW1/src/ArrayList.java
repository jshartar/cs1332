
/**
 * Your implementation of an ArrayList.
 *
 * @author Jordan Shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index is negative");
        }
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2 + 1];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        if (backingArray[index] == null) {
            backingArray[index] = data;
        } else {
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2 + 1];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        for (int i = size; i > 0; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[0] = data;
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2 + 1];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        T removed = backingArray[index];
        if (index == size - 1) {
            backingArray[index] = null;
            size--;
            return removed;
        }
        for (int i = index; i < size; i++) {
            T next = backingArray[i + 1];
            backingArray[i] = next;
        }
        size--;
        return removed;
    }

    @Override
    public T removeFromFront() {
        T removed = backingArray[0];
        for (int i = 0; i < size; i++) {
            T next = backingArray[i + 1];
            backingArray[i] = next;
        }
        size--;
        return removed;
    }

    @Override
    public T removeFromBack() {
        T removed = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        T item = backingArray[index];
        return item;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
