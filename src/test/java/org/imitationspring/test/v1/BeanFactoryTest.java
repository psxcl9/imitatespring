package org.imitationspring.test.v1;

import org.imitationspring.beans.factory.config.BeanDefinition;
import org.imitationspring.beans.factory.BeanCreationException;
import org.imitationspring.beans.factory.BeanDefinitionStoreException;
import org.imitationspring.beans.factory.support.DefaultBeanFactory;
import org.imitationspring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitationspring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        BeanDefinition definition = factory.getBeanDefinition("petStoreService");
        assertEquals("org.imitationspring.service.v1.PetStoreService", definition.getBeanClassName());
        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStoreService");
        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
        try {
            reader.loadBeanDefinitions("xxx.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
