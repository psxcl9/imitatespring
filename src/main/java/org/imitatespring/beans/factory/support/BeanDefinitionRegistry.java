package org.imitatespring.beans.factory.support;

import org.imitatespring.beans.factory.config.BeanDefinition;

/**
 * @author liaocx
 */
public interface BeanDefinitionRegistry {

    /**
     * 通过beanId获取一个BeanDefinition对象
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 仅用于注册BeanDefinition
     * @param beanId
     * @param bd
     */
    void registerBeanDefinition(String beanId, BeanDefinition bd);
}
