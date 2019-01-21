package org.imitatespring.test.v4;

import org.imitatespring.core.annotation.AnnotationAttributes;
import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.core.type.AnnotationMetadata;
import org.imitatespring.core.type.classreading.MetadataReader;
import org.imitatespring.core.type.classreading.SimpleMetadataReader;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import org.imitatespring.stereotype.Component;
import org.junit.Test;

import java.io.IOException;

public class MetadataReaderTest {

    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/imitatespring/service/v4/PetStore.class");
        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd = reader.getAnnotationMetadata();
        String annotation = Component.class.getName();
        assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        assertEquals("petStore", attributes.getString("value"));
        assertFalse(amd.isAbstract());
        assertFalse(amd.isFinal());
        assertEquals("org.imitatespring.service.v4.PetStore", amd.getClassName());
    }
}
