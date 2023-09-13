package org.example.hashtable;

import java.util.Collection;
import java.util.Set;

public interface HashTable<K, V> {

    void put(K key, V value);

    V remove(K key);

    V get(K key);

    boolean isEmpty();

    Set<K> keys();

    Collection<V> values();
}
