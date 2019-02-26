package org.imitatespring.test.v4;

import org.imitatespring.context.ApplicationContext;
import org.imitatespring.context.support.ClassPathXmlApplicationContext;
import org.imitatespring.service.v4.PetStore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * last test
 */
public class ApplicationContextV4 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v4.xml");
        PetStore petStore = (PetStore) context.getBean("petStore");
        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
    }
}
