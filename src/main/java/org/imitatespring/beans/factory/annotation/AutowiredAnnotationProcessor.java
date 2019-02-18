package org.imitatespring.beans.factory.annotation;

import org.imitatespring.beans.BeansException;
import org.imitatespring.beans.factory.BeanCreationException;
import org.imitatespring.beans.factory.config.AutowireCapableBeanFactory;
import org.imitatespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.imitatespring.core.annotation.AnnotationUtils;
import org.imitatespring.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor {

    private AutowireCapableBeanFactory beanFactory;

    private String requiredParameterName = "required";

    private boolean requiredParameterValue = true;

    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();

    public AutowiredAnnotationProcessor() {
        this.autowiredAnnotationTypes.add(Autowired.class);
    }

    public InjectionMetadata buildAutowiringMetadata(Class<?> clazz) {

        LinkedList<InjectionElement> elements = new LinkedList<>();
        Class<?> targetClass = clazz;

        do {
            LinkedList<InjectionElement> currElements = new LinkedList<>();
            for (Field field : targetClass.getDeclaredFields()) {
                Annotation ann = findAutowiredAnnotation(field);
                if (ann != null) {
                    //存在Autowired注解, 判断是否是静态属性
                    if (Modifier.isStatic(field.getModifiers())) {
                        //静态属性, 跳出当前循环
                        continue;
                    }
                    //非静态, 判断其required属性
                    boolean required = determineRequiredStatus(ann);
                    currElements.add(new AutowiredFieldElement(field, required, beanFactory));
                }
            }
            for (Method method : targetClass.getDeclaredMethods()) {
                //todo 处理方法注入
            }
            elements.addAll(0, currElements);
            //改变循环变量
            targetClass = targetClass.getSuperclass();
        } while (targetClass != null && targetClass != Object.class);
        return new InjectionMetadata(clazz, elements);
    }

    protected boolean determineRequiredStatus(Annotation ann) {
        try {
            Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
            if (method == null) {
                // Annotations like @Inject and @Value don't have a method (attribute) named "required"
                // -> default to required status
                return true;
            }
            return (this.requiredParameterValue == (Boolean) ReflectionUtils.invokeMethod(method, ann));
        }
        catch (Exception ex) {
            // An exception was thrown during reflective invocation of the required attribute
            // -> default to required status
            return true;
        }
    }

    protected Annotation findAutowiredAnnotation(AccessibleObject ao) {
        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            Annotation ann = AnnotationUtils.getAnnotation(ao, type);
            if (ann != null) {
                return ann;
            }
        }
        return null;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //do nothing
        return null;
    }

    @Override
    public boolean afterInstantiation(Object bean, String beanName) throws BeansException {
        //do nothing
        return false;
    }

    @Override
    public Object beforeInitializaion(Object bean, String beanName) throws BeansException {
        //do nothing
        return bean;
    }

    @Override
    public Object afterInitializaion(Object bean, String beanName) throws BeansException {
        //do nothing
        return bean;
    }

    @Override
    public void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = buildAutowiringMetadata(bean.getClass());
        try {
            metadata.inject(bean);
        } catch (Throwable t) {
            throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", t);
        }
    }
}
