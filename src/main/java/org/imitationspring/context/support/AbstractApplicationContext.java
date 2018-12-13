package org.imitationspring.context.support;

import org.imitationspring.beans.factory.support.DefaultBeanFactory;
import org.imitationspring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitationspring.context.ApplicationContext;
import org.imitationspring.core.io.Resource;
import org.imitationspring.util.ClassUtils;

/**
 * 抽象类, 模版方法设计模式, 具体实现类只需要重写获取Resource的方法
 * @author liaocx
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
        //Spring中也是这样处理, 也是取得是默认ClassLoader
        factory.setBeanClassLoader(getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    /**
     *  不同实现类根据不同文件路径获取Resource对象
     * @param path
     * @return
     */
    protected abstract Resource getResourceByPath(String path);

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return beanClassLoader != null ? beanClassLoader : ClassUtils.getDefaultClassLoader();
    }
}
