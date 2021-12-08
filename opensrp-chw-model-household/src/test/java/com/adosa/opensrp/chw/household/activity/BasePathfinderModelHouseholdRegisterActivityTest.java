package com.adosa.opensrp.chw.household.activity;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

public class BasePathfinderModelHouseholdRegisterActivityTest {

    @Mock
    private BasePathfinderModelHouseholdRegisterActivity basePathfinderModelHouseholdRegisterActivity = new BasePathfinderModelHouseholdRegisterActivity();

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(basePathfinderModelHouseholdRegisterActivity);
    }

    @Test
    public void testFormConfig() {
        Assert.assertNull(basePathfinderModelHouseholdRegisterActivity.getFormConfig());
    }

}