package org.imitationspring.beans.factory.support;

import org.imitationspring.beans.BeanDefinition;

/**
 * @author liaocx
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }
    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }
}
