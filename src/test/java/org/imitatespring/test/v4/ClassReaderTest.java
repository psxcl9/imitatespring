package org.imitatespring.test.v4;

import org.imitatespring.core.annotation.AnnotationAttributes;
import org.imitatespring.core.io.ClassPathResource;
import static org.junit.Assert.*;

import org.imitatespring.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.imitatespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

public class ClassReaderTest {

    @Test
    public void testGetClassMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/imitatespring/service/v4/PetStore.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        assertFalse(visitor.isAbstract());
        assertFalse(visitor.isFinal());
        assertFalse(visitor.isInterface());
        assertEquals("org.imitatespring.service.v4.PetStore", visitor.getClassName());
        assertEquals("java.lang.Object", visitor.getSuperClassName());
        assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnotation() throws Exception {
        ClassPathResource resource = new ClassPathResource("org/imitatespring/service/v4/PetStore.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        String annotation = "org.imitatespring.stereotype.Component";
        assertTrue(visitor.hasAnnotation(annotation));
        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        assertEquals("petStore", attributes.getString("value"));
    }
}
