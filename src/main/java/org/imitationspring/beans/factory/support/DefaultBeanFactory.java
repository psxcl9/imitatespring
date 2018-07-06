package org.imitationspring.beans.factory.support;

import org.imitationspring.beans.BeanDefinition;
import org.imitationspring.beans.factory.BeanCreationException;
import org.imitationspring.beans.factory.BeanFactory;
import org.imitationspring.util.BaseClassUtils;

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

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition bd) {
        beanDefinitionMap.put(beanId, bd);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = getBeanDefinition(beanId);
        if (Objects.isNull(bd)) {
            throw new BeanCreationException("BeanDefinition is not exist");
        }
        String beanClassName = bd.getBeanClassName();
        ClassLoader cl = BaseClassUtils.getDefaultClassLoader();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }
}