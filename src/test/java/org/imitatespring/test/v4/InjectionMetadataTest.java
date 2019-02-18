package org.imitatespring.test.v4;

import org.imitatespring.beans.factory.annotation.AutowiredFieldElement;
import org.imitatespring.beans.factory.annotation.InjectionElement;
import org.imitatespring.beans.factory.annotation.InjectionMetadata;
import org.imitatespring.beans.factory.support.DefaultBeanFactory;
import org.imitatespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.imitatespring.core.io.ClassPathResource;
import org.imitatespring.core.io.Resource;
import org.imitatespring.dao.v4.AccountDao;
import org.imitatespring.dao.v4.ItemDao;
import org.imitatespring.service.v4.PetStore;
import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;

public class InjectionMetadataTest {

    @Test
    public void testInjection() throws Exception {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinitions(resource);

        Class<?> clz = PetStore.class;
        LinkedList<InjectionElement> elements = new LinkedList<>();
        {
            Field field = PetStore.class.getDeclaredField("accountDao");
            InjectionElement injectionElem = new AutowiredFieldElement(field, true, factory);
            elements.add(injectionElem);
        }
        {
            Field field = PetStore.class.getDeclaredField("itemDao");
            InjectionElement injectionElem = new AutowiredFieldElement(field, true, factory);
            elements.add(injectionElem);
        }
        InjectionMetadata metadata = new InjectionMetadata(clz, elements);

        PetStore petStore = new PetStore();

        metadata.inject(petStore);
        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getItemDao() instanceof ItemDao);
    }
}
