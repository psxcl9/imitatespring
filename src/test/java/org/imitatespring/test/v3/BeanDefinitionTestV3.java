package org.imitatespring.test.v3;

import org.imitatespring.beans.ConstructorArgument;
import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.beans.factory.config.RuntimeBeanReference;
import org.imitatespring.beans.factory.config.TypedStringValue;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.core.io.ClassPathResource;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * ConstructorArgument数据结构的测试
 */
public class BeanDefinitionTestV3 {

    @Test
    public void testBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        assertEquals("org.imitatespring.service.v3.PetStore", bd.getBeanClassName());

        ConstructorArgument argument = bd.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> holders = argument.getArgumentValues();

        assertEquals(3, holders.size());
        RuntimeBeanReference ref1 = (RuntimeBeanReference) holders.get(0).getValue();
        assertEquals("accountDao", ref1.getBeanName());
        RuntimeBeanReference ref2 = (RuntimeBeanReference) holders.get(1).getValue();
        assertEquals("itemDao", ref2.getBeanName());
        TypedStringValue ref3 = (TypedStringValue) holders.get(2).getValue();
        assertEquals("24", ref3.getValue());
    }
}
