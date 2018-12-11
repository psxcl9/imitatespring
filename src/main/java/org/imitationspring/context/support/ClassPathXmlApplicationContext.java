package org.imitationspring.context.support;

import org.imitationspring.beans.factory.support.DefaultBeanFactory;
import org.imitationspring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitationspring.context.ApplicationContext;
import org.imitationspring.core.io.ClassPathResource;
import org.imitationspring.core.io.Resource;

/**
 * @author liaocx
 */
public class ClassPathXmlApplicationContext implements ApplicationContext{

    private DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

}