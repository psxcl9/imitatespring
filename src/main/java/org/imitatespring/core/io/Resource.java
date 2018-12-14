package org.imitatespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liaocx
 */
public interface Resource {

    /**
     * 获取对应资源的InputStream
     * @return InputStream
     * @exception IOException
     */
    InputStream getInputStream() throws IOException;

    /**
     * 获取资源路径的描述
     * @return String
     */
    String getDescription();
}
