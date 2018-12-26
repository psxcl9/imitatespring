package org.imitatespring.beans.factory.support;

import org.imitatespring.beans.factory.BeanFactory;
import org.imitatespring.beans.factory.config.RuntimeBeanReference;
import org.imitatespring.beans.factory.config.TypedStringValue;

/**
 * 将封装成PropertyValue对象转化成对应的bean或者value值
 * @author liaocx
 */
public class BeanDefinitionValueResolver {

    private final BeanFactory beanFactory;

    public BeanDefinitionValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else {
            //todo
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }

}
