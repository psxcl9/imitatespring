package org.imitatespring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClassPathBeanDefinitionScannerTest.class,
        ClassReaderTest.class,
        MetadataReaderTest.class,
        PackageResourceLoaderTest.class,
        XmlBeanDefinitionReaderTest.class,
        DependencyDescriptorTest.class,
        InjectionMetadataTest.class
})
public class V4AllTests {
}
