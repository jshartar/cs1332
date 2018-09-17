import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author jordan shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        this.size = 0;
        this.backingArray =
                (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null/invalid cannot "
                    + "create max heap");
        }
        this.size = 0;
        this.backingArray =
                (T[]) new Comparable[data.size() * 2 + 1];
        for (int i = 1; i <= data.size(); i++) {
            if (data.get(size) == null) {
                throw new IllegalArgumentException("data is null/invalid cannot "
                        + "create max heap");
            }
            backingArray[i] = data.get(size);
            size++;
        }
        for (int i = size / 2; i > 0; i--) {
            maxHeapHelp(i);
        }
    }
    /**
     * private helper method that max heaps a spot in the backing array
     * @param index index to start max heap process
     */
    private void maxHeapHelp(int index) {
        int big = index;
        int left = index * 2;
        int right = index * 2 + 1;
        if (left <= size && backingArray[index].compareTo(backingArray[left])
                < 0) {
            big = left;
        }
        if (right <= size && backingArray[big].compareTo(backingArray[right])
                < 0) {
            big = right;
        }
        if (big != index) {
            T temp = backingArray[index];
            backingArray[index] = backingArray[big];
            backingArray[big] = temp;
            maxHeapHelp(big);
        }
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item null/invalid can't add");
        }
        if (size == 0) {
            backingArray[1] = item;
            size++;
        } else {
            if (size == backingArray.length - 1) {
                T[] narr = (T[]) new Comparable[backingArray.length * 2];
                for (int i = 1; i < backingArray.length; i++) {
                    narr[i] = backingArray[i];
                }
                backingArray = narr;
            }
            int count = 1;
            int empty = 1;
            while (empty == 1) {
                if (backingArray[count] == null) {
                    empty = count;
                } else  {
                    count++;
                }
            }
            backingArray[empty] = item;
            int parent = empty / 2;
            while (parent >= 1 && backingArray[parent].compareTo(
                    backingArray[empty]) < 0) {
                T temp = backingArray[parent];
                backingArray[parent] = backingArray[empty];
                backingArray[empty] = temp;
                empty = parent;
                parent = parent / 2;
            }
            size++;
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("no element to remove size is 0");
        }
        T root = backingArray[1];
        if (size == 1) {
            backingArray[1] = null;
            size--;
            return root;
        } else {
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            maxHeapHelp(1);
        }
        return root;
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

    @Override
    public void clear() {
        this.size = 0;
        this.backingArray =
                (T[]) new Comparable[HeapInterface.INITIAL_CAPACITY];
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
