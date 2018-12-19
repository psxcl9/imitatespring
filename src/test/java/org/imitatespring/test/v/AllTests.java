package org.imitatespring.test.v;

import org.imitatespring.test.v1.V1AllTests;
import org.imitatespring.test.v2.V2AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTests.class,
        V2AllTests.class
    })
public class AllTests {
}
