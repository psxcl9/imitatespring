package org.imitatespring.beans.factory.config;

import org.imitatespring.beans.BeansException;

public interface BeanPostProcessor {

    Object beforeInitializaion(Object bean, String beanName) throws BeansException;

    Object afterInitializaion(Object bean, String beanName) throws BeansException;
}
