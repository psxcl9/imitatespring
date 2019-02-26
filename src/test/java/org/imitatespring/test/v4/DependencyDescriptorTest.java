package org.imitatespring.test.v4;

import org.imitatespring.beans.factory.config.DependencyDescriptor;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.core.io.Resource;
import org.imitatespring.dao.v4.AccountDao;
import org.imitatespring.service.v4.PetStore;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * sixth test
 */
public class DependencyDescriptorTest {

    @Test
    public void testResolveDependency() throws Exception {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinitions(resource);

        //通过反射的方式拿到声明的属性, 此处和Component注解中使用的ASM有所区别
        Field field = PetStore.class.getDeclaredField("accountDao");
        DependencyDescriptor descriptor = new DependencyDescriptor(field, true);
        Object obj = factory.resolveDependency(descriptor);
        Assert.assertTrue(obj instanceof AccountDao);
    }
}
