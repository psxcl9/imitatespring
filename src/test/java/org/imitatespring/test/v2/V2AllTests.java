package org.imitatespring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextV2.class,
        BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest.class,
        CustomNumberEditorTest.class,
        CustomBooleanEditorTest.class,
        TypeConverterTest.class
})
public class V2AllTests {
}
