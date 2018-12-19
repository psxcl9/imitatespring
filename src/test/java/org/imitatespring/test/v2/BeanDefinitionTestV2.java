package org.imitatespring.test.v2;

import org.imitatespring.beans.PropertyValue;
import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.beans.factory.config.RuntimeBeanReference;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.core.io.ClassPathResource;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

/**
 * PropertyValue数据结构的测试
 */
public class BeanDefinitionTestV2 {

    @Test
    public void testBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        List<PropertyValue> pvs = bd.getPropertyValue();
        assertEquals(pvs.size(), 4);
        {
            PropertyValue pv = getPropertyValue("accountDao", pvs);
            assertNotNull(pv);
            assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
        {
            PropertyValue pv = getPropertyValue("itemDao", pvs);
            assertNotNull(pv);
            assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs) {
        for (PropertyValue pv : pvs) {
            if (pv.getName().equals(name)) {
                return pv;
            }
        }
        return null;
    }
}
