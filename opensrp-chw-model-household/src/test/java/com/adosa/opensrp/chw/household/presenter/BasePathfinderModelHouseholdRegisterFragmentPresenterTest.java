package com.adosa.opensrp.chw.household.presenter;

import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdRegisterFragmentContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BasePathfinderModelHouseholdRegisterFragmentPresenterTest {

    @Mock
    protected BaseModelHouseholdRegisterFragmentContract.View view;

    @Mock
    protected BaseModelHouseholdRegisterFragmentContract.Model model;

    private BasePathfinderModelHouseholdRegisterFragmentPresenter basePathfinderModelHouseholdRegisterFragmentPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        basePathfinderModelHouseholdRegisterFragmentPresenter = new BasePathfinderModelHouseholdRegisterFragmentPresenter(view, model);
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(basePathfinderModelHouseholdRegisterFragmentPresenter);
    }

    @Test
    public void getMainCondition() {
        Assert.assertEquals(" ec_family_member.date_removed is null AND ec_family_planning.is_closed = 0 AND ec_family_planning.ecp = 1", basePathfinderModelHouseholdRegisterFragmentPresenter.getMainCondition());
    }

}