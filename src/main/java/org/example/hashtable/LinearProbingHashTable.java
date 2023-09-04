package org.example.hashtable;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class LinearProbingHashTable<K, V> implements HashTable<K, V> {

    private static final int INITIAL_SIZE = 31;

    private Entry<K, V>[] bucket;
    private int numberOfElements;

    public LinearProbingHashTable() {
        this.bucket = (Entry<K, V>[]) new Entry[INITIAL_SIZE];
        this.numberOfElements = 0;
    }

    @Override
    public void put(final K key, final V value) {
        final int hash = Objects.hash(key) % bucket.length;
        final Entry<K, V> entryToAdd = new Entry<>(key, value);

        int index = hash;
        while (index < bucket.length) {
            final Entry<K, V> entry = bucket[index];
            if (entry == null) {
                addNewEntry(entryToAdd, index);
                return;
            }

            if (entry.isKey(key)) {
                overwriteValue(entryToAdd, index);
                return;
            }
            index++;
        }
    }

    @Override
    public V remove(final K key) {
        return null;
    }

    @Override
    public V get(final K key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    public int size() {
        return numberOfElements;
    }

    private void addNewEntry(final Entry<K, V> entryToAdd, final int index) {
        bucket[index] = entryToAdd;
        numberOfElements++;
    }

    private void overwriteValue(final Entry<K, V> entryToAdd, final int index) {
        bucket[index] = entryToAdd;
    }
}
