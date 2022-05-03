package org.boothverse.foodpants.business.services;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(JUnitPlatform.class)
@SelectPackages("org.boothverse.foodpants.business.services")
@IncludeClassNamePatterns("^.*Tests?$")
public class ServiceTests {
}
