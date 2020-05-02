package org.smartregister.chw.fp_pathfinder.model;

import org.json.JSONObject;
import org.smartregister.chw.fp_pathfinder.contract.BaseFpRegisterContract;
import org.smartregister.chw.fp_pathfinder.util.FpJsonFormUtils;

public class BaseFpRegisterModel implements BaseFpRegisterContract.Model {
    @Override
    public JSONObject getFormAsJson(String formName, String entityId) throws Exception {
        JSONObject jsonObject = FpJsonFormUtils.getFormAsJson(formName);
        FpJsonFormUtils.getRegistrationForm(jsonObject, entityId);
        return jsonObject;
    }
}
