package com.adosa.opensrp.chw.fp.presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.adosa.opensrp.chw.fp.R;
import com.adosa.opensrp.chw.fp.contract.BaseFpRegisterContract;
import com.adosa.opensrp.chw.fp.util.FpJsonFormUtils;
import com.adosa.opensrp.chw.fp.util.PathfinderFamilyPlanningConstants;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.JSONArray;
import org.json.JSONObject;
import org.smartregister.domain.Location;
import org.smartregister.repository.LocationRepository;
import org.smartregister.util.JsonFormUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import timber.log.Timber;

public class BasePathfinderFpRegisterPresenter implements BaseFpRegisterContract.Presenter, BaseFpRegisterContract.InteractorCallBack {

    public static final String TAG = BasePathfinderFpRegisterPresenter.class.getName();

    protected WeakReference<BaseFpRegisterContract.View> viewReference;
    protected BaseFpRegisterContract.Model model;
    private BaseFpRegisterContract.Interactor interactor;

    public BasePathfinderFpRegisterPresenter(BaseFpRegisterContract.View view, BaseFpRegisterContract.Model model, BaseFpRegisterContract.Interactor interactor) {
        viewReference = new WeakReference<>(view);
        this.interactor = interactor;
        this.model = model;
    }

    @Override
    public void startForm(String formName, String entityId, String payloadType, String updateValue, JSONObject form) throws Exception {
        if (StringUtils.isBlank(entityId)) {
            return;
        }
        if (PathfinderFamilyPlanningConstants.ActivityPayload.UPDATE_REGISTRATION_PAYLOAD_TYPE.equals(payloadType)) {
            getView().startFormActivity(form);
        } else {
            JSONObject formAsJson = model.getFormAsJson(formName, entityId);
            JSONObject stepOne = formAsJson.getJSONObject(JsonFormUtils.STEP1);
            JSONArray jsonArray = stepOne.getJSONArray(JsonFormUtils.FIELDS);
            if (PathfinderFamilyPlanningConstants.ActivityPayload.GIVE_FP_METHOD.equals(payloadType)) {
                JSONObject fp_method = new JSONObject();
                fp_method.put("fp_method", updateValue);
                formAsJson.put("global", fp_method);
            }else if (PathfinderFamilyPlanningConstants.ActivityPayload.CITIZEN_REPORT_CARD.equals(payloadType)) {
                JSONArray fields = formAsJson.getJSONObject("step5").getJSONArray("fields");
                for (int i = 0; i < fields.length(); i++) {
                    JSONObject object = fields.getJSONObject(i);
                    if (object.getString("key").equals("name_of_health_facility_visited_for_family_planning_services")) {
                        List<Location> locations = new LocationRepository().getAllLocations();

                        JSONObject openmrsIds = new JSONObject();
                        JSONArray values = new JSONArray();
                        for (Location location : locations) {
                            openmrsIds.put(location.getProperties().getName(), location.getId());
                            values.put(location.getProperties().getName());
                        }

                        object.put("values", values);
                        object.put("keys", values);
                        object.put("openmrs_choice_ids", openmrsIds);
                        break;
                    }
                }
            } else {
                int age = new Period(new DateTime(updateValue), new DateTime()).getYears();
                FpJsonFormUtils.updateFormField(jsonArray, PathfinderFamilyPlanningConstants.DBConstants.AGE, String.valueOf(age));
            }
            getView().startFormActivity(formAsJson);
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
    private BaseFpRegisterContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }
}
