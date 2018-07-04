package org.imitationspring.beans.factory;

import org.imitationspring.beans.BeanDefinition;

/**
 * 用于管理bean，可从中获取注册到Factory的bean来使用
 * @author liaocx
 */
public interface BeanFactory {
    /**
     * 通过beanId获取一个BeanDefinition对象
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 通过beanId获取一个具体的Bean
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
