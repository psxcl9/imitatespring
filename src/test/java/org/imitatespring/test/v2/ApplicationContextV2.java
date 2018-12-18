package org.imitatespring.test.v2;

import org.imitatespring.context.ApplicationContext;
import org.imitatespring.context.support.ClassPathXmlApplicationContext;
import org.imitatespring.service.v2.PetStore;
import static org.junit.Assert.*;
import org.junit.Test;

public class ApplicationContextV2 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStore petStore = (PetStore) context.getBean("petStore");
        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
    }
}
