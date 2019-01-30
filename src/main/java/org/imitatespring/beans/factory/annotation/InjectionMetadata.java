package org.imitatespring.beans.factory.annotation;

import java.util.LinkedList;

public class InjectionMetadata {

    /**
     * 往哪个类中进行注入
     */
    private Class<?> targetClass;

    /**
     * 这个类中有哪些可被注入的元素
     */
    private LinkedList<InjectionElement> injectionElements;

    public InjectionMetadata(Class<?> targetClass, LinkedList<InjectionElement> elements) {
        this.targetClass = targetClass;
        this.injectionElements = elements;
    }

    public LinkedList<InjectionElement> getInjectionElements() {
        return injectionElements;
    }

    public void inject(Object target) {
        if (injectionElements == null || injectionElements.isEmpty()) {
            //表示没有需要注入的
            return;
        }
        for (InjectionElement ele : this.injectionElements) {
            //每一个可被注入的element都会调用一次InjectionElement的inject方法
            ele.inject(target);
        }
    }
}
