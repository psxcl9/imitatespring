package org.imitatespring.context.support;

import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.core.io.Resource;

/**
 * @author liaocx
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
//    private DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
//        factory = new DefaultBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        Resource resource = new ClassPathResource(configFile);
//        reader.loadBeanDefinitions(resource);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path, super.getBeanClassLoader());
    }
//    @Override
//    public Object getBean(String beanId) {
//        return factory.getBean(beanId);
//    }
}
