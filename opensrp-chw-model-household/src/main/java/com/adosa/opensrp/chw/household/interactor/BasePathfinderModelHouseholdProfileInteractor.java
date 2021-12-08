package com.adosa.opensrp.chw.household.interactor;

import androidx.annotation.VisibleForTesting;

import com.adosa.opensrp.chw.household.PathfinderModelHouseholdLibrary;
import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdProfileContract;
import com.adosa.opensrp.chw.household.util.AppExecutors;

import org.smartregister.commonregistry.CommonPersonObject;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.commonregistry.CommonRepository;

/**
 * Created by cozej4 on 08/12/2021.
 */
public class BasePathfinderModelHouseholdProfileInteractor implements BaseModelHouseholdProfileContract.Interactor {

    private AppExecutors appExecutors;

    @VisibleForTesting
    BasePathfinderModelHouseholdProfileInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BasePathfinderModelHouseholdProfileInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {// TODO implement this
    }

    @Override
    public void refreshProfileView(final String baseEntityId, final BaseModelHouseholdProfileContract.InteractorCallBack callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final CommonPersonObject personObject = getCommonRepository("ec_family_member").findByBaseEntityId(baseEntityId);
                final CommonPersonObjectClient pClient = new CommonPersonObjectClient(personObject.getCaseId(),
                        personObject.getDetails(), "");
                pClient.setColumnmaps(personObject.getColumnmaps());

                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.refreshProfileTopSection(pClient);
                    }
                });
            }
        };

        appExecutors.diskIO().execute(runnable);
    }

    public CommonRepository getCommonRepository(String tableName) {
        return PathfinderModelHouseholdLibrary.getInstance().context().commonrepository(tableName);
    }
}
