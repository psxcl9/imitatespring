package org.imitatespring.test.v1;

import org.imitatespring.context.ApplicationContext;
import org.imitatespring.context.support.ClassPathXmlApplicationContext;
import org.imitatespring.context.support.FileSystemXmlApplicationContext;
import org.imitatespring.service.v1.PetStore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationContextTest {

    @Test
    public void getBeanFromClassPath() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStore petStore = (PetStore) context.getBean("petStore");
        assertNotNull(petStore);
    }

    @Test
    public void getBeanFromFileSystem() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/test/resources/petstore-v1.xml");
        PetStore petStore = (PetStore) context.getBean("petStore");
        assertNotNull(petStore);
    }
}
