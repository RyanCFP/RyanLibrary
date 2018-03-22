package com.ryan.utilslibrary.utils;

/**
 * Created by ryan on 18-3-2.
 * Email: Ryan_chan01212@yeah.net
 */

import java.util.Collection;

public class CollectionUtils {
    /**
     * 判断集合是否为null or Empty
     *
     * @return boolean
     * @parama
     * @error
     */
    public static boolean isNullOrEmpty(Collection c) {
        return c == null || c.isEmpty();
    }
}
