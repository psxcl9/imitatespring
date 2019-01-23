package org.imitatespring.core.io.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.imitatespring.core.io.FileSystemResource;
import org.imitatespring.core.io.Resource;
import org.imitatespring.util.Assert;
import org.imitatespring.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 解析Java包下的所有类
 * @author liaocx
 */
public class PackageResourceLoader {

    private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

    private ClassLoader classLoader;

    public PackageResourceLoader(ClassLoader classLoader) {
        //防止注入一个"null"参数
        Assert.notNull(classLoader, "classLoader must not be null");
        this.classLoader = classLoader;
    }

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * 解析某个包名下的类,将其封装成Resource返回
     * @param basePackage
     * @return
     */
    public Resource[] getResources(String basePackage) throws IOException {
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
     * 首先判断传入的包名所对应的目录是否存在、以及权限是否可读, 然后通过递归解析包名所在目录下的所有的类, 将其放在Set中
     * @param rootDir
     * @return
     */
    private Set<File> retrieveMatchingFiles(File rootDir) throws IOException {
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
            //can not read
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.EMPTY_SET;
        }
        Set<File> result = new LinkedHashSet<>(8);
        doRecursionMatchingFiles(rootDir, result);
        return result;
    }

    /**
     * 组装包名所对应目录下的所有class文件, 将其存放在Set中
     * @param rootDir
     * @param result
     * @throws IOException
     */
    private void doRecursionMatchingFiles(File rootDir, Set<File> result) throws IOException {
        //list()返回的是一个String类型数组，它只是一个数组，仅仅只是一个个文件的名字；
        //而listFiles()方法返回的是File类的引用
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
                    //目录不可读
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + rootDir.getAbsolutePath() +
                                "] because the application is not allowed to read the directory");
                    }
                } else {
                    //通过递归跳过所有的目录
                    doRecursionMatchingFiles(content, result);
                }
            } else {
                result.add(content);
            }
        }
    }

}
