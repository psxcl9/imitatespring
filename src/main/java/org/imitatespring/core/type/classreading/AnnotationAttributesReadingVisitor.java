package org.imitatespring.core.type.classreading;

import org.imitatespring.core.annotation.AnnotationAttributes;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

/**
 * 实现对一个注解属性的解析
 * @author liaocx
 */
final class AnnotationAttributesReadingVisitor extends AnnotationVisitor {

    /**
     * 注解的全类名
     */
    private final String annotationType;

    /**
     * 注解下的所有属性集合, key值=this.annotationType value=AnnotationAttributes对象
     */
    private final Map<String, AnnotationAttributes> attributesMap;

    private AnnotationAttributes attributes = new AnnotationAttributes();

    /**
     * @param annotationType 该注解的全类名
     * @param attributesMap
     */
    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }

    /**
     * asm机制自动调用 参数由ClassReader传过来
     * @param attributeName
     * @param attributeValue
     */
    @Override
    public void visit(String attributeName, Object attributeValue) {
        this.attributes.put(attributeName, attributeValue);
    }

    /**
     * 将所有的属性解析完了之后会调用此方法
     * annotationType 注解名
     * attributes 注解对应的所有属性
     */
    @Override
    public final void visitEnd(){
        this.attributesMap.put(this.annotationType, this.attributes);
    }


}
