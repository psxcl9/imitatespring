package org.imitatespring.context.support;

import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.core.io.Resource;

/**
 * @author liaocx
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path, super.getBeanClassLoader());
    }
}
