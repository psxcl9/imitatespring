package org.imitatespring.beans.factory.annotation;

import org.imitatespring.beans.factory.BeanCreationException;
import org.imitatespring.beans.factory.config.AutowireCapableBeanFactory;
import org.imitatespring.beans.factory.config.DependencyDescriptor;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.util.ReflectionUtils;

import java.lang.reflect.Field;

public class AutowiredFieldElement extends InjectionElement {

    private boolean required;

    public AutowiredFieldElement(Field field, boolean required, AutowireCapableBeanFactory factory) {
        super(field, factory);
        this.required = required;
    }

    public Field getField() {
        return (Field) super.member;
    }

    /**
     * 从Factory中找到依赖的Bean, 调用Field的setter方法
     * @param target 需要被注入的目标类
     */
    @Override
    public void inject(Object target) {
        Field field = getField();
        try {
            DependencyDescriptor descriptor = new DependencyDescriptor(field, this.required);
            Object value = super.factory.resolveDependency(descriptor);
            if (value != null) {
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        } catch (Throwable ex) {
            throw new BeanCreationException("Could not autowire field: " + field, ex);
        }
    }
}
