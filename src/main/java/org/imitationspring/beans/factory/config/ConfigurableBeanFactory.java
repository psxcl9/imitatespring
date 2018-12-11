package org.imitationspring.beans.factory.config;

import org.imitationspring.beans.factory.BeanFactory;

/**
 * 支持动态设置ClassLoader
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
