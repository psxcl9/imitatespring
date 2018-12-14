package org.imitatespring.beans.factory.support;

import org.imitatespring.beans.factory.config.SingletonBeanRegistry;
import org.imitatespring.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册获取scope为singleton的bean
 * @author liaocx
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjs = new ConcurrentHashMap<>(64);

    @Override
    public void registerSingleton(String beanName, Object singletonObj) {
        Assert.notNull(beanName, "'beanName' must not be null");
        Object oldObj = getSingleton(beanName);
        if (oldObj != null) {
            throw new IllegalStateException("Could not register object [" + singletonObj +
                    "] under bean name '" + beanName + "': there is already object [" + oldObj + "] bound");
        }
        singletonObjs.put(beanName, singletonObj);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjs.get(beanName);
    }
}
