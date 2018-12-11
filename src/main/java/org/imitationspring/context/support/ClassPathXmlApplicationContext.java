package org.imitationspring.context.support;

import org.imitationspring.core.io.ClassPathResource;
import org.imitationspring.core.io.Resource;

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
