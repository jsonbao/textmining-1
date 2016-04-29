package com.deepak.textmining.util;

import java.util.Map;

/**
 * @author Deepak
 *
 */
public class MapUtils {

    /**
     * Count the number of entries in a {@link Map}
     * 
     * @param map
     * @return The number of entries in the given {@link Map}
     */
    public long mapCount(Map<?, ?> map) {
        return map.values().stream().count();
    }
}
