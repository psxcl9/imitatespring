package org.imitatespring.beans;

import org.imitatespring.util.Assert;

/**
 * bean节点中property属性的数据结构
 * @author liaocx
 */
public class PropertyValue {

    private final String name;
    /**
     * 将ref或者value封装成一个对象
     */
    private final Object value;

    private boolean converted = false;

    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        Assert.notNull(name, "Name must not be null");
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public synchronized boolean isConverted() {
        return this.converted;
    }

    public synchronized void setConvertedValue(Object value) {
        this.converted = true;
        this.convertedValue = value;
    }

    public synchronized Object getConvertedValue() {
        return this.convertedValue;
    }
}
