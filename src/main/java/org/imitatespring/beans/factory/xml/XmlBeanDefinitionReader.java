package org.imitatespring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.imitatespring.beans.ConstructorArgument;
import org.imitatespring.beans.PropertyValue;
import org.imitatespring.beans.factory.BeanDefinitionStoreException;
import org.imitatespring.beans.factory.config.BeanDefinition;
import org.imitatespring.beans.factory.config.RuntimeBeanReference;
import org.imitatespring.beans.factory.config.TypedStringValue;
import org.imitatespring.beans.factory.support.BeanDefinitionRegistry;
import org.imitatespring.beans.factory.support.GenericBeanDefinition;
import org.imitatespring.core.io.Resource;
import org.imitatespring.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 读取xml配置文件, 注册生成bean的Definition对象
 * @author liaocx
 */
public class XmlBeanDefinitionReader {

    protected final Log logger = LogFactory.getLog(getClass());


    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    /**
     * property相关的属性值
     */
    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     *  解析Resource对象
     */
    public void loadBeanDefinitions(Resource resource) {
        InputStream is = null;
        try {
            try {
                is = resource.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //利用dom4j的方法将InputStream对象变成Document
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            //对doc中的rootElement进行遍历 即遍历beans标签
            Element beans = doc.getRootElement();
            Iterator<Element> iterator = beans.elementIterator();
            while (iterator.hasNext()) {
                //为每个bean创建对应的BeanDefinition对象
                Element bean = iterator.next();
                String id = bean.attributeValue(ID_ATTRIBUTE);
                String beanClassName = bean.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                if (bean.attribute(SCOPE_ATTRIBUTE) != null) {
                    bd.setScope(bean.attributeValue(SCOPE_ATTRIBUTE));
                }
                //解析bean中的constructor
                parseConstructorArgElements(bean, bd);
                //解析bean中的所有property
                parsePropertyElement(bean, bd);
                registry.registerBeanDefinition(id, bd);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from class path resource", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void parseConstructorArgElements(Element bean, BeanDefinition bd) {
        Iterator args = bean.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (args.hasNext()) {
            Element arg = (Element) args.next();
            parseConstructorArgElement(arg, bd);
        }
    }

    private void parseConstructorArgElement(Element arg, BeanDefinition bd) {
        String typeAttr = arg.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = arg.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(arg, bd, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        bd.getConstructorArgument().addArgumentValue(valueHolder);
    }

    private void parsePropertyElement(Element beanElem, BeanDefinition bd) {
        //取一个bean中所有的property
        Iterator propertys = beanElem.elementIterator(PROPERTY_ELEMENT);
        while (propertys.hasNext()) {
            Element propElem = (Element) propertys.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                //name不能为空
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object value = parsePropertyValue(propElem, bd, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, value);
            bd.getPropertyValue().add(pv);
        }
    }


    /**
     * 将ref或value封装成一个RuntimeBeanReference对象或者TypedStringValue对象
     * @param ele
     * @param bd
     * @param propertyName
     * @return
     */
    private Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null) ? "<property> element for property '" + propertyName + "'" :
                            "<constructor-arg> element";

        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        } else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }

}
