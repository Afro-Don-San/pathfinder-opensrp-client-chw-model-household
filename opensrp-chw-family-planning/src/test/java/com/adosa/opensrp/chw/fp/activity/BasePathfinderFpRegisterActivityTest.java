package com.adosa.opensrp.chw.fp.activity;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

public class BasePathfinderFpRegisterActivityTest {

    @Mock
    private BasePathfinderFpRegisterActivity basePathfinderFpRegisterActivity = new BasePathfinderFpRegisterActivity();

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(basePathfinderFpRegisterActivity);
    }

    @Test
    public void testFormConfig() {
        Assert.assertNull(basePathfinderFpRegisterActivity.getFormConfig());
    }

}