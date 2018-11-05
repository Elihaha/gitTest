package com.bst.backcommon.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全的 LUR 缓存
 *
 * @author wanna
 * @date 2018-08-02
 */
public class LURCache<K, V> {

    private static final Lock lock = new ReentrantLock();

    private static final float LOAD_FACTOR = 0.75f;

    private final int MAX_SIZE;

    private LinkedHashMap<K, V> cache;

    public LURCache(int cacheSize) {
        MAX_SIZE = cacheSize;
        // 根据 cacheSize 和 LOAD_FACTOR 计算 HashMap 的 capacity, +1确保当达到 cacheSize 上限时不会触发 resize
        int capacity = (int) Math.ceil(MAX_SIZE / LOAD_FACTOR) + 1;
        cache = new LinkedHashMap(capacity, LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_SIZE;
            }
        };
    }

    /**
     * @param key   key
     * @param value value
     */
    public void put(K key, V value) {
        try {
            lock.lock();
            cache.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param key key
     * @return value
     */
    public V get(K key) {
        try {
            lock.lock();
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param key key
     */
    public void remove(K key) {
        try {
            lock.lock();
            cache.remove(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * @return all of cache
     */
    public Set<Map.Entry<K, V>> getAll() {
        try {
            lock.lock();
            return cache.entrySet();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @return cache size
     */
    public int size() {
        try {
            lock.lock();
            return cache.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     * clear
     */
    public void clear() {
        try {
            lock.lock();
            cache.clear();
        } finally {
            lock.lock();
        }
    }
}
