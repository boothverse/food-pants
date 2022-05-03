package org.boothverse.foodpants.business.dao;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("org.boothverse.foodpants.business.dao")
@IncludeClassNamePatterns("^.*Tests?$")
public class DAOTests {
}
