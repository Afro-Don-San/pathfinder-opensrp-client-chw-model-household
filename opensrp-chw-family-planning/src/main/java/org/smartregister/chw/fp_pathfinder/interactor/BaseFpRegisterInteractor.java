package org.smartregister.chw.fp_pathfinder.interactor;

import android.support.annotation.VisibleForTesting;

import org.smartregister.chw.fp_pathfinder.contract.BaseFpRegisterContract;
import org.smartregister.chw.fp_pathfinder.util.AppExecutors;
import org.smartregister.chw.fp_pathfinder.util.FpUtil;

public class BaseFpRegisterInteractor implements BaseFpRegisterContract.Interactor {

    private AppExecutors appExecutors;

    @VisibleForTesting
    BaseFpRegisterInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BaseFpRegisterInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void saveRegistration(final String jsonString, final BaseFpRegisterContract.InteractorCallBack callBack) {

        Runnable runnable = () -> {
            try {
                FpUtil.saveFormEvent(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            appExecutors.mainThread().execute(() -> callBack.onRegistrationSaved());
        };
        appExecutors.diskIO().execute(runnable);
    }
}
