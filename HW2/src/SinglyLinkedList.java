/**
 * Your implementation of a circular singly linked list.
 *
 * @author Jordan Shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index out of range");
        }
        if (data == null) {
            throw new IllegalArgumentException("data does not exist");
        }
        LinkedListNode<T> temp = head;
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            LinkedListNode<T> dataNode =
                    new LinkedListNode<T>(data, temp.getNext());
            temp.setNext(dataNode);
            this.size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data does not exist");
        }
        if (size == 0) {
            this.head = new LinkedListNode<T>(data);
            head.setNext(head);
            this.size++;
        } else {
            LinkedListNode<T> node = new LinkedListNode<T>(data, head);
            this.head = node;
            this.size++;
            LinkedListNode<T> temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(head);
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data does not exist");
        }
        if (size == 0) {
            this.head = new LinkedListNode<T>(data);
            head.setNext(head);
        } else {
            LinkedListNode<T> temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.getNext();
            }
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, head);
            temp.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of range");
        }
        if (size == 0) {
            return null;
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        }
        LinkedListNode<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        T data = temp.getNext().getData();
        temp.setNext(temp.getNext().getNext());
        this.size--;
        return data;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        LinkedListNode<T> old = head;
        if (size == 1) {
            this.head = null;
            this.size--;
            return old.getData();
        }
        this.head = head.getNext();
        this.size--;
        return old.getData();
    }

    @Override
    public T removeFromBack() {
        LinkedListNode<T> temp = head;
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            this.head = null;
            this.size--;
            return temp.getData();
        }
        for (int i = 0; i < size - 1; i++) {
            temp = temp.getNext();
        }
        T data = temp.getData();
        temp.setNext(head);
        this.size--;
        return data;
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        LinkedListNode<T> temp = head;
        T spot = null;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (temp.getData().equals(data)) {
                spot = temp.getData();
                index = i;
            }
            temp = temp.getNext();
        }
        if (spot == null) {
            return spot;
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        }
        temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        temp.setNext(temp.getNext().getNext());
        this.size--;
        return spot;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("out of range");
        }
        LinkedListNode<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        LinkedListNode<T> temp = head;
        for (int i = 0; i < size; i++) {
            arr[i] = temp.getData();
            temp = temp.getNext();
        }
        return arr;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}