package org.imitatespring.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造器参数的数据结构封装
 * @author liaocx
 */
public class ConstructorArgument {

    private final List<ValueHolder> argumentValues = new ArrayList<>();
    /**
     * Create a new empty ConstructorArgumentValues object.
     */
    public ConstructorArgument() {
    }

    public void addArgumentValue(ValueHolder holder) {
        this.argumentValues.add(holder);
    }

    public List<ValueHolder> getArgumentValues() {
        return Collections.unmodifiableList(this.argumentValues);
    }

    public int getArgumentCount() {
        return this.argumentValues.size();
    }

    public boolean isEmpty() {
        return this.argumentValues.isEmpty();
    }

    /**
     * Clear this holder, removing all argument values.
     */
    public void clear() {
        this.argumentValues.clear();
    }

    public static class ValueHolder {
        private Object value;

        private String type;

        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public ValueHolder(Object value, String type, String name) {
            this.value = value;
            this.type = type;
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
