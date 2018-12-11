package org.imitationspring.beans.factory.support;

import org.imitationspring.beans.factory.config.BeanDefinition;
import org.imitationspring.beans.factory.BeanCreationException;
import org.imitationspring.beans.factory.BeanFactory;
import org.imitationspring.util.ClassUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liaocx
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory() {
    }

    /**
     * BeanDefinitionRegistry
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition bd) {
        beanDefinitionMap.put(beanId, bd);
    }

    /**
     * BeanFactory
     */
    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = getBeanDefinition(beanId);
        if (Objects.isNull(bd)) {
            throw new BeanCreationException("BeanDefinition is not exist");
        }
        String beanClassName = bd.getBeanClassName();
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            //默认有个无参的构造函数
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }
}