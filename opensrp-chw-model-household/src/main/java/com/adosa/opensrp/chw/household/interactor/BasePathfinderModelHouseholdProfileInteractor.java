package com.adosa.opensrp.chw.household.interactor;

import android.support.annotation.VisibleForTesting;

import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdProfileContract;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;
import com.adosa.opensrp.chw.household.util.AppExecutors;

import org.smartregister.domain.AlertStatus;

import java.util.Date;

public class BasePathfinderModelHouseholdProfileInteractor implements BaseModelHouseholdProfileContract.Interactor {
    protected AppExecutors appExecutors;

    @VisibleForTesting
    BasePathfinderModelHouseholdProfileInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BasePathfinderModelHouseholdProfileInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void refreshProfileView(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject, boolean isForEdit, BaseModelHouseholdProfileContract.InteractorCallback callback) {
        Runnable runnable = () -> appExecutors.mainThread().execute(() -> callback.refreshProfileTopSection(pathfinderModelHouseholdMemberObject));
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateProfileFpStatusInfo(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject, BaseModelHouseholdProfileContract.InteractorCallback callback) {
        Runnable runnable = () -> appExecutors.mainThread().execute(() -> {
            callback.refreshFamilyStatus(AlertStatus.normal);
            callback.refreshUpComingServicesStatus("Family Planning Followup Visit", AlertStatus.normal, new Date());
            callback.refreshLastVisit(new Date());
        });
        appExecutors.diskIO().execute(runnable);
    }
}
