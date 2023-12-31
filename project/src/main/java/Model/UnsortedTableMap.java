package Model;


import java.util.ArrayList;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Scanner;


public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    /**
     * Constructs an initially empty map.
     */
    public UnsortedTableMap() {
    }
// private utility

    /**
     * Returns the index of an entry with equal key, or -1 if none found.
     */
    private int findIndex(K key) {
        int n = table.size();
        for (int j = 0; j < n; j++)
            if (table.get(j).getKey().equals(key))
                return j;
        return -1;
// special value denotes that key was not found
    }


    /** Returns the number of entries in the map. */
    public int size() { return table.size(); }
    /** Returns the value associated with the specified key (or else null). */
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1) return null;   // not found
        return table.get(j).getValue();
    }
    /** Associates given value with given key, replacing a previous value (if any). */
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j == -1) {
            table.add(new MapEntry<>(key, value));      // add new entry
            return null;
        }else                                   // key already exists
            return table.get(j).setValue(value);   // replaced value is returned
    }

    /** Removes the entry with the specified key (if any) and returns its value. */
    public V remove(K key) {
        int j = findIndex(key);
        if(j== -1) return null;
        int n = size();             // not found
        V answer = table.get(j).getValue();
        if (j != n - 1)
            table.set(j, table.get(n-1));  // relocate last entry to 'hole' created by removal
        table.remove(n-1);                  //remove last entry of table
        return answer;
    }

    @Override
    public Iterable<K> KeySet() {
        return super.keySet();
    }

    @Override
    public Iterable<V> Values() {
        return super.values();
    }

    // Support for public entrySet method...
    private class Entrylterator implements Iterator<Entry<K,V>> {
        private int j = 0;

        public boolean hasNext() {
            return j < table.size();
        }

        public Entry<K, V> next() {
            if (j == table.size()) throw new NoSuchElementException();
            return table.get(j++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Entrylterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new Entrylterator();
        }
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     */
    public Iterable<Entry<K, V>> entrySet() {
        return new Entrylterable();
    }
}
interface Entry<K, V> {
    K getKey();

    V getValue();
}
interface Map<K, V> {
    int size();

    boolean isEmpty();

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    Iterable<K> KeySet();

    Iterable<V> Values();

    Iterable<Entry<K, V>> entrySet();
}
abstract class AbstractMap<K, V> implements Map<K, V> {

    public boolean isEmpty() {
        return size() == 0;
    }

    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k; // key
        private V v; // value

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }
        // public methods of the Entry interface

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            k = key;
        }

        protected V setValue(V value) {
            V old = v;
            v= value;
            return old;
        }

    } //end of nested MapEntry class

    // Support for public keySet method...
    private class Keylterator implements Iterator<K> {

        private Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet

        public boolean hasNext() {
            return entries.hasNext();
        }

        public K next() {
            return entries.next().getKey();
        } // return key!

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Keylterable implements Iterable<K> {

        public Iterator<K> iterator() {
            return new Keylterator();
        }
    }

    public Iterable<K> keySet() {
        return new Keylterable();
    }

    // Support for public values method...
    private class Valuelterator implements Iterator<V> {

        private Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet

        public boolean hasNext() {
            return entries.hasNext();
        }

        public V next() {
            return entries.next().getValue();
        }

        // return value!
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Valuelterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new Valuelterator();
        }
    }

    public Iterable<V> values() {
        return new Valuelterable();
    }
}



