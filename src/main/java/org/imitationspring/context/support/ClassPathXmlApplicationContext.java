package org.imitationspring.context.support;

import org.imitationspring.beans.factory.support.DefaultBeanFactory;
import org.imitationspring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitationspring.context.ApplicationContext;

/**
 * @author liaocx
 */
public class ClassPathXmlApplicationContext implements ApplicationContext{

    private DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(configFile);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

}
