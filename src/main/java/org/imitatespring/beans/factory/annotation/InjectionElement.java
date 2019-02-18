package org.imitatespring.beans.factory.annotation;

import org.imitatespring.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Member;

public abstract class InjectionElement {

    /**
     * a single member (a field or a method) or a constructor
     *
     */
    protected Member member;

    /**
     * 注入时需要用到
     */
    protected AutowireCapableBeanFactory factory;

    InjectionElement(Member member, AutowireCapableBeanFactory factory) {
        this.member = member;
        this.factory = factory;
    }

    /**
     * 抽象的注入依赖的方法, 具体的实现在继承了这个抽象类的类中
     * @param target
     */
    public abstract void inject(Object target);
}
