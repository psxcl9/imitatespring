package org.imitatespring.core.type.classreading;

import org.imitatespring.core.type.ClassMetadata;
import org.imitatespring.util.ClassUtils;
import org.imitatespring.util.StringUtils;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.SpringAsmInfo;

/**
 * Class元数据解析
 * @author liaocx
 */
public class ClassMetadataReadingVisitor extends ClassVisitor implements ClassMetadata {

    private String className;

    private boolean isInterface;

    private boolean isAbstract;

    private boolean isFinal;

    private String superClassName;

    private String[] interfaces;

    public ClassMetadataReadingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    /**
     * 将ASM传过来的数据进行处理, 保存
     * @param version 一个class的编译版本号
     * @param access
     * @param classPath 一个类的文件路径
     * @param signature
     * @param superName 一个类的父类文件路径
     * @param interfaces 一个类所实现的接口文件路径
     */
    @Override
    public void visit(int version, int access, String classPath, String signature, String superName, String[] interfaces) {
        this.className = ClassUtils.convertResourcePathToClassName(classPath);
        this.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        this.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
        this.isFinal = ((access & Opcodes.ACC_FINAL) != 0);
        if (!StringUtils.isEmpty(superName)) {
            this.superClassName = ClassUtils.convertResourcePathToClassName(superName);
        }
        this.interfaces = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isConcrete() {
        return !(this.isInterface || this.isAbstract);
    }

    @Override
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean hasSuperClass() {
        return (this.superClassName != null);
    }

    @Override
    public String getSuperClassName() {
        return superClassName;
    }

    @Override
    public String[] getInterfaceNames() {
        return interfaces;
    }
}
