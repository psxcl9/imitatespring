package org.imitatespring.beans.factory.config;

/**
 * property节点中有ref属性的数据结构
 * @author liaocx
 */
public class RuntimeBeanReference {
    /**
     * <property name="xxxbeanName" ref="xxxbeanId"/>中的ref
     */
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
