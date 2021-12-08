package com.adosa.opensrp.chw.household.presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdRegisterContract;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

import timber.log.Timber;

public class BasePathfinderModelHouseholdRegisterPresenter implements BaseModelHouseholdRegisterContract.Presenter, BaseModelHouseholdRegisterContract.InteractorCallBack {

    public static final String TAG = BasePathfinderModelHouseholdRegisterPresenter.class.getName();

    protected WeakReference<BaseModelHouseholdRegisterContract.View> viewReference;
    protected BaseModelHouseholdRegisterContract.Model model;
    private BaseModelHouseholdRegisterContract.Interactor interactor;

    public BasePathfinderModelHouseholdRegisterPresenter(BaseModelHouseholdRegisterContract.View view, BaseModelHouseholdRegisterContract.Model model, BaseModelHouseholdRegisterContract.Interactor interactor) {
        viewReference = new WeakReference<>(view);
        this.interactor = interactor;
        this.model = model;
    }

    @Override
    public void startForm(String formName, String entityId, String payloadType, String updateValue, JSONObject form) throws Exception {
        if (StringUtils.isBlank(entityId)) {
            return;
        }
        if (PathfinderModelHouseholdConstants.ActivityPayload.UPDATE_HOUSEHOLD_REGISTRATION_PAYLOAD_TYPE.equals(payloadType)) {
            getView().startFormActivity(form);
        } else {
            if (form == null) {
                JSONObject formAsJson = model.getFormAsJson(formName, entityId);
                getView().startFormActivity(formAsJson);
            } else
                getView().startFormActivity(form);
        }
    }

    @Override
    public void saveForm(String jsonString) {
        try {
            if (getView() != null)
                getView().showProgressDialog(R.string.saving_dialog_title);
            interactor.saveRegistration(jsonString, this);
        } catch (Exception e) {
            Timber.e(TAG, Log.getStackTraceString(e));
        }
    }

    @Override
    public void onRegistrationSaved() {
        if (getView() != null) {
            getView().onFormSaved();
        }
    }

    @Override
    public void registerViewConfigurations(List<String> list) {
//        implement
    }

    @Override
    public void unregisterViewConfiguration(List<String> list) {
//        implement
    }

    @Override
    public void onDestroy(boolean b) {
//        implement
    }

    @Override
    public void updateInitials() {
//        implement
    }

    @Nullable
    private BaseModelHouseholdRegisterContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }
}
