package org.imitationspring.beans.factory.support;

import org.imitationspring.beans.factory.config.BeanDefinition;

/**
 * bean在spring容器中的数据结构
 * @author liaocx
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = BeanDefinition.SCOPE_DEFAULT;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = BeanDefinition.SCOPE_SINGLETON.equals(scope) || BeanDefinition.SCOPE_DEFAULT.equals(scope);
        this.prototype = BeanDefinition.SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }
}
