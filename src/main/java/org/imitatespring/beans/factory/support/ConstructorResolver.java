package org.imitatespring.beans.factory.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.imitatespring.beans.ConstructorArgument;
import org.imitatespring.beans.SimpleTypeConverter;
import org.imitatespring.beans.factory.BeanCreationException;
import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 匹配xml配置的constructor 并实现构造器注入
 * @author liaocx
 */
public class ConstructorResolver {

    protected Log logger = LogFactory.getLog(getClass());

    private final ConfigurableBeanFactory beanFactory;

    public ConstructorResolver(ConfigurableBeanFactory factory) {
        this.beanFactory = factory;
    }

    public Object autowireConstructor(final BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        //获取一个beanId的完整包名
        String className = bd.getBeanClassName();
        try {
            //获取这个bean的Class对象
            beanClass = beanFactory.getBeanClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //通过反射获取这个bean的所有Constructor
        Constructor<?>[] candidates = beanClass.getConstructors();

        //用于将构造器参数中的ref转换成相应的对象
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory);
        //用于将构造器参数中的value转换成对应的值 int/boolean
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        //获取当前定义的Constructor数据结构
        ConstructorArgument cargs = bd.getConstructorArgument();
        //遍历每个Constructor
        for (Constructor constructor : candidates) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            //比较xml中配置的构造器参数个数 和 当前取得的构造器参数个数
            if (parameterTypes.length != cargs.getArgumentCount()) {
                //跳出当前循环
                continue;
            }
            //初始化 参数数组的大小
            argsToUse = new Object[parameterTypes.length];

            boolean result = valueMatchTypes(parameterTypes, cargs.getArgumentValues(), argsToUse,
                    valueResolver, typeConverter);
            if (result) {
                constructorToUse = constructor;
                break;
            }
        }

        //找不到合适的构造函数
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getId(), "can't find a appropriate constructor ");
        }
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(bd.getId(), "can't find a creat instance using " + e);
        }
    }

    /**
     * 检查配置的值和构造器中的是否匹配
     * @param parameterTypes
     * @param valueHolders
     * @param argsToUse
     * @param valueResolver
     * @param typeConverter
     * @return
     */
    private boolean valueMatchTypes(Class<?>[] parameterTypes, List<ConstructorArgument.ValueHolder> valueHolders,
                                    Object[] argsToUse, BeanDefinitionValueResolver valueResolver, SimpleTypeConverter typeConverter) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
            //获取参数的值, 可能是RuntimeBeanReference 也有可能是TypedStringValue
            Object originValue = valueHolder.getValue();
            try {
                // 获取真正的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originValue);
                //再进行一次校验, 如果是定义的各种bean的类型, 直接返回
                //但如果参数类型是int, 由于bean配置的value是字符串, 还需要继续转型 转型失败, 抛出异常 说明这个构造函数不可用
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                //转型成功, 将其放入存放参数的数组中
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }
        return true;
    }
}
