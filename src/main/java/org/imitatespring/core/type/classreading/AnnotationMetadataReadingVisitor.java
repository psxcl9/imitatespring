package org.imitatespring.core.type.classreading;

import org.imitatespring.core.annotation.AnnotationAttributes;
import org.imitatespring.core.type.AnnotationMetadata;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 解析所有的注解
 * @author liaocx
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {

    /**
     * 所有注解的集合
     */
    private final Set<String> annotationSet = new LinkedHashSet<>(4);

    /**
     * 每一个注解的属性集合
     */
    private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<>(4);

    public AnnotationMetadataReadingVisitor() {
    }

    /**
     * 先调用父类的visit* 方法
     * @param desc L开头的注解完整类名
     * @param visible
     * @return
     */
    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {

        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        //进一步解析注解的属性
        AnnotationAttributesReadingVisitor attributesReadingVisitor = new AnnotationAttributesReadingVisitor(className, this.attributeMap);
        return attributesReadingVisitor;
    }

    @Override
    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    /**
     * @param annotationType 注解的全类名
     * @return
     */
    @Override
    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    @Override
    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }
}
