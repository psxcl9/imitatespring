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
 * Class文件中注解部分的解析, 处理ClassReader读取Class文件时传过来的数据
 * @author liaocx
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {

    /**
     * 所有类上的注解的集合
     */
    private final Set<String> annotationSet = new LinkedHashSet<>(4);

    /**
     * 每一个注解的属性集合
     */
    private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<>(4);

    public AnnotationMetadataReadingVisitor() {
    }

    /**
     * 当扫描器扫描到类注解声明时进行调用, 有多个类注解时依次调用
     * @param desc 注解的类型。它使用的是（“L” + “类型路径” + “;”）形式表示
     * @param visible 该注解是否在 JVM 中可见, 为 true 表示虚拟机可见
     * @return
     */
    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {

        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        //进一步解析注解的属性
        return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
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
