package org.imitatespring.core.io.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.imitatespring.core.io.FileSystemResource;
import org.imitatespring.core.io.Resource;
import org.imitatespring.util.Assert;
import org.imitatespring.util.ClassUtils;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 包资源加载器
 * @author liaocx
 */
public class PackageResourceLoader {

    private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

    private ClassLoader classLoader;

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "classLoader must not be null");
        this.classLoader = classLoader;
    }

    public PackageResourceLoader() {
        classLoader = ClassUtils.getDefaultClassLoader();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * 解析某个包名下的类,将其封装成FileSystemResource返回
     * @param basePackage
     * @return
     */
    public Resource[] getResources(String basePackage) {
        Assert.notNull(basePackage, "basePackage must not be null");
        //ex. org.imitatespring.dao.v4 --> "org/imitatespring/dao/v4"
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader cl = getClassLoader();
        URL url = cl.getResource(location);
        //用包名所在的路径创建一个file对象, 是一个目录
        File rootDir = new File(url.getFile());
        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            result[i++] = new FileSystemResource(file);
        }
        return result;
    }

    /**
     * 通过递归解析包名所在的根目录下的所有声明的类, 将其放在Set中
     * @param rootDir
     * @return
     */
    private Set<File> retrieveMatchingFiles(File rootDir) {
        //下面三个if判断为了确保rootDir是存在的、是一个目录以及可读
        if (!rootDir.exists()) {
            //Silently skip non-existing directories
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it dose not exist");
            }
            return Collections.EMPTY_SET;
        }
        if (!rootDir.isDirectory()) {
            //Complain louder if it exists but is no directory
            if (logger.isWarnEnabled()) {
                logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it dose not denote a directory");
            }
            return Collections.EMPTY_SET;
        }
        if (!rootDir.canRead()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.EMPTY_SET;
        }
        Set<File> result = new LinkedHashSet<>(8);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    private void doRetrieveMatchingFiles(File rootDir, Set<File> result) {
        File[] dirContents = rootDir.listFiles();
        if (dirContents == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not retrieve contents of directory [" + rootDir.getAbsolutePath() + "]");
            }
            return;
        }
        for (File content : dirContents) {
            if (content.isDirectory()) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + rootDir.getAbsolutePath() +
                                "] because the application is not allowed to read the directory");
                    }
                } else {
                    //通过递归跳过所有的目录
                    doRetrieveMatchingFiles(content, result);
                }
            } else {
                result.add(content);
            }
        }
    }

}
