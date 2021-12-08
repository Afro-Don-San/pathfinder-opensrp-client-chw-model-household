package com.adosa.opensrp.chw.household.activity;

import android.app.Activity;
import android.content.Intent;

import com.adosa.opensrp.chw.household.R;

import org.smartregister.chw.anc.activity.BaseAncHomeVisitActivity;
import org.smartregister.chw.anc.domain.MemberObject;

import java.text.MessageFormat;

import static org.smartregister.chw.anc.util.Constants.ANC_MEMBER_OBJECTS.EDIT_MODE;
import static org.smartregister.chw.anc.util.Constants.ANC_MEMBER_OBJECTS.MEMBER_PROFILE_OBJECT;

public class BasePathfinderFpFollowUpVisitActivity extends BaseAncHomeVisitActivity {
    public static void startMe(Activity activity, MemberObject memberObject, Boolean isEditMode) {
        Intent intent = new Intent(activity, BasePathfinderFpFollowUpVisitActivity.class);
        intent.putExtra(MEMBER_PROFILE_OBJECT, memberObject);
        intent.putExtra(EDIT_MODE, isEditMode);
        activity.startActivity(intent);
    }

    @Override
    public void redrawHeader(MemberObject memberObject) {
        tvTitle.setText(MessageFormat.format("{0}, {1} \u00B7 {2}", memberObject.getFullName(), memberObject.getAge(), getString(R.string.pathfinder_fp_follow_up_visit)));
    }
}
