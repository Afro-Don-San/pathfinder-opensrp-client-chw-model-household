package com.adosa.opensrp.chw.fp.model;

import com.adosa.opensrp.chw.fp.contract.BaseFpRegisterContract;
import com.adosa.opensrp.chw.fp.util.FpJsonFormUtils;

import org.json.JSONObject;

public class BaseFpRegisterModel implements BaseFpRegisterContract.Model {
    @Override
    public JSONObject getFormAsJson(String formName, String entityId) throws Exception {
        JSONObject jsonObject = FpJsonFormUtils.getFormAsJson(formName);
        FpJsonFormUtils.getRegistrationForm(jsonObject, entityId);
        return jsonObject;
    }
}
