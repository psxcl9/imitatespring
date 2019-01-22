package org.imitatespring.test.v4;

import org.imitatespring.core.io.Resource;
import static org.junit.Assert.*;

import org.imitatespring.core.io.support.PackageResourceLoader;
import org.junit.Test;

import java.io.IOException;

/**
 * first test
 */
public class PackageResourceLoaderTest {
    @Test
    public void testGetResources() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("org.imitatespring.dao");
        assertEquals(6, resources.length);
    }
}
