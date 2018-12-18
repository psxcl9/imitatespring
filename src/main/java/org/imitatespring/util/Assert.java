package org.imitatespring.util;

/**
 * 设置为abstract是为了防止Assert被new出来
 * @author liaocx
 */
public abstract class Assert {
    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
