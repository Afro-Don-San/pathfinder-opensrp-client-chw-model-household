package com.adosa.opensrp.chw.fp.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.adosa.opensrp.chw.fp.contract.BaseFpRegisterFragmentContract;

public class BasePathfinderFpRegisterFragmentPresenterTest {

    @Mock
    protected BaseFpRegisterFragmentContract.View view;

    @Mock
    protected BaseFpRegisterFragmentContract.Model model;

    private BasePathfinderFpRegisterFragmentPresenter basePathfinderFpRegisterFragmentPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        basePathfinderFpRegisterFragmentPresenter = new BasePathfinderFpRegisterFragmentPresenter(view, model);
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(basePathfinderFpRegisterFragmentPresenter);
    }

    @Test
    public void getMainCondition() {
        Assert.assertEquals(" ec_family_member.date_removed is null AND ec_family_planning.is_closed = 0 AND ec_family_planning.ecp = 1", basePathfinderFpRegisterFragmentPresenter.getMainCondition());
    }

}