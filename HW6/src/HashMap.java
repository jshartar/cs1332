import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author jordan shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        this.table = new MapEntry[initialCapacity];
        this.size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key or value null/invalid "
            + "can not put");
        }
        if (table.length < (size + 1) / MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }
        int index =  Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            this.size++;
            return null;
        } else {
            MapEntry<K, V> temp = table[index];
            while (temp != null) {
                if (temp.getKey().hashCode() == key.hashCode()) {
                    V out = temp.getValue();
                    temp.setValue(value);
                    return out;
                } else if (temp.getNext() == null) {
                    temp = new MapEntry<>(key, value);
                    temp.setNext(table[index]);
                    table[index] = temp;
                    this.size++;
                    return null;
                }
                temp = temp.getNext();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null/invalid can not "
            + "remove");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            throw new NoSuchElementException("key not in table");
        }
        if (table[index].getKey().hashCode() == key.hashCode()) {
            V out = table[index].getValue();
            if (table[index].getNext() != null) {
                table[index] = table[index].getNext();
            } else {
                table[index] = null;
            }
            this.size--;
            return out;
        }
        MapEntry<K, V> temp = table[index].getNext();
        MapEntry<K, V> old = table[index];
        while (temp != null) {
            if (temp.getKey().hashCode() == key.hashCode()) {
                V out = temp.getValue();
                old.setNext(temp.getNext());
                this.size--;
                return out;
            } else {
                temp = temp.getNext();
                old = old.getNext();
            }
        }
        throw new NoSuchElementException("key not in table");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null/invalid can't "
                    + "complete get()");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            throw new NoSuchElementException("key not in table");
        }
        if (table[index].getKey().hashCode() == key.hashCode()) {
            V out = table[index].getValue();
            return out;
        }
        MapEntry<K, V> temp = table[index].getNext();
        MapEntry<K, V> old = table[index];
        while (temp != null) {
            if (temp.getKey().hashCode() == key.hashCode()) {
                V out = temp.getValue();
                return out;
            } else {
                temp = temp.getNext();
                old = old.getNext();
            }
        }
        throw new NoSuchElementException("key not in table");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null/invalid can't "
            + "do contains key");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return false;
        }
        MapEntry<K, V> temp = table[index];
        while (temp != null) {
            if (temp.getKey().hashCode() == key.hashCode()) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        this.table = new MapEntry[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> out = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> temp = table[i];
                while (temp != null) {
                    out.add(temp.getKey());
                    temp = temp.getNext();
                }
            }
        }
        return out;
    }

    @Override
    public List<V> values() {
        List<V> out = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> temp = table[i];
                while (temp != null) {
                    out.add(temp.getValue());
                    temp = temp.getNext();
                }
            }
        }
        return out;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("length must be positive "
            + "or greater than or equal to size");
        }
        MapEntry<K, V>[] nTable = new MapEntry[length];
        MapEntry<K, V>[] old = table;
        table = nTable;
        size = 0;
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null) {
                MapEntry<K, V> temp = old[i];
                while (temp != null) {
                    put(temp.getKey(), temp.getValue());
                    temp = temp.getNext();
                }
            }
        }
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
