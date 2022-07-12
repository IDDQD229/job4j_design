package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        boolean rsl = false;
        int index = indexFor(hash(hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }


    private int hash(int hashCode) {
        return hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {
        if (count >= table.length * LOAD_FACTOR) {
            int expandSize = table.length * 2;
            table = Arrays.copyOf(table, expandSize);
            MapEntry<K, V>[] rehash = new MapEntry[capacity];
            for (int i = 0; i < capacity - 1; i++) {
                if (table[i] != null) {
                    int index = indexFor(hash(hashCode()));
                    if (rehash[index] == null) {
                        rehash[index] = table[i];
                    }
                }
            }
            table = Arrays.copyOf(rehash, capacity);
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = indexFor(hash(hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(hashCode()));
        if (table[index].key.equals(key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {

        return new Iterator<>() {
            final int expectModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < count;
            }

            @Override
            public MapEntry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++];
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}