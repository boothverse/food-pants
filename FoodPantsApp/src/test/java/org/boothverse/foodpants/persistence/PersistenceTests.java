package org.boothverse.foodpants.persistence;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(JUnitPlatform.class)
@SelectPackages("org.boothverse.foodpants.persistence")
@IncludeClassNamePatterns("^.*Tests?$")
public class PersistenceTests {
}
