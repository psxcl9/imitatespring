package org.imitationspring.beans.factory;

import org.imitationspring.beans.BeansException;

/**
 * 读取XML文件出错时抛出异常
 * @author liaocx
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
