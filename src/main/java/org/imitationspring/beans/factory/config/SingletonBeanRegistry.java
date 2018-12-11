package org.imitationspring.beans.factory.config;

/**
 * @author liaocx
 */
public interface SingletonBeanRegistry {

    /**
     * 注册一个单例的bean
     * @param beanName
     * @param singletonObj
     */
    void registerSingleton(String beanName, Object singletonObj);

    /**
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
