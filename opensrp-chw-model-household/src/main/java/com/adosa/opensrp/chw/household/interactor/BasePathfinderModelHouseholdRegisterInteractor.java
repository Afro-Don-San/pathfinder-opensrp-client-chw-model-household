package com.adosa.opensrp.chw.household.interactor;

import android.support.annotation.VisibleForTesting;

import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdRegisterContract;
import com.adosa.opensrp.chw.household.util.AppExecutors;
import com.adosa.opensrp.chw.household.util.ModelHouseholdUtil;

public class BasePathfinderModelHouseholdRegisterInteractor implements BaseModelHouseholdRegisterContract.Interactor {

    private AppExecutors appExecutors;

    @VisibleForTesting
    BasePathfinderModelHouseholdRegisterInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BasePathfinderModelHouseholdRegisterInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void saveRegistration(final String jsonString, final BaseModelHouseholdRegisterContract.InteractorCallBack callBack) {

        Runnable runnable = () -> {
            try {
                ModelHouseholdUtil.saveFormEvent(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            appExecutors.mainThread().execute(() -> callBack.onRegistrationSaved());
        };
        appExecutors.diskIO().execute(runnable);
    }
}
