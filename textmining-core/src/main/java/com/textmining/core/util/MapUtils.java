package com.textmining.core.util;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * A Map Utility class to get map details and compare maps to get common and uncommon keysets
 * 
 * @author Deepak
 *
 */
public class MapUtils {

    /**
     * Fetch all the common words present in the {@link Map}.keySet()
     * 
     * @param map1
     *            {@link Map}1
     * @param map2
     *            {@link Map}2
     * @return {@link Set} of Common Key present in both {@link Map}1 & {@link Map}2
     */
    public Set<String> commonKeys(Map<String, ?> map1, Map<String, ?> map2) {
        Set<String> commonKeySet = new TreeSet<>(map1.keySet());
        commonKeySet.retainAll(map2.keySet());
        return commonKeySet;
    }

    /**
     * Fetch all the UnCommon words present in the {@link Map}.keySet()
     * 
     * @param map1
     *            {@link Map}1
     * @param map2
     *            {@link Map}2
     * @return {@link Set} of UnCommon Key present in both {@link Map}1 & {@link Map}2
     */
    public Set<String> uncommonKeys(Map<String, ?> map1, Map<String, ?> map2) {
        Set<String> commonKeys = commonKeys(map1, map2);
        Set<String> uncommonKeys = new TreeSet<>();
        for (String string : map1.keySet()) {
            if (!commonKeys.add(string)) {
                continue;
            } else {
                uncommonKeys.add(string);
            }
        }
        for (String string : map2.keySet()) {
            if (!commonKeys.add(string)) {
                continue;
            } else {
                uncommonKeys.add(string);
            }
        }
        return uncommonKeys;
    }
}
