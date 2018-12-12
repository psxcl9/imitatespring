package org.imitationspring.test.v1;

import org.imitationspring.context.ApplicationContext;
import org.imitationspring.context.support.ClassPathXmlApplicationContext;
import org.imitationspring.context.support.FileSystemXmlApplicationContext;
import org.imitationspring.service.v1.PetStore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liaocx
 */
public class ApplicationContextTest {

    @Test
    public void getBeanFromClassPath() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStore petStore = (PetStore) ac.getBean("petStore");
        assertNotNull(petStore);
    }

    @Test
    public void getBeanFromFileSystem() {
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/test/resources/petstore-v1.xml");
        PetStore petStore = (PetStore) ac.getBean("petStore");
        assertNotNull(petStore);
    }
}
