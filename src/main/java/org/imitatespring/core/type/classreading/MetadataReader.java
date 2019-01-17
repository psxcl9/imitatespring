package org.imitatespring.core.type.classreading;

import org.imitatespring.core.io.Resource;
import org.imitatespring.core.type.AnnotationMetadata;
import org.imitatespring.core.type.ClassMetadata;

public interface MetadataReader {
    Resource getResource();

    ClassMetadata getClassMetadata();

    AnnotationMetadata getAnnotationMetadata();
}
