import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Jordan Shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Arr is null/invalid can "
                    + "not sort");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null/invalid can "
                    + "not sort");
        }
        int length = arr.length;
        int i = 1;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int j = 0; j < (length - i); j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            i++;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Arr is null/invalid can "
                    + "not sort");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null/invalid can "
                    + "not sort");
        }
        for (int i = 1;  i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Arr is null/invalid can "
                    + "not sort");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null/invalid can "
                    + "not sort");
        }
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            T temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Arr is null/invalid can "
                    + "not sort");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null/invalid can "
                    + "not sort");
        }
        if (rand == null) {
            throw new IllegalArgumentException("rand is null/invalid can "
            + "not sort");
        }
        if (arr.length == 0) {
            return;
        } else {
            quickSortH(arr, comparator, 0, arr.length - 1, rand);
        }
    }
    /**
     *  quick sort helper method recursive
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param left the left most unsorted element
     * @param right the right most unsorted element
     * @param rand the Random object used to select pivots
     * @param <T> data type to sort
     */
    private static <T> void quickSortH(T[]arr, Comparator<T> comparator,
                                       int left, int right, Random rand) {
        if (left < right) {
            int l = left;
            int r = right - 1;
            int pivot = rand.nextInt((right - left)) + left;
            T piv = arr[pivot];
            T temp = arr[pivot];
            arr[pivot] = arr[right];
            arr[right] = temp;
            while (l <= r) {
                while (l <= r && comparator.compare(arr[l], piv) < 0) {
                    l++;
                }
                while (l <= r && comparator.compare(arr[r], piv) > 0) {
                    r--;
                }
                if (l <= r) {
                    T tempo = arr[l];
                    arr[l] = arr[r];
                    arr[r] = tempo;
                    l++;
                    r--;
                }
            }
            T tempor = arr[right];
            arr[right] = arr[l];
            arr[l] = tempor;
            quickSortH(arr, comparator, left, l - 1, rand);
            quickSortH(arr, comparator, l + 1, right, rand);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Arr is null/invalid can "
                    + "not sort");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null/invalid can "
                    + "not sort");
        }
        if (arr.length > 1) {
            arr = mergeSortH(arr, comparator);
        }
    }
    /**
     * recursive helper for mergesort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @param <T> data type to sort
     * @return sorted array
     */
    private static <T> T[] mergeSortH(T[] arr, Comparator<T> comparator) {
        if (arr.length == 1) {
            return arr;
        }
        int length = arr.length;
        int mid = arr.length / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[mid + 1];
        if (arr.length % 2 == 0) {
            right = (T[]) new Object[mid];
        }
        for (int i = 0; i < arr.length; i++) {
            if (i < mid) {
                left[i] = arr[i];
            } else {
                right[i - mid] = arr[i];
            }
        }
        mergeSortH(left, comparator);
        mergeSortH(right, comparator);
        int leftI = 0;
        int rightI = 0;
        int current = 0;
        while (leftI < mid && rightI < (length - mid)) {
            if (comparator.compare(left[leftI], right[rightI]) <= 0) {
                arr[current] = left[leftI];
                leftI++;
            } else {
                arr[current] = right[rightI];
                rightI++;
            }
            current++;
        }
        while (leftI < mid) {
            arr[current] = left[leftI];
            leftI++;
            current++;
        }
        while (rightI < (length - mid)) {
            arr[current] = right[rightI];
            rightI++;
            current++;
        }
        return arr;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Arr is null/invalid can "
                    + "not sort");
        }
        ArrayList<LinkedList<Integer>> buckets =
                new ArrayList<LinkedList<Integer>>(20);
        for (int i = 0; i < 19; i++) {
            buckets.add(i, new LinkedList<Integer>());
        }
        int length = arr.length;
        int numbucks = 10;
        for (int i = 0; i < numbucks; i++) {
            for (int j = 0; j< length; j++) {
                //doing log10 without math is silly why do?
                int nums = (int) (Math.log10(Math.abs(arr[j])) + 1);
                if (nums > numbucks || j == 0) {
                    numbucks = nums;
                }
                int buck = arr[j] / pow(10, i);
                buckets.get((buck % 10) + 9).addLast(arr[j]);
            }
            int index = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[index] = bucket.removeFirst();
                    index++;
                }
            }
        }
    }
    /**
     * power function cause u guys wont let me use math.pow which is dumb
     * @throws IllegalArgumentException if base or exp are 0
     * @throws IllegalArgumentException if exp is negative
     * @param base base number
     * @param exp power to raise base to
     * @return base raise to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}
