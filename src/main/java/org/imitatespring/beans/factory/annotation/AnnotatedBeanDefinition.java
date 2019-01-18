package org.imitatespring.beans.factory.annotation;

import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.core.type.AnnotationMetadata;

public interface AnnotatedBeanDefinition extends BeanDefinition {
    AnnotationMetadata getMetadata();
}
