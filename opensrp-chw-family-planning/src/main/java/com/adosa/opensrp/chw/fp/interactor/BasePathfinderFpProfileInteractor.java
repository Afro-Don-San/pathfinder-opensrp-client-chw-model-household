package com.adosa.opensrp.chw.fp.interactor;

import android.support.annotation.VisibleForTesting;

import com.adosa.opensrp.chw.fp.contract.BaseFpProfileContract;
import com.adosa.opensrp.chw.fp.domain.PathfinderFpMemberObject;
import com.adosa.opensrp.chw.fp.util.AppExecutors;
import org.smartregister.domain.AlertStatus;

import java.util.Date;

public class BasePathfinderFpProfileInteractor implements BaseFpProfileContract.Interactor {
    protected AppExecutors appExecutors;

    @VisibleForTesting
    BasePathfinderFpProfileInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BasePathfinderFpProfileInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void refreshProfileView(PathfinderFpMemberObject pathfinderFpMemberObject, boolean isForEdit, BaseFpProfileContract.InteractorCallback callback) {
        Runnable runnable = () -> appExecutors.mainThread().execute(() -> callback.refreshProfileTopSection(pathfinderFpMemberObject));
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateProfileFpStatusInfo(PathfinderFpMemberObject pathfinderFpMemberObject, BaseFpProfileContract.InteractorCallback callback) {
        Runnable runnable = () -> appExecutors.mainThread().execute(() -> {
            callback.refreshFamilyStatus(AlertStatus.normal);
            callback.refreshUpComingServicesStatus("Family Planning Followup Visit", AlertStatus.normal, new Date());
            callback.refreshLastVisit(new Date());
        });
        appExecutors.diskIO().execute(runnable);
    }
}
