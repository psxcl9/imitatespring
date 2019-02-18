package org.imitatespring.beans.factory.config;

import org.imitatespring.beans.factory.BeanFactory;

/**
 * 具备自动装配能力的BeanFactory, 框架内部使用的接口
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    Object resolveDependency(DependencyDescriptor descriptor);
}
