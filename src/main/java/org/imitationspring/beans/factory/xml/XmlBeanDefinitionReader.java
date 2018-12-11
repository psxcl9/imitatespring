package org.imitationspring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.imitationspring.beans.factory.config.BeanDefinition;
import org.imitationspring.beans.factory.BeanDefinitionStoreException;
import org.imitationspring.beans.factory.support.BeanDefinitionRegistry;
import org.imitationspring.beans.factory.support.GenericBeanDefinition;
import org.imitationspring.core.io.ClassPathResource;
import org.imitationspring.core.io.Resource;
import org.imitationspring.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author liaocx
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

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
                Element bean = iterator.next();
                String id = bean.attributeValue(ID_ATTRIBUTE);
                String beanClassName = bean.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
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

}
