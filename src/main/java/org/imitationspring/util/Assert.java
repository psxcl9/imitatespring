package org.imitationspring.util;

/**
 * @author liaocx
 */
public abstract class Assert {
    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new IllegalArgumentException(msg);
        }
    }
}
