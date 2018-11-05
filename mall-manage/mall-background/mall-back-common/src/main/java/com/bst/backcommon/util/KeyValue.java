package com.bst.backcommon.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * KeyValue 键值对
 *
 * @author wanna
 * @date 2018-07-26
 */
@Data
@AllArgsConstructor
public class KeyValue<K, V> {

    private K key;


    private V value;

}
