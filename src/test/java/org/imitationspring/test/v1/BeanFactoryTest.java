package org.imitationspring.test.v1;

import org.imitationspring.beans.factory.config.BeanDefinition;
import org.imitationspring.beans.factory.BeanCreationException;
import org.imitationspring.beans.factory.BeanDefinitionStoreException;
import org.imitationspring.beans.factory.support.DefaultBeanFactory;
import org.imitationspring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitationspring.core.io.ClassPathResource;
import org.imitationspring.core.io.Resource;
import org.imitationspring.service.v1.PetStore;
import org.imitationspring.service.v1.Prototype;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    Resource resource = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        resource = new ClassPathResource("petstore-v1.xml");
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions(resource);
        BeanDefinition definition = factory.getBeanDefinition("petStore");
        assertTrue(definition.isSingleton());
        assertFalse(definition.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, definition.getScope());
        assertEquals("org.imitationspring.service.v1.PetStore", definition.getBeanClassName());
        PetStore petStoreService = (PetStore) factory.getBean("petStore");
        assertNotNull(petStoreService);
        PetStore petStoreService1 = (PetStore) factory.getBean("petStore");
        assertTrue(petStoreService.equals(petStoreService1));
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions(resource);
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
            resource = new ClassPathResource("xxx.xml");
            reader.loadBeanDefinitions(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }

    @Test
    public void testPrototypeScope() {
        reader.loadBeanDefinitions(resource);
        BeanDefinition definition = factory.getBeanDefinition("prototype");
        assertTrue(definition.isPrototype());
        Prototype prototype1 = (Prototype) factory.getBean("prototype");
        Prototype prototype2 = (Prototype) factory.getBean("prototype");
        assertFalse(prototype1.equals(prototype2));
    }
}
