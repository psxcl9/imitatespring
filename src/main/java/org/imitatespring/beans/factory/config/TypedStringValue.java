package org.imitatespring.beans.factory.config;

/**
 * property节点中有value属性的数据结构
 * @author liaocx
 */
public class TypedStringValue {

    private String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
