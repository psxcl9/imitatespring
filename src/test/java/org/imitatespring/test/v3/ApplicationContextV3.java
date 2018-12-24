package org.imitatespring.test.v3;

import org.imitatespring.context.ApplicationContext;
import org.imitatespring.context.support.ClassPathXmlApplicationContext;
import org.imitatespring.dao.v3.AccountDao;
import org.imitatespring.dao.v3.ItemDao;
import org.imitatespring.service.v3.PetStore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ApplicationContextV3 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStore petStore = (PetStore) context.getBean("petStore");
        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getItemDao() instanceof ItemDao);
        assertEquals(24, petStore.getAge());
    }
}
