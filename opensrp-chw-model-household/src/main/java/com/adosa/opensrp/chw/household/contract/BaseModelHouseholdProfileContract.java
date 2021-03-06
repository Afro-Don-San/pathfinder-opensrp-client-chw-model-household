package com.adosa.opensrp.chw.household.contract;

import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.view.contract.BaseProfileContract;

import java.util.Set;

public interface BaseModelHouseholdProfileContract {

    interface View extends BaseProfileContract.View {

        BaseModelHouseholdProfileContract.Presenter presenter();

        void setProfileImage(String baseEntityId);

        void setProfileName(String fullName);

        void setProfileDetailOne(String detailOne);

        void setProfileDetailTwo(String detailTwo);

        void setProfileDetailThree(String detailThree);

        void toggleFamilyHead(boolean show);

        void togglePrimaryCaregiver(boolean show);

        String getString(int resId);

    }

    interface Presenter {

        void onDestroy(boolean isChangingConfiguration);

        void fetchProfileData();

        void refreshProfileView();

    }

    interface Model {
        RegisterConfiguration defaultRegisterConfiguration();

        ViewConfiguration getViewConfiguration(String viewConfigurationIdentifier);

        Set<org.smartregister.configurableviews.model.View> getRegisterActiveColumns(String viewConfigurationIdentifier);
    }

    interface Interactor {

        void onDestroy(boolean isChangingConfiguration);

        void refreshProfileView(String baseEntityId, InteractorCallBack callback);

    }

    interface InteractorCallBack {

        void refreshProfileTopSection(CommonPersonObjectClient client);
    }
}
