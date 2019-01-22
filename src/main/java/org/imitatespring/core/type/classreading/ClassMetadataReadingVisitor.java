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
     * 扫描类时第一个拜访的方法,主要用于类声明使用, 将ASM传过来的数据进行处理
     * @param version 类版本
     * @param access 类的修饰符：修饰符在 ASM 中是以 “ACC_” 开头的常量进行定义。可以作用到类级别上的修饰符有：ACC_PUBLIC（public）、
     *               ACC_PRIVATE（private）、ACC_PROTECTED（protected）、ACC_FINAL（final）、ACC_SUPER（extends）、
     *               ACC_INTERFACE（接口）、ACC_ABSTRACT（抽象类）、ACC_ANNOTATION（注解类型）、ACC_ENUM（枚举类型）、
     *               ACC_DEPRECATED（标记了@Deprecated注解的类）、ACC_SYNTHETIC。
     * @param classPath 类的名称, 通常我们使用的类完整类名来表示, 但是字节码文件中会以路径的形式表示（/替换.）
     * @param signature 泛型信息，如果类并未定义任何泛型该参数为空
     * @param superName 类的父类文件路径
     * @param interfaces 类所实现的接口文件路径
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
