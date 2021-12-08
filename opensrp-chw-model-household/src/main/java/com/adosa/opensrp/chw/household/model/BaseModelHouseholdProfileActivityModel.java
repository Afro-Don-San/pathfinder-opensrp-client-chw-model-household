package com.adosa.opensrp.chw.household.model;

import com.adosa.opensrp.chw.household.PathfinderModelHouseholdLibrary;
import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdProfileContract;
import com.adosa.opensrp.chw.household.util.ConfigHelper;

import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;

import java.util.Set;

public class BaseModelHouseholdProfileActivityModel implements BaseModelHouseholdProfileContract.Model {

    @Override
    public RegisterConfiguration defaultRegisterConfiguration() {
        return ConfigHelper.defaultRegisterConfiguration(PathfinderModelHouseholdLibrary.getInstance().context().applicationContext());
    }

    @Override
    public ViewConfiguration getViewConfiguration(String viewConfigurationIdentifier) {
        return ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().getViewConfiguration(viewConfigurationIdentifier);
    }

    @Override
    public Set<View> getRegisterActiveColumns(String viewConfigurationIdentifier) {
        return ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().getRegisterActiveColumns(viewConfigurationIdentifier);
    }
}
