package org.imitatespring.beans.factory.config;

import org.imitatespring.beans.factory.BeanFactory;

/**
 * 支持自定义设置ClassLoader
 * @author liaocx
 */
public interface ConfigurableBeanFactory extends BeanFactory {

    /**
     * 自定义ClassLoader
     * @param var
     */
    void setBeanClassLoader(ClassLoader var);

    /**
     * 获取自定义的ClassLoader
     * @return
     */
    ClassLoader getBeanClassLoader();
}
