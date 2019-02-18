package org.imitatespring.test.v4;

import org.imitatespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import org.imitatespring.beans.factory.annotation.AutowiredFieldElement;
import org.imitatespring.beans.factory.annotation.InjectionElement;
import org.imitatespring.beans.factory.annotation.InjectionMetadata;
import org.imitatespring.service.v4.PetStore;
import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;

public class AutowiredAnnotationProcessorTest {

    @Test
    public void testGetInjectionMetadata() {
        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
        InjectionMetadata injectionMetadata = processor.buildAutowiringMetadata(PetStore.class);
        LinkedList<InjectionElement> elements = injectionMetadata.getInjectionElements();
        assertEquals(2, elements.size());

        assertFieldExists(elements, "accountDao");
        assertFieldExists(elements, "itemDao");
    }

    private void assertFieldExists(LinkedList<InjectionElement> elements, String fieldName) {
        for (InjectionElement ele : elements) {
            AutowiredFieldElement fieldEle = (AutowiredFieldElement) ele;
            Field f = fieldEle.getField();
            if (f.getName().equals(fieldName)) {
                return;
            }
        }
        fail(fieldName + "does not exist");
    }
}
