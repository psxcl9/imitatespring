package org.imitatespring.test.v3;

import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.beans.factory.support.ConstructorResolver;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.dao.v3.AccountDao;
import org.imitatespring.dao.v3.ItemDao;
import org.imitatespring.service.v3.PetStore;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 找到一个匹配的构造器测试
 * @author liaocx
 */
public class ConstructorResolverTest {

    @Test
    public void testAutowireConstructor() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        ConstructorResolver resolver = new ConstructorResolver(factory);

        PetStore petStore = (PetStore) resolver.autowireConstructor(bd);

        assertEquals(24, petStore.getAge());
        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getItemDao() instanceof ItemDao);
    }
}
