package org.imitatespring.core.type.classreading;

import org.imitatespring.core.io.Resource;
import org.imitatespring.core.type.AnnotationMetadata;
import org.imitatespring.core.type.ClassMetadata;

/**
 * Simple facade for accessing class metadata,
 * as read by an ASM {@link org.springframework.asm.ClassReader}.
 * MetadataReader和ClassMetadata、AnnotationMetadata是组合关系
 *
 * @author Juergen Hoeller
 * @since 2.5
 */
public interface MetadataReader {

    /**
     * Return the resource reference for the class file.
     */
    Resource getResource();

    /**
     * Read basic class metadata for the underlying class.
     */
    ClassMetadata getClassMetadata();

    /**
     * Read full annotation metadata for the underlying class,
     * including metadata for annotated methods.
     */
    AnnotationMetadata getAnnotationMetadata();
}
