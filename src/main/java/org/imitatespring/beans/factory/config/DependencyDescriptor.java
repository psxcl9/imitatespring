package org.imitatespring.beans.factory.config;

import org.imitatespring.util.Assert;

import java.lang.reflect.Field;

/**
 * 封装依赖注入相关的参数类, 目前只实现属性注入
 * @author liaocx
 */
public class DependencyDescriptor {

    private Field field;

    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        Assert.notNull(field, "Field must not be null");
        this.field = field;
        this.required = required;
    }

    public Class<?> getDependencyType() {
        if (this.field != null) {
            return this.field.getType();
        }
        //目前只支持属性依赖注入, 可扩展
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return this.required;
    }
}
