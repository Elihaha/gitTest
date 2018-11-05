package com.bst.backcommon.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanna
 * @date 2018-5-21
 */
public class MapBuilder<T> {

    private Map<String, T> data = new HashMap<>();

    public MapBuilder<T> map(String key, T value) {
        data.put(key, value);
        return this;
    }

    public Map<String, T> build() {
        return data;
    }
}
