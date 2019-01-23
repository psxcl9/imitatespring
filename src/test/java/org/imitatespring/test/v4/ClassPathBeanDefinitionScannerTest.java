package org.imitatespring.test.v4;

import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.context.annotation.ClassPathBeanDefinitionScanner;
import org.imitatespring.context.annotation.ScannedGenericBeanDefinition;
import org.imitatespring.core.annotation.AnnotationAttributes;
import org.imitatespring.core.type.AnnotationMetadata;
import org.imitatespring.stereotype.Component;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * fourth test
 */
public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void testParseScanedBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackages = "org.imitatespring.service.v4, org.imitatespring.dao.v4";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);

        String annotation = Component.class.getName();

        {
            BeanDefinition bd = factory.getBeanDefinition("petStore");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sgbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sgbd.getMetadata();

            assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            assertEquals("petStore", attributes.getString("value"));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sgbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sgbd.getMetadata();

            assertTrue(amd.hasAnnotation(annotation));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sgbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sgbd.getMetadata();

            assertTrue(amd.hasAnnotation(annotation));
        }
    }
}

