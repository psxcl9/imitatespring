package org.imitatespring.core.type.classreading;

import org.imitatespring.core.NestedIOException;
import org.imitatespring.core.io.Resource;
import org.imitatespring.core.type.AnnotationMetadata;
import org.imitatespring.core.type.ClassMetadata;
import org.springframework.asm.ClassReader;

import java.io.BufferedInputStream;
import java.io.IOException;

public class SimpleMetadataReader implements MetadataReader {
    private final Resource resource;
    private final ClassMetadata classMetadata;
    private final AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(Resource resource) throws IOException {
        BufferedInputStream is = new BufferedInputStream(resource.getInputStream());

        ClassReader classReader;
        try {
            classReader = new ClassReader(is);
        } catch (IllegalArgumentException var9) {
            throw new NestedIOException("ASM ClassReader failed to parse class file - probably due to a new Java class file version that isn't supported yet: " + resource, var9);
        } finally {
            is.close();
        }
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
        this.annotationMetadata = visitor;
        this.classMetadata = visitor;
        this.resource = resource;
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
