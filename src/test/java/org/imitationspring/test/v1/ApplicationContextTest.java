package org.imitationspring.test.v1;

import org.imitationspring.context.ApplicationContext;
import org.imitationspring.context.support.ClassPathXmlApplicationContext;
import org.imitationspring.context.support.FileSystemXmlApplicationContext;
import org.imitationspring.service.v1.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liaocx
 */
public class ApplicationContextTest {

    @Test
    public void getBeanFromClassPath() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ac.getBean("petStoreService");
        assertNotNull(petStoreService);
    }

    @Test
    public void getBeanFromFileSystem() {
        ApplicationContext ac = new FileSystemXmlApplicationContext("/Users/liaocx/IdeaProjects/imitationspring/src/test/resources/petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ac.getBean("petStoreService");
        assertNotNull(petStoreService);
    }
}
