package org.imitationspring.context.support;

import org.imitationspring.core.io.FileSystemResource;
import org.imitationspring.core.io.Resource;

/**
 * @author liaocx
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
