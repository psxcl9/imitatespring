package org.imitatespring.test.v2;

import org.imitatespring.beans.factory.config.RuntimeBeanReference;
import org.imitatespring.beans.factory.config.TypedStringValue;
import org.imitatespring.beans.factory.support.BeanDefinitionValueResolver;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.dao.v2.AccountDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeanDefinitionValueResolverTest {

    private BeanDefinitionValueResolver resolver;

    @Before
    public void setUp() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        resolver = new BeanDefinitionValueResolver(factory);
    }

    @Test
    public void testResolveRuntimeBeanReference() {
        RuntimeBeanReference ref = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(ref);
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }

    @Test
    public void testResolveTypedStringValue() {
        TypedStringValue stringValue = new TypedStringValue("test");
        Object value = resolver.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);
    }
}
