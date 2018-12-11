package org.imitationspring.test.v1;

import org.imitationspring.core.io.ClassPathResource;
import org.imitationspring.core.io.FileSystemResource;
import org.imitationspring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {
    @Test
    public void testClassPathResource() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        InputStream is = null;
        try {
            is = resource.getInputStream();
            //这是一个不充分测试
            Assert.assertNotNull(is);
        } catch (IOException e) {
            e.printStackTrace();
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

    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource = new FileSystemResource("/Users/liaocx/IdeaProjects/imitationspring/src/test/resources/petstore-v1.xml");
        InputStream is = null;
        try {
            is = resource.getInputStream();
            //这是一个不充分测试
            Assert.assertNotNull(is);
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
