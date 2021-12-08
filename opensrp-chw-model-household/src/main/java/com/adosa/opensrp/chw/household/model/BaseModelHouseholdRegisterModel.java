package com.adosa.opensrp.chw.household.model;

import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdRegisterContract;
import com.adosa.opensrp.chw.household.util.ModelHouseholdJsonFormUtils;

import org.json.JSONObject;

public class BaseModelHouseholdRegisterModel implements BaseModelHouseholdRegisterContract.Model {
    @Override
    public JSONObject getFormAsJson(String formName, String entityId) throws Exception {
        JSONObject jsonObject = ModelHouseholdJsonFormUtils.getFormAsJson(formName);
        ModelHouseholdJsonFormUtils.getRegistrationForm(jsonObject, entityId);
        return jsonObject;
    }
}
