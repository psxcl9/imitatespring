package org.imitatespring.beans.factory.support;

import org.imitatespring.beans.ConstructorArgument;
import org.imitatespring.beans.PropertyValue;
import org.imitatespring.beans.factory.config.BeanDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * bean在spring容器中的数据结构
 * @author liaocx
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;

    private String beanClassName;

    private String scope = SCOPE_DEFAULT;

    private List<PropertyValue> propertyValues = new ArrayList<>();

    //最开始这个属性没有初始化, 导致通过它addArgumentValue时报NullPoint异常
    private ConstructorArgument args = new ConstructorArgument();

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
    }

    @Override
    public List<PropertyValue> getPropertyValue() {
        return this.propertyValues;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.args;
    }

    @Override
    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(this.scope) || SCOPE_DEFAULT.equals(this.scope);
    }

    @Override
    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(this.scope);
    }
}
