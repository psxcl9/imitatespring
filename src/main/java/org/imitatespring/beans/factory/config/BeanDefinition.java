package org.imitatespring.beans.factory.config;

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

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);
}
