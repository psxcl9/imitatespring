package org.imitatespring.test.v1;

import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.beans.factory.BeanCreationException;
import org.imitatespring.beans.factory.BeanDefinitionStoreException;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.core.io.Resource;
import org.imitatespring.service.v1.PetStore;
import org.imitatespring.service.v1.Prototype;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeanFactoryTest {

    private DefaultBeanFactory factory = null;
    private XmlBeanDefinitionReader reader = null;
    private Resource resource = null;

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
        //getBean的时候才会加载beanClass对象
        assertNull(definition.getBeanClass());
        assertTrue(definition.isSingleton());
        assertFalse(definition.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, definition.getScope());
        assertEquals("org.imitatespring.service.v1.PetStore", definition.getBeanClassName());
        PetStore petStore = (PetStore) factory.getBean("petStore");
        assertNotNull(petStore);
        PetStore petStore1 = (PetStore) factory.getBean("petStore");
        assertEquals(petStore, petStore1);
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
        assertNotEquals(prototype1, prototype2);
    }
}
