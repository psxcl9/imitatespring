package org.imitatespring.beans.factory.config;

/**
 * 注册及获取scope为singleton的bean
 * @author liaocx
 */
public interface SingletonBeanRegistry {

    /**
     * 注册一个作用域是单例的bean
     * @param beanName
     * @param singletonObj
     */
    void registerSingleton(String beanName, Object singletonObj);

    /**
     * 获取一个scope为单例的对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
