package org.imitatespring.stereotype;

import java.lang.annotation.*;

/**
 * 1.RetentionPolicy.SOURCE：声明注解只保留在 Java 源程序中，在编译 Java 类时注解信息不会被写入到 Class。如果使用的是这个配置
 * ASM 也将无法探测到这个注解。
 * 2.RetentionPolicy.CLASS：声明注解仅保留在 Class 文件中，JVM 运行时并不会处理它，这意味着 ASM 可以在 visitAnnotation 时候探测
 * 到它，但是通过Class 反射无法获取到注解信息。
 * 3.RetentionPolicy.RUNTIME：这是最常用的一种声明，ASM 可以探测到这个注解，同时 Java 反射也可以取得注解的信息。所有用到反射获取的注解
 * 都会用到这个配置，就是这个原因。
 */
@Target(ElementType.TYPE) //表示该注解目标
@Retention(RetentionPolicy.RUNTIME)
@Documented //指示将此注解包含在 javadoc 中
public @interface Component {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";
}
