package com.adosa.opensrp.chw.fp.interactor;

import android.support.annotation.VisibleForTesting;

import com.adosa.opensrp.chw.fp.contract.BaseFpRegisterContract;
import com.adosa.opensrp.chw.fp.util.AppExecutors;
import com.adosa.opensrp.chw.fp.util.FpUtil;

public class BasePathfinderFpRegisterInteractor implements BaseFpRegisterContract.Interactor {

    private AppExecutors appExecutors;

    @VisibleForTesting
    BasePathfinderFpRegisterInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BasePathfinderFpRegisterInteractor() {
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
