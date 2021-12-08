package com.adosa.opensrp.chw.household.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.v4.app.Fragment;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdRegisterContract;
import com.adosa.opensrp.chw.household.fragment.BasePathfinderModelHouseholdRegisterFragment;
import com.adosa.opensrp.chw.household.interactor.BasePathfinderModelHouseholdRegisterInteractor;
import com.adosa.opensrp.chw.household.listener.BaseModelHouseholdBottomNavigationListener;
import com.adosa.opensrp.chw.household.model.BaseModelHouseholdRegisterModel;
import com.adosa.opensrp.chw.household.presenter.BasePathfinderModelHouseholdRegisterPresenter;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;
import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.domain.Form;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.helper.BottomNavigationHelper;
import org.smartregister.listener.BottomNavigationListener;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class BasePathfinderModelHouseholdRegisterActivity extends BaseRegisterActivity implements BaseModelHouseholdRegisterContract.View {

    protected String BASE_ENTITY_ID;
    protected String DOB;
    protected String ACTION;
    protected String FORM_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BASE_ENTITY_ID = getIntent().getStringExtra(PathfinderModelHouseholdConstants.ActivityPayload.BASE_ENTITY_ID);
        DOB = getIntent().getStringExtra(PathfinderModelHouseholdConstants.ActivityPayload.DOB);
        ACTION = getIntent().getStringExtra(PathfinderModelHouseholdConstants.ActivityPayload.ACTION);
        FORM_NAME = getIntent().getStringExtra(PathfinderModelHouseholdConstants.ActivityPayload.MODEL_HOUSEHOLD_FORM_NAME);
        onStartActivityWithAction();
    }

    /**
     * Process a payload when an activity is started with an action
     */
    protected void onStartActivityWithAction() {
        if (FORM_NAME != null && ACTION != null) {
            startFormActivity(FORM_NAME, BASE_ENTITY_ID, ACTION);
        }
    }

    @Override
    public void startRegistration() {
        startFormActivity(FORM_NAME, null, null);
    }

    @Override
    public void startFormActivity(String formName, String entityId, String payloadType) {
        try {
            if (mBaseFragment instanceof BasePathfinderModelHouseholdRegisterFragment) {
                presenter().startForm(formName, entityId, payloadType, DOB, getFpFormForEdit());
            }
        } catch (Exception e) {
            Timber.e(e);
            displayToast(getString(R.string.error_unable_to_start_form));
        }
    }

    public JSONObject getFpFormForEdit() {
        return null;
    }

    @Override
    public void startFormActivity(JSONObject jsonForm) {
        Intent intent = new Intent(this, BasePathfinderModelHouseholdRegisterActivity.class);
        intent.putExtra(PathfinderModelHouseholdConstants.JsonFromExtra.JSON, jsonForm.toString());

        if (getFormConfig() != null) {
            intent.putExtra(JsonFormConstants.JSON_FORM_KEY.FORM, getFormConfig());
        }

        startActivityForResult(intent, PathfinderModelHouseholdConstants.REQUEST_CODE_GET_JSON);
    }

    @Override
    public Form getFormConfig() {
        return null;
    }

    @Override
    public void onFormSaved() {
        hideProgressDialog();
    }

    @Override
    protected void onActivityResultExtended(int requestCode, int resultCode, Intent data) {
        if (requestCode == PathfinderModelHouseholdConstants.REQUEST_CODE_GET_JSON && resultCode == RESULT_OK) {
            presenter().saveForm(data.getStringExtra(PathfinderModelHouseholdConstants.JsonFromExtra.JSON));
        }
    }

    protected Activity getModelHouseholdRegisterActivity() {
        return this;
    }

    @Override
    public List<String> getViewIdentifiers() {
        return Arrays.asList(PathfinderModelHouseholdConstants.CONFIGURATION.MODEL_HOUSEHOLD_REGISTER);
    }

    /**
     * Override this to subscribe to bottom navigation
     */
    @Override
    protected void registerBottomNavigation() {
        bottomNavigationHelper = new BottomNavigationHelper();
        bottomNavigationView = findViewById(org.smartregister.R.id.bottom_navigation);

        if (bottomNavigationView != null) {
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
            bottomNavigationView.getMenu().removeItem(org.smartregister.R.id.action_clients);
            bottomNavigationView.getMenu().removeItem(R.id.action_register);
            bottomNavigationView.getMenu().removeItem(org.smartregister.R.id.action_search);
            bottomNavigationView.getMenu().removeItem(org.smartregister.R.id.action_library);
            bottomNavigationView.inflateMenu(getMenuResource());
            bottomNavigationHelper.disableShiftMode(bottomNavigationView);
            BottomNavigationListener familyBottomNavigationListener = new BaseModelHouseholdBottomNavigationListener(this);
            bottomNavigationView.setOnNavigationItemSelectedListener(familyBottomNavigationListener);
        }
    }

    @MenuRes
    public int getMenuResource() {
        return R.menu.bottom_nav_family_menu;
    }

    @Override
    protected void initializePresenter() {
        presenter = new BasePathfinderModelHouseholdRegisterPresenter(this, new BaseModelHouseholdRegisterModel(), new BasePathfinderModelHouseholdRegisterInteractor());
    }

    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        return new BasePathfinderModelHouseholdRegisterFragment();
    }

    @Override
    protected Fragment[] getOtherFragments() {
        return new Fragment[0];
    }

    @Override
    public BaseModelHouseholdRegisterContract.Presenter presenter() {
        return (BaseModelHouseholdRegisterContract.Presenter) presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == PathfinderModelHouseholdConstants.REQUEST_CODE_GET_JSON) {
            try {
                String jsonString = data.getStringExtra(PathfinderModelHouseholdConstants.JsonFromExtra.JSON);
                JSONObject form = new JSONObject(jsonString);
                presenter().saveForm(form.toString());
            } catch (JSONException e) {
                Timber.e(e);
                displayToast(getString(R.string.error_unable_to_save_form));
            }
        } else {
            getModelHouseholdRegisterActivity().finish();
        }
    }
}
