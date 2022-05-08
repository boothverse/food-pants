package org.boothverse.foodpants;

import org.boothverse.foodpants.business.QuantitiesTests;
import org.boothverse.foodpants.business.dao.DAOTests;
import org.boothverse.foodpants.business.services.ServiceTests;
import org.boothverse.foodpants.persistence.PersistenceTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServiceTests.class,
    DAOTests.class,
    PersistenceTests.class,
    QuantitiesTests.class
})
public class AllTests {
}
