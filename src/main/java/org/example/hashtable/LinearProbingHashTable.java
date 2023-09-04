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
        final Entry<K, V> entryToAdd = new Entry<>(key, value);
        final int availableIndex = findAvailableIndex(key);
        if (bucket[availableIndex] == null) {
            numberOfElements++;
        }
        bucket[availableIndex] = entryToAdd;
        // TODO: check load factor and resize bucket
    }

    @Override
    public V remove(final K key) {
        return null;
    }

    @Override
    public V get(final K key) {
        final Entry<K, V> entry = bucket[findAvailableIndex(key)];
        if (entry == null) {
            return null;
        }
        return entry.getValue();
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

    // FIXME: may return bucket.length if no bucket available - but will be fixed after adding dynamic resizing
    private int findAvailableIndex(final K key) {
        int index = hash(key);
        while (index < bucket.length) {
            final Entry<K, V> entry = bucket[index];
            if (entry == null) {
                break;
            }

            if (entry.isKey(key)) {
                return index;
            }
            index++;
        }
        return index;
    }

    private int hash(final K key) {
        return Objects.hash(key) % bucket.length;
    }
}
