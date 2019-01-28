package org.imitatespring.beans.factory.config;

import org.imitatespring.beans.ConstructorArgument;
import org.imitatespring.beans.PropertyValue;

import java.util.List;

/**
 * 将Spring配置文件中的bean的定义转成Spring的内部数据结构
 * @author liaocx 
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

    /**
     * 获取类的完整包名
     * @return String
     */
    String getBeanClassName();

    /**
     * bean的作用域是否是单例
     * @return
     */
    boolean isSingleton();

    /**
     * bean的作用域是否是prototype
     * @return
     */
    boolean isPrototype();

    /**
     * 获取bean的作用域
     * @return
     */
    String getScope();

    /**
     * 设置bean的作用域
     * @param scope
     */
    void setScope(String scope);

    /**
     * 获取bean的property配置
     * @return
     */
    List<PropertyValue> getPropertyValue();

    /**
     * 获取bean的constructor配置
     * @return
     */
    ConstructorArgument getConstructorArgument();

    /**
     * 获取beanId
     * @return
     */
    String getId();

    /**
     * 判断是否存在有参构造器
     * @return
     */
    boolean hasConstructorArgumentValues();

    /**
     * 设置bean的Class对象
     * @param beanClass
     */
    void setBeanClass(Class<?> beanClass);

    /**
     * 获取bean的Class对象
     * @return
     */
    Class<?> getBeanClass() throws IllegalStateException;

    boolean hasBeanClass();

    /**
     * 加载bean的Class对象
     * @param classLoader
     * @return
     */
    Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;
}
