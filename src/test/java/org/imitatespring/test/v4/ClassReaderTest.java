package org.imitatespring.test.v4;

import org.imitatespring.core.annotation.AnnotationAttributes;
import org.imitatespring.core.io.ClassPathResource;
import static org.junit.Assert.*;

import org.imitatespring.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.imitatespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * second test
 */
public class ClassReaderTest {

    private ClassPathResource resource = null;
    private ClassReader reader = null;

    @Before
    public void setUp() throws IOException {
        resource = new ClassPathResource("org/imitatespring/service/v4/PetStore.class");
        reader = new ClassReader(resource.getInputStream());
    }

    @Test
    public void testGetClassMetadata() {
        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        //开始解析Class文件, 不断地回调自定义的visitor类中重写的visit*方法
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        assertFalse(visitor.isAbstract());
        assertFalse(visitor.isFinal());
        assertFalse(visitor.isInterface());
        assertEquals("org.imitatespring.service.v4.PetStore", visitor.getClassName());
        assertEquals("java.lang.Object", visitor.getSuperClassName());
        assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnotation() {
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        String annotation = "org.imitatespring.stereotype.Component";
        assertTrue(visitor.hasAnnotation(annotation));
        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        assertEquals("petStore", attributes.getString("value"));
    }
}
