package org.imitationspring.beans;

/**
 * 将Spring配置文件中的bean的定义转成Spring的内部数据结构
 * @author liaocx 
 */
public interface BeanDefinition {
    /**
     * 获取类的完整包名
     * @return
     */
    String getBeanClassName();
}
