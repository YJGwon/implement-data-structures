package org.example.hashtable;

public class Entry<K, V> {

    private final K key;
    private final V value;

    public Entry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public boolean isKey(final K key) {
        return this.key.equals(key);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
