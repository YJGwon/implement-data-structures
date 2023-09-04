package org.example.hashtable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
        final int targetIndex = findAvailableIndex(key);
        final Entry<K, V> entryToRemove = bucket[targetIndex];
        if (entryToRemove == null) {
            return null;
        }
        bucket[targetIndex] = null;
        rearrangeFollowingEntries(targetIndex);
        numberOfElements--;

        return entryToRemove.getValue();
    }

    @Override
    public V get(final K key) {
        final int targetIndex = findAvailableIndex(key);
        final Entry<K, V> entry = bucket[targetIndex];
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public Set<K> keys() {
        return Arrays.stream(bucket)
                .filter(Objects::nonNull)
                .map(Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(bucket)
                .filter(Objects::nonNull)
                .map(Entry::getValue)
                .collect(Collectors.toList());
    }

    public int size() {
        return numberOfElements;
    }

    // FIXME: may execute in infinite loop - but will be fixed after adding dynamic resizing
    private void rearrangeFollowingEntries(final int targetIndex) {
        int indexToRearrange = (targetIndex + 1) % bucket.length;
        while (indexToRearrange < bucket.length) {
            final Entry<K, V> entryToRearrange = bucket[indexToRearrange];
            if (entryToRearrange == null) { // no more following entries
                break;
            }
            final int availableIndex = findAvailableIndex(entryToRearrange.getKey());
            if (indexToRearrange == availableIndex) { // already in right place
                break;
            }
            bucket[availableIndex] = entryToRearrange;
            indexToRearrange = (indexToRearrange + 1) % bucket.length;
        }
    }

    // FIXME: may execute in infinite loop - but will be fixed after adding dynamic resizing
    private int findAvailableIndex(final K key) {
        int index = hash(key);
        while (true) {
            final Entry<K, V> entry = bucket[index];
            if (entry == null) {
                break;
            }

            if (entry.isKey(key)) {
                return index;
            }
            index = (index + 1) % bucket.length;
        }
        return index;
    }

    private int hash(final K key) {
        return Objects.hash(key) % bucket.length;
    }
}
